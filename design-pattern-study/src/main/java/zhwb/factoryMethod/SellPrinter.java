package zhwb.factoryMethod;

public class SellPrinter extends Sell {

	@Override
	protected Device getDevice(String name) {
		return new Printer(1000, name);
	}

}
