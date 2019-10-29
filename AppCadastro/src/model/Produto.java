package model;

import java.sql.Date;

public class Produto {

    private int id;
    private String nome;
    private int estoque;
    private float precoVenda;
    private float precoCusto;
    private Date dataCompra;
    private Date dataVenda;
    private Marca marca;
    private Fornecedor fornecedor;

    public Produto(){}
    
    public Produto(int id, String nome, int estoque, float precoVenda, float precoCusto, Marca marca, Fornecedor fornecedor) {
        this.id = id;
        this.nome = nome;
        this.estoque = estoque;
        this.precoVenda = precoVenda;
        this.precoCusto = precoCusto;
    
        this.marca = marca;
        this.fornecedor = fornecedor;
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

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public float getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(float precoVenda) {
        this.precoVenda = precoVenda;
    }

    public float getPrecoCusto() {
        return precoCusto;
    }

    public void setPrecoCusto(float precoCusto) {
        this.precoCusto = precoCusto;
    }

    public Date getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Date dataCompra) {
        this.dataCompra = dataCompra;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

}
