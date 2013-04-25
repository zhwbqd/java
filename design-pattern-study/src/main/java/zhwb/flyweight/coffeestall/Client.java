package zhwb.flyweight.coffeestall;

public class Client {

	private static Order[] flavors = new Flavor[20];
	private static int ordersMade = 0;
	private static FlavorFactory flavorFactory;

	private static void takeOrder(String aFlavor) {
		flavors[ordersMade++] = flavorFactory.getOrder(aFlavor);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		flavorFactory=new FlavorFactory();
		
		takeOrder("Black Coffee");
		takeOrder("Capucino");
		takeOrder("Capucino");
		takeOrder("Espresso");
		takeOrder("Capucino");
		takeOrder("Black Coffee");
		
		for (int i = 0; i < ordersMade; i++) {
			flavors[i].serve();
		}
		System.out.println("\nTotal teaFlavor object made" + flavorFactory.getTotalFlavorsMade());
	}

}
