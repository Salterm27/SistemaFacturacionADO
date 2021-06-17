package domain.vista;

import domain.controlador.ControllerProducto;
import domain.controlador.ControllerProveedor;

import javax.swing.*;

public class CompulsaDePrecios  {
    private JPanel panelPrincipal;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JButton consultarButton;
    private JButton cancelarButton;
    private ControllerProducto cldrProducto;
    private ControllerProveedor cldrProveedor;

    public CompulsaDePrecios(){
        this.cldrProveedor = ControllerProveedor.getInstance();
        this.cldrProducto = ControllerProducto.getInstance();

    }

    public void start(){
        JFrame frame = new JFrame("Compulsa de Precios");
        frame.setContentPane( new CompulsaDePrecios().panelPrincipal);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }



}
