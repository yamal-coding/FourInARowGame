package tp.pr5.logica;

import java.util.ArrayList;

import tp.pr5.Observer.Observer;
import tp.pr5.control.Jugador;


public class Partida {
	private Tablero tablero;
	private Ficha turno;
	private boolean terminada;
	private Ficha ganador;
	private ReglasJuego reglas;

	private UndoStack pila;
	private int numUndo;
	
	public static final int N_UNDO = 10;
	
	//lista de observadores
	ArrayList<Observer> Obs;
	
	//constructor de la clase Partida que crea una partida nueva
	public Partida(ReglasJuego reglas){
		Obs = new ArrayList<Observer>();
		reset(reglas);

		pila = new UndoStack(N_UNDO);
	}
	
	//procedimiento que inicia la partida
	public void start(){

		for (Observer o : Obs){
			o.onStart(tablero, reglas.jugadorInicial(), reglas.getTipoJuego());
		}
		
		continuarPartida();
	}
	
	//procedimiento que reinicia una partida
	public void reset(ReglasJuego reglas){
		this.reglas = reglas;
		tablero = reglas.iniciaTablero();
		turno = reglas.jugadorInicial();
		terminada = false;
		numUndo = 0;
		ganador = Ficha.VACIA;
		Ficha.BLANCA.cambiarModo(TipoTurno.HUMANO, new ModoHumano());
		Ficha.NEGRA.cambiarModo(TipoTurno.HUMANO, new ModoHumano());
	}
	
	public void reiniciar(ReglasJuego reglas){
		detenerPartida();
		reset(reglas);

		for (Observer o : Obs){
			o.onReset(tablero, turno);
		}
	}
	
	public void cambioJuego(ReglasJuego reglas){
		detenerPartida();
		reset(reglas);
		
		for (Observer o : Obs){
			o.onCambioJuego(tablero, turno, reglas.getTipoJuego());
		}
	}
	
	//funci�n que devuelve el color de la ficha a la que le toca jugar
	public Ficha getTurno(){
		return turno;
	}
	
	public void ejecutaMovimiento(Movimiento mov){
		
		if((terminada) || (mov.getJugador() != turno)){
			//throw new MovimientoInvalido("Movimiento incorrecto.");
			for (Observer o : Obs)
				o.onMovimientoIncorrecto(new MovimientoInvalido("Movimiento incorrecto."));
		}
		else {
			try {
				mov.ejecutaMovimiento(tablero);
				
				//una vez realizado el movimiento lo insertamos en la pila undoStack
				pila.push(mov);
				if(numUndo < N_UNDO)
					numUndo++;
				
				//y comprobamos si la patida ha terminado con un grupo de 4 fichas
				if(numUndo > 0){
					if(reglas.tablas(pila.getUltimo().getJugador(), tablero) || (reglas.hayGanador(pila.getUltimo(), tablero) != Ficha.VACIA)){
						terminada = true;
					}
				}
				
				//al final cambia el turno para que juegue el siguiente jugador
				turno = reglas.siguienteTurno(turno, tablero);		
				
				if (!terminada){
					for (Observer o : Obs)
						o.onMovimientoEnd(tablero, reglas.siguienteTurno(turno, tablero), turno);
					continuarPartida();
				}
				else{
					for (Observer o : Obs)
						o.onPartidaTerminada(tablero, getGanador());
				}
			} catch (MovimientoInvalido e) {
				for (Observer o : Obs)
					o.onMovimientoIncorrecto(e);
			}
		}
	}
	
	//funci�n que devuelve el tablero de una partida
	public Tablero getTablero(){
		return tablero;
	}
	
	//funci�n que deshace un movimiento y devuelve true si ha sido posible,
	//y false en caso contrario
	public boolean undo(){
		boolean ok = true;
		
		//comprobamos si hay movimientos que deshacer
		if(numUndo == 0 || numUndo > N_UNDO){
			ok = false;
			for (Observer o : Obs)
				o.onUndoNotPossible(tablero, turno);
		}
		else{
			//se deshace el movimiento segun el tipo de movimiento que sea
			pila.pop().undo(tablero);
			numUndo--;	
			
			//y cambiamos el turno
			turno = reglas.siguienteTurno(turno, tablero);
			
			for (Observer o : Obs)
				o.onUndo(tablero, turno, numUndo > 0);
		}
			
		return ok;
	}
	
	//funci�n que devuelve el valor del atributo termindaa de partida
	public boolean isTerminada(){
		
		return terminada;
	}
	
	//funci�n que devuelve el color de la ficha que ha ganado la partida
	//(la partida debe haber terminado)
	public Ficha getGanador(){
		
		if(terminada){
			if(!reglas.tablas(pila.getUltimo().getJugador(), tablero))
				//ganador = pila.getUltimo().getJugador();
				ganador = reglas.hayGanador(pila.getUltimo(), tablero);
		}
		
		return ganador;
	}
	
	public Movimiento siguenteMovimiento(Jugador jugador, Ficha color){
		return jugador.getMovimiento(tablero, color);
	}
	
	public String dameTablero(){
		return tablero.toString();
	}
	
	public void addObserver(Observer obs){
		this.Obs.add(obs);
	}
	
	public void detenerPartida(){
		turno.getModo().terminar();
	}
	
	public void continuarPartida(){
		if(!terminada){
			
			boolean[][] pistas = reglas.pistas(tablero, turno);
			
			for (Observer o : Obs)
				o.onMovStart(turno, tablero, pistas);
		}
	}
}