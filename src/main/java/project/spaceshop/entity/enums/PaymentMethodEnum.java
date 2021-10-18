package project.spaceshop.entity.enums;

public enum PaymentMethodEnum {

    CASH("Cash"),
    CARD("Card");

    private final String name;

    PaymentMethodEnum(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
