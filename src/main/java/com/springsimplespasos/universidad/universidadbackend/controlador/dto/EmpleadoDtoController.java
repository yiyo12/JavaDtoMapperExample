package com.springsimplespasos.universidad.universidadbackend.controlador.dto;

import com.springsimplespasos.universidad.universidadbackend.modelo.dto.EmpleadoDTO;
import com.springsimplespasos.universidad.universidadbackend.modelo.dto.PersonaDTO;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Empleado;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Persona;
import com.springsimplespasos.universidad.universidadbackend.modelo.mapper.mapstruct.EmpleadoMapper;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.PersonaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/empleado")
@ConditionalOnProperty(prefix = "app", name = "controller.enable-dto", havingValue = "true")
public class EmpleadoDtoController extends PersonaDtoController {

    @Autowired
    private EmpleadoMapper empleadoMapper;

    public EmpleadoDtoController(PersonaDAO service) {
        super(service, "Empleado");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerEmpleadoPorId(@PathVariable Integer id){
        Map<String, Object> mensaje = new HashMap<>();

        PersonaDTO dto = super.buscarPersonaPorId(id);

        if(dto == null) {
            mensaje.put("succes", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No existe %s con ID %d", nombre_entidad, id));
            return ResponseEntity.badRequest().body(mensaje);
        }

        mensaje.put("success", Boolean.TRUE);
        mensaje.put("data", dto);

        return ResponseEntity.ok(mensaje);
    }
/*
    @GetMapping("/{tipo}")
    public ResponseEntity<?> obtenerEmpleadoPorTipo(@PathVariable String tipo){
        Map<String, Object> mensaje = new HashMap<>();

        PersonaDTO dto = personaCon.buscarPersonaPorId(id);

        if(dto == null) {
            mensaje.put("succes", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No existe %s con ID %d", "Empleados", id));
            return ResponseEntity.badRequest().body(mensaje);
        }

        mensaje.put("success", Boolean.TRUE);
        mensaje.put("data", dto);

        return ResponseEntity.ok(mensaje);
    }*/


    @GetMapping()
    public ResponseEntity<?> obtenerEmpleados(){
        Map<String, Object> mensaje = new HashMap<>();
        //usando generico
        //List<Persona> dto = personaCon.obtenerTodos();
        List<?> dto =  super.obtenerTodoss();

        if(dto == null) {
            mensaje.put("succes", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No existe %s con ID %d", "Empleados"));
            return ResponseEntity.badRequest().body(mensaje);
        }

        mensaje.put("success", Boolean.TRUE);
        mensaje.put("data", dto);

        return ResponseEntity.ok(mensaje);
    }

    @PostMapping
    public ResponseEntity<?> altaEmpleado(@Valid @RequestBody PersonaDTO personaDTO, BindingResult result){
        Map<String, Object> mensaje = new HashMap<>();

        if(result.hasErrors()) {
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("validaciones", super.obtenerValidaciones(result));
            return ResponseEntity.badRequest().body(mensaje);
        }

        PersonaDTO save = super.altaPersona(empleadoMapper.mapEmpleado((EmpleadoDTO) personaDTO));

        mensaje.put("succes", Boolean.TRUE);
        mensaje.put("data", save);

        return ResponseEntity.status(HttpStatus.CREATED).body(mensaje);
    }
}
