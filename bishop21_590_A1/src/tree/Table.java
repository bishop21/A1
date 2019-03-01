package tree;

import java.io.IOException;
import java.util.ArrayList;
import io.InputStreamBitSource;
import io.InsufficientBitsLeftException;

public class Table{
    int progress;
    int[] table= new int[256];
    ArrayList<CharLenPair> sortedTable = new ArrayList<CharLenPair>();
    
    public Table(InputStreamBitSource isbs) {
    
    	for(int i=0;i<256;i++) {
    		try {
    			table[i] = isbs.next(8);
    		} catch (InsufficientBitsLeftException e) {
    			e.printStackTrace();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}

    	}

    	for (int currLen = 0; currLen<17;currLen++) {
    		for (int j=0;j<256;j++) {
    			if(table[j]==currLen) {
    				sortedTable.add(new CharLenPair((char)j, currLen));	
    			}
    		}
    	}
    	
    	//for (int l=0;l<256;l++) {System.out.println("char: " + sortedTable.get(l).Char + " len: " + sortedTable.get(l).len);}
	
    }
}
