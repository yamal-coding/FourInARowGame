package tp.pr5.logica;

public enum TipoJuego {
	CONECTA4 {
		public String getNombre(){
			return "Conecta 4";
		}
	}, COMPLICA {
		public String getNombre(){
			return "Complica";
		}
	}, GRAVITY {
		public String getNombre(){
			return "Gravity";
		}
	} ,REVERSI {
		public String getNombre(){
			return "Reversi";
		}
	};
	
	public abstract String getNombre();
}
