package name.ruiz.juanfrancisco;

import java.util.Enumeration;
import java.util.Locale;
import java.util.Properties;

public class App {

	public static void main(String[] args) {
		Long bytesMaximosDisponibles = 0L;
		Long bytesLibres = 0L;
		Long bytesTotales = 0L;
		Integer numProcesadores = 0;
		
		bytesMaximosDisponibles = Runtime.getRuntime().maxMemory();
		numProcesadores = Runtime.getRuntime().availableProcessors();
		bytesLibres = Runtime.getRuntime().freeMemory();
		bytesTotales = Runtime.getRuntime().totalMemory();
		
		System.out.print("Máxima cantidad de memoria disponible para la JVM: " );
		System.out.format(Locale.getDefault(), "%1$,d", bytesMaximosDisponibles);
		System.out.println(" bytes.");
		System.out.println("Procesadores disponibles para la JVM: " + numProcesadores);
		System.out.print("Memoria libre para la JVM: ");
		System.out.format(Locale.getDefault(), "%1$,d", bytesLibres);
		System.out.println(" bytes.");
		System.out.print("Memoria ocupada por la JVM: ");
		System.out.format(Locale.getDefault(), "%1$,d", bytesTotales);
		System.out.println(" bytes.");
		System.out.println("");
		System.out.println("Propiedades de la JVM");
		
		Properties p = System.getProperties();
		Enumeration<Object> en =  p.keys();
		while (en.hasMoreElements()) {
			String clave = (String) en.nextElement();
			String valor = p.getProperty(clave);
			System.out.println(clave + "=" + valor);
		}
		
	}

}
