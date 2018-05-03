package name.ruiz.juanfco.vista;

import java.util.List;
import java.util.Map;

/**
 *
 * @author hamfree
 */
public interface Vista {

    public StringBuffer exploraObjeto(Object obj);

    public StringBuffer exploraLista(List<?> lista);

    public StringBuffer exploraMapa(Map<Object, Object> mapa);

    public StringBuffer exploraObjetos(Object... obj);

    public StringBuffer exploraVector(Object[] vector);
}
