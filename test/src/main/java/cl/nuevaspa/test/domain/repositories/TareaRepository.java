package cl.nuevaspa.test.domain.repositories;

import cl.nuevaspa.test.domain.enteties.TareaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Date;
import java.util.Set;

public interface TareaRepository extends JpaRepository<TareaEntity,Long> {
    @Query("select t from tarea t where date_trunc('Day',t.fechaRegistro) >= :fechaDesde and date_trunc('Day',t.fechaRegistro) <= :fechaHasta")
    Set<TareaEntity> findByEntreFechaRegistro(Date fechaDesde, Date fechaHasta);

    @Query("select t from tarea t where t.estado.codigoEstado = :estado")
    Set<TareaEntity> findAllByCodEstado(String estado);
}
