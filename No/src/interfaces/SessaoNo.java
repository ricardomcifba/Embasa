/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import blockchain.Block;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Aluno
 */
public interface SessaoNo extends Remote{
  
    public void comando(String comando) throws RemoteException;
//    public void insertBC(Block block)  throws RemoteException;    
}
