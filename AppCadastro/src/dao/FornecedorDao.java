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
import model.Fornecedor;

/**
 *
 * @author 23034-2
 */
public class FornecedorDao implements DAO<Fornecedor> {
    Connection conexao;

    public FornecedorDao() throws InstantiationException, IllegalAccessException {
        this.conexao = ConnectionFactory.getConnection();
    }

    @Override
    public void insert(Fornecedor parm) throws SQLException {
        PreparedStatement ps = null;

        try {

            String sql = "INSERT INTO `fornecedor` (`razao`,`fantasia`,`cpf_cnpj`,`rg_inscr`,`telefone`) VALUES (?, ?, ?, ?, ?)";

            ps = conexao.prepareStatement(sql);
            ps.setString(1, parm.getRazao());
            ps.setString(2, parm.getFantasia());
            ps.setString(3, parm.getCpf_cnpj());
            ps.setString(4, parm.getRg_insc());
            ps.setString(5, parm.getTelefone());

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
    public void update(Fornecedor parm) throws SQLException {

        PreparedStatement ps = null;

        try {

            String sql = "UPDATE `fornecedor` SET `razao` = ?, `fantasia` = ?, `cpf_cnpj` = ?, `rg_inscr` = ?, `telefone` = ? WHERE `id` = ?";

            ps = conexao.prepareStatement(sql);
            ps.setString(1, parm.getRazao());
            ps.setString(2, parm.getFantasia());
            ps.setString(3, parm.getCpf_cnpj());
            ps.setString(4, parm.getRg_insc());
            ps.setString(5, parm.getTelefone());
            ps.setInt(6, parm.getId());

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
    public void delete(int id) throws SQLException {
        PreparedStatement ps = null;
        try {

            String sql = "DELETE FROM fornecedor WHERE id = ?";
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
        //Conexão com o bando de dados

    }


    @Override
    public List selectAll() throws SQLException {
        PreparedStatement ps = null;
        String sql = "select * from fornecedor";
        List list = new ArrayList();

        try {
            ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Fornecedor c = new Fornecedor();
                c.setId(rs.getInt("id"));
                c.setRazao(rs.getString("razao"));
                c.setFantasia(rs.getString("fantasia"));
                c.setCpf_cnpj(rs.getString("cpf_cnpj"));
                c.setRg_insc(rs.getString("rg_inscr"));
                c.setTelefone(rs.getString("telefone"));

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
