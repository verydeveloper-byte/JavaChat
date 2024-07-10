package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

public class HiloCliente implements Observer, Runnable {

	Socket cliente;
	Mensajes mensajes;
	DataInputStream input;
	DataOutputStream output;
	boolean conectado = true;

	public HiloCliente(Socket cliente, Mensajes mensajes) {
		this.cliente = cliente;
		this.mensajes = mensajes;

		try {
			input = new DataInputStream(cliente.getInputStream());
			output = new DataOutputStream(cliente.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Escribir en el socket del cliente el nuevo mensaje
	 */
	@Override
	public void update(Observable o, Object arg) {
		try {
			output.writeUTF(arg.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * leer mensajes del socket servidor y notificar a los 
	 * clientes.
	 */
	@Override
	public void run() {
		mensajes.addObserver(this);

		while(conectado) {
			try {
				String leido = input.readUTF();
				mensajes.setMensaje(leido);
			} catch (IOException e) {
				e.printStackTrace();
				conectado = false;
			}
		}
	}

}
