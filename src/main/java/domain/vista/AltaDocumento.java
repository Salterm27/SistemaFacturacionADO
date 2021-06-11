package domain.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class AltaDocumento {
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JTable table1;
    private JComboBox comboBox3;
    private JSpinner spinner1;
    private JButton agregarItemButton;
    private JButton generarDocumentoButton;
    private JLabel labelProveedor;
    private JButton CANCELARButton;
    private JPanel panelDoc;

    public AltaDocumento (){
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("Nombre");
        model.addColumn("Precio");
        model.addColumn("Cantidad");
        model.addColumn("Total");

        model.addRow(new Object[]{"item1","200","2","400"});
        model.addRow(new Object[]{"item2","200","2","400"});

        table1.setModel(model);

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Alta Proveedor");
        frame.setContentPane( new AltaDocumento().panelDoc);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
