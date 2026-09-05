package vendingmachine.domain;

public class HoldingAmount {
    private final int amount;

    public HoldingAmount(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("[ERROR] 금액은 0원 이상이어야 합니다.");
        }
        if (amount % 10 != 0) {
            throw new IllegalArgumentException("[ERROR] 금액은 10원 단위여야 합니다.");
        }
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}
