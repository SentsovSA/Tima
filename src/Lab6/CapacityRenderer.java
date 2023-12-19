package Lab6;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class CapacityRenderer extends DefaultTableCellRenderer {
    public CapacityRenderer() {
        setHorizontalAlignment(SwingConstants.RIGHT);
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (value == null) return this;
        Component renderer = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        renderer.setBackground((Integer) value > 4 ? Color.GREEN : Color.LIGHT_GRAY);
        return renderer;
    }
}
