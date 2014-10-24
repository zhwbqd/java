package domain;

/**
 * @author jack.zhang
 * @since 2014/10/24
 */
public class Person {
    private Address address;

    private Integer saleRegion;

    private Double discount;

    public Integer getSaleRegion() {
        return saleRegion;
    }

    public void setSaleRegion(Integer saleRegion) {
        this.saleRegion = saleRegion;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
