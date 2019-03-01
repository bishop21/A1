package tree;

import java.util.LinkedList;

public class MVT {
	LinkedList<CharFreq> table = new LinkedList<CharFreq>();
	int loc = 0;
	int len = 0;
	public MVT() {
		
	}
	public void addNode(CharFreq cf) {
		loc++;
	}
}
