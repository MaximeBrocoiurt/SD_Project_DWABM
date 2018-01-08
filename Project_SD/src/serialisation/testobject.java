package serialisation;

import java.net.*;
import java.io.*;

class testobject implements Serializable {
	int value ;
	String id;
	
	public  testobject(int v, String s ){
		this.value=v;
		this.id=s;
	}
	
	public String toString() {
		String res="id : "+id+" value : "+value;
		return res;
	}
}