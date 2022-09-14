package com.springsimplespasos.universidad.universidadbackend.controlador.dto;

import com.springsimplespasos.universidad.universidadbackend.modelo.dto.EmpleadoDTO;
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
        if(oPersona.isEmpty()){
            return null;
        } else {
            persona = oPersona.get();
        }
        if(persona instanceof Alumno) {
            dto = alumnoMapper.mapAlumno((Alumno) persona);
        } else if (persona instanceof Profesor) {
            //aplicariamos mapper de profesor
        } else if (persona instanceof Empleado) {
            //aplicamos el mapper de empelado
        }
        return dto;
    }

    public List<EmpleadoDTO> obtenerTodoss() {
        List<Persona> oPersona = super.obtenerTodos();
        List<Persona> persona;
        List<Empleado> oEmpl;
        List<EmpleadoDTO> dto = null;
        if(oPersona.isEmpty()){
            return null;
        } else {
            persona = oPersona;

            dto = empleadoMapper.mapListEmpelado(oPersona);

        }
        if(persona instanceof Alumno) {

            //dto = alumnoMapper.mapAlumno((Alumno) persona);
        } else if (persona instanceof Profesor) {
            //aplicariamos mapper de profesor
           // dto = profesorMapper.mapProfesor((Profesor) persona);
        } else if (super.nombre_entidad == "Empleado") {
            //aplicamos el mapper de empelado
            for (EmpleadoDTO dta: dto){
                System.out.println(dta.getTipoEmpleado());

            }
            //dto = empleadoMapper.mapEmpleado((Empleado) persona);
        }

        return dto;
        //return (List<Persona>) dto;
    }

    public List<Persona> obtenerPorTipoEmpleado() {
        List<Persona> oPersona = super.obtenerTodos();
        List<Persona> tmp;
        Persona persona;
        PersonaDTO dto = null;
        if(oPersona.isEmpty()){
            return null;
        } else {
            persona = (Persona) oPersona;
        }
        if(persona instanceof Alumno) {
            dto = alumnoMapper.mapAlumno((Alumno) persona);
        } else if (persona instanceof Profesor) {
            //aplicariamos mapper de profesor
            dto = profesorMapper.mapProfesor((Profesor) persona);
        } else if (persona instanceof Empleado) {
            //aplicamos el mapper de empelado
            dto = empleadoMapper.mapEmpleado((Empleado) persona);
            for (Persona lst: oPersona){
            }
        }
        return (List<Persona>) dto;
    }

}
