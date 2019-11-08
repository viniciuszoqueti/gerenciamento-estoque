/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import br.com.adapters.AdapterProdutoJTable;
import dao.ProdutoDAO;
import java.sql.SQLException;
import java.util.List;
import model.Produto;

/**
 *
 * @author Nicolas
 */
public class ControllerProduto {

    ProdutoDAO dao;

    public ControllerProduto() throws InstantiationException, IllegalAccessException {
        this.dao = new ProdutoDAO();
    }

    public void Excluir(Produto produto) throws Exception {

        try {
            dao.delete(produto.getId());
            produto.criaDelete();

        } catch (Exception ex) {
            throw ex;
        }

    }

    public void Editar(Produto produto) throws Exception {

        try {
            dao.update(produto);
            produto.criaNovo("ALTERAR");

        } catch (Exception ex) {
            throw ex;
        }
    }

    public void Adicionar(Produto produto) throws Exception {

        try {
            int novoId = dao.insert(produto);
            produto.setId(novoId);
            produto.criaNovo("INSERIR");

        } catch (Exception ex) {
            throw ex;
        }
    }

    public AdapterProdutoJTable AllTable() throws Exception {
        List<Produto> produtos = dao.selectAll();
        return new AdapterProdutoJTable(produtos);
    }
}
