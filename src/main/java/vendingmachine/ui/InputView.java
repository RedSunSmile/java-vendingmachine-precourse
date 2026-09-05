package vendingmachine.ui;

import camp.nextstep.edu.missionutils.Console;


public class InputView {

    public int inputOfVendingMachine() {
        System.out.println("자판기가 보유하고 있는 금액을 입력해 주세요.");
        int numbers = Integer.parseInt(Console.readLine());
        return numbers;
    }

    public String inputOfProduct() {
        System.out.println("상품명과 가격, 수량을 입력해 주세요.");
        return Console.readLine();
    }

    public int inputOfMoney() {
        System.out.println("투입 금액을 입력해 주세요.");
        int price = Integer.parseInt(Console.readLine());
        return price;
    }

    public String inputOfItemName() {
        System.out.println("구매할 상품명을 입력해 주세요.");
        String items = Console.readLine();
        return items;
    }

}

