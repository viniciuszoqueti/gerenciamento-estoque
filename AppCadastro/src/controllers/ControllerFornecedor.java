/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import br.com.adapters.AdpterFornecedorJTable;
import dao.FornecedorDao;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Fornecedor;

/**
 *
 * @author Vinicius
 */
public class ControllerFornecedor {
    
    FornecedorDao dao;
    
    public ControllerFornecedor() throws InstantiationException, IllegalAccessException {
        this.dao = new FornecedorDao();
    }
    
    public void Excluir(Fornecedor fornecedor) throws Exception {
        try {
            dao.delete(fornecedor.getId());
            fornecedor.criaDelete();
        } catch (SQLException ex) {
            throw ex;
        }
    }
    
    public void Editar(Fornecedor fornecedor) throws SQLException {
        
        try {
            dao.update(fornecedor);
            fornecedor.criaNovo("ALTERAR");
            
        } catch (SQLException ex) {
            throw ex;
        }
        
    }
    
    public void Adicionar(Fornecedor fornecedor) throws SQLException {
        
        try {
            int novoId = dao.insert(fornecedor);
            fornecedor.setId(novoId);
            fornecedor.criaNovo("INSERIR");
            
        } catch (SQLException ex) {
            throw ex;
        }
        
    }
    
    public List<Fornecedor> getFornecedores() throws SQLException {
        List<Fornecedor> fornecedores = dao.selectAll();
        return fornecedores;
    }
    
    public AdpterFornecedorJTable AllTable() throws SQLException {
        List<Fornecedor> fornecedores = dao.selectAll();
        return new AdpterFornecedorJTable(fornecedores);
    }
    
}
