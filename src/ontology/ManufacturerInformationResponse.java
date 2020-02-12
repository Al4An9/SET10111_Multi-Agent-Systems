package ontology;


import jade.content.*;
import jade.util.leap.*;
import jade.core.*;

public class ManufacturerInformationResponse implements Concept {


   private List componentAvailability = new ArrayList();
   public void addComponentAvailability(StockAvailability elem) { 
     List oldList = this.componentAvailability;
     componentAvailability.add(elem);
   }
   public boolean removeComponentAvailability(StockAvailability elem) {
     List oldList = this.componentAvailability;
     boolean result = componentAvailability.remove(elem);
     return result;
   }
   public void clearAllComponentAvailability() {
     List oldList = this.componentAvailability;
     componentAvailability.clear();
   }
   public Iterator getAllComponentAvailability() {return componentAvailability.iterator(); }
   public List getComponentAvailability() {return componentAvailability; }
   public void setComponentAvailability(List l) {componentAvailability = l; }

   private AID forManufacturer;
   public void setForManufacturer(AID value) { 
    this.forManufacturer=value;
   }
   public AID getForManufacturer() {
     return this.forManufacturer;
   }

}
