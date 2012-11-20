package zhwb.factoryMethod;

public class FactoryPatternTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Sell comSell = new SellComputer();
		Sell comPrinter = new SellPrinter();
		comSell.sellDevice("this is a computer");
		comPrinter.sellDevice("this is a printer");
	}
}
