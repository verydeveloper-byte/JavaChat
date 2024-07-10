package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	public Servidor() {

	}

	public static void main(String[] args) {

		try {
			ServerSocket server = new ServerSocket(3333);
			Socket sCliente;
			Mensajes mensajes = new Mensajes();

			while (true) {
				sCliente = server.accept();
				System.out.println("client connected");
				Thread hiloCliente = new Thread(
						new HiloCliente(sCliente, mensajes));
				hiloCliente.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
