package utn.estudiantes.servicio;

import utn.estudiantes.modelo.Estudiante;

import java.util.List;

public class EstudianteServicio implements IEstudianteServicio{
    @Override
    public List<Estudiante> listarEstudiantes() {
        return List.of();
    }

    @Override
    public Estudiante buscarEstudiante(Integer idEstudiante) {
        return null;
    }

    @Override
    public void guardarEstudiante(Estudiante estudiante) {

    }

    @Override
    public void eliminarEstudiante(Estudiante estudiante) {

    }
}
