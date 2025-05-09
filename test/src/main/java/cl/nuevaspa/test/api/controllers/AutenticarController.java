package cl.nuevaspa.test.api.controllers;

import cl.nuevaspa.test.api.models.request.LoginRequest;
import cl.nuevaspa.test.api.models.request.RegistroUsuarioRequest;
import cl.nuevaspa.test.api.models.response.AutenticarResponse;
import cl.nuevaspa.test.infraestructure.abstract_services.IAutenticarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autenticar")
@RequiredArgsConstructor
@Tag(name = "Usuarios")
public class AutenticarController {
    private final IAutenticarService iAutenticarService;

    @Operation(summary = "Servicio para que un usuario se autentique.")
    @PostMapping(value = "login")
    public ResponseEntity<AutenticarResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(iAutenticarService.login(request));
    }

    @Operation(summary = "Servicio para registrar un nuevo usuario.")
    @PostMapping(value = "registro")
    public ResponseEntity<AutenticarResponse> registro(@RequestBody RegistroUsuarioRequest request){
        return ResponseEntity.ok(iAutenticarService.registro(request));
    }
}