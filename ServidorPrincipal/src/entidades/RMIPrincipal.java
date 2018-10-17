/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

//import dao.sql.MensagemDAOSQL;
import interfaces.Sessao;
import java.net.InetAddress;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author e127787
 */
public class RMIPrincipal extends UnicastRemoteObject implements Sessao {

    private int porta;
    private String ip;
    private Registry registro;    // rmi registry for lookup the remote objects.
    private int id;
    private String nomeServer;

    public RMIPrincipal(String nome, int porta) throws RemoteException, NotBoundException {

        this.nomeServer = nome;
        this.porta = porta;
        try {
            // Pega o ip da máquina
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (Exception e) {
            throw new RemoteException("can't get inet address.");
        }
        
        System.out.println("RMIPrincipal: IP de endereço do servidor = " + ip + ", port= " + porta);
        try {
            // create the registry and bind the name and object.
            registro = LocateRegistry.createRegistry(porta);
            registro.rebind(nomeServer, this);
            
            //O REGISTRO TEM Q SER O MESMO DO RMISERVER, ARRUMAR ALGUMA FORMA DE COMPARTILHA-LO 
            //OU CONECTAR O RMI SERVER AO MESMO DO DE CÁ
            //OU TRAZER ESSE SERVER PARA O OUTRO CÓDIGO
            
            //Porque, basicamente, para cada nó você está criando um registro único para cada (Ver linha 83 do RMIPrincipal do outro arquivo)
            //por isso, não há uma rede de comunicação entre eles (pois estes ficam referenciando ao registro proprio)
            //Aqui também, o mesmo, ele tem um proprio registro
            //isso impossibilita a comunicação entre os demais (não é atoa que NO1 não está sendo achado)
            //Precisamos arrumar uma forma de tornar um registro comum a todos
            //eu imagino que esse registro fique no servidor principal e para cada conexão, ele adicione
            //posso tá falando merda ;~; mas o q eu entendi de RMI foi basicamente isso LUL
            //Por exemplo, ao executar o arquivo atual (ESTEAQUI) ponha o nome dele de ss
            //e mude a string abaixo
            //voce verá q ocorrerá o print

            
        } catch (RemoteException e) {
            throw e;
        }

    }

    @Override
    public void comando(String comando) throws RemoteException, AccessException {
        try {
            Sessao name = (Sessao) (registro.lookup("No1"));
            name.comando(comando);
//            for(String s : registro.list())
//                System.out.println(s);
        } catch (NotBoundException ex) {
            Logger.getLogger(RMIPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
