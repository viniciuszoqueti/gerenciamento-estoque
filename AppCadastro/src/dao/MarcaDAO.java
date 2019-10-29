/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Marca;

/**
 *
 * @author 23021-9
 */
public class MarcaDAO implements DAO<Marca> {

    //Conexão com o bando de dados
    Connection conexao;

    public MarcaDAO() throws InstantiationException, IllegalAccessException {
        this.conexao = ConnectionFactory.getConnection();
    }

    @Override
    public void insert(Marca marca) {

        PreparedStatement ps = null;

        try {

            String sql = "INSERT INTO marca(marca) VALUES (?) ";
            ps = conexao.prepareStatement(sql);
            ps.setString(1, marca.getMarca());

            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                ps.close();
            } catch (SQLException ex2) {
                System.out.println(ex2.getMessage());
            }
        }
    }

    @Override
    public void update(Marca marca) {

        PreparedStatement ps = null;

        try {

            String sql = "UPDATE marca SET marca = ? WHERE id = ?";
            ps = conexao.prepareStatement(sql);
            ps.setString(1, marca.getMarca());
            ps.setInt(2, marca.getId());

            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        } finally {
            try {
                ps.close();
            } catch (SQLException ex) {
                System.out.println("Erro: " + ex.getMessage());
            }
        }
    }

    @Override
    public void delete(int id) {

        PreparedStatement ps = null;
        try {

            String sql = "DELETE FROM marca WHERE id = ?";
            ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        } finally {
            try {
                ps.close();
            } catch (SQLException ex) {
                System.out.println("Erro: " + ex.getMessage());
            }
        }

    }

    @Override
    public List selectAll() throws SQLException {
        PreparedStatement ps = null;
        String sql = "select * from marca";
        List list = new ArrayList();

        try {
            ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Marca c = new Marca();
                c.setId(rs.getInt("id"));
                c.setMarca(rs.getString("marca"));
                list.add(c);
            }

        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        } finally {
            try {
                ps.close();
            } catch (SQLException ex) {
                System.out.println("Erro: " + ex.getMessage());
            }
        }
        return list;
    }

}
