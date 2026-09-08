package vendingmachine;

import vendingmachine.domain.*;
import vendingmachine.service.CoinGenerator;
import vendingmachine.ui.InputView;
import vendingmachine.ui.OutputView;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();

        CoinGenerator coinGenerator = new CoinGenerator();
        HoldingAmount holdingAmount = new HoldingAmount(inputView.inputOfVendingMachine());
        Map<Coin, Integer> coins = coinGenerator.generate(holdingAmount.getAmount());
        OutputView outputView = new OutputView();
        Coins sources = new Coins(coins);
        outputView.coinsInVendingMachine(sources);

        String productInput = inputView.inputOfProduct();
        Products products = new Products(productInput);
        int insertedMoney = inputView.inputOfMoney();
        InsertedAmount insertedAmount=new InsertedAmount(insertedMoney);
        VendingMachine vendingMachine=new VendingMachine(products,insertedAmount);

        while(!vendingMachine.isFinished()){
            outputView.insertedAmount(insertedAmount.takeAmount());
            String itemName=inputView.inputOfItemName();
            vendingMachine.buy(itemName);
        }
    }
}
