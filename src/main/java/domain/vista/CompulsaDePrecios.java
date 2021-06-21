package domain.vista;

import domain.controlador.ControllerProducto;
import domain.controlador.ControllerProveedor;
import domain.modelo.producto.Producto;
import domain.modelo.producto.ProductoSeleccionable;
import domain.modelo.producto.Rubro;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CompulsaDePrecios  {
    private JPanel panelPrincipal;
    private JComboBox comboRubro;
    private JComboBox comboProducto;
    private JButton consultarButton;
    private JButton cancelarButton;
    private JTable table1;
    private ControllerProducto cldrProducto;
    private ControllerProveedor cldrProveedor;
    private DefaultTableModel model;

    public CompulsaDePrecios(){
        this.cldrProducto = ControllerProducto.getInstance();

        model = new DefaultTableModel();
        model.addColumn("Proveedor");
        model.addColumn("Total");
        table1.setModel(model);

        comboRubro.addItem("");
        for(Rubro r:cldrProducto.getRubros()){
            comboRubro.addItem(r.getNombre());
        }
        comboRubro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comboProducto.removeAllItems();
                comboProducto.addItem("");
                for(Producto p:cldrProducto.getProductos()){
                    if(p.getRubro().getNombre() == comboRubro.getSelectedItem().toString()){
                        comboProducto.addItem(p.getNombre());
                    }
                }

            }
        });

        consultarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.getDataVector().removeAllElements();
                for(ProductoSeleccionable ps:cldrProducto.getProductoSeleccionables()){
                    if(ps.getProducto().getNombre()==comboProducto.getSelectedItem().toString()){
                        model.addRow(new Object[]{
                                ps.getProveedor().getNombreFantasia(),
                                ps.getPrecioPorUnidad()
                        });
                    }
                }
                table1.setAutoCreateRowSorter(true);
                model.fireTableDataChanged();
            }
        });
    }

    public void start(){
        JFrame frame = new JFrame("Compulsa de Precios");
        frame.setContentPane( new CompulsaDePrecios().panelPrincipal);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
    }



}
