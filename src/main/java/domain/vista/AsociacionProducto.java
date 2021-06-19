package domain.vista;

import domain.controlador.ControllerProducto;
import domain.controlador.ControllerProveedor;
import domain.modelo.producto.Producto;
import domain.modelo.producto.ProductoSeleccionable;
import domain.modelo.proveedores.Proveedor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AsociacionProducto {
    private JPanel panelPrincipal;
    private JComboBox comboTipoUnidad;
    private JComboBox comboProveedores;
    private JComboBox comboTipoIVA;
    private JTextField textPrecioUnidad;
    private JButton asociarProductoButton;
    private JButton cancelarButton;
    private JComboBox comboNombreProducto;
    private ControllerProducto cldrProducto;
    private ControllerProveedor cldrProveedor;


    public AsociacionProducto(){
        this.cldrProducto = ControllerProducto.getInstance();
        this.cldrProveedor = ControllerProveedor.getInstance();
        desplegarProducto();
        desplegarProveedores();
        int cuit = Integer.valueOf(comboProveedores.getSelectedItem().toString().split(", CUIT:")[1]);



        asociarProductoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int cuit = Integer.valueOf(comboProveedores.getSelectedItem().toString().split(", CUIT:")[1]);

                ProductoSeleccionable ps=cldrProducto.CrearProductoSeleccionable(Float.valueOf(textPrecioUnidad.getText()), comboTipoUnidad.getSelectedItem().toString(),
                        cldrProducto.getProducto(comboNombreProducto.getSelectedItem().toString()),
                        cldrProveedor.getProveedorXcuit( cuit),
                        Float.valueOf(comboTipoIVA.getSelectedItem().toString()));


                cldrProveedor.asociarProductoSeleccionable(cuit, ps);

                JOptionPane.showMessageDialog(null,"Se asocio el Producto: " + comboNombreProducto.getSelectedItem().toString() + "al Proveedor "
                + comboProveedores.getSelectedItem().toString());
            }
        });

    }

    public void start(){
        JFrame frame = new JFrame("Asociacion de Productos");
        frame.setContentPane( new AsociacionProducto().panelPrincipal);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }

    private void desplegarProducto(){

        for (Producto p: cldrProducto.getProductos()){
            comboNombreProducto.addItem(p.getNombre());
        }

    }

    private void desplegarProveedores(){

        for (Proveedor p: cldrProveedor.getProveedores()){
            comboProveedores.addItem(p.getRazonSocial() + ", CUIT:" + p.getCuit());
        }

    }

}
