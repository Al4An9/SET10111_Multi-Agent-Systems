
package ontology;

import jade.content.onto.*;
import jade.content.schema.*;
import jade.util.leap.HashMap;
import jade.content.lang.Codec;
import jade.core.CaseInsensitiveString;

public class SupplyChainOntology extends jade.content.onto.Ontology  {
  //NAME
  public static final String ONTOLOGY_NAME = "SupplyChain";
  // The singleton instance of this ontology
  private static ReflectiveIntrospector introspect = new ReflectiveIntrospector();
  private static Ontology theInstance = new SupplyChainOntology();
  public static Ontology getInstance() {
     return theInstance;
  }


   // VOCABULARY
    public static final String CUSTOMERORDERREQUEST_FORCUSTOMER="forCustomer";
    public static final String CUSTOMERORDERREQUEST="CustomerOrderRequest";
    public static final String MANUFACTURERINFORMATIONREQUEST_FORMANUFACTURER="forManufacturer";
    public static final String MANUFACTURERINFORMATIONREQUEST="ManufacturerInformationRequest";
    public static final String MANUFACTURERORDERREQUEST_FORMANUFACTURER="forManufacturer";
    public static final String MANUFACTURERORDERREQUEST="ManufacturerOrderRequest";
    public static final String Battery_2000mAh="Battery_2000mAh";
    public static final String Battery_3000mAh="Battery_3000mAh";
    public static final String RAM_8GB="RAM_8GB";
    public static final String Screen_5inch="Screen_5inch";
    public static final String MANUFACTURERORDERRESPONSE_MANUFACTURERORDERLINE="manufacturerOrderLine";
    public static final String MANUFACTURERORDERRESPONSE="ManufacturerOrderResponse";
    public static final String COMPONENTORDERPART_QUANTITY="quantity";
    public static final String COMPONENTORDERPART_COMPONENT="component";
    public static final String COMPONENTORDERPART="ComponentOrderPart";
    public static final String CUSTOMERORDERRESPONSE_CUSTOMERORDERLINE="customerOrderLine";
    public static final String CUSTOMERORDERRESPONSE="CustomerOrderResponse";
    public static final String STOCKAVAILABILITY_COST="cost";
    public static final String STOCKAVAILABILITY_QUANTITY="quantity";
    public static final String STOCKAVAILABILITY_COMPONENT="component";
    public static final String STOCKAVAILABILITY="StockAvailability";
    public static final String RAM_4GB="RAM_4GB";
    public static final String COMPONENT="Component";
    public static final String MANUFACTURERINFORMATIONRESPONSE_FORMANUFACTURER="forManufacturer";
    public static final String MANUFACTURERINFORMATIONRESPONSE_COMPONENTAVAILABILITY="componentAvailability";
    public static final String MANUFACTURERINFORMATIONRESPONSE="ManufacturerInformationResponse";
    public static final String Storage_64GB="Storage_64GB";
    public static final String Storage_256GB="Storage_256GB";
    public static final String Screen_7inch="Screen_7inch";

