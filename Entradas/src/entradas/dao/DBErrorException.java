/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entradas.dao;

/**
 *
 * @author Tiago
 */
public class DBErrorException extends Exception{

    public DBErrorException(String msg) {
        super(msg);
    }
    
}
