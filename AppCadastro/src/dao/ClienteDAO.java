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
import model.Cliente;

/**
 *
 * @author 23034-2
 */
public class ClienteDAO implements DAO<Cliente>{
    Connection conexao;

    public ClienteDAO() throws InstantiationException, IllegalAccessException {
        this.conexao = ConnectionFactory.getConnection();
    }
    
    @Override
    public void insert(Cliente parm) throws SQLException {
       PreparedStatement ps = null;

        try {

            String sql = "INSERT INTO clientes(nome,dat_nascimento,cpf,rg,endereco,numero,bairro,cidade,estado,cep) VALUES (?,?,?,?,?,?,?,?,?,?) ";
            ps = conexao.prepareStatement(sql);
            ps.setString(1, parm.getNome());
            ps.setDate(2, parm.getDat_nascimento());
            ps.setString(3, parm.getCpf());
            ps.setString(4, parm.getRg());
            ps.setString(5, parm.getEndereco());
            ps.setString(6, parm.getNumero());
            ps.setString(7, parm.getBairro());
            ps.setString(8, parm.getCidade());
            ps.setString(9, parm.getEstado());
            ps.setString(10, parm.getCep());

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
    public void update(Cliente parm) throws SQLException {
        PreparedStatement ps = null;

        try {

            String sql = "UPDATE clientes SET nome=?,dat_nascimento=?,cpf=?,rg=?,endereco=?,numero=?,bairro=?,cidade=?,estado=?,cep=? WHERE id = ?";
            ps = conexao.prepareStatement(sql);
            ps.setString(1, parm.getNome());
            ps.setDate(2, parm.getDat_nascimento());
            ps.setString(3, parm.getCpf());
            ps.setString(4, parm.getRg());
            ps.setString(5, parm.getEndereco());
            ps.setString(6, parm.getNumero());
            ps.setString(7, parm.getBairro());
            ps.setString(8, parm.getCidade());
            ps.setString(9, parm.getEstado());
            ps.setString(10, parm.getCep());

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

            String sql = "DELETE FROM clientes WHERE id = ?";
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
        String sql = "select * from clientes";
        List list = new ArrayList();
       
        try{
            ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                 Cliente c = new Cliente();
            c.setNome(rs.getString("nome"));
            c.setDat_nascimento(rs.getDate("dat_nascimento"));
            c.setCpf(rs.getString("cpf"));
            c.setRg(rs.getString("rg"));
            c.setEndereco(rs.getString("endereco"));
            c.setNumero(rs.getString("numero"));
            c.setBairro(rs.getString("bairro"));
            c.setCidade(rs.getString("cidade"));
            c.setEstado(rs.getString("estado"));
            c.setCep(rs.getString("cep"));
            c.setId(rs.getInt("id"));
            list.add(c);
            }
             
         }catch (SQLException ex) {
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
