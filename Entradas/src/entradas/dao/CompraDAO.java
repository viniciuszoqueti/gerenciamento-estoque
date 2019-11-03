/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entradas.dao;

import entradas.model.Compra;
import entradas.model.CompraItem;
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
public class CompraDAO {
    
    public void salvar( Compra compra ) throws DBErrorException, InstantiationException, IllegalAccessException{
    
        if (compra.getId() == 0)
            this.insert(compra);
        else
            this.update(compra);
    }
    
    private void insert(Compra compra) throws DBErrorException, InstantiationException, IllegalAccessException{
        
        PreparedStatement ps = null;
        
        try{
            ps = Conexao.getConexao().prepareStatement("INSERT INTO compras(num_nota, dat_compra, fornecedores_id) VALUES(?,?,?)");
            
            ps.setString(1, compra.getNota());
            ps.setDate(2, new java.sql.Date(compra.getDatCompra().getTimeInMillis()));
            ps.setInt(3, compra.getFornecedor().getId());
        
            if ( ps.executeUpdate() == 0 ){
                throw new DBErrorException("Ocorreu um erro durante o cadastro da compra. Entre em contato com o administrador.");
            }else{
                
                Conexao.getConexao().commit();
                
                compra.setId( buscaUltimoIdInserido() );
                
                insereItens(compra);
            }
        }catch(ClassNotFoundException | SQLException  ex){
            throw new DBErrorException(ex.getMessage());
        }finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                throw new DBErrorException(ex.getMessage());
            }
        }        
    }
    
    private void insereItens(Compra compra) throws DBErrorException, InstantiationException, IllegalAccessException{
        
        PreparedStatement ps = null;
        
        try{
            CompraItem item = null;
            
            for(int i = 0; i < compra.getItens().size(); i++){
                
                item = compra.getItens().get(i);
                
                ps = Conexao.getConexao().prepareStatement("INSERT INTO compras_itens(quantidade, valor, compras_id, produtos_id) VALUES(?,?,?,?)");
               
                ps.setDouble(1, item.getQuantidade());
                ps.setDouble(2, item.getValor());
                ps.setInt(3, compra.getId());
                ps.setInt(4, item.getProduto().getId());

                if ( ps.executeUpdate() == 0 ){
                    throw new DBErrorException("Ocorreu um erro durante o cadastro dos itens da compra. Entre em contato com o administrador.");
                }else{
                    Conexao.getConexao().commit();
                    compra.getItens().get(i).setId(buscaUltimoIdInserido());
                }
            }
            
        }catch(ClassNotFoundException | SQLException  ex){
            throw new DBErrorException(ex.getMessage());
        }finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                throw new DBErrorException(ex.getMessage());
            }
        }        
        
    }
    
    private void update(Compra compra) throws DBErrorException, InstantiationException, IllegalAccessException{
        
        PreparedStatement ps = null;
        
        try{
            ps = Conexao.getConexao().prepareStatement("UPDATE compras SET num_nota = ?, dat_compra = ?, fornecedores_id = ? WHERE id = ?");
            ps.setString(1, compra.getNota());
            ps.setDate(2, new java.sql.Date(compra.getDatCompra().getTimeInMillis()));
            ps.setInt(3, compra.getFornecedor().getId());
            ps.setInt(4, compra.getId());
        
        
            if ( ps.executeUpdate() == 0 ){
                throw new DBErrorException("Ocorreu um erro durante a atualização dos dados da compra. Entre em contato com o administrador.");
            }else{
                Conexao.getConexao().commit();
                updateItens(compra);
            }
            
        }catch(ClassNotFoundException | SQLException  ex){
            throw new DBErrorException(ex.getMessage());
        }finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                throw new DBErrorException(ex.getMessage());
            }
        }        
    }
    
    private void updateItens(Compra compra) throws DBErrorException, InstantiationException, IllegalAccessException{
        removeItens(compra);
        insereItens(compra);
    }
    
    private void removeItens(Compra compra) throws DBErrorException, InstantiationException, IllegalAccessException{
        
        PreparedStatement ps = null;
        
        try{
            ps = Conexao.getConexao().prepareStatement("DELETE FROM compras_itens WHERE compras_id = ?");
            ps.setInt(1, compra.getId());
        
        
            if ( ps.executeUpdate() == 0 ){
                throw new DBErrorException("Ocorreu um erro durante a exclusão dos itens da compra. Entre em contato com o administrador.");
            }
            
        }catch(ClassNotFoundException | SQLException  ex){
            throw new DBErrorException(ex.getMessage());
        }finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                throw new DBErrorException(ex.getMessage());
            }
        }        
        
    }
    
    public void delete(Compra compra) throws DBErrorException, InstantiationException, IllegalAccessException{
        
        PreparedStatement ps = null;
        
        try{
            ps = Conexao.getConexao().prepareStatement("DELETE FROM compras WHERE id = ?");
            ps.setInt(1, compra.getId());
        
        
            if ( ps.executeUpdate() == 0 ){
                throw new DBErrorException("Ocorreu um erro durante a exclusão do grupo. Entre em contato com o administrador.");
            }else{
                removeItens(compra);
            }
            
        }catch(ClassNotFoundException | SQLException  ex){
            throw new DBErrorException(ex.getMessage());
        }finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                throw new DBErrorException(ex.getMessage());
            }
        }        
    
    }

    private int buscaUltimoIdInserido() throws DBErrorException, InstantiationException, IllegalAccessException{

        Statement st = null;
        ResultSet rs = null;
        
        try{
            st = Conexao.getConexao().createStatement();
            rs = st.executeQuery("SELECT LAST_INSERT_ID() AS id");
        
            while(rs.next()){
                return rs.getInt("id");
            }
            
        }catch(ClassNotFoundException | SQLException  ex){
            throw new DBErrorException(ex.getMessage());
        }finally{
            try {
                rs.close();
                st.close();
            } catch (SQLException ex) {
                throw new DBErrorException(ex.getMessage());
            }            
        }        
        return 0;
    }
    
    private Compra buscaPorId(int id) throws DBErrorException, InstantiationException, IllegalAccessException{

        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            ps = Conexao.getConexao().prepareStatement("SELECT * FROM compras WHERE id = ?");
            ps.setInt(1, id);
            
            rs = ps.executeQuery();
        
            while(rs.next()){
                Compra compra = new Compra();
                preencheObjeto(compra, rs);
                return compra;
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

    private void preencheObjeto(Compra compra, ResultSet rs) throws SQLException, ValorInvalidoException, DBErrorException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        
        compra.setId(rs.getInt("id"));
        compra.setNota(rs.getString("num_nota"));

        FornecedorDAO fdao = new FornecedorDAO();
        compra.setFornecedor( fdao.buscaPorId( rs.getInt("fornecedores_id") ) );

        compra.setItens( buscaItensCompra(rs.getInt("id")));
            
    }

    private List<CompraItem> buscaItensCompra(int compra_id) throws SQLException, ValorInvalidoException, DBErrorException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        ArrayList<CompraItem> lista = new ArrayList<>();
        
        ps = Conexao.getConexao().prepareStatement("SELECT * FROM compras_itens WHERE compras_id = ?");
        ps.setInt(1, compra_id);

        rs = ps.executeQuery();

        CompraItem item;
        while(rs.next()){
            
            item = new CompraItem();
            
            preencheObjetoItem(item, rs);
            
            lista.add(item);
        }
            
        return lista;
    }

    private void preencheObjetoItem(CompraItem item, ResultSet rs) throws SQLException, ValorInvalidoException, DBErrorException, InstantiationException, IllegalAccessException {
        
        item.setId(rs.getInt("id"));
        item.setQuantidade(rs.getDouble("quantidade"));
        item.setValor(rs.getDouble("valor"));
        
        ProdutoDAO pdao = new ProdutoDAO();
        item.setProduto( pdao.buscaPorId(rs.getInt("produtos_id")));
        
    }

    public ArrayList<Compra> buscaPorFornecedor(Fornecedor fornecedor) throws DBErrorException, InstantiationException, IllegalAccessException{

        PreparedStatement ps = null;
        ResultSet rs = null;
        
        ArrayList<Compra> lista = new ArrayList<>();
        
        try{
            ps = Conexao.getConexao().prepareStatement("SELECT * FROM compras WHERE fornecedores_id = ?");
            ps.setInt(1, fornecedor.getId());
            
            rs = ps.executeQuery();
            
            Compra c;
            while(rs.next()){
                c = new Compra();
                this.preencheObjeto(c, rs);
                lista.add(c);
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

    public ArrayList<Compra> buscaPorPeriodo(Calendar dataInicial, Calendar dataFinal) throws DBErrorException, InstantiationException, IllegalAccessException{

        PreparedStatement ps = null;
        ResultSet rs = null;
        
        ArrayList<Compra> lista = new ArrayList<>();
        
        try{
            ps = Conexao.getConexao().prepareStatement("SELECT * FROM compras WHERE dat_compra BETWEEN ? AND ?");
            ps.setDate(1, new java.sql.Date(dataInicial.getTimeInMillis()));
            ps.setDate(2, new java.sql.Date(dataFinal.getTimeInMillis()));
            
            rs = ps.executeQuery();
            
            Compra c;
            while(rs.next()){
                c = new Compra();
                this.preencheObjeto(c, rs);
                lista.add(c);
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

    public Compra buscaCompraPorNotaFiscal(String nota) throws DBErrorException, InstantiationException, IllegalAccessException{

        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            ps = Conexao.getConexao().prepareStatement("SELECT * FROM compras WHERE num_nota = ?");
            ps.setString(1, nota);
            
            rs = ps.executeQuery();
        
            while(rs.next()){
                Compra compra = new Compra();
                preencheObjeto(compra, rs);
                return compra;
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
    
    public ArrayList<Compra> getTodasCompras() throws DBErrorException, InstantiationException, IllegalAccessException{

        Statement st = null;
        ResultSet rs = null;
        
        ArrayList<Compra> lista = new ArrayList<>();
        
        try{
            st = Conexao.getConexao().createStatement();
            rs = st.executeQuery("SELECT * FROM compras");
            
            Compra c;
            while(rs.next()){
                c = new Compra();
                this.preencheObjeto(c, rs);
                lista.add(c);
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
