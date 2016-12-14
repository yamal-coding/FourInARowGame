package tp.pr5.logica;

public class UndoStack {
	private int puntero;
	private int max;
	private Movimiento [] pila;
		
	public UndoStack(int x){
		puntero = 0;
		max = x;
		pila = new Movimiento[x];
	}
	
	public void push(Movimiento mov){
		pila[puntero] = mov;
		
		if(puntero == (max - 1))
			puntero = 0;
		else
			puntero++;
	}
	
	public Movimiento pop(){
		if (puntero == 0)
			puntero = max - 1;
		else
			puntero--;
		
		return pila[puntero];
	}
	
	public Movimiento getUltimo(){
		int posicion;
		
		if (puntero == 0)
			posicion = max - 1;
		else
			posicion = puntero - 1;
		
		return pila[posicion];
	}
}
