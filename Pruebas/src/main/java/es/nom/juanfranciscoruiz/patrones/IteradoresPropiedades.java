package es.nom.juanfranciscoruiz.patrones;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;

public class IteradoresPropiedades implements ObjetostIterador {

    private final Object objeto;
    private final PropertyDescriptor[] propiedades;
    private int siguienteIndice = -1;
    private PropertyDescriptor propiedadActual;

    public IteradoresPropiedades(Object objeto) {
        this.objeto = objeto;
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(objeto.getClass());
            propiedades = beanInfo.getPropertyDescriptors();
        } catch (RuntimeException e) {
            throw e;
        } catch (IntrospectionException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public boolean siguiente() {
        if (siguienteIndice + 1 >= propiedades.length) {
            return false;
        }

        siguienteIndice++;
        propiedadActual = propiedades[siguienteIndice];
        if (propiedadActual.getReadMethod() == null || "class".equals(propiedadActual.getName())) {
            return siguiente();
        }
        return true;
    }

    @Override
    public String getNombre() {
        if (propiedadActual == null) {
            return null;
        }
        return propiedadActual.getName();
    }

    @Override
    public Object getValor() {
        try {
            if (propiedadActual == null) {
                return null;
            }
            return propiedadActual.getReadMethod().invoke(objeto);
        } catch (RuntimeException e) {
            throw e;
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
