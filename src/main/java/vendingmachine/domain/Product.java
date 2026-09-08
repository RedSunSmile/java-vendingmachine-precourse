package vendingmachine.domain;

public class Product {

    private final String item;
    private final int price;
    private int count;

    public Product(String item, int price, int count) {
        this.item = item;
        this.price = price;
        this.count = count;
    }

    public String takeItem() {
        return item;
    }

    public int takePrice() {
        return price;
    }

    public int takeCount() {
        return count;
    }

    public void reduceCount() {
        if (count <= 0) {
            throw new IllegalArgumentException("[ERROR] 재고수량은 양수여야 합니다.");
        }
        count--;
    }

    public boolean isSoldOut() {
        return count <= 0;
    }

}
