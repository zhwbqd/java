package zhwb.factoryMethod;

public abstract class Sell {
	public void sellDevice(String name) {
		Device device = getDevice(name);
		System.out.println("Price: " + device.getPrice());
		System.out.println("Device is : " + device.getDeviceName());
	}

	abstract protected Device getDevice(String num);
}
