/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.adapters;

import model.Cliente;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

public class AdapterClienteJTable extends AbstractTableModel {

    private enum Colunas {
        ID,
        NOME,
        DAT_NASCIMENTO,
        CPF,
        RG,
        ENDERECO,
        NUMERO,
        BAIRRO,
        CIDADE,
        ESTADO,
        CEP

    }

    private List<Cliente> clientes = null;

    public AdapterClienteJTable(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    @Override
    public int getRowCount() {
        return clientes.size();
    }

    @Override
    public int getColumnCount() {
        return Colunas.values().length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Cliente clientes = this.clientes.get(rowIndex);

        switch (Colunas.values()[columnIndex]) {

            case ID:
                return clientes.getId();
            case NOME:
                return clientes.getNome();
            case DAT_NASCIMENTO:
                return clientes.getDat_nascimento();
            case CPF:
                return clientes.getCpf();
            case RG:
                return clientes.getRg();
            case ENDERECO:
                return clientes.getEndereco();
            case NUMERO:
                return clientes.getNumero();
            case BAIRRO:
                return clientes.getBairro();
            case CIDADE:
                return clientes.getCidade();
            case ESTADO:
                return clientes.getEstado();
            case CEP:
                return clientes.getCep();
            default:
                return "";

        }

    }

    @Override
    public String getColumnName(int columnIndex) {

        switch (Colunas.values()[columnIndex]) {
            case ID:
                return Colunas.ID.name();
            case NOME:
                return Colunas.NOME.name().toLowerCase();
            case DAT_NASCIMENTO:
                return Colunas.DAT_NASCIMENTO.name().toLowerCase();
            case CPF:
                return Colunas.CPF.name().toLowerCase();
            case RG:
                return Colunas.RG.name().toLowerCase();
            case ENDERECO:
                return Colunas.ENDERECO.name().toLowerCase();
            case NUMERO:
                return Colunas.NUMERO.name().toLowerCase();
            case BAIRRO:
                return Colunas.BAIRRO.name().toLowerCase();
            case CIDADE:
                return Colunas.CIDADE.name().toLowerCase();
            case ESTADO:
                return Colunas.ESTADO.name().toLowerCase();
            case CEP:
                return Colunas.CEP.name().toLowerCase();
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
            case DAT_NASCIMENTO:
                return String.class;
            case CPF:
                return String.class;
            case RG:
                return String.class;
            case ENDERECO:
                return String.class;
            case NUMERO:
                return String.class;
            case BAIRRO:
                return String.class;
            case CIDADE:
                return String.class;
            case ESTADO:
                return String.class;
            case CEP:
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

    public Cliente getValue(int rowIndex) {
        return clientes.get(rowIndex);
    }

}
