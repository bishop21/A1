package tree;

public class CharLenPair implements CharLenPairInterface, Comparable<CharLenPair>{
	int codeLen;
	char sym;
	CharLenPair leftChild;
	CharLenPair rightChild;
	CharLenPair parent;
	int loc;
	int value;

	public CharLenPair(char Char, int len) {
		this.codeLen = len;
		this.sym = Char;
	}

	public CharLenPair(CharLenPair left, CharLenPair right) {
		this.leftChild = left;
		this.rightChild = right;
	}
	
	
	@Override
	public void insertNode(CharLenPair clp) {
		// TODO Auto-generated method stub
	}

	// codeLength() should return the code length associated with this symbol
	public int codeLength() {
		return codeLen;
	}

	// value() returns the symbol value of the symbol
	public int value() {
		// Needs implementation
		return value;
	}
	@Override
	public int compareTo(CharLenPair o) {
		// TODO Auto-generated method stub
		int len1 = this.codeLen;
		int len2 = o.codeLen;
		if(len1>len2) {return 1;}
		if(len2>len1) {return -1;}
		if(this.sym>o.sym) {return 1;}
		if(this.sym<o.sym) {return -1;}
		return 0;
	}

	
	
	
	
}
