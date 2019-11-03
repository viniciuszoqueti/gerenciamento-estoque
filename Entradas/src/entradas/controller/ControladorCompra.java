/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entradas.controller;

import entradas.dao.CompraDAO;
import entradas.dao.DBErrorException;
import entradas.dao.FornecedorDAO;
import entradas.dao.ProdutoDAO;
import entradas.model.Compra;
import entradas.model.CompraItem;
import entradas.model.Fornecedor;
import entradas.model.Produto;
import entradas.model.ValorInvalidoException;
import java.util.List;

/**
 *
 * @author Tiago Rosa
 */
public class ControladorCompra {
    
    private Compra compra;
    private CompraDAO compraDAO;
    private FornecedorDAO fDao;
    private ProdutoDAO pdao;
    private CompraItem item;
    
    public ControladorCompra(){
        this.compra = new Compra();
        this.compraDAO = new CompraDAO();
        this.fDao = new FornecedorDAO();
        this.pdao = new ProdutoDAO();
        this.item = new CompraItem();
    }
    
    public CompraItem getItem(){
        return item;
    }
    
    public Compra getCompra(){
        return this.compra;    
    }
    
    public void getCompraPorNotaFiscal(String nota) throws DBErrorException, Exception{
        
        this.compra = this.compraDAO.buscaCompraPorNotaFiscal( nota );
        
        if (this.compra == null){
            this.compra = new Compra();
            this.compra.setNota(nota);
        }
        
    }

    public void buscaFornecedor(String cod) throws DBErrorException, Exception {
        
        Fornecedor f = fDao.buscaPorId( Integer.parseInt(cod) );
        if ( f == null)
            throw new Exception("Forncedor não encontrado");
        
        this.compra.setFornecedor(f);
    }

    public void buscaProduto(String codprod) throws DBErrorException, Exception {
        
        Produto p = pdao.buscaPorId( Integer.parseInt(codprod));
        
        this.item.setProduto(p);
        
        if ( p == null)
            throw new Exception("Produto não encontrado");
    }

    public void adicionaItem() throws ValorInvalidoException {
        this.compra.addItem(item);
        item = new CompraItem();
    }

    public void removeItem(int selectedRow) throws ValorInvalidoException {
        
        CompraItem itemASerRemovido = this.compra.getItens().get(selectedRow);
        this.compra.removeItem( itemASerRemovido );
        
    }

    public void salvar() throws DBErrorException {
        this.compraDAO.salvar(compra);
        this.compra = new Compra();
    }

    public void excluiCompra() throws DBErrorException {
        this.compraDAO.delete(compra);
        this.compra = new Compra();
    }

    public List<Compra> buscaEntradas(String codfor) throws DBErrorException {
    
        
        List<Compra> l;
        
        if (codfor.equals("")){
            l = this.compraDAO.getTodasCompras();
        }else{
            l = this.compraDAO.buscaPorFornecedor( fDao.buscaPorId(Integer.parseInt(codfor)));
        }
        
        
        return l;
        
    }

    public void setCompra(Compra compraSelecionada) {
        this.compra = compraSelecionada;
    }

    public void novaCompra() {
        this.compra = new Compra();
        this.item = new CompraItem();
    }
    
}
