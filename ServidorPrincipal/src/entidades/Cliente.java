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
        
        String m1 = new String("Create table teste (comando varchar);");

        try {
            // get the “registry” 
            registry = LocateRegistry.getRegistry(
                    rmiAddress,
                    rmiPorta
            );

            // look up the remote object 
            rmiServer = (Sessao) (registry.lookup(rmiNome));
            rmiServer.comando(m1);
            // call the remote method
            //rmiServer.addAluno(a1);//Adiciona A,luno
            //rmiServer.find(nome);// Procura pelo nome
            //rmiServer.removeAluno(nome); //remove aluno pelo nome
            
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }

    }
    
    public static void main(String[] args) {
    
        Cliente c = new Cliente("Teste", "localhost", 1234);
    
    }
    
}
