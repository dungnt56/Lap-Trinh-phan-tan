package BakeryTest;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class MyThread extends Thread{
    int myId;
    Lock lock = new Bakery(10);
    ArrayList<Integer> A = new ArrayList<>();
    Random r = new Random();
    public MyThread (int id, Lock lock){
        myId = id;
        this.lock = lock;
    }
    void nonCriticalSection(){
        for (int i = 0; i < A.size(); i++) {
            if (isPrimeNumber(A.get(i))) {
                System.out.println("So nguyen to trong Thread " + myId + " : " + A.get(i));
            }
        }
    }
    void CriticalSection(){
        System.out.println("Thread " + myId + " bat dau");
        for (int i = 0; i < 5; i++) {
            double randomDouble = Math.random();
            randomDouble = randomDouble * 100 + 1;
            int randomInt = (int) randomDouble;
            A.add(randomInt);
//            int indexArr = random.nextInt(10);
//            arr[indexArr] = random.nextInt(100);
        }
        System.out.println("Luong " + myId + ": " + A);
//        for (int i = 0; i < arr.length; i++) {
//            System.out.println("arr[" + i + "] = " + arr[i]);
//        }
//        System.out.println("Thread " + threadID + " ket thuc");
    }
    public void run(){
//        while (true){
            lock.requestCS(myId);
            CriticalSection();
            lock.releaseCS(myId);
            nonCriticalSection();
//        }
    }
    public static void main(String[] args) throws IOException, InterruptedException {
        int N = 3;
        MyThread t[] = new MyThread[N];
        Lock lock = new Bakery(N);
        for (int i = 0; i < N; i++){
            t[i] = new MyThread(i, lock);
            t[i].start();
        }
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
