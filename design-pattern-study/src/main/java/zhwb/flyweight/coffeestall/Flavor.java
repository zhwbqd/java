package zhwb.flyweight.coffeestall;

public class Flavor extends Order {

	private String flavor;

	public Flavor(String flavor) {
		this.flavor = flavor;
	}

	@Override
	public void serve() {
		System.out.println("Server flaour" + flavor);
	}

	@Override
	public String getFlavor() {
		return this.flavor;
	}

}
