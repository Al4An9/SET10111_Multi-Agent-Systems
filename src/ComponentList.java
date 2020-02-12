import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ComponentList {
	private List<HardwareComponent> componentList;
	private static final int numberItemsRow = 2;
	
	public ComponentList(final List<HardwareComponent> c) {
		componentList = c;
	}
	
	public ComponentList(String csvFile) {
		List<List<String>> grid = csvFileRead(csvFile);
		componentList = stringGridToComponentlist(grid);
	}
	
	public ComponentList(String input, boolean distinguishFromOtherConstructor) {
		List<List<String>> grid = stringRead(input);
		componentList = stringGridToComponentlist(grid);
	}
	
	
	public List<HardwareComponent> getComponentLists() {
		return componentList;
	}
	
	public double getPrice() {
		double price = 0.0;
		for (HardwareComponent componentOrder: componentList) {
			price  += componentOrder.getOrderPrice();
		}
		return price;
	}
	
	public int getWarehouseManufactarerStock() {
		int warehouseStock = 0;
		for (HardwareComponent part: componentList) {
			warehouseStock += part.getComponentQuantity();
		}
		return warehouseStock;
	}
	
	// Converts a csv file to a 2d string list
	static List<List<String>> csvFileRead(String csvFile) {
		List<List<String> > grid = new ArrayList<List<String> >();
		int numberLine = 0;
		try {
			BufferedReader csvInput = new BufferedReader(new FileReader(new File(csvFile)));
			try {
				String line;
				while ((line = csvInput.readLine()) != null) {
						List<String> row = new ArrayList<String>();
						StringTokenizer tk = new StringTokenizer(line, ",");
						while (tk.hasMoreTokens())  {
							row.add(tk.nextToken().trim());
						}
						if (row.size() < numberItemsRow) {
							System.err.println(csvFile + ":" + (numberLine+1) + " has less than " + numberItemsRow + " components: " + line);
						}
						grid.add(row);
						++numberLine;
					}
				} 
				finally {
					csvInput.close();
				}
			}
			catch (IOException e) {
				System.err.println("Error opening " + csvFile + ": " + e);
			}
			return grid;
		}
		// reads string which is semi-colon seperated and comma seperated into 2d list
		private static List<List<String>> stringRead(String input) {
			List<List<String> > grid = new ArrayList<List<String> >();
			int numberLine = 0;
		
			StringTokenizer tkSemiColon = new StringTokenizer(input, ";");
			while (tkSemiColon.hasMoreTokens())  {
				String line = tkSemiColon.nextToken().trim();
				List<String> row = new ArrayList<String>();
				StringTokenizer tkComma = new StringTokenizer(line, ",");
				while (tkComma.hasMoreTokens())  {
					row.add(tkComma.nextToken().trim());
				}
				if (row.size() < numberItemsRow) {
					System.err.println(" " + (numberLine+1) + " has less than " + numberItemsRow + " components: " + line);
				}
				grid.add(row);
				++numberLine;
			}
			return grid;
		}
		
		
		private static List<HardwareComponent> stringGridToComponentlist(List<List<String>> grid) {
			List<HardwareComponent> componentLists = new ArrayList<HardwareComponent>();
			for (List<String> row: grid) {
				assert(row.size() >= numberItemsRow);
				try {
					String name = row.get(0);
					int qty = Integer.parseInt(row.get(1));
					double price = 0.0;
					if (row.size() > 2) {
						price = Double.parseDouble(row.get(2));
					}
					HardwareComponent component = new HardwareComponent(name, price, qty);
					componentLists.add(component);
				} catch (Exception e) {
					System.err.println("Error parsing string:" + e);
				}
			}
			return componentLists;
		}
		
		// checks the catalogue for the components requested
		public HardwareComponent getComponentName(String name) {
			for (HardwareComponent p: componentList) {
				if (p.getComponentName().equalsIgnoreCase(name)) {
					return p;
				}
			}
			return null;
		}
		
		// checks if component is in other list (for more than one agent supplier)
		public boolean contains(final ComponentList otherList) {
			for (HardwareComponent otherComponent: otherList.getComponentLists()) {
				HardwareComponent thisComponent = getComponentName(otherComponent.getComponentName());
				if (thisComponent == null) {
					return false;
				} else if (thisComponent.getComponentQuantity() < otherComponent.getComponentQuantity()) {
					return false;
				}
			}
			return true;
		}
		
		// removes item from the list
		public void subtract(final ComponentList otherList) {
			for (HardwareComponent otherPart: otherList.getComponentLists()) {
				HardwareComponent thisPart = getComponentName(otherPart.getComponentName());
				// Must be guaranteed by a check against contains before calling this function
				assert(thisPart != null && thisPart.getComponentQuantity() >= otherPart.getComponentQuantity());
				thisPart.setQuantity(thisPart.getComponentQuantity() - otherPart.getComponentQuantity());
			}
		}
		
		// adds to the catalogue more components
		public void add(final ComponentList otherList) {
			for (HardwareComponent otherComponent: otherList.getComponentLists()) {
				HardwareComponent thisComponent = getComponentName(otherComponent.getComponentName());
				thisComponent.setQuantity(thisComponent.getComponentQuantity() + otherComponent.getComponentQuantity());
			}
		}
		
		// calculates total price of an order
		public ComponentList getOrderPrice(final ComponentList order) {
			List<HardwareComponent> componentListPrice = new ArrayList<HardwareComponent>();
			for (HardwareComponent orderComponent: order.getComponentLists()) {
				HardwareComponent thisComponent = getComponentName(orderComponent.getComponentName());
				assert(thisComponent != null && thisComponent.getComponentQuantity() >= orderComponent.getComponentQuantity());
				HardwareComponent componentPrice = new HardwareComponent(orderComponent.getComponentName(), thisComponent.getComponentPrice(), orderComponent.getComponentQuantity());
				componentListPrice.add(componentPrice);
			}
			return new ComponentList(componentListPrice);
		}
		
		
		public String getString() {
			String componentListString = "";
			for (HardwareComponent component: getComponentLists()) {
				String componentString = component.getComponentName() + "," + component.getComponentQuantity() + "," + component.getComponentPrice();
				componentListString += componentString + ";";
			}
			return componentListString;
		}
}
