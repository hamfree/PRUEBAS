package name.ruiz.juanfco.texto;

import java.util.*;

public class EjemploLocale {


    public void muestraLocale() {
        Locale locale = Locale.getDefault();
        System.out.println("Pa\u00EDs para mostrar.....: '" + locale.getDisplayCountry() + "'");
        System.out.println("Lenguaje para mostrar.: '" + locale.getDisplayLanguage() + "'");
        System.out.println("Nombres...............: '" + locale.getDisplayName() + "'");
        System.out.println("Pais ISO3.............: '" + locale.getISO3Country() + "'");
        System.out.println("Lenguaje ISO3.........: '" + locale.getISO3Language() + "'");
        System.out.println("Lenguaje..............: '" + locale.getLanguage() + "'");
        System.out.println("Pa\u00EDs..................: '" + locale.getCountry() + "'");
    }

}
