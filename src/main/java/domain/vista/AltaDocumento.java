package domain.vista;

import com.toedter.calendar.JDateChooser;
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
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


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
    private JPanel crearCheque;
    private JPanel fechaEmisionCheque;
    private JPanel fechaVigenciaCheque;
    private JTextField firmaCheque;
    private JTextField montoCheque;
    private JCheckBox pagoConChequeCheckBox;
    private JButton autocompletarConTotalButton;
    private ControllerProveedor cldrProveedor;
    private ControllerProducto cldrProducto;
    private Proveedor proveedor;
    private DefaultTableModel model;
    private List<Integer> FAsociadasAOP;

    JDateChooser fechaEmision;
    JDateChooser fechaVigencia;
    LocalDate datefechaVigencia;
    LocalDate datefechaEmision;

    public AltaDocumento(){
        this.cldrProducto = ControllerProducto.getInstance();
        this.cldrProveedor = ControllerProveedor.getInstance();
        aprobacionCheckBox.setVisible(false);
        mostrarOrdenesDeCompraAsociadas(false);
        mostrarFacturasAsociadas(false);
        pagoConChequeCheckBox.setVisible(false);
        Calendar cld = Calendar.getInstance();
        fechaEmision = new JDateChooser(cld.getTime());
        fechaVigencia = new JDateChooser(cld.getTime());
        fechaEmisionCheque.add(fechaEmision);fechaVigenciaCheque.add(fechaVigencia);
        fechaVigencia.setDateFormatString("dd/MM/yyyy");
        fechaEmision.setDateFormatString("dd/MM/yyyy");
        crearCheque.setVisible(false);
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
                if(aprobacionCheckBox.isSelected()){generarDocumentoButton.setEnabled((true));}
                else{ generarDocumentoButton.setEnabled((false));}
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
                pagoConChequeCheckBox.setVisible(false);
                crearCheque.setVisible(false);
                esFactura();

                FAsociadasAOP = new ArrayList();
                esOP();
                esNCoND();

            }

        });
        generarDocumentoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Item> detalle = new ArrayList<>();
                boolean docWasOk = false;// VERIFICACION PARA DOCUMENTO OK

                //RECORRE CADA FILA DE LA TABLA, BUSCA EL PRODUCTO SELECCIONABLE SEGUN EL NOMBRE
                //Y GENERA UN ITEM QUE SE AGREGA AL DETALLE.
                for (int i = 0; i < model.getRowCount(); i++) {
                    ProductoSeleccionable ps = buscarPs(model.getValueAt(i, 0).toString());
                    int cant = Integer.valueOf(model.getValueAt(i, 2).toString());
                    Item item = new Item(ps, cant);
                    //SE AGREGA CADA ITEM RECUPERADO, AL DETALLE GENERAL QUE SE USARA EN LA GENERACION DE DOCUMENTOS.
                    detalle.add(item);
                }

                if (tipoDocBox.getSelectedItem().toString() == "Factura") {
                    int OrdenCompra = -1;
                    if(ordenesdecompra.getSelectedItem().toString()!=""){
                        try {
                            OrdenCompra = Integer.valueOf((ordenesdecompra.getSelectedItem().toString().split("-")[0]));
                        } catch (NullPointerException ex) {
                            OrdenCompra = -1;
                        }
                    }

                    if ( OrdenCompra == -1 && !aprobacionCheckBox.isSelected()){
                        docWasOk = false;
                        aprobacionCheckBox.setVisible(true);
                    }

                    else{
                        cldrProveedor.addFactura(
                                proveedor.getCuit(), false,
                                OrdenCompra,
                                detalle);
                        proveedor.addDeudaCorriente(Double.valueOf(labelTotal.getText()));
                        aprobacionCheckBox.setSelected(false);
                        aprobacionCheckBox.setVisible(false);
                        docWasOk = true;
                    }

                }

                if (tipoDocBox.getSelectedItem().toString() == "Nota de Credito") {
                    int fcsAsociadas = -1;

                    if(facturasAsociadas.getSelectedItem().toString()!=""){
                        try {
                            fcsAsociadas = Integer.valueOf((facturasAsociadas.getSelectedItem().toString().split("-")[0]));
                        } catch (NullPointerException ex) {
                            fcsAsociadas = -1;
                        }
                    }

                    cldrProveedor.addNotadeCredito(fcsAsociadas,proveedor.getCuit(),detalle);
                    docWasOk = true;

                }

                if (tipoDocBox.getSelectedItem().toString() == "Nota de Debito") {
                    int fcsAsociadas = -1;

                    if(facturasAsociadas.getSelectedItem().toString()!=""){
                        try {
                            fcsAsociadas = Integer.valueOf((facturasAsociadas.getSelectedItem().toString().split("-")[0]));
                        } catch (NullPointerException ex) {
                            fcsAsociadas = -1;
                        }
                    }

                    cldrProveedor.addNotadeDebito(fcsAsociadas,proveedor.getCuit(),detalle);
                    docWasOk = true;

                }

                if (tipoDocBox.getSelectedItem().toString() == "Orden de compra") {
                    if (proveedor.getLimiteDeuda() >
                            Double.valueOf(labelTotal.getText()) + proveedor.getdeudaCorriente() || aprobacionCheckBox.isSelected()) {
                        //validacion OK Orden de compra
                        cldrProveedor.addOrdenDeCompra(proveedor.getCuit(), detalle);
                        docWasOk = true;
                    } else {
                        //Validacion NOK orden de Compra
                        aprobacionCheckBox.setVisible((true));
                        generarDocumentoButton.setEnabled((false));
                        pagoConChequeCheckBox.setVisible(false);
                        crearCheque.setVisible(false);

                    }

                }

                if (tipoDocBox.getSelectedItem().toString() == "Orden de pago") {

                    if(pagoConChequeCheckBox.isSelected()){
                        datefechaVigencia = LocalDate.ofInstant(fechaVigencia.getDate().toInstant(), ZoneId.systemDefault());
                        datefechaEmision = LocalDate.ofInstant(fechaEmision.getDate().toInstant(), ZoneId.systemDefault());
                        List FAsociadasclone = new ArrayList();
                        for(Integer i : FAsociadasAOP){
                            FAsociadasclone.add(i);
                        }
                        cldrProveedor.addOrdenDePago(
                                proveedor.getCuit(), detalle, Double.valueOf(labelTotal.getText()), 0 ,LocalDate.now(), FAsociadasclone,
                                datefechaEmision,  datefechaVigencia,  firmaCheque.getText(),  Double.parseDouble(montoCheque.getText())
                        );
                    }
                    else{
                        cldrProveedor.addOrdenDePago(
                                proveedor.getCuit(), detalle, Double.valueOf(labelTotal.getText()), 0 ,LocalDate.now(), FAsociadasAOP,
                                null,  null,  null, null
                        );
                    }
                    docWasOk = true;
                }

                if (docWasOk){
                    model.getDataVector().removeAllElements();
                    model.fireTableDataChanged();
                    labelTotal.setText("0");
                    aprobacionCheckBox.setSelected(false);
                    aprobacionCheckBox.setVisible(false);
                    FAsociadasAOP.clear();
                    JOptionPane.showMessageDialog(null, "Se genero un Documento de tipo: " + " " + tipoDocBox.getSelectedItem().toString());
                    docWasOk = false;
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
        facturasAsociadas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String factAsc = facturasAsociadas.getSelectedItem().toString() ;
                if ( factAsc.length() > 2){
                    table1.setModel(cldrProveedor.RellenarDetalleFactura_aOrdenDePago(
                        proveedor,
                        Integer.valueOf(factAsc.split("-")[0]),
                        model));
                    FAsociadasAOP.add(Integer.valueOf(factAsc.split("-")[0]));
                    recalcularTotalDesdeModelo();
                }

                for(Integer i: FAsociadasAOP){
                    System.out.println("Asociado a documento:" + i);
                }
            }
        });
        ordenesdecompra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // RellenarDetalleOrdenDeCompra_aFactura
                try{
                    String OCAsc = ordenesdecompra.getSelectedItem().toString() ;
                    if ( OCAsc.length() > 2){
                        table1.setModel(cldrProveedor.RellenarDetalleOrdenDeCompra_aFactura(
                                proveedor,
                                Integer.valueOf(OCAsc.split("-")[0]),
                                model));
                        recalcularTotalDesdeModelo();
                    }
                }catch (NullPointerException n){

                }
            }
        });
        crearProveedorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                arrancarAltaProveedor();
            }
        });
        pagoConChequeCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearCheque.setVisible(pagoConChequeCheckBox.isSelected());
            }
        });
        autocompletarConTotalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                montoCheque.setText(labelTotal.getText());
            }
        });
    }
    private void recalcularTotalDesdeModelo(){
        double total = 0;
        for (int i = 0; i<model.getRowCount(); i++){
            total = total + Double.parseDouble(model.getValueAt(i,3).toString());
        }
        labelTotal.setText(String.valueOf(total));
    }
    private void esFactura() {
        if(tipoDocBox.getSelectedItem().toString() == "Factura"){
            mostrarOrdenesDeCompraAsociadas(true);
            if(proveedor!=null){
                ordenesdecompra.removeAllItems();
                ordenesdecompra.addItem("");
                for(OrdenDeCompra oc: proveedor.getOrdenesdecompra()){
                    ordenesdecompra.addItem(oc.getNumeroDocumento() + "-" + oc.getFecha().toString() +" $"+ oc.getMonto() );
                }
            }
        }
    }

    private void esNCoND(){
        if (tipoDocBox.getSelectedItem().toString() == "Nota de Credito"
                || tipoDocBox.getSelectedItem().toString() == "Nota de Debito"){

            mostrarFacturasAsociadas(true);
            if (proveedor != null) {
                facturasAsociadas.removeAllItems();
                facturasAsociadas.addItem("");
                for (Factura f : proveedor.getFacturas()) {
                    facturasAsociadas.addItem(f.getNumeroDocumento() + "-" + f.getFecha() + " $" + f.getMonto());
                    proveedor.substractDeudaCorriente(f.getMonto());
                }
            }
        }

    }

    private void esOP(){
        if (tipoDocBox.getSelectedItem().toString() == "Orden de pago") {
            pagoConChequeCheckBox.setVisible(true);

            mostrarFacturasAsociadas(true);
            if (proveedor != null) {
                facturasAsociadas.removeAllItems();
                facturasAsociadas.addItem("");
                for (Factura f : proveedor.getFacturas()) {
                    facturasAsociadas.addItem(f.getNumeroDocumento() + "-" + f.getFecha() + " $" + f.getMonto());
                    proveedor.substractDeudaCorriente(f.getMonto());
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
        try{
            facturasAsociadas.removeAllItems();
            facturasAsociadas.setVisible(select);
            labelFacturasAsociadas.setVisible(select);
        }catch (NullPointerException n){

        }
    }

    private void arrancarAltaProveedor(){
        AltaProveedor fr= new AltaProveedor();
        fr.start();
    }

    private void setItemsToSeach(){
        int cuit = Integer.valueOf(buscarProveedor.getSelectedItem().toString().split(" cuit:")[1]);
        this.proveedor =  cldrProveedor.getProveedorXcuit(cuit);
        buscarItem.removeAllItems();
        System.out.println(cuit);
        if (proveedor!=null){
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
}

