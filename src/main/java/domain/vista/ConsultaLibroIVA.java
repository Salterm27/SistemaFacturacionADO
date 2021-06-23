package domain.vista;

import domain.controlador.ControllerProveedor;
import domain.modelo.documentos.Factura;
import domain.modelo.documentos.NotaDeCredito;
import domain.modelo.documentos.NotaDeDebito;
import domain.modelo.documentos.OrdenDePago;
import domain.modelo.proveedores.Proveedor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConsultaLibroIVA {
    private JComboBox selecProveedor;
    private JTable table1;
    private JPanel panel;
    private JLabel labelSubTotal;
    private JLabel labelIVA;
    private JLabel LabelTotal;
    private JLabel labelCantDoc;
    private ControllerProveedor cldrProveedor;
    private Proveedor proveedorSeleccionado;
    public ConsultaLibroIVA() {
        this.cldrProveedor = ControllerProveedor.getInstance();

        setProveedor();
        DefaultTableModel modelTabla = new DefaultTableModel();
        modelTabla.addColumn("Documento");
        modelTabla.addColumn("Fecha emision");
        modelTabla.addColumn("SubTotal");
        modelTabla.addColumn("IVA");
        modelTabla.addColumn("Total");
        table1.setModel(modelTabla);


        selecProveedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int cuit = Integer.valueOf(selecProveedor.getSelectedItem().toString().split("cuit:")[1]);
                proveedorSeleccionado = cldrProveedor.getProveedorXcuit(cuit);
                if ( proveedorSeleccionado != null){
                    double Subtotal = 0;
                    double totaliva = 0;
                    int cantDocEmitidos = 0;
                    modelTabla.getDataVector().removeAllElements();
                    for (Factura f: proveedorSeleccionado.getFacturas()){
                        modelTabla.addRow(new Object[]{
                                f.getNumeroDocumento(), f.getFecha(),f.getMonto() - f.getIva(),f.getIva(),f.getMonto()});
                        totaliva = totaliva + f.getIva();
                        Subtotal = Subtotal + f.getMonto() - f.getIva();
                        cantDocEmitidos++;
                    }
                    for (NotaDeCredito nc: proveedorSeleccionado.getNotasdecredito()){
                        modelTabla.addRow(new Object[]{
                                nc.getNumeroDocumento(), nc.getFecha(),nc.getMonto() - nc.getIva(),nc.getIva(),nc.getMonto()});
                        totaliva = totaliva - nc.getIva();
                        Subtotal = Subtotal - nc.getMonto() - nc.getIva();
                        cantDocEmitidos++;
                    }
                    for (NotaDeDebito nd: proveedorSeleccionado.getNotasdedebito()){
                        modelTabla.addRow(new Object[]{
                                nd.getNumeroDocumento(), nd.getFecha(),nd.getMonto() - nd.getIva(),nd.getIva(),nd.getMonto()});
                        totaliva = totaliva + nd.getIva();
                        Subtotal = Subtotal + nd.getMonto() - nd.getIva();
                        cantDocEmitidos++;
                    }
                    for (OrdenDePago op: proveedorSeleccionado.getOrdenesdepago()){
                        modelTabla.addRow(new Object[]{
                                op.getNumeroDocumento(), op.getFecha(),op.getMonto() - op.getIva(),op.getIva(),op.getMonto()});
                        totaliva = totaliva - op.getIva();
                        Subtotal = Subtotal - op.getMonto() - op.getIva();
                        cantDocEmitidos++;
                    }
                    labelCantDoc.setText("Total documentos: " + cantDocEmitidos);
                    labelIVA.setText("IVA: " + totaliva);
                    LabelTotal.setText("Total: " + (totaliva+Subtotal));
                    labelSubTotal.setText("SubTotal: " + Subtotal);
                }
                modelTabla.fireTableDataChanged();
            }
        });
    }
    private void setProveedor(){
        this.selecProveedor.addItem("");
        for(Proveedor p: cldrProveedor.getProveedores() ){
            selecProveedor.addItem(p.getNombreFantasia() + ", cuit:" +p.getCuit());
        }
    }

    public void start(){
        JFrame frame = new JFrame("Consultas - factura por proveedor");
        frame.setContentPane( new ConsultaLibroIVA().panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(640,480);
        frame.pack();
        frame.setVisible(true);
    }

}
