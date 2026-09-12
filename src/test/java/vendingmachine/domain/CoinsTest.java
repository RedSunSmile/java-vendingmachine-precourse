package vendingmachine.domain;

import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class CoinsTest {

    @Test
    void 최소_개수의_동전으로_거슬러_준다() {
        Map<Coin, Integer> source = new EnumMap<>(Coin.class);
        source.put(Coin.COIN_500, 1);
        source.put(Coin.COIN_100, 2);

        Coins coins = new Coins(source);
        Coins changes = coins.change(700);

        assertThat(changes.countOf(Coin.COIN_500)).isEqualTo(1);
        assertThat(changes.countOf(Coin.COIN_100)).isEqualTo(2);
    }

    @Test
    void 자판기가_보유한_동전이_부족하면_가능한_만큼만_돌려준다() {
        Map<Coin, Integer> source = new EnumMap<>(Coin.class);
        source.put(Coin.COIN_500, 1);
        source.put(Coin.COIN_100, 1);

        Coins coins = new Coins(source);
        Coins changes = coins.change(800);

        assertThat(changes.countOf(Coin.COIN_500)).isEqualTo(1);
        assertThat(changes.countOf(Coin.COIN_100)).isEqualTo(1);
        assertThat(changes.countOf(Coin.COIN_50)).isEqualTo(0);
        assertThat(changes.countOf(Coin.COIN_10)).isEqualTo(0);
    }

    @Test
    void 거스름돈으로_나간_만큼_자판기_보유량이_줄어든다() {
        Map<Coin, Integer> source = new EnumMap<>(Coin.class);
        source.put(Coin.COIN_500, 2);

        Coins coins = new Coins(source);
        coins.change(500);

        assertThat(coins.countOf(Coin.COIN_500)).isEqualTo(1);
    }
}
