package domain.vista;

import com.toedter.calendar.JDateChooser;
import domain.controlador.ControllerProducto;
import domain.controlador.ControllerProveedor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;


public class AltaProveedor extends JFrame{

    private JPanel PanelDeProveedor;
    private JTextField textRazonSocial;
    private JTextField textNombreFantasia;
    private JTextField textCuit;
    private JComboBox textIVA;
    private JTextField textIIBB;
    private JTextField textCalle;
    private JTextField textCiudad;
    private JTextField textTelefono;
    private JTextField textElectronico;
    private JButton aceptarButton;
    private JLabel calle;
    private JLabel ciudad;
    private JLabel telefono;
    private JLabel correoElectronico;
    private JPanel calendarPanel;
    private JDateChooser fechaActividad;
    private ControllerProveedor cldrProveedor;
    private ControllerProducto cldrProducto;
    private AltaProveedor self;
    private JFrame frame;

    public AltaProveedor(){
        this.cldrProveedor = ControllerProveedor.getInstance();
        this.cldrProducto = ControllerProducto.getInstance();// por ahora se crea desp se recibe como param
        this.asociarEventos();

        Calendar cld = Calendar.getInstance();
        fechaActividad = new JDateChooser(cld.getTime());
        calendarPanel.add(fechaActividad);
        fechaActividad.setDateFormatString("dd/MM/yyyy");


        LocalDate date = LocalDate.ofInstant(fechaActividad.getDate().toInstant(), ZoneId.systemDefault());
        System.out.println(date);


    }

    /*public static void main (String args[]){
        JFrame frame = new JFrame("Alta Proveedor");
        frame.setContentPane( new AltaProveedor(cldrProveedor).PanelDeProveedor);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }*/

    public void start(){
        //this.frame = new JFrame("Alta Proveedor");
        this.setContentPane(new AltaProveedor().PanelDeProveedor);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    private int StringtoNum(String x){
        return Integer.valueOf(x);
    }

    private void altaProveedor(){

        cldrProveedor.addProveedor(StringtoNum(textCuit.getText()),textIVA.getActionCommand(),textRazonSocial.getText(),
                textNombreFantasia.getText(),textCalle.getText(),StringtoNum(textTelefono.getText()),
                textElectronico.getText(), Double.parseDouble(textIIBB.getText()), LocalDate.ofInstant(fechaActividad.getDate().toInstant(), ZoneId.systemDefault()), 1);

        /*int cuit, String responsabilidadIVA, String razonSocial,
                String nombreFantasia, String direccion, int telefono,
        String correoElectronico, int nroIIBB, LocalDate inicioActividad,
        int retencionImpuestos*/

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    private void asociarEventos() {
        aceptarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                altaProveedor();
                JOptionPane.showMessageDialog(null,"Se creo el Proveedor:" + " " + textRazonSocial.getText());

            }
        });


    }



}
