package vendingmachine.ui;

import vendingmachine.Coin;
import vendingmachine.domain.Coins;

public class OutputView {

    public void coinsInVendingMachine(Coins coins) {
        System.out.println("자판기가 보유한 동전");
        for(Coin coin:Coin.values()){
            System.out.println(coin.getAmount() + "원 - " + coins.countOf(coin) + "개");
        }
    }

    public void resultOfInputtingCoins(int coins) {
        System.out.println("투입금액: " + coins);
        System.out.println("잔돈");
        System.out.println("100원 - " + coins + "개");
        System.out.println("50원 - " + coins + "개");
    }

}
