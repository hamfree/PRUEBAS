package name.ruiz.juanfco;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;


/**
 * 
 * @author operador
 *
 */
public class App {
	
	// Usada por el metodo testListaComoAtributo()
	private List<String> unaLista;
	
	public App() {
		super();
		unaLista = new ArrayList<>(10);
	}

	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		App miApp = new App();
		
		if (args.length == 1 || args.length == 2) {
			try {
				miApp.ejecuta(args);
			} catch (Exception e) {
				System.out.println(e.getLocalizedMessage());
				e.printStackTrace();
			}
		} else {
			sb.append("Este programa requiere de un comando y ").append("opcionalmente un segundo argumento.")
					.append("Escriba 'App help' para mostrar la lista de comandos disponibles");
			System.out.println(sb.toString());
		}

		System.exit(0);
	}

	public void ejecuta(String[] args) throws Exception {
		

		String comandoPedido = args[0];
		if (comandoPedido.equalsIgnoreCase(Comandos.HELP.toString())) {
			System.out.println("Los argumentos disponibles son: ");
			int i = 0;
			while (i < Comandos.values().length) {
				if (i < (Comandos.values().length) - 1) {
					System.out.print(Comandos.values()[i] + ",");
				} else {
					System.out.print(Comandos.values()[i]);
				}
				i++;
			}
			System.out.println("\n");
		} else if (comandoPedido.equalsIgnoreCase(Comandos.REPLACE.toString())) {
			testReplace();
		} else if (comandoPedido.equalsIgnoreCase(Comandos.HOLAMUNDO.toString())) {
			holaMundo();
		} else if (comandoPedido.equalsIgnoreCase(Comandos.MAPA2LISTA.toString())) {
			testMapa2Lista();
		} else if (comandoPedido.equalsIgnoreCase(Comandos.EXTRACTDIGITS.toString())) {
			testExtractDigits();
		} else if (comandoPedido.equalsIgnoreCase(Comandos.EXTRACTDOUBLEFROMSTRING.toString())) {
			testExtractDoubleFromString();
		} else if (comandoPedido.equalsIgnoreCase(Comandos.EXTRACTLONGFROMSTRING.toString())) {
			testExtractLongFromString();
		} else if (comandoPedido.equalsIgnoreCase(Comandos.COMPARACIONES.toString())) {
			testComparaciones();
		} else if (comandoPedido.equalsIgnoreCase(Comandos.PROCESADUMMIES.toString())) {
			testProcesaDummies();
		} else if (comandoPedido.equalsIgnoreCase(Comandos.TROCEOCADENA.toString())) {
			testTroceoCadena();
		} else if (comandoPedido.equalsIgnoreCase(Comandos.ELIMINAPARENTESIS.toString())) {
			testEliminaParentesis();
		} else if (comandoPedido.equalsIgnoreCase(Comandos.VALIDAFORMATO.toString())) {
			testValidaFormato();
		} else if (comandoPedido.equalsIgnoreCase(Comandos.ARRAYTOSTRING.toString())) {
			testArrayToString();
		} else if (comandoPedido.equalsIgnoreCase(Comandos.OBJETOS.toString())) {
			testObjetos();
		} else if (comandoPedido.equalsIgnoreCase(Comandos.FECHAS.toString())) {
			testFechas();
		} else if (comandoPedido.equalsIgnoreCase(Comandos.ALCANCE.toString())) {
			testAlcance();
		} else if (comandoPedido.equalsIgnoreCase(Comandos.DOSLISTAS.toString())) {
			testDosListas("AC");
		} else if (comandoPedido.equalsIgnoreCase(Comandos.LISTACOMOATRIBUTO.toString())) {
			testListaComoAtributo();
		} else if (comandoPedido.equalsIgnoreCase(Comandos.OPTIONAL.toString())) {
			testOptional();
		} else if (comandoPedido.equalsIgnoreCase(Comandos.LAMBDA.toString())) {
			testLambda();
		} else if (comandoPedido.equalsIgnoreCase(Comandos.MEMORIA.toString())) {
			testMemoria();
		} else {
			System.out.println("Error: Comando '" + comandoPedido + "' no reconocido.");
		}
	}

	private void testMemoria() {
		cabecera("testMemoria", true);
		Long maxmem = Runtime.getRuntime().maxMemory();
		Utl.imp(true, 1, "Memoria disponible : ",NumberFormat.getInstance().format(maxmem), " bytes.");
		cabecera("testMemoria", false);
		
	}

	private void testListaComoAtributo() {
		try {
			List<String> otraLista = new ArrayList<>();
			otraLista.add("uno");
			otraLista.add("dos");
			otraLista.add("tres");
			
			setUnaLista(otraLista);
			
			otraLista.clear();
			
			getUnaLista().remove(0);
			
			System.out.println(otraLista.get(0));
			
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
		
	}

	private void testDosListas(String tipoTransaccion) {
		List<String> transaccionesEntrada = new ArrayList<String>();
		transaccionesEntrada.add("APORT");
		transaccionesEntrada.add("AC");
		transaccionesEntrada.add("MI");

		List<String> transaccionesSalida = new ArrayList<String>();
		transaccionesSalida.add("EVMT");
		transaccionesSalida.add("EVMC");
		transaccionesSalida.add("EVMG");
		

		String transactionTypeResult = "";
		if (tipoTransaccion != null) {
			int indice = 0;
			for (String tipoTranEnt : transaccionesEntrada) {
				if (transaccionesSalida.size() > 1) {
					if (tipoTransaccion.contentEquals(tipoTranEnt)) {
						transactionTypeResult = transaccionesSalida.get(indice);
						break;
					}
				} else {
					transactionTypeResult = transaccionesSalida.get(0);
					break;
				}
				indice++;
			}
		}
		
		System.out.println("Transaccion resultante: " + transactionTypeResult);
		
	}

	private void testAlcance() {
		cabecera("testAlcance", true);
		A b = generaUnObjeto("Juan Francisco");
		Utl.imp(true, 1, "El objeto 'b' de clase A dentro del método 'testAlcance()' vale ",b);
		cabecera("testAlcance", false);
	}
	
	private A generaUnObjeto(String nombre) {
		A a = new A(nombre);
		Utl.imp(true, 1, "El objeto 'a' de clase A dentro del método 'generaUnObjeto()' vale ",a);
		return a;
	}

	private void testFechas() {
		cabecera("testFechas", true);
		Utl.imp(true, 2, "Probamos la conversion dentro del método...");
		
		Date fecha = new Date(System.currentTimeMillis());
		Utl.imp(true, 1, "Valor de la variable del tipo Date.......: ", fecha);
		
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(fecha);
		LocalDate localdate = LocalDate.of(gc.get(Calendar.YEAR), gc.get(Calendar.MONTH),
				gc.get(Calendar.DAY_OF_MONTH));
		Utl.imp(true, 2, "Valor de la variable del tipo LocalDate..: ", localdate);
		
		Utl.imp(true, 2, "Probamos la conversión como resultado de la llamada al método Utl.date2LocalDate()...");
		
		Utl.imp(true, 1, "Establecemos la fecha a 1 de Enero de 2015....");
		gc.set(2015, Calendar.JANUARY, 1);
		Date fecha2 = new Date(gc.getTimeInMillis());
		Utl.imp(true, 1, "Valor de la variable del tipo Date.......: ", fecha2);
		
		LocalDate localdate2 = Utl.date2LocalDate(fecha2);
		Utl.imp(true, 2, "Valor de la variable del tipo LocalDate..: ", localdate2);
		
		
		Utl.imp(true, 1, "Establecemos la fecha a 31 de Diciembre de 2015....");
		gc.set(2015, Calendar.DECEMBER, 31);
		Date fecha3 = new Date(gc.getTimeInMillis());
		Utl.imp(true, 1, "Valor de la variable del tipo Date.......: ", fecha3);
		
		LocalDate localdate3 = Utl.date2LocalDate(fecha3);
		Utl.imp(true, 2, "Valor de la variable del tipo LocalDate..: ", localdate3);
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
		
		Integer effectiveDate = 201802;
		Utl.imp(true, 1, "Valor de la variable del tipo Integer.......: ", effectiveDate);
		Date effDate = getDateOfLastDayOfEffectiveDate(effectiveDate);
		Utl.imp(true, 2, "Valor de la variable del tipo Date..: ", sdf.format(effDate));
		
		
		effectiveDate = 201812;
		Utl.imp(true, 1, "Valor de la variable del tipo Integer.......: ", effectiveDate);
		effDate = getDateOfLastDayOfEffectiveDate(effectiveDate);
		Utl.imp(true, 2, "Valor de la variable del tipo Date..: ", sdf.format(effDate));
		
		effectiveDate = 201804;
		Utl.imp(true, 1, "Valor de la variable del tipo Integer.......: ", effectiveDate);
		effDate = getDateOfLastDayOfEffectiveDate(effectiveDate);
		
		Utl.imp(true, 2, "Valor de la variable del tipo Date..: ", sdf.format(effDate));
		
		cabecera("testFechas", false);
	}

	private void testObjetos() {
		cabecera("testObjeto", true);
		Dummy d1 = new Dummy("Rui", 49, LocalDate.now());
		Dummy d2 = null;

		try {
			d2 = (Dummy) d1.clone();
		} catch (CloneNotSupportedException e) {
			Utl.imp(true, 1, e.getLocalizedMessage());
		}

		d2.setEdad(38);
		Utl.imp(true, 1, "edad en d1 -> ", d1.getEdad());
		Utl.imp(true, 1, "edad en d2 -> ", d2.getEdad());
		Utl.imp(true, 1, "nombre en d2 -> ", d2.getNombre());
		
		Utl.imp(true, 2, "Preguntar si dos objetos son iguales cuando los dos apuntan a null");
		String a = null;
		String b = null;
		Utl.imp(true, 1, "a -> ",a);
		Utl.imp(true,1,"b -> ",b);
		Utl.imp(true,1,"¿A es igual a B? ",Objects.equals(a, b));
		
		cabecera("testObjeto", false);
	}

	// Todos los test que voy realizando....

	private void testArrayToString() {
		cabecera("testArrayToString", true);

		boolean[] booleans = { true, false, false };
		char[] chars = { 'B', 'P', 'H' };
		byte[] bytes = { 3 };
		short[] shorts = { 5, 6 };
		int[] ints = { 7, 8, 9, 10 };
		long[] longs = { 100, 101, 102 };
		float[] floats = { 99.9f, 63.2f };
		double[] doubles = { 212.2, 16.236, 42.2 };
		String[] strings = { "blah", "blah", "blah" };
		java.util.Date[] dates = { new java.util.Date(), new java.util.Date() };

		int[] nullInts = null;
		int[] emptyInts = {};
		String[] emptyStrings = { "", "" };
		String[] nullStrings = { null, null };

		String[] arrayA = { "A", "a" };
		String[] arrayB = { "B", "b" };
		String[][] arrayOfArrays = { arrayA, arrayB };

		System.out.println("booleans: " + Utl.arrayToString(booleans));
		System.out.println("chars: " + Utl.arrayToString(chars));
		System.out.println("bytes: " + Utl.arrayToString(bytes));
		System.out.println("shorts: " + Utl.arrayToString(shorts));
		System.out.println("ints: " + Utl.arrayToString(ints));
		System.out.println("longs: " + Utl.arrayToString(longs));
		System.out.println("floats: " + Utl.arrayToString(floats));
		System.out.println("double: " + Utl.arrayToString(doubles));
		System.out.println("strings: " + Utl.arrayToString(strings));
		System.out.println("dates: " + Utl.arrayToString(dates));

		System.out.println("null ints: " + Utl.arrayToString(nullInts));
		System.out.println("empty ints: " + Utl.arrayToString(emptyInts));
		System.out.println("empty Strings: " + Utl.arrayToString(emptyStrings));
		System.out.println("null Strings: " + Utl.arrayToString(nullStrings));

		System.out.println("array Of Arrays: " + Utl.arrayToString(arrayOfArrays));

		cabecera("testArrayToString", false);
	}

	private void testValidaFormato() throws Exception {
		cabecera("testValidaFormato", true);
		final String SEP = ",";
		final String DEL = "'";
		boolean isNumericList;
		boolean isStringList;
		StringBuilder sb = new StringBuilder();

		Utl.imp(false, 1, "Dame una CONDITIONVALUE para validarla:");
		String valueToValidate = Utl.read(false);

		if (Utl.isNull(valueToValidate)) {
			Utl.imp(true, 1, "CONDITIONVALUE can't be NULL");
			return;
		} else {
			if (valueToValidate.isEmpty()) {
				Utl.imp(true, 1, "CONDITIONVALUE can't be blank");
				return;
			} else {
				// Comprobamos que no empiece ni termine por comas
				if (valueToValidate.startsWith(SEP) || valueToValidate.endsWith(SEP)) {
					sb.append("CONDITIONVALUE can't begins or ends with the value delimiter (").append(SEP).append(")");
					Utl.imp(true, 1, sb.toString());
					return;
				}

				// Comprobamos si es una lista de números o de cadenas.
				if (valueToValidate.substring(0, 1).equals(DEL)) {
					isStringList = true;
					isNumericList = false;
				} else {
					isStringList = false;
					isNumericList = true;
				}

				// Comprobamos el formato
				Scanner sc = new Scanner(valueToValidate);
				sc.useDelimiter(SEP);
				if (isStringList) {
					while (sc.hasNext()) {
						sb.setLength(0);
						String elemento = sc.next();
						if (!elemento.startsWith(DEL) || !elemento.endsWith(DEL)) {
							sb.append("The item |").append(elemento)
									.append("| is not enclosed in quotes or one of them is missing.");
							Utl.imp(true, 1, sb.toString());
							sc.close();
							return;
						}
						if (elemento.isEmpty()) {
							Utl.imp(true, 1, "The item can not be an empty string.");
							sc.close();
							return;
						}
						sb.append("Element |").append(elemento).append("| meets the syntax.");
						Utl.imp(true, 1, sb.toString());
					}
				} else if (isNumericList) {
					while (sc.hasNext()) {
						sb.setLength(0);
						String elemento = sc.next();
						if (elemento.startsWith(DEL) || elemento.endsWith(DEL)) {
							sb.append("the item  |").append(elemento).append("| can not be in single quotes.");
							Utl.imp(true, 1, sb.toString());
							sc.close();
							return;
						}
						if (elemento.isEmpty()) {
							Utl.imp(true, 1, "The item can not be an empty string.");
							sc.close();
							return;
						}
						sb.append("Element |").append(elemento).append("| meets the syntax.");
						Utl.imp(true, 1, sb.toString());
					}
				} else {
					sc.close();
					throw new Exception("No se qué diablos me has pasado...");
				}
				sc.close();
			}
		}
		cabecera("testValidaFormato", false);
	}

	/**
	 * 
	 * @param d
	 */
	private void testReplace() {
		cabecera("testReplace", true);
		Utl.imp(true, 1, "");
		Utl.imp(true, 1, "ANTES");
		
		Dummy d = new Dummy("Juan Francisco Ruiz", new Integer(48), LocalDate.of(1969, 11, 22));


		String nombre = d.getNombre();
		System.out.println("nombre = " + nombre);
		nombre.replace('u', 'X');
		System.out.println("DESPUES SIN ASIGNACION");
		System.out.println("nombre = " + nombre);
		System.out.println("DESPUES CON ASIGNACION");
		nombre = nombre.replace('u', 'X');
		System.out.println("nombre = " + nombre);
		cabecera("testReplace", false);
	}

	/**
	 * 
	 */
	private void holaMundo() {
		cabecera("holaMundo", true);
		Utl.imp(true, 1, "");
		System.out.println("¡Hola mundo!");
		cabecera("holaMundo", false);
	}

	/**
	 * 
	 */
	@SuppressWarnings("unused")
	private void testColeccion2Lista() {
		cabecera("testColeccion2Lista", true);

		cabecera("testColeccion2Lista", false);
	}

	/**
	 * 
	 */
	private void testMapa2Lista() {
		cabecera("testMapa2Lista", true);

		cabecera("testMapa2Lista", false);
	}

	/**
	 * 
	 */
	private void testExtractDigits() {
		cabecera("testExtractDigits", true);

		cabecera("testExtractDigits", false);
	}

	/**
	 * 
	 */
	private void testExtractDoubleFromString() {
		cabecera("testExtractDoubleFromString", true);

		cabecera("testExtractDoubleFromString", false);
	}

	/**
	 * 
	 */
	private void testExtractLongFromString() {
		cabecera("testExtractLongFromString", true);

		cabecera("testExtractLongFromString", false);
	}

	/**
	 * 
	 */
	@SuppressWarnings("null")
	private void testComparaciones() {
		cabecera("testComparaciones", true);
		Utl.imp(true, 1, "");
		try {
			String a = "con contenido";
			String b = null;
			String c = "con contenido";
			String e = "";
			String f = null;

			if (!a.equalsIgnoreCase(b)) {
				System.out.println("a y b no son iguales");
			} else {
				System.out.println("a y b son iguales");
			}

			if (!a.equalsIgnoreCase(c)) {
				System.out.println("a y c no son iguales");
			} else {
				System.out.println("a y c son iguales");
			}

			if (e != null) {
				if (!e.equalsIgnoreCase(f)) {
					System.out.println("e y f no son iguales");
				} else {
					System.out.println("e y f no son iguales");
				}
			}

			// Lo hago adrede
			if (!b.equalsIgnoreCase(a)) {
				System.out.println("b y a no son iguales");
			}

		} catch (Exception ex) {
			System.out.println(ex.getClass().getCanonicalName());
		}
		cabecera("testComparaciones", false);
	}

	private void testProcesaDummies() {
		cabecera("testProcesaDummies", true);

		Dummy d1 = new Dummy("Juan Francisco Ruiz", new Integer(48), LocalDate.of(1969, 11, 22));
		Dummy d2 = new Dummy("Jose Antonio Sevillano", new Integer(52), LocalDate.of(1965, 07, 05));
		
		Utl.imp(true, 1, "");
		Utl.imp(true, 1, "ANTES");
		Utl.imp(true, 1, "d1=", d1.toString());
		Utl.imp(true, 1, "d2=", d2.toString());
		
		if (d1 != null && d2 != null) {
			if (!d1.equals(d2)) {
				String nombre1 = d1.getNombre();
				Integer edad1 = d1.getEdad();
				LocalDate nacimiento1 = d1.getNacimiento();

				String nombre2 = d2.getNombre();
				Integer edad2 = d2.getEdad();
				LocalDate nacimiento2 = d2.getNacimiento();

				nombre1 = (nombre1 == null) ? "" : nombre1;
				edad1 = (edad1 == null) ? new Integer(0) : edad1;
				nacimiento1 = (nacimiento1 == null) ? LocalDate.now() : nacimiento1;

				nombre2 = (nombre2 == null) ? "" : nombre2;
				edad2 = (edad2 == null) ? new Integer(0) : edad2;
				nacimiento2 = (nacimiento2 == null) ? LocalDate.now() : nacimiento2;

				if (!nombre2.equalsIgnoreCase(nombre1)) {
					d1.setNombre(nombre2);
				}
				if (!edad2.equals(edad1)) {
					d1.setEdad(edad2);
				}
				if (!nacimiento1.equals(nacimiento2)) {
					d1.setNacimiento(nacimiento2);
				}
			}
		}

		Utl.imp(true, 1, "DESPUES");
		Utl.imp(true, 1, "d1=", d1.toString());
		Utl.imp(true, 1, "d2=", d2.toString());

		cabecera("testProcesaDummies", false);
	}

	@SuppressWarnings("unused")
	private void testTroceoCadena() {
		String cadena = "'CPC1','CPC2','CPC3',67,34.11,333,'Ultimo'";
		Scanner sc = new Scanner(cadena);
		ArrayList<String> lista = new ArrayList<>();
		int index = 0;
		sc.useDelimiter(",");

		while (sc.hasNext()) {
			lista.add(sc.next());
			index++;
		}
		sc.close();

		for (String elemento : lista) {
			System.out.print(elemento + ",");
		}
	}

	private void testEliminaParentesis() {
		cabecera("testEliminaParentesis", true);
		String cadena = "(20,50,100)";
		Utl.imp(true, 1, "ANTES:", cadena);
		if (cadena.startsWith("(")) {
			cadena = cadena.substring(1);
		}
		if (cadena.endsWith(")")) {
			cadena = cadena.substring(0, cadena.length() - 1);
		}
		Utl.imp(true, 1, "DESPUES:", cadena);
		cabecera("testEliminaParentesis", false);
	}
	
	private void testOptional() {
		cabecera("testOptional", true);
		String source = "una cadena";
		Utl.imp(true, 1, "La variable 'source' tiene un valor:",source);
		Optional<String> opt = Optional.ofNullable(source);
		if (opt.isPresent()) {
			Utl.imp(true, 1, "La variable 'source' no es nula");
		} else {
			Utl.imp(true, 1, "La variable 'source' es nula");
		}
		
		source = null;
		Utl.imp(true, 1, "La variable 'source' ahora esta a null:",source);
		opt = Optional.ofNullable(source);
		if (opt.isPresent()) {
			Utl.imp(true, 1, "La variable 'source' no es nula");
		} else {
			Utl.imp(true, 1, "La variable 'source' es nula");
		}
		
		cabecera("testOptional", false);
	}
	
	

	private void testLambda() {
		cabecera("testLambda", true);
		List<String> nombres = Arrays.asList("Juan", "Francisco", "José", "Pablo");
		Utl.imp(true, 1, ""); 
		Utl.imp(true, 2, "Imprimiendo una lista con una expresion Lambda: ");
		//Usando lambda
		nombres.forEach(nombre -> System.out.println(nombre));
		Utl.imp(true, 1, "");
		Utl.imp(true, 2, "Imprimiendo una lista usando referencias a método: ");
		//Usando referencias a método
		nombres.forEach(System.out::println);
		
		Map<String,String> map = new HashMap<>();
		map = System.getenv();
		Utl.imp(true, 1, "");
		Utl.imp(true, 2, "Imprimiendo un mapa recorriendolo de forma tradicional con un bucle: ");
		//Forma tradicional de recorrer un mapa
		for (Map.Entry<String,String> e : map.entrySet()) {
		    System.out.println(e.getKey() + " => " + e.getValue());
		}
		Utl.imp(true, 1, "");
		Utl.imp(true, 2, "Imprimiendo un mapa recorriendolo usando una lambda: ");
		//Recorrer un mapa usando lambdas
		map.forEach((k, v) -> System.out.println(k + " => " + v));
		
		cabecera("testLambda", false);
	}
	
	/***********************
	 * Metodos de utilidad *
	 ***********************/
	/**
	 * 
	 * @param msg
	 * @param esInicio
	 */
	public void cabecera(String msg, boolean esInicio) {
		StringBuilder sb = new StringBuilder();
		if (msg != null && msg.length() > 0) {
			Utl.imp(true, 1, "");
			linea('-', 80);
			sb.append(Utl.getSl());
			sb.append(msg);
			if (esInicio) {
				sb.append("() - INICIO");
			} else {
				sb.append("() - FIN");
			}
			Utl.imp(true, 1, sb.toString());
			linea('-', 80);
			Utl.imp(true, 1, "");
		}
	}

	/**
	 * 
	 * @param car
	 * @param longitud
	 */
	public void linea(Character car, int longitud) {
		if (car != null && longitud > 0) {
			String linea = repiteCaracter(car, longitud);
			Utl.imp(false, 1, linea);
		}
	}

	/**
	 * 
	 * @param car
	 * @param veces
	 * @return
	 */
	public String repiteCaracter(Character car, int veces) {
		StringBuilder sb = new StringBuilder();
		sb.setLength(0);
		if (car != null && veces > 0) {
			for (int i = 0; i < veces; i++) {
				sb.append(car);
			}
		}
		return sb.toString();
	}

	public List<String> getUnaLista() {
		return this.unaLista;
	}

	public void setUnaLista(List<String> unaLista) {
		if (unaLista != null) {
			if (this.unaLista.size() > unaLista.size()) {
				Collections.copy(this.unaLista, unaLista);
			}
		}
		
	}
	
	
	@SuppressWarnings("deprecation")
	public Date getDateOfLastDayOfEffectiveDate(Integer effectiveDate) {
		String tmp;
		Date result = null;
		Date fecha = null;
		Integer month;
		Integer year;
		if (effectiveDate != null) {
			tmp = String.valueOf(effectiveDate);
			if (tmp.length() == 6) {
				try {
					year = Integer.parseInt(tmp.substring(0, 4));
					month = Integer.parseInt(tmp.substring(4, 6));
					fecha = new Date();
					fecha.setMonth(--month);
					fecha.setYear(year - 1900);
					result = DateHelper.endingMonth(fecha);
				} catch (NumberFormatException e) {
					return null;
				}
				return result;
			}
		}
		return null;
	}
}
