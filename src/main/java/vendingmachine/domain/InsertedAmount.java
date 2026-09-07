package vendingmachine.domain;

public class InsertedAmount {
    private int amount;

    public InsertedAmount(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("[ERROR] 금액은 0원 이상이어야 합니다.");
        }
        if (amount % 10 != 0) {
            throw new IllegalArgumentException("[ERROR] 금액은 10원 단위여야 합니다.");
        }
        this.amount = amount;
    }

    public void subtract(int price) {
        if (amount < price) {
            throw new IllegalArgumentException("[ERROR] 투입 금액이 부족합니다.");
        }
        amount -= price;
    }

    public boolean isLessThan(int price) {
        return amount < price;
    }
}
