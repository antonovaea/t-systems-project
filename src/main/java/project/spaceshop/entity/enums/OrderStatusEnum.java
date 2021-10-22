package project.spaceshop.entity.enums;

public enum OrderStatusEnum {

    AWAITING_SHIPMENT ("Awaiting shipment"),

    SHIPPED ("Shipped"),

    DELIVERED ("Delivered"),

    DONE ("Done");

    private final String name;

    OrderStatusEnum(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
