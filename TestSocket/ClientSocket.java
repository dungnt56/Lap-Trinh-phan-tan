package TestSocket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class ClientSocket {

	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {

		int arr[] = new int[10];
		

//		Scanner inFromUser = new Scanner(System.in);
		try (Socket clientSocket = new Socket("localhost", 6786)) {
			ObjectOutputStream outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
			ObjectInputStream inFromServer = new ObjectInputStream(clientSocket.getInputStream());
			Random random = new Random();
			for (int i = 0; i < 10; i++) {
				arr[i] = random.nextInt(100);
			}
			// Gui du lieu len server
			outToServer.writeObject(arr);
			
			System.out.println("Array to Server: " + Arrays.toString(arr));
			// Lay du lieu tu server
			
			int[] arrFromServer = (int[]) inFromServer.readObject();
			

			for (int i = 0; i < arrFromServer.length; i++) {
				if (arrFromServer[i] != 0) {
					System.out.println("So nguyen to tra ve tu Server : " + arrFromServer[i]);
				}
			}

			outToServer.close();
			inFromServer.close();
			clientSocket.close();
		}

	}

}
