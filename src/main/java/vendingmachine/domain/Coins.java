package vendingmachine.domain;

import vendingmachine.Coin;

import java.util.EnumMap;
import java.util.Map;

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
}
