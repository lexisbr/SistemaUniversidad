/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import Objetos.Catedratico;

/**
 *
 * @author lex
 */
public class ArbolAVL<T> {

    public static void main(String[] args) {
        ArbolAVL<Catedratico> arbol = new ArbolAVL<Catedratico>();
        arbol.add(new Catedratico(1, "hola", "gordos"));
        arbol.add(new Catedratico(2, "hola", "gordos"));
        arbol.add(new Catedratico(3, "hola", "gordos"));
        arbol.add(new Catedratico(4, "hola", "gordos"));
        arbol.add(new Catedratico(5, "hola", "gordos"));
        arbol.showTree();
        arbol.remove(2);
        arbol.remove(4);
        arbol.remove(1);
        arbol.showTree();
    }

    private Nodo<T> root;
    private boolean nodeExists = false;
    private boolean nodeDeleted = false;

    public ArbolAVL() {
        this.root = null;
    }

    public void clearAll() {
        root = null;
    }

    public int getId(Nodo<T> node) {
        T data = node.getData();
        if (data instanceof Catedratico)
        {
            return ((Catedratico) data).getId();
        }
        return 0;
    }
    
    public int getDataId(T data){
        if (data instanceof Catedratico)
        {
            return ((Catedratico) data).getId();
        }
        return 0;
    }

    public boolean remove(int id) {
        nodeDeleted=false;
        root = delete(root, id);
        return nodeDeleted;
    }

    public Nodo<T> delete(Nodo<T> actualNode, int id) {
        if (actualNode == null)
        {
            return actualNode;
        }

        if (id < getId(actualNode))
        {
            actualNode.setLeft(delete(actualNode.getLeft(), id));
        } else if (id > getId(actualNode))
        {
            actualNode.setRight(delete(actualNode.getRight(), id));
        } else
        {
            if ((actualNode.getLeft() == null) || (actualNode.getRight() == null))
            {
                Nodo<T> aux = null;
                if (actualNode.getRight() == aux)
                {
                    aux = actualNode.getLeft();
                } else
                {
                    aux = actualNode.getRight();
                }

                if (aux == null)
                {
                    actualNode = null;
                } else
                {
                    actualNode = aux;
                }
                nodeDeleted = true;
            } else
            {
                Nodo<T> aux = getBigest(actualNode.getLeft());
                actualNode.setData(aux.getData());
                actualNode.setLeft(delete(actualNode.getLeft(), getId(aux)));

            }
        }

        if (actualNode == null)
        {
            return actualNode;
        }

        actualNode.setHeight(maxHeight(getHeight(actualNode.getLeft()), getHeight(actualNode.getRight())) + 1);

        int fe = getFE(actualNode);

        if (fe > 1 && getFE(actualNode.getRight()) >= 0)
        {
            return leftRotation(actualNode);
        }

        if (fe < -1 && getFE(actualNode.getLeft()) <= 0)
        {
            return rightRotation(actualNode);
        }

        if (fe > 1 && getFE(actualNode.getRight()) < 0)
        {
            actualNode.setRight(rightRotation(actualNode.getRight()));
            return leftRotation(actualNode);
        }

        if (fe < -1 && getFE(actualNode.getLeft()) > 0)
        {
            actualNode.setLeft(leftRotation(actualNode.getLeft()));
            return rightRotation(actualNode);
        }

        return actualNode;
    }

    public boolean add(T data) {
        nodeExists = false;
        root = insert(root, new Nodo<T>(data));
        return nodeExists;
    }

    private Nodo<T> insert(Nodo<T> actualNode, Nodo<T> newNode) {
        if (actualNode == null)
        {
            return newNode;
        }

        if (getId(newNode) < getId(actualNode))
        {
            actualNode.setLeft(insert(actualNode.getLeft(), newNode));
        } else if (getId(newNode) > getId(actualNode))
        {
            actualNode.setRight(insert(actualNode.getRight(), newNode));
        } else
        {
            nodeExists = true;
            return actualNode;
        }

        actualNode.setHeight(1 + maxHeight(getHeight(actualNode.getLeft()), getHeight(actualNode.getRight())));

        int fe = getFE(actualNode);

        if (fe > 1 && getId(newNode) > getId(actualNode.getRight()))
        {
            return leftRotation(actualNode);
        }

        if (fe < -1 && getId(newNode) > getId(actualNode.getLeft()))
        {
            return rightRotation(actualNode);
        }

        if (fe >= 2 && getFE(actualNode.getRight()) < 0)
        {
            actualNode.setRight(rightRotation(actualNode.getRight()));
            return leftRotation(actualNode);
        }

        if (fe <= -2 && getFE(actualNode.getLeft()) > 0)
        {
            actualNode.setLeft(leftRotation(actualNode.getLeft()));
            return rightRotation(actualNode);
        }

        return actualNode;
    }
    
