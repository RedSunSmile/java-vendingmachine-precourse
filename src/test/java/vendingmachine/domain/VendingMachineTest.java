package vendingmachine.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class VendingMachineTest {

    @Test
    void 품절된_상품을_사려_하면_금액이_차감되지_않는다() {
        Products products = new Products("[콜라,1500,0];[사이다,1000,10]");
        InsertedAmount insertedAmount = new InsertedAmount(3000);
        VendingMachine machine = new VendingMachine(products, insertedAmount);

        assertThatThrownBy(() -> machine.buy("콜라"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 품절된 상품입니다.");

        assertThat(insertedAmount.takeAmount()).isEqualTo(3000);
    }

    @Test
    void 투입_금액이_부족하면_금액이_차감되지_않는다() {
        Products products = new Products("[콜라,2500,10]");
        InsertedAmount insertedAmount = new InsertedAmount(2000);
        VendingMachine machine = new VendingMachine(products, insertedAmount);

        assertThatThrownBy(() -> machine.buy("콜라"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 투입 금액이 부족합니다.");

        assertThat(insertedAmount.takeAmount()).isEqualTo(2000);
    }

    @Test
    void 상품을_사면_금액이_차감된다() {
        Products products = new Products("[콜라,1500,10]");
        InsertedAmount insertedAmount = new InsertedAmount(3000);
        VendingMachine machine = new VendingMachine(products, insertedAmount);

        machine.buy("콜라");

        assertThat(insertedAmount.takeAmount()).isEqualTo(1500);
    }

    @Test
    void 마지막_재고를_사면_품절된다() {
        Products products = new Products("[콜라,1500,1]");
        InsertedAmount insertedAmount = new InsertedAmount(3000);
        VendingMachine machine = new VendingMachine(products, insertedAmount);

        machine.buy("콜라");

        assertThatThrownBy(() -> machine.buy("콜라"))
                .hasMessageContaining("[ERROR] 품절된 상품입니다.");
    }

    @Test
    void 모든_상품이_품절되면_종료된다() {
        Products products = new Products("[콜라,1500,0];[사이다,1000,0]");
        InsertedAmount insertedAmount = new InsertedAmount(3000);
        VendingMachine machine = new VendingMachine(products, insertedAmount);

        assertThat(machine.isFinished()).isTrue();
    }

    @Test
    void 잔액이_최저가보다_적으면_종료된다() {
        Products products = new Products("[콜라,1500,10]");
        InsertedAmount insertedAmount = new InsertedAmount(1000);
        VendingMachine machine = new VendingMachine(products, insertedAmount);

        assertThat(machine.isFinished()).isTrue();
    }

    @Test
    void 상품이_남고_잔액도_충분히면_종료되지_않는다() {
        Products products = new Products("[콜라,1500,10]");
        InsertedAmount insertedAmount = new InsertedAmount(5000);
        VendingMachine machine = new VendingMachine(products, insertedAmount);

        assertThat(machine.isFinished()).isFalse();
    }

    @Test
    void 없는_상품을_사려_하면_예외가_발생하고_금액은_그대로다(){
        Products products=new Products("[콜라,1500,10]");
        InsertedAmount insertedAmount=new InsertedAmount(3000);
        VendingMachine machine=new VendingMachine(products,insertedAmount);

        assertThatThrownBy(()-> machine.buy("환타"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 존재하지 않는 상품입니다.");

        assertThat(insertedAmount.takeAmount()).isEqualTo(3000);
    }
}
