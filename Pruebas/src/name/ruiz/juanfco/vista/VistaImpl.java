package name.ruiz.juanfco.vista;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author hamfree
 */
public class VistaImpl implements Vista {

    private final String S = "|";
    private final String T = "\"";
    private final String CO = "'";
    private final String LA = "{";
    private final String LC = "}";
    private final String CA = "[";
    private final String CC = "]";
    private final String PA = "(";
    private final String PC = ")";
    private final String SL = System.getProperty("line.separator");
    private final String TAB = " ";

    @Override
    public StringBuffer exploraObjeto(Object msj) {
        StringBuffer sb = new StringBuffer();
        sb.setLength(0);
        if (msj != null) {
            sb = procesaObjeto(msj);
            if (sb.length() == 0) {
                Class cl = msj.getClass();
                if (cl.isArray()) {
                    sb = exploraVector((Object[]) msj);
                } else if (cl.isAssignableFrom(ArrayList.class)) {
                    sb = exploraLista((List<?>) msj);
                } else if (cl.isAssignableFrom(HashMap.class)) {
                    sb = exploraMapa((Map<Object, Object>) msj);
                }
            }
        } else {
            sb.append("NULL");
        }
        return sb;
    }

    @Override
    public StringBuffer exploraVector(Object[] matriz) {
        StringBuffer sb = new StringBuffer();
        if (matriz != null) {
            int elementos = matriz.length;
            if (elementos > 0) {
                int indice = 0;
                sb.append("VECTOR").append(PA)
                        .append(elementos).append(PC)
                        .append(":").append(LA).append(" ");
                for (Object o : matriz) {
                    sb.append(CA).append(indice).append(CC).append(" = ");
                    sb.append(LA);
                    if (o != null) {
                        Class clazz = o.getClass();
                        if (clazz.isArray()) {
                            Object oa[] = (Object[]) o;
                            sb.append(exploraVector(oa));
                        } else if (clazz.isAssignableFrom(HashMap.class)) {
                            HashMap<Object, Object> hm = (HashMap<Object, Object>) o;
                            sb.append(exploraMapa(hm));
                        } else {
                            sb.append(procesaObjeto(o));
                        }
                    } else {
                        sb.append("NULL").append(",");
                    }
                    sb.append(" ").append(LC).append(",");
                    indice++;
                }
                sb = sb.deleteCharAt(sb.length() - 1);
            } else {
                sb.append("* Vector VACIO *").append(" ").append(LC);
            }
        } else {
            sb.append("NULL").append(" ").append(LC);
        }
        return sb;
    }

    @Override
    public StringBuffer exploraLista(List<?> lista) {
        StringBuffer sb = new StringBuffer();
        if (lista != null) {
            int indice = 0;
            int elementos = lista.size();
            if (elementos > 0) {
                sb.append("ARRAYLIST")
                        .append(PA).append(elementos).append(PC)
                        .append(":").append(LA).append(" ");
                for (Object o : lista) {
                    sb.append(CA).append(indice).append(CC);
                    sb.append(LA);
                    if (o != null) {
                        Class cl = o.getClass();
                        if (cl.isArray()) {
                            Object oa[] = (Object[]) o;
                            sb.append(exploraVector(oa));
                        } else if (cl.isAssignableFrom(ArrayList.class)) {
                            ArrayList<Object> otraLista = (ArrayList<Object>) o;
                            sb.append(exploraLista(otraLista));
                        } else if (cl.isAssignableFrom(HashMap.class)) {
                            HashMap<Object, Object> hm = (HashMap<Object, Object>) o;
                            sb.append(exploraMapa(hm));
                        } else {
                            sb.append(procesaObjeto(o));
                        }
                    } else {
                        sb.append("NULL");
                    }
                    sb.append(LC).append(",");
                    indice++;
                }
                sb = sb.deleteCharAt(sb.length() - 1);
                sb.append(" ").append(LC);
            } else {
                sb.append("* ArrayList VACIO *").append(" ").append(LC);
            }
        } else {
            sb.append("NULL").append(" ").append(LC);
        }
        return sb;
    }

    @Override
    public StringBuffer exploraMapa(Map<Object, Object> mapa) {
        StringBuffer sb = new StringBuffer();
        if (mapa != null) {
            if (!mapa.isEmpty()) {
                int tam = mapa.size();
                Set<Object> conjunto = mapa.keySet();
                Iterator<Object> iter = conjunto.iterator();
                sb.append("HASHMAP").append(PA).append(tam).append(PC).append(":");
                while (iter.hasNext()) {
                    Object key = iter.next();
                    Object value = mapa.get(key);
                    sb.append("CLAVE=").append(LA).append(key.toString()).append(LC)
                            .append(", VALOR=").append(LA).append(" ");
                    if (value != null) {
                        Class clazz = value.getClass();
                        if (clazz.isArray()) {
                            sb.append(exploraVector((Object[]) value));
                        } else if (clazz.isAssignableFrom(ArrayList.class)) {
                            sb.append(exploraLista((ArrayList) value));
                        } else if (clazz.isAssignableFrom(HashMap.class)) {
                            sb.append(exploraMapa((HashMap) value));
                        } else {
                            sb.append(procesaObjeto(value));
                        }
                    } else {
                        sb.append("NULL");
                    }
                    sb.append(" ").append(LC);
                }
            } else {
                sb.append("* HashMap VACIO *").append(" ").append(LC);
            }
        } else {
            sb.append("NULL").append(" ").append(LC);
        }
        return sb;
    }

    @Override
    public StringBuffer exploraObjetos(Object... msj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private StringBuffer procesaObjeto(Object o) {
        StringBuffer sb = new StringBuffer();
        if (o != null) {
            Class cl = o.getClass();
            String nc = cl.getSimpleName();

            if (cl.isAssignableFrom(Byte.class)) {
                Byte b = (Byte) o;
                sb.append(S).append(b.toString()).append(S);
            } else if (cl.isAssignableFrom(Short.class)) {
                Short s = (Short) o;
                sb.append(S).append(s.toString()).append(S);
            } else if (cl.isAssignableFrom(Integer.class)) {
                Integer i = (Integer) o;
                sb.append(S).append(i.toString()).append(S);
            } else if (cl.isAssignableFrom(Double.class)) {
                Double d = (Double) o;
                sb.append(S).append(d.toString()).append(S);
            } else if (cl.isAssignableFrom(BigDecimal.class)) {
                BigDecimal bg = (BigDecimal) o;
                sb.append(S).append(bg.toPlainString()).append(S);
            } else if (cl.isAssignableFrom(Long.class)) {
                Long l = (Long) o;
                sb.append(S).append(l.toString()).append(S);
            } else if (cl.isAssignableFrom(String.class)) {
                String s = (String) o;
                sb.append(T).append(s).append(T);
            } else if (cl.isAssignableFrom(Character.class)) {
                Character ch = (Character) o;
                sb.append(CO).append(ch.toString()).append(CO);
            }
        } else {
            sb.append("NULL");
        }
        return sb;
    }
}
