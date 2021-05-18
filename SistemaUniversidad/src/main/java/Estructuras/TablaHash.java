/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import Objetos.Estudiante;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author lex
 */
public class TablaHash<T> {

    private int CAPACITY;
    private float LF;
    private int MAX_SIZE;
    private List<T>[] array;
    private int size = 0;

    public TablaHash() {
        CAPACITY = 37;
        LF = 0.55f;
        MAX_SIZE = (int) (LF * CAPACITY);
        array = new List[CAPACITY];
        for (int i = 0; i < CAPACITY; i++)
        {
            array[i] = new List<>();
        }
    }

    public int getCapacity() {
        return CAPACITY;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T searchData(int id) {
        int index = hashing(id);
        List<T> aux_lista;
        int contador = 0;
        while (contador <= getSize())
        {
            aux_lista = array[index];
            if (aux_lista.getHead() != null)
            {
                if (getId(aux_lista.getHead().val) == id)
                {
                    return aux_lista.getHead().val;
                }
            }
            index = hashingCollision(id, contador);
            contador++;
        }
        return null;
    }

    public int getId(T data) {
        if (data instanceof Estudiante)
        {
            Estudiante estudiante = (Estudiante) data;
            return estudiante.getId();
        }
        return 0;
    }

    public void update(Estudiante estudianteEdited) {
        Estudiante estudiante = (Estudiante) searchData(estudianteEdited.getId());
        estudiante.setName(estudianteEdited.getName());
        estudiante.setAddress(estudianteEdited.getAddress());
    }

    public boolean add(T data) {
        if (searchData(getId(data)) != null)
        {
            return false;
        }

        if (contains(data))
        {
            int cont = 0;
            while (containsColission(getId(data), cont))
            {
                cont++;
            }
            addItemColission(data, cont);
            ++size;
            if (size == MAX_SIZE)
            {
                resize(); // resize
                return true;
            }

        } else
        {
            addItem(data); // add item into array
            ++size; // increase size
            System.out.println();
            if (size == MAX_SIZE)
            {
                resize(); // resize
                return true;
            }

        }

        return true;
    }

    private void addItem(T item) {
        int index = hashing(getId(item));
        array[index].add(item);
    }

    private void addItemColission(T item, int i) {
        int index = hashingCollision(getId(item), i); // rehash
        array[index].add(item);
    }

    public boolean contains(T item) {
        int index = hashing(getId(item));
        List<T> aux_list = array[index];
        if (aux_list.getHead() != null)
        {
            return true;
        }
        return false;
    }

    public boolean containsColission(int id, int i) {
        int index = hashingCollision(id, i);
        List<T> list = array[index];
        if (list.getHead() != null)
        {
            return true;
        }
        return false;
    }

    public boolean remove(int id) {
        int index = hashing(id);
        List<T> aux_list;
        int contador = 0;

        while (contador <= getSize())
        {
            aux_list = array[index];
            if (aux_list.getHead() != null)
            {
                if (getId(aux_list.getHead().val) == id)
                {
                    aux_list.removeHead();
                    size--;
                    return true;
                }
            }
            index = hashingCollision(id, contador);
            contador++;
        }
        return false;

    }

    private boolean resize() {
        int temp = CAPACITY;
        CAPACITY = CAPACITY << 1;
        MAX_SIZE = (int) (LF * CAPACITY);
        List<T>[] arrayCopy = array;
        array = new List[CAPACITY];
        for (int i = 0; i < CAPACITY; i++)
        {
            array[i] = new List<>();
        }
        for (int i = 0; i < temp; i++)
        {
            Iterator<T> it = arrayCopy[i].iterator();
            while (it.hasNext())
            {
                addItem(it.next());
            }
        }
        return true;
    }

    public int hashing(int item) {
        int hash = item % CAPACITY;
        return hash;
    }

    public int hashingCollision(int item, int i) {
        int hash = ((item % 7) + 1) * i;
        return hash;
    }

    public ArrayList<T> getNodes() {
        ArrayList<T> lista = new ArrayList<>();
        for (int i = 0; i < array.length; i++)
        {
            if (array[i].getHead() != null)
            {
                lista.add(array[i].getHead().val);
            }

        }
        return lista;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < CAPACITY; i++)
        {
            sb.append(array[i].toString() + (i == CAPACITY - 1 ? "" : ", "));
        }
        return sb.toString();
    }

    private class List<T> {

        public Node<T> getHead() {
            return head;
        }

        public void removeHead() {
            head = tail = null;
        }

        public void setHead(Node<T> head) {
            this.head = head;
        }

        public Node<T> head, tail;
        public int size;

        public List() {
            head = tail = null;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void add(T val) {
            if (head == null)
            {
                head = tail = new Node<>(val);
            } else
            {
                tail.next = new Node<>(val);
                tail = tail.next;
            }
            ++size;
        }

        public void remove(T val) {
            Node<T> current = head, prev = new Node<>(val);
            prev.next = current;
            while (current != null)
            {
                if (val.equals(current.val))
                {
                    prev.next = current.next;
                    --size;
                    return;
                }
            }
            throw new NoSuchElementException();
        }

        public String toString() {
            Node<T> current = head;
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            while (current != null)
            {
                sb.append(((Estudiante) current.val).getId() + ",");
                current = current.next;
            }
            if (sb.length() > 1)
            {
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.append("]");
            return sb.toString();
        }

        public Iterator<T> iterator() {
            return new ListIterator<>();
        }

        private class ListIterator<T> implements Iterator<T> {

            Node<T> next;

            public ListIterator() {
                next = (Node<T>) head;
            }

            public boolean hasNext() {
                return next != null;
            }

            public T next() {
                if (next == null)
                {
                    throw new NoSuchElementException();
                }
                T val = next.val;
                next = next.next;
                return val;
            }
            // incorrect and needs to be corrected!

            public void remove() {
                if (next == null)
                {
                    throw new NoSuchElementException();
                }
                T val = next.val;
                next = next.next;
            }

        }

        private class Node<T> {

            public T val;
            public Node<T> next;

            public Node(T val) {
                this.val = val;
                this.next = null;
            }
        }
    }
}
