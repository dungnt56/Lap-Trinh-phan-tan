package ThucHanh1;

import java.util.ArrayList;
import java.util.List;

public class Main extends Thread{

    public static void main(String[] args) throws InterruptedException{
        // start initial array
        int sizeOfArray = 200000;
        int numberOfThread = 100;

        ArrayList<Integer> randomArray = new ArrayList();
        for (int i=0; i<sizeOfArray; i++) {
            double randomDouble = Math.random();
            randomDouble = randomDouble * 100 + 1;
            int randomInt = (int) randomDouble;
            randomArray.add(randomInt);
        }
//        System.out.println(randomArray);
        // end initial array

        Runnable[] arrayRunnable = new Runnable[numberOfThread];
        Thread[] arrayThread = new Thread[numberOfThread];

        for (int i = 0; i < numberOfThread; i++){
            arrayRunnable[i] = new Runnable(randomArray.subList(
                    (sizeOfArray/numberOfThread) * i, (sizeOfArray/numberOfThread) * (i+1)
            ), "Th-"+(i+1));
        }
        Integer sumOfPrime = 0;
        for (int i = 0; i < numberOfThread; i++){
            arrayThread[i] = new Thread(arrayRunnable[i]);
            arrayThread[i].start();
            arrayThread[i].join();
            sumOfPrime += arrayRunnable[i].getCount();
        }
        System.out.println("Count prime " + sumOfPrime);

    }
}