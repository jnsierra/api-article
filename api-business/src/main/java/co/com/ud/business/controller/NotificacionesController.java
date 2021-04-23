package co.com.ud.business.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v.1/notificaciones")
public class NotificacionesController {

    @GetMapping(value = "/profesor/{idProfesor}/")
    public ResponseEntity<Integer> getNotificacionesProf(@PathVariable("idProfesor") Long idProf){
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/alumno/{idProfesor}/")
    public ResponseEntity<Integer> getNotificacionesAlumn(@PathVariable("idProfesor") Long idAlumn){
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
