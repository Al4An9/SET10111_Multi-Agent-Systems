package ontology;


import jade.content.*;
import jade.util.leap.*;
import jade.core.*;

public class ManufacturerOrderResponse implements Concept {

   private List manufacturerOrderLine = new ArrayList();
   public void addManufacturerOrderLine(ComponentOrderPart elem) { 
     List oldList = this.manufacturerOrderLine;
     manufacturerOrderLine.add(elem);
   }
   public boolean removeManufacturerOrderLine(ComponentOrderPart elem) {
     List oldList = this.manufacturerOrderLine;
     boolean result = manufacturerOrderLine.remove(elem);
     return result;
   }
   public void clearAllManufacturerOrderLine() {
     List oldList = this.manufacturerOrderLine;
     manufacturerOrderLine.clear();
   }
   public Iterator getAllManufacturerOrderLine() {return manufacturerOrderLine.iterator(); }
   public List getManufacturerOrderLine() {return manufacturerOrderLine; }
   public void setManufacturerOrderLine(List l) {manufacturerOrderLine = l; }

}