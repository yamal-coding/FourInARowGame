package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.TableroSoloLectura;

public class JugadorHumanoGravity implements Jugador {

	private Scanner in;
	private FactoriaTipoJuego factoria;
	
	public JugadorHumanoGravity(FactoriaTipoJuego f, Scanner in){
		this.in = in;
		factoria = f;
	}
	
	@Override
	public Movimiento getMovimiento(TableroSoloLectura tab, Ficha color) {
		System.out.print("Introduce la columna: ");
		int columna = in.nextInt();
		in.nextLine();
		System.out.print("Introduce la fila: ");
		int fila = in.nextInt();
		in.nextLine();
		
		return factoria.creaMovimiento(columna, fila, color);
	}

}
