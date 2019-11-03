/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entradas.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Tiago
 */
public class Compra {
    
    private int id;
    private String nota;
    private Calendar datCompra;
    private Fornecedor fornecedor = null;
    private List<CompraItem> itens;

    public Compra() {
        id = 0;
        nota = "";
        datCompra = Calendar.getInstance();
        fornecedor = null;
        itens = new ArrayList<CompraItem>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) throws ValorInvalidoException {
        if ((nota == null) || (nota.equals("")))
            throw new ValorInvalidoException("O numero da nota fiscal deve ser informado!");
        this.nota = nota;
    }

    public Calendar getDatCompra() {
        return datCompra;
    }

    public void setDatCompra(Calendar datCompra) {
        this.datCompra = datCompra;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) throws ValorInvalidoException {
        if (( fornecedor == null) || (fornecedor.getRazao().equals("")))
            throw new ValorInvalidoException("O fornecedor deve ser informado!");
        this.fornecedor = fornecedor;
    }

    public List<CompraItem> getItens() {
        return itens;
    }

    public void setItens(List<CompraItem> itens) {
        this.itens = itens;
    }
    
    public void addItem(CompraItem item) throws ValorInvalidoException {
        if (( item.getProduto() == null) || (item.getProduto().getId() == 0))
            throw new ValorInvalidoException("Produto não informado!");
        
        for (int i = 0; i< itens.size(); i++) {
            if (item.getProduto().getId() == itens.get(i).getProduto().getId()){
                itens.set(i, item);
                return;
            }                
        }
        
        itens.add(item);
    }

    public void removeItem(CompraItem item) throws ValorInvalidoException {
        
        if (( item == null) || (item.getProduto().getId() == 0))
            throw new ValorInvalidoException("Selecione o item a ser removido!");
        
        for (int i = 0; i< itens.size(); i++) {
            if (item.getProduto().getId() == itens.get(i).getProduto().getId()){
                itens.remove(i);
                return;
            }                
        }
        
    }

}
