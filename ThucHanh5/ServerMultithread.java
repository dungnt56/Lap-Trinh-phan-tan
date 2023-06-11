package ThucHanh5;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerMultithread {
    private static final int THREAD_POOL_SIZE = 5; // Số lượng luồng trong pool

    public static void main(String[] args) {
        try {
            CalculatorSumOfArray calculator = new CalculatorSumOfArrayImpl();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("SumCalculator", calculator);
            System.out.println("Server is running...");

            ExecutorService executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

            while (true) {
                // Chấp nhận kết nối từ client
                CalculatorRequestHandler requestHandler = new CalculatorRequestHandler(calculator);
                executor.execute(requestHandler);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class CalculatorRequestHandler implements Runnable {
        private CalculatorSumOfArray calculator;

        public CalculatorRequestHandler(CalculatorSumOfArray calculator) {
            this.calculator = calculator;
        }

        @Override
        public void run() {
            try {
                ArrayList<Integer> numbers = new ArrayList<>();
                // Nhận dữ liệu từ client

                // Xử lý yêu cầu
                int sum = calculator.calculateSum(numbers);

                // Gửi kết quả về client
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
