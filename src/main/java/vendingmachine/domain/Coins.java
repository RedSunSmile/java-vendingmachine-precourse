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

    public Coins change(int remain) {
        Map<Coin, Integer> returned = new EnumMap<>(Coin.class);
        while (remain > 0) {
            List<Integer> changeMoney = availableCoins(remain);
            if (changeMoney.isEmpty()) {
                break;
            }
            int picked = changeMoney.get(0);
            remain -= picked;
            Coin pickedCoin = Coin.from(picked);
            coins.put(pickedCoin, countOf(pickedCoin) - 1);//동전개수줄이기
            returned.put(pickedCoin, returned.getOrDefault(pickedCoin, 0) + 1);
        }
        return new Coins(returned);
    }

    //자판기 보유잔돈 개수세기
    private List<Integer> availableCoins(int remain) {
        return Arrays.stream(Coin.values())
                .filter(coin -> coin.takeAmount() <= remain && countOf(coin) > 0)
                .map(coin -> coin.takeAmount())
                .toList();
    }
}
