package blockchain;

import java.util.Date;

public class BlockchainJouet {

    public String hash;
    public String hashPrecedent;
    private String donneeBlock;
    private long timeStamp;
    private int nonce;

    public BlockchainJouet(String donneeBlock, String hashPrecedent) {
        this.donneeBlock = donneeBlock;
        this.hashPrecedent = hashPrecedent;
        this.timeStamp = new Date().getTime();

        this.hash = calcHash();
    }

    public String calcHash() {
        String hashCalc = StringUtil.toSha256(hashPrecedent + Long.toString(timeStamp) + Integer.toString(nonce) + donneeBlock);
        return hashCalc;
    }

    public void BlockExtr(int difficulty) {
        String cible = new String(new char[difficulty]).replace('\0', '0');
        while(!hash.substring( 0, difficulty).equals(cible)) {
            nonce ++;
            hash = calcHash();
        }
        System.out.println("Block extrait!!! : " + hash);
    }
}
