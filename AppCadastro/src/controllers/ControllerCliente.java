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

        try {
            dao.delete(cliente.getId());
          //  cliente.criaDelete();

        } catch (SQLException ex) {
            throw ex;
        }

    }

    public void Editar(Cliente cliente) throws SQLException {

        try {
            dao.update(cliente);
         //   cliente.criaNovo();

        } catch (SQLException ex) {
            throw ex;
        }
    }

    public void Adicionar(Cliente cliente) throws SQLException {

        try {
            dao.insert(cliente);

           // cliente.criaNovo();

        } catch (SQLException ex) {
            throw ex;
        }
    }

    public AdapterClienteJTable AllTable() throws SQLException {
        List<Cliente> clientes = dao.selectAll();
        return new AdapterClienteJTable(clientes);
    }
}
