/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import blockchain.Block;
import static blockchain.NoobChain.blockchain;
import com.google.gson.GsonBuilder;
import dao.sql.ComandoDAOSQL;
import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import interfaces.SessaoNo;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.util.ArrayList;
/**
 *
 * @author e127787
 */
public class RMINo extends UnicastRemoteObject implements SessaoNo {

    private ArrayList<Block> blockchains = new ArrayList<>();
    private RMIPrincipal rmi;
    private int porta;
    private String ip;
    private Registry registro;   // rmi registry for lookup the remote objects.
    private int id;
    private String nomeServer;
    private static RMINo instance = null;
    private ComandoDAOSQL comandoSQL = new ComandoDAOSQL();
       
    public RMINo(String ipPrincipal, int portaPrincipal, String nome, int id, int porta) throws RemoteException, NotBoundException, AlreadyBoundException {
        
        Registry registry;
        this.nomeServer = nome;
        this.porta = porta;
        this.id = id;
        instance = this;
        
        //registrando servidor atual no servidor principal - Assim o servidor criado vai estar registrado no principal
        registry = LocateRegistry.getRegistry(ipPrincipal, portaPrincipal);
        
        //Sessao s1 = (Sessao)(registry.lookup("Teste"));
        //System.out.println("Servidor: "+ s1);
        
        //A partir daqui ele cria um servidor para o nó
        try {
            // get the address of this host.
         ip = InetAddress.getLocalHost().getHostAddress();
        } catch (Exception e) {
            throw new RemoteException("can't get inet address.");
        }
        
        System.out.println("IP de endereço do Nó servidor = " + ip + ", port= " + porta);
        try {
            // create the registry and bind the name and object.
            registro = LocateRegistry.createRegistry(porta);
            registro.rebind(nomeServer, instance);

        } catch (RemoteException e) {
            throw e;
        }
        
    }
    
        public RMINo(String ipPrincipal, int portaPrincipal, String nome, int porta) throws RemoteException, NotBoundException, AlreadyBoundException {
        
        Registry registry;
        this.nomeServer = nome;
        this.porta = porta;
        this.id = id;
        instance = this;
        
        //registrando servidor atual no servidor principal - Assim o servidor criado vai estar registrado no principal
        registry = LocateRegistry.getRegistry(ipPrincipal, portaPrincipal);
        
        //Sessao s1 = (Sessao)(registry.lookup("Teste"));
        //System.out.println("Servidor: "+ s1);
        
        //A partir daqui ele cria um servidor para o nó
        try {
            // get the address of this host.
         ip = InetAddress.getLocalHost().getHostAddress();
        } catch (Exception e) {
            throw new RemoteException("can't get inet address.");
        }
        
        System.out.println("IP de endereço do Nó servidor = " + ip + ", port= " + porta);
        try {
            // create the registry and bind the name and object.
            registro = LocateRegistry.createRegistry(porta);
            registro.rebind(nomeServer, instance);

        } catch (RemoteException e) {
            throw e;
        }
        
    }


    public void blockchain(String comando) throws RemoteException, Exception{
        if(blockchains.size() == 0){
            blockchains.add(new Block(comando, "0"));
            
        }
        else{
            blockchains.add(new Block(comando, blockchains.get(blockchains.size() - 1).hash));
        }
        //comandoSQL.insertBC(blockchains.get(blockchains.size()));
    }
    
    @Override
    public void comando(String comando) {
        //Criar implementação do blockchain
        try {
            comandoSQL.comando(comando);
            blockchain(comando);
        } catch (Exception ex) {
            Logger.getLogger(RMINo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
//    @Override
//    public void insertBC(Block block) throws RemoteException {
//        try {
//            comandoSQL.insertBC(block);
//        } catch (Exception ex) {
//            Logger.getLogger(RMINo.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    
    public void consultarBC(){
        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchains);
        System.out.println(blockchainJson);
    }
    
}
