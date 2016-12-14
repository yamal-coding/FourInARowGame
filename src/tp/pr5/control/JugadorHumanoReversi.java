package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.TableroSoloLectura;

public class JugadorHumanoReversi implements Jugador {
	
	private FactoriaReversi factoria;
	private Scanner in;
	
	public JugadorHumanoReversi(FactoriaReversi f, Scanner in){
		factoria = f;
		this.in = in;
	}
	@Override
	public Movimiento getMovimiento(TableroSoloLectura tablero, Ficha color) {
		System.out.print("Introduce la columna: ");
		int columna = in.nextInt();
		in.nextLine();
		System.out.print("Introduce la fila: ");
		int fila = in.nextInt();
		in.nextLine();
		
		return factoria.creaMovimiento(columna, fila, color);
	}

}
