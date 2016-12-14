package tp.pr5.vista;

import java.util.Scanner;

import tp.pr5.Observer.Observer;
import tp.pr5.control.ControladorConsola;
import tp.pr5.control.FactoriaComplica;
import tp.pr5.control.FactoriaConecta4;
import tp.pr5.control.FactoriaGravity;
import tp.pr5.control.FactoriaTipoJuego;
import tp.pr5.control.Jugador;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.ReglasJuego;
import tp.pr5.logica.TableroSoloLectura;
import tp.pr5.logica.TipoJuego;

public class VistaConsola implements Observer{
	private ControladorConsola c;
	
	private Scanner in;
	
	private TableroSoloLectura tablero;
	private Ficha turnoActual;
	private boolean terminada;
	private Ficha ganador;
	
	public VistaConsola(Scanner in, ControladorConsola c){
		this.in = in;
		this.c = c;
		this.c.addObservador(this);
		
		terminada = false;
		turnoActual = Ficha.BLANCA;
		
		this.c.start();
	}
	
	public void run(){
		String opcion = "";
		
		System.out.print(tablero);
		opcion = menu();
		
		while (!terminada && !opcion.equals("salir")){
			
			try {
				switch(opcion){
					case "poner":{
						poner();
					} break;
					
					case "deshacer":{
						deshacer();
					} break;
					
					case "reiniciar":{
						reiniciar();
					} break;
					
					case "jugar":{
						cambiaJuego();
					} break;
					
					case "jugador":{
						cambiarTipoJugador();			
					} break;
					
					case "ayuda":{
						mostrarAyuda();	
					} break;
					
					default:{
						String aux = in.nextLine();
						throw new Exception();
					}
				}
			} catch (Exception e) {
				System.err.print("No te entiendo.\n");
			}
			
			System.out.print(tablero);
			
			if (!terminada)
				opcion = menu();
			else
				mostrarGanador(ganador);
			
		}
	}

	private void cambiarTipoJugador() throws Exception {
		String color = in.next();
		String tipoJugador = in. next();
		String aux = in.nextLine();
		if(!aux.equals(""))
			throw new Exception();
		else{
			if(tipoJugador.toLowerCase().equals("humano")){
				if(color.toLowerCase().equals("blancas"))
					c.cambiarJugadorAHumano(Ficha.BLANCA);
				else if (color.toLowerCase().equals("negras"))
					c.cambiarJugadorAHumano(Ficha.NEGRA);
				else
					throw new Exception();
			}
			else if(tipoJugador.toLowerCase().equals("aleatorio")){
				if(color.toLowerCase().equals("blancas"))
					c.cambiarJugadorAAleatorio(Ficha.BLANCA);
				else if (color.toLowerCase().equals("negras"))
					c.cambiarJugadorAAleatorio(Ficha.NEGRA);
				else
					throw new Exception();
			}
			else
				throw new Exception();
		}
	}

	private void cambiaJuego() throws Exception {
		String tipoJuego = in.next().toLowerCase();
		if (tipoJuego.equals("c4")){
			String aux = in.nextLine();
			if(!aux.equals(""))
				throw new Exception();
			else
				c.cambioJuego(0, 0, TipoJuego.CONECTA4);
		}
		else if (tipoJuego.equals("co")){
			String aux = in.nextLine();
			if(!aux.equals(""))
				throw new Exception();
			else
				c.cambioJuego(0, 0, TipoJuego.COMPLICA);
		}
		else if (tipoJuego.equals("gr")){
			int x = in.nextInt();
			int y = in.nextInt();
			String aux = in.nextLine(); 
			if(!aux.equals(""))
				throw new Exception();
			else
				c.cambioJuego(y, x, TipoJuego.GRAVITY);
		}
		else if (tipoJuego.equals("rv")){
			String aux = in.nextLine();
			if(!aux.equals(""))
				throw new Exception();
			else
				c.cambioJuego(0, 0, TipoJuego.REVERSI);
		}
		else{
			throw new Exception();
		}
		
		//jugadorBlancas = f.creaJugadorHumanoConsola(in);
		//jugadorNegras = f.creaJugadorHumanoConsola(in);
	}

	private void reiniciar() throws Exception {
		String aux = in.nextLine();
		if(!aux.equals(""))
			throw new Exception();
		else{
			c.reiniciar();
		}
	}

