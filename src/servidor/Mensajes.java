package servidor;

import java.util.Observable;


public class Mensajes extends Observable {

	private String mensaje;

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
		/*
		 * notificar mensaje nuevo al
		 * resto de observadores (clientes)
		 */
		this.setChanged();
		this.notifyObservers(this.mensaje);
	}

}
