package ThucHanh5;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class RmiClient {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 8888);
            CalculatorSumOfArray calculator = (CalculatorSumOfArray) registry.lookup("abc");

            while (true){
                int sizeOfArray = 5;
                // todo: tao mang random
                ArrayList<Integer> numbers = new ArrayList();
                for (int i=0; i<sizeOfArray; i++) {
                    double randomDouble = Math.random();
                    randomDouble = randomDouble * 100 + 1;
                    int randomInt = (int) randomDouble;
                    numbers.add(randomInt);
                }

                int sum = calculator.calculateSum(numbers);
                System.out.println("Sum: " + sum);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
