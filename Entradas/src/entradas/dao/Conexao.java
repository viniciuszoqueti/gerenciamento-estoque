/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entradas.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Tiago
 */
public class Conexao {
 
    public static Connection con = null;
    
    public static Connection getConexao() throws ClassNotFoundException, SQLException {
        if ( con == null)
            criaConexao();
        return con;
    }
    
    public static void criaConexao() throws ClassNotFoundException, SQLException{
        
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection ("jdbc:mysql://127.0.0.1:3306/compras", "root", "");
        con.setAutoCommit(false);
        con.setTransactionIsolation(con.TRANSACTION_READ_COMMITTED);
        
    }
    
    public static void fechaConexao() throws SQLException{
        con.close();
    }

    
}
