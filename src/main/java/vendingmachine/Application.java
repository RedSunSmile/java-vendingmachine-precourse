package vendingmachine;

import vendingmachine.controller.VendingMachineController;
import vendingmachine.domain.*;
import vendingmachine.domain.CoinGenerator;
import vendingmachine.ui.InputView;
import vendingmachine.ui.OutputView;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Application {
    public static void main(String[] args) {
        VendingMachineController vendingMachineController=new VendingMachineController();
        vendingMachineController.run();
    }
}
