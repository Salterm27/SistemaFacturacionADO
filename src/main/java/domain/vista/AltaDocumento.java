package domain.vista;

import domain.controlador.ControllerProducto;
import domain.controlador.ControllerProveedor;
import domain.modelo.producto.ProductoSeleccionable;
import domain.modelo.proveedores.Proveedor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;


public class AltaDocumento {
    private JComboBox buscarProveedor;
    private JComboBox tipoDocBox;
    private JTable table1;
    private JComboBox buscarItem;
    private JSpinner cantidadPedida;
    private JButton agregarItemButton;
    private JButton generarDocumentoButton;
    private JLabel labelProveedor;
    private JButton CANCELARButton;
    private JPanel panelDoc;
    private JLabel labelFecha;
    private JLabel labelTotal;
    private JButton crearProveedorButton;
    private ControllerProveedor cldrProveedor;
    private ControllerProducto cldrProducto;
    public AltaDocumento(ControllerProveedor cldrProveedor, ControllerProducto cldrProducto){
        this.cldrProveedor = cldrProveedor;
        this.cldrProducto = cldrProducto;

        labelFecha.setText("Fecha: "+ LocalDate.now().toString());
        tipoDocBox.addItem("Factura");
        tipoDocBox.addItem("Nota de Credito");
        tipoDocBox.addItem("Nota de Debito");
        tipoDocBox.addItem("Orden de compra");
        tipoDocBox.addItem("Orden de pago");
        labelTotal.setText("0");

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nombre");
        model.addColumn("Precio");
        model.addColumn("Cantidad");
        model.addColumn("Total");

        table1.setModel(model);

        setItemsProveedor();
        agregarItemButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                // model.addRow(new Object[]{"item1","200","2","400"})
                String[] selecc = buscarItem.getSelectedItem().toString().split("Precio:");
                int cant = (Integer)cantidadPedida.getValue();
                Double valorTotal = ( Double.parseDouble(selecc[1]) * cant);
                model.addRow(new Object[]{
                        selecc[0],
                        selecc[1],
                        cant,
                        Double.toString(valorTotal)
                });
                labelTotal.setText(Double.toString(Double.parseDouble(labelTotal.getText()) + valorTotal));
            }
        });
        buscarProveedor.addActionListener (new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setItemsToSeach();
            }
        });
    }
    public void start(){
        JFrame frame = new JFrame("Alta Proveedor");
        frame.setContentPane( new AltaDocumento(cldrProveedor, cldrProducto).panelDoc);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

    private void setItemsProveedor(){
        for(Proveedor p: cldrProveedor.getProveedores() ){
            buscarProveedor.addItem(p.getNombreFantasia() + ", cuit:" +p.getCuit());
        }
    }

    private void setItemsToSeach(){
        int cuit = Integer.valueOf(buscarProveedor.getSelectedItem().toString().split(" cuit:")[1]);
        buscarItem.removeAllItems();
        System.out.println(cuit);
        for (ProductoSeleccionable ps: cldrProveedor.getProveedorXcuit(cuit).getProductosSeleccionables()){
            buscarItem.addItem ( ps.getProducto().getNombre() +
                    " <" + ps.getProducto().getRubro().getNombre() + "> Precio:" +
                    (ps.getPrecioPorUnidad() + ((ps.getPrecioPorUnidad() * ps.getProducto().getIva())/100)));
        }
    }
}
