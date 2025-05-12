package cl.nuevaspa.test.api.controllers;

import cl.nuevaspa.test.api.models.request.TareaRequest;
import cl.nuevaspa.test.api.models.response.TareaResponse;
import cl.nuevaspa.test.infraestructure.abstract_services.ITareaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

@RestController
@RequestMapping("/api/tarea")
@RequiredArgsConstructor
@Tag(name = "Tareas")
public class TareaController {
    private final ITareaService iTareaService;

    @Operation(summary = "Servicio para registrar una nueva tarea.")
    @PostMapping
    public ResponseEntity<TareaResponse> post(@RequestBody TareaRequest request){
        return ResponseEntity.ok(iTareaService.create(request));
    }

    @Operation(summary = "Servicio para modificar datos de una tarea mediante su ID.")
    @PutMapping
    public ResponseEntity<TareaResponse> put(@RequestBody TareaRequest request, @RequestParam Long idTarea){
        return ResponseEntity.ok(iTareaService.update(request, idTarea));
    }

    @Operation(summary = "Servicio para eliminar una tarea mediante su ID.")
    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam Long idTarea){
        iTareaService.delete(idTarea);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Servicio para buscar tareas creadas un un rango de fechas.")
    @GetMapping(path = "tareasEntreFechas")
    public ResponseEntity<Set<TareaResponse>> getTareasEntreFechas(
            @RequestParam String fechaDesde,
            @RequestParam String fechaHasta){

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaDesdeDate = new Date();
        Date fechaHastaDate = new Date();

        try {
            fechaDesdeDate = sdf.parse(fechaDesde);
            fechaHastaDate = sdf.parse(fechaHasta);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        var tareas = iTareaService.tareasEntreFechas(fechaDesdeDate, fechaHastaDate);

        return tareas.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(tareas);
    }

    @Operation(summary = "Servicio para buscar tareas con un estado definido.")
    @GetMapping(path = "tareasPorEstado")
    public ResponseEntity<Set<TareaResponse>> getTareasPorEstado(@RequestParam String estado){
        var tareas = iTareaService.tareasPorEstado(estado);

        return tareas.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(tareas);
    }
}
