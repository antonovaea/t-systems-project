package project.spaceshop.entity.enums;

public enum PaymentStatusEnum {

    AWAITING_PAYMENT ("Awaiting payment"),
    PAID ("Paid");

    private final String name;

    PaymentStatusEnum(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
