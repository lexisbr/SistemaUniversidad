/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nucleo;

import Estructuras.ListaCircular;
import Objetos.Curso;
import Objetos.Edificio;
import Objetos.Salon;
import Objetos.Usuario;

/**
 *
 * @author lex
 */
public class Manejador {

    private static ListaCircular<Usuario> listaUsuarios = new ListaCircular<Usuario>();
    private static ListaCircular<Edificio> listaEdificios = new ListaCircular<Edificio>();
    private static ListaCircular<Curso> listaCursos = new ListaCircular<Curso>();
    private static Usuario usuarioActual;

    public static void inicializarSistema() {
        listaUsuarios.add(new Usuario(12345, "Alejandro", "1", Usuario.SUPER));
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

    public static Usuario getUsuarioActual() {
        return usuarioActual;
    }

    /**
     * **
     * METODOS PARA LISTA DE USUARIOS
     */
    public static boolean addUser(int id, String name, String password, String type) {
        boolean add = listaUsuarios.add(new Usuario(id, name, password, type));
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
    
    public static boolean deleteSalonEdificio(String name, String numero_salon){
        Edificio edificio = listaEdificios.getData(name);
        return edificio.getListaSalones().delete(numero_salon);
    }
    
    public static Salon searchSalonEdificio(String name, String numero_salon){
        Edificio edificio = listaEdificios.getData(name);
        return edificio.getListaSalones().getData(numero_salon);
    }
    
    public static boolean updateSalonEdificio(String name, Salon salon){
        Edificio edificio = listaEdificios.getData(name);
        return edificio.getListaSalones().update(salon);
    }
    
    /**
     * METODOS PARA LISTA DE CURSO
     */
    
    public static boolean addCurso(int id, String name,int semester,int credits){
        return listaCursos.add(new Curso(id, name, semester, credits));
    }
    
    public static Curso searchCurso(String id){
        return listaCursos.getData(id);
    }
    
    public static boolean deleteCurso(String id){
        return listaCursos.delete(id);
    }
    
    public static ListaCircular<Curso> getListaCursos(){
        return listaCursos;
    }
}
