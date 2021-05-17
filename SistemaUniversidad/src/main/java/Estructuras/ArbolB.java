/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import Objetos.Horario;
import java.util.ArrayList;

/**
 *
 * @author lex
 */
public class ArbolB<T> {

    public static void main(String[] args) {
        ArbolB<Horario> arbol = new ArbolB<Horario>(3);
        arbol.insert(new Horario(20));
        arbol.insert(new Horario(10));
        arbol.insert(new Horario(50));
        arbol.insert(new Horario(30));
        arbol.insert(new Horario(40));
        arbol.insert(new Horario(60));
        arbol.insert(new Horario(60));
        arbol.insert(new Horario(70));
        arbol.insert(new Horario(80));
        arbol.insert(new Horario(90));
        arbol.insert(new Horario(90));
        arbol.showBTree();
        ArrayList<Horario> horarioslist = arbol.getHorarios();
        System.out.println();
        for (int i = 0; i < horarioslist.size(); i++)
        {
            System.out.println("Horario: " + horarioslist.get(i).getId());
        }
    }
    private Nodo<T> root;
    private ArrayList<Horario> horarios;
    int grado;

    public ArbolB(int grado) {
        this.grado = grado;
        root = new Nodo<T>(grado);
    }

    public int findBiggerKey() {
        int maxKey = getBiggerKey(root);
        return maxKey;
    }

    private int getBiggerKey(Nodo<T> current) {
        if (current == null)
        {
            return 0;
        }

        while (!current.isLeaf())
        {
            current = current.getChild()[current.getClaves()];
        }

        return biggerKeyInNode(current);
    }

    private int biggerKeyInNode(Nodo<T> current) {
        return getKeyObject(current.getKeys()[current.claves - 1]);
    }

    public boolean insert(T data) {
        Nodo<T> aux = root;

        if (searchNode(getKey(data)) == null)
        {
            if (aux.getClaves() == ((2 * grado) - 1))
            {
                Nodo<T> newNode = new Nodo<T>(grado);
                root = newNode;
                newNode.setLeaf(false);
                newNode.setClaves(0);
                newNode.getChild()[0] = aux;
                split(newNode, 0, aux);
                nonFullInsert(newNode, data);
            } else
            {
                nonFullInsert(aux, data);
            }
            return true;
        }
        System.out.println("Clave ya existe");
        return false;
    }

    private void split(Nodo<T> x, int i, Nodo<T> y) {
        Nodo<T> z = new Nodo<T>(grado);
        z.setLeaf(y.isLeaf());
        z.setClaves((grado - 1));

        for (int j = 0; j < (grado - 1); j++)
        {
            z.getKeys()[j] = y.getKeys()[(j + grado)];
        }

        if (!y.isLeaf())
        {
            for (int k = 0; k < grado; k++)
            {
                z.getChild()[k] = y.getChild()[(k + grado)];
            }
        }

        y.setClaves((grado - 1));

        for (int j = x.getClaves(); j > i; j--)
        {
            x.getChild()[(j + 1)] = x.getChild()[j];
        }

        x.getChild()[(i + 1)] = z;

        for (int j = x.getClaves(); j > i; j--)
        {
            x.getKeys()[(j + 1)] = x.getKeys()[j];
        }

        x.getKeys()[i] = y.getKeys()[(grado - 1)];
        x.setClaves(x.getClaves() + 1);
    }

    private void nonFullInsert(Nodo<T> nodo, T data) {
        int key = getKey(data);
        if (nodo.isLeaf())
        {
            int claves = nodo.getClaves();

            while (claves >= 1 && key < getKeyObject(nodo.getKeys()[claves - 1]))
            {
                nodo.getKeys()[claves] = nodo.getKeys()[claves - 1];
                claves--;
            }

            nodo.getKeys()[claves] = data;
            nodo.setClaves(nodo.getClaves() + 1);
        } else
        {
            int cont = 0;

            while (cont < nodo.getClaves() && key > getKeyObject(nodo.getKeys()[cont]))
            {
                cont++;
            }

            if (nodo.getChild()[cont].getClaves() == (2 * grado - 1))
            {
                split(nodo, cont, nodo.getChild()[cont]);

                if (key > getKeyObject(nodo.getKeys()[cont]))
                {
                    cont++;
                }
            }

            nonFullInsert(nodo.getChild()[cont], data);
        }
    }

