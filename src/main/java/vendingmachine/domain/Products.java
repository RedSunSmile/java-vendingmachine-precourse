package vendingmachine.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Products {
    private final List<Product> products;

    public Products(String input) {
        this.products = parse(input);
    }

    private Product splitProducts(String input) {
       return Optional.of(input.replace("[","")
               .replace("]","")
                .split(","))
               .filter(parts->parts.length==3)
               .map(parts->new Product(parts[0],Integer.parseInt(parts[1]),Integer.parseInt(parts[2])))
               .orElseThrow(()->new IllegalArgumentException("[ERROR] 상품 형식이 올바르지 않습니다."));

    }

    private List<Product> parse(String input) {
        return Arrays.stream(input.split(";"))
                .map(chunk->splitProducts(chunk))
                .toList();
    }

    public Product findByName(String name) {
       return products.stream()
               .filter(product -> product.takeItem().equals(name))
               .findAny()
               .orElseThrow(()->new IllegalArgumentException("[ERROR] 존재하지 않는 상품입니다."));
    }

    public boolean isAllSoldOut() {
        return products.stream()
                .allMatch(Product::isSoldOut);
    }

    public int minPrice() {
        return products.stream()
                .filter(product -> !product.isSoldOut())
                .mapToInt(Product::takePrice)
                .min()
                .orElse(Integer.MAX_VALUE);
    }
}
