package cl.nuevaspa.test.infraestructure.services;

import cl.nuevaspa.test.api.models.request.LoginRequest;
import cl.nuevaspa.test.api.models.request.RegistroUsuarioRequest;
import cl.nuevaspa.test.api.models.response.AutenticarResponse;
import cl.nuevaspa.test.domain.enteties.UsuarioEntity;
import cl.nuevaspa.test.domain.repositories.UsuarioRepository;
import cl.nuevaspa.test.infraestructure.abstract_services.IAutenticarService;
import cl.nuevaspa.test.utils.Rol;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static cl.nuevaspa.test.utils.JwtTokenConfig.getToken;

@Service
@RequiredArgsConstructor
public class AutenticarService implements IAutenticarService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AutenticarResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
        UserDetails usuario = usuarioRepository.findByUsername(request.getUsername()).orElseThrow();

        String token = getToken(usuario);

        return AutenticarResponse.builder()
                .token(token)
                .build();
    }

    public AutenticarResponse registro(RegistroUsuarioRequest request) {
        UsuarioEntity usuarioEntity = UsuarioEntity.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .nombre(request.getNombre())
                .apellido(request.getApellido())
                .rol(Rol.USUARIO)
                .build();

        usuarioRepository.save(usuarioEntity);

        return AutenticarResponse.builder()
                .token(getToken(usuarioEntity))
                .build();
    }
}
