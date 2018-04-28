/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package name.ruiz.juanfco.servicio;

import name.ruiz.juanfco.modelo.Poblacion;

/**
 *
 * @author hamfree
 */
public interface PoblacionesServicio {

    public Poblacion busca(String idPoblacion);

    public boolean inserta(Poblacion poblacion);

    public boolean elimina(String idPoblacion);

    public boolean modifica(Poblacion poblacion);
}
