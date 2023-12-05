package Lab6;

import Lab2.Class.HardDrive;
import Lab2.Class.HardDriveType;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static Lab2.Class.ExMemory.dateFormat;

public class MainTable implements TableModelListener {
    private static Color defaultColor;
    JTable tbl;
    TableModel tblModel;
    private TableRowSorter<DriveTableModel> sorter;
    private RowFilter<Object, Object> filter;

    private boolean flag = false;

    //String manufacturer, double capacity, Date accessTime, HardDriveType type(kod, name), String model, double cost, Date launchDate
    JTextField manufacturer = new JTextField(5);
    JTextField capacity = new JTextField(10);
    JTextField accessTime = new JTextField(5);
    JTextField kod = new JTextField(5);
    JTextField name = new JTextField(4);
    JTextField model = new JTextField(5);
    JTextField cost = new JTextField(5);
    JTextField launchDate = new JTextField(7);

    public MainTable() throws ParseException {
        JFrame frm = new JFrame("Books");
        JPanel pnlTbl = new JPanel();
        JPanel pnlEdt = new JPanel();

        pnlTbl.setLayout(new BorderLayout());
        pnlEdt.setLayout(new FlowLayout());


        JButton bAdd = new JButton("Add");
        JButton bClear = new JButton("Clear");
        frm.setLayout(new BorderLayout());
        frm.setSize(600, 200);
        frm.setLocation(50, 300);
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        HardDrive[] drives = new HardDrive[10];
        for (int i = 0; i < drives.length; i++) {
            drives[i] = new HardDrive(
                    "Manufacturer " + (drives.length - i),
                    512 + (drives.length - i) * 8,
                    new Date(),
                    new HardDriveType(i, "Name " + i),
                    "Model " + i,
                    200 + i * 20,
                    new Date()
            );
        }

        List<HardDrive> driveList = Arrays.asList(drives);
        ArrayList<HardDrive> driveArrayList = new ArrayList<>(driveList);
        bAdd.addActionListener(e -> {
            try {
                if(flag) {
                    driveArrayList.get(tbl.getSelectedRow()).setManufacturer(manufacturer.getText());
                    driveArrayList.get(tbl.getSelectedRow()).setCapacity(Double.parseDouble(capacity.getText()));
                    driveArrayList.get(tbl.getSelectedRow()).setAccessTime(dateFormat.parse(accessTime.getText()));
                    driveArrayList.get(tbl.getSelectedRow()).getType().setKod(Integer.parseInt(kod.getText()));
                    driveArrayList.get(tbl.getSelectedRow()).getType().setName(name.getText());
                    driveArrayList.get(tbl.getSelectedRow()).setModel(model.getText());
                    driveArrayList.get(tbl.getSelectedRow()).setCost(Double.parseDouble(cost.getText()));
                    driveArrayList.get(tbl.getSelectedRow()).setLaunchDate(dateFormat.parse(launchDate.getText()));
                } else {
                    driveArrayList.add(new HardDrive(
                            manufacturer.getText(),
                            Double.parseDouble(capacity.getText()),
                            dateFormat.parse(accessTime.getText()),
                            new HardDriveType(Integer.parseInt(kod.getText()), name.getText()),
                            model.getText(),
                            Double.parseDouble(cost.getText()),
                            dateFormat.parse(launchDate.getText())));
                }
            } catch (NumberFormatException | ParseException exception) {
                exception.printStackTrace();
            }
            ((AbstractTableModel)tblModel).fireTableDataChanged();
            tbl.updateUI();
        });
        bClear.addActionListener(e -> clearFields());
        tblModel = new DriveTableModel(driveArrayList);
        tblModel.addTableModelListener(this);
        tbl = new JTable(tblModel);
        tbl.setDefaultRenderer (Object.class, new StrRenderer());
        tbl.setDefaultRenderer (Number.class, new CapacityRenderer());

        RowSorter<DriveTableModel> sorter =
                new TableRowSorter<>((DriveTableModel) tblModel);
        tbl.setRowSorter(sorter);
        JScrollPane scroll = new JScrollPane(tbl);
        tbl.setPreferredScrollableViewportSize(new Dimension(300, 100));
        pnlTbl.add(scroll);
        pnlEdt.add(manufacturer);
        pnlEdt.add(capacity);
        pnlEdt.add(accessTime);
        pnlEdt.add(kod);
        pnlEdt.add(name);
        pnlEdt.add(model);
        pnlEdt.add(cost);
        pnlEdt.add(launchDate);
        pnlEdt.add(bAdd);
        pnlEdt.add(bClear);
        defaultColor = kod.getBackground();
        kod.setForeground(Color.BLACK);


        JTextField filterText = new JTextField(20);
        JButton filterButton = new JButton("Фильтр");

        filterButton.addActionListener(e -> {
            String text = filterText.getText();
            applyFilter(text);
        });

        pnlEdt.add(filterText);
        pnlEdt.add(filterButton);

        JButton sortButton = new JButton("Сортировка по ёмкости");

        sortButton.addActionListener(e -> applySorting());

        pnlEdt.add(sortButton);

        tbl.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = tbl.getSelectedRow();
            setFieldsFromSelectedRow(selectedRow, driveArrayList);
        });

        frm.getContentPane().add(pnlTbl, BorderLayout.CENTER);
        frm.getContentPane().add(pnlEdt, BorderLayout.SOUTH);
        frm.setVisible(true);
        frm.pack();

    }

    public static Color getDefaultColor() {
        return defaultColor;
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        int row = e.getFirstRow();
        int column = e.getColumn();
        TableModel model = (TableModel) e.getSource();
        String columnName = model.getColumnName(column);
        Object data = model.getValueAt(row, column);
    }

    public static void main(String[] args) {
        try {
            new MainTable();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void applyFilter(String text) {
        RowFilter<DriveTableModel, Object> rf;
        sorter = new TableRowSorter<>((DriveTableModel) tblModel);
        tbl.setRowSorter(sorter);
        try {
            rf = RowFilter.regexFilter(text);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
    }

    private void applySorting() {
        sorter = new TableRowSorter<>((DriveTableModel) tblModel);
        tbl.setRowSorter(sorter);
        List<RowSorter.SortKey> sortKeys = new ArrayList<>();
        sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);
    }

    private void setFieldsFromSelectedRow(int rowIndex, ArrayList<HardDrive> driveArrayList) {
        flag = true;
        if (rowIndex >= 0 && rowIndex < tblModel.getRowCount()) {
            HardDrive selectedBook = driveArrayList.get(rowIndex);
            manufacturer.setText(selectedBook.getManufacturer());
            capacity.setText(String.valueOf(selectedBook.getCapacity()));
            accessTime.setText(dateFormat.format(selectedBook.getAccessTime()));
            kod.setText(String.valueOf(selectedBook.getType().getKod()));
            name.setText(selectedBook.getType().getName());
            model.setText(selectedBook.getModel());
            cost.setText(String.valueOf(selectedBook.getCost()));
            launchDate.setText(dateFormat.format(selectedBook.getLaunchDate()));
        } else {
            clearFields();
        }
    }

    private void clearFields() {
        manufacturer.setText("");
        capacity.setText("");
        accessTime.setText("");
        kod.setText("");
        name.setText("");
        model.setText("");
        cost.setText("");
        launchDate.setText("");
    }
}
