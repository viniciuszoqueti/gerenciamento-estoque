/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entradas.dao;

import com.sun.org.omg.CORBA.ParameterMode;
import entradas.model.Fornecedor;
import entradas.model.ValorInvalidoException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Tiago
 */
public class FornecedorDAO {

    public Fornecedor buscaPorId(int id) throws DBErrorException, InstantiationException, IllegalAccessException {

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = Conexao.getConexao().prepareStatement("SELECT * FROM fornecedores WHERE id = ?");
            ps.setInt(1, id);

            rs = ps.executeQuery();

            Fornecedor f;
            while (rs.next()) {
                f = new Fornecedor();
                preencheObjeto(f, rs);

                return f;
            }

        } catch (ClassNotFoundException | SQLException | ValorInvalidoException ex) {
            throw new DBErrorException(ex.getMessage());
        } finally {
            try {
                rs.close();
                ps.close();
            } catch (SQLException ex) {
                throw new DBErrorException(ex.getMessage());
            }
        }

        return null;
    }

    private void preencheObjeto(Fornecedor f, ResultSet rs) throws DBErrorException, SQLException, ValorInvalidoException {

        f.setBairro(rs.getString("bairro"));
        f.setCelular(rs.getString("celular"));
        f.setCidade(rs.getString("cidade"));
        f.setCnpj(rs.getString("cnpj"));
        f.setContato(rs.getString("nome_contato"));

        Calendar c = Calendar.getInstance();
        if (rs.getDate("dat_ultima_compra") != null) {
            c.setTimeInMillis(rs.getDate("dat_ultima_compra").getTime());
        }
        f.setDatUltimaCompra(c);

        f.setEndereco(rs.getString("endereco"));
        f.setEstado(rs.getString("estado"));
        f.setFantasia(rs.getString("nome_fantasia"));
        f.setId(rs.getInt("id"));
        f.setInscricao(rs.getString("inscricao"));
        f.setNumero(rs.getString("numero"));
        f.setRazao(rs.getString("razao"));
        f.setTelefone(rs.getString("telefone"));

    }

    public List<Fornecedor> buscaPorRazao(String razao) throws DBErrorException, InstantiationException, IllegalAccessException {

        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Fornecedor> lista = new ArrayList<>();

        try {
            ps = Conexao.getConexao().prepareStatement("SELECT * FROM fornecedores WHERE razao like ?");
            ps.setString(1, "%" + razao + "%");

            rs = ps.executeQuery();

            Fornecedor f;
            while (rs.next()) {
                f = new Fornecedor();
                preencheObjeto(f, rs);

                lista.add(f);
            }

        } catch (ClassNotFoundException | SQLException | ValorInvalidoException ex) {
            throw new DBErrorException(ex.getMessage());
        } finally {
            try {
                rs.close();
                ps.close();
            } catch (SQLException ex) {
                throw new DBErrorException(ex.getMessage());
            }
        }

        return lista;
    }

    public List<Fornecedor> buscaTodos() throws DBErrorException, InstantiationException, IllegalAccessException {

        Statement st = null;
        ResultSet rs = null;

        List<Fornecedor> lista = new ArrayList<>();

        try {
            st = Conexao.getConexao().createStatement();

            rs = st.executeQuery("SELECT * FROM fornecedores");

            Fornecedor f;
            while (rs.next()) {
                f = new Fornecedor();
                preencheObjeto(f, rs);

                lista.add(f);
            }

        } catch (ClassNotFoundException | SQLException | ValorInvalidoException ex) {
            throw new DBErrorException(ex.getMessage());
        } finally {
            try {
                rs.close();
                st.close();
            } catch (SQLException ex) {
                throw new DBErrorException(ex.getMessage());
            }
        }

        return lista;
    }

    public void insert(Fornecedor parm) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        PreparedStatement ps = null;

        try {

            String sql = "INSERT INTO `fornecedores` (razao, nome_fantasia, telefone, celular, nome_contato, cnpj, inscricao, id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            ps = Conexao.getConexao().prepareStatement(sql);

            ps.setString(1, parm.getRazao());
            ps.setString(2, parm.getFantasia());
            ps.setString(3, parm.getTelefone());
            ps.setString(4, "(11) 1 1111-111");
            ps.setString(5, parm.getFantasia());
            ps.setString(6, parm.getCnpj());
            ps.setString(7, parm.getInscricao());
            ps.setInt(8, parm.getId());

            ps.executeUpdate();
            Conexao.getConexao().commit();

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

    public void update(Fornecedor parm) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {

        PreparedStatement ps = null;

        try {

            String sql = "UPDATE `fornecedores` SET `razao` = ?,`nome_fantasia`= ?, telefone= ?, celular= ?, nome_contato= ?, `cnpj`= ?, inscricao WHERE `id` = ?";

            ps = Conexao.getConexao().prepareStatement(sql);

            ps.setString(1, parm.getRazao());
            ps.setString(2, parm.getFantasia());
            ps.setString(3, parm.getTelefone());
            ps.setString(4, "(11) 1 1111-111");
            ps.setString(5, parm.getFantasia());
            ps.setString(6, parm.getCnpj());
            ps.setString(7, parm.getInscricao());
            ps.setInt(8, parm.getId());

            ps.executeUpdate();
            Conexao.getConexao().commit();

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

    public void delete(int id) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        PreparedStatement ps = null;
        try {

            String sql = "DELETE FROM fornecedores WHERE id = ?";
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            Conexao.getConexao().commit();

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

}
