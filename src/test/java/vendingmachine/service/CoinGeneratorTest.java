package vendingmachine.service;

import org.junit.jupiter.api.Test;
import vendingmachine.Coin;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class CoinGeneratorTest {

    @Test
    void 생성된_동전의_총액은_입력_금액과_같다() {
        Map<Coin, Integer> coins = new CoinGenerator().generate(450);
        int sum=0;
        for (Coin coin : Coin.values()) {
            sum+= coin.getAmount()*coins.get(coin);

        }
        assertThat(sum).isEqualTo(450);
    }

    @Test
    void 생성된_동전은_네_종류를_모두_가진다(){
        Map<Coin,Integer> coins=new CoinGenerator().generate(450);
        assertThat(coins).containsOnlyKeys(Coin.values());
    }
}
