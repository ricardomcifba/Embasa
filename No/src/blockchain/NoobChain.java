/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockchain;

import java.util.ArrayList;
import com.google.gson.GsonBuilder;

/**
 *
 * @author e127787
 */
public class NoobChain {
    
    public static ArrayList<Block> blockchain = new ArrayList<Block>();

    public static void main(String[] args) {
        
        String m1 = new String("Teste de banco 1");
        String m2 = new String("Teste de banco 2");
        String m3 = new String("Teste de banco 3");

        blockchain.add(new Block(m1, "0"));
        blockchain.add(new Block(m2, blockchain.get(blockchain.size() - 1).hash));
        blockchain.add(new Block(m3, blockchain.get(blockchain.size() - 1).hash));

        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println(blockchainJson);

    }
}
