/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package name.ruiz.juanfco.pruebas.consola;

/**
 *
 * @author hamfree
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * Sends text back and forth between the command line and an Interpreter. JDK
 * less than 6.
 */
public final class Consola {

    /**
     * Build and launch a specific <code>Interpreter</code>, whose
     * package-qualified name is passed in on the command line.
     *
     * @param aArguments
     */
    public static void main(String... aArguments) {
        try {
            Class theClass = Class.forName(aArguments[0]);
            Interpreter interpreter = (Interpreter) theClass.newInstance();
            Consola console = new Consola(interpreter);
            console.run();
        } catch (ClassNotFoundException ex) {
            System.err.println(ex + " Interpreter class must be in class path.");
        } catch (InstantiationException ex) {
            System.err.println(ex + " Interpreter class must be concrete.");
        } catch (IllegalAccessException ex) {
            System.err.println(ex + " Interpreter class must have a no-arg constructor.");
        }
    }

    public Consola(Interpreter aInterpreter) {
        if (aInterpreter == null) {
            throw new IllegalArgumentException("Cannot be null.");
        }
        fInterpreter = aInterpreter;
    }

    /**
     * Display a prompt, wait for a full line of input, and then parse the input
     * using an Interpreter.
     *
     * Exit when <code>Interpreter.parseInput</code> returns true.
     */
    public void run() {
        display(fInterpreter.getHelloPrompt());

        //pass each line of input to fInterpreter, and display
        //fInterpreter's result
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader stdin = new BufferedReader(inputStreamReader);
        boolean hasRequestedQuit = false;

        List<Object> result = new ArrayList<>();
        try {
            while (!hasRequestedQuit) {
                String line = stdin.readLine();
                //note that "result" is passed as an "out" parameter
                hasRequestedQuit = fInterpreter.parseInput(line, result);
                display(result);
                result.clear();
            }
        } catch (IOException ex) {
            System.err.println(ex);
        } finally {
            display(BYE);
            shutdown(stdin);
        }
    }

    // PRIVATE
    private static final String BYE = "Bye.";
    private Interpreter fInterpreter;

    /**
     * Display some text to stdout. The result of toString() is used.
     */
    private void display(Object aText) {
        System.out.print(aText.toString());
        System.out.flush();
    }

    /**
     * Display a List of objects as text in stdout, in the order returned by the
     * iterator of aText.
     */
    private void display(List<Object> aText) {
        for (Object item : aText) {
            display(item);
        }
    }

    private void shutdown(Reader aStdin) {
        try {
            aStdin.close();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}
