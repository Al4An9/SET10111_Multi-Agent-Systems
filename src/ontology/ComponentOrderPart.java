package ontology;


import jade.content.*;


public class ComponentOrderPart implements Concept {

   private Hardware Hardware;
   public void setHardware(Hardware value) { 
    this.Hardware=value;
   }
   
   public Hardware getHardware() {
     return this.Hardware;
   }

   private int quantity;
   public void setQuantity(int value) { 
    this.quantity=value;
   }
   public int getQuantity() {
     return this.quantity;
   }

}