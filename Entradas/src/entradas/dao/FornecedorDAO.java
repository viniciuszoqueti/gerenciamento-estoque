/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entradas.dao;

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

    public Fornecedor buscaPorId(int id) throws DBErrorException{

        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            ps = Conexao.getConexao().prepareStatement("SELECT * FROM fornecedores WHERE id = ?");
            ps.setInt(1, id);
            
            rs = ps.executeQuery();
        
            Fornecedor f;
            while(rs.next()){
                f = new Fornecedor();
                preencheObjeto(f, rs);
                
                return f;
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
        
        return null;
    }

    private void preencheObjeto(Fornecedor f, ResultSet rs) throws DBErrorException, SQLException, ValorInvalidoException{
        
        f.setBairro(rs.getString("bairro"));
        f.setCelular(rs.getString("celular"));
        f.setCidade(rs.getString("cidade"));
        f.setCnpj(rs.getString("cnpj"));
        f.setContato(rs.getString("nome_contato"));
        
        Calendar c = Calendar.getInstance();
        if (rs.getDate("dat_ultima_compra") != null){
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
    
    public List<Fornecedor> buscaPorRazao(String razao) throws DBErrorException{

        PreparedStatement ps = null;
        ResultSet rs = null;
        
        List<Fornecedor> lista = new ArrayList<>();
        
        try{
            ps = Conexao.getConexao().prepareStatement("SELECT * FROM fornecedores WHERE razao like ?");
            ps.setString(1, "%" + razao + "%");
            
            rs = ps.executeQuery();
        
            Fornecedor f;
            while(rs.next()){
                f = new Fornecedor();
                preencheObjeto(f, rs);
                
                lista.add(f);
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
    
    public List<Fornecedor> buscaTodos() throws DBErrorException{

        Statement st = null;
        ResultSet rs = null;
        
        List<Fornecedor> lista = new ArrayList<>();
        
        try{
            st = Conexao.getConexao().createStatement();
            
            
            rs = st.executeQuery("SELECT * FROM fornecedores");
        
            Fornecedor f;
            while(rs.next()){
                f = new Fornecedor();
                preencheObjeto(f, rs);
                
                lista.add(f);
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
