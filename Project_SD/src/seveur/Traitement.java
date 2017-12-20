package seveur;

public class Traitement {
	
	 public static char[] oneSpace(char[] cbuf, int off, int len){
	        boolean delSpace = false;
	        for (int i = off; i < off + len; i++) {
	            if (cbuf[i] == ' ' && delSpace) {
	                System.arraycopy(cbuf, i, cbuf, i - 1, cbuf.length - i);
	                i--;
	            } else if (cbuf[i] == ' ' && !delSpace) {
	                delSpace = true;
	            } else {
	                delSpace = false;
	            }
	        }
	        return cbuf;

	    }
}
