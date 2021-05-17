/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nucleo;

import Estructuras.ArbolAVL;
import Estructuras.ArbolB;
import Estructuras.ListaCircular;
import Estructuras.TablaHash;
import Objetos.Asignacion;
import Objetos.Catedratico;
import Objetos.Curso;
import Objetos.Edificio;
import Objetos.Estudiante;
import Objetos.Horario;
import Objetos.Salon;
import Objetos.Usuario;
import java.util.ArrayList;

/**
 *
 * @author lex
 */
public class Manejador {

    private static ListaCircular<Usuario> listaUsuarios = new ListaCircular<Usuario>();
    private static ListaCircular<Edificio> listaEdificios = new ListaCircular<Edificio>();
    private static ListaCircular<Curso> listaCursos = new ListaCircular<Curso>();
    private static TablaHash<Estudiante> tablaEstudiantes = new TablaHash<Estudiante>();
    private static ArbolAVL<Catedratico> arbolCatedraticos = new ArbolAVL<Catedratico>();
    private static ArbolB<Horario> arbolHorarios = new ArbolB<Horario>(3);
    private static ListaCircular<Asignacion> listaAsignaciones = new ListaCircular<Asignacion>();
    private static Usuario usuarioActual;

    public static void inicializarSistema() {
        listaUsuarios.add(new Usuario(12345, "Alejandro", "1", Usuario.SUPER));
        listaUsuarios.add(new Usuario(123, "Alejandro", "1", Usuario.COLABORADOR));
        addEstudiante(123, "lexis", "zona1");
        addEstudiante(1234, "lexis", "zona1");
        addEstudiante(1235, "lexis", "zona1");
        listaEdificios.add((new Edificio("edificio1")));
        addSalonEdificio("edificio1", 1, 2);
        listaCursos.add(new Curso(1,"ciencias",2,20));
        arbolCatedraticos.add(new Catedratico(1,"alfredo ","chileverde"));
        Horario horario = getDataHorario(0, "9:00", "lunes","1", "1", "edificio1", 1);
        if(horario != null){
            addHorario(horario);
        }else{
            System.out.println("Algo salio mal con el horario");
        }
    }

    public static boolean loginUser(String user, String password) {
        Usuario usuario = (Usuario) listaUsuarios.getData(user);
        if (usuario != null)
        {
            if (usuario.getPassword().equals(password))
            {
                usuarioActual = usuario;
                return true;
            } else
            {
                return false;
            }
        } else
        {
            return false;
        }
    }

    public static void logOut() {
        if (usuarioActual != null)
        {
            usuarioActual = null;
        }
    }

    public static Usuario getUsuarioActual() {
        return usuarioActual;
    }

    /**
     * **
     * METODOS PARA LISTA DE USUARIOS
     */
    public static boolean addUser(int id, String name, String password, String type, Estudiante estudiante) {
        boolean add = listaUsuarios.add(new Usuario(id, name, password, type, estudiante));
        listaUsuarios.mostrarDatos();
        return add;
    }

    public static Usuario searchUser(int id) {
        Usuario user = listaUsuarios.getData(String.valueOf(id));
        return user;
    }

    public static boolean deleteUser(int id) {
        return listaUsuarios.delete(String.valueOf(id));
    }

    public static boolean updateUser(Usuario user) {
        return listaUsuarios.update(user);
    }

    public static boolean isEmptyUser() {
        return listaUsuarios.isEmpty();
    }

    /**
     * METODOS PARA LISTA DE EDIFICIOS
     */
    public static boolean addEdificio(String name) {
        boolean add = listaEdificios.add(new Edificio(name));
        listaEdificios.mostrarDatos();
        return add;
    }

    public static Edificio searchEdificio(String name) {
        Edificio edificio = listaEdificios.getData(name);
        return edificio;
    }

    public static boolean deleteEdificio(String name) {
        return listaEdificios.delete(name);
    }

    public static ListaCircular<Edificio> getListaEdificios() {
        return listaEdificios;
    }

    /**
     * METODOS PARA LISTA DE SALONES
     */
    public static boolean addSalonEdificio(String name, int numero_salon, int capacidad) {
        Edificio edificio = listaEdificios.getData(name);
        return edificio.getListaSalones().add(new Salon(numero_salon, capacidad));
    }

