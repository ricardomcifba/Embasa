/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.sql;

import dao.GenericDAO;
import entidades.Mensagem;
import interfaces.MensagemDAO;
import java.sql.PreparedStatement;

/**
 *
 * @author Aluno
 */
public class MensagemDAOSQL extends GenericDAO implements MensagemDAO{
    
    private static final String INSERT = "insert into mensagem(id, mensagem) values (?, ?)";
    
    @Override
    public void insert(Mensagem mensagem) throws Exception {
        PreparedStatement pStmt = this.getConnection().prepareStatement(MensagemDAOSQL.INSERT);
        pStmt.setInt(1, mensagem.getId());
        pStmt.setString(2, mensagem.getTexto());
        pStmt.executeUpdate();
    }
    
}
