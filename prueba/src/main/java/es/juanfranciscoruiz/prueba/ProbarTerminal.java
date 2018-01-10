/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.juanfranciscoruiz.prueba;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.TerminalResizeListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hamfree
 */
public class ProbarTerminal {

    private DefaultTerminalFactory dTF;
    private Terminal term;
    private TerminalPosition posIni;

    public ProbarTerminal() {
    }

    public boolean test() throws IOException {

        dTF = new DefaultTerminalFactory();
        this.posIni = null;

        try {
            term = dTF.createTerminal();

            /*
            Imprimimos algo de texto, esto es lo mimsmo que llamar a
            System.out.println("Hola");
             */
            term.putCharacter('H');
            term.putCharacter('o');
            term.putCharacter('l');
            term.putCharacter('a');
            term.putCharacter('\n');
            term.flush();
            /*
            Note la llamada a flush() arriba; es necesario terminar las
            operaciones de salida al terminal con una llamada a flush() tanto en
            el caso de de un terminal nativo y los emuladores de terminal
            empaquetados. El terminal Unix de Lanterna no cachea la salida por
            sí mosmo pero uno puede asumir que la capa de E/S subyacente lo
            haga. En el caso de los emuladores de terminal empaquetados en
            lanterna, la llamada a flush señalizará un repintado al componente
            de IU subyacente.
             */

            posIni = term.getCursorPosition();
            term.setCursorPosition(posIni.withRelativeColumn(3).withRelativeRow(2));
            term.flush();
            Thread.sleep(2000);

            /*
            Continuamos cambiando el color del texto impreso. Esto no cambia
            ningún texto ya existente, solo tomará efecto sobre cualquier cosa
            que imprimamos despu´és de esto.
             */
            term.setBackgroundColor(TextColor.ANSI.BLUE);
            term.setForegroundColor(TextColor.ANSI.YELLOW);

            /*
            Ahora imprimos texto con estos nuevos colores
             */
            term.putCharacter('A');
            term.putCharacter('m');
            term.putCharacter('a');
            term.putCharacter('r');
            term.putCharacter('i');
            term.putCharacter('l');
            term.putCharacter('l');
            term.putCharacter('o');
            term.putCharacter(' ');
            term.putCharacter('y');
            term.putCharacter(' ');
            term.putCharacter('a');
            term.putCharacter('z');
            term.putCharacter('u');
            term.putCharacter('l');
            term.flush();
            Thread.sleep(2000);

            /*
            Además de los colores, la mayoría de los terminales soportan alguna
            clase de estilo que puede ser habilitado selectivamente. El más
            común es el modo negrita, el cual en muchas implementaciones de
            terminal ( emuladores y otros) en realidad no usan texto en negrita,
            sino que cambia el tinte del color de primer plano para que se
            destaque un poco.  Imprimamos el mismo texto que encima pero en
            modo negrita para comparar. Note que posIni tiene el mismo valor
            qye cuando lo recuperamos con getTerminalSize(), la clase
            TerminalPosition es inmutable y llamarla con métodos with^
            devolverán una copia. Así que la siguiente llamada a
            setCursorPosition(..) nos pondrá exactamente una fila debajo de la
            fila previa.
             */
            term.setCursorPosition(posIni.withRelativeColumn(3).withRelativeRow(3));
            term.flush();
            Thread.sleep(2000);
            term.enableSGR(SGR.BOLD);
            term.putCharacter('A');
            term.putCharacter('m');
            term.putCharacter('a');
            term.putCharacter('r');
            term.putCharacter('i');
            term.putCharacter('l');
            term.putCharacter('l');
            term.putCharacter('o');
            term.putCharacter(' ');
            term.putCharacter('y');
            term.putCharacter(' ');
            term.putCharacter('a');
            term.putCharacter('z');
            term.putCharacter('u');
            term.putCharacter('l');
            term.flush();
            Thread.sleep(2000);

            /*
            De acuerdo, eso es suficiente por ahora. Reseteamos los colores y
            los modificadores SGR y nos movemos abajo una línea más
             */
            term.resetColorAndSGR();
            term.setCursorPosition(term.getCursorPosition().withColumn(0).withRelativeRow(1));
            term.putCharacter('H');
            term.putCharacter('e');
            term.putCharacter('c');
            term.putCharacter('h');
            term.putCharacter('o');
            term.putCharacter('\n');
            term.flush();

            Thread.sleep(2000);

            /*
            Pitido y salida
             */
            term.bell();
            term.flush();
            Thread.sleep(200);
            return true;

        } catch (IOException | InterruptedException e) {
            Logger.getLogger(ProbarTerminal.class.getName()).log(Level.SEVERE, null, e);
            return false;
        } finally {
            if (term != null) {
                try {
                    /*
                    Cerrar el terminal no siempre hace algo, pero si ejecuta los
                    emuladores de terminal Swing o AWT empaquetados por ejemplo,
                    estos cerrarán la ventana y permitirán a esta aplicación
                    terminar. Llamarlo en un UnixTerminal no tendrá ningún
                    efecto.
                     */
                    term.close();
                } catch (IOException e) {
                }
            }
        }
    }

