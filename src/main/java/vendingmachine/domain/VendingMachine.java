package vendingmachine.domain;

public class VendingMachine {
    private final Products products;
    private final InsertedAmount insertedAmount;

    public VendingMachine(Products products, InsertedAmount insertedAmount) {
        this.products = products;
        this.insertedAmount = insertedAmount;
    }
    public void buy(String name){
        Product product=products.findByName(name);//이름으로 상품 찾기
        insertedAmount.subtract(product.takePrice());//금액에서 가격 차감
        product.reduceCount();//재고 1 감소
    }

    public boolean isFinished() {
        return products.isAllSoldOut() || insertedAmount.isLessThan(products.minPrice());
    }
}
