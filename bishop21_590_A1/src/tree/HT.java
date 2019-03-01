package tree;

public class HT implements TreeInterface{
	public CharLenPair root;
	public CharLenPair curr;
	public CharFreq froot;
	
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
		int depth = 0;
		if(loc == null) {			//if the tree is new....
			CharFreq temp;
			while(cf.len==-1) {
				temp = cf.leftChild;
				depth++;
			}
			loc = new boolean[depth];
		}
		
		else {	
			/*//if the tree is established...
			if(cf.codeLen > loc.length) {
				loc = appendZeros(loc, clp.codeLen);
			}
			*/
			//System.out.println(clp.Char);
			//for(int i=0;i<clp.codeLen;i++) {
				//System.out.println(loc[i]);
			//}
			for(int i=0;i<depth;i++) {
				if(!loc[i]) {
					if(curr.leftChild == null) {curr.leftChild = new CharLenPair('y',-1);} //null check
					curr = curr.leftChild;
				}
				else {
					if(curr.rightChild == null) {curr.rightChild = new CharLenPair('y',-1);} //null check
					curr = curr.rightChild;
				}
			}
			//curr.sym = clp.sym;
			//curr.codeLen = clp.codeLen;
		}
		addOne(loc);
		return null;
	}
	
	
}
