package domain.vista;

import domain.controlador.ControllerProducto;
import domain.controlador.ControllerProveedor;

import javax.swing.*;

public class AsociacionProducto {
    private JPanel panelPrincipal;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JTextField textField1;
    private JComboBox comboBox3;
    private JTextField textField2;
    private JButton asociarProductoButton;
    private JButton cancelarButton;
    private ControllerProducto cldrProducto;
    private ControllerProveedor cldrProveedor;

    public AsociacionProducto(){
        this.cldrProducto = ControllerProducto.getInstance();
        this.cldrProveedor = ControllerProveedor.getInstance();
    }

    public void start(){
        JFrame frame = new JFrame("Asociacion de Productos");
        frame.setContentPane( new AsociacionProducto().panelPrincipal);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }


}
