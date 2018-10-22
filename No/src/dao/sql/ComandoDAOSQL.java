/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.sql;

import dao.GenericDAO;
import java.sql.PreparedStatement;
import interfaces.ComandoDAO;


/**
 *
 * @author Aluno
 */
public class ComandoDAOSQL extends GenericDAO implements ComandoDAO{
    
    private static final String INSERT = "insert into blockchain(hash, previous, comando, timestamps) values (?,?,?,?)";
     
    
    @Override
    public void comando(String comando) throws Exception {
        PreparedStatement pStmt = this.getConnection().prepareStatement(comando);
        pStmt.executeUpdate();
        
    }

//    public void insertBC(Block block) throws Exception {
//        PreparedStatement pStmt = this.getConnection().prepareStatement(INSERT);
//        pStmt.setString(1, block.getHash());
//        pStmt.setString(2, block.getPreviousHash());
//        pStmt.setString(3, block.getData());
//        pStmt.setLong(4, block.getTimeStamp());
//        pStmt.executeUpdate();
//    }
    
}
