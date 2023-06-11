package ThucHanh5;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
public interface CalculatorSumOfArray extends Remote{
    int calculateSum(ArrayList<Integer> numbers) throws RemoteException;
}
