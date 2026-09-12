package vendingmachine.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class InsertedAmountTest {

    @Test
    void 음수면_예외가_발생한다(){
        assertThatThrownBy(()->new InsertedAmount(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 금액은 0원 이상이어야 합니다.");
    }

    @Test
    void 십원_단위가_아니면_예외가_발생한다(){
        assertThatThrownBy(()->new InsertedAmount(957))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 금액은 10원 단위여야 합니다.");
    }
    @Test
    void 상품_가격만큼_차감된다(){
    InsertedAmount insertedAmount=new InsertedAmount(3000);

    insertedAmount.subtract(1500);
    assertThat(insertedAmount.takeAmount()).isEqualTo(1500);

    }

    @Test
    void 잔액보다_비싸면_예외가_발생하고_금액은_그대로다(){
        InsertedAmount insertedAmount=new InsertedAmount(1000);

        assertThatThrownBy(()->insertedAmount.subtract(1500))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 투입 금액이 부족합니다.");

        assertThat(insertedAmount.takeAmount()).isEqualTo(1000);
    }

    @Test
    void 잔액이_가격보다_적으면_참이다(){
        InsertedAmount insertedAmount=new InsertedAmount(500);

        assertThat(insertedAmount.isLessThan(1000)).isTrue();
    }
}
