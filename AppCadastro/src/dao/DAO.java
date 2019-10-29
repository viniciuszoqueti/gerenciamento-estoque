/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.List;


/**
 *
 * @author 23021-9
 * @param <T>
 */
public interface DAO<T> {
    
    public abstract void insert(T parm)throws SQLException;
    public abstract void update(T parm)throws SQLException;
    public abstract void delete(int id)throws SQLException;
    public abstract List selectAll()throws SQLException;
    
}
    
  
   
    
    

