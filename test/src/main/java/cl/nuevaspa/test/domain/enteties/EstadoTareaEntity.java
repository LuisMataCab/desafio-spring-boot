package cl.nuevaspa.test.domain.enteties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity(name = "estado_tarea")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EstadoTareaEntity implements Serializable {
    @Id
    @Column(name = "codigo_estado")
    private String codigoEstado;
    @Column(name = "desc_estado")
    private String descEstado;
}
