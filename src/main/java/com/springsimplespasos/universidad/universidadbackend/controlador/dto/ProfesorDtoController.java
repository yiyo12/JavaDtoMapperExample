package com.springsimplespasos.universidad.universidadbackend.controlador.dto;

import com.springsimplespasos.universidad.universidadbackend.modelo.dto.AlumnoDTO;
import com.springsimplespasos.universidad.universidadbackend.modelo.dto.PersonaDTO;
import com.springsimplespasos.universidad.universidadbackend.modelo.dto.ProfesorDTO;
import com.springsimplespasos.universidad.universidadbackend.modelo.mapper.mapstruct.AlumnoMapper;
import com.springsimplespasos.universidad.universidadbackend.modelo.mapper.mapstruct.ProfesorMapper;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.PersonaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/profesor")
@ConditionalOnProperty(prefix = "app", name = "controller.enable-dto", havingValue = "true")
public class ProfesorDtoController extends PersonaDtoController{

    @Autowired
    private ProfesorMapper profesorMapper;

    public ProfesorDtoController(PersonaDAO service) {
        super(service, "Profesor");
    }


    @PostMapping
    public ResponseEntity<?> altaProfesor(@Valid @RequestBody PersonaDTO personaDTO, BindingResult result){
        Map<String, Object> mensaje = new HashMap<>();

        if(result.hasErrors()) {
            mensaje.put("success", Boolean.FALSE);
            //mensaje.put("validaciones", super.obtenerValidaciones(result));
            mensaje.put("validaciones", super.obtenerValidaciones(result));
            return ResponseEntity.badRequest().body(mensaje);
        }

        PersonaDTO save2 = super.altaPersona(profesorMapper.mapProfesor((ProfesorDTO) personaDTO));

        //PersonaDTO save = super.altaPersona(alumnoMapper.mapAlumno((AlumnoDTO) personaDTO));

        mensaje.put("succes", Boolean.TRUE);
        mensaje.put("data", save2);

        return ResponseEntity.status(HttpStatus.CREATED).body(mensaje);
    }


}
