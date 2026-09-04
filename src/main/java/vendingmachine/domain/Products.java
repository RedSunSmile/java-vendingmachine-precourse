package vendingmachine.domain;

import java.util.ArrayList;
import java.util.List;

public class Products {
  private final List<Product> products;

    public Products(String input) {
        this.products = parse(input);
    }

    private Product splitProducts(String input) {
        String result=input.replace("[","");
        String result2=result.replace("]","");
        String[] parts =result2.split(",");
        String name =parts[0];
        int price=Integer.parseInt(parts[1]);
        int counts=Integer.parseInt(parts[2]);
       return new Product(name,price,counts);
    }

    private List<Product> parse(String input){
        String[] chunks=input.split(";");
        List<Product> products=new ArrayList<>();
        for(String chunk:chunks){
            products.add(splitProducts(chunk));
        }
        return products;
    }

}
