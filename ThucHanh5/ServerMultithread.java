//package ThucHanh5;
//
//import java.rmi.RemoteException;
//import java.rmi.registry.LocateRegistry;
//import java.rmi.registry.Registry;
//import java.rmi.server.UnicastRemoteObject;
//import java.util.ArrayList;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//public class ServerMultithread {
//
//    public static void main(String[] args) {
//        try {
//            CalculatorSumOfArray calculator = new CalculatorSumOfArrayImpl();
//            Registry registry = LocateRegistry.createRegistry(1099);
//            registry.rebind("SumCalculator", calculator);
//            System.out.println("Server is running...");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    static class CalculatorRequestHandler implements Runnable {
//        private CalculatorSumOfArray calculator;
//
//        public CalculatorRequestHandler(CalculatorSumOfArray calculator) {
//            this.calculator = calculator;
//        }
//
//        @Override
//        public void run() {
//            try {
//                ArrayList<Integer> numbers = new ArrayList<>();
//                int sum = calculator.calculateSum(numbers);
//            } catch (RemoteException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
