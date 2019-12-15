package es.nom.juanfranciscoruiz.vista;

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
    public StringBuffer exploraObjeto(Object msj, boolean sl) {
        StringBuffer sb = new StringBuffer();
        sb.setLength(0);
        if (msj != null) {
            sb = procesaObjeto(msj);
            if (sb.length() == 0) {
                Class<? extends Object> cl = msj.getClass();
                if (cl.isArray()) {
                    sb = exploraVector((Object[]) msj, sl);
                } else if (cl.isAssignableFrom(ArrayList.class)) {
                    sb = exploraLista((List<?>) msj, sl);
                } else if (cl.isAssignableFrom(HashMap.class)) {
                    sb = exploraMapa((Map<?, ?>) msj, sl);
                }
            }
        } else {
            sb.append("NULL");
        }
        return sb;
    }

    @Override
    public StringBuffer exploraVector(Object[] matriz, boolean sl) {
        StringBuffer sb = new StringBuffer();
        if (matriz != null) {
            int elementos = matriz.length;
            if (elementos > 0) {
                int indice = 0;
                sb.append("VECTOR").append(PA)
                        .append(elementos).append(PC)
                        .append(":").append(LA).append(SL);
                for (Object o : matriz) {
                    sb.append(CA).append(indice).append(CC).append(" = ");
                    sb.append(LA);
                    if (o != null) {
                        Class<? extends Object> clazz = o.getClass();
                        if (clazz.isArray()) {
                            Object oa[] = (Object[]) o;
                            sb.append(exploraVector(oa, sl));
                        } else if (clazz.isAssignableFrom(HashMap.class)) {
                            HashMap<?, ?> hm = (HashMap<?, ?>) o;
                            sb.append(exploraMapa(hm, sl));
                        } else {
                            sb.append(procesaObjeto(o));
                        }
                    } else {
                        sb.append("NULL");
                    }
                    sb.append(" ").append(LC);

                    if (sl) {
                        sb.append(SL);
                    } else {
                        sb.append(",");
                    }
                    indice++;
                }
                sb = sb.deleteCharAt(sb.length() - 1);
                sb.append(SL).append(LC);
            } else {
                sb.append("* Vector VACIO *").append(" ").append(LC);
            }
        } else {
            sb.append("NULL").append(" ").append(LC);
        }
        return sb;
    }

    @Override
    public StringBuffer exploraLista(List<?> lista, boolean sl) {
        StringBuffer sb = new StringBuffer();
        if (lista != null) {
            int indice = 0;
            int elementos = lista.size();
            if (elementos > 0) {
                sb.append("ARRAYLIST")
                        .append(PA).append(elementos).append(PC)
                        .append(":").append(LA).append(SL);
                for (Object o : lista) {
                    sb.append(CA).append(indice).append(CC);
                    sb.append(LA);
                    if (o != null) {
                        Class<? extends Object> cl = o.getClass();
                        if (cl.isArray()) {
                            Object oa[] = (Object[]) o;
                            sb.append(exploraVector(oa, sl));
                        } else if (cl.isAssignableFrom(ArrayList.class)) {
                            ArrayList<?> otraLista = (ArrayList<?>) o;
                            sb.append(exploraLista(otraLista, sl));
                        } else if (cl.isAssignableFrom(HashMap.class)) {
                            HashMap<?, ?> hm = (HashMap<?, ?>) o;
                            sb.append(exploraMapa(hm, sl));
                        } else {
                            sb.append(procesaObjeto(o));
                        }
                    } else {
                        sb.append("NULL");
                    }

                    sb.append(LC);
                    if (sl) {
                        sb.append(SL);
                    } else {
                        sb.append(",");
                    }
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
    public StringBuffer exploraMapa(Map<?,?> value2, boolean sl) {
        StringBuffer sb = new StringBuffer();
        if (value2 != null) {
            if (!value2.isEmpty()) {
                int tam = value2.size();
                Set<?> conjunto = (Set<?>) value2.keySet();
                Iterator<?> iter = conjunto.iterator();
                sb.append("HASHMAP").append(PA).append(tam).append(PC).append(":");
                while (iter.hasNext()) {
                    Object key = iter.next();
                    Object value = value2.get(key);
                    sb.append("CLAVE=").append(LA).append(key.toString()).append(LC)
                            .append(", VALOR=").append(LA).append(" ");
                    if (value != null) {
                        Class<? extends Object> clazz = value.getClass();
                        if (clazz.isArray()) {
                            sb.append(exploraVector((Object[]) value, sl));
                        } else if (clazz.isAssignableFrom(ArrayList.class)) {
                            sb.append(exploraLista((ArrayList<?>) value, sl));
                        } else if (clazz.isAssignableFrom(HashMap.class)) {
                            sb.append(exploraMapa((HashMap<?,?>) value, sl));
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
    public StringBuffer exploraObjetos(boolean sl, Object... msj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private StringBuffer procesaObjeto(Object o) {
        StringBuffer sb = new StringBuffer();
        if (o != null) {
            Class<? extends Object> cl = o.getClass();

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
