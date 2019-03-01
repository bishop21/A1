package tree;

import java.util.ArrayList;

public class CharFreq implements Comparable<CharFreq>{
	int freq = 0;
	char Char;
	int len = -1;
	int depth = 0;
	boolean noncoding = false;
	CharFreq leftChild = null;
	CharFreq rightChild = null;
	
	public CharFreq(char Char, int freq) {
		this.freq = freq;
		this.Char = Char;
	}
	
	public CharFreq(CharFreq leftNode, CharFreq rightNode) {
		this.leftChild = leftNode;
		this.rightChild = rightNode;
		this.freq = leftNode.freq + rightNode.freq;
		this.depth = max(leftNode.depth, rightNode.depth)+1;
	}
	

	
	public int max(int a, int b) {
		if(a>b) {return a;}
		return b;
	}
	
	@Override
	public int compareTo(CharFreq o) {
		int freq1 = this.freq;
		int freq2 = o.freq;
		if(freq1<freq2) {return -1;}
		else if(freq1>freq2) {return 1;}
		else {
			if(this.depth<o.depth) {return -1;}
			else if(this.depth>o.depth) {return 1;}
		}
		return 0;
	}

	public static void findLen(CharFreq poll, int i) {
		// TODO Auto-generated method stub
		
	}
}
