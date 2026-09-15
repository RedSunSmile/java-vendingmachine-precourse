package vendingmachine.domain;

import java.util.Arrays;

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

    public int takeAmount() {
        return amount;
    }

    public static Coin from(int amount) {
        return Arrays.stream(values())
                .filter(coin -> coin.amount == amount)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 동전입니다."));
    }

}
