package com.springsimplespasos.universidad.universidadbackend.modelo.mapper.mapstruct;

import com.springsimplespasos.universidad.universidadbackend.modelo.dto.EmpleadoDTO;
import com.springsimplespasos.universidad.universidadbackend.modelo.dto.ProfesorDTO;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Empleado;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Persona;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Profesor;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", config = EmpleadoMapperConfig.class)
public abstract class EmpleadoMapper {

    public abstract EmpleadoDTO mapEmpleado(Empleado empleado);
    public abstract Empleado mapEmpleado(EmpleadoDTO empleadoDTO);

    public abstract List<EmpleadoDTO> mapListEmpelado(List<Persona> empleado);

}
