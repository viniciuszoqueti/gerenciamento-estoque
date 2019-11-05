/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entradas.controller;

import entradas.dao.DBErrorException;
import entradas.dao.FornecedorDAO;
import entradas.model.Fornecedor;
import java.sql.SQLException;
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

    public List<Fornecedor> buscaFornecedores(String razao) throws DBErrorException, InstantiationException, IllegalAccessException {
        if (!razao.equals("")) {
            return fdao.buscaPorRazao(razao);
        } else {
            return fdao.buscaTodos();
        }
    }

    public void Inserir(Fornecedor fornecedor) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        fdao.insert(fornecedor);
    }

    public void Editar(Fornecedor fornecedor) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, DBErrorException {

        if (fdao.buscaPorId(fornecedor.getId()) != null) {
            fdao.update(fornecedor);
        } else {
            fdao.insert(fornecedor);
        }

    }

    public void Excluir(Fornecedor fornecedor) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        fdao.delete(fornecedor.getId());
    }
}
