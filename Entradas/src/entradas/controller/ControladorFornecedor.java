/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entradas.controller;

import entradas.dao.DBErrorException;
import entradas.dao.FornecedorDAO;
import entradas.model.Fornecedor;
import java.util.List;

/**
 *
 * @author Tiago Rosa
 */
public class ControladorFornecedor {
    
    private FornecedorDAO fdao;

    public ControladorFornecedor() {
        fdao = new FornecedorDAO();
    }

    public List<Fornecedor> buscaFornecedores(String razao) throws DBErrorException {
        if (!razao.equals(""))
            return fdao.buscaPorRazao(razao);
        else
            return fdao.buscaTodos();
    }
    
    
    
}
