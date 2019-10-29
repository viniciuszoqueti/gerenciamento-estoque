/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import br.com.adapters.AdapterClienteJTable;
import dao.ClienteDAO;
import java.sql.SQLException;
import java.util.List;
import model.Cliente;

/**
 *
 * @author Nicolas
 */
public class ControllerCliente {
    ClienteDAO dao;

    public ControllerCliente() throws InstantiationException, IllegalAccessException {
        this.dao = new ClienteDAO();
    }
    
     public void Excluir(Cliente cliente) throws Exception {

        dao.delete(cliente.getId());

    }

    public void Editar(Cliente cliente) throws SQLException {
        dao.update(cliente);
    }

    public void Adicionar(Cliente cliente) throws SQLException {
        dao.insert(cliente);
    }


    public AdapterClienteJTable AllTable() throws SQLException {
        List<Cliente> clientes = dao.selectAll();
        return new AdapterClienteJTable(clientes);
    }
}
