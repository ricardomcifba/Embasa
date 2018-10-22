/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

//import dao.sql.MensagemDAOSQL;
import interfaces.Sessao;
import interfaces.SessaoNo;
import java.net.InetAddress;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author e127787
 */
public class RMIPrincipal extends UnicastRemoteObject implements Sessao {

    private int porta;
    private String ip;
    private Registry registro; // rmi registry for lookup the remote objects.
    private ArrayList<Registry> registries = new ArrayList<Registry>();// Lista de Registros
    private Registry registry; // registra servidor externo   
    private int id;
    private String nomeServer;
    //criado atributo estático para evitar que o garbage collector elimine por achar que não está sendo mais usado
    private static RMIPrincipal instance = null;

    public RMIPrincipal(String nome, int porta) throws RemoteException, NotBoundException {

        this.nomeServer = nome;
        this.porta = porta;
        instance = this;
        
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
            registro.rebind(nomeServer, instance);
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
        //classe que envia os comandos sql do servidor principal para os remotos.
        //mudar para enviar para o lider
        try {
            //alterar para mandar para o líder
            registry = LocateRegistry.getRegistry("localhost", 2345);
            //alterar para prgar o nome do lider 
            SessaoNo name = (SessaoNo) (registry.lookup("No1"));
            name.comando(comando);
        } catch (NotBoundException ex) {
            Logger.getLogger(RMIPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Como os Servidores nós já se registram no principal a partir da conexção
    //essa parte d ocódigo está sendo utilizada para adicionar os nós na lista de nós Registrados
    public void addRegistro(String ip, int porta) throws RemoteException {
        registry = LocateRegistry.getRegistry(ip, porta);
        registries.add(registry);
    }
    
    //Lista os nós registrados
    public void listarRegistros() throws RemoteException{
        for(int i = 0; i < registries.size(); i++){
            for(String s : registries.get(i).list())
                System.out.println(s);            
            //String[] s = registries.get(i).list();
            System.out.println(registries.get(i).list());
        }
    }
    
    public void eleigerLider(){
        registries.get(0);                                            
    }
    
    public void removeRegistro(Registry reg){
        registries.remove(reg);
    }

}
