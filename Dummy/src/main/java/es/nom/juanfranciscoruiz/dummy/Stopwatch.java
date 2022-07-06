package es.nom.juanfranciscoruiz.dummy;

import java.math.BigDecimal;
import java.math.RoundingMode;

/** 
 Calcula el tiempo de ejecución de cualquier bloque de código.
 
 <P>Esta implementación mide la duración usando <tt>System.nanoTime</tt>.
 
 <P>En la mayoría de los sistemas <tt>System.currentTimeMillis</tt> tiene una resolución de tiempo 
 de aproximadamente 10 ms, lo cuale es bastante pobre para la medición del código, por lo que 
 aquí se evita.
*/
public final class Stopwatch {

  /**
   Un ejemplo del uso de esta clase para 
   medir la ejecución de un código simple de manipulación de String.
   * @param arguments
  */
  public static void main(String... arguments) {
    Stopwatch stopwatch = new Stopwatch();

    stopwatch.start();

    //hace cosas
    StringBuilder messageOne = new StringBuilder();
    int numIterations = 5000;
    for(int idx=0; idx < numIterations; ++idx){
      messageOne.append("blah");
    }

    stopwatch.stop();
    //Tenga en cuenta que no es necesario llamar a un método para obtener la duración,
    //ya que toString es automático aquí 
    System.out.println("La lectura de StringBuilder es: " + stopwatch);

    //reutiliza el mismo cronómetro para medir una implementación alternativa
    //Tenga en cuenta que no es necesario llamar a un método de reinicio.
    stopwatch.start();

    //hace cosas de nuevo
    String messageTwo = null;
    for(int idx=0; idx < numIterations; ++idx){
      messageTwo = messageTwo + "blah";
    }

    stopwatch.stop();
    //realiza una comparación numérica
    if ( stopwatch.toValue() > 5 ) {
      System.out.println("La lectura es alta: " + stopwatch);
    }
    else {
      System.out.println("La lectura es baja: " + stopwatch);
    }
  }

  /**
   Pone en marcha el cronómetro.
   @throws IllegalStateException si el cronómetro ya está funcionando.
  */
  public void start(){
    if (fIsRunning) {
      throw new IllegalStateException("Debe detenerse antes de llamar a comenzar de nuevo.");
    }
    //restablece el inicio y la parada
    fStart = System.nanoTime();
    fStop = 0;
    fIsRunning = true;
    fHasBeenUsedOnce = true;
  }

  /**
   Para el cronómetro.
   @throws IllegalStateException si el cronómetro aún no está funcionando.
  */
  public void stop() {
    if (!fIsRunning) {
      throw new IllegalStateException("No se puede detener si no se está ejecutando actualmente.");
    }
    fStop = System.nanoTime();
    fIsRunning = false;
  }

  /**
   Expresa la "lectura" en el cronómetro.
    
   <P>Ejemplo: <tt>123.456 ms</tt>. La resolución de las mediciones en la mayoría de sistemas 
   está en el orden de unos pocos microsegundos, así que este estilo de presentación es habitualmente
   apropiado para reflejar la precisión real de la mayoría de los temporizadores
  
   <P>Ref: https://blogs.oracle.com/dholmes/entry/inside_the_hotspot_vm_clocks
     
   @throws IllegalStateException si el Stopwatch nunca se ha utilizado,
    o si el cronómetro aún está funcionando.
  */
  @Override public String toString() {
    validateIsReadable();
    StringBuilder result = new StringBuilder();
    BigDecimal value = new BigDecimal(toValue());//la escala es cero
    //milisegundos, con 3 decimales:
    value = value.divide(MILLION, 3, RoundingMode.HALF_EVEN);
    result.append(value);
    result.append(" ms");
    return result.toString();
  }

  /**
   Expresa la "lectura" del cronómetro como un tipo numérico, en nanosegundos.
  
   @throws IllegalStateException si el Stopwatch nunca se ha utilizado,
    o si el cronómetro aún está funcionando.
  */
  public long toValue() {
    validateIsReadable();
    return  fStop - fStart;
  }
  
  // PRIVADO
  private long fStart;
  private long fStop;

  private boolean fIsRunning;
  private boolean fHasBeenUsedOnce;
  
  /** Convierte de nanos a microsegundos. */
  private static final BigDecimal MILLION = new BigDecimal("1000000");
  
  /**
   Throws IllegalStateException si el reloj no ha sido iniciado nunca, 
   o si el reloj está aún funcionando.
  */
  private void validateIsReadable() {
    if (fIsRunning) {
      String message = "No se puede leer un cronómetro que aún está funcionando.";
      throw new IllegalStateException(message);
    }
    if (!fHasBeenUsedOnce) {
      String message = "No se puede leer un cronómetro que nunca se ha iniciado.";
      throw new IllegalStateException(message);
    }
  }
} 