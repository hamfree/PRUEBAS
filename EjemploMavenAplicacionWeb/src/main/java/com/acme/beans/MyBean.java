/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.beans;

/**
 *
 * @author hamfree
 */
public class MyBean {

    private String foo = "Default Foo";

    public String getFoo() {
        return (this.foo);
    }

    public void setFoo(String foo) {
        this.foo = foo;
    }

    private int bar = 0;

    public int getBar() {
        return (this.bar);
    }

    public void setBar(int bar) {
        this.bar = bar;
    }
}
