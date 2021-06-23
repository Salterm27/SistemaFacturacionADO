package domain.vista;

import domain.controlador.ControllerProducto;
import domain.controlador.ControllerProveedor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipal {
    private JPanel panelPrincipal;
    private JPanel panelTitulo;
    private JPanel panelMenu;
    private JButton buttonProveedores;
    private JButton buttonCompulsa;
    private JButton buttonDocumentos;
    private JButton creacionDeProductosButton;
    private JButton consultasGeneralesButton;
    private ControllerProducto cldrProducto;
    private ControllerProveedor cldrProveedor;
    private MenuPrincipal self;
    private JFrame frame;

    public MenuPrincipal(){
        this.cldrProducto = ControllerProducto.getInstance();
        this.cldrProveedor = ControllerProveedor.getInstance();
        frame = new JFrame("Menu Inicial");

        buttonProveedores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                arrancarAltaProveedor();
            }
        });

        buttonDocumentos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                arrancarAltaDocumentos();
            }
        });

        buttonCompulsa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                arrancarCompulsaDePrecios();

            }
        });

        creacionDeProductosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                arrancarCreacionDeProductos();
            }
        });

        consultasGeneralesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                arrancarConsultasGenerales();
            }
        });
    }

    private void arrancarConsultasGenerales() {
        ConsultasGenerales cg = new ConsultasGenerales();
        cg.start();
    }

    public void start(){

        frame.setContentPane( new MenuPrincipal().panelPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(800,400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        }

    private void arrancarAltaProveedor(){
        AltaProveedor frame= new AltaProveedor();
        this.frame.setVisible(false);
        frame.start();

    }

    private void arrancarAltaDocumentos() {

        AltaDocumento frame=new AltaDocumento();
        frame.start();
    }

    private void arrancarCompulsaDePrecios() {

        CompulsaDePrecios frame = new CompulsaDePrecios();
        frame.start();
    }

    private void arrancarCreacionDeProductos(){
        CreacionProducto frame = new CreacionProducto();
        frame.start();
    }

}
