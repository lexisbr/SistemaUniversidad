/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import Objetos.Edificio;
import Objetos.Usuario;
import javax.swing.JOptionPane;

/**
 *
 * @author lex
 */
public class ListaCircular<T> {

    private Nodo<T> root;
    private Nodo<T> end;
    private int size;

    public static void main(String[] args) {
        ListaCircular<Usuario> lista = new ListaCircular<Usuario>();
        lista.add(new Usuario(123, "Alejandrio", "Alejandrio", Usuario.ESTUDIANTE));
        lista.add(new Usuario(1234, "Gordo", "Alejandrio", Usuario.ESTUDIANTE));
        lista.add(new Usuario(1235, "Trolo", "Alejandrio", Usuario.ESTUDIANTE));

        lista.mostrarDatos();
        lista.delete("123");
        lista.mostrarDatos();
        lista.delete("1235");
        lista.mostrarDatos();
        lista.delete("1234");
        lista.mostrarDatos();
        lista.mostrarDatos();
        lista.update(new Usuario(1235, "Alejandro Barrios", "sheeesh", Usuario.COLABORADOR));
        lista.mostrarDatos();

    }

    public ListaCircular() {
        root = null;
        end = null;
    }

    public void add(T data) {
        Nodo nuevoNodo = new Nodo<T>(data);

        if (root == null)
        {
            root = nuevoNodo;
            end = nuevoNodo;
            root.setNext(root);
            root.setPrev(end);
        } else if (doesntExist(data))
        {
            end.setNext(nuevoNodo);
            nuevoNodo.setNext(root);
            nuevoNodo.setPrev(end);
            end = nuevoNodo;
            root.setPrev(end);
        } else
        {
            String id = getId(data);
            JOptionPane.showMessageDialog(null, "El dato: \"" + id + "\" ya existe.");
        }
    }

    private boolean doesntExist(T data) {
        String aux_id;
        String id = getId(data);

        Nodo<T> aux = root;

        do
        {
            aux_id = getId(aux.getData());
            if (aux_id.equals(id))
            {
                return false;
            }
            aux = aux.getNext();

        } while (aux != root);

        return true;
    }

    private String getId(T data) {
        if (data instanceof Usuario)
        {
            Usuario user = (Usuario) data;
            return String.valueOf(user.getId());
        } else if (data instanceof Edificio)
        {
            Edificio edificio = (Edificio) data;
            return edificio.getName();
        }

        return null;
    }

    public Nodo<T> getNodo(String id) {
        if (root != null)
        {
            Nodo<T> aux = root;
            do
            {
                String id_aux = getId(aux.getData());
                if (id.equals(id_aux))
                {
                    return aux;
                } else
                {
                    aux = aux.getNext();
                }
            } while (aux != root);
        }
        return null;
    }

    public T getData(String id) {
        if (root != null)
        {
            Nodo<T> aux = root;
            do
            {
                String id_aux = getId(aux.getData());
                if (id.equals(id_aux))
                {
                    return aux.getData();
                } else
                {
                    aux = aux.getNext();
                }
            } while (aux != root);
        }
        return null;

    }

    public boolean delete(String id) {
        if (root != null)
        {
            Nodo nodo = getNodo(id);
            if (nodo != null)
            {
                String id_root = getId(root.getData());
                String id_end = getId(end.getData());
                if (root == end)
                {
                    root = end = null;
                    return true;
                } else if (id.equals(id_root))
                {
                    root = root.getNext();
                } else if (id.equals(id_end))
                {
                    end = end.getPrev();
                }
                Nodo<T> nodoAnterior = nodo.getPrev();
                Nodo<T> nodoSiguiente = nodo.getNext();
                nodoAnterior.setNext(nodoSiguiente);
                nodoSiguiente.setPrev(nodoAnterior);
                return true;
            }
        }
        return false;
    }

    public boolean update(T data) {
        Nodo<T> nodo = getNodo(getId(data));
        if (nodo != null)
        {
            nodo.setData(data);
            return true;
        }

        return false;
    }

    public void mostrarDatos() {
        if (root != null)
        {
            Nodo<T> aux = root;
            System.out.println("///////////////////////////////////////");
            do
            {
                if (aux.getData() instanceof Usuario)
                {
                    Usuario user = (Usuario) aux.getData();
                    System.out.println("************ USUARIO ************");
                    System.out.println("ID: " + user.getId());
                    System.out.println("Nombre: " + user.getName());
                    System.out.println("Password: " + user.getPassword());
                    System.out.println("Tipo: " + user.getType());
                    if (aux.getNext() != null)
                    {
                        System.out.println("Siguiente: " + getId(aux.getNext().getData()));
                    }
                    if (aux.getPrev() != null)
                    {
                        System.out.println("Anterior: " + getId(aux.getPrev().getData()));
                    }

                } else if (aux.getData() instanceof Edificio)
                {

                }
                aux = aux.getNext();
            } while (aux != root);
        } else
        {
            System.out.println("******* LISTA VACIA *******");
        }
    }

    private class Nodo<T> {

        private T data;
        private Nodo<T> next;
        private Nodo<T> prev;

        public Nodo(T data) {
            this.data = data;
        }

        public T getData() {
            return data;
        }

        public Nodo<T> getNext() {
            return next;
        }

        public Nodo<T> getPrev() {
            return prev;
        }

        public void setData(T data) {
            this.data = data;
        }

        public void setNext(Nodo<T> next) {
            this.next = next;
        }

        public void setPrev(Nodo<T> prev) {
            this.prev = prev;
        }

    }

}
