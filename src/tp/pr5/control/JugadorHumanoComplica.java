package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.TableroSoloLectura;

public class JugadorHumanoComplica implements Jugador {

	private Scanner in;
	private FactoriaTipoJuego factoria;
	
	public JugadorHumanoComplica(FactoriaTipoJuego f, Scanner in){
		this.in = in;
		factoria = f;
	}
	
	@Override
	public Movimiento getMovimiento(TableroSoloLectura tab, Ficha color) {
		System.out.print("Introduce la columna: ");
		int columna = in.nextInt();
		in.nextLine();
		
		return factoria.creaMovimiento(columna, 0, color);
	}

}
