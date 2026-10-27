package utn.tienda_libros.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utn.tienda_libros.modelo.Libro;

@Repository
public interface LibroRepositorio extends JpaRepository<Libro, Integer> {
}
