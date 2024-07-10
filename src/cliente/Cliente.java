package cliente;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {

	DataOutputStream output;
	Socket socket;

	public Cliente() {
		
		try {
			socket = new Socket("localhost", 3333);
			output = new DataOutputStream(socket.getOutputStream());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void mandaMsg(String msg){
		try {
			output.writeUTF(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * lanzar hilo para leer msg que lleguen del servidor
	 */
	public void leerMsg() {
		Thread hiloLector = new Thread(
				new HiloLector(socket));
		hiloLector.start();
	}

	public static void main(String[] args) {
		Cliente c = new Cliente();
		c.leerMsg();
		
		BufferedReader stdIn = new BufferedReader(
				new InputStreamReader(System.in));
		
		System.out.print("chat: ");
		String leeString;
		try {
			while ((leeString = stdIn.readLine()) != null) {
				c.mandaMsg(leeString);
				System.out.print("chat: ");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}


