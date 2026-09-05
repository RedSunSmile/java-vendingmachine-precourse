package vendingmachine.ui;

import vendingmachine.Coin;
import vendingmachine.domain.Coins;

public class OutputView {
    public void coinsInVendingMachine(Coins coins) {
        System.out.println("자판기가 보유한 동전");
        for (Coin coin : Coin.values()) {
            System.out.println(coin.getAmount() + "원 - " + coins.countOf(coin) + "개");
        }
    }

    public void insertedAmount(int amount) {
        System.out.println("투입 금액: " + amount + "원");
    }

    public void returnedCoins(Coins changesCoins) {
        System.out.println("잔돈");
        for (Coin coin : Coin.values()) {
            if (changesCoins.countOf(coin) > 0) {
                System.out.println(coin.getAmount() + "원 - " + changesCoins.countOf(coin) + "개");
            }
        }
    }

}

