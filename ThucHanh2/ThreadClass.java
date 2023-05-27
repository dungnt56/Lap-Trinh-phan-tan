package ThucHanh2;

import java.util.ArrayList;
import java.util.Random;

public class ThreadClass extends Thread{
    Lock lock;
    int threadID;
//    static int[] arr = new int[30];
    public static ArrayList<Integer> A = new ArrayList<>();
    public ThreadClass(int tid, Lock lock) {
        threadID = tid;
        this.lock = lock;
    }
    void CS() {
        System.out.println("Thread " + threadID + " bat dau");
        for (int i = 0; i < 5; i++) {
            // Sinh số ngẫu nhiên
            double randomDouble = Math.random();
            randomDouble = randomDouble * 100 + 1;
            int randomInt = (int) randomDouble;

            //todo: Đưa số ngẫu nhiên vào mảng dùng chung (Mảng A)
            A.add(randomInt);
        }
    }
    void nonCS() {
        for (int i = 0; i < A.size(); i++) {
            if (isPrimeNumber(A.get(i))) {
                System.out.println("So nguyen to trong Thread " + threadID + " : " + A.get(i));
            }
        }
    }
    @Override
    public void run() {
//        while (true){
            // TODO Auto-generated method stub
            lock.requestCS(threadID);
            CS();
            lock.releaseCS(threadID);
            nonCS();
//        }

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
