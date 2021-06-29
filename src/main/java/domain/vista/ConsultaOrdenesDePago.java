package domain.vista;

import domain.controlador.ControllerProducto;
import domain.controlador.ControllerProveedor;
import domain.modelo.documentos.Cheque;
import domain.modelo.documentos.OrdenDePago;
import domain.modelo.producto.ProductoSeleccionable;
import domain.modelo.proveedores.Proveedor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConsultaOrdenesDePago {
    private JComboBox selecProveedor;
    private JTable table1;
    private JPanel panel;
    private ControllerProveedor cldrProveedor;
    DefaultTableModel model;
    private Proveedor proveedorSeleccionado;
    public ConsultaOrdenesDePago(){
        this.cldrProveedor = ControllerProveedor.getInstance();
        selecProveedor = cldrProveedor.comboProveedor(selecProveedor);
        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Fecha");
        model.addColumn("Fecha Limite");
        model.addColumn("Total");
        model.addColumn("Total Retenido");
        model.addColumn("Se pago con cheque");
        table1.setModel(model);
        selecProveedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int cuit = Integer.valueOf(selecProveedor.getSelectedItem().toString().split(", cuit:")[1]);
                proveedorSeleccionado  = cldrProveedor.getProveedorXcuit(cuit);
                if(proveedorSeleccionado!=null){
                    model.getDataVector().removeAllElements();
                    for(OrdenDePago op: proveedorSeleccionado.getOrdenesdepago()){
                        Cheque ch = op.getCheque();
                        if(ch==null){
                            model.addRow(new Object[]{
                                    op.getNumeroDocumento(),
                                    op.getFecha(),
                                    op.getFechaLimite(),
                                    op.getMonto(),
                                    op.getTotalRetenciones(),
                                    "No"
                            });
                        }else{
                            model.addRow(new Object[]{
                                    op.getNumeroDocumento(),
                                    op.getFecha(),
                                    op.getFechaLimite(),
                                    op.getMonto(),
                                    op.getTotalRetenciones(),
                                    ch.getFechaEmision() + " $"+ch.getImporte()
                            });
                        }
                    }
                    model.fireTableDataChanged();
                }
            }
        });
    }

    public void start(){
        JFrame frame = new JFrame("Consultas - Ordenes de pago");
        frame.setContentPane( new ConsultaOrdenesDePago().panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //frame.setSize(640,480);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

}
