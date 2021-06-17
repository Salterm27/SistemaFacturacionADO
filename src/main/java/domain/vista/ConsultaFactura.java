package domain.vista;

import com.toedter.calendar.JDateChooser;
import domain.controlador.ControllerProveedor;
import domain.modelo.documentos.Factura;
import domain.modelo.proveedores.Proveedor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;

public class ConsultaFactura {
    private JComboBox selecProveedor;
    private JTable table1;
    private JPanel panel1;
    private JPanel panelDesde;
    private JPanel panelHasta;
    private JButton buttonFiltrar;
    private ControllerProveedor cldrProveedor;
    private Proveedor proveedorSeleccionado;

    public ConsultaFactura(){
        this.cldrProveedor = ControllerProveedor.getInstance();
        setProveedor();

        Calendar cld = Calendar.getInstance();
        JDateChooser fechaDesde = new JDateChooser(cld.getTime());
        JDateChooser fechaHasta = new JDateChooser(cld.getTime());
        panelDesde.add(fechaDesde);panelHasta.add(fechaHasta);
        fechaHasta.setDateFormatString("dd/MM/yyyy");
        fechaDesde.setDateFormatString("dd/MM/yyyy");


        DefaultTableModel modelTabla = new DefaultTableModel();
        modelTabla.addColumn("Documento");
        modelTabla.addColumn("Fecha emision");
        modelTabla.addColumn("Total");
        table1.setModel(modelTabla);

        selecProveedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int cuit = Integer.valueOf(selecProveedor.getSelectedItem().toString().split("cuit:")[1]);
                proveedorSeleccionado = cldrProveedor.getProveedorXcuit(cuit);
                if ( proveedorSeleccionado != null){
                    modelTabla.getDataVector().removeAllElements();
                    for (Factura f: proveedorSeleccionado.getFacturas()){
                        modelTabla.addRow(new Object[]{
                                f.getNumeroDocumento(), f.getFecha(), f.getMonto()});
                    }
                }

            }
        });
        buttonFiltrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDate datedesde = LocalDate.ofInstant(fechaDesde.getDate().toInstant(), ZoneId.systemDefault());
                LocalDate datehasta = LocalDate.ofInstant(fechaHasta.getDate().toInstant(), ZoneId.systemDefault());
                modelTabla.getDataVector().removeAllElements();
                if ( proveedorSeleccionado != null){
                    for (Factura f: proveedorSeleccionado.getFacturas()){
                        if( f.getFecha().isAfter(datedesde) && f.getFecha().isBefore(datehasta) || f.getFecha().equals(datedesde) || f.getFecha().isEqual(datehasta) ){
                            System.out.println(f.getFecha().toString()+ " " + datedesde.toString() + " " + datehasta.toString());
                            modelTabla.addRow(new Object[]{
                                    f.getNumeroDocumento(), f.getFecha(), f.getMonto()});
                        }


                    }
                    modelTabla.fireTableDataChanged();
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
        frame.setContentPane( new ConsultaFactura().panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(640,480);
        frame.pack();
        frame.setVisible(true);
    }

}
