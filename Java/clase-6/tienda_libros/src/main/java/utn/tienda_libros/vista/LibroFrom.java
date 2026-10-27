package utn.tienda_libros.vista;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import utn.tienda_libros.modelo.Libro;
import utn.tienda_libros.servicio.LibroServicio;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Component
public class LibroFrom extends JFrame {
    LibroServicio libroServicio;
    private JPanel panel;
    private JTable tablaLibros;
    private JTextField idTexto;
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
        tablaLibros.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                cargarLibrosSeleccionado();
            }
        });

        modificarButton.addActionListener(e -> modificarLibro());
        eliminarButton.addActionListener(e -> eliminarLibro());
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

    private void cargarLibrosSeleccionado(){
        //Los indices de las columnas incias en 0
        var renglon = tablaLibros.getSelectedRow();
        if(renglon != -1){
            String idLibro = tablaLibros.getModel().getValueAt(renglon, 0).toString();
            String nombreLibro = tablaLibros.getModel().getValueAt(renglon, 1).toString();
            String autor = tablaLibros.getModel().getValueAt(renglon, 2).toString();
            String precio = tablaLibros.getModel().getValueAt(renglon, 3).toString();
            String existencias = tablaLibros.getModel().getValueAt(renglon, 4).toString();
        }
    }

    private void modificarLibro(){
        if(this.idTexto.equals("")){
            mostrarMensaje("Debes seleccionar un registro en la tabla");
        }else{
            //Verificamos que nobre del libro no sea nulo
            if (libroTextoTextField.getText().equals("")) {

                mostrarMensaje("Digite el nombre del libro...");
                libroTextoTextField.requestFocusInWindow();
                return;
            }
            //Llenamos el objeto libroa actualizar
            int idLibro = Integer.parseInt(idTexto.getText());
            var nombreLibro = libroTextoTextField.getText();
            var autor = libroTextoTextField.getText();
            var precio = precioTextoTextField.getText();
            var existencias = Integer.parseInt(existenciaTextoTextField.getText());
            var libro = new Libro(idLibro, nombreLibro, autor, precio, existencias);
            libroServicio.guardarLibro(libro);
            mostrarMensaje("Se modifico el libro...");
            limpiarFormulario();
            listarLibros();
        }
    }

    private void eliminarLibro(){
        var renglon = tablaLibros.getSelectedRow();
        if(renglon != -1){
            String idLibro = tablaLibros.getModel().getValueAt(renglon, 0).toString();
            var libro = new Libro();
            libro.setIdLibro(Integer.parseInt(idLibro));
            libroServicio.eliminarLibro(libro);
            mostrarMensaje("Libro "+idLibro+" ELIMINADO");
            limpiarFormulario();
            listarLibros();
        }else {
            mostrarMensaje("No se ha seleccionado ningun libro de la tabla a eliminar");
        }
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
        idTexto  = new JTextField("");
        idTexto.setVisible(false);
        this.tablaModeloLibros = new DefaultTableModel(0, 5){
            @Override
            public boolean isCellEditable(int row, int com){
                return false;
            }
        };
        String[] cabecera = {"id", "Libro", "Autor", "Precio", "Existencia"};
        this.tablaModeloLibros.setColumnIdentifiers(cabecera);
        //Instanciar el objeto de JTable
        this.tablaLibros = new JTable(tablaModeloLibros);
        //Evitamos que se seleccionen varios registros
        tablaLibros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
