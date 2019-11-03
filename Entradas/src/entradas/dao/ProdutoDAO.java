/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entradas.dao;

import entradas.model.Produto;
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
public class ProdutoDAO {

    public Produto buscaPorId(int id)  throws DBErrorException{
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            ps = Conexao.getConexao().prepareStatement("SELECT * FROM produtos WHERE id = ?");
            ps.setInt(1, id);
            
            rs = ps.executeQuery();
        
            Produto p;
            while(rs.next()){
                p = new Produto();
                preencheObjeto(p, rs);
                
                return p;
            }
            
        }catch(ClassNotFoundException | SQLException | ValorInvalidoException ex){
            throw new DBErrorException(ex.getMessage());
        }finally{
            try {
                rs.close();
                ps.close();
            } catch (SQLException ex) {
                throw new DBErrorException(ex.getMessage());
            }            
        }        
        
        return null;
    }

    private void preencheObjeto(Produto p, ResultSet rs) throws DBErrorException, SQLException, ValorInvalidoException{

        p.setCodigoBarras(rs.getString("codigo_barras"));
        p.setCusto(rs.getDouble("vlr_custo"));
        p.setId(rs.getInt("id"));
        p.setMargemLucro(rs.getDouble("margem_lucro"));
        p.setNome(rs.getString("nome"));
        p.setVenda(rs.getDouble("vlr_venda"));
        
        Calendar c = Calendar.getInstance();
        if (rs.getDate("dat_ultima_compra") != null){
            c.setTimeInMillis(rs.getDate("dat_ultima_compra").getTime());
        }
        p.setDatUltimaCompra(c);
        
        Calendar c2 = Calendar.getInstance();
        if (rs.getDate("dat_ultima_venda") != null){
            c.setTimeInMillis(rs.getDate("dat_ultima_venda").getTime());
        }
        p.setDatUltimaVenda(c2);
        
        p.setEstoque(rs.getInt("estoque"));
        
        FornecedorDAO fdao = new FornecedorDAO();
        p.setFornecedor(fdao.buscaPorId(rs.getInt("fornecedores_id")));
        
        
    }

    public List<Produto> buscaPorDescricao(String desc) throws DBErrorException{

        PreparedStatement ps = null;
        ResultSet rs = null;
        
        List<Produto> lista = new ArrayList<>();
        
        try{
            ps = Conexao.getConexao().prepareStatement("SELECT * FROM produtos WHERE nome like ? ORDER BY nome");
            ps.setString(1, "%" + desc + "%");
            
            rs = ps.executeQuery();
        
            Produto p;
            while(rs.next()){
                p = new Produto();
                preencheObjeto(p, rs);
                
                lista.add(p);
            }
            
        }catch(ClassNotFoundException | SQLException | ValorInvalidoException  ex){
            throw new DBErrorException(ex.getMessage());
        }finally{
            try {
                rs.close();
                ps.close();
            } catch (SQLException ex) {
                throw new DBErrorException(ex.getMessage());
            }            
        }        
        
        return lista;
    }
    
    public List<Produto> buscaTodos() throws DBErrorException{

        Statement st = null;
        ResultSet rs = null;
        
        List<Produto> lista = new ArrayList<>();
        
        try{
            st = Conexao.getConexao().createStatement();
            
            
            rs = st.executeQuery("SELECT * FROM produtos ORDER BY nome");
        
            Produto p;
            while(rs.next()){
                p = new Produto();
                preencheObjeto(p, rs);
                
                lista.add(p);
            }
            
        }catch(ClassNotFoundException | SQLException | ValorInvalidoException  ex){
            throw new DBErrorException(ex.getMessage());
        }finally{
            try {
                rs.close();
                st.close();
            } catch (SQLException ex) {
                throw new DBErrorException(ex.getMessage());
            }            
        }        
        
        return lista;
    }
    
}
