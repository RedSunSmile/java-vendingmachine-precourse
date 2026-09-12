package vendingmachine.domain;

import java.util.ArrayList;
import java.util.List;

public class Products {
    private final List<Product> products;

    public Products(String input) {
        this.products = parse(input);
    }

    private Product splitProducts(String input) {
        String result = input.replace("[", "");
        String result2 = result.replace("]", "");
        String[] parts = result2.split(",");

        if (parts.length != 3) {
            throw new IllegalArgumentException("[ERROR] 상품 형식이 올바르지 않습니다.");
        }
        String name = parts[0];
        int price = Integer.parseInt(parts[1]);
        int counts = Integer.parseInt(parts[2]);
        return new Product(name, price, counts);
    }

    private List<Product> parse(String input) {
        String[] chunks = input.split(";");
        List<Product> products = new ArrayList<>();
        for (String chunk : chunks) {
            products.add(splitProducts(chunk));
        }
        return products;
    }

    public Product findByName(String name) {
        for (Product product : products) {
            if (product.takeItem().equals(name))
                return product;
        }
        throw new IllegalArgumentException("[ERROR] 존재하지 않는 상품입니다.");
    }


    public boolean isAllSoldOut() {
        for (Product product : products) {
            if (!product.isSoldOut()) {
                return false;
            }
        }
        return true;
    }

    public int minPrice() {
        int min = Integer.MAX_VALUE;
        for (Product product : products) {
            if (!product.isSoldOut() && product.takePrice() < min) //품절제외 & 음료금액 부족할 때 =>최저가고르기
                min = product.takePrice();
        }
        return min;

    }
}
