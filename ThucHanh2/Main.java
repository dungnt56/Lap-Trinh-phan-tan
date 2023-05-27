package ThucHanh2;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        int NUM_THREADS = 5;
        ArrayList AShare = new ArrayList<>();

        Lock lock = new Bakery(NUM_THREADS);

        ThreadClass[] threadArr = new ThreadClass[NUM_THREADS] ;
        for (int i = 0; i < NUM_THREADS; i++){
            ThreadClass thread = new ThreadClass(i, lock);
            threadArr[i] = thread;
        }
        for (int i = 0; i < NUM_THREADS; i++){
            final int threadId = i;
            Thread thread = new Thread(() -> {
                threadArr[threadId].run();
            });
            thread.start();
        }
    }
}