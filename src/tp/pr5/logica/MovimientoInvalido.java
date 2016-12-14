package tp.pr5.logica;

public class MovimientoInvalido extends Exception {
	
	public MovimientoInvalido(){}
	
	public MovimientoInvalido(String msg){
		super(msg);
		
	}
	
	public MovimientoInvalido(String msg,
            Throwable arg){
		super(msg, arg);
	}
	
	public MovimientoInvalido(Throwable arg){
		super(arg);
	}
}
