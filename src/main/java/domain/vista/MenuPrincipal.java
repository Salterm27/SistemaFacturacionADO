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
    private ControllerProducto cldrProducto;
    private ControllerProveedor cldrProveedor;

    public MenuPrincipal(ControllerProducto cldrProducto, ControllerProveedor cldrProveedor){
        this.cldrProducto = cldrProducto;
        this.cldrProveedor = cldrProveedor;


    }

    public void start(){
        JFrame frame = new JFrame("Menu Inicial");
        frame.setContentPane( new MenuPrincipal(cldrProducto, cldrProveedor).panelPrincipal);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setSize(800,400);
        frame.setResizable(false);
    }

    //metodo para meter todas las acciones de los botones de la pantalla Menu
    private void asociarEventos() {

        buttonProveedores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


    }

}
