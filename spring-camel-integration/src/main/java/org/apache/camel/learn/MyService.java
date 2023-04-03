package org.apache.camel.learn;

import org.springframework.web.bind.annotation.RestController;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class MyService {

    @Autowired
    private ProducerTemplate template;

    @GetMapping(value = "/listarclient")
    public String consultaPersonas() {
        return template.requestBody("direct:listarclient", "").toString();
    }

    @PostMapping(value = "/cliente")
    public ResponseEntity<String> crearCliente(@RequestBody Client cliente) {
        try {
            template.requestBody("direct:crearCliente", cliente);
            return ResponseEntity.status(HttpStatus.CREATED).body("exito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }
}
