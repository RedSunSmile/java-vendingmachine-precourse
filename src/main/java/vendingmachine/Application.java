package vendingmachine;

import vendingmachine.domain.Coins;
import vendingmachine.service.CoinGenerator;
import vendingmachine.ui.InputView;
import vendingmachine.ui.OutputView;
import java.util.Map;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        CoinGenerator coinGenerator = new CoinGenerator();
        Map<Coin,Integer> coins=coinGenerator.generate(inputView.inputOfVendingMachine());
        OutputView outputView = new OutputView();
        Coins coines=new Coins(coins);
        outputView.coinsInVendingMachine(coines);
    }
}
