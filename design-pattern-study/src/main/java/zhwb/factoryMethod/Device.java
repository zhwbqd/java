package zhwb.factoryMethod;

public class Device {
	protected Integer price;
	protected String deviceName;

	protected Integer getPrice() {
		return price;
	}

	public Device(Integer price, String deviceName) {
		super();
		this.price = price;
		this.deviceName = deviceName;
	}

	protected void setPrice(Integer price) {
		this.price = price;
	}

	protected String getDeviceName() {
		return deviceName;
	}

	protected void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
}
