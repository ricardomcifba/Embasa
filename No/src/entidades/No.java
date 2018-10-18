package entidades;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import interfaces.SessaoNo;

public class No {    

    private SessaoNo rmiServer;
    
    public No (int porta, String ip, String nome ){
        
        Registry registry;
        int rmiPorta = porta;
        String rmiAddress = ip;
        String rmiNome = nome;        

        String m1 = new String("Create table teste (comando varchar)");

        try {
            // get the “registry” 
            registry = LocateRegistry.getRegistry(
                    rmiAddress,
                    rmiPorta
            );
            // look up the remote object 
            rmiServer = (SessaoNo) (registry.lookup(rmiNome));
            // chama método remoto
            rmiServer.comando(m1);//Insere mensagem na base
            
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }

    }
    
    /*public static void main (String args[]){        
        No no = new No(2345,"localhost", "No1" );        
    }*/
    
}
