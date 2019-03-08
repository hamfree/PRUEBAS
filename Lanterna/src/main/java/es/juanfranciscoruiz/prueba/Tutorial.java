package es.juanfranciscoruiz.prueba;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.ComboBox;
import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.gui2.EmptySpace;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.Separator;
import com.googlecode.lanterna.gui2.TextBox;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.TerminalResizeListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * Clase con la que voy estudiando los tutoriales. Cada método corresponde a un
 * tutorial en :
 *
 * @see https://github.com/mabe02/lanterna/blob/master/docs/contents.md
 *
 * @author hamfree
 */
public class Tutorial {

    private DefaultTerminalFactory dTF;
    private Terminal term;
    private TerminalPosition posIni;

    public Tutorial() {
    }

    /**
     * Código del tutorial uno de Lanterna
     *
     * @see
     * https://github.com/mabe02/lanterna/blob/master/docs/tutorial/Tutorial01.md
     * @return boolean true si todo ha ido bien, false en caso contrario.
     */
    public boolean tutorial1() {

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
            Logger.getLogger(Tutorial.class.getName()).log(Level.SEVERE, null, e);
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

    /**
     * Código del tutorial dos de Lanterna
     *
     * @see
     * https://github.com/mabe02/lanterna/blob/master/docs/tutorial/Tutorial02.md
     * @return boolean true si todo ha ido bien, false en caso contrario.
     */
    public boolean tutorial2() {
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
            Logger.getLogger(Tutorial.class.getName()).log(Level.SEVERE, e.getMessage());
            return false;
        } finally {
            if (term != null) {
                try {
                    term.close();
                } catch (IOException e) {
                    Logger.getLogger(Tutorial.class.getName()).log(Level.SEVERE, e.getMessage());
                }
            }
        }
    }

