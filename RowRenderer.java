package asiakasrekisteri;

import java.awt.Component;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;

public class RowRenderer extends JLabel implements TableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object color,
        boolean isSelected, boolean hasFocus, int row, int column) {
        setText(Integer.toString(row));
        return this;
    }
}