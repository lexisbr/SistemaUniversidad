/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nodos;

/**
 *
 * @author lex
 */
public class NodoListaCircular<T> {

    private T data;
    private NodoListaCircular<T> next;
    private NodoListaCircular<T> prev;

    public NodoListaCircular(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public NodoListaCircular<T> getNext() {
        return next;
    }

    public NodoListaCircular<T> getPrev() {
        return prev;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setNext(NodoListaCircular<T> next) {
        this.next = next;
    }

    public void setPrev(NodoListaCircular<T> prev) {
        this.prev = prev;
    }

}
