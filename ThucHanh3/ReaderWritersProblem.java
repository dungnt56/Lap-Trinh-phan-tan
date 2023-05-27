package ThucHanh3;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Semaphore;
public class ReaderWritersProblem {
	static Semaphore readLock = new Semaphore(1);
    static Semaphore writeLock = new Semaphore(1);
    static int readCount = 0;
    static int NUM_ARRAY = 10;

    static int[] arr = new int[NUM_ARRAY];
    
    static void startRead() throws InterruptedException {
    	readLock.acquire();
        readCount++;
        if (readCount == 1) {
            writeLock.acquire();
        }
        readLock.release();
    }
    static void endRead() throws InterruptedException {
    	readLock.acquire();
        readCount--;
        if(readCount == 0) {
            writeLock.release();
        }
        readLock.release();
    }
    static void startWrite() throws InterruptedException {
    	writeLock.acquire();
    }
    static void endWrite() {
    	writeLock.release();
    }
    static class Read implements Runnable {
        @Override
        public void run() {
            try {
                
                startRead();

                System.out.println("Thread "+Thread.currentThread().getName() + " bat dau doc du lieu");
                Random random = new Random();
                for (int i = 0; i < arr.length; i++) {
                	Long duration = (long) (Math.random() * 200);
                	int indexArr = random.nextInt(NUM_ARRAY);
        			
        			arr[indexArr] = 0;
        			if (isPrimeNumber(arr[i])) {
        				System.out.println("R "+Thread.currentThread().getName() + ": " + arr[i] +" - la SNT - Time " + duration );
        			}
        			Thread.sleep(duration);
        		}
             
//                System.out.println("Thread "+Thread.currentThread().getName() + " hoan thanh viec doc du lieu");

                
                endRead();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    static class Write implements Runnable {
        @Override
        public void run() {
            try {
                startWrite();
//                System.out.println("Thread "+Thread.currentThread().getName() + " bat dau ghi du lieu");
                
                Random random = new Random();
        		for (int i = 0; i < NUM_ARRAY; i++) {
        			
        			Long duration = (long) (Math.random() * 200);
        			
        			int indexArr = random.nextInt(NUM_ARRAY);
        			int randomNum = random.nextInt(100);
        			
        			
        			arr[indexArr] = randomNum;
        			System.out.println("W: "+ Thread.currentThread().getName()+ " : RandNum "+randomNum +" - Time "+ duration);
        			Thread.sleep(duration);
        			
        		}
                
//                System.out.println("Thread "+Thread.currentThread().getName() + " hoan thanh ghi du lieu");
                
                endWrite();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
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

    public static void main(String[] args) throws Exception {
    	int k,h;
		Scanner scanner = new Scanner(System.in);
        k = 10;
        h = 15;
//		System.out.println("Nhap k luong ghi: ");
//		k = scanner.nextInt();
//		System.out.println("Nhap h luong doc: ");
//		h = scanner.nextInt();
		
		Thread[] threadRead = new Thread[h];
		Thread[] threadWrite = new Thread[k];
		
        Read read = new Read();
        Write write = new Write();
        for(int i = 0 ; i < k; i++) {
        	threadWrite[i] = new Thread(write);
        	threadWrite[i].setName("T"+i);
        }
        for(int i = 0 ; i < h; i++) {
        	threadRead[i] = new Thread(read);
        	threadRead[i].setName("T"+i);
        }
        for(int i = 0 ; i < k; i++) {
        	threadWrite[i].start();
        }
        for(int i = 0 ; i < h; i++) {
        	threadRead[i].start();
        }
        

    }
	

}