  /**
   * Constructor
  */
  private SupplyChainOntology(){ 
    super(ONTOLOGY_NAME, BasicOntology.getInstance());
    try { 

    // adding Concept(s)
    ConceptSchema raM_8gbSchema = new ConceptSchema(RAM_8GB);
    add(raM_8gbSchema, ontology.RAM_8GB.class);
    ConceptSchema Battery_2000mAhSchema = new ConceptSchema(Battery_2000mAh);
    add(Battery_2000mAhSchema, ontology.Battery_2000mAh.class);
    ConceptSchema Storage_64GBSchema = new ConceptSchema(Storage_64GB);
    add(Storage_64GBSchema, ontology.Storage_64GB.class);
    ConceptSchema manufacturerInformationResponseSchema = new ConceptSchema(MANUFACTURERINFORMATIONRESPONSE);
    add(manufacturerInformationResponseSchema, ontology.ManufacturerInformationResponse.class);
    ConceptSchema componentSchema = new ConceptSchema(COMPONENT);
    add(componentSchema, ontology.Hardware.class);
    ConceptSchema raM_4gbSchema = new ConceptSchema(RAM_4GB);
    add(raM_4gbSchema, ontology.RAM_4GB.class);
    ConceptSchema stockAvailabilitySchema = new ConceptSchema(STOCKAVAILABILITY);
    add(stockAvailabilitySchema, ontology.StockAvailability.class);
    ConceptSchema customerOrderResponseSchema = new ConceptSchema(CUSTOMERORDERRESPONSE);
    add(customerOrderResponseSchema, ontology.CustomerOrderResponse.class);
    ConceptSchema componentOrderPartSchema = new ConceptSchema(COMPONENTORDERPART);
    add(componentOrderPartSchema, ontology.ComponentOrderPart.class);
    ConceptSchema manufacturerOrderResponseSchema = new ConceptSchema(MANUFACTURERORDERRESPONSE);
    add(manufacturerOrderResponseSchema, ontology.ManufacturerOrderResponse.class);
    ConceptSchema Battery_3000mAhSchema = new ConceptSchema(Battery_3000mAh);
    add(Battery_3000mAhSchema, ontology.Battery_2000mAh.class);
    ConceptSchema Storage_256GBSchema = new ConceptSchema(Storage_256GB);
    add(Storage_256GBSchema, ontology.Storage_256GB.class);
    ConceptSchema Screen_5inchSchema = new ConceptSchema(Screen_5inch);
    add(Screen_5inchSchema, ontology.Screen_5inch.class);
    ConceptSchema Screen_7inchSchema = new ConceptSchema(Screen_7inch);
    add(Screen_5inchSchema, ontology.Screen_7inch.class);

    // adding AgentAction(s)
    AgentActionSchema manufacturerOrderRequestSchema = new AgentActionSchema(MANUFACTURERORDERREQUEST);
    add(manufacturerOrderRequestSchema, ontology.ManufacturerOrderRequest.class);
    AgentActionSchema manufacturerInformationRequestSchema = new AgentActionSchema(MANUFACTURERINFORMATIONREQUEST);
    add(manufacturerInformationRequestSchema, ontology.ManufacturerInformationRequest.class);
    AgentActionSchema customerOrderRequestSchema = new AgentActionSchema(CUSTOMERORDERREQUEST);
    add(customerOrderRequestSchema, ontology.CustomerOrderRequest.class);

    // adding AID(s)

    // adding Predicate(s)


    // adding fields
    manufacturerInformationResponseSchema.add(MANUFACTURERINFORMATIONRESPONSE_COMPONENTAVAILABILITY, stockAvailabilitySchema, 0, ObjectSchema.UNLIMITED);
    manufacturerInformationResponseSchema.add(MANUFACTURERINFORMATIONRESPONSE_FORMANUFACTURER, (ConceptSchema)getSchema(BasicOntology.AID), ObjectSchema.OPTIONAL);
    stockAvailabilitySchema.add(STOCKAVAILABILITY_COMPONENT, componentSchema, ObjectSchema.OPTIONAL);
    stockAvailabilitySchema.add(STOCKAVAILABILITY_QUANTITY, (TermSchema)getSchema(BasicOntology.INTEGER), ObjectSchema.OPTIONAL);
    stockAvailabilitySchema.add(STOCKAVAILABILITY_COST, (TermSchema)getSchema(BasicOntology.INTEGER), ObjectSchema.OPTIONAL);
    customerOrderResponseSchema.add(CUSTOMERORDERRESPONSE_CUSTOMERORDERLINE, componentOrderPartSchema, 0, ObjectSchema.UNLIMITED);
    componentOrderPartSchema.add(COMPONENTORDERPART_COMPONENT, componentSchema, ObjectSchema.OPTIONAL);
    componentOrderPartSchema.add(COMPONENTORDERPART_QUANTITY, (TermSchema)getSchema(BasicOntology.INTEGER), ObjectSchema.OPTIONAL);
    manufacturerOrderResponseSchema.add(MANUFACTURERORDERRESPONSE_MANUFACTURERORDERLINE, componentOrderPartSchema, 0, ObjectSchema.UNLIMITED);
    manufacturerOrderRequestSchema.add(MANUFACTURERORDERREQUEST_FORMANUFACTURER, (ConceptSchema)getSchema(BasicOntology.AID), ObjectSchema.OPTIONAL);
    manufacturerInformationRequestSchema.add(MANUFACTURERINFORMATIONREQUEST_FORMANUFACTURER, (ConceptSchema)getSchema(BasicOntology.AID), ObjectSchema.OPTIONAL);
    customerOrderRequestSchema.add(CUSTOMERORDERREQUEST_FORCUSTOMER, (ConceptSchema)getSchema(BasicOntology.AID), ObjectSchema.OPTIONAL);

    // adding name mappings

    // adding inheritance
    raM_8gbSchema.addSuperSchema(componentSchema);
    Battery_2000mAhSchema.addSuperSchema(componentSchema);
    Battery_3000mAhSchema.addSuperSchema(componentSchema);
    raM_4gbSchema.addSuperSchema(componentSchema);
    Storage_64GBSchema.addSuperSchema(componentSchema);
    Storage_256GBSchema.addSuperSchema(componentSchema);
    Screen_5inchSchema.addSuperSchema(componentSchema);
    Screen_7inchSchema.addSuperSchema(componentSchema);

   }catch (java.lang.Exception e) {e.printStackTrace();}
  }
  }