	private void deshacer() throws Exception {
		String aux = in.nextLine();
		if(!aux.equals(""))
			throw new Exception();
		else{
			c.undo();
		}
	}

	private void poner() throws Exception {
		String aux = in.nextLine();
		if(!aux.equals(""))
			throw new Exception();
		else{
			/*Movimiento mov;
			
			if (turnoActual == Ficha.BLANCA)
				mov = jugadorBlancas.getMovimiento(tablero, Ficha.BLANCA);
			else
				mov = jugadorNegras.getMovimiento(tablero, Ficha.NEGRA);
			
			c.poner(mov);*/
			c.poner();
		}
	}
	
	private void mostrarAyuda() throws Exception {
		String aux = in.nextLine();
		if(!aux.equals(""))
			throw new Exception();
		else{
			System.out.print("Los comandos disponibles son:\n\n"
					+ "PONER: utilízalo para poner la siguiente ficha."
					+ "\nDESHACER: deshace el último movimiento hecho en la partida."
					+ "\nREINICIAR: reinicia la partida."
					+ "\nJUGAR [c4|co|gr|rv] [tamX tamY]: cambia el tipo de juego."
					+ "\nJUGADOR [blancas|negras] [humano|aleatorio]: cambia el tipo de jugador."
					+ "\nSALIR: termina la aplicación."
					+ "\nAYUDA: muestra esta ayuda.\n\n");
		}
	}
		
	//procedimiento que devuelve un String con la opción introducida por el ususario
	private String menu(){
		String opcion = "";
		
		System.out.print("Juegan " + nombreFicha(turnoActual) + "\n");
		System.out.print("Qué quieres hacer? ");
		opcion = in.next();

		opcion = opcion.toLowerCase();
		
		return opcion;
	}
	
	//función que devuelve un String a partir de una Ficha
	private String nombreFicha(Ficha ficha){
		String color = "";
		
		switch (ficha){
			case BLANCA: color = "blancas"; break;
			case NEGRA: color = "negras";
		}
		
		//vacío nunca nos lo devolverá porque turno solo vale vacío cuando acabe la partida,
		//y esta función sólo se utiliza para mostrar al ganador o el turno de un jugador con la
		//partida aun sin terminar
		
		return color;
	}
	
	//procedimiento que muestra por pantalla al ganador de la partida
	private void mostrarGanador(Ficha ficha){
		
		if (ficha != Ficha.VACIA)
			System.out.print("Ganan las " + nombreFicha(ficha) + "\n");
		//en caso de que la partida se termine por que el tablero est� lleno
		//se muestra que la partida ha terminado en tablas
		else
			System.out.print("Partida terminada en tablas.\n");
		
	}

	public void onReset(TableroSoloLectura tab, Ficha turno){
		this.tablero = tab;
		turnoActual = turno;
		
		System.out.print("Partida reiniciada.\n");
	}
	
	public void onPartidaTerminada(TableroSoloLectura tab, Ficha ganador){
		//this.tablero = tab hace falta actualizar el tablero??
		terminada = true;
		this.ganador = ganador;
	}
	
	public void onCambioJuego(TableroSoloLectura tab, Ficha turno, TipoJuego tipo){
		this.tablero = tab;
		turnoActual = turno;
	}
	
	public void onUndoNotPossible(TableroSoloLectura tablero, Ficha turno){
		System.err.print("Imposible deshacer.\n");
		
	}
	
	public void onUndo(TableroSoloLectura tablero, Ficha turno, boolean hayMas){
		this.tablero = tablero;
		turnoActual = turno;
	}
	
	public void onMovimientoEnd(TableroSoloLectura tablero,
			Ficha jugador, Ficha turno){
		this.tablero = tablero;
		turnoActual = turno;
	}
	
	public void onMovimientoIncorrecto(MovimientoInvalido movimientoException){
		System.err.println(movimientoException.getMessage());
	}
	
	public void onStart(TableroSoloLectura tablero, Ficha turnoPartida, TipoJuego tipo){
		this.tablero = tablero;
		turnoActual = turnoPartida;
	}

	public void onMovStart(Ficha turno, TableroSoloLectura tab, boolean[][] pistas) {
		// TODO Auto-generated method stub
		
	}
}
