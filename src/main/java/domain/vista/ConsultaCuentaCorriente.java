package domain.vista;

import com.toedter.calendar.JDateChooser;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;

public class ConsultaCuentaCorriente {
    private JPanel panel;
    private JComboBox selecProveedor;
    private JTable table1;
    private JLabel FacturasEmitidas;
    private JLabel NCEmitidas;
    private JLabel NDEmtidas;
    private JLabel OPEmitidas;
    private JLabel EstadoDeCuenta;
    private ControllerProveedor cldrProveedor;
    private Proveedor proveedorSeleccionado;
    private DefaultTableModel modelTabla;
    public ConsultaCuentaCorriente(){
        this.cldrProveedor = ControllerProveedor.getInstance();
        setProveedor();

        modelTabla = new DefaultTableModel();
        modelTabla.addColumn("Documento");
        modelTabla.addColumn("Fecha emision");
        modelTabla.addColumn("Total");
        table1.setModel(modelTabla);

        selecProveedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int cuit = Integer.valueOf(selecProveedor.getSelectedItem().toString().split("cuit:")[1]);
                proveedorSeleccionado = cldrProveedor.getProveedorXcuit(cuit);
                cargarData();
            }
        });
    }
    private void cargarData(){
        double total = 0;
        if ( proveedorSeleccionado != null){
            modelTabla.getDataVector().removeAllElements();
            this.FacturasEmitidas.setText("Facturas Emitidas: " + proveedorSeleccionado.getFacturas().size());
            this.NCEmitidas.setText("NC Emitidas: " + proveedorSeleccionado.getNotasdecredito().size());
            this.NDEmtidas.setText("ND Emitidas: " + proveedorSeleccionado.getNotasdedebito().size());
            this.OPEmitidas.setText("OP Emitidas: " + proveedorSeleccionado.getOrdenesdepago().size());
            for (Factura f: proveedorSeleccionado.getFacturas()){
                modelTabla.addRow(new Object[]{
                        "Factura "+f.getNumeroDocumento(), f.getFecha(), f.getMonto()});
                total =total+ f.getMonto();
            }
            for (NotaDeCredito nc: proveedorSeleccionado.getNotasdecredito()){
                modelTabla.addRow(new Object[]{
                        "Nota de Credito "+nc.getNumeroDocumento(), nc.getFecha(), nc.getMonto()});
                total =total + nc.getMonto();
            }
            for (NotaDeDebito nd: proveedorSeleccionado.getNotasdedebito()){
                modelTabla.addRow(new Object[]{
                        "Nota de Debito "+nd.getNumeroDocumento(), nd.getFecha(), nd.getMonto()});
                total =total+ nd.getMonto();
            }
            for (OrdenDePago op: proveedorSeleccionado.getOrdenesdepago()){
                modelTabla.addRow(new Object[]{
                        "Orden de pago "+op.getNumeroDocumento(), op.getFecha(), op.getMonto()});
                total = total- op.getMonto();
            }

            this.EstadoDeCuenta.setText("Estado de cuenta: " +  total);
        }
    }
    private void setProveedor(){
        this.selecProveedor.addItem("");
        for(Proveedor p: cldrProveedor.getProveedores() ){
            selecProveedor.addItem(p.getNombreFantasia() + ", cuit:" +p.getCuit());
        }
    }
    public void start(){
        JFrame frame = new JFrame("Consultas - Cuenta Corriente");
        frame.setContentPane( new ConsultaCuentaCorriente().panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(640,480);
        frame.pack();
        frame.setVisible(true);
    }
}
