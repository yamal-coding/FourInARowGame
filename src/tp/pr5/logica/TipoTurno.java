package tp.pr5.logica;

public enum TipoTurno {
	HUMANO("HUMANO"), AUTOMATICO("AUTOMATICO");
	
	private String nombre;
	private TipoTurno (String nombre){
		this.nombre = nombre;
	}
	
	public String getNombre(){
		return nombre;
	}
}
