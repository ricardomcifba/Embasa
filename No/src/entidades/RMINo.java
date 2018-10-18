/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import dao.sql.ComandoDAOSQL;
import interfaces.Sessao;
import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import interfaces.SessaoNo;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
/**
 *
 * @author e127787
 */
public class RMINo extends UnicastRemoteObject implements SessaoNo {

    private RMIPrincipal rmi;
    private int porta;
    private String ip;
    private Registry registro;    // rmi registry for lookup the remote objects.
    private int id;
    private String nomeServer;
    private static RMINo instance = null;
    Scanner ler = new Scanner(System.in);
    
       
    public RMINo(String ipPrincipal, int portaPrincipal, String nome, int id, int porta) throws RemoteException, NotBoundException, AlreadyBoundException {
        
        
        
        //System.out.println("Digite o nome para o servidor:");
        Registry registry;
        this.nomeServer = nome;
        this.porta = porta;
        this.id = id;
        instance = this;
        
        //registrando servidor atual no servidor principal - 
        //Assim no servidor criado vai estar registrado no principal
        //System.setProperty("principal",ipPrincipal);
        registry = LocateRegistry.getRegistry(ipPrincipal, portaPrincipal);
        
       // for(String s : registry.list())
       //     System.out.println(s);
        
        //registry.rebind("No1", this);
        
        Sessao s1 = (Sessao)(registry.lookup("Teste"));
        System.out.println("Servidor: "+ s1);
        
        //A partir daqui ele cria um servidor para o nó
        try {
            // get the address of this host.
         ip = InetAddress.getLocalHost().getHostAddress();
        } catch (Exception e) {
            throw new RemoteException("can't get inet address.");
        }
        //porta = 3231;  // this port(registry’s port)
        System.out.println("IP de endereço do servidor = " + ip + ", port= " + porta);
        try {
            // create the registry and bind the name and object.
            registro = LocateRegistry.createRegistry(porta);
            registro.rebind(nomeServer, this);

        } catch (RemoteException e) {
            throw e;
        }
        
    }
    
    /*static public void main(String args[]) {
        Scanner ler = new Scanner(System.in);
        int id = 1;
        System.out.println("Digite o numero da porta:");
        int porta = ler.nextInt();
        try {
            RMIServer s = new RMIServer(id, porta);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }*/
    private ComandoDAOSQL mensagemDAOSQL = new ComandoDAOSQL();
    
    @Override
    public void comando(String comando) {
        //Criar implementação do blockchain
        try {
            mensagemDAOSQL.comando(comando);
        } catch (Exception ex) {
            Logger.getLogger(RMINo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    
}
