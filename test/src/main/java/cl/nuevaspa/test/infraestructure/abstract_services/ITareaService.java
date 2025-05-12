package cl.nuevaspa.test.infraestructure.abstract_services;

import cl.nuevaspa.test.api.models.request.TareaRequest;
import cl.nuevaspa.test.api.models.response.TareaResponse;

import java.util.Date;
import java.util.Set;

public interface ITareaService{
    TareaResponse create(TareaRequest request);
    TareaResponse read(Long id);
    TareaResponse update(TareaRequest request, Long id);
    void delete(Long id);

    Set<TareaResponse> tareasEntreFechas(Date fechaDesde, Date fechaHasta);
    Set<TareaResponse> tareasPorEstado(String estado);
}
