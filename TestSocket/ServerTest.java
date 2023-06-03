package TestSocket;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
public class ServerTest {
    public static void main(String[] args) {
        try {
            // Tạo Socket Server và lắng nghe kết nối từ khách hàng
            ServerSocket serverSocket = new ServerSocket(8888);
            System.out.println("Server waiting for connection...");

            Socket socket = serverSocket.accept();
            System.out.println("Client connected.");

            // Nhận mảng từ khách hàng
            InputStream inputStream = socket.getInputStream();
            byte[] buffer = new byte[1024];
            int bytesRead = inputStream.read(buffer);
            String data = new String(buffer, 0, bytesRead);
            String[] numbers = data.split(",");

            // Tính tổng mảng
            int sum = 0;
            for (String number : numbers) {
                sum += Integer.parseInt(number.trim());
            }

            // Gửi tổng về cho khách hàng
            OutputStream outputStream = socket.getOutputStream();
            String response = String.valueOf(sum);
            outputStream.write(response.getBytes());

            // Đóng kết nối
            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
