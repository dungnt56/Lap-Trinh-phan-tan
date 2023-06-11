package ThucHanh5;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
public class CalculatorSumOfArrayImpl extends UnicastRemoteObject implements CalculatorSumOfArray {
    public CalculatorSumOfArrayImpl() throws RemoteException {
        super();
    }

    public int calculateSum(ArrayList<Integer> numbers) throws RemoteException {
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return sum;
    }
}
