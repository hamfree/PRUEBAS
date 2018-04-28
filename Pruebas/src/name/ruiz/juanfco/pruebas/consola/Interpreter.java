package name.ruiz.juanfco.pruebas.consola;

import java.util.*;

/**
 * Parse a line of text and return a result.
 *
 * @author hamfree
 */
public interface Interpreter {

    /**
     * @param aLine is non-null.
     * @param aResult is a non-null, empty List which acts as an "out"
     * parameter; when returned, aResult must contain a non-null, non-empty List
     * of items which all have a <code>toString</code> method, to be used for
     * displaying a result to the user.
     *
     * @return true if the user has requested to quit the Interpreter.
     * @exception IllegalArgumentException if a param does not comply.
     */
    boolean parseInput(String aLine, List<Object> aResult);

    /**
     * Return the text to be displayed upon start-up of the Interpreter.
     *
     * @return
     */
    String getHelloPrompt();
}
