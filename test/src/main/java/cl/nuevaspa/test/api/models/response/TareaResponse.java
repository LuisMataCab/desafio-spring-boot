package cl.nuevaspa.test.api.models.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TareaResponse implements Serializable {
    private Long id;
    private String descripcion;
    private String usuario;
    private Date fechaRegistro;
    private Date fechaModificacion;
    private String estado;
}
