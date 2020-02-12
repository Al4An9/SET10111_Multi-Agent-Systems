package ontology;


import jade.content.*;
import jade.util.leap.*;
import jade.core.*;

public class ManufacturerOrderRequest implements AgentAction {

   private AID forManufacturer;
   public void setForManufacturer(AID value) { 
    this.forManufacturer=value;
   }
   public AID getForManufacturer() {
     return this.forManufacturer;
   }

}