    public static boolean deleteSalonEdificio(String name, String numero_salon) {
        Edificio edificio = listaEdificios.getData(name);
        return edificio.getListaSalones().delete(numero_salon);
    }

    public static Salon searchSalonEdificio(String name, String numero_salon) {
        Edificio edificio = listaEdificios.getData(name);
        return edificio.getListaSalones().getData(numero_salon);
    }

    public static boolean updateSalonEdificio(String name, Salon salon) {
        Edificio edificio = listaEdificios.getData(name);
        return edificio.getListaSalones().update(salon);
    }

    /**
     * METODOS PARA LISTA DE CURSO
     */
    public static boolean addCurso(int id, String name, int semester, int credits) {
        return listaCursos.add(new Curso(id, name, semester, credits));
    }

    public static Curso searchCurso(String id) {
        return listaCursos.getData(id);
    }

    public static boolean deleteCurso(String id) {
        return listaCursos.delete(id);
    }

    public static boolean updateCurso(Curso curso) {
        return listaCursos.update(curso);
    }
    
    public static ArrayList<Curso> getCursos(){
        return listaCursos.getNodes();
    }

    public static ListaCircular<Curso> getListaCursos() {
        return listaCursos;
    }

    /**
     * METODOS PARA TABLA HASH DE ESTUDIANTES
     */
    public static boolean addEstudiante(int carnet, String nombre, String direccion) {
        return tablaEstudiantes.add(new Estudiante(carnet, nombre, direccion));
    }

    public static Estudiante searchEstudiante(int carnet) {
        return tablaEstudiantes.searchData(carnet);
    }

    public static boolean deleteEstudiante(int carnet) {
        return tablaEstudiantes.remove(carnet);
    }

    public static void updateEstudiante(Estudiante estudiante) {
        tablaEstudiantes.update(estudiante);
    }
    
    public static ArrayList<Estudiante> getEstudiantes(){
        return tablaEstudiantes.getNodes();
    }

    public static TablaHash<Estudiante> getTablaEstudiante() {
        return tablaEstudiantes;
    }

    /**
     * *
     * METODOS PARA ARBOL DE CATEDRATICOS
     */
    public static ArbolAVL<Catedratico> getArbolCatedraticos() {
        return arbolCatedraticos;
    }

    public static boolean addCatedratico(int id, String nombre, String direccion) {
        return arbolCatedraticos.add(new Catedratico(id, nombre, direccion));
    }

    public static Catedratico searchCatedratico(int id) {
        return arbolCatedraticos.getData(id);
    }

    public static boolean deleteCatedratico(int id) {
        return arbolCatedraticos.remove(id);
    }

    public static boolean updateCatedratico(Catedratico catedratico) {
        return arbolCatedraticos.update(catedratico);
    }
    
    public static ArrayList<Catedratico> getCatedraticos(){
        return arbolCatedraticos.getNodes();
    }

    /**
     * *
     * METODOS PARA ARBOL DE HORARIOS
     */
    public static Horario getDataHorario(int id, String rango, String dia, String cursoId, String salonId, String edificioId, int catedraticoId) {
        Edificio edificio = listaEdificios.getData(edificioId);
        Salon salon = edificio.getListaSalones().getData(salonId);
        Catedratico catedratico = arbolCatedraticos.getData(catedraticoId);
        Curso curso = listaCursos.getData(cursoId);
        if (edificio != null && salon != null && catedratico != null && curso != null)
        {
            return new Horario(id, rango, dia, curso, salon, edificio, catedratico);
        } else
        {
            return null;
        }
    }
    
    public static boolean addHorario(Horario horario){
        return arbolHorarios.insert(horario);
    }

    public static ArrayList<Horario> getHorarios() {
        return arbolHorarios.getHorarios();
    }

    /**
     * METODOS PARA LISTA DE ASIGNACIONES
     */
    public static ListaCircular<Asignacion> getListaAsignaciones(){
        return listaAsignaciones;
    }
    
    public static boolean addAsignacion(int id, Horario horario, Estudiante estudiante, int zona, int examen_final) {
        return listaAsignaciones.add(new Asignacion(id, estudiante, zona, examen_final, horario));
    }

    public static int getAsignacionesSize(){
        return listaAsignaciones.getSize();
    }
    
    public static int getAsignacionesSalon(int salon, String edificio) {
        return listaAsignaciones.getAsignacionesSalon(salon, edificio);
    }
}
