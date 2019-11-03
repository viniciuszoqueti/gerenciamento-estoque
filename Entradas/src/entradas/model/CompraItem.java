/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entradas.model;

/**
 *
 * @author Tiago
 */
public class CompraItem {

    private int id;
    private double quantidade;
    private double valor;
    private Produto produto;

    public CompraItem() {
        id = 0;
        quantidade = 0;
        valor = 0;
        produto = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) throws ValorInvalidoException {
        if (quantidade <= 0)
            throw new ValorInvalidoException("Quantidade inválida!");
        this.quantidade = quantidade;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) throws ValorInvalidoException {
        if (valor <= 0)
            throw new ValorInvalidoException("Valor de compra do produto inválido!");
        this.valor = valor;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) throws ValorInvalidoException {
        if (( produto == null) || (produto.getNome().equals("")))
            throw new ValorInvalidoException("Produto não informado!");
        this.produto = produto;
    }  
    
}
