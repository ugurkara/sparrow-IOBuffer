package com.sparrow.io.buffer.view;

import com.sparrow.io.buffer.BaseIOBuffer;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ugurkara
 */
class IOBufferTableModel<T> extends AbstractTableModel {

    private final BaseIOBuffer<T> buffer;
    private final StringConverter<T> converter;

    public IOBufferTableModel(BaseIOBuffer<T> buffer) {

        this(buffer,
                new StringConverter<T>() {
            @Override
            public String toString(T object) {
                return object.toString();
            }

            @Override
            public T fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }

    public IOBufferTableModel(BaseIOBuffer<T> buffer, StringConverter<T> converter) {
        this.buffer = buffer;
        this.converter = converter;

    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 1;
    }

    @Override
    public int getRowCount() {
        return buffer.getSize();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public String getColumnName(int column) {
        if (column == 0) {
            return "Index";
        }

        return "Value";

    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (columnIndex == 1) {
            buffer.setValue(rowIndex, converter.fromString((String) aValue));
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return rowIndex;
        }

        return converter.toString(buffer.getValue(rowIndex));

    }

    public BaseIOBuffer<T> getBuffer() {
        return buffer;
    }

}
