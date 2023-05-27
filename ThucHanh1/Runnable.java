package ThucHanh1;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Runnable implements java.lang.Runnable {

    private List<Integer> mainArray;
    private String nameThread;
    private Integer primeSum;
    public Runnable(List<Integer> array, String thread){
        mainArray = array;
        nameThread = thread;
//        System.out.println("Create thread " + thread);
    }
    @Override
    public void run() {
        ArrayList<Integer> prime = new ArrayList<>();
        for (int i = 0; i < mainArray.size(); i++) {
            if (isPrimeNumber(mainArray.get(i))){
                System.out.println("Thread " + nameThread +
                        ": " + mainArray.get(i) + " la so nguyen to.");
                prime.add(mainArray.get(i));
            }
        }
        System.out.println("Count thread " + nameThread + ": " +
                prime.size());
        primeSum = prime.size();
    }
    public Integer getCount(){
        return primeSum;
    }
    public static boolean isPrimeNumber(int n) {
        if (n < 2) {
            return false;
        }
        int squareRoot = (int) Math.sqrt(n);
        for (int i = 2; i <= squareRoot; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
