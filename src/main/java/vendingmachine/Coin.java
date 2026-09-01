package vendingmachine;

public enum Coin {
    COIN_500(500),
    COIN_100(100),
    COIN_50(50),
    COIN_10(10);

    private final int amount;

    Coin(final int amount) {
        this.amount = amount;
    }

    // 추가 기능 구현

    public int getAmount() {
        return amount;
    }

    public static Coin from(int amount) {
        for (Coin coin : values()) {
            if (coin.amount == amount) {
                return coin;
            }
        }
        throw new IllegalArgumentException("[ERROR] 존재하지 않는 동전입니다.");
    }
}
