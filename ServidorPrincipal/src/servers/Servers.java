/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servers;

import entidades.RMIPrincipal;
import java.rmi.server.ServerNotActiveException;
import java.util.ArrayList;
import sun.rmi.transport.tcp.TCPTransport;

/**
 *
 * @author Aluno
 */
public class Servers {
    private ArrayList<RMIPrincipal> servers = new ArrayList<RMIPrincipal>();

    
    public void addServer(RMIPrincipal rmiserver){
        servers.add(rmiserver);
    }
    
    public void removeServer(RMIPrincipal rmiserver){
        servers.remove(rmiserver);
    }
    
    public String getClientHost() throws ServerNotActiveException {
        return TCPTransport.getClientHost();
    }
    
//    public void eleicao() {
//        if (servers.get(0).getId() == 0) {// pegar id dos clientes - id = 0 primeiro lider
//
//        }
//    }
    
}
