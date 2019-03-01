package tree;

import java.util.Map;

public class HT implements TreeInterface{
	public CharLenPair root;
	public CharLenPair curr;
	public CharFreq froot;
	public CharFreq temp;
	
	public boolean[] loc;
	
	public HT() {
		root = new CharLenPair('r', -2); 	//the -1 length denotes a non-leaf node; -2 denotes new tree; 
		//root.loc = -1;			   		//-3 denotes a non-leaf node whose children are all occupied;
	}										//-4 is just a test node
	
	public void addNode(CharLenPair clp) {
		if(loc == null) {									//if the tree is new....
			loc = new boolean[clp.codeLen]; 
			curr = root;
			for(int i=0;i<clp.codeLen-1;i++) {					//place the first node below the root, using all 0's
				curr.leftChild = new CharLenPair('x',-1);	//traverse left for 0
				curr = curr.leftChild;						//set curr to the newly created left child
			}
			curr.leftChild = clp;							//place the first node!
		}
		
		else {												//if the tree is established...
			curr = root;
			if(clp.codeLen > loc.length) {
				loc = appendZeros(loc, clp.codeLen);
			}
			//System.out.println(clp.Char);
			//for(int i=0;i<clp.codeLen;i++) {
				//System.out.println(loc[i]);
			//}
			for(int i=0;i<clp.codeLen;i++) {
				if(!loc[i]) {
					if(curr.leftChild == null) {curr.leftChild = new CharLenPair('y',-1);} //null check
					curr = curr.leftChild;
				}
				else {
					if(curr.rightChild == null) {curr.rightChild = new CharLenPair('y',-1);} //null check
					curr = curr.rightChild;
				}
			}
			curr.sym = clp.sym;
			curr.codeLen = clp.codeLen;
		}
		addOne(loc);
		
	}
	
	
	public boolean[] appendZeros(boolean[] in, int desiredLen) {
		boolean[] output = new boolean[desiredLen];
		for(int i=0;i<in.length;i++) {
			output[i] = in[i];
		}
		for(int i=in.length;i<desiredLen;i++) {
			output[i] = false;
		}
		return output;
	}
	
	public boolean addOne(boolean[] in) {
		int rightMostZero = -1;
		for(int i=in.length-1;i>=0;i--) {
			if(!in[i]) {
				rightMostZero = i;
				break;
			}
		}
		if(rightMostZero != -1) {
			for(int i=rightMostZero; i<in.length;i++) {
				in[i] = !in[i];
			}
		}
		return (rightMostZero == -1);
	}

	public String findCode(CharFreq cf) {
		String str = "";
		int depth = 0;
		temp = cf;
		if(loc == null) {			//if the tree is new....
			while(temp.len==-1) {
				temp = cf.leftChild;
				str+= "0";
				depth++;
				//System.out.println("while loop");
			}
			loc = new boolean[depth];
			addOne(loc);
			return temp.Char + str;
		}
		
		else {						//if the tree already has a loc[] 
			int i=0;
			while(true) {
				if(!loc[i]) {								//if the next node is left
					System.out.println("Navigating left...");
					if(temp.leftChild == null) {			//make sure left isn't null
						System.out.println("Left is null! Checking if leaf.");
						//if "left" is null, is "left" a leaf or do we need to add zeroes?
						if(temp.rightChild == null) {	//leaf? add a 0 and we're done
							str+="0";
							System.out.println("Left is leaf. Printing char and code.");
							break;
						} 
						else{		 //not a leaf? try again with corrected loc[]
							loc = appendZeros(loc, loc.length+1);
							System.out.println("Left is not leaf. Trying again.");
						}
					} 
					temp = temp.leftChild;					//if left is not null, traverse left
				}
				else {										//if the next node is right		
						if(temp.rightChild == null) {			//make sure right isn't null
							//if "right" is null, is "right" a leaf or do we need to add zeroes?
							if(temp.leftChild == null) {	//leaf? add a 1 and we're done
								str+="1";
								System.out.println("?");
								break;
							} 
							else{		 //not a leaf? try again with corrected loc[]
								loc = appendZeros(loc, loc.length+1);
							}
						} 
						temp = temp.rightChild;					//if left is not null, traverse left
				}
			}
		}
		addOne(loc);
		return temp.Char + str;
	}
}	

