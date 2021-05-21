/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import Nucleo.Manejador;
import Objetos.Asignacion;
import Objetos.Curso;
import Objetos.Edificio;
import Objetos.Estudiante;
import Objetos.Horario;
import Objetos.Salon;
import Objetos.Usuario;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author lex
 */
public class ListaCircular<T> {

    private Nodo<T> root;
    private Nodo<T> end;
    private int size;

    public ListaCircular() {
        root = null;
        end = null;
    }

    public boolean add(T data) {
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
            return false;
        }
        size++;
        return true;
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
        } else if (data instanceof Curso)
        {
            Curso curso = (Curso) data;
            return String.valueOf(curso.getId());
        } else if (data instanceof Asignacion)
        {
            Asignacion asignacion = (Asignacion) data;
            return String.valueOf(asignacion.getId());
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
            Nodo<T> nodo = getNodo(id);
            if (nodo != null)
            {
                String id_root = getId(root.getData());
                String id_end = getId(end.getData());
                if (root == end)
                {
                    root = end = null;
                    size--;
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
                size--;
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

    public ArrayList<T> getNodes() {
        ArrayList<T> lista = new ArrayList<>();
        if (root != null)
        {
            Nodo<T> aux = root;
            do
            {
                lista.add(aux.getData());
                aux = aux.getNext();
            } while (aux != root);
        }
        return lista;
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

                } else if (aux.getData() instanceof Edificio)
                {
                    Edificio edificio = (Edificio) aux.getData();
                    System.out.println("************ EDIFICIO ************");
                    System.out.println("Nombre: " + edificio.getName());
                    System.out.println("\tSalones:");
                    edificio.getListaSalones().showData();
                } else if (aux.getData() instanceof Curso)
                {
                    Curso curso = (Curso) aux.getData();
                    System.out.println("************ CURSO ************");
                    System.out.println("Codigo: " + curso.getId());
                    System.out.println("Nombre: " + curso.getName());
                    System.out.println("Semestre: " + curso.getSemester());
                    System.out.println("Creditos: " + curso.getCredits());
                } else if (aux.getData() instanceof Asignacion)
                {
                    Asignacion asignacion = (Asignacion) aux.getData();
                    System.out.println("************ ASIGNACION ************");
                    System.out.println("Codigo: " + asignacion.getId());
                    System.out.println("Horario: " + asignacion.getHorario().getPeriod());
                    System.out.println("Estudiante: " + asignacion.getEstudiante().getId());
                    System.out.println("Zona: " + asignacion.getZona());
                    System.out.println("Final: " + asignacion.getFinal_test());
                }

                if (aux.getNext() != null)
                {
                    System.out.println("Siguiente: " + getId(aux.getNext().getData()));
                }
                if (aux.getPrev() != null)
                {
                    System.out.println("Anterior: " + getId(aux.getPrev().getData()));
                }
                aux = aux.getNext();
            } while (aux != root);
        } else
        {
            System.out.println("******* LISTA VACIA *******");
        }
    }

    public boolean isEmpty() {
        if (root != null && end != null)
        {
            return false;
        } else
        {
            return true;
        }
    }

    public int getAsignacionesSalon(int salon, String edificio) {
        int contador = 0;
        if (root != null)
        {
            Nodo<T> aux = root;
            do
            {
                if (aux.getData() instanceof Asignacion)
                {
                    Asignacion asignacion = (Asignacion) aux.getData();
                    Horario horario = asignacion.getHorario();

                    if (horario.getEdificio().getName().equals(edificio) && horario.getSalon().getId() == salon)
                    {
                        contador++;
                    }
                    aux = aux.getNext();
                }
            } while (aux != root);
        }
        return contador;
    }

    public ArrayList<Asignacion> getAsignacionesEstudiante(int carnet) {
        ArrayList<Asignacion> asignaciones = new ArrayList<>();
        if (root != null)
        {
            Nodo<T> aux = root;

            do
            {
                if (aux.getData() instanceof Asignacion)
                {
                    Asignacion asignacion = (Asignacion) aux.getData();
                    if (asignacion.getEstudiante().getId() == carnet)
                    {
                        asignaciones.add(asignacion);
                    }
                }
                aux = aux.getNext();
            } while (aux != root);
        }
        return asignaciones;
    }

    public ArrayList<Estudiante> getAsignacionesCurso(int codigo) {
        ArrayList<Estudiante> estudiantes = new ArrayList<>();
        if (root != null)
        {
            Nodo<T> aux = root;

            do
            {
                if (aux.getData() instanceof Asignacion)
                {
                    Asignacion asignacion = (Asignacion) aux.getData();
                    if (asignacion.getHorario().getCurso().getId() == codigo)
                    {
                        estudiantes.add(asignacion.getEstudiante());
                    }
                }
                aux = aux.getNext();
            } while (aux != root);
        }
        return estudiantes;
    }

    public ArrayList<String[]> getConteoSemestre(int semestre) {
        ArrayList<String[]> conteoCursos = new ArrayList<>();
        if (root != null)
        {
            Nodo<T> aux = root;

            do
            {
                if (aux.getData() instanceof Asignacion)
                {
                    Asignacion asignacion = (Asignacion) aux.getData();
                    if (asignacion.getHorario().getCurso().getSemester() == semestre)
                    {
                        Curso curso = asignacion.getHorario().getCurso();
                        if (!yaExisteCurso(conteoCursos, curso))
                        {
                            String aprobados = String.valueOf(getAprobados(curso.getId()));
                            String reprobados = String.valueOf(getReprobados(curso.getId()));
                            String[] cursoCadena =
                            {
                                curso.getName(), aprobados, reprobados
                            };
                            conteoCursos.add(cursoCadena);
                        }
                    }
                }
                aux = aux.getNext();
            } while (aux != root);
        }
        return conteoCursos;
    }

    private boolean yaExisteCurso(ArrayList<String[]> cursos, Curso curso) {
        for (String[] cursoLista : cursos)
        {
            if (cursoLista[0].equals(String.valueOf(curso.getName())))
            {
                return true;
            }
        }
        return false;
    }

    public int getSize() {
        return size;
    }

    public int getAprobados(int curso) {
        int conteo = 0;
        if (root != null)
        {
            Nodo<T> aux = root;

            do
            {
                if (aux.getData() instanceof Asignacion)
                {
                    Asignacion asignacion = (Asignacion) aux.getData();
                    if (asignacion.getHorario().getCurso().getId() == curso)
                    {
                        if (asignacion.getZona() + asignacion.getFinal_test() >= 61)
                        {
                            conteo++;
                        }
                    }
                }
                aux = aux.getNext();
            } while (aux != root);
        }
        return conteo;
    }

    public int getReprobados(int curso) {
        int conteo = 0;
        if (root != null)
        {
            Nodo<T> aux = root;

            do
            {
                if (aux.getData() instanceof Asignacion)
                {
                    Asignacion asignacion = (Asignacion) aux.getData();
                    if (asignacion.getHorario().getCurso().getId() == curso)
                    {
                        if (asignacion.getZona() + asignacion.getFinal_test() < 61)
                        {
                            conteo++;
                        }
                    }
                }
                aux = aux.getNext();
            } while (aux != root);
        }
        return conteo;
    }

    public void graficarListaCircular() throws IOException {
        String salida = "digraph G{\n";
        salida += "graph [compound=true];\n";
        salida += "style=filled;\n";
        int contadorSalones = 0;
        if (root != null)
        {
            Nodo<T> aux = root;
            if (aux.getData() instanceof Usuario)
            {
                salida += "label = \" Lista de Usuarios \";\n";
            } else if (aux.getData() instanceof Curso)
            {
                salida += "label = \" Lista de Cursos \";\n";
            } else if (aux.getData() instanceof Edificio)
            {
                salida += "label = \" Lista de Edificios y salones \";\n";
            }
            salida += "subgraph Lista { node [shape = rect,height=.1]; label=\"Lista doble circular\"; \n";
            do
            {
                if (aux.getData() instanceof Usuario)
                {

                    Usuario user = (Usuario) aux.getData();
                    Usuario userSiguiente = (Usuario) aux.getNext().getData();
                    Usuario userAnterior = (Usuario) aux.getNext().getPrev().getData();
                    salida += "\"" + user.getId() + "\n" + user.getName() + "\n" + user.getType() + "\n" + user.getPassword() + "\"" + "->"
                            + "\"" + userSiguiente.getId() + "\n" + userSiguiente.getName() + "\n" + userSiguiente.getType() + "\n" + userSiguiente.getPassword() + "\""
                            + " [constraint=false]; \n";
                    salida += "\"" + userSiguiente.getId() + "\n" + userSiguiente.getName() + "\n" + userSiguiente.getType() + "\n" + userSiguiente.getPassword() + "\"" + "->"
                            + "\"" + userAnterior.getId() + "\n" + userAnterior.getName() + "\n" + userAnterior.getType() + "\n" + userAnterior.getPassword() + "\""
                            + " [constraint=false]; \n";
                } else if (aux.getData() instanceof Curso)
                {
                    Curso curso = (Curso) aux.getData();
                    Curso cursoSiguiente = (Curso) aux.getNext().getData();
                    Curso cursoAnterior = (Curso) aux.getNext().getPrev().getData();
                    salida += "\"" + curso.getId() + "\n" + curso.getName() + "\n" + curso.getSemester() + "\n" + curso.getSemester() + "\"" + "->"
                            + "\"" + cursoSiguiente.getId() + "\n" + cursoSiguiente.getName() + "\n" + cursoSiguiente.getSemester() + "\n" + cursoSiguiente.getSemester() + "\"" + " [constraint=false]; \n";
                    salida += "\"" + cursoSiguiente.getId() + "\n" + cursoSiguiente.getName() + "\n" + cursoSiguiente.getSemester() + "\n" + cursoSiguiente.getSemester() + "\"" + "->"
                            + "\"" + cursoAnterior.getId() + "\n" + cursoAnterior.getName() + "\n" + cursoAnterior.getSemester() + "\n" + cursoAnterior.getSemester() + "\"" + " [constraint=false]; \n";
                } else if (aux.getData() instanceof Edificio)
                {
                    Edificio edificio = (Edificio) aux.getData();
                    Edificio edificioSiguiente = (Edificio) aux.getNext().getData();
                    Edificio edificioAnterior = (Edificio) aux.getNext().getPrev().getData();
                    salida += edificio.getName() + "->" + edificioSiguiente.getName() + " [constraint=false]; \n";
                    salida += edificioSiguiente.getName() + "->" + edificioAnterior.getName() + " [constraint=false]; \n";
                    ListaSimple<Salon> salones = edificio.getListaSalones();
                    salida += "subgraph cluster_" + contadorSalones + "{node [shape = rect,height=.1]; rankdir=LR; label=\"Salones_" + contadorSalones + "\";  \n";
                    salida += salones.graficarSalones(edificio.getName());
                    salida += " } \n";
                    if (salones.getRoot() != null)
                    {
                        if (salones.getRootData() instanceof Salon)
                        {
                            Salon salon = (Salon) salones.getRootData();
                            salida += edificio.getName() + "->\"" + salon.getId() + "_" + edificio.getName() + "\n" + salon.getSize() + " Estudiantes\" [lhead = cluster_" + contadorSalones + "]; \n";
                        }
                    }
                    contadorSalones++;
                }
                aux = aux.getNext();
            } while (aux != root);
            salida += "}";
            salida += "}";
            StringBuffer codigo = new StringBuffer(salida);
            if (aux.getData() instanceof Usuario)
            {
                Manejador.generarGrafo(codigo, "listaUsuarios");
            } else if (aux.getData() instanceof Curso)
            {
                Manejador.generarGrafo(codigo, "listaCursos");
            } else if (aux.getData() instanceof Edificio)
            {
                Manejador.generarGrafo(codigo, "listaEdificios");
            }
        }

    }

    public String getGraficaCursos() {
        String salida = "";
        if (root != null)
        {
            Nodo<T> aux = root;
            do
            {
                Curso curso = (Curso) aux.getData();
                Curso cursoSiguiente = (Curso) aux.getNext().getData();
                Curso cursoAnterior = (Curso) aux.getNext().getPrev().getData();
                salida += "\"" + curso.getId() + "\n" + curso.getName() + "\n" + curso.getSemester() + "\n" + curso.getSemester() + "\"" + "->"
                        + "\"" + cursoSiguiente.getId() + "\n" + cursoSiguiente.getName() + "\n" + cursoSiguiente.getSemester() + "\n" + cursoSiguiente.getSemester() + "\"" + " [constraint=false]; \n";
                salida += "\"" + cursoSiguiente.getId() + "\n" + cursoSiguiente.getName() + "\n" + cursoSiguiente.getSemester() + "\n" + cursoSiguiente.getSemester() + "\"" + "->"
                        + "\"" + cursoAnterior.getId() + "\n" + cursoAnterior.getName() + "\n" + cursoAnterior.getSemester() + "\n" + cursoAnterior.getSemester() + "\"" + " [constraint=false]; \n";
                aux = aux.getNext();
            } while (aux != root);
        }
        return salida;
    }

    public String getGraficaEdificios() throws IOException {
        String salida = "";
        int contadorSalones = 5;
        if (root != null)
        {
            Nodo<T> aux = root;
            do
            {
                Edificio edificio = (Edificio) aux.getData();
                Edificio edificioSiguiente = (Edificio) aux.getNext().getData();
                Edificio edificioAnterior = (Edificio) aux.getNext().getPrev().getData();
                salida += edificio.getName() + "->" + edificioSiguiente.getName() + " [constraint=false]; \n";
                salida += edificioSiguiente.getName() + "->" + edificioAnterior.getName() + " [constraint=false]; \n";
                ListaSimple<Salon> salones = edificio.getListaSalones();
                salida += "subgraph cluster_" + contadorSalones + "{node [shape = rect,height=.1]; rankdir=LR; label=\"Salones_" + contadorSalones + "\";  \n";
                salida += salones.graficarSalones(edificio.getName());
                salida += " } \n";
                if (salones.getRoot() != null)
                {
                    if (salones.getRootData() instanceof Salon)
                    {
                        Salon salon = (Salon) salones.getRootData();
                        salida += edificio.getName() + "->\"" + salon.getId() + "_" + edificio.getName() + "\n" + salon.getSize() + " Estudiantes\" [lhead = cluster_" + contadorSalones + "]; \n";
                    }
                }
                contadorSalones++;
                aux = aux.getNext();
            } while (aux != root);
        }
        return salida;
    }

    public void graficarListaAsignaciones() {
        String salida = "digraph G{\n";
        salida += "style=filled;\n";
        salida += "subgraph cluster_0{\n"
                + "style=filled;\n"
                + "color=lightgrey;\n"
                + "compound=true;\n"
                + "node[shape=rect,height=.1,color=white];\n"
                + "edge [arrowhead=normal,arrowtail=dot,color=black];\n";
        salida += getGraficaAsignaciones();
        salida += "label=\"Lista de Asignaciones\";\n";
        salida += "}";
        salida += Manejador.getGraficaHorariosAsignaciones();
        salida += Manejador.getConexionHorariosAsignaciones(getListaAsignaciones());
        salida += Manejador.getSupgraphEstudiantes();
        salida += getConexionAsignacionEstudiante();
        salida += "}";
        StringBuffer codigo = new StringBuffer(salida);
        Manejador.generarGrafo(codigo, "listaAsignaciones");
    }

    private ArrayList<Asignacion> getListaAsignaciones() {
        ArrayList<Asignacion> asignaciones = new ArrayList<>();
        if (root != null)
        {
            Nodo<T> aux = root;
            do
            {
                Asignacion asignacion = (Asignacion) aux.getData();
                asignaciones.add(asignacion);
                aux = aux.getNext();
            } while (aux != root);
        }
        return asignaciones;
    }

    private String getGraficaAsignaciones() {
        String salida = "";
        if (root != null)
        {
            Nodo<T> aux = root;
            do
            {
                Asignacion asignacion = (Asignacion) aux.getData();
                Asignacion asignacionSiguiente = (Asignacion) aux.getNext().getData();
                Asignacion asignacionAnterior = (Asignacion) aux.getNext().getPrev().getData();
                salida += "\"" + asignacion.getId() + "\n" + asignacion.getEstudiante().getId() + "\n" + asignacion.getHorario().getId() + "\n" + asignacion.getZona() + "\n" + asignacion.getFinal_test() + "\"" + "->"
                        + "\"" + asignacionSiguiente.getId() + "\n" + asignacionSiguiente.getEstudiante().getId() + "\n" + asignacionSiguiente.getHorario().getId() + "\n" + asignacionSiguiente.getZona() + "\n" + asignacionSiguiente.getFinal_test() + "\"" + " [constraint=false]; \n";
                salida += "\"" + asignacionSiguiente.getId() + "\n" + asignacionSiguiente.getEstudiante().getId() + "\n" + asignacionSiguiente.getHorario().getId() + "\n" + asignacionSiguiente.getZona() + "\n" + asignacionSiguiente.getFinal_test() + "\"" + "->"
                        + "\"" + asignacionAnterior.getId() + "\n" + asignacionAnterior.getEstudiante().getId() + "\n" + asignacionAnterior.getHorario().getId() + "\n" + asignacionAnterior.getZona() + "\n" + asignacionAnterior.getFinal_test() + "\"" + " [constraint=false]; \n";
                aux = aux.getNext();
            } while (aux != root);
        }
        return salida;
    }

    private String getConexionAsignacionEstudiante() {
        String salida = "";
        if (root != null)
        {
            Nodo<T> aux = root;
            do
            {
                Asignacion asignacion = (Asignacion) aux.getData();
                salida += "\"" + asignacion.getId() + "\n" + asignacion.getEstudiante().getId() + "\n" + asignacion.getHorario().getId() + "\n" + asignacion.getZona() + "\n" + asignacion.getFinal_test() + "\"" + "->";
                salida += "\" [" + asignacion.getEstudiante().getId() + "]\"" + "[constraint=false,lhead = cluster_Estudiante,arrowhead=normal,arrowtail=dot,color=cadetblue4]; \n";
                aux = aux.getNext();
            } while (aux != root);
        }
        return salida;
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
