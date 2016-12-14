package tp.pr5.logica;

public abstract class Movimiento {
	
	private Ficha turno;
	private int donde;
	private int fila;
	
	public Movimiento(Ficha color, int donde){
		turno = color;
		this.donde = donde;
	}
	
	public Ficha getJugador(){
		return turno;
	}
	
	public int getDonde(){
		return donde;
	}
	
	public int getFila(){
		return fila;
	}
	
	public void setDonde(int nuevaCol){
		donde = nuevaCol;
	}
	
	public void setFila(int nuevaFila){
		fila = nuevaFila;
	}
	
	public abstract void ejecutaMovimiento(Tablero tab) throws MovimientoInvalido;
	
	public abstract void undo(Tablero tab);
	
}
