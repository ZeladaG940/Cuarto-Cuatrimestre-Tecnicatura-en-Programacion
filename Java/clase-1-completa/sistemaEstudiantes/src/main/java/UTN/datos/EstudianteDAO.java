package UTN.datos;

import UTN.dominio.Estudiante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static UTN.conexion.Conexion.getConnection;

public class EstudianteDAO {

    //Metodo listar
    public List<Estudiante> listarEstudiantes() {
        List<Estudiante> estudiantes = new ArrayList<>();

        //Creamos algunos objetos queson necesarios para comunicarnos con la base de datos
        PreparedStatement ps; //Envia la sentencia a la base de datos
        ResultSet rs;  //Obtenemos el resultado de la base de datos

        //Creani un objeto de tipo conexion
        Connection con = getConnection();
        String sql = "SELECT * FROM estudiantes2022 ORDER BY idestudiantes2022";

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                var estudiante = new Estudiante();
                estudiante.setIdEstudiante(rs.getInt("idestudiantes2022"));
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setApellido(rs.getString("apellido"));
                estudiante.setTelefono(rs.getString("telefono"));
                estudiante.setEmail(rs.getString("email"));

                //Falta agregarlo ala lista
                estudiantes.add(estudiante);
            }
        } catch (Exception e) {
            System.out.println("Ocurrio un error al seleccionar datos: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Ocurrio un error al cerrar la conexion: " + e.getMessage());
            }
        }//Fin finallyu
        return estudiantes;
    }//Fin del metodo

    //Metodo para id -> fin by id
    public boolean buscarEstudiantePorId(Estudiante estudiante) {
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConnection();
        String sql = "SELECT * FROM estudiantes2022 WHERE idestudiantes2022=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, estudiante.getIdEstudiante());
            rs = ps.executeQuery();
            if (rs.next()) {
                estudiante.setIdEstudiante(rs.getInt("idestudiantes2022"));
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setApellido(rs.getString("apellido"));
                estudiante.setTelefono(rs.getString("telefono"));
                estudiante.setEmail(rs.getString("email"));
                return true; //Se encontro un registro
            }//Fin del if
        } catch (Exception e) {
            System.out.println("Ocurrio un error al buscar estudiante: " + e.getMessage());
        }//Fin catch
        finally {
            try {
                con.close();
            }catch (Exception e){
                System.out.println("Ocurrio un error al cerrar conexion: " + e.getMessage());
            }//Fin de catch
        }// Fin de finally
        return false;
    }
    //Metodo agregar un nuevo estudiante
    public boolean agregarEstudiante(Estudiante estudiante){
        PreparedStatement ps;
        Connection con = getConnection();
        String sql = "INSERT INTO estudiantes2022 (nombre, apellido, telefono, email) VALUES ( ?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, estudiante.getNombre());
            ps.setString(2, estudiante.getApellido());
            ps.setString(3, estudiante.getTelefono());
            ps.setString(4, estudiante.getEmail());
            ps.execute();
            return true;
        }catch (Exception e){
            System.out.println("Ocurrio un error al agregar estudiante: "+e.getMessage());
        }//Fin de catch
        finally {
            try {
                con.close();
            }catch (Exception e){
                System.out.println("Error al cerra la conexion: "+e.getMessage());
            }//Fin del catch
        }//Fin del finally
        return  false;
    }//Fin metodo agregarEstudiante

    //Modificar Estudiante
    public boolean modificarEstudiante(Estudiante estudiante){
        PreparedStatement ps;
        Connection con = getConnection();
        String sql = "UPDATE estudiantes2022 SET nombre=?, apellido=?, telefono=?, email=? WHERE idestudiantes2022=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, estudiante.getNombre());
            ps.setString(2, estudiante.getApellido());
            ps.setString(3, estudiante.getTelefono());
            ps.setString(4, estudiante.getEmail());
            ps.setInt(5, estudiante.getIdEstudiante());
            ps.execute();
            return true;
        }catch (Exception e){
            System.out.println("Error al modificar estudiante: " + e.getMessage());
        }//Fin del catch
        finally {
            try{
                con.close();
            }catch (Exception e){
                System.out.println("Error al cerra la conexion: " + e.getMessage());
            }//Fin del catch
        }//Fin del finally
        return false;
    }//Fin del metodod modificar

    //Eliminar Estudiante
    public boolean eliminarEstudiante(Estudiante estudiante){
        PreparedStatement ps;
        Connection con = getConnection();
        String sql = "DELETE FROM estudiantes2022 WHERE idestudiantes2022=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, estudiante.getIdEstudiante());
            ps.execute();
            return true;
        } catch (Exception e){
            System.out.println("Error al eliminar estudiante: " + e.getMessage());
        }
        finally {
            try {
                con.close();
            }catch (Exception e){
                System.out.println("Error al cerra la conexion: " + e.getMessage());
            }//Fin del catch
        }//Fin del finally

        return false;
    }


    //Pruebas
    public static void main(String[] args) {
        var EstudianteDAO = new EstudianteDAO();

        //Lista los estudiantes
        System.out.println("Listado de estudiantes: ");
        List<Estudiante> estudiantes = EstudianteDAO.listarEstudiantes();
        estudiantes.forEach(System.out::println); //Funcion lamda para imprimir

        /*
        //Modificar estudiante

        var estudianteModificado = new Estudiante(2, "Juan Calos", "Juares", "2622147258", "Juam@gmail.com");
        var modificado = EstudianteDAO.modificarEstudiante(estudianteModificado);
        if(modificado) {
            System.out.println("Estudiante modificado: " + estudianteModificado);
        }else {
            System.out.println("No se modifico el estudiante: " + estudianteModificado);
        }
        */

        /*
        //Agregar Estudiante

        var nuevoEstudiante = new Estudiante("Carlos", "Lara", "2622123456", "carlos@gmail.com");
        var agregado = EstudianteDAO.agregarEstudiante(nuevoEstudiante);
        if(agregado){
            System.out.println("Estudiante agregado: "+nuevoEstudiante);
        }else {
            System.out.println("No se a agregado estudiante: " + nuevoEstudiante);
        }
        */

        /*
        //Buscar por id

        var estudiante1 = new Estudiante(2);
        System.out.println("Estudiantes antes de la busqueda: "+estudiante1);
        var encontrado = EstudianteDAO.buscarEstudiantePorId(estudiante1);
        if(encontrado){
            System.out.println("Estudiante encontrado: " + estudiante1);
        }else {
            System.out.println("No se encontro el estudiante: " + estudiante1.getIdEstudiante());
        }
        */

        //Eliminar estudiante con id 3
        var estudianteEliminar = new Estudiante(3);
        var eliminado = EstudianteDAO.eliminarEstudiante(estudianteEliminar);
        if(eliminado){
            System.out.println("Estudiante eliminado: " + estudianteEliminar);
        }else {
            System.out.println("No se elimino el estudiante: " + estudianteEliminar);
        }
    }
}
