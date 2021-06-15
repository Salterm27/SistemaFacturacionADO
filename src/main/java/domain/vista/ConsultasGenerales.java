package domain.vista;



import com.toedter.calendar.JDateChooser;
import domain.controlador.ControllerProducto;
import domain.controlador.ControllerProveedor;

import javax.swing.*;
import java.util.Calendar;

public class ConsultasGenerales {
    private JPanel panelPrincipal;
    private JTabbedPane tabbedPane1;
    private JComboBox comboBox1;
    private JTable table1;
    private JButton GENERARButton;
    private JPanel calendario;
    //private JDateChooser fechaDesde;
    private ControllerProveedor cldrProveedor;
    private ControllerProducto cldrProducto;

    public ConsultasGenerales(ControllerProveedor cldrProveedor, ControllerProducto cldrProducto) {

        this.cldrProveedor = cldrProveedor;
        this.cldrProducto = cldrProducto;
    }

    public void start(){
        JFrame frame = new JFrame("Consultas Generales");
        frame.setContentPane( new ConsultasGenerales(cldrProveedor, cldrProducto).panelPrincipal);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);

    }
}

