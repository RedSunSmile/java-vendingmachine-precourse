package vendingmachine.domain;

public class Product {

    private final String item;
    private final int price;
    private final int count;

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

}
