/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package name.ruiz.juanfco.pruebas;

/**
 *
 * @author hamfree
 */
public class MetodosDeObject {

    private Object unObjeto;
    private Object otroObjeto;
    
    // objetoB apuntará a objetoA para probar el metodo equals() y hashCode().
    private Object objetoA;
    private Object objetoB;

    // Constructores
    public MetodosDeObject() {
        this.objetoA = new Object();
        this.objetoB = this.objetoA; // ObjetoB es una referencia a objetoA
    }

    public MetodosDeObject(Object unObjeto, Object otroObjeto) {
        this.unObjeto = unObjeto;
        this.otroObjeto = otroObjeto;
        this.objetoA = new Object();
        this.objetoB = this.objetoA; // ObjetoB es una referencia a objetoA
    }

    public MetodosDeObject(Object unObjeto, Object otroObjeto, Object objetoA, Object objetoB) {
        this.unObjeto = unObjeto;
        this.otroObjeto = otroObjeto;

        /* Con este constructor objetoA y objetoB son instancias independientes (a menos que se les pase como parámetro el mismo
           objeto
         */
        this.objetoA = objetoA;
        this.objetoB = objetoB;
    }
    
    
    // Getters y Setters
    public Object getUnObjeto() {
        return unObjeto;
    }

    public void setUnObjeto(Object unObjeto) {
        this.unObjeto = unObjeto;
    }

    public Object getOtroObjeto() {
        return otroObjeto;
    }

    public void setOtroObjeto(Object otroObjeto) {
        this.otroObjeto = otroObjeto;
    }

    public Object getObjetoA() {
        return objetoA;
    }

    public void setObjetoA(Object objetoA) {
        this.objetoA = objetoA;
    }

    public Object getObjetoB() {
        return objetoB;
    }

    public void setObjetoB(Object objetoB) {
        this.objetoB = objetoB;
    }

}
