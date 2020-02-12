package ontology;


import jade.content.*;
import jade.util.leap.*;
import jade.core.*;

public class StockAvailability implements Concept {

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

   private int cost;
   public void setCost(int value) { 
    this.cost=value;
   }
   public int getCost() {
     return this.cost;
   }

}
