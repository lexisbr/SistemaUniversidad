/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nucleo;

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
public class CargaDatos {

    private ArrayList<Estudiante> listaEstudiantes;
    private ArrayList<Usuario> listaUsuarios;
    private ArrayList<Catedratico> listaCatedraticos;
    private ArrayList<Edificio> listaEdificios;
    private ArrayList<Salon> listaSalones;
    private ArrayList<Curso> listaCursos;
    private ArrayList<Horario> listaHorarios;
    private ArrayList<Asignacion> listaAsignaciones;

    public CargaDatos(ArrayList<Estudiante> listaEstudiantes, ArrayList<Usuario> listaUsuarios, ArrayList<Catedratico> listaCatedraticos, ArrayList<Edificio> listaEdificios, ArrayList<Salon> listaSalones, ArrayList<Curso> listaCursos, ArrayList<Horario> listaHorarios, ArrayList<Asignacion> listaAsignaciones) {
        this.listaEstudiantes = listaEstudiantes;
        this.listaUsuarios = listaUsuarios;
        this.listaCatedraticos = listaCatedraticos;
        this.listaEdificios = listaEdificios;
        this.listaSalones = listaSalones;
        this.listaCursos = listaCursos;
        this.listaHorarios = listaHorarios;
        this.listaAsignaciones = listaAsignaciones;
    }

    public ArrayList<Estudiante> getListaEstudiantes() {
        return listaEstudiantes;
    }

    public ArrayList<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public ArrayList<Catedratico> getListaCatedraticos() {
        return listaCatedraticos;
    }

    public ArrayList<Edificio> getListaEdificios() {
        return listaEdificios;
    }

    public ArrayList<Salon> getListaSalones() {
        return listaSalones;
    }

    public ArrayList<Curso> getListaCursos() {
        return listaCursos;
    }

    public ArrayList<Horario> getListaHorarios() {
        return listaHorarios;
    }

    public ArrayList<Asignacion> getListaAsignaciones() {
        return listaAsignaciones;
    }

    public void mostrarDatos() {
        for (int i = 0; i < getListaEstudiantes().size(); i++)
        {
            System.out.println("****************ESTUDIANTE****************");
            Usuario obj = getListaUsuarios().get(i);
            System.out.println("Carnet " + obj.getId());
            System.out.println("Nombre " + obj.getName());
            System.out.println("Password " + obj.getPassword());
            System.out.println("Tipo " + obj.getType());
        }
        for (int i = 0; i < getListaUsuarios().size(); i++)
        {
            Usuario obj = getListaUsuarios().get(i);
            System.out.println("****************USUARIO****************");
            System.out.println("Carnet " + obj.getId());
            System.out.println("Nombre " + obj.getName());
            System.out.println("Password " + obj.getPassword());
            System.out.println("Tipo " + obj.getType());
        }
        for (int i = 0; i < getListaCatedraticos().size(); i++)
        {
            System.out.println("****************CATEDRATICO****************");
            Catedratico obj = getListaCatedraticos().get(i);
            System.out.println("Carnet " + obj.getId());
            System.out.println("Nombre " + obj.getName());
            System.out.println("Direccion " + obj.getAddress());
        }

        for (int i = 0; i < getListaEdificios().size(); i++)
        {
            System.out.println("**************EDIFICIO******************");
            Edificio obj = getListaEdificios().get(i);
            System.out.println("Nombre " + obj.getName());
        }

        for (int i = 0; i < getListaSalones().size(); i++)
        {
            System.out.println("***************SALON*****************");
            Salon obj = getListaSalones().get(i);
            System.out.println("Numero " + obj.getId());
            System.out.println("Capacidad " + obj.getSize());
            System.out.println("Edificio " + obj.getEdificio());
        }

        for (int i = 0; i < getListaCursos().size(); i++)
        {
            System.out.println("***************CURSOS*****************");
            Curso obj = getListaCursos().get(i);
            System.out.println("Numero " + obj.getId());
            System.out.println("Nombre " + obj.getName());
            System.out.println("Semestre " + obj.getSemester());
            System.out.println("Creditos " + obj.getCredits());
        }

        for (int i = 0; i < getListaHorarios().size(); i++)
        {
            System.out.println("***************HORARIOS*****************");
            Horario obj = getListaHorarios().get(i);
            System.out.println("Numero " + obj.getId());
            System.out.println("Periodo " + obj.getPeriod());
            System.out.println("Dia " + obj.getDay());
            System.out.println("Curso " + obj.getCursoId());
            System.out.println("Salon " + obj.getSalonId());
            System.out.println("Edificio " + obj.getEdificioId());
            System.out.println("Catedratico " + obj.getCatedraticoId());
        }

        for (int i = 0; i < getListaAsignaciones().size(); i++)
        {
            System.out.println("***************ASIGNACIONES*****************");
            Asignacion obj = getListaAsignaciones().get(i);
            System.out.println("Carnet " + obj.getEstudianteId());
            System.out.println("Horario " + obj.getHorarioId());
            System.out.println("Zona " + obj.getZona());
            System.out.println("Final " + obj.getFinal_test());
        }
    }

