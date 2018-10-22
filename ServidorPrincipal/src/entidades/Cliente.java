/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import interfaces.Sessao;

/**
 *
 * @author Aluno
 */
public class Cliente {
    
    private Sessao rmiServer;
    
    public Cliente (String nome, String ip, int porta){
        
        Registry registry;
        int rmiPorta = porta;
        String rmiAddress = ip;
        String rmiNome = nome;
        
        try {
            // get the “registry” 
            registry = LocateRegistry.getRegistry(
                    rmiAddress,
                    rmiPorta
            );

            // look up the remote object 
            rmiServer = (Sessao) (registry.lookup(rmiNome));

            
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }

    }
    
    public void enviar(String enviar) throws RemoteException{
        rmiServer.comando(enviar);
    }
    
}
