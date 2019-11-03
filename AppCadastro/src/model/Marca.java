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
 * @author 23021-9
 */
public class Marca {

    private int id;
    private String marca;

    public Marca(int id, String marca) {
        this.id = id;
        this.marca = marca;
    }

    public Marca() {
        this.id = 0;
        this.marca = "";
    }

    public Marca(int id) {
        this.id = id;

    }

    public void criaNovo() {

        StringBuilder conteudo = new StringBuilder();
        String acao = "INSERIR@ID_ARQUIVO=" + Instant.now().toEpochMilli();

        if (id > 0) {
            conteudo.append(id).append(";");
            acao = "ALTERAR@ID=" + id;
        }

        conteudo.append(marca);

        GravaArquivo.criaArquivo(conteudo.toString(), acao, this);
    }

    public void criaDelete() {
        StringBuilder conteudo = new StringBuilder();
        String acao = "DELETAR@ID" + id;

        conteudo.append(id).append(";");
        conteudo.append(marca);

        GravaArquivo.criaArquivo(conteudo.toString(), acao, this);
    }

    public int getId() {
        return id;
    }

    public String getMarca() {
        return marca;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Override
    public String toString() {
        return marca;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.id;
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
        final Marca other = (Marca) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
