package cl.nuevaspa.test.infraestructure.services;

import cl.nuevaspa.test.api.models.request.TareaRequest;
import cl.nuevaspa.test.api.models.response.TareaResponse;
import cl.nuevaspa.test.domain.enteties.EstadoTareaEntity;
import cl.nuevaspa.test.domain.enteties.TareaEntity;
import cl.nuevaspa.test.domain.enteties.UsuarioEntity;
import cl.nuevaspa.test.domain.repositories.EstadoTareaRepository;
import cl.nuevaspa.test.domain.repositories.TareaRepository;
import cl.nuevaspa.test.domain.repositories.UsuarioRepository;
import cl.nuevaspa.test.infraestructure.abstract_services.ITareaService;
import cl.nuevaspa.test.utils.JwtFiltroAutenticacion;
import cl.nuevaspa.test.utils.JwtTokenConfig;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TareaService implements ITareaService {
    private final TareaRepository tareaRepository;
    private final UsuarioRepository usuarioRepository;
    private final EstadoTareaRepository estadoTareaRepository;
    private final JwtTokenConfig jwtTokenConfig;
    private final JwtFiltroAutenticacion jwtFiltroAutenticacion;

    @Autowired
    private final HttpServletRequest requestHttp;

    @Override
    public TareaResponse create(TareaRequest request) {
        final String token = jwtFiltroAutenticacion.getTokenFromRequest(requestHttp);

        String usernameToken = jwtTokenConfig.getUsernameFromToken(token);

        EstadoTareaEntity estadoTareaEntity = estadoTareaRepository.findById(request.getEstado()).orElseThrow();
        Optional<UsuarioEntity> usuarioEntity = usuarioRepository.findByUsername(usernameToken);

        TareaEntity nuevaTarea = TareaEntity.builder()
                .descripcion(request.getDescripcion())
                .fechaRegistro(new Date())
                .fechaModificacion(new Date())
                .usuario(usuarioEntity.get())
                .estado(estadoTareaEntity)
                .build();

        TareaEntity tareaIngresada = tareaRepository.save(nuevaTarea);

        return entityToResponse(tareaIngresada);
    }

    @Override
    public TareaResponse read(Long aLong) {
        return null;
    }

    @Override
    public TareaResponse update(TareaRequest request, Long idTarea) {
        var tareaMod = tareaRepository.findById(idTarea).orElseThrow();
        final String token = jwtFiltroAutenticacion.getTokenFromRequest(requestHttp);

        String usernameToken = jwtTokenConfig.getUsernameFromToken(token);

        EstadoTareaEntity estadoTareaEntity = estadoTareaRepository.findById(request.getEstado()).orElseThrow();
        Optional<UsuarioEntity> usuarioEntity = usuarioRepository.findByUsername(usernameToken);

        tareaMod.setDescripcion(request.getDescripcion());
        tareaMod.setEstado(estadoTareaEntity);
        tareaMod.setUsuario(usuarioEntity.get());
        tareaMod.setFechaModificacion(new Date());

        TareaEntity tareaModificada = tareaRepository.save(tareaMod);

        return entityToResponse(tareaModificada);
    }

    @Override
    public void delete(Long idTarea) {
        TareaEntity tareaEntity = tareaRepository.findById(idTarea).orElseThrow();

        tareaRepository.delete(tareaEntity);
    }

    @Override
    public Set<TareaResponse> tareasEntreFechas(Date fechaDesde, Date fechaHasta) {
        var tareas = tareaRepository.findByEntreFechaRegistro(fechaDesde, fechaHasta);
        //var tareas = tareaRepository.findAllByFechaRegistroGreaterThanEqualAndFechaRegistroLessThanEqual(fechaDesde, fechaHasta);

        return tareas.stream().map(this::entityToResponse).collect(Collectors.toSet());
    }

    @Override
    public Set<TareaResponse> tareasPorEstado(String estado) {
        var tareas = tareaRepository.findAllByCodEstado(estado);

        return tareas.stream().map(this::entityToResponse).collect(Collectors.toSet());
    }

    private TareaResponse entityToResponse(TareaEntity entity){
        var tareaResponse = new TareaResponse();
        BeanUtils.copyProperties(entity, tareaResponse);

        tareaResponse.setUsuario(entity.getUsuario().getUsername());
        tareaResponse.setEstado(entity.getEstado().getCodigoEstado());

        return tareaResponse;
    }
}
