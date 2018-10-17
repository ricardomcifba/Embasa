/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

//import dao.sql.MensagemDAOSQL;
import interfaces.Sessao;
import java.io.Serializable;
import java.net.InetAddress;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;
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
    
    Scanner ler = new Scanner(System.in);

    public int getPorta() {
        return porta;
    }

    public void setPorta(int porta) {
        this.porta = porta;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Registry getRegistro() {
        return registro;
    }

    public void setRegistro(Registry registro) {
        this.registro = registro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RMIPrincipal(String nome, int porta) throws RemoteException, NotBoundException {

        //System.out.println("Digite o nome para o servidor:");
        this.nomeServer = nome;
        this.porta = porta;
        try {
            // get the address of this host.
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (Exception e) {
            throw new RemoteException("can't get inet address.");
        }
        //porta = 3231;  // this port(registry’s port)
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
            Sessao s1 = (Sessao)(registro.lookup(this.nomeServer));
            System.out.println(s1);
            System.out.println("batatatatata");

            
        } catch (RemoteException e) {
            throw e;
        }

    }

    public void enviar(Serializable data) {

    }

//    static public void main(String args[]) {
//        Scanner ler = new Scanner(System.in);
//        int id = 1;
//        System.out.println("Digite o numero da porta:");
//        int porta = ler.nextInt();
//        System.out.println("Digite o nome do servidor:");
//        String nome = ler.nextLine();
//        try {
//            RMIPrincipal s = new RMIPrincipal(nome, id, porta);
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.exit(1);
//        }
//    }
//
//    private MensagemDAOSQL mensagemDAOSQL = new MensagemDAOSQL();
//
//    @Override
//    public void insert(Mensagem mensagem) {
//
//        try {
//            mensagemDAOSQL.insert(mensagem);
//        } catch (Exception ex) {
//            Logger.getLogger(RMIPrincipal.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }

}
