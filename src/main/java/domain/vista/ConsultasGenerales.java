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
    private JDateChooser fechaDesde;
    private ControllerProducto cldrProducto;
    private ControllerProveedor cldrProveedor;

    public ConsultasGenerales() {
        this.cldrProveedor = ControllerProveedor.getInstance();
        this.cldrProducto = ControllerProducto.getInstance();

    }

    public void start(){
        JFrame frame = new JFrame("Consultas Generales");
        frame.setContentPane( new ConsultasGenerales().panelPrincipal);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);

    }
}

