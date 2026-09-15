package vendingmachine.domain;

import java.util.*;

public class Coins {
    private final Map<Coin, Integer> coins;

    public Coins(Map<Coin, Integer> source) {
        Map<Coin, Integer> copy = new EnumMap<>(Coin.class);
        for (Coin coin : Coin.values()) {
            copy.put(coin, source.getOrDefault(coin, 0));
        }
        this.coins = copy;
    }

    public int countOf(Coin coin) {
        return coins.get(coin);
    }

    public Coins change(int amount) {
        Map<Coin, Integer> returned = new EnumMap<>(Coin.class);
        while (amount > 0) {
            List<Integer> changeMoney = availableCoins(amount);
            if (changeMoney.isEmpty()) {
                break;
            }
            int picked = changeMoney.get(0);
            amount -= picked;
            Coin pickedCoin = Coin.from(picked);
            coins.put(pickedCoin, countOf(pickedCoin) - 1);//동전개수줄이기
            returned.put(pickedCoin, returned.getOrDefault(pickedCoin, 0) + 1);
        }
        return new Coins(returned);
    }

    //자판기 보유잔돈 개수세기
    private List<Integer> availableCoins(int amount) {
        return Arrays.stream(Coin.values())
                .filter(coin -> coin.takeAmount() <= amount && countOf(coin) > 0)
                .map(coin -> coin.takeAmount())
                .toList();

    }

}
