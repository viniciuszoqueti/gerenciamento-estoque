/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.adapters;

import java.sql.Date;
import java.util.List;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import model.Produto;

/**
 *
 * @author 23006-0
 */
public class AdapterProdutoJTable extends AbstractTableModel {

    private enum Colunas {
        ID,
        NOME,
        ESTOQUE,
        PRECO_VENDA,
        PRECO_CUSTO,
        DATA_COMPRA,
        DATA_VENDA

    }
    private List<Produto> produto = null;

    public AdapterProdutoJTable(List<Produto> produtos) {
        this.produto = produtos;
    }

    @Override
    public int getRowCount() {
        return produto.size();
    }

    @Override
    public int getColumnCount() {
        return AdapterProdutoJTable.Colunas.values().length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Produto produto = this.produto.get(rowIndex);

        switch (Colunas.values()[columnIndex]) {

            case ID:
                return produto.getId();
            case NOME:
                return produto.getNome();
            case ESTOQUE:
                return produto.getEstoque();
            case PRECO_VENDA:
                return produto.getPrecoVenda();
            case PRECO_CUSTO:
                return produto.getPrecoCusto();
            case DATA_COMPRA:
                return produto.getDataCompra();
            case DATA_VENDA:
                return produto.getDataVenda();

            default:
                return "";

        }

    }

    @Override
    public String getColumnName(int columnIndex) {

        switch (Colunas.values()[columnIndex]) {
            case ID:
                return Colunas.ID.name().toLowerCase();
            case NOME:
                return Colunas.NOME.name().toLowerCase();
            case ESTOQUE:
                return Colunas.ESTOQUE.name().toLowerCase();
            case PRECO_VENDA:
                return Colunas.PRECO_VENDA.name().toLowerCase();
            case PRECO_CUSTO:
                return Colunas.PRECO_CUSTO.name().toLowerCase();
            case DATA_COMPRA:
                return Colunas.DATA_COMPRA.name().toLowerCase();
            case DATA_VENDA:
                return Colunas.DATA_VENDA.name().toLowerCase();
            default:
                return "Indefinido";
        }

    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {

        switch (Colunas.values()[columnIndex]) {
            case ID:
                return Integer.class;
            case NOME:
                return String.class;

            case ESTOQUE:
                return Integer.class;
            case PRECO_VENDA:
                return Float.class;
            case PRECO_CUSTO:
                return Float.class;
            case DATA_COMPRA:
                return Date.class;
            case DATA_VENDA:
                return Date.class;
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

    public Produto getValue(int rowIndex) {
        return produto.get(rowIndex);
    }
}
