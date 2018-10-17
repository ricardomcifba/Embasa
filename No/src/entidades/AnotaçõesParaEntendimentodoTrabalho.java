/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author Aluno
 */
public class AnotaçõesParaEntendimentodoTrabalho {
    /*
    para testar o código, será necessário criar uma base de dados no Postgres de nome SD.
    o login e a senha do postgres deverão ser alterados conforme suas configurações
    basta ir na classe GenericDAO e alterar as Strings USER e PWD
    para criar a tabela na base basta utilizar o comando abaixo
    
    create table mensagem (
	id int,
	mensagem varchar
    );
    
    O Cliente faz requisições no servidor principal
    
    
    */
    


    /*
    Mudanças feitar posteriormente, desfeitas com control z
    
    public RMINo(String ipPrincipal, int portaPrincipal, String nome, int id, int porta) throws RemoteException, NotBoundException, AlreadyBoundException {
        
        
        
        //System.out.println("Digite o nome para o servidor:");
        Registry registry;
        this.nomeServer = nome;
        this.porta = porta;
        this.id = id;
        
        //registrando servidor atual no servidor principal - 
        //Assim no servidor criado vai estar registrado no principal
        registry = LocateRegistry.getRegistry(ipPrincipal, portaPrincipal);
        //registry.bind(nomeServer, this);
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
            registro = LocateRegistry.getRegistry(ip, porta);
            registro.bind(this.nomeServer, this);
        } catch (RemoteException e) {
            throw e;
        }
        
    }
    
    private ComandoDAOSQL mensagemDAOSQL = new ComandoDAOSQL();
    
    @Override
    public void comando(String comando) {

        try {
            mensagemDAOSQL.comando(comando);
        } catch (Exception ex) {
            Logger.getLogger(RMINo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    
}

    */
    
}
