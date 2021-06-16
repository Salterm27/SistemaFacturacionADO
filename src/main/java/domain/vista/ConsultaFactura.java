package domain.vista;

import domain.controlador.ControllerProveedor;
import domain.modelo.documentos.Factura;
import domain.modelo.proveedores.Proveedor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConsultaFactura {
    private JComboBox selecProveedor;
    private JTable table1;
    private JPanel panel1;
    private ControllerProveedor cldrProveedor;

    public ConsultaFactura(ControllerProveedor cldrProveedor){
        this.cldrProveedor = cldrProveedor;
        setProveedor();

        DefaultTableModel modelTabla = new DefaultTableModel();
        modelTabla.addColumn("Documento");
        modelTabla.addColumn("Fecha emision");
        modelTabla.addColumn("Total");
        table1.setModel(modelTabla);

        selecProveedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int cuit = Integer.valueOf(selecProveedor.getSelectedItem().toString().split("cuit:")[1]);
                Proveedor p = cldrProveedor.getProveedorXcuit(cuit);
                if ( p != null){
                    modelTabla.getDataVector().removeAllElements();
                    for (Factura f: p.getFacturas()){
                        modelTabla.addRow(new Object[]{
                                f.getNumeroDocumento(), f.getFecha(), f.getMonto()});
                    }
                }

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
        frame.setContentPane( new ConsultaFactura(cldrProveedor).panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(640,480);
        frame.pack();
        frame.setVisible(true);
    }

}
