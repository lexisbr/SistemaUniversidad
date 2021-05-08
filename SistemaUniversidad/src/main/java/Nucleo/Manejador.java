/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nucleo;

import Estructuras.ListaCircular;
import Objetos.Usuario;

/**
 *
 * @author lex
 */
public class Manejador {

    private static ListaCircular<Usuario> listaUsuarios = new ListaCircular<Usuario>();
    private static Usuario usuarioActual;

    public static void inicializarSistema(){
        listaUsuarios.add(new Usuario(12345, "Alejandro", "1", Usuario.SUPER));
        listaUsuarios.mostrarDatos();
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
    
    public static Usuario getUsuarioActual(){
        return usuarioActual;
    }
    
    /****
     * METODOS PARA LISTA DE USUARIOS    
     */ 
    
    public static boolean addUser(int id, String name, String password, String type){
        boolean add = listaUsuarios.add(new Usuario(id,name,password,type));
        listaUsuarios.mostrarDatos();
        return add;
    }
    
    public static Usuario searchUser(int id){
        Usuario user = listaUsuarios.getData(String.valueOf(id));
        return user;
    }
    
    public static boolean deleteUser(int id){
        return listaUsuarios.delete(String.valueOf(id));
    }
    
    public static boolean updateUser(Usuario user){
        return listaUsuarios.update(user);
    }
}
