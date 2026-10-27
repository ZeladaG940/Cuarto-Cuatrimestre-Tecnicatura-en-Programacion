package UTN.presentacion;

import UTN.conexion.Conexion;
import UTN.datos.EstudianteDAO;
import UTN.dominio.Estudiante;

import java.util.Scanner;

public class SistemaEstudiantesApp {
    public static void main(String[] args) {
        var salir = false;
        var consola = new Scanner((System.in)); // para leer informacion de la consola
        var estudianteDao = new EstudianteDAO(); //esta instancia debe de hacerse una vez
        while (!salir){
            try {
                mostrarMenu(); //este sera el metodo que devolvera un booleano
                salir = ejecutarOpciones(consola, estudianteDao); //Este arroja una exception
            }catch (Exception e){
                System.out.println("Ocurrio un error al ejecutar la operacion: " + e.getMessage());
            }
        } //Fin del while
    }// Fin del main

    private static  void mostrarMenu(){
        System.out.println("""
                *******Sistema de Estudiantes *******
                1. Listar Estudiantes
                2. Buscar Estudiantes
                3. Agregar Estudiantes
                4. Modificar Estudiantes
                5. Eliminar EStudiantes
                6. salir
                
                Elige una opcion:
                """);
    }
    //Metodo para ejecutar las opciones, va a regresar un valor booleano, ya que es el que
    //puede modificar el valor de una variables salir, si es verdaderi termina el ciclo while
    private static  boolean ejecutarOpciones(Scanner consola, EstudianteDAO estudianteDAO){
        var opcion = Integer.parseInt(consola.nextLine());
        var salir = false;
        switch (opcion){
            case 1 ->{
                System.out.println("Listado de Estudiantes...");
                //no muestra la informacion, solo recupera la info y regresa una lista
                var estudiantes = estudianteDAO.listarEstudiantes(); //resibe el listado
                //vamos a iterara cada objeto de tipo estudiante
                estudiantes.forEach(System.out::println); //para imprimir la lista
            }
            case 2 -> {
                System.out.println("Introduce el id_estudiante a buscar: ");
                var idEstudiante = Integer.parseInt(consola.nextLine());
                var estudiante = new Estudiante(idEstudiante);
                var encontrado = estudianteDAO.buscarEstudiantePorId(estudiante);
                if(encontrado){
                    System.out.println("Estudiante encontrado: " + estudiante);
                }else {
                    System.out.println("Estudiante No encontrado: "+ estudiante);
                }
            }
            case 3 ->{
                System.out.println("Agregar estudiante: ");
                System.out.println("Nombre: ");
                var nombre = consola.nextLine();
                System.out.println("Apellido: ");
                var apellido = consola.nextLine();
                System.out.println("Telefono: ");
                var telefono = consola.nextLine();
                System.out.println("Email: ");
                var email = consola.nextLine();
                //Crear objeto estudiante(sin id)
                var estudiante = new Estudiante(nombre, apellido, telefono, email);
                var agregado = estudianteDAO.agregarEstudiante(estudiante);
                if(agregado){
                    System.out.println("Estudiante agregado: " + estudiante);
                }else {
                    System.out.println("Estudiante No agregado: " + estudiante);
                }
            }
            case 4 ->{
                System.out.println("Modificar estudiante: ");
                ///Aqui lo primero es especificar cual es el id dle objeto a modificar
                System.out.println("Id Estudiante: ");
                var idEstudiante = Integer.parseInt(consola.nextLine());
                System.out.println("Nombre: ");
                var nombre = consola.nextLine();
                System.out.println("Apellido: ");
                var apellido = consola.nextLine();
                System.out.println("Telefono: ");
                var telefono = consola.nextLine();
                System.out.println("Email: ");
                var email = consola.nextLine();
                //crea el objeto estudiante a modificar
                var estudiante = new Estudiante(idEstudiante, nombre, apellido, telefono, email);
                var modificado = estudianteDAO.modificarEstudiante(estudiante);
                if(modificado){
                    System.out.println("Estudiante modificado: " + estudiante);
                }else{
                    System.out.println("Estudiante No modificado: " + estudiante);
                }
            }
            case 5 ->{
                System.out.println("Eliminar estudiante: ");
                System.out.println("Id estudiante: ");
                var idEstudiante = Integer.parseInt(consola.nextLine());
                var estudiante = new Estudiante(idEstudiante);
                var eliminado = estudianteDAO.eliminarEstudiante(estudiante);
                if(eliminado){
                    System.out.println("Estudiante eliminado: " + estudiante);
                }else{
                    System.out.println("Estudiante No eliminado: " + estudiante);
                }
            }
            case 6 ->{
                System.out.println("Hasta pronto!!!");
                salir = true;
            }
            default -> System.out.println("Opcion no reconocida, ingrese otra opcion");
        }//Fin del switch
        return salir;
    }

}//Fin de la Clase