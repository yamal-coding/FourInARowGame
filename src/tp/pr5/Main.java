package tp.pr5;



import java.util.Scanner;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.UnrecognizedOptionException;




import tp.pr5.control.ControladorConsola;
import tp.pr5.control.ControladorGUI;
import tp.pr5.control.FactoriaComplica;
import tp.pr5.control.FactoriaConecta4;
import tp.pr5.control.FactoriaGravity;
import tp.pr5.control.FactoriaReversi;
import tp.pr5.control.FactoriaTipoJuego;
import tp.pr5.logica.Partida;
import tp.pr5.logica.ReglasJuego;
import tp.pr5.vista.VistaConsola;
import tp.pr5.vista.VistaVentana;

public class Main {
	
	public static void main(String[] args) {
		
		//Por defecto consideramos que se va a jugar a Conecta4
		FactoriaTipoJuego fact = new FactoriaConecta4();
		
		Options opciones = new Options();
		opciones.addOption(OptionBuilder.withLongOpt("game")
										.hasArg()
										.withArgName("game")
										.withDescription("Tipo de juego (c4, co, gr, rv). Por defecto, c4.")
										.create('g'));
		opciones.addOption(OptionBuilder.withLongOpt("help")
										.withDescription("Muestra esta ayuda.")
										.create('h'));
		opciones.addOption(OptionBuilder.withLongOpt("tamX")
										.hasArg()
										.withArgName("columnNumber")
										.withDescription("Número de columnas del tablero (sólo para Gravity). Por defecto, 10.")
										.create('x'));
		opciones.addOption(OptionBuilder.withLongOpt("tamY")
										.hasArg()
										.withArgName("rowNumber")
										.withDescription("Número de filas del tablero (sólo para Gravity). Por defecto, 10.")
										.create('y'));
		opciones.addOption(OptionBuilder.withLongOpt("ui")
										.hasArg()
										.withArgName("tipo")
										.withDescription("Tipo de interfaz (console, window). Por defecto, console.")
										.create('u'));
										
		
		//Análisis de argumentos
		try{
			CommandLineParser parser = new BasicParser();
			CommandLine cmdLine = parser.parse(opciones, args);
			
			//Guardamos todo lo que no haya sido reconocido como opción válida
			String[] errores = cmdLine.getArgs();
			if (errores.length != 0){
				//Aquí se lanza la excepcion con la causa de ella
				String invalidArgs = errores[0];
				for (int i = 1; i < errores.length; i++)
					invalidArgs += " " + errores[i];
				
				throw new ArgumentoInvalido("Argumentos no entendidos: " + invalidArgs);
			}else{
				if (cmdLine.hasOption("h")){

					HelpFormatter formatter = new HelpFormatter();
					formatter.printHelp(Main.class.getCanonicalName(), opciones, true);
					
					System.exit(0);
				}
				
				if (cmdLine.hasOption("g")){
					String g = cmdLine.getOptionValue("g");
					
					//Aquí se identifica el tipo de juego al que se va a jugar
					if (g.equals("c4")){
						fact = new FactoriaConecta4();
					}
					else if (g.equals("co")){
						fact = new FactoriaComplica();
					}
					else if (g.equals("gr")){
						if (cmdLine.hasOption("x") && cmdLine.hasOption("y")){
							int x = Integer.parseInt(cmdLine.getOptionValue("x"));
							int y = Integer.parseInt(cmdLine.getOptionValue("y"));
							
							fact = new FactoriaGravity(x, y);
						}
						else if (!cmdLine.hasOption("x") && !cmdLine.hasOption("y")){
							fact = new FactoriaGravity(10, 10);
						}
						else{
							throw new Exception();
						}
					}
					else if (g.equals("rv")){
						fact = new FactoriaReversi();
					}
					else{
						throw new ArgumentoInvalido("Juego '" + g + "' incorrecto.");
					}
				}
				
				if (cmdLine.hasOption("u")){
					String u = cmdLine.getOptionValue("u");
					
					if (u.equals("console")){
						ReglasJuego reglas = fact.creaReglas();
						Partida partida = new Partida(reglas);
						Scanner in = new Scanner(System.in);
						ControladorConsola c = new ControladorConsola(fact, partida, in);
						VistaConsola v = new VistaConsola(in, c);
						v.run();
					}
					else if (u.equals("window")){
						Partida p = new Partida(fact.creaReglas());
						ControladorGUI c = new ControladorGUI(fact, p);
						VistaVentana v = new VistaVentana(c);
					}
					else {
						throw new ArgumentoInvalido("Modo de vista " + u + " no reconocido.");
					}
				}
				else {//por defecto se jugara en modo consola
					ReglasJuego reglas = fact.creaReglas();
					Partida partida = new Partida(reglas);
					Scanner in = new Scanner(System.in);
					ControladorConsola c = new ControladorConsola(fact, partida, in);
					VistaConsola v = new VistaConsola(in, c);
					v.run();
				}
			}
		}

		//Se capturan las posibles excepciones y al final se usa System.exit(1) para que el programa termine de ejecutarse
		catch(ArgumentoInvalido e){
			System.err.print("Uso incorrecto: " + e.getMessage() + "\nUse -h|--help para más detalles.\n");
			System.exit(1);
		}
		catch(UnrecognizedOptionException e){
			System.err.print("Uso incorrecto: " + e.getMessage() + "\nUse -h|--help para más detalles.\n");
			System.exit(1);
		}
		catch(Exception e){
			System.err.print("Las dimensiones de gravity deben ser numéricas, y se debe elegir entre introducir"
					+ " ambas o utilizar las dimensiones por defecto.\nUse -h|--help para más detalles.\n");
			System.exit(1);
		}
		
		/*Partida p = new Partida(fact.creaReglas());
		ControladorGUI c = new ControladorGUI(fact, p);
		VistaVentana v = new VistaVentana(c);*/
		
		/*ReglasJuego reglas = fact.creaReglas();
		Partida partida = new Partida(reglas);
		Scanner in = new Scanner(System.in);
		ControladorConsola c = new ControladorConsola(fact, partida, in);
		VistaConsola v = new VistaConsola(in, c);
		v.run();*/
	}
}
