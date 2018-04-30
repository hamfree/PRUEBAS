/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package name.ruiz.juanfco.servicio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import name.ruiz.juanfco.modelo.Poblacion;

/**
 *
 * @author hamfree
 */
public class ImportaCSVimpl implements ImportaCSV {

    static final Logger LOG = Logger.getLogger(ImportaCSVimpl.class.getName());

    @Override
    public List<Poblacion> importa(File fcsv) {
        ArrayList<Poblacion> alPoblaciones = null;
        int contador = 0;
        if (fcsv == null) {
            return null;
        } else if (fcsv.exists() && fcsv.canRead()) {
            try {
                debug();
                LOG.log(Level.INFO, "El fichero ''{0}'' existe y se puede leer.", fcsv.getName());
            } catch (SecurityException | IOException ex) {
                Logger.getLogger(ImportaCSVimpl.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {

                alPoblaciones = new ArrayList<>();
                Scanner scanner = new Scanner(fcsv, "UTF-8");
                while (scanner.hasNextLine()) {
                    String linea = scanner.nextLine();
                    contador++;
                    Scanner scLinea = new Scanner(linea);
                    scLinea.useDelimiter(";");
                    if (scLinea.hasNext()) {
                        String idCCAA = scLinea.next();
                        String idProv = scLinea.next();
                        String idPobl = scLinea.next();
                        String poblacion = scLinea.next();

                        //Las cadenas van entre comillas y las quitamos
                        idCCAA = idCCAA.replace("\"", "");
                        idProv = idProv.replace("\"", "");
                        idPobl = idPobl.replace("\"", "");
                        poblacion = poblacion.replace("\"", "");
                        Poblacion pob = new Poblacion(idPobl, idProv, idCCAA, poblacion);
                        alPoblaciones.add(pob);
                    }
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ImportaCSVimpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchElementException ex) {
                Logger.getLogger(ImportaCSVimpl.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Excepcion en la linea " + contador);
                System.out.println(ex.getLocalizedMessage());
                ex.printStackTrace();
                return alPoblaciones;
            }
        } else {
            StringBuilder sb = new StringBuilder();
            if (!fcsv.exists()) {
                sb.append("No existe el fichero '")
                        .append(fcsv.getName())
                        .append("'");
            } else if (!fcsv.canRead()) {
                sb.append("El fichero '")
                        .append(fcsv.getName())
                        .append("'")
                        .append(" existe pero no se puede leer.");
            }
            System.out.println(sb.toString());
        }
        return alPoblaciones;
    }

    private void debug()
            throws SecurityException, IOException {
        // creando manejador de archivo
        FileHandler fh = new FileHandler("./municipios.log", //pattern
                10485760, //limit
                3, // count
                true); //append
        fh.setLevel(Level.ALL); // level
        fh.setFormatter(new SimpleFormatter()); //formatter

        // agregar el manejador de archivo al LOG
        LOG.addHandler(fh);

        // el manejador de consola se agrega automaticamente, solo
        // cambiamos el nivel de detalle a desplegar
        LOG.getHandlers()[0].setLevel(Level.SEVERE);

        // se establece el nivel predeterminado global
        LOG.setLevel(Level.INFO);
    }
}
