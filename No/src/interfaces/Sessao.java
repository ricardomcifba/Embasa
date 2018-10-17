/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entidades.Mensagem;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Aluno
 */
public interface Sessao extends Remote{
  
    public void insert(Mensagem mensagem) throws RemoteException;
    
}
