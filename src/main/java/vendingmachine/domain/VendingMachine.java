package vendingmachine.domain;

public class VendingMachine {
    private final Products products;
    private final InsertedAmount insertedAmount;

    public VendingMachine(Products products, InsertedAmount insertedAmount) {
        this.products = products;
        this.insertedAmount = insertedAmount;
    }

    public void buy(String name){
        Product product=products.findByName(name);
        validateBuyable(product);
        insertedAmount.subtract(product.takePrice());
        product.reduceCount();
    }

    private void validateBuyable(Product product){
        if(product.isSoldOut()){
            throw new IllegalArgumentException("[ERROR] 품절된 상품입니다.");
        }
        if(insertedAmount.isLessThan(product.takePrice())){
            throw new IllegalArgumentException("[ERROR] 투입 금액이 부족합니다.");
        }
    }

    public boolean isFinished() {
        return products.isAllSoldOut() || insertedAmount.isLessThan(products.minPrice());
    }
}