    public void cargarDatos() {
        cargarEstudiantes();
        cargarUsuarios();
        cargarCatedraticos();
        cargarEdificios();
        cargarSalones();
        cargarCursos();
        cargarHorarios();
        cargarAsignaciones();
        Manejador.getListaEdificios().mostrarDatos();
        Manejador.getListaCursos().mostrarDatos();
        Manejador.getArbolHorario().showBTree();
        System.out.println();
        Manejador.getListaAsignaciones().mostrarDatos();
    }

    private void cargarEstudiantes() {
        for (Estudiante estudiante : listaEstudiantes)
        {
            Manejador.addEstudiante(estudiante.getId(), estudiante.getName(), estudiante.getAddress());
        }
    }

    private void cargarUsuarios() {
        for (Usuario usuario : listaUsuarios)
        {
            if (usuario.getType().equals(Usuario.ESTUDIANTE))
            {
                Estudiante estudiante = Manejador.searchEstudiante(usuario.getId());
                if (estudiante != null)
                {
                    Manejador.addUser(usuario.getId(), usuario.getName(), usuario.getPassword(), usuario.getType(), estudiante);
                }
            } else
            {
                Manejador.addUser(usuario.getId(), usuario.getName(), usuario.getPassword(), usuario.getType(), null);
            }
        }
    }

    private void cargarCatedraticos() {
        for (Catedratico catedratico : listaCatedraticos)
        {
            Manejador.addCatedratico(catedratico.getId(), catedratico.getName(), catedratico.getAddress());
        }
    }

    private void cargarEdificios() {
        for (Edificio edificio : listaEdificios)
        {
            Manejador.addEdificio(edificio.getName());
        }
    }

    private void cargarSalones() {
        for (Salon salon : listaSalones)
        {
            Manejador.addSalonEdificio(salon.getEdificio(), salon.getId(), salon.getSize());
        }
    }

    private void cargarCursos() {
        for (Curso curso : listaCursos)
        {
            Manejador.addCurso(curso.getId(), curso.getName(), curso.getSemester(), curso.getCredits());
        }
    }

    private void cargarHorarios() {
        for (Horario horario : listaHorarios)
        {
            Horario horarioFinal = Manejador.getDataHorario(
                    horario.getId(),
                    horario.getPeriod(),
                    horario.getDay(),
                    String.valueOf(horario.getCursoId()),
                    String.valueOf(horario.getSalonId()),
                    horario.getEdificioId(),
                    horario.getCatedraticoId()
            );
            if (horarioFinal != null)
            {
                Manejador.addHorario(horarioFinal);
            }
        }
    }

    private void cargarAsignaciones() {
        for (Asignacion asignacion : listaAsignaciones)
        {
            Estudiante estudiante = Manejador.searchEstudiante(asignacion.getEstudianteId());
            Horario horario = Manejador.searchHorario(asignacion.getHorarioId());
            if(estudiante != null && horario != null){
                System.out.println("Asignacion "+estudiante.getId());
                Manejador.addAsignacion(Manejador.getAsignacionesSize(), horario, estudiante, asignacion.getZona(), asignacion.getFinal_test());
            }
        }
    }

}
