package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Fornecedor;
import model.Marca;
import model.Produto;

public class ProdutoDAO implements DAO<Produto> {

    Connection conexao;

    public ProdutoDAO() throws InstantiationException, IllegalAccessException {
        this.conexao = ConnectionFactory.getConnection();
    }

    @Override
    public int insert(Produto parm) throws SQLException {
        PreparedStatement ps = null;
        int novoId = 0;
        try {
            conexao.setAutoCommit(false);
            String sql = "INSERT INTO produto (nome, estoque, preco_venda, preco_custo, dat_compra, dat_venda, marca_id, fornecedor_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
            ps = conexao.prepareStatement(sql);
            ps.setString(1, parm.getNome());
            ps.setInt(2, parm.getEstoque());
            ps.setFloat(3, parm.getPrecoVenda());
            ps.setFloat(4, parm.getPrecoCusto());
            ps.setDate(5, parm.getDataCompra());
            ps.setDate(6, parm.getDataVenda());
            ps.setInt(7, parm.getMarca().getId());
            ps.setInt(8, parm.getFornecedor().getId());

            ps.executeUpdate();
            
            ResultSet resultSet = ps.executeQuery("SELECT LAST_INSERT_ID()");

            if (resultSet.next()) {
                novoId = resultSet.getInt("LAST_INSERT_ID()");
            }

            conexao.commit();
            conexao.setAutoCommit(true);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                ps.close();
            } catch (Exception ex2) {
                System.out.println(ex2.getMessage());
            }
        }
        return novoId;
    }

    @Override
    public void update(Produto parm) throws SQLException {
        PreparedStatement ps = null;
        try {

            String sql = "UPDATE produto SET nome = ?, estoque = ?, preco_venda = ?, preco_custo = ?, marca_id = ?,  fornecedor_id = ? WHERE id = ?";
            ps = conexao.prepareStatement(sql);
            ps.setString(1, parm.getNome());
            ps.setInt(2, parm.getEstoque());
            ps.setFloat(3, parm.getPrecoVenda());
            ps.setFloat(4, parm.getPrecoCusto());
            ps.setInt(5, parm.getMarca().getId());
            ps.setInt(6, parm.getFornecedor().getId());
            ps.setInt(7, parm.getId());

            ps.executeUpdate();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                ps.close();
            } catch (Exception ex2) {
                System.out.println(ex2.getMessage());
            }
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        PreparedStatement ps = null;
        try {

            String sql = "DELETE FROM produto WHERE id = ?";
            ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
        } finally {
            try {
                ps.close();
            } catch (Exception ex) {
                System.out.println("Erro: " + ex.getMessage());
            }
        }
    }

    @Override
    public List selectAll() throws SQLException {
        PreparedStatement ps = null;
        String sql = "select * from produto";
        List list = new ArrayList();

        try {
            ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                Produto p = new Produto();

                p.setId(rs.getInt("id"));
                p.setDataCompra(rs.getDate("dat_compra"));
                p.setDataVenda(rs.getDate("dat_venda"));
                p.setEstoque(rs.getInt("estoque"));
                p.setFornecedor(new Fornecedor(rs.getInt("fornecedor_id")));
                p.setMarca(new Marca(rs.getInt("marca_id")));
                p.setNome(rs.getString("nome"));
                p.setPrecoCusto(rs.getFloat("preco_custo"));
                p.setPrecoVenda(rs.getFloat("preco_venda"));

                list.add(p);
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
