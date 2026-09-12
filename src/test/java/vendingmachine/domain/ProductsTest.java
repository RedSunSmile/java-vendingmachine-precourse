package vendingmachine.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ProductsTest {

    @Test
    void 조각이_세_개가_아니면_예외가_발생한다() {
        assertThatThrownBy(() -> new Products("[콜라,1500]"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 상품 형식이 올바르지 않습니다.");

    }

    @Test
    void 이름으로_상품을_찾는다() {
        Products products = new Products("[콜라,3500,20];[사이다,1000,10]");
        assertThat(products.findByName("콜라").takePrice()).isEqualTo(3500);
    }

    @Test
    void 이름으로_예외를_찾는다() {
        Products products = new Products("[콜라,3500,20];[사이다,1000,10]");

        assertThatThrownBy(() -> products.findByName("환타"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 존재하지 않는 상품입니다.");

    }

    @Test
    void 하나라도_남아_있으면_전체_품절이_아니다() {
        Products products = new Products("[콜라,3500,0];[사이다,1000,8]");
        assertThat(products.isAllSoldOut()).isFalse();
    }

    @Test
    void 모두_재고가_없으면_전체_품절이다() {
        Products products = new Products("[콜라,3500,0];[사이다,1000,0]");
        assertThat(products.isAllSoldOut()).isTrue();
    }

    @Test
    void 최저가를_고를_때_품절_상품은_제외한다() {
        Products products = new Products("[사이다,1000,0];[콜라,1500,5]");
        assertThat(products.minPrice()).isEqualTo(1500);
    }

    @Test
    void 재고가_있는_상품_중_최저가를_고른다() {
        Products products = new Products("[사이다,2000,10];[콜라,3500,5]");
        assertThat(products.minPrice()).isEqualTo(2000);
    }
}