    public Nodo<T> searchNode(int key) {
        return search(root, key);
    }

    public Object searchByKey(int key) {
        Nodo<T> aux = search(root, key);
        return aux.find(key);
    }

    private Nodo<T> search(Nodo<T> current, int key) {
        int i = 0;

        while (i < current.getClaves() && key > getKeyObject(current.getKeys()[i]))
        {
            i++;
        }

        if (i < current.getClaves() && key == getKeyObject(current.getKeys()[i]))
        {
            return current;
        }

        if (current.isLeaf())
        {
            return null;
        } else
        {
            return search(current.getChild()[i], key);
        }
    }

    private int getKey(T data) {
        if (data instanceof Horario)
        {
            return ((Horario) data).getId();
        }
        return 0;
    }

    private int getKeyObject(Object data) {
        if (data instanceof Horario)
        {
            return ((Horario) data).getId();
        }
        return 0;
    }

    public void showBTree() {
        print(root);
    }

    private void print(Nodo<T> nodo) {
        nodo.imprimir();

        if (!nodo.isLeaf())
        {
            for (int j = 0; j <= nodo.getClaves(); j++)
            {
                if (nodo.getChild()[j] != null)
                {
                    System.out.println();
                    print(nodo.getChild()[j]);
                }
            }
        }
    }

    public ArrayList<Horario> getHorarios() {
        horarios = new ArrayList<>();
        return searchHorarios(root);
    }

    private ArrayList<Horario> searchHorarios(Nodo<T> nodo) {

        ArrayList<Horario> horarios_nodo = nodo.getHorarios();
        for (int i = 0; i < horarios_nodo.size(); i++)
        {
            horarios.add(horarios_nodo.get(i));
        }

        if (!nodo.isLeaf())
        {
            for (int j = 0; j <= nodo.getClaves(); j++)
            {
                if (nodo.getChild()[j] != null)
                {
                    searchHorarios(nodo.getChild()[j]);
                }
            }
        }

        return horarios;
    }

    private class Nodo<T> {

        private int claves;
        private boolean leaf;
        private Object keys[];
        private Nodo<T> child[];

        public Nodo(int t) {
            claves = 0;
            leaf = true;
            keys = new Object[((2 * t) - 1)];
            child = new Nodo[(2 * t)];
        }

        public void imprimir() {
            System.out.print("[");
            for (int i = 0; i < claves; i++)
            {
                if (i < claves - 1)
                {
                    System.out.print(castKey(keys[i]) + " | ");
                } else
                {
                    System.out.print(castKey(keys[i]));
                }
            }
            System.out.print("]");
        }

        public ArrayList<Horario> getHorarios() {
            ArrayList<Horario> horarios = new ArrayList<>();
            for (int i = 0; i < claves; i++)
            {
                horarios.add((Horario) keys[i]);
            }
            return horarios;
        }

        public Object find(int key) {
            for (int i = 0; i < claves; i++)
            {
                if (castKey(keys[i]) == key)
                {
                    return keys[i];
                }
            }
            return null;
        }

        public int castKey(Object key) {
            if (key instanceof Horario)
            {
                return ((Horario) key).getId();
            }
            return 0;
        }

        public int getClaves() {
            return claves;
        }

        public boolean isLeaf() {
            return leaf;
        }

        public Object[] getKeys() {
            return keys;
        }

        public Nodo<T>[] getChild() {
            return child;
        }

        public void setClaves(int claves) {
            this.claves = claves;
        }

        public void setLeaf(boolean leaf) {
            this.leaf = leaf;
        }

        public void setKeys(Object[] keys) {
            this.keys = keys;
        }

        public void setChild(Nodo<T>[] child) {
            this.child = child;
        }

    }

}
