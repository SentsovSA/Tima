package Lab6;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

class StrRenderer implements TableCellRenderer {
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JTextField editor = new JTextField();
        if (value != null) editor.setText(value.toString());
        editor.setBackground (row % 2 == 1 ? Color.WHITE : Color.PINK);
        return editor;
    }
}

