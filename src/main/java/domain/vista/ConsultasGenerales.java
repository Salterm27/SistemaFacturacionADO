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

public class ConsultasGenerales {
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JComboBox comboBox1;
    private JButton GENERARButton;
    private JPanel calendario;
    private JComboBox cuentaCorriente_Proveedor;
    private JTextField CuentaCorriente_EstadodeudaTotal;
    private JTextField CuentaCorriente_impuesto;
    private ControllerProveedor cldrProveedor;
    private JTable Facturas_table;
    private JTable CuentaCorriente_tabla;
    private DefaultTableModel modelCCtabla;

    // CUENTA CORRIENTE
    // PROVEEDOR, SALDO ( TODOS COMPROBANTES - LO QUE SE LE PAGO )
    public ConsultasGenerales(ControllerProveedor cldrProveedor){
        this.cldrProveedor = cldrProveedor;
        setProveedor();

        makeTableModelCCtabla();

        cuentaCorriente_Proveedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int cuit = Integer.valueOf(cuentaCorriente_Proveedor.getSelectedItem().toString().split("cuit:")[1]);
                CuentaCorriente_EstadodeudaTotal.setText("El estado actual es: "+ estadoDeCuentaCorriente(cuit));
            }
        });
    }

    public void start(){
        JFrame frame = new JFrame("Consultas");
        frame.setContentPane( new ConsultasGenerales(cldrProveedor).panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void setProveedor(){
        this.cuentaCorriente_Proveedor.addItem("");
        for(Proveedor p: cldrProveedor.getProveedores() ){
            cuentaCorriente_Proveedor.addItem(p.getNombreFantasia() + ", cuit:" +p.getCuit());
        }
    }

    private void makeTableModelCCtabla(){
        modelCCtabla = new DefaultTableModel();
        modelCCtabla.addColumn("Documento");
        modelCCtabla.addColumn("Fecha emision");
        modelCCtabla.addColumn("Total");
        CuentaCorriente_tabla.setModel(modelCCtabla);
    }
    private void addToModelCCtabla(String documento, String fechaEmision, String total){
        modelCCtabla.addRow(new Object[]{
                documento, fechaEmision, total
        });
    }
    private double estadoDeCuentaCorriente(int cuit){

        modelCCtabla.getDataVector().removeAllElements();

        // falta impuestos
        double estadoCuenta= 0;
        Proveedor p  =  cldrProveedor.getProveedorXcuit(cuit);
        for(Factura f : p.getFacturas()){
            estadoCuenta = estadoCuenta + f.getMonto();
            addToModelCCtabla(String.valueOf(f.getNumeroDocumento()),f.getFecha().toString(),String.valueOf(f.getMonto()));
        }
        for (NotaDeCredito nc: p.getNotasdecredito()){
            estadoCuenta = estadoCuenta - nc.getMonto();
            addToModelCCtabla(String.valueOf(nc.getNumeroDocumento()),nc.getFecha().toString(),String.valueOf(nc.getMonto()));

        }
        for (NotaDeDebito nd: p.getNotasdedebito()){
            estadoCuenta = estadoCuenta + nd.getMonto();
            addToModelCCtabla(String.valueOf(nd.getNumeroDocumento()),nd.getFecha().toString(),String.valueOf(nd.getMonto()));

        }
        for (OrdenDePago op: p.getOrdenesdepago()){
            estadoCuenta = estadoCuenta - op.getMonto();
            addToModelCCtabla(String.valueOf(op.getNumeroDocumento()),op.getFecha().toString(),String.valueOf(op.getMonto()));

        }
        return estadoCuenta;
    }
}

