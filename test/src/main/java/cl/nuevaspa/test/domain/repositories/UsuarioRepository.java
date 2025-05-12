package cl.nuevaspa.test.domain.repositories;

import cl.nuevaspa.test.domain.enteties.UsuarioEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<UsuarioEntity,Integer> {
    Optional<UsuarioEntity> findByUsername(String username);
}
