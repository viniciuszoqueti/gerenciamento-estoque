/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entradas.controller;

import entradas.dao.DBErrorException;
import entradas.dao.ProdutoDAO;
import entradas.model.Produto;
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
        if (!desc.equals(""))
            return dao.buscaPorDescricao(desc);
        else
            return dao.buscaTodos();
    }
    
}
