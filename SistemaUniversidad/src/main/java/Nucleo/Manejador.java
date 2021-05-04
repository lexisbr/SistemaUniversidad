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
        listaUsuarios.add(new Usuario(123, "Alejandrio", "1", Usuario.ESTUDIANTE));
        listaUsuarios.add(new Usuario(1234, "Gordo", "1", Usuario.ESTUDIANTE));
        listaUsuarios.add(new Usuario(1235, "Trolo", "1", Usuario.ESTUDIANTE));
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

}
