package com.springsimplespasos.universidad.universidadbackend.controlador.dto;

import com.springsimplespasos.universidad.universidadbackend.modelo.dto.PersonaDTO;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Alumno;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Empleado;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Persona;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Profesor;
import com.springsimplespasos.universidad.universidadbackend.modelo.mapper.mapstruct.AlumnoMapper;
import com.springsimplespasos.universidad.universidadbackend.modelo.mapper.mapstruct.EmpleadoMapper;
import com.springsimplespasos.universidad.universidadbackend.modelo.mapper.mapstruct.ProfesorMapper;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.PersonaDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PersonaDtoController extends GenericDtoController<Persona, PersonaDAO> {
    @Autowired
    protected AlumnoMapper alumnoMapper;
    @Autowired
    private ProfesorMapper profesorMapper;
    @Autowired
    private EmpleadoMapper empleadoMapper;


    public PersonaDtoController(PersonaDAO service, String nombre_entidad) {
        super(service, nombre_entidad);


    }

    public PersonaDTO altaPersona(Persona persona) {
        Persona personaEntidad = super.altaEntidad(persona);
        PersonaDTO dto = null;
        if(personaEntidad instanceof Alumno) {
            dto = alumnoMapper.mapAlumno((Alumno) personaEntidad);
        } else if (personaEntidad instanceof Profesor) {
            //aplicariamos mapper de profesor
            dto = profesorMapper.mapProfesor((Profesor) personaEntidad);
        } else if (personaEntidad instanceof Empleado) {
            //aplicamos el mapper de empelado
            dto = empleadoMapper.mapEmpleado((Empleado) personaEntidad);
        }
        return dto;
    }

    public PersonaDTO buscarPersonaPorId(Integer id) {
        Optional<Persona> oPersona = super.obtenerPorId(id);
        Persona persona;
        PersonaDTO dto = null;
        System.out.println(this.nombre_entidad);
        if(oPersona.isEmpty()){
            return null;
        } else {
            persona = oPersona.get();
        }
        if(this.nombre_entidad == "Alumno" && persona instanceof Alumno) {
            dto = alumnoMapper.mapAlumno((Alumno) persona);
        } else if (this.nombre_entidad.toString() == "Profesor" && persona instanceof Profesor) {
            //aplicariamos mapper de profesor
            dto = profesorMapper.mapProfesor((Profesor) persona);
        } else if (this.nombre_entidad.toString() == "Empleado" && persona instanceof Empleado) {
            //aplicamos el mapper de empelado
            dto = empleadoMapper.mapEmpleado((Empleado) persona);

        }
        return dto;
    }

    public List<?> obtenerTodoss() {
        List<Persona> oPersona = super.obtenerTodos();
        List<Persona> persona;
        Empleado empleado = new Empleado();
        Alumno alumno = new Alumno();
        Profesor profesor = new Profesor();
        ArrayList<Persona> d = new ArrayList<>();
        if(oPersona.isEmpty()){
            return null;
        } else {
            persona = oPersona;
        }
        if(this.nombre_entidad == "Alumno") {
            for (Persona per: persona){
                if (per.getClass().isInstance(alumno))
                    d.add(per);
            }
        } else if (this.nombre_entidad == "Profesor") {
            for (Persona per: persona){
                if (per.getClass().isInstance(profesor))
                    d.add(per);
            }
        } else if (this.nombre_entidad == "Empleado") {
            for (Persona per: persona){
                if (per.getClass().isInstance(empleado))
                    d.add(per);
            }
        }

        return d;
    }

}
