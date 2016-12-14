package tp.pr5.GUI;

import javax.swing.ImageIcon;

public enum Botones {
	DESHACER("Deshacer"){
		public String rutaValor(){
			return "imagenes/undo.png";
		}
	}, 
	REINICIAR("Reiniciar"){
		public String rutaValor(){
			return "imagenes/reiniciar.png";
		}
	}, 
	ALEATORIO("Poner Aleatorio"){
		public String rutaValor(){
			return "imagenes/random.png";
		}
	}, 
	CAMBIA_JUEGO("Cambiar Juego"){
		public String rutaValor(){
			return "imagenes/cambiar.png";
		}
	}, 
	SALIR("Salir"){
		public String rutaValor(){
			return "imagenes/exit.png";
		}
	};
	
	private String nombre;
	
	private Botones(String nombre){
		this.nombre = nombre;
	}
	
	public String getNombre(){
		return nombre;
	}
	
	public ImageIcon getIcono(){
		ImageIcon f = null;
		java.net.URL url = null;
		url = Botones.class.getResource(rutaValor());
		if (url != null)
			f = new ImageIcon(url);
		return f;	
	}

	public abstract String rutaValor();
	
}
