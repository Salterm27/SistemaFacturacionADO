package domain.vista;

import domain.controlador.ControllerProducto;
import domain.controlador.ControllerProveedor;

import javax.swing.*;

public class CreacionProducto {
    private JPanel panelPrincipal;
    private JTextField textField1;
    private JComboBox comboBox1;
    private JButton asociarPoductoYProveedorButton;
    private JButton aceptarButton;
    private JButton cancelarButton;
    private static ControllerProducto cldrProducto;


    public CreacionProducto(ControllerProducto cldrProducto) {
        this.cldrProducto = cldrProducto;

    }


    public void start(){
        JFrame frame = new JFrame("Creacion de Producto");
        frame.setContentPane( new CreacionProducto(cldrProducto).panelPrincipal);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }
}
