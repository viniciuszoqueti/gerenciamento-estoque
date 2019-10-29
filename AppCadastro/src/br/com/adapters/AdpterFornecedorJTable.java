/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.adapters;


import java.util.List;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import model.Fornecedor;

/**
 *
 * @author 23016-9
 */
public class AdpterFornecedorJTable extends AbstractTableModel {

    private enum Colunas {

        ID,
        RAZAO,
        FANTASIA,
        CPF_CNPJ,
        RG_INSCR,
        TELEFONE
    }

    private List<Fornecedor> fornecedores = null;

    public AdpterFornecedorJTable(List<Fornecedor> fornecedores) {
        this.fornecedores = fornecedores;
    }

    @Override
    public int getRowCount() {
        return fornecedores.size();
    }

    @Override
    public int getColumnCount() {
        return Colunas.values().length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Fornecedor fornecedor = this.fornecedores.get(rowIndex);

        switch (Colunas.values()[columnIndex]) {

            case ID:
                return fornecedor.getId();
            case RAZAO:
                return fornecedor.getRazao();
            case FANTASIA:
                return fornecedor.getFantasia();
            case CPF_CNPJ:
                return fornecedor.getCpf_cnpj();
            case RG_INSCR:
                return fornecedor.getRg_insc();
            case TELEFONE:
                return fornecedor.getTelefone();
            default:
                return "";

        }

    }

    @Override
    public String getColumnName(int columnIndex) {

        switch (Colunas.values()[columnIndex]) {

            case ID:
                return Colunas.ID.name().toLowerCase();
            case RAZAO:
                return Colunas.RAZAO.name().toLowerCase();
            case FANTASIA:
                return Colunas.FANTASIA.name().toLowerCase();
            case CPF_CNPJ:
                return Colunas.CPF_CNPJ.name().toLowerCase();
            case RG_INSCR:
                return Colunas.RG_INSCR.name().toLowerCase();
            case TELEFONE:
                return Colunas.TELEFONE.name().toLowerCase();
            default:
                return "Indefinido";
        }

    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {

        switch (Colunas.values()[columnIndex]) {
            case ID:
                return Integer.class;
            case RAZAO:
                return String.class;
            case FANTASIA:
                return String.class;
            case CPF_CNPJ:
                return String.class;
            case RG_INSCR:
                return String.class;
            case TELEFONE:
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

    public Fornecedor getValue(int rowIndex) {
        return fornecedores.get(rowIndex);
    }

}