    public boolean test2() {
        //TODO: Introducir los comentarios aclaratorios
        dTF = new DefaultTerminalFactory();
        term = null;
        try {
            term = dTF.createTerminal();

            term.enterPrivateMode();
            term.clearScreen();
            term.setCursorVisible(false);
            final TextGraphics tg = term.newTextGraphics();

            tg.setForegroundColor(TextColor.ANSI.WHITE);
            tg.setBackgroundColor(TextColor.ANSI.BLACK);

            tg.putString(2, 1, "Lanterna Tutorial 2 - Presiona ESC para salir", SGR.BOLD);
            tg.setForegroundColor(TextColor.ANSI.DEFAULT);
            tg.setBackgroundColor(TextColor.ANSI.DEFAULT);
            tg.putString(5, 3, "Tamaño del Terminal: ", SGR.BOLD);
            tg.putString(5 + "Tamaño del Terminal: ".length(), 3, term.getTerminalSize().toString());

            term.flush();

            /*
            Se añade un oyente para que se ejecute si el usuario
            redimensiona la ventana del terminal
             */
            term.addResizeListener(new TerminalResizeListener() {
                @Override
                public void onResized(Terminal terminal, TerminalSize newSize) {
                    //TODO: Traducir esto adecuadamente
                    // Be careful here though, this is likely running on a
                    // separate thread. Lanterna is threadsafe in a best-effort
                    // way so while it shouldn't blow up if you call terminal
                    // methods on multiple threads,  it might have unexpected
                    // behavior if you don't do any external synchronization
                    tg.drawLine(5, 3, newSize.getColumns() - 1, 3, ' ');
                    tg.putString(5, 3, "Terminal Size: ", SGR.BOLD);
                    tg.putString(5 + "Terminal Size: ".length(), 3, newSize.toString());
                    try {
                        terminal.flush();
                    } catch (IOException e) {
                        // Not much we can do here
                        throw new RuntimeException(e);
                    }
                }
            });

            tg.putString(5, 4, "Última Pulsación de Tecla: ", SGR.BOLD);
            tg.putString(5 + "Última Pulsación de Tecla: ".length(), 4, "<Pendiente>");
            term.flush();

            /*
               Ahora esperamos la entrada del usuario que guardará en ks
               cuando termine la entrada y pulse <INTRO>
             */
            KeyStroke ks = term.readInput();

            while (ks.getKeyType() != KeyType.Escape) {
                tg.drawLine(5, 4, term.getTerminalSize().getColumns() - 1, 4, ' ');
                tg.putString(5, 4, "Última Pulsación de Tecla: ", SGR.BOLD);
                tg.putString(5 + "Última Pulsación de Tecla: ".length(), 4, ks.toString());
                term.flush();
                ks = term.readInput();
            }
            return true;

        } catch (IOException e) {
            Logger.getLogger(ProbarTerminal.class.getName()).log(Level.SEVERE, e.getMessage());
            return false;
        } finally {
            if (term != null) {
                try {
                    term.close();
                } catch (IOException e) {
                    Logger.getLogger(ProbarTerminal.class.getName()).log(Level.SEVERE, e.getMessage());
                }
            }
        }
    }

}
