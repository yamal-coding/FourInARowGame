package tp.pr5;

//Creamos esta clase para poder controlar también si al meter las dimensiones
//de Gravity en los argumentos se meten ambas y son números enteros
public class ArgumentoInvalido extends Exception {
	
	public ArgumentoInvalido(String causa){
		super(causa);
	}
	
}
