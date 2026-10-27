package utn.estudiantes.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Entity
@Table(name = "estudiantes2022")
public class Estudiantes2022 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEstudiantes2022;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
}
