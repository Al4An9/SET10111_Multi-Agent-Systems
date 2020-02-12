import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;

public class MainApp {
	public static void main(String[] args) {
		// Setup the JADE environment
		Profile myProfile = new ProfileImpl();
		Runtime myRuntime = Runtime.instance();
		ContainerController myContainer = myRuntime.createMainContainer(myProfile);
		try {
			// Start the agent controller, which is itself an agent (rma)
			AgentController rma = myContainer.createNewAgent("rma", "jade.tools.rma.rma", null);
			rma.start();

			String[] manufacturerCSV = {"../Smartphone_Manufacturer/Manufacturer.csv"};
			String[] supplierCSV1 = {"../Smartphone_Manufacturer/Supplier1.csv"};
			String[] supplierCSV2 = {"../Smartphone_Manufacturer/Supplier2.csv"};
			
			// Parameter for number of customers
			int customers = 3;
						
			for (int i = 0 ; i < customers; i++)
			{
				AgentController myCustomer = myContainer.createNewAgent("Customer"+i, CustomerAgent.class.getCanonicalName(), null);
				myCustomer.start();
			}
		
			AgentController myManufacturer = myContainer.createNewAgent("Manufacturer", ManufacturerAgent.class.getCanonicalName(), manufacturerCSV);
			myManufacturer.start();
			
			AgentController mySupplier1 = myContainer.createNewAgent("Supplier1", SupplierAgent.class.getCanonicalName(), supplierCSV1);
			mySupplier1.start();
			
			AgentController mySupplier2 = myContainer.createNewAgent("Supplier2", SupplierAgent.class.getCanonicalName(), supplierCSV2);
			mySupplier2.start();
			
			
			

		} catch(Exception e) {
			System.out.println("Exception starting agent: " + e.toString());
		}
	}
}