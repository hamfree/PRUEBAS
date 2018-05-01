package name.ruiz.juanfco.inicio;

import name.ruiz.juanfco.beans.A;

public class Prueba {

    public static void main(String[] args) {
        Prueba prueba = new Prueba();
        A a = new A("Creado por el constructor", 0);

        System.out.println("a = " + a.toString());
        prueba.metodo1(a);
        System.out.println("a = " + a.toString());

        /*
            Mientras no asignemos el valor devuelto por el método al objeto no 
            lo  cambiaremos por otro nuevo asignado dentro del método, pero sí
            se  pueden modificar sus propiedades....
         */
        prueba.metodo2(a);
        System.out.println("a = " + a.toString());

        a = prueba.metodo2(a);
        System.out.println("a = " + a.toString());

        /*
            Tanto metodo3() como metodo5() consiguen cambiar las propiedades de 
            la instancia a, ya sea que devuelvan la 
            instancia a o no devuelvan nada.
         */
        prueba.metodo3(a, "modificado en metodo 3", 3);
        System.out.println("a = " + a.toString());

        a = prueba.metodo4(a, "modificado en metodo 5", 5);
        System.out.println("a = " + a.toString());

        // Sin asignar el resultado, metodo4() no consigue que a sea null...
        prueba.metodo5(a);
        System.out.println("a = " + a.toString());

        // Asignando el resultado, metodo4() hace que a sea null...
        a = prueba.metodo5(a);
        if (a != null) {
            System.out.println("a = " + a.toString());
        } else {
            System.out.println("a = null");
        }

    }

    /**
     * Asigna una nueva instancia de A con parametros nulos a la instancia de A que se le pasa
     *
     * @param a la instancia de A a la que se quiere asignar una nueva instancia de A con parámetros nulos
     */
    public void metodo1(A a) {
        System.out.println("--> metodo1()");
        if (a != null) {
            System.out.println("\ta = " + a.toString());
            a = new A(null, null);
            System.out.println("\ta = " + a.toString());
        }
        System.out.println("<-- metodo1()");
    }

    /**
     * Asigna una nueva instancia de A con parametros nulos a la instancia de A que se le pasa, devolviendo de nuevo la instancia
     * modificada
     *
     * @param a instancia de A a la que se quiere asignar una nueva instancia de A con parámetros nulos
     * @return una instancia de A con los parámetros nulos
     */
    public A metodo2(A a) {
        System.out.println("--> metodo2()");
        if (a != null) {
            System.out.println("\ta = " + a.toString());
            a = new A(null, null);
            System.out.println("\ta = " + a.toString());
        }
        System.out.println("<-- metodo2()");
        return a;
    }

    /**
     * Cambia los parámetros de la instancia de A.
     *
     * @param a la instancia de A a la que se modifican sus propiedades 'cadena' y 'entero'
     * @param s la nueva String que contendrá la propiedad 'cadena' de la instancia de A apuntada por a
     * @param i el nuevo Integer que contendrá la propiedad 'entero' de la instancia de A apuntada por a
     */
    public void metodo3(A a, String s, Integer i) {
        System.out.println("--> metodo3()");
        if (a != null) {
            System.out.println("\ta = " + a.toString());
            a.setCadena(s);
            a.setEntero(i);
            System.out.println("\ta = " + a.toString());
        }
        System.out.println("<-- metodo3()");
    }

    /**
     * Cambia los parámetros de una instancia de A, devolviéndola al finalizar.
     *
     * @param a la instancia de A a la que se modifican sus propiedades 'cadena' y 'entero'
     * @param s la nueva String que contendrá la propiedad 'cadena' de la instancia de A apuntada por a
     * @param i el nuevo Integer que contendrá la propiedad 'entero' de la instancia de A apuntada por a
     * @return una instancia de A con los parámetros cambiados
     */
    public A metodo4(A a, String s, Integer i) {
        System.out.println("--> metodo4()");
        if (a != null) {
            System.out.println("\ta = " + a.toString());
            a.setCadena(s);
            a.setEntero(i);
            System.out.println("\ta = " + a.toString());
        }
        System.out.println("<-- metodo4()");
        return a;
    }

    /**
     * Hace que instancia de A pasada como argumento apunte a null.
     *
     * @param a
     * @return una instancia de A que apunta a null.
     */
    public A metodo5(A a) {
        System.out.println("--> metodo5()");
        System.out.println("\ta = " + a.toString());
        a = null;
        System.out.println("\ta = null");
        System.out.println("<-- metodo5()");
        return a;
    }

}
