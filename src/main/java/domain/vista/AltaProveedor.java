package domain.vista;

import javax.swing.*;

public class AltaProveedor {
    private JPanel panel;
    private JTextField textRazonSocial;
    private JTextField textNombreFantasia;
    private JTextField textCuit;
    private JTextField textIVA;
    private JTextField textIIBB;
    private JTextField textActividad;
    private JTextField textCalle;
    private JTextField textNumero;
    private JTextField textCiudad;
    private JTextField textTelefono;
    private JTextField textElectronico;
    private JButton aceptarButton;
    private JButton cancelarButton;
    private JLabel rSocial;
    private JLabel nFantasia;
    private JLabel cuit;
    private JLabel rIVA;
    private JLabel nIIBB;
    private JLabel iActividad;
    private JLabel calle;
    private JLabel numero;
    private JLabel ciudad;
    private JLabel telefono;
    private JLabel correoElectronico;

    public AltaProveedor(){

    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("Alta Proveedor");
        frame.setContentPane( new AltaProveedor().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }


}
