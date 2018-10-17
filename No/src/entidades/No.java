package entidades;

import interfaces.Sessao;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class No {    

    private Sessao rmiServer;
    
    public No (int porta, String ip, String nome ){
        
        Registry registry;
        int rmiPorta = porta;
        String rmiAddress = ip;
        String rmiNome = nome;        

        Mensagem m1 = new Mensagem(1, "Teste de banco");

        try {
            // get the “registry” 
            registry = LocateRegistry.getRegistry(
                    rmiAddress,
                    rmiPorta
            );
            // look up the remote object 
            rmiServer = (Sessao) (registry.lookup(rmiNome));
            // chama método remoto
            rmiServer.insert(m1);//Insere mensagem na base
            
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }

    }
    
    public static void main (String args[]){        
        No no = new No(1234,"localhost", "Teste" );        
    }
    
}