    public Nodo<T> getData(int id) {
        return search(root, id);
    }

    private Nodo<T> search(Nodo<T> actualNodo, int id) {
        if (actualNodo == null)
        {
            return null;
        } else if (id == getId(actualNodo))
        {
            return actualNodo;
        } else if (id < getId(actualNodo))
        {
            return search(actualNodo.getLeft(), id);
        } else
        {
            return search(actualNodo.getRight(), id);
        }
    }

    public boolean update(T data) {
        Nodo<T> node = getData(getDataId(data));

        if (node != null)
        {
            node.setData(data);
            return true;
        } else
        {
            return false;
        }
    }
    
    private Nodo<T> rightRotation(Nodo<T> actualNode) {
        Nodo<T> newRoot = actualNode.getLeft();
        Nodo<T> aux = newRoot.getRight();

        newRoot.setRight(actualNode);
        actualNode.setLeft(aux);

        actualNode.setHeight(maxHeight(getHeight(actualNode.getLeft()), getHeight(actualNode.getRight())) + 1);
        newRoot.setHeight(maxHeight(getHeight(newRoot.getLeft()), getHeight(newRoot.getRight())) + 1);

        return newRoot;
    }

    private Nodo<T> leftRotation(Nodo<T> actualNode) {
        Nodo<T> newRoot = actualNode.getRight();
        Nodo<T> aux = newRoot.getLeft();

        newRoot.setLeft(actualNode);
        actualNode.setRight(aux);

        actualNode.setHeight(maxHeight(getHeight(actualNode.getLeft()), getHeight(actualNode.getRight())) + 1);
        newRoot.setHeight(maxHeight(getHeight(newRoot.getLeft()), getHeight(newRoot.getRight())) + 1);

        return newRoot;
    }

    private int maxHeight(int a, int b) {
        return (a > b) ? a : b;
    }

    private int getHeight(Nodo<T> actualNode) {
        if (actualNode == null)
        {
            return 0;
        }

        return actualNode.getHeight();
    }

    private int getFE(Nodo<T> actualNode) {
        if (actualNode == null)
        {
            return 0;
        }

        return getHeight(actualNode.getRight()) - getHeight(actualNode.getLeft());
    }
    
    private Nodo<T> getBigest(Nodo<T> nodo) {
        Nodo<T> actualNodo = nodo;

        while (actualNodo.getRight() != null)
        {
            actualNodo = actualNodo.getRight();
        }

        return actualNodo;
    }

    public void showTree() {
        System.out.println("Arbol AVL");
        showData(root);
    }

    private void showData(Nodo<T> node) {

        if (root != null)
        {

            if (node.getLeft() == null && node.getRight() == null)
            {
                System.out.println("Nodo: " + getId(node));
            }
            if (node.getRight() != null)
            {
                System.out.println("Nodo: " + getId(node) + " Hijo derecho: " + getId(node.getRight()));
                showData(node.getRight());
            }
            if (node.getLeft() != null)
            {
                System.out.println("Nodo: " + getId(node) + " Hijo Izquierdo: " + getId(node.getLeft()));
                showData(node.getLeft());
            }
        } else
        {
            System.out.println("ARBOL VACIO");
        }

    }

    private class Nodo<T> {

        private T data;
        private int height;
        private Nodo<T> left;
        private Nodo<T> right;

        public Nodo(T data) {
            this.data = data;
            this.height = 1;
        }

        public Nodo(T data, Nodo<T> left, Nodo<T> right) {
            this.data = data;
            this.height = 1;
            this.left = left;
            this.right = right;
        }

        public T getData() {
            return data;
        }

        public int getHeight() {
            return height;
        }

        public Nodo<T> getLeft() {
            return left;
        }

        public Nodo<T> getRight() {
            return right;
        }

        public void setData(T data) {
            this.data = data;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public void setLeft(Nodo<T> left) {
            this.left = left;
        }

        public void setRight(Nodo<T> right) {
            this.right = right;
        }

    }
}
