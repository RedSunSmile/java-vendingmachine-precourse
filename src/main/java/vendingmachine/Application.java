package vendingmachine;

import vendingmachine.controller.VendingMachineController;
import vendingmachine.domain.*;

public class Application {
    public static void main(String[] args) {
        VendingMachineController vendingMachineController=new VendingMachineController();
        vendingMachineController.run();
    }
}
