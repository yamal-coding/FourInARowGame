package tp.pr5.logica;


public class Tablero implements TableroSoloLectura{
	private Ficha [ ][ ] tablero;
	private int ancho;
	private int alto;
	
	//constructor de un objeto Tablero que inicializa su anchura y alto, y lo vac�a
	public Tablero(int tx, int ty){
		if (tx <= 0 || ty <= 0){
			ancho = 1;
			alto = 1;
			tablero = new Ficha[alto][ancho];
		}
		else {
			ancho = tx; //n�mero de columnas o ancho
			alto = ty; //n�mero de filas o alto
			tablero = new Ficha[alto][ancho];
		}
		vaciarTablero(); //vaciamos el tablero, en este caso es una inicializaci�n
	}
	
	//procedimiento que vac�a el tablero
	public void vaciarTablero(){
		for (int f = 0; f < alto; f++){
			for (int c = 0; c < ancho; c++){
				tablero[f][c] = Ficha.VACIA;
			}
		}
	}
	
	//funci�n que devuelve el ancho del tablero
	public int getAncho(){
		return ancho;
	}
	
	//funci�n que devuelve el alto del tablero
	public int getAlto(){
		return alto;
	}
	
	//funci�n que devuelve una ficha del tablero dadas sus coordenadas
	public Ficha getCasilla(int x, int y){
		Ficha ficha;
		x -= 1;
		y -= 1;
		if ((x < ancho && x >= 0) &&  (y < alto && y >= 0))
			ficha = tablero[y][x];
		else
			ficha = Ficha.VACIA;
		
		return ficha;
	}
	
	//procedimiento que mete una ficha en el tablero dadas sus coordenadas
	public void setCasilla(int x, int y, Ficha color){
		x -= 1;
		y -= 1;
		if ((x < ancho && x >= 0) &&  (y < alto && y >= 0))
			tablero[y][x] = color;
	}
	
	//procedimiento que muestra el tablero
	public String toString(){
		String cadena = "";
		for (int f = 0; f < alto; f++){
			cadena += "|";
			for (int c = 0; c < ancho; c++){
				cadena += tablero[f][c].getSimbolo();
			}
			cadena += "|\n";
		}
		
		cadena += "+";
		
		for (int i = 0; i < ancho; i++)
			cadena += "-";
		
		cadena += "+\n";
		
		cadena += " ";
		
		int j = 1;
		for (int i = 0; i < ancho; i++){
			if (j % 10 == 0)
				j = 0;
			cadena += j;
			j++;
		}
		
		cadena += "\n";
		
		return cadena;
	}
}
