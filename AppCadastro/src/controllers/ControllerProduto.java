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

        dao.delete(produto.getId());

    }

    public void Editar(Produto produto) throws SQLException {
        dao.update(produto);
    }

    public void Adicionar(Produto produto) throws SQLException {
        dao.insert(produto);
    }

    public AdapterProdutoJTable AllTable() throws SQLException {
        List<Produto> produtos = dao.selectAll();
        return new AdapterProdutoJTable(produtos);
    }
}
