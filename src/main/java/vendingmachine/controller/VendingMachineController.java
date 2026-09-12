package vendingmachine.controller;

import vendingmachine.domain.Coin;
import vendingmachine.domain.*;
import vendingmachine.ui.InputView;
import vendingmachine.ui.OutputView;

import java.util.Map;

public class VendingMachineController {
    InputView inputView = new InputView();
    OutputView outputView = new OutputView();

    public void run() {
        Map<Coin, Integer> coins = takeHoldingMoney();
        Coins sources = takeKindsOfCoinsInMachine(coins);
        Products products = takeProducts();

        InsertedAmount insertedAmount = takeInsertedAmount();
        VendingMachine vendingMachine = new VendingMachine(products, insertedAmount);

        checkStartOrEndAboutMachine(vendingMachine, insertedAmount);
        changeAboutInsertedMoney(insertedAmount, sources);
    }

    private Products takeProducts() {
        try{
            return new Products(inputView.inputOfProduct());
        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
            return takeProducts();
        }
    }

    private InsertedAmount takeInsertedAmount() {
        try {
            int insertedMoney = inputView.inputOfMoney();
            InsertedAmount insertedAmount = new InsertedAmount(insertedMoney);
            return insertedAmount;
        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
            return takeInsertedAmount();
        }
    }

    private void changeAboutInsertedMoney(InsertedAmount insertedAmount, Coins sources) {
        outputView.insertedAmount(insertedAmount.takeAmount());
        Coins changes = sources.change(insertedAmount.takeAmount());
        outputView.returnedCoins(changes);
    }

    private void checkStartOrEndAboutMachine(VendingMachine vendingMachine, InsertedAmount insertedAmount) {
        while (!vendingMachine.isFinished()) {
            outputView.insertedAmount(insertedAmount.takeAmount());
           buyItem(vendingMachine);
        }
    }

    private void buyItem(VendingMachine vendingMachine) {
        try {
            vendingMachine.buy(inputView.inputOfItemName());
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            buyItem(vendingMachine);
        }
    }

    private Coins takeKindsOfCoinsInMachine(Map<Coin, Integer> coins) {
        Coins sources = new Coins(coins);
        outputView.coinsInVendingMachine(sources);
        return sources;
    }

    private Map<Coin, Integer> takeHoldingMoney() {
        try {
            HoldingAmount holdingAmount = new HoldingAmount(inputView.inputOfVendingMachine());
            CoinGenerator coinGenerator = new CoinGenerator();
            return coinGenerator.generate(holdingAmount.takeAmount());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return takeHoldingMoney();
        }
    }

}
