package zhwb.factoryMethod;

public class SellComputer extends Sell {

	@Override
	protected Device getDevice(String name) {
		return new Computer(2000, name);
	}

}
