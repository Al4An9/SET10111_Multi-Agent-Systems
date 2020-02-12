package ontology;


import jade.content.*;
import jade.core.*;

public class CustomerOrderRequest implements AgentAction {

   private AID forCustomer;
   public void setForCustomer(AID value) { 
    this.forCustomer=value;
   }
   public AID getForCustomer() {
     return this.forCustomer;
   }

}
