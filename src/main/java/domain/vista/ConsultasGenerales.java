package domain.vista;



import com.toedter.calendar.JDateChooser;
import domain.controlador.ControllerProducto;
import domain.controlador.ControllerProveedor;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class ConsultasGenerales {
    private JPanel panelPrincipal;
    private JButton CONSULTACUENTACORRIENTEButton;
    private JButton CONSULTADEFACTURASPORButton;


    public ConsultasGenerales() {


        CONSULTACUENTACORRIENTEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConsultaCuentaCorriente ccc = new ConsultaCuentaCorriente();
                ccc.start();
            }
        });
        CONSULTADEFACTURASPORButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConsultaFactura cf = new ConsultaFactura();
                cf.start();
            }
        });
    }

    public void start(){
        JFrame frame = new JFrame("Consultas Generales");
        frame.setContentPane( new ConsultasGenerales().panelPrincipal);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(300,300);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);

    }
}

