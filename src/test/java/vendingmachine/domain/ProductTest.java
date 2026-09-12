package vendingmachine.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ProductTest {

    @Test
    void 구십원은_100원_미만입니다(){
        assertThatThrownBy(()->new Product("콜라",90,10))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 상품 가격은 100원 이상이어야 합니다.");
    }

    @Test
    void 백오원은_10원_단위입니다(){
        assertThatThrownBy(()->new Product("사이다",105,10))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 상품 가격은 10원 단위여야 합니다.");
    }
    @Test
    void 재고수량은_없다면_0개로_양수입니다(){
        Product product=new Product("환타",1500,0);
        assertThatThrownBy(()->product.reduceCount())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 재고수량은 양수여야 합니다.");

    }
    @Test
    void 상품이_품절이면_0과_같다(){
        Product product=new Product("환타",1500,0);
        assertThat(product.isSoldOut()).isTrue();
    }


}
