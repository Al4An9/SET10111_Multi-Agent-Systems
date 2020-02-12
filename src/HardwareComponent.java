
public class HardwareComponent {
	private String componentName;
	private double componentPrice;
	private int componentQuantity;
	
	public HardwareComponent(String n, double p, int q) {
		componentName = n;
		componentPrice = p;
		componentQuantity = q;
	}
	
	public String getComponentName() {
		return componentName;
	}
		
	public int getComponentQuantity() {
		return componentQuantity;
	}
	
	public double getComponentPrice() {
		return componentPrice;
	}
	
	public void setQuantity(int q) {
		componentQuantity = q;
	}
	
	public double getOrderPrice() {
		return componentPrice * componentQuantity;
	}
}