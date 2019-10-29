/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import br.com.adapters.AdapterMarcaJTable;
import dao.MarcaDAO;
import java.sql.SQLException;
import java.util.List;
import model.Marca;

/**
 *
 * @author Nicolas
 */
public class ControllerMarca {

    MarcaDAO dao;

    public ControllerMarca() throws InstantiationException, IllegalAccessException {
        this.dao = new MarcaDAO();
    }

    public void Excluir(Marca cliente) throws Exception {

        dao.delete(cliente.getId());

    }

    public void Editar(Marca marca) throws SQLException {
        dao.update(marca);
    }

    public void Adicionar(Marca marca) throws SQLException {
        dao.insert(marca);
    }

    public List<Marca> getMarcas() throws SQLException {
        List<Marca> marcas = dao.selectAll();
        return marcas;
    }

    public AdapterMarcaJTable AllTable() throws SQLException {
        List<Marca> marcas = dao.selectAll();
        return new AdapterMarcaJTable(marcas);
    }
}
