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
        Map<Coin, Integer> coins = readHoldingCoins();
        Coins sources = new Coins(coins);
        outputView.coinsInVendingMachine(sources);
        Products products = readProducts();

        InsertedAmount insertedAmount = readInsertedAmount();
        VendingMachine vendingMachine = new VendingMachine(products, insertedAmount);

        checkStartOrEndAboutMachine(vendingMachine, insertedAmount);
        changeAboutInsertedMoney(insertedAmount, sources);
    }

    private Products readProducts() {
        try {
            return new Products(inputView.inputOfProduct());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return readProducts();
        }
    }

    private InsertedAmount readInsertedAmount() {
        try {
            return new InsertedAmount(inputView.inputOfMoney());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return readInsertedAmount();
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
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            buyItem(vendingMachine);
        }
    }

    private Map<Coin, Integer> readHoldingCoins() {
        try {
            HoldingAmount holdingAmount = new HoldingAmount(inputView.inputOfVendingMachine());
            CoinGenerator coinGenerator = new CoinGenerator();
            return coinGenerator.generate(holdingAmount.takeAmount());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return readHoldingCoins();
        }
    }

}
