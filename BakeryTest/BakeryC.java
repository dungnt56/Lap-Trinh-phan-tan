package BakeryTest;

import java.util.ArrayList;

public class BakeryC {
    static int myId;
    private static final int NUM_THREADS = 5;
    private static Bakery bakeryAlgorithm = new Bakery(NUM_THREADS);
    private static Lock lock = new Bakery(NUM_THREADS);
    static ArrayList<Integer> A = new ArrayList<>();

    public static void main(String[] args) {
        for (int i = 0; i < NUM_THREADS; i++) {
//            final int threadId = i;
            myId = i;
            Thread thread = new Thread(() -> {
                // Tiến trình yêu cầu truy cập tài nguyên chia sẻ
//                bakeryAlgorithm.requestCS(threadId);
                lock.requestCS(myId);

                // Tiến trình thực hiện công việc trên tài nguyên chia sẻ
//                System.out.println("Thread " + myId + " is accessing the shared resource.");

                CriticalSection(myId);
                // Tiến trình hoàn thành công việc và giải phóng tài nguyên chia sẻ
//                bakeryAlgorithm.releaseCS(threadId);
                lock.releaseCS(myId);
            });
            thread.start();
        }
    }
    public static void run(){
//        while (true){
        lock.requestCS(myId);
        CriticalSection(1);
        lock.releaseCS(myId);
        nonCriticalSection();
//        }
    }
    static void nonCriticalSection(){
        for (int i = 0; i < A.size(); i++) {
            if (isPrimeNumber(A.get(i))) {
                System.out.println("So nguyen to trong Thread " + myId + " : " + A.get(i));
            }
        }
    }
    static void CriticalSection(int threadId){
        System.out.println("Thread " + threadId + " bat dau");
//        for (int i = 0; i < 5; i++) {
//            double randomDouble = Math.random();
//            randomDouble = randomDouble * 100 + 1;
//            int randomInt = (int) randomDouble;
//            A.add(randomInt);
//        }
//        System.out.println("Luong " + myId + ": " + A);
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
