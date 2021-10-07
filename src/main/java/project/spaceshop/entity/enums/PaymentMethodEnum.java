package project.spaceshop.entity.enums;

public enum PaymentMethodEnum {

    CASH("Cash"),
    CARD("Credit card");

    private final String name;

    PaymentMethodEnum(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
