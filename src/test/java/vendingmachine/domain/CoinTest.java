package vendingmachine.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CoinTest {

    @Test
    void 금액으로_동전을_찾는다() {

        assertThat(Coin.from(500)).isEqualTo(Coin.COIN_500);
    }

    @Test
    void 없는_금액이면_예외가_발생한다() {
        assertThatThrownBy(() -> Coin.from(7))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 존재하지 않는 동전입니다.");
    }
}
