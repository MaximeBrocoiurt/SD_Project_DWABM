package blockchain;

import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class MainChain {

    public static ArrayList<BlockchainJouet> blockchain = new ArrayList<BlockchainJouet>();
    public static int difficulty = 5;

    public static void main(String[] args) {

        blockchain.add(new BlockchainJouet("Block numéro 1", "0"));
        System.out.println("Extraction block 1... ");
        blockchain.get(0).BlockExtr(difficulty);

        blockchain.add(new BlockchainJouet("Block numéro 2", blockchain.get(blockchain.size() - 1).hash));
        System.out.println("Extraction block 2... ");
        blockchain.get(1).BlockExtr(difficulty);

        blockchain.add(new BlockchainJouet("Block numéro 3",blockchain.get(blockchain.size() - 1).hash));
        System.out.println("Extraction block 3... ");
        blockchain.get(2).BlockExtr(difficulty);

        System.out.println("\nLa blockchain est valide : " + isValidChain());

        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println("\nRésultat : ");
        System.out.println(blockchainJson);

    }

    public static Boolean isValidChain() {
        BlockchainJouet blockActuel;
        BlockchainJouet blockPrecedent;

        for(int i = 1; i < blockchain.size(); i++) {
            blockActuel = blockchain.get(i);
            blockPrecedent = blockchain.get(i - 1);

            if(!blockActuel.hash.equals(blockActuel.calcHash()) ){
                System.out.println("Le block actuel n'est pas correct");
                return false;
            }

            if(!blockPrecedent.hash.equals(blockActuel.hashPrecedent) ) {
                System.out.println("Le block précédent n'est pas correct");
                return false;
            }
        }
        return true;
    }
}
