/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package name.ruiz.juanfco.servicio;

import java.io.File;
import java.util.List;
import name.ruiz.juanfco.modelo.Poblacion;

/**
 *
 * @author hamfree
 */
public interface ImportaCSV {
    public List<Poblacion> importa(File fcsv);
}
