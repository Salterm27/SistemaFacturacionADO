package domain.vista;

import domain.controlador.ControllerProducto;
import domain.controlador.ControllerProveedor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreacionProducto {
    private JPanel panelPrincipal;
    private JTextField textField1;
    private JComboBox comboBox1;
    private JButton asociarPoductoYProveedorButton;
    private JButton aceptarButton;
    private JButton cancelarButton;
    //private static ControllerProducto cldrProducto;
    private ControllerProducto cldrProducto;
    private ControllerProveedor cldrProveedor;


    public CreacionProducto() {
        this.cldrProducto = ControllerProducto.getInstance();
        this.cldrProveedor = ControllerProveedor.getInstance();
        //this.cldrProducto = cldrProducto;

        asociarPoductoYProveedorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                arrancarAsociacionProducto();
            }

        });
    }


    public void start(){
        JFrame frame = new JFrame("Creacion de Producto");
        frame.setContentPane( new CreacionProducto().panelPrincipal);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }

    private void arrancarAsociacionProducto() {
        AsociacionProducto frame = new AsociacionProducto();
        frame.start();
    }
}
