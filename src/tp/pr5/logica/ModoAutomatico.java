package tp.pr5.logica;

import tp.pr5.control.ControladorGUI;

public class ModoAutomatico implements Modo {

	private ControladorGUI c;
	private Thread hebra;
	
	public ModoAutomatico(ControladorGUI c){
		this.c = c;
	}
	
	@Override
	public void comenzar() {
		hebra = new Thread(){
			public void run(){
				try {
					Thread.sleep(1000);
					c.ponerAleatorio();
				} catch (InterruptedException e){}
			}
		};
		hebra.start();
	}

	@Override
	public void terminar() {
		if (hebra != null)
			hebra.interrupt();
	}

}
