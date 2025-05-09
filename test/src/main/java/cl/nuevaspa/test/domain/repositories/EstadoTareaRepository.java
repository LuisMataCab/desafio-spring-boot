package cl.nuevaspa.test.domain.repositories;

import cl.nuevaspa.test.domain.enteties.EstadoTareaEntity;
import org.springframework.data.repository.CrudRepository;

public interface EstadoTareaRepository extends CrudRepository<EstadoTareaEntity,String> {
}
