package ThucHanh5;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerNoThread {
    public static void main(String[] args) {
        try {
            CalculatorSumOfArray calculator = new CalculatorSumOfArrayImpl();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("SumCalculator", calculator);
            System.out.println("Server is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
