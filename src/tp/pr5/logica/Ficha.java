package tp.pr5.logica;

import java.awt.Color;

public enum Ficha {
	
	BLANCA(new Color(255, 255, 255), "O", "blancas", new ModoHumano(), TipoTurno.HUMANO),
	NEGRA(new Color(0, 0, 0), "X", "negras", new ModoHumano(), TipoTurno.HUMANO),
	VACIA(new Color(0, 200, 100), " ", "", new ModoHumano(), TipoTurno.HUMANO);
	
	private Ficha(Color color, String simbolo, String nombre, Modo modo, TipoTurno tipo) {
		this.color = color;
		this.simbolo = simbolo;
		this.nombre = nombre;
		this.modo = modo;
		this.tipo = tipo;
	}
	private Color color;
	private String simbolo;
	private String nombre;
	private Modo modo;
	private TipoTurno tipo;
	
	public Color getColor() {
		return color;
	}
	
	public String getSimbolo() {
		return simbolo;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void cambiarModo(TipoTurno tipo, Modo m){
		this.modo = m;
		this.tipo = tipo;
	}
	
	
	public TipoTurno getTipoTurno(){
		return tipo;
	}
	
	public Modo getModo(){
		return modo;
	}
}