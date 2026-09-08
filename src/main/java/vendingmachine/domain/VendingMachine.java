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
        insertedAmount.subtract(product.takePrice());
        product.reduceCount();
    }

    public boolean isFinished() {
        return products.isAllSoldOut() || insertedAmount.isLessThan(products.minPrice());
    }
}
