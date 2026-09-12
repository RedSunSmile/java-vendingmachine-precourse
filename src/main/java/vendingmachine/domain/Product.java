package vendingmachine.domain;

public class Product {

    private final String item;
    private final int price;
    private int count;

    public Product(String item, int price, int count) {
        validatePrice(price);
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

    public void reduceCount() {
        if (count <= 0) {
            throw new IllegalArgumentException("[ERROR] 재고수량은 양수여야 합니다.");
        }
        count--;
    }

    public boolean isSoldOut() {
        return count <= 0;
    }

    private void validatePrice(int price) {
        if (price < 100) {
            throw new IllegalArgumentException("[ERROR] 상품 가격은 100원 이상이어야 합니다.");
        }
        if (price % 10 != 0) {
            throw new IllegalArgumentException("[ERROR] 상품 가격은 10원 단위여야 합니다.");
        }
    }

}