    /**
     * Código del tutorial tres de Lanterna
     *
     * @see
     * https://github.com/mabe02/lanterna/blob/master/docs/tutorial/Tutorial03.md
     * @return boolean true si todo ha ido bien, false en caso contrario.
     */
    public boolean tutorial3() {

        /*
        In the third tutorial, we will look at using the next layer available in Lanterna, which is built on top of the
        Terminal interface you saw in tutorial 1 and 2.

        A Screen works similar to double-buffered video memory, it has two surfaces than can be directly addressed and
        modified and by calling a special method that content of the back-buffer is move to the front. Instead of pixels
        though, a Screen holds two text character surfaces (front and back) which corresponds to each "cell" in the
        terminal. You can freely modify the back "buffer" and you can read from the front "buffer", calling the
        refreshScreen() method to copy content from the back buffer to the front buffer, which will make Lanterna also
        apply the changes so that the user can see them in the terminal.
         */
        DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
        Screen screen = null;
        try {
            /*
            You can use the DefaultTerminalFactory to create a Screen, this will generally give you the TerminalScreen
            implementation that is probably what you want to use. Please see VirtualScreen for more details on a separate
            implementation that allows you to create a terminal surface that is bigger than the physical size of the
            terminal emulator the software is running in. Just to demonstrate that a Screen sits on top of a Terminal,
            we are going to create one manually instead of using DefaultTerminalFactory.
             */
            Terminal terminal = defaultTerminalFactory.createTerminal();
            screen = new TerminalScreen(terminal);

            /*
            Screens will only work in private mode and while you can call methods to mutate its state, before you can
            make any of these changes visible, you'll need to call startScreen() which will prepare and setup the
            terminal.
             */
            screen.startScreen();

            /*
            Let's turn off the cursor for this tutorial
             */
            screen.setCursorPosition(null);

            /*
            Now let's draw some random content in the screen buffer
             */
            Random random = new Random();
            TerminalSize terminalSize = screen.getTerminalSize();
            for (int column = 0; column < terminalSize.getColumns(); column++) {
                for (int row = 0; row < terminalSize.getRows(); row++) {
                    screen.setCharacter(column, row, new TextCharacter(
                            ' ',
                            TextColor.ANSI.DEFAULT,
                            // This will pick a random background color
                            TextColor.ANSI.values()[random.nextInt(TextColor.ANSI.values().length)]));
                }
            }

            /*
            So at this point, we've only modified the back buffer in the screen, nothing is visible yet. In order to
            move the content from the back buffer to the front buffer and refresh the screen, we need to call refresh()
             */
            screen.refresh();

            /*
            Now there should be completely random colored cells in the terminal (assuming your terminal (emulator)
            supports colors). Let's look at it for two seconds or until the user press a key.
             */
            long startTime = System.currentTimeMillis();
            while (System.currentTimeMillis() - startTime < 2000) {
                // The call to pollInput() is not blocking, unlike readInput()
                if (screen.pollInput() != null) {
                    break;
                }
                try {
                    Thread.sleep(1);
                } catch (InterruptedException ignore) {
                    break;
                }
            }

            /*
            Ok, now we loop and keep modifying the screen until the user exits by pressing escape on the keyboard or the
            input stream is closed. When using the Swing/AWT bundled emulator, if the user closes the window this will
            result in an EOF KeyStroke.
             */
            while (true) {
                KeyStroke keyStroke = screen.pollInput();
                if (keyStroke != null && (keyStroke.getKeyType() == KeyType.Escape || keyStroke.getKeyType() == KeyType.EOF)) {
                    break;
                }

                /*
                Screens will automatically listen and record size changes, but you have to let the Screen know when is
                a good time to update its internal buffers. Usually you should do this at the start of your "drawing"
                loop, if you have one. This ensures that the dimensions of the buffers stays constant and doesn't change
                while you are drawing content. The method doReizeIfNecessary() will check if the terminal has been
                resized since last time it was called (or since the screen was created if this is the first time
                calling) and update the buffer dimensions accordingly. It returns null if the terminal has not changed
                size since last time.
                 */
                TerminalSize newSize = screen.doResizeIfNecessary();
                if (newSize != null) {
                    terminalSize = newSize;
                }

                // Increase this to increase speed
                final int charactersToModifyPerLoop = 1;
                for (int i = 0; i < charactersToModifyPerLoop; i++) {
                    /*
                    We pick a random location
                     */
                    TerminalPosition cellToModify = new TerminalPosition(
                            random.nextInt(terminalSize.getColumns()),
                            random.nextInt(terminalSize.getRows()));

                    /*
                    Pick a random background color again
                     */
                    TextColor.ANSI color = TextColor.ANSI.values()[random.nextInt(TextColor.ANSI.values().length)];

                    /*
                    Update it in the back buffer, notice that just like TerminalPosition and TerminalSize, TextCharacter
                    objects are immutable so the withBackgroundColor(..) call below returns a copy with the background color
                    modified.
                     */
                    TextCharacter characterInBackBuffer = screen.getBackCharacter(cellToModify);
                    characterInBackBuffer = characterInBackBuffer.withBackgroundColor(color);
                    characterInBackBuffer = characterInBackBuffer.withCharacter(' ');   // Because of the label box further down, if it shrinks
                    screen.setCharacter(cellToModify, characterInBackBuffer);
                }

                /*
                Just like with Terminal, it's probably easier to draw using TextGraphics. Let's do that to put a little
                box with information on the size of the terminal window
                 */
                String sizeLabel = "Terminal Size: " + terminalSize;
                TerminalPosition labelBoxTopLeft = new TerminalPosition(1, 1);
                TerminalSize labelBoxSize = new TerminalSize(sizeLabel.length() + 2, 3);
                TerminalPosition labelBoxTopRightCorner = labelBoxTopLeft.withRelativeColumn(labelBoxSize.getColumns() - 1);
                TextGraphics textGraphics = screen.newTextGraphics();
                //This isn't really needed as we are overwriting everything below anyway, but just for demonstrative purpose
                textGraphics.fillRectangle(labelBoxTopLeft, labelBoxSize, ' ');

                /*
                Draw horizontal lines, first upper then lower
                 */
                textGraphics.drawLine(
                        labelBoxTopLeft.withRelativeColumn(1),
                        labelBoxTopLeft.withRelativeColumn(labelBoxSize.getColumns() - 2),
                        Symbols.DOUBLE_LINE_HORIZONTAL);
                textGraphics.drawLine(
                        labelBoxTopLeft.withRelativeRow(2).withRelativeColumn(1),
                        labelBoxTopLeft.withRelativeRow(2).withRelativeColumn(labelBoxSize.getColumns() - 2),
                        Symbols.DOUBLE_LINE_HORIZONTAL);

                /*
                Manually do the edges and (since it's only one) the vertical lines, first on the left then on the right
                 */
                textGraphics.setCharacter(labelBoxTopLeft, Symbols.DOUBLE_LINE_TOP_LEFT_CORNER);
                textGraphics.setCharacter(labelBoxTopLeft.withRelativeRow(1), Symbols.DOUBLE_LINE_VERTICAL);
                textGraphics.setCharacter(labelBoxTopLeft.withRelativeRow(2), Symbols.DOUBLE_LINE_BOTTOM_LEFT_CORNER);
                textGraphics.setCharacter(labelBoxTopRightCorner, Symbols.DOUBLE_LINE_TOP_RIGHT_CORNER);
                textGraphics.setCharacter(labelBoxTopRightCorner.withRelativeRow(1), Symbols.DOUBLE_LINE_VERTICAL);
                textGraphics.setCharacter(labelBoxTopRightCorner.withRelativeRow(2), Symbols.DOUBLE_LINE_BOTTOM_RIGHT_CORNER);

                /*
                Finally put the text inside the box
                 */
                textGraphics.putString(labelBoxTopLeft.withRelative(1, 1), sizeLabel);

                /*
                Ok, we are done and can display the change. Let's also be nice and allow the OS to schedule other
                threads so we don't clog up the core completely.
                 */
                screen.refresh();
                Thread.yield();

                /*
                Every time we call refresh, the whole terminal is NOT re-drawn. Instead, the Screen will compare the
                back and front buffers and figure out only the parts that have changed and only update those. This is
                why in the code drawing the size information box above, we write it out every time we loop but it's
                actually not sent to the terminal except for the first time because the Screen knows the content is
                already there and has not changed. Because of this, you should never use the underlying Terminal object
                when working with a Screen because that will cause modifications that the Screen won't know about.
                 */
            }
        } catch (IOException e) {
        } finally {
            if (screen != null) {
                try {
                    /*
                    The close() call here will restore the terminal by exiting from private mode which was done in
                    the call to startScreen()
                     */
                    screen.close();
                } catch (IOException e) {
                }
            }
        }

        return false;
    }

