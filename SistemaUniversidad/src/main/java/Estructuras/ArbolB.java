/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import Nucleo.Manejador;
import Objetos.Asignacion;
import Objetos.Catedratico;
import Objetos.Curso;
import Objetos.Edificio;
import Objetos.Horario;
import Objetos.Salon;
import java.util.ArrayList;

/**
 *
 * @author lex
 */
public class ArbolB<T> {

    private Nodo<T> root;
    private ArrayList<Horario> horarios;
    private int grado;
    private StringBuffer grafica;
    private int contadorNodos;

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

    public String getGraficaForAsignaciones() {
        grafica = new StringBuffer();
        grafica.append("subgraph cluster_1{\n"
                + "style=filled;\n"
                + "color=lightgrey;\n"
                + "compound=true;\n"
                + "node[shape=record,height=.1,color=white];\n"
                + "edge [arrowhead=normal,arrowtail=dot,color=black];\n");
        contadorNodos = 0;
        generarGrafica(root);
        grafica.append("label=\"Arbol de Horarios\";\n");
        grafica.append("}\n");
        getGraficaCurso();
        getGraficaCatedratico();
        getGraficaEdificios();
        conectarHorarioCurso(root);
        conectarHorarioCatedratico(root);
        conectarHorarioSalon(root);
        return grafica.toString();
    }
    
    public String getConexionHorarioAsignaciones(ArrayList<Asignacion> asignaciones){
        String salida = "";
        for(Asignacion asignacion: asignaciones){
            salida += "\"" + asignacion.getId() + "\n" + asignacion.getEstudiante().getId() + "\n" + asignacion.getHorario().getId() + "\n" + asignacion.getZona() + "\n" + asignacion.getFinal_test() + "\"" + "->";
            Nodo<T> nodo = searchNode(asignacion.getHorario().getId());
            salida +="\"nodo" + nodo.getFirst() + "\"" + ":f" + nodo.findPosition(asignacion.getHorario().getId())+"[lhead = cluster_1,arrowhead=normal,arrowtail=dot,color=chartreuse4]; \n";
        }
        return salida;
    }

    public void getGrafica() {
        grafica = new StringBuffer();
        grafica.append("digraph G{\n"
                + "subgraph cluster_1{\n"
                + "style=filled;\n"
                + "color=lightgrey;\n"
                + "compound=true;\n"
                + "node[shape=record,height=.1,color=white];\n"
                + "edge [arrowhead=normal,arrowtail=dot,color=black];\n");
        contadorNodos = 0;
        generarGrafica(root);
        grafica.append("label=\"Arbol de Horarios\";\n");
        grafica.append("}\n");
        getGraficaCurso();
        getGraficaCatedratico();
        getGraficaEdificios();
        conectarHorarioCurso(root);
        conectarHorarioCatedratico(root);
        conectarHorarioSalon(root);
        grafica.append("}\n");
        Manejador.generarGrafo(grafica, "arbolHorarios");
    }

    private void generarGrafica(Nodo<T> nodo) {
        contadorNodos++;
        grafica.append("nodo" + nodo.getFirst() + " [label = \" ");
        ArrayList<Horario> horarios_nodo = nodo.getHorarios();
        for (int i = 0; i < horarios_nodo.size(); i++)
        {
            Horario horario = horarios_nodo.get(i);
            grafica.append("<f" + i + ">");
            grafica.append("|");
            grafica.append(horario.getId());
            grafica.append("|");
            if (i + 1 == horarios_nodo.size())
            {
                grafica.append("<f" + (i + 1) + ">");
            }
        }
        grafica.append("\"];\n");

        if (!nodo.isLeaf())
        {
            for (int j = 0; j <= nodo.getClaves(); j++)
            {
                if (nodo.getChild()[j] != null)
                {
                    grafica.append("\"nodo" + nodo.getFirst() + "\"" + ":f" + j + "->" + "\"nodo" + nodo.getChild()[j].getFirst() + "\":f" + j + " ; \n");
                    generarGrafica(nodo.getChild()[j]);
                }
            }
        }

    }

