package com.springsimplespasos.universidad.universidadbackend.modelo.mapper.mapstruct;

import com.springsimplespasos.universidad.universidadbackend.modelo.dto.EmpleadoDTO;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Empleado;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Persona;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-14T10:43:15-0700",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 18.0.2.1 (Oracle Corporation)"
)
@Component
public class EmpleadoMapperImpl extends EmpleadoMapper {

    @Override
    public EmpleadoDTO mapEmpleado(Empleado empleado) {
        if ( empleado == null ) {
            return null;
        }

        EmpleadoDTO empleadoDTO = new EmpleadoDTO();

        empleadoDTO.setId( empleado.getId() );
        empleadoDTO.setNombre( empleado.getNombre() );
        empleadoDTO.setApellido( empleado.getApellido() );
        empleadoDTO.setDni( empleado.getDni() );
        empleadoDTO.setDireccion( empleado.getDireccion() );
        empleadoDTO.setSueldo( empleado.getSueldo() );
        empleadoDTO.setTipoEmpleado( empleado.getTipoEmpleado() );

        return empleadoDTO;
    }

    @Override
    public Empleado mapEmpleado(EmpleadoDTO empleadoDTO) {
        if ( empleadoDTO == null ) {
            return null;
        }

        Empleado empleado = new Empleado();

        empleado.setId( empleadoDTO.getId() );
        empleado.setNombre( empleadoDTO.getNombre() );
        empleado.setApellido( empleadoDTO.getApellido() );
        empleado.setDni( empleadoDTO.getDni() );
        empleado.setDireccion( empleadoDTO.getDireccion() );
        empleado.setSueldo( empleadoDTO.getSueldo() );
        empleado.setTipoEmpleado( empleadoDTO.getTipoEmpleado() );

        return empleado;
    }

    @Override
    public List<EmpleadoDTO> mapListEmpelado(List<Persona> empleado) {
        if ( empleado == null ) {
            return null;
        }

        List<EmpleadoDTO> list = new ArrayList<EmpleadoDTO>( empleado.size() );
        for ( Persona persona : empleado ) {
            list.add( personaToEmpleadoDTO( persona ) );
        }

        return list;
    }

    protected EmpleadoDTO personaToEmpleadoDTO(Persona persona) {
        if ( persona == null ) {
            return null;
        }

        EmpleadoDTO empleadoDTO = new EmpleadoDTO();

        empleadoDTO.setId( persona.getId() );
        empleadoDTO.setNombre( persona.getNombre() );
        empleadoDTO.setApellido( persona.getApellido() );
        empleadoDTO.setDni( persona.getDni() );
        empleadoDTO.setDireccion( persona.getDireccion() );

        return empleadoDTO;
    }
}
