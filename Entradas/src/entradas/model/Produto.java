/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entradas.model;

import java.util.Calendar;

/**
 *
 * @author Tiago
 */
public class Produto {
    
    private int id;
    private String nome;
    private String codigoBarras;
    private Calendar datUltimaCompra;
    private Calendar datUltimaVenda;
    private double custo;
    private double venda;
    private double margemLucro;
    private int estoque;
    
    private Fornecedor fornecedor = null;

    public Produto() {
        id = 0;
        nome = "";
        codigoBarras = "";
        datUltimaCompra = Calendar.getInstance();
        datUltimaVenda = Calendar.getInstance();
        custo = 0;
        venda = 0;
        margemLucro = 0;
        estoque = 0;
        fornecedor = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome)throws ValorInvalidoException {
        if ((nome == null) || (nome.equals("")))
            throw new ValorInvalidoException("O nome/descrição do produto deve ser informado!");
        this.nome = nome;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public Calendar getDatUltimaCompra() {
        return datUltimaCompra;
    }

    public void setDatUltimaCompra(Calendar datUltimaCompra) {
        this.datUltimaCompra = datUltimaCompra;
    }

    public Calendar getDatUltimaVenda() {
        return datUltimaVenda;
    }

    public void setDatUltimaVenda(Calendar datUltimaVenda) {
        this.datUltimaVenda = datUltimaVenda;
    }

    public double getCusto() {
        return custo;
    }

    public void setCusto(double custo) throws ValorInvalidoException {
        if ( custo < 0)
            throw new ValorInvalidoException("Valor do custo do produto inválido!");
        this.custo = custo;
    }

    public double getVenda() {
        return venda;
    }

    public void setVenda(double venda) throws ValorInvalidoException {
        if ( venda <= 0)
            throw new ValorInvalidoException("Valor de venda do produto inválido!");
        this.venda = venda;
    }

    public double getMargemLucro() {
        return margemLucro;
    }

    public void setMargemLucro(double margemLucro) {
        this.margemLucro = margemLucro;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) throws ValorInvalidoException {
        if (( fornecedor == null) || (fornecedor.getRazao().equals("")))
            throw new ValorInvalidoException("O fornecedor do produto deve ser informado!");
        this.fornecedor = fornecedor;
    }
    
    
}
