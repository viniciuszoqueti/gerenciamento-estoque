/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.adapters;

import java.util.List;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import model.Marca;
/**
 *
 * @author 23006-0
 */
public class AdapterMarcaJTable extends AbstractTableModel {
    
    private enum Colunas {
        ID,
        MARCA,
    }
    private List<Marca> marca = null;

    public AdapterMarcaJTable(List<Marca> marca) {
        this.marca = marca;
    }

    @Override
    public int getRowCount() {
        return marca.size();
    }

    @Override
    public int getColumnCount() {
        return AdapterMarcaJTable.Colunas.values().length;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Marca marca = this.marca.get(rowIndex);

        switch (Colunas.values()[columnIndex]) {

            case ID:
                return marca.getId();
            case MARCA:
                return marca.getMarca();
            default:
                return "";

        }

    }
    
    @Override
    public String getColumnName(int columnIndex) {

        switch (Colunas.values()[columnIndex]) {
            case ID:
                return Colunas.ID.name().toLowerCase();
            case MARCA:
                return Colunas.MARCA.name().toLowerCase();
            default:
                return "Indefinido";
        }

    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {

        switch (Colunas.values()[columnIndex]) {
            case ID:
                return Integer.class;
            case MARCA:
                return String.class;
            default:
                return Object.class;
        }

    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return super.isCellEditable(rowIndex, columnIndex);
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        super.setValueAt(aValue, rowIndex, columnIndex);
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        super.addTableModelListener(l);
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        super.removeTableModelListener(l);
    }

    public Marca getValue(int rowIndex) {
        return marca.get(rowIndex);
    }
    
}
