/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.Instant;
import utils.GravaArquivo;

/**
 *
 * @author 23016-9
 */
public class Fornecedor {

    private int id;
    private String razao;
    private String cpf_cnpj;
    private String rg_insc;
    private String telefone;
    private String fantasia;

    public Fornecedor(int id, String razao, String cpf_cnpj, String rg_insc, String telefone, String fantasia) {
        this.razao = razao;
        this.cpf_cnpj = cpf_cnpj;
        this.rg_insc = rg_insc;
        this.telefone = telefone;
        this.fantasia = fantasia;
        this.id = id;
    }

    public Fornecedor(int id) {

        this.id = id;
    }

    public Fornecedor() {

    }

    public void criaNovo(String acaoParam) {

        StringBuilder conteudo = new StringBuilder();
        String acao = acaoParam + "@ID_ARQUIVO=" + Instant.now().toEpochMilli();

        if (id > 0) {
            conteudo.append(id).append(";");
            acao = acaoParam + "@ID=" + id;
        } else {
            conteudo.append("0").append(";");
        }

        conteudo.append(razao).append(";");
        conteudo.append(cpf_cnpj).append(";");
        conteudo.append(rg_insc).append(";");
        conteudo.append(telefone).append(";");
        conteudo.append(fantasia);

        GravaArquivo.criaArquivo(conteudo.toString(), acao, this);
    }

    public void criaDelete() {
        StringBuilder conteudo = new StringBuilder();
        String acao = "DELETAR@ID=" + id;

        conteudo.append(id).append(";");
        conteudo.append(razao).append(";");
        conteudo.append(cpf_cnpj).append(";");
        conteudo.append(rg_insc).append(";");
        conteudo.append(telefone).append(";");
        conteudo.append(fantasia);

        GravaArquivo.criaArquivo(conteudo.toString(), acao, this);
    }

    public String getFantasia() {
        return fantasia;
    }

    public void setFantasia(String fantasia) {
        this.fantasia = fantasia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRazao() {
        return razao;
    }

    public void setRazao(String razao) {
        this.razao = razao;
    }

    public String getCpf_cnpj() {
        return cpf_cnpj;
    }

    public void setCpf_cnpj(String cpf_cnpj) {
        this.cpf_cnpj = cpf_cnpj;
    }

    public String getRg_insc() {
        return rg_insc;
    }

    public void setRg_insc(String rg_insc) {
        this.rg_insc = rg_insc;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return fantasia;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Fornecedor other = (Fornecedor) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
