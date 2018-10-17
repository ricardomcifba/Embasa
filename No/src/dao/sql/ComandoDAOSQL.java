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
    
    private static final String COMANDO = "?";
    
    @Override
    public void comando(String comando) throws Exception {
        PreparedStatement pStmt = this.getConnection().prepareStatement(ComandoDAOSQL.COMANDO);
        pStmt.setString(1, comando);
        pStmt.executeUpdate();
        
    }
    
}
