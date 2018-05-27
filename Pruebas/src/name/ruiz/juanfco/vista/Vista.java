package name.ruiz.juanfco.vista;

import java.util.List;
import java.util.Map;

/**
 *
 * @author hamfree
 */
public interface Vista {

    public StringBuffer exploraObjeto(Object obj, boolean saltarLinea);

    public StringBuffer exploraLista(List<?> lista, boolean saltarLinea);

    public StringBuffer exploraMapa(Map<Object, Object> mapa, boolean saltarLinea);

    public StringBuffer exploraObjetos(boolean saltarLinea, Object... obj);

    public StringBuffer exploraVector(Object[] vector, boolean saltarLinea);
}
