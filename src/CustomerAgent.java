import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class CustomerAgent extends Agent{
	
	// Order string for customer t pass through messages
	private String customerOrder;
	// The list of manufacturer agents (only one)
	private AID[] manufacturerAgents;
	// Checks if customer has to order a new order
	boolean newOrder = true;
	int day;
 	protected void setup() {

 		System.out.println("\nCustomer agent " + getAID().getName() + " is starting.");
 		
 		Object[] args = getArguments();
 		if (args == null) {
 			double unitprice = Math.floor(100+500*Math.random()); 
 	 		 				
 			// TickerBehaviour which requests to manufacturer whether order can be fulfilled
 			addBehaviour(new TickerBehaviour(this, 2500) {
 					protected void onTick() {
 						if(newOrder == true) {
 				 
 				 	 		// Setup quantity randomly between 1-50
 				 	        int QuantityofSmartphones = (int) Math.floor(1+50*Math.random());
 				 	 	    float randomOrder = (float)Math.random();
 				
 				 			customerOrder = (String) orderCustomer(randomOrder, QuantityofSmartphones);
 				 			//System.out.println(getAID().getName() + ") Customer order is: '" + customerOrder + "'");
 			 	 		}
 						System.out.println("\n"+getAID().getName() + ") Is ordering: '" + customerOrder.toString() + "'");
 						// Update whether orders can be fulfilled
 						DFAgentDescription temp = new DFAgentDescription();
 						ServiceDescription sd = new ServiceDescription();
 						sd.setType("manufacturer");
 						temp.addServices(sd);
 						try {
 							DFAgentDescription[] res = DFService.search(myAgent, temp); 
 							manufacturerAgents = new AID[res.length];
 							for (int i = 0; i < res.length; ++i) {
 								manufacturerAgents[i] = res[i].getName();
 								System.out.println(manufacturerAgents[i].getName());
 							}
 						}
 						catch (FIPAException fe) {
 							fe.printStackTrace();
 						}

 						// Perform the request
 						myAgent.addBehaviour(new RequestCustomerOrder());
 					}
 				} );
 		// TickerBehaviour to iterate day
				addBehaviour(new TickerBehaviour(this, 4950) {
					protected void onTick() {
						if (day == 100) {
							// termination of agent
							System.out.println(getAID().getName() + " terminating.");
							doDelete();
						}else {
							day += 1;
						}
					}
				} );
 		}
		else {
			// termination of agent
			System.out.println(getAID().getName() + " terminating.");
			doDelete();
		}
 	}
 	
 	// Randomises a customer order according to smartphone or phablet
 	public String orderCustomer(float random, int orderSmartphoneQuantity) {
 		//random = (float)Math.random();
		String screen, battery, memory, storage;
		
		if (random<0.5) {
			screen = "5inch Screen"+","+orderSmartphoneQuantity+",0.0;";
			battery = "2000mAh Battery"+","+orderSmartphoneQuantity+",0.0;";
			memory = "4Gb RAM"+","+orderSmartphoneQuantity+",0.0;";
			storage = "64Gb Storage"+","+orderSmartphoneQuantity+",0.0;";
		} else {
			screen = "7inch Screen"+","+orderSmartphoneQuantity+",0.0;";
			battery = "3000mAh Battery"+","+orderSmartphoneQuantity+",0.0;";
			memory = "8Gb RAM"+","+orderSmartphoneQuantity+",0.0;";
			storage = "256Gb Storage"+","+orderSmartphoneQuantity+",0.0;";
		}
		String[] order = {screen+battery+memory+storage};
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < order.length; i++) {
		   s.append(order[i]);
		}
		String orderSpecifications = s.toString();
		return orderSpecifications;
	}
 	
 	
 	// Behaviour asking manufacturer to fulfil a customer order
	private class RequestCustomerOrder extends Behaviour {
		private AID manufacturer;     // manufacturer agent to send orders too
		private double orderPrice;   // price of order
		private int numberReplies = 0; 	// total replies form manufacturer
		private MessageTemplate mt; 
		private int step = 0;

		public void action() {
			switch (step) {
			case 0:
				// Send cfp to manufacturer agent
				ACLMessage cfp = new ACLMessage(ACLMessage.CFP);
				for (int i = 0; i < manufacturerAgents.length; ++i) {
					cfp.addReceiver(manufacturerAgents[i]);
				} 
				cfp.setContent(customerOrder);
				cfp.setConversationId("customer-order");
				cfp.setReplyWith("cfp"+System.currentTimeMillis());
				myAgent.send(cfp);
				mt = MessageTemplate.and(MessageTemplate.MatchConversationId("customer-order"),
						MessageTemplate.MatchInReplyTo(cfp.getReplyWith()));
				step = 1;
				break;
			case 1:
				// receive all proposals and refusals
				ACLMessage reply = myAgent.receive(mt);
				if (reply != null) {
					// get reply from manufacturer
					if (reply.getPerformative() == ACLMessage.PROPOSE) {
						// The offer from manufacturer
						double price = Double.parseDouble(reply.getContent());
						if (manufacturer == null || price < orderPrice) {
							orderPrice = price;
							manufacturer = reply.getSender();
						}
					}
					numberReplies++;
					if (numberReplies >= manufacturerAgents.length) {
						// checks all replies received
						step = 2; 
					}
				}
				else {
					block();
				}
				break;
			case 2:
				// sends accept proposal to manufacturer
				ACLMessage order = new ACLMessage(ACLMessage.ACCEPT_PROPOSAL);
				order.addReceiver(manufacturer);
				order.setContent(customerOrder);
				order.setConversationId("customer-order");
				order.setReplyWith("order" + System.currentTimeMillis());
				myAgent.send(order);
				mt = MessageTemplate.and(MessageTemplate.MatchConversationId("customer-order"),
						MessageTemplate.MatchInReplyTo(order.getReplyWith()));
				step = 3;
				break;
			case 3:      
				// Receive the purchased order reply
				reply = myAgent.receive(mt);
				if (reply != null) {
					// order purchase reply received
					if (reply.getPerformative() == ACLMessage.INFORM) {
						// if order successful print
						System.out.println(getAID().getName() + ") '" + customerOrder + "' successfully bought from agent " + reply.getSender().getName());
						System.out.println("Price = £" + orderPrice);
						// order satisfied customer can now request new order
						newOrder = true;
					}
					else {
						System.out.println(getAID().getName() + ") Attempt failed: Could not buy '" + customerOrder + "'");
						newOrder = false;
					}
					step = 4;
				}
				else {
					block();
				}
				break;
			}        
		}

		public boolean done() {
			if (step == 2 && manufacturer == null) {
				System.out.println("Attempt failed: '" + customerOrder + "' could not be fulfilled");
			}
			return ((step == 2 && manufacturer == null) || step == 4);
		}
	}
	
	protected void takeDown() {
 		System.out.println("Customer "+getAID().getName()+" terminating");
 	}
 	
	}