    private void conectarHorarioCurso(Nodo<T> nodo) {
        ArrayList<Horario> horarios = nodo.getHorarios();
        for (int i = 0; i < horarios.size(); i++)
        {
            Horario horario = horarios.get(i);
            Curso curso = horario.getCurso();
            grafica.append("\"nodo" + nodo.getFirst() + "\"" + ":f" + i + "->" + "\"" + curso.getId() + "\n" + curso.getName() + "\n" + curso.getSemester() + "\n" + curso.getSemester() + "\"  [lhead = cluster_2,arrowhead=normal,arrowtail=dot,color=darkorchid2]; \n");
        }

        if (!nodo.isLeaf())
        {
            for (int j = 0; j <= nodo.getClaves(); j++)
            {
                if (nodo.getChild()[j] != null)
                {
                    conectarHorarioCurso(nodo.getChild()[j]);
                }
            }
        }
    }

    private void conectarHorarioCatedratico(Nodo<T> nodo) {
        ArrayList<Horario> horarios = nodo.getHorarios();
        for (int i = 0; i < horarios.size(); i++)
        {
            Horario horario = horarios.get(i);
            Catedratico catedratico = horario.getCatedratico();
            grafica.append("\"nodo" + nodo.getFirst() + "\"" + ":f" + i + "->" + "\" " + catedratico.getId() + "\n" + catedratico.getName() + "\"  [lhead = cluster_3,arrowhead=normal,arrowtail=dot,color=blue]; \n");
        }

        if (!nodo.isLeaf())
        {
            for (int j = 0; j <= nodo.getClaves(); j++)
            {
                if (nodo.getChild()[j] != null)
                {
                    conectarHorarioCatedratico(nodo.getChild()[j]);
                }
            }
        }
    }

    private void conectarHorarioSalon(Nodo<T> nodo) {
        ArrayList<Horario> horarios = nodo.getHorarios();
        for (int i = 0; i < horarios.size(); i++)
        {
            Horario horario = horarios.get(i);
            Salon salon = horario.getSalon();
            Edificio edificio = horario.getEdificio();
            grafica.append("\"nodo" + nodo.getFirst() + "\"" + ":f" + i + "->" + "\"" + salon.getId() + "_" + edificio.getName() + "\n" + salon.getSize() + " Estudiantes\" [arrowhead=normal,arrowtail=dot,color=deeppink]; \n");
        }

        if (!nodo.isLeaf())
        {
            for (int j = 0; j <= nodo.getClaves(); j++)
            {
                if (nodo.getChild()[j] != null)
                {
                    conectarHorarioSalon(nodo.getChild()[j]);
                }
            }
        }
    }

    private void getGraficaCurso() {
        String codigo = Manejador.getGraficaCursosHorario();
        grafica.append("subgraph cluster_2{\n"
                + "style=filled;\n"
                + "color=lightgrey;\n"
                + "node[shape=rect,height=.1,color=black];\n");
        grafica.append(codigo);
        grafica.append("label=\"Lista de Cursos\";\n"
                + "}\n");
    }

    private void getGraficaCatedratico() {
        String codigo = Manejador.getGraficaCatedraticosHorario();
        grafica.append("subgraph cluster_3{\n"
                + "style=filled;\n"
                + "color=lightgrey;\n"
                + "node[shape=rect,height=.1,color=black];\n");
        grafica.append(codigo);
        grafica.append("label=\"Arbol de Catedraticos\";\n"
                + "}\n");
    }

    private void getGraficaEdificios() {
        String codigo = Manejador.getGraficaEdificiosHorario();
        grafica.append("subgraph cluster_4{\n"
                + "style=filled;\n"
                + "color=lightgrey;\n"
                + "compound=true;\n"
                + "node[shape=rect,height=.1,color=black];\n"
                + "");
        grafica.append(codigo);
        grafica.append("label=\"Lista de Edificios\";\n"
                + "}\n");
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

        public int getFirst() {
            return ((Horario) keys[0]).getId();
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
        
        public int findPosition(int key){
            for (int i = 0; i < claves; i++)
            {
                if (castKey(keys[i]) == key)
                {
                    return i;
                }
            }
            return 0;
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
