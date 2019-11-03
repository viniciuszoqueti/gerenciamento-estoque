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
public class Fornecedor {
    
    private int id;
    private String razao;
    private String fantasia;
    private String telefone;
    private String celular;
    private String contato;
    private String endereco;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String cnpj;
    private String inscricao;
    private Calendar DatUltimaCompra;

    public Fornecedor() {
        id = 0;
        razao = "";
        fantasia = "";
        telefone = "";
        celular = "";
        contato = "";
        endereco = "";
        numero = "";
        bairro = "";
        cidade = "";
        estado = "";
        cnpj = "";
        inscricao = "";
        DatUltimaCompra = Calendar.getInstance();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRazao(){
        return razao;
    }

    public void setRazao(String razao) throws ValorInvalidoException {
        if ((razao == null) || (razao.equals("")))
            throw new ValorInvalidoException("A razão social do fornecedor deve ser informada!");
        this.razao = razao;
    }

    public String getFantasia() {
        return fantasia;
    }

    public void setFantasia(String fantasia) {
        this.fantasia = fantasia;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) throws ValorInvalidoException {
        if ((telefone == null) || (telefone.equals("")))
            throw new ValorInvalidoException("O numero do telefone deve ser informado!");
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) throws ValorInvalidoException {
        if ((celular == null) || (celular.equals("")))
            throw new ValorInvalidoException("O numero do celular deve ser informado!");
        this.celular = celular;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato)throws ValorInvalidoException {
        if ((contato == null) || (contato.equals("")))
            throw new ValorInvalidoException("O nome de contato do fornecedor deve ser informado!");
        this.contato = contato;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) throws ValorInvalidoException {
        if ((cnpj == null) || (cnpj.equals("")))
            throw new ValorInvalidoException("O numero do CNPJ deve ser informado!");
        this.cnpj = cnpj;
    }

    public String getInscricao() {
        return inscricao;
    }

    public void setInscricao(String inscricao) throws ValorInvalidoException {
        if ((inscricao == null) || (inscricao.equals("")))
            throw new ValorInvalidoException("O numero da inscrição estadual do fornecedor deve ser informado");
        this.inscricao = inscricao;
    }

    public Calendar getDatUltimaCompra() {
        return DatUltimaCompra;
    }

    public void setDatUltimaCompra(Calendar DatUltimaCompra) {
        this.DatUltimaCompra = DatUltimaCompra;
    }
    
    
    
}
