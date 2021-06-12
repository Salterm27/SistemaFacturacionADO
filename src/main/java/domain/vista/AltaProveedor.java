package domain.vista;

import domain.controlador.ControllerProveedor;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;

public class AltaProveedor {
    private JPanel PanelDeProveedor;
    private JTextField textRazonSocial;
    private JTextField textNombreFantasia;
    private JTextField textCuit;
    private JComboBox textIVA;
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
    private ControllerProveedor cldrProveedor;

    public AltaProveedor(ControllerProveedor cldrProveedor){
        this.cldrProveedor = new ControllerProveedor(); // por ahora se crea desp se recibe como param
        aceptarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                altaProveedor();
            }
        });

    }

    public void start(){
        JFrame frame = new JFrame("Alta Proveedor");
        frame.setContentPane( new AltaProveedor(cldrProveedor).PanelDeProveedor);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private int StringtoNum(String x){
        return Integer.valueOf(x);
    }

    private void altaProveedor(){
        cldrProveedor.addProveedor(StringtoNum(textCuit.getText()),textIVA.getActionCommand(),textRazonSocial.getText(),
                textNombreFantasia.getText(),textCalle.getText(),StringtoNum(textTelefono.getText()),
                textElectronico.getText(), StringtoNum(textIIBB.getText()), LocalDate.now(), 1);

    }
}
