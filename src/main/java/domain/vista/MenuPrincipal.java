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

        creacionDeProductosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                arrancarCreacionProducto();
            }


        });

        buttonCompulsa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("IMPRESION DE PREUBA");
                arrancarCompulsaDePrecios();
            }
        });

        consultasGeneralesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                arrancarConsultasGenerales();
            }
        });
    }

    public void start(){

        frame.setContentPane( new MenuPrincipal().panelPrincipal);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(800,400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        //asociarEventos();
    }

    //metodo para meter todas las acciones de los botones de la pantalla Menu
   /* private void asociarEventos() {

        buttonProveedores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("marquillo");
                AltaProveedor frame= new AltaProveedor(cldrProveedor);
                frame.start();



            }
        });


    }*/


    private void arrancarAltaProveedor(){
        AltaProveedor frame= new AltaProveedor();
        this.frame.setVisible(false);
        frame.start();

    }

    private void arrancarAltaDocumentos() {

        AltaDocumento frame=new AltaDocumento();
        frame.start();
    }

    private void arrancarCreacionProducto() {

        CreacionProducto frame = new CreacionProducto();
        frame.start();
    }

    private void arrancarCompulsaDePrecios(){
        CompulsaDePrecios frame = new CompulsaDePrecios();
        frame.start();
    }

    private void arrancarConsultasGenerales(){
        ConsultasGenerales frame = new ConsultasGenerales();
        frame.start();
    }


}
