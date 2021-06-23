package domain.vista;

import com.toedter.calendar.JDateChooser;
import domain.controlador.ControllerProveedor;
import domain.modelo.documentos.*;
import domain.modelo.proveedores.Proveedor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.List;

public class ConsultaLibroIVA {
    private JComboBox selecProveedor;
    private JTable table1;
    private JPanel panel;
    private JLabel labelSubTotal;
    private JLabel labelIVA;
    private JLabel LabelTotal;
    private JLabel labelCantDoc;
    private JButton APLICARButton;
    private JPanel panelDesde;
    private JPanel panelHasta;
    private JLabel IVA5;
    private JLabel IVA21;
    private JLabel IVA2_5;
    private JLabel IVA10_5;
    private JLabel IVA27;
    private JLabel IIBB;
    private ControllerProveedor cldrProveedor;
    JDateChooser fechaDesde;
    DefaultTableModel modelTabla;
    JDateChooser fechaHasta;
    LocalDate datehasta;
    LocalDate datedesde;

    public ConsultaLibroIVA() {
        this.cldrProveedor = ControllerProveedor.getInstance();



        Calendar cld = Calendar.getInstance();
        fechaDesde = new JDateChooser(cld.getTime());
        fechaHasta = new JDateChooser(cld.getTime());
        panelDesde.add(fechaDesde);panelHasta.add(fechaHasta);
        fechaHasta.setDateFormatString("dd/MM/yyyy");
        fechaDesde.setDateFormatString("dd/MM/yyyy");

        modelTabla = new DefaultTableModel();
        modelTabla.addColumn("CUIT");
        modelTabla.addColumn("Documento");
        modelTabla.addColumn("Fecha emision");
        modelTabla.addColumn("SubTotal");
        modelTabla.addColumn("IVA");
        modelTabla.addColumn("Total");
        table1.setModel(modelTabla);


        APLICARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generarLibro(); APLICARButton.setEnabled(false);
            }
        });
    }

    double Subtotal = 0;
    double iva2_5 = 0;
    double iva5 = 0;
    double iva10_5 = 0;
    double iva21 = 0;
    double iva27 = 0;
    double iibb = 0;
    double totaliva = 0;
    int cantDocEmitidos = 0;

    private void generarLibro(){
        modelTabla.getDataVector().removeAllElements();
        datedesde = LocalDate.ofInstant(fechaDesde.getDate().toInstant(), ZoneId.systemDefault());
        datehasta = LocalDate.ofInstant(fechaHasta.getDate().toInstant(), ZoneId.systemDefault());

        if (datedesde.isBefore(datehasta) || datedesde.isEqual(datehasta)){
            for (Proveedor proveedorSeleccionado: cldrProveedor.getProveedores()){
                List docs = proveedorSeleccionado.getFacturas();
                iteracionPorDocumento(docs,proveedorSeleccionado);

                docs = proveedorSeleccionado.getNotasdecredito();
                iteracionPorDocumento(docs,proveedorSeleccionado);

                docs = proveedorSeleccionado.getNotasdedebito();
                iteracionPorDocumento(docs,proveedorSeleccionado);
            }
            IVA2_5.setText(IVA2_5.getText() + (iva2_5));
            IVA5.setText(IVA5.getText() + (iva5));
            IVA10_5.setText(IVA10_5.getText() + (iva10_5));
            IVA21.setText(IVA21.getText() + (iva21));
            IVA27.setText(IVA27.getText() + (iva27));
            totaliva = iva2_5 +iva5+ iva10_5 +iva21+iva27;
            labelCantDoc.setText("Total documentos: " + cantDocEmitidos);
            labelIVA.setText("IVA: " + totaliva);
            labelSubTotal.setText("SubTotal: " + Subtotal);
            LabelTotal.setText("Total: " + (totaliva+Subtotal));
            IIBB.setText(IIBB.getText() + iibb);
        }
        modelTabla.fireTableDataChanged();
    }
    private void iteracionPorDocumento(List<Documento> listDoc, Proveedor p){
        for (Documento doc: listDoc){
            if(validarFecha(doc.getFecha())){
                modelTabla.addRow(new Object[]{
                        p.getCuit(), doc.getNumeroDocumento(), doc.getFecha(),
                        doc.getMonto() - doc.getIva(),doc.getIva(),
                        doc.getMonto()});
                for (Item item: doc.getDetalle()){
                    if(item.getPs().getProducto().getIva() == 2.5){
                        iva2_5 = iva2_5 + item.getTotalIva();
                    }
                    if(item.getPs().getProducto().getIva() == 5){
                        iva5 = iva5 + item.getTotalIva();
                    }
                    if(item.getPs().getProducto().getIva() == 10){
                        iva10_5 = iva10_5 + item.getTotalIva();
                    }
                    if(item.getPs().getProducto().getIva() == 21){
                        iva21 = iva21 + item.getTotalIva();
                    }
                    if(item.getPs().getProducto().getIva() == 27){
                        iva27 = iva27 + item.getTotalIva();
                    }
                }
                iibb = iibb + (((doc.getMonto() - doc.getIva()) * p.getNroIIBB()) / 100);
                Subtotal = Subtotal + doc.getMonto() - doc.getIva();
                cantDocEmitidos++;
            }
        }
    }

    private boolean validarFecha (LocalDate d){
        if(d.isEqual(datedesde) ||
                d.isEqual(datehasta) ||
                d.isAfter(datedesde)||
                d.isBefore(datehasta)){
            return true;
        }
        return false;
    }

    public void start(){
        JFrame frame = new JFrame("Consultas - LIBRO IVA");
        frame.setContentPane( new ConsultaLibroIVA().panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(640,480);
        frame.pack();
        frame.setVisible(true);
    }

}
