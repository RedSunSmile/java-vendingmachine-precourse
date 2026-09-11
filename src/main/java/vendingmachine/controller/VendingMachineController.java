package vendingmachine.controller;

import vendingmachine.Coin;
import vendingmachine.domain.*;
import vendingmachine.ui.InputView;
import vendingmachine.ui.OutputView;

import java.util.Map;

public class VendingMachineController {
    InputView inputView=new InputView();
    OutputView outputView=new OutputView();

    public void run(){
        Map<Coin,Integer> coins=takeHoldingMoney();
        Coins sources=takeKindsOfCoinsInMachine(coins);
        String productInput = inputView.inputOfProduct();
        Products products = new Products(productInput);

        int insertedMoney = inputView.inputOfMoney();
        InsertedAmount insertedAmount = new InsertedAmount(insertedMoney);
        VendingMachine vendingMachine = new VendingMachine(products, insertedAmount);

        checkStartOrEndAboutMachine(vendingMachine, insertedAmount);
        changeAboutInsertedMoney(insertedAmount, sources);
    }

    private void changeAboutInsertedMoney(InsertedAmount insertedAmount, Coins sources) {
        outputView.insertedAmount(insertedAmount.takeAmount());
        Coins changes= sources.change(insertedAmount.takeAmount());
        outputView.returnedCoins(changes);
    }

    private void checkStartOrEndAboutMachine(VendingMachine vendingMachine, InsertedAmount insertedAmount) {
        while (!vendingMachine.isFinished()) {
            outputView.insertedAmount(insertedAmount.takeAmount());
            String itemName = inputView.inputOfItemName();
            vendingMachine.buy(itemName);
        }
    }

    private Coins takeKindsOfCoinsInMachine(Map<Coin,Integer> coins) {
        Coins sources = new Coins(coins);
        outputView.coinsInVendingMachine(sources);
        return sources;
    }

    private Map<Coin,Integer> takeHoldingMoney() {
        CoinGenerator coinGenerator = new CoinGenerator();
        HoldingAmount holdingAmount = new HoldingAmount(inputView.inputOfVendingMachine());
        Map<Coin, Integer> coins = coinGenerator.generate(holdingAmount.getAmount());
        return coins;
    }

}