    /**
     * Código del tutorial cuatro de Lanterna
     *
     * @see
     * https://github.com/mabe02/lanterna/blob/master/docs/tutorial/Tutorial04.md
     * @return voolean true si todo ha ido bien, false en caso contrario
     */
    public boolean tutorial4() {
        /*
        In this forth tutorial we will finally look at creating a multi-window text GUI, all based on text. Just like
        the Screen-layer in the previous tutorial was based on the lower-level Terminal layer, the GUI classes we will
        use here are all build upon the Screen interface. Because of this, if you use these classes, you should never
        interact with the underlying Screen that backs the GUI directly, as it might modify the screen in a way the
        GUI isn't aware of.
        The GUI system is designed around a background surface that is usually static, but can have components, and
        multiple windows. The recommended approach it to make all windows modal and not let the user switch between
        windows, but the latter can also be done. Components are added to windows by using a layout manager that
        determines the position of each component.
         */
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        Screen screen = null;

        try {
            /*
            The DefaultTerminalFactory class doesn't provide any helper method for creating a Text GUI, you'll need to
             get a Screen like we did in the previous tutorial and start it so it puts the terminal in private mode.
             */
            screen = terminalFactory.createScreen();
            screen.startScreen();

            /*
            There are a couple of different constructors to MultiWindowTextGUI, we are going to go with the defaults for
            most of these values. The one thing to consider is threading; with the default options, lanterna will use
            the calling thread for all UI operations which mean that you are basically letting the calling thread block
            until the GUI is shut down. There is a separate TextGUIThread implementaiton you can use if you'd like
            Lanterna to create a dedicated UI thread and not lock the caller. Just like with AWT and Swing, you should
            be scheduling any kind of UI operation to always run on the UI thread but lanterna tries to be best-effort
            if you attempt to mutate the GUI from another thread. Another default setting that will be applied is that
            the background of the GUI will be solid blue.
             */
            final WindowBasedTextGUI textGUI = new MultiWindowTextGUI(screen);

            /*
            Creating a
             */
            final Window window = new BasicWindow("My Root Window");

            /*
            The window has no content initially, you need to call setComponent to populate it with something. In this
            case, and quite often in fact, you'll want to use more than one component so we'll create a composite
            'Panel' component that can hold multiple sub-components. This is where we decide what the layout manager
            should be.
             */
            Panel contentPanel = new Panel(new GridLayout(2));

            /**
             * Lanterna contains a number of built-in layout managers, the
             * simplest one being LinearLayout that simply arranges components
             * in either a horizontal or a vertical line. In this tutorial,
             * we'll use the GridLayout which is based on the layout manager
             * with the same name in SWT. In the constructor above we have
             * specified that we want to have a grid with two columns, below we
             * customize the layout further by adding some spacing between the
             * columns.
             */
            GridLayout gridLayout = (GridLayout) contentPanel.getLayoutManager();
            gridLayout.setHorizontalSpacing(3);

            /*
            One of the most basic components is the Label, which simply displays a static text. In the example below,
            we use the layout data field attached to each component to give the layout manager extra hints about how it
            should be placed. Obviously the layout data has to be created from the same layout manager as the container
            is using, otherwise it will be ignored.
             */
            Label title = new Label("This is a label that spans two columns");
            title.setLayoutData(GridLayout.createLayoutData(
                    GridLayout.Alignment.BEGINNING, // Horizontal alignment in the grid cell if the cell is larger than the component's preferred size
                    GridLayout.Alignment.BEGINNING, // Vertical alignment in the grid cell if the cell is larger than the component's preferred size
                    true, // Give the component extra horizontal space if available
                    false, // Give the component extra vertical space if available
                    2, // Horizontal span
                    1));                  // Vertical span
            contentPanel.addComponent(title);

            /*
            Since the grid has two columns, we can do something like this to add components when we don't need to
            customize them any further.
             */
            contentPanel.addComponent(new Label("Text Box (aligned)"));
            contentPanel.addComponent(
                    new TextBox()
                            .setLayoutData(GridLayout.createLayoutData(GridLayout.Alignment.BEGINNING, GridLayout.Alignment.CENTER)));

            /*
            Here is an example of customizing the regular text box component so it masks the content and can work for
            password input.
             */
            contentPanel.addComponent(new Label("Password Box (right aligned)"));
            contentPanel.addComponent(
                    new TextBox()
                            .setMask('*')
                            .setLayoutData(GridLayout.createLayoutData(GridLayout.Alignment.END, GridLayout.Alignment.CENTER)));

            /*
            While we are not going to demonstrate all components here, here is an example of combo-boxes, one that is
            read-only and one that is editable.
             */
            contentPanel.addComponent(new Label("Read-only Combo Box (forced size)"));
            List<String> timezonesAsStrings = new ArrayList<>();
            timezonesAsStrings.addAll(Arrays.asList(TimeZone.getAvailableIDs()));
            ComboBox<String> readOnlyComboBox = new ComboBox<>(timezonesAsStrings);
            readOnlyComboBox.setReadOnly(true);
            readOnlyComboBox.setPreferredSize(new TerminalSize(20, 1));
            contentPanel.addComponent(readOnlyComboBox);

            contentPanel.addComponent(new Label("Editable Combo Box (filled)"));
            contentPanel.addComponent(
                    new ComboBox<>("Item #1", "Item #2", "Item #3", "Item #4")
                            .setReadOnly(false)
                            .setLayoutData(GridLayout.createHorizontallyFilledLayoutData(1)));
            /*
            Some user interactions, like buttons, work by registering callback methods. In this example here, we're
            using one of the pre-defined dialogs when the button is triggered.
             */
            contentPanel.addComponent(new Label("Button (centered)"));
            contentPanel.addComponent(new Button("Button", new Runnable() {
                @Override
                public void run() {
                    MessageDialog.showMessageDialog(textGUI, "MessageBox", "This is a message box", MessageDialogButton.OK);
                }
            }).setLayoutData(GridLayout.createLayoutData(GridLayout.Alignment.CENTER, GridLayout.Alignment.CENTER)));

            /*
            Close off with an empty row and a separator, then a button to close the window
             */
            contentPanel.addComponent(
                    new EmptySpace()
                            .setLayoutData(
                                    GridLayout.createHorizontallyFilledLayoutData(2)));
            contentPanel.addComponent(
                    new Separator(Direction.HORIZONTAL)
                            .setLayoutData(
                                    GridLayout.createHorizontallyFilledLayoutData(2)));
            contentPanel.addComponent(
                    new Button("Close", new Runnable() {
                        @Override
                        public void run() {
                            window.close();
                        }
                    }).setLayoutData(
                            GridLayout.createHorizontallyEndAlignedLayoutData(2)));

            /*
            We now have the content panel fully populated with components. A common mistake is to forget to attach it to
            the window, so let's make sure to do that.
             */
            window.setComponent(contentPanel);

            /*
            Now the window is created and fully populated. As discussed above regarding the threading model, we have the
            option to fire off the GUI here and then later on decide when we want to stop it. In order for this to work,
            you need a dedicated UI thread to run all the GUI operations, usually done by passing in a
            SeparateTextGUIThread object when you create the TextGUI. In this tutorial, we are using the conceptually
            simpler SameTextGUIThread, which essentially hijacks the caller thread and uses it as the GUI thread until
            some stop condition is met. The absolutely simplest way to do this is to simply ask lanterna to display the
            window and wait for it to be closed. This will initiate the event loop and make the GUI functional. In the
            "Close" button above, we tied a call to the close() method on the Window object when the button is
            triggered, this will then break the even loop and our call finally returns.
             */
            textGUI.addWindowAndWait(window);

            /*
            When our call has returned, the window is closed and no longer visible. The screen still contains the last
            state the TextGUI left it in, so we can easily add and display another window without any flickering. In
            this case, we want to shut down the whole thing and return to the ordinary prompt. We just need to stop the
            underlying Screen for this, the TextGUI system does not require any additional disassembly.
             */
        } catch (IOException e) {
        } finally {
            if (screen != null) {
                try {
                    screen.stopScreen();
                } catch (IOException e) {
                }
            }
        }
        return false;
    }
}
