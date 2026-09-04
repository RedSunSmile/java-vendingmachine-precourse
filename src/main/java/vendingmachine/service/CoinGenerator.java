package vendingmachine.service;

import camp.nextstep.edu.missionutils.Randoms;
import vendingmachine.Coin;

import java.util.*;

public class CoinGenerator {

    public Map<Coin, Integer> generate(int totalAmount) {
        Map<Coin, Integer> result = new EnumMap<>(Coin.class);
        for (Coin coin : Coin.values()) {
            result.put(coin, 0);
        }
        int remain = totalAmount;

        calculateAboutCoins(remain, result);

        return result;
    }

    private static void calculateAboutCoins(int remain, Map<Coin, Integer> result) {
        while (remain > 0) {
            List<Integer> candidates = new ArrayList<>();
            for (Coin coin : Coin.values()) {
                if (coin.getAmount() <= remain) {
                    candidates.add(coin.getAmount());
                }
            }
            int picked = Randoms.pickNumberInList(candidates);
            result.put(Coin.from(picked), result.get(Coin.from(picked)) + 1);
            remain -= picked;
        }
    }
}
