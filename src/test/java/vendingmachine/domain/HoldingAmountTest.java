package vendingmachine.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class HoldingAmountTest {

    @Test
    void 음수면_예외가_발생한다() {
        assertThatThrownBy(() -> new HoldingAmount(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 금액은 0원 이상이어야 합니다.");
    }

    @Test
    void 십원_단위가_아니면_예외가_발생한다() {
        assertThatThrownBy(() -> new HoldingAmount(957))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 금액은 10원 단위여야 합니다.");
    }
}
