package domain.vista;

import domain.controlador.ControllerProducto;
import domain.controlador.ControllerProveedor;
import domain.modelo.documentos.Factura;
import domain.modelo.documentos.Item;
import domain.modelo.documentos.OrdenDeCompra;
import domain.modelo.producto.ProductoSeleccionable;
import domain.modelo.proveedores.Proveedor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AltaDocumento {
    private JComboBox buscarProveedor;
    private JComboBox tipoDocBox;
    private JTable table1;
    private JComboBox buscarItem;
    private JSpinner cantidadPedida;
    private JButton agregarItemButton;
    private JButton generarDocumentoButton;
    private JLabel labelProveedor;
    private JPanel panelDoc;
    private JLabel labelFecha;
    private JLabel labelTotal;
    private JButton crearProveedorButton;
    private JComboBox ordenesdecompra;
    private JLabel labelOCasociada;
    private JComboBox facturasAsociadas;
    private JLabel labelFacturasAsociadas;
    private JLabel fechaHoy;
    private JCheckBox aprobacionCheckBox;
    private ControllerProveedor cldrProveedor;
    private ControllerProducto cldrProducto;
    private Proveedor proveedor;
    private DefaultTableModel model;

    public AltaDocumento(){
        this.cldrProducto = ControllerProducto.getInstance();
        this.cldrProveedor = ControllerProveedor.getInstance();

        mostrarOrdenesDeCompraAsociadas(false);
        mostrarFacturasAsociadas(false);

        fechaHoy.setText("Fecha: "+ LocalDate.now().toString());
        tipoDocBox.addItem("");
        tipoDocBox.addItem("Factura");
        tipoDocBox.addItem("Nota de Credito");
        tipoDocBox.addItem("Nota de Debito");
        tipoDocBox.addItem("Orden de compra");
        tipoDocBox.addItem("Orden de pago");
        labelTotal.setText("0");

        model = new DefaultTableModel();
        model.addColumn("Nombre");
        model.addColumn("Precio");
        model.addColumn("Cantidad");
        model.addColumn("Total");

        table1.setModel(model);

        buscarProveedor.addItem("");
        buscarProveedor = cldrProveedor.comboProveedor(buscarProveedor);

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
        aprobacionCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(aprobacionCheckBox.isSelected()){
                    generarDocumentoButton.setEnabled((true));
                }
                else{
                    generarDocumentoButton.setEnabled((false));
                }
            }
        });
        buscarProveedor.addActionListener (new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.getDataVector().removeAllElements();
                model.fireTableDataChanged();
                labelTotal.setText("0");
                setItemsToSeach();
                esOP();
                esFactura();
            }
        });
        tipoDocBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarFacturasAsociadas(false);
                mostrarOrdenesDeCompraAsociadas(false);
                esFactura();
                esOP();
            }

        });
        generarDocumentoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Item> detalle = new ArrayList<>();
                boolean docWasOk = false;
                for (int i = 0; i < model.getRowCount(); i++) {
                    ProductoSeleccionable ps = buscarPs(model.getValueAt(i, 0).toString());
                    int cant = Integer.valueOf(model.getValueAt(i, 2).toString());
                    Item item = new Item(ps, cant);
                    detalle.add(item);
                }

                if (tipoDocBox.getSelectedItem().toString() == "Factura") {
                    int OrdenCompra = -1;
                    try {
                        OrdenCompra = Integer.valueOf((ordenesdecompra.getSelectedItem().toString().split(" ")[0]));
                    } catch (NullPointerException ex) {
                        OrdenCompra = -1;
                    }
                    cldrProveedor.addFactura(
                            proveedor.getCuit(), false,
                            OrdenCompra,
                            detalle);
                }
                if (tipoDocBox.getSelectedItem().toString() == "Orden de compra") {
                    if (proveedor.getLimiteDeuda() >
                            Double.valueOf(labelTotal.getText()) + proveedor.getdeudaCorriente() || aprobacionCheckBox.isSelected()) {
                        //validacion OK Orden de compra
                        System.out.print("genero orden");
                        cldrProveedor.addOrdenDeCompra(proveedor.getCuit(), detalle);
                        docWasOk = true;
                    } else {
                        //Validacion NOK orden de Compra
                        System.out.print("no genero orden");
                        aprobacionCheckBox.setVisible((true));
                        generarDocumentoButton.setEnabled((false));
                    }
                    model.getDataVector().removeAllElements();
                    model.fireTableDataChanged();
                    labelTotal.setText("0");




                }

                if (docWasOk){
                    JOptionPane.showMessageDialog(null, "Se genero un Documento de tipo: " + " " + tipoDocBox.getSelectedItem().toString());
                }
                else {
                    JOptionPane.showMessageDialog(null, "No se puede generar un Documento");

                }


            }

                private ProductoSeleccionable buscarPs (String nombre){
                    nombre = nombre.split(" <")[0];
                    for (ProductoSeleccionable ps : proveedor.getProductosSeleccionables()) {
                        if (ps.getProducto().getNombre().equals(nombre)) {
                            return ps;
                        }
                    }
                    return null;
                }
            });




    }
    private void esFactura() {
        if(tipoDocBox.getSelectedItem().toString() == "Factura"){
            mostrarOrdenesDeCompraAsociadas(true);
            if(proveedor!=null){
                ordenesdecompra.removeAllItems();
                for(OrdenDeCompra oc: proveedor.getOrdenesdecompra()){
                    ordenesdecompra.addItem(oc.getNumeroDocumento() + " " + oc.getFecha().toString() +" $"+ oc.getMonto() );
                }
            }
        }
    }
    private void esOP(){
        if (tipoDocBox.getSelectedItem().toString() == "Orden de pago") {
            mostrarFacturasAsociadas(true);
            if (proveedor != null) {
                for (Factura f : proveedor.getFacturas()) {
                    facturasAsociadas.addItem(f.getNumeroDocumento() + "-" + f.getFecha() + " $" + f.getMonto());
                }
            }
        }
    }

    public void start(){
        JFrame frame = new JFrame("Alta Documento");
        frame.setContentPane( new AltaDocumento().panelDoc);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    private void mostrarOrdenesDeCompraAsociadas(boolean select){
        ordenesdecompra.setVisible(select);
        labelOCasociada.setVisible(select);
    }
    private void mostrarFacturasAsociadas(boolean select){
        facturasAsociadas.removeAllItems();
        facturasAsociadas.setVisible(select);
        labelFacturasAsociadas.setVisible(select);
    }



    private void setItemsToSeach(){
        int cuit = Integer.valueOf(buscarProveedor.getSelectedItem().toString().split(" cuit:")[1]);
        this.proveedor =  cldrProveedor.getProveedorXcuit(cuit);
        buscarItem.removeAllItems();
        System.out.println(cuit);
        for (ProductoSeleccionable ps: proveedor.getProductosSeleccionables()){
            buscarItem.addItem (
                    ps.getProducto().getNombre() +
                    " <" + ps.getProducto().getRubro().getNombre() + "> " +
                            "Precio:" +
                    ps.getPrecioPorUnidad());
            //TO DO - No calcular el IVA aca sino en el final de la OP
        }
    }
}

