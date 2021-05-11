package Estructuras;

import Objetos.Edificio;
import Objetos.Salon;
import javax.swing.JOptionPane;

/**
 *
 * @author lex
 */
public class ListaSimple<T> {

    private Nodo<T> root;
    private Nodo<T> end;

    public ListaSimple() {
        this.root = null;
        this.end = null;
    }

    public boolean add(T data) {
        Nodo<T> nuevoNodo = new Nodo<T>(data);
        if (root == null)
        {
            root = nuevoNodo;
            end = nuevoNodo;
        } else if (doesntExists(data))
        {
            end.setNext(nuevoNodo);
            end = nuevoNodo;
        } else
        {
            System.out.println("EL OBJETO YA EXISTE: " + getId(data));
            return false;
        }
        return true;
    }

    public boolean doesntExists(T data) {
        Nodo<T> aux = root;
        String aux_id;
        String data_id = getId(data);
        while (aux != null)
        {
            aux_id = getId(aux.getData());
            if (aux_id.equals(data_id))
            {
                return false;
            } else
            {
                aux = aux.getNext();
            }
        }
        return true;
    }

    private String getId(T data) {
        if (data instanceof Salon)
        {
            Salon salon = (Salon) data;
            return String.valueOf(salon.getId());
        }
        return null;
    }

    private Nodo<T> getNodo(String id) {
        if (root != null)
        {
            Nodo<T> aux = root;
            while (aux != null)
            {
                String id_aux = getId(aux.getData());
                if (id.equals(id_aux))
                {
                    return aux;
                } else
                {
                    aux = aux.getNext();
                }
            }
        }
        return null;
    }

    public T getData(String id) {
        if (root != null)
        {
            Nodo<T> aux = root;
            while (aux != null)
            {
                String id_aux = getId(aux.getData());
                if (id.equals(id_aux))
                {
                    return aux.getData();
                } else
                {
                    aux = aux.getNext();
                }
            }
        }
        return null;
    }

    private Nodo<T> getPrevious(Nodo<T> nodo) {
        Nodo<T> aux = root;

        while (aux != null)
        {
            if (aux.getNext() == nodo)
            {
                return aux;
            } else
            {
                aux = aux.getNext();
            }
        }
        return null;
    }

    public boolean delete(String id) {
        if (root != null)
        {
            Nodo<T> nodo = getNodo(id);
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
                    return true;
                } else if (id.equals(id_end))
                {
                    end = getPrevious(end);
                    end.setNext(null);
                    return true;
                }
                Nodo<T> nodoAnterior = getPrevious(nodo);
                Nodo<T> nodoSiguiente = nodo.getNext();
                nodoAnterior.setNext(nodoSiguiente);
                return true;
            }
        }
        return false;
    }

    public boolean update(T data) {
        String id = getId(data);
        Nodo<T> nodo = getNodo(id);
        if (nodo != null)
        {
            nodo.setData(data);
            return true;
        } else
        {
            return false;
        }
    }

    public T get(int indice) {
        int indice_aux = 0;
        if (root != null)
        {
            Nodo<T> aux = root;
            while (aux != null)
            {
                if (indice_aux == indice)
                {
                    return aux.getData();
                } else
                {
                    aux = aux.getNext();
                    indice_aux++;
                }
            }
        }
        return null;
    }

    public int getSize() {
        int size = 0;
        if (root != null)
        {
            Nodo<T> aux = root;
            while (aux != null)
            {
                size++;
                aux = aux.getNext();
            }
        }
        return size;
    }

    public void showData() {
        System.out.println("\n///////////////////////////////////////");
        if (root != null)
        {
            Nodo<T> aux = root;
            while (aux != null)
            {
                if (aux.getData() instanceof Salon)
                {
                    System.out.println("************ SALON ************");
                    Salon salon = (Salon) aux.getData();
                    System.out.println("Numero: " + salon.getId());
                    System.out.println("Capacidad: " + salon.getSize());
                    if (aux.getNext() != null)
                    {
                        System.out.println(" Siguiente: " + getId(aux.getNext().getData()));
                    }
                }

                aux = aux.getNext();
            }
        } else
        {
            System.out.println("************ LISTA VACIA ************");
        }
    }

    private class Nodo<T> {

        private T data;
        private Nodo<T> next;

        public Nodo(T data) {
            this.data = data;
        }

        public T getData() {
            return data;
        }

        public Nodo<T> getNext() {
            return next;
        }

        public void setData(T data) {
            this.data = data;
        }

        public void setNext(Nodo<T> next) {
            this.next = next;
        }

    }
}
