package cliente;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class HiloLector implements Runnable {

	Socket socket;
	DataInputStream input;
	String msgLeido;

	public HiloLector(Socket socket) {
		this.socket = socket;
		try {
			input = new DataInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				msgLeido = input.readUTF();
				System.out.println(msgLeido);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
