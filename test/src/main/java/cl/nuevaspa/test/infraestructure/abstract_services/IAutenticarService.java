package cl.nuevaspa.test.infraestructure.abstract_services;

import cl.nuevaspa.test.api.models.request.LoginRequest;
import cl.nuevaspa.test.api.models.request.RegistroUsuarioRequest;
import cl.nuevaspa.test.api.models.response.AutenticarResponse;

public interface IAutenticarService {
    public AutenticarResponse login(LoginRequest request);
    public AutenticarResponse registro(RegistroUsuarioRequest request);
}
