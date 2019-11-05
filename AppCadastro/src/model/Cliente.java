/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import java.time.Instant;
import utils.GravaArquivo;

/**
 *
 * @author 23034-2
 */
public class Cliente {

    private int id;
    private String nome;
    private Date dat_nascimento;
    private String cpf;
    private String rg;
    private String endereco;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;

    public Cliente(int id, String nome, Date dat_nascimento, String cpf, String rg, String endereco, String numero, String bairro, String cidade, String estado, String cep) {
        this.id = id;
        this.nome = nome;
        this.dat_nascimento = dat_nascimento;
        this.cpf = cpf;
        this.rg = rg;
        this.endereco = endereco;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
    }

    public Cliente() {

    }

    public void criaNovo() {

        StringBuilder conteudo = new StringBuilder();
        String acao = "INSERIR@ID_ARQUIVO=" + Instant.now().toEpochMilli();

        if (id > 0) {
            conteudo.append(id).append(";");
            acao = "ALTERAR@ID=" + id;
        } else {
            conteudo.append("0").append(";");
        }

        conteudo.append(nome).append(";");
        conteudo.append(dat_nascimento).append(";");
        conteudo.append(cpf).append(";");
        conteudo.append(rg).append(";");
        conteudo.append(endereco).append(";");
        conteudo.append(numero).append(";");
        conteudo.append(bairro).append(";");
        conteudo.append(cidade).append(";");
        conteudo.append(estado).append(";");
        conteudo.append(cep);

        GravaArquivo.criaArquivo(conteudo.toString(), acao, this);
    }

    public void criaDelete() {
        StringBuilder conteudo = new StringBuilder();
        String acao = "DELETAR@ID" + id;

        conteudo.append(id).append(";");
        conteudo.append(nome).append(";");
        conteudo.append(dat_nascimento).append(";");
        conteudo.append(cpf).append(";");
        conteudo.append(rg).append(";");
        conteudo.append(endereco).append(";");
        conteudo.append(numero).append(";");
        conteudo.append(bairro).append(";");
        conteudo.append(cidade).append(";");
        conteudo.append(estado).append(";");
        conteudo.append(cep);
        GravaArquivo.criaArquivo(conteudo.toString(), acao, this);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDat_nascimento() {
        return dat_nascimento;
    }

    public void setDat_nascimento(Date dat_nascimento) {
        this.dat_nascimento = dat_nascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
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

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
