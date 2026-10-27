package utn.tienda_libros.vista;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import utn.tienda_libros.modelo.Libro;
import utn.tienda_libros.servicio.LibroServicio;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

@Component
public class LibroFrom extends JFrame {
    LibroServicio libroServicio;
    private JPanel panel;
    private JTable tablaLibros;
    private JTextField libroTextoTextField;
    private JTextField autorTextoTextField;
    private JTextField precioTextoTextField;
    private JTextField existenciaTextoTextField;
    private JButton agregarButton;
    private JButton modificarButton;
    private JButton eliminarButton;
    private DefaultTableModel tablaModeloLibros;

    @Autowired
    public LibroFrom(LibroServicio libroServicio) {
        this.libroServicio = libroServicio;
        iniciarForma();
        agregarButton.addActionListener(e ->agregarLibro());
    }

    private void iniciarForma() {
        setContentPane(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(900, 700);

        //Para obtener las dimeciones de la ventana
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension tamanioPantalla = toolkit.getScreenSize();
        int x = (tamanioPantalla.width - getWidth() / 2);
        int y = (tamanioPantalla.height - getHeight() / 2);
        setLocation(x, y);
    }


    private void agregarLibro(){
        //Leer los valores del formulario
        if(libroTextoTextField.getText().equalsIgnoreCase("")){
            mostrarMensaje("Ingresa el nombre del libro: ");
            libroTextoTextField.requestFocusInWindow();
            return;
        }
        var nombreLibro = libroTextoTextField.getText();
        var autor = autorTextoTextField.getText();
        var precio = Double.parseDouble(precioTextoTextField.getText());
        var existencias = Integer.parseInt(existenciaTextoTextField.getText());
        //Creamos el objeto libro
        var libro = new Libro();
        libro.setNombreLibro(nombreLibro);
        libro.setAutor(autor);
        libro.setPrecio(String.valueOf(precio));
        libro.setExistencias(existencias);

        this.libroServicio.guardarLibro(libro);
        mostrarMensaje("Se agrego el libro");
        limpiarFormulario();
        listarLibros();
    }

    private void limpiarFormulario(){
        libroTextoTextField.setText("");
        autorTextoTextField.setText("");
        precioTextoTextField.setText("");
        existenciaTextoTextField.setText("");
    }

    private void mostrarMensaje(String mensaje){
        JOptionPane.showMessageDialog(this, mensaje);
    }

    private void createUIComponents() {
        this.tablaModeloLibros = new DefaultTableModel(0, 5);
        String[] cabecera = {"id", "Libro", "Autor", "Precio", "Existencia"};
        this.tablaModeloLibros.setColumnIdentifiers(cabecera);

        //Instanciar el objeto de JTable
        this.tablaLibros = new JTable(tablaModeloLibros);
        listarLibros();
    }

    private void listarLibros() {
        //Limpiar la tabla
        tablaModeloLibros.setRowCount(0);
        //Obtener los libros de la BD
        var libros = libroServicio.listarLibros();
        //Iteramos cada libro
        libros.forEach((libro -> {
            //Creamos cada registro para agregarlos ala tabla
            Object[] renglonLibro = {
                    libro.getIdLibro(),
                    libro.getNombreLibro(),
                    libro.getAutor(),
                    libro.getPrecio(),
                    libro.getExistencias()
            };
            this.tablaModeloLibros.addRow(renglonLibro);
        }));

    }

}
