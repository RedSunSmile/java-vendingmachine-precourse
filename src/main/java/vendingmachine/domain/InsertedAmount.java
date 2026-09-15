package vendingmachine.domain;

import static vendingmachine.domain.HoldingAmount.toNumber;

public class InsertedAmount {
    private int insertedMoney;

    public InsertedAmount(String input) {
        this(toNumber(input));
    }

    public InsertedAmount(int insertedMoney) {
        if (insertedMoney < 0) {
            throw new IllegalArgumentException("[ERROR] 금액은 0원 이상이어야 합니다.");
        }
        if (insertedMoney % 10 != 0) {
            throw new IllegalArgumentException("[ERROR] 금액은 10원 단위여야 합니다.");
        }
        this.insertedMoney = insertedMoney;
    }

    public void subtract(int price) {
        if (insertedMoney < price) {
            throw new IllegalArgumentException("[ERROR] 투입 금액이 부족합니다.");
        }
        insertedMoney -= price;
    }

    public boolean isLessThan(int price) {
        return insertedMoney < price;
    }

    public int takeAmount() {
        return insertedMoney;
    }

}
