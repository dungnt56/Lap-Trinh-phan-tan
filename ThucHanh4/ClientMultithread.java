package ThucHanh4;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ClientMultithread{
    public static void main(String[] args) throws InterruptedException {
        int numClients = 15; // Số lượng client
        int threadId = 0;
        while (true){
            threadId++;
            Thread thread = new Thread(new ClientThread(threadId));
            Thread.sleep(100);
            thread.start();
        }
//        for (int i = 0; i < numClients; i++) {
//            Thread thread = new Thread(new ClientThread(i));
//            Thread.sleep(500);
//            thread.start();
//        }
    }

    private static class ClientThread implements Runnable {
        private int clientId;

        public ClientThread(int clientId) {
            this.clientId = clientId;
        }

        @Override
        public void run() {
            try {
                Socket socket = new Socket("localhost", 8888);

                int sizeOfArray = 5;
                // todo: tao mang random
                ArrayList<Integer> randomArray = new ArrayList();
                for (int i=0; i<sizeOfArray; i++) {
                    double randomDouble = Math.random();
                    randomDouble = randomDouble * 100 + 1;
                    int randomInt = (int) randomDouble;
                    randomArray.add(randomInt);
                }
                OutputStream outputStream = socket.getOutputStream();
                String[] numbers =  new String[sizeOfArray];
                for (int i = 0; i < sizeOfArray; i++) {
                    numbers[i] = String.valueOf(randomArray.get(i));
                }
                String data = String.join(",", numbers);
                outputStream.write(data.getBytes());

                InputStream inputStream = socket.getInputStream();
                byte[] buffer = new byte[1024];
                int bytesRead = inputStream.read(buffer);
                String response = new String(buffer, 0, bytesRead);
                System.out.println("Client " + clientId + ": sum cal by Server: (" + response
                + ") with array: " + randomArray);

                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
