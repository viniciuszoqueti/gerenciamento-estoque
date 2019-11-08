/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entradas.controller;

import entradas.dao.DBErrorException;
import entradas.dao.ProdutoDAO;
import entradas.model.Produto;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Tiago
 */
public class ControladorProduto {

    private ProdutoDAO dao;

    public ControladorProduto() {
        dao = new ProdutoDAO();
    }

    public List<Produto> buscaProdutos(String desc) throws DBErrorException, InstantiationException, IllegalAccessException {
        if (!desc.equals("")) {
            return dao.buscaPorDescricao(desc);
        } else {
            return dao.buscaTodos();
        }
    }

    public void Inserir(Produto produto) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        dao.insert(produto);
    }

    public void Editar(Produto produto) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, DBErrorException {

        if (dao.buscaPorId(produto.getId()) != null) {
            dao.update(produto);
        } else {
            dao.insert(produto);
        }

    }

    public void Excluir(Produto produto) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        dao.delete(produto.getId());
    }

}
