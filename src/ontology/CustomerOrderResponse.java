package ontology;


import jade.content.*;
import jade.util.leap.*;

public class CustomerOrderResponse implements Concept {

   private List customerOrderLine = new ArrayList();
   public void addCustomerOrderLine(ComponentOrderPart elem) { 
     List oldList = this.customerOrderLine;
     customerOrderLine.add(elem);
   }
   public boolean removeCustomerOrderLine(ComponentOrderPart elem) {
     List oldList = this.customerOrderLine;
     boolean result = customerOrderLine.remove(elem);
     return result;
   }
   public void clearAllCustomerOrderLine() {
     List oldList = this.customerOrderLine;
     customerOrderLine.clear();
   }
   public Iterator getAllCustomerOrderLine() {return customerOrderLine.iterator(); }
   public List getCustomerOrderLine() {return customerOrderLine; }
   public void setCustomerOrderLine(List l) {customerOrderLine = l; }

}