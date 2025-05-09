package cl.nuevaspa.test.domain.enteties;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Entity(name = "tarea")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TareaEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;
    @Column(name = "fecha_registro")
    private Date fechaRegistro;
    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private UsuarioEntity usuario;

    @ManyToOne
    @JoinColumn(name = "codigo_estado")
    private EstadoTareaEntity estado;
}
