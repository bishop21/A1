package tree;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import io.InputStreamBitSource;
import io.InsufficientBitsLeftException;
import io.OutputStreamBitSink;

public class Main {
	public static void main(String[] args) throws InsufficientBitsLeftException, IOException {
		String fileLoc = "data/compressed.dat";
		InputStreamBitSource isbs = new InputStreamBitSource(new FileInputStream(new File(fileLoc)));
		OutputStreamBitSink osbs = new OutputStreamBitSink(new FileOutputStream(new File("data/uncompressed.dat")));
		Table table = new Table(isbs);						//"table" keeps track of the codeword lengths
		HT huffy = new HT();
		/*
		System.out.println((int)'—');
		System.out.println((int)'.');
		System.out.println((int)'†');
		System.out.println((int)' ');
		*/
		for (int i = 0; i < 256; i++) {
			huffy.addNode(table.sortedTable.get(i));		//the table's elements are added to the tree,
		}													//starting with the most probable letters
		
		int size = isbs.next(32);			//this is NOT WORKING CORRECTLY
		//System.out.println(size);
		size = 698442;
		//System.out.println(size);
		
		
		
		CharLenPair curr = huffy.root;
		for (int i=0;i<size*4;i++) {						//bit by bit, we dive down the tree
			if(isbs.next(1) == 0) {curr = curr.leftChild;}
			else{curr = curr.rightChild;}
			if(curr.codeLen>0) {								//when we reach a leaf, we print 
				System.out.print(curr.sym);					//its associated char...
				osbs.write(curr.sym, 8);					//write it to an output file...
				curr = huffy.root;							//and return to the tree root for the next char
			}
		}
		
		//DECODER 
		//////////////////////////////////////////////////////////////////////////////////////
		//ENCODER
		
		InputStreamBitSource isbs2 = new InputStreamBitSource(new FileInputStream(new File("data/decompressed.dat")));
		CharFreq[] freqs = new CharFreq[256];
		for(int i=0;i<256;i++) {
			freqs[i] = new CharFreq((char)i, 0);			//the array tracks the frequency of 
		}													//each letter's occurrence
		for(int i=0;i<100000;i++) {
			char myChar = (char)isbs2.next(8);
			freqs[(int)myChar].freq++;						//add 1 to frequency if the letter occurs
		}
		
		
		
		
		/*
		LinkedList<CharFreq> sortedFreqs = new LinkedList<CharFreq>();
		//this uses a LinkedList to keep up with the order of all our frequencies/priorities
		for (int j=0;j<25000;j++) {
			for (int currChar = 0; currChar<256;currChar++) {
				if(freqs[currChar].freq == j) {
    				sortedFreqs.add(new CharFreq((char)currChar, j));	//sort the table by frequency
    			}
    		}
    	}
		
		HT huf2 = new HT();
		for(int i=0;i<255;i++) {
			CharFreq cf1 = sortedFreqs.get(i);
			CharFreq cf2 = sortedFreqs.get(i+1);
			//System.out.println(cf1.Char + " occurs " + cf1.freq + " times.");
			//huf2.addNode(new CharLenPair(cf1.Char, cf1.len));
			}
		

		*/
	
		PriorityQueue<CharFreq> pq = new PriorityQueue<CharFreq>();
		Double entropy = 0.0;
		
		for(int i=0;i<256;i++) {
			CharFreq tempNode = new CharFreq((char)i, freqs[i].freq);
			int frequency = freqs[i].freq;
			Double prob = (double)freqs[i].freq/(double)size;
			//System.out.println("char: "+(char)i + " probability: "+ prob);
			if(prob>0) {
				Double charEntropy = (Math.log(prob) / Math.log(2));
				entropy -= charEntropy * frequency;
			}
			pq.add(tempNode);
			
		}
		//System.out.println("Total file entropy: " + entropy);
		
		
		while(pq.size() !=1) {
			//make parent nodes for the two least frequent chars/leaves
			CharFreq lefty = pq.poll();
			CharFreq righty = pq.poll();
			CharFreq parent = new CharFreq(lefty,righty);
			pq.add(parent);
			//since it's a priority queue, it should resort as it adds
		}
		Map<Integer, String> cmap = new HashMap<Integer, String>();
		ArrayList<CharLenPair> charz = new ArrayList<CharLenPair>();
		CharFreq root = pq.poll();
		//System.out.println(root.Char + "!");
		//System.out.println(root.freq);
		
		// Start at root and walk down to each leaf, forming code string along the
		// way (0 means left, 1 means right). Insert mapping between symbol value and
		// code string into cmap when each leaf is reached.
		HT encodeTree = new HT();
		encodeTree.froot = pq.poll();
		//encodeTree.findCode(pq.poll());
		
		for(int i=0;i<256;i++) {
			CharFreq.findLen(pq.poll(), 0);
		}
		Collections.sort(charz);	//sort the arraylist
		
		// Create empty list of SymbolWithCodeLength objects
		
		
		// generate code lengths and map it with the character
		// make a new class SymbolWithCodeLength and the constructer takes a symbol and the code length
		// SymbolWithCodeLength implements comparable
		// compareTo method: compares the code lengths first and if theyre equal, compare the symbols
		// for loop i<256 and make new SymbolWithCodeLength objects
		// add each object to a arraylist
		// do arraylist.sort(null) to sort the list
		// make a new root node
		// make the new huffman tree using ur array list 
		// Node node = new Node(arraylist char, arraylist length)
		// buildhuffmantree(root, node);
		// generate codes from your huffman tree
		// yay ur pretty much done :D (with part 2..)
		// also look at skeleton code bc it helps
		
		
		
		
		
		
		
		
	}

	
	public static void findLen(CharFreq root, int depth, ArrayList<CharLenPair> clpz) {
		if(root.leftChild==null && root.rightChild == null) {
			clpz.add(new CharLenPair(root.Char, depth));
		}
		if (root.leftChild!=null) {
			findLen(root.leftChild, depth++, clpz);
		}
		if(root.rightChild!=null) {
			findLen(root.rightChild, depth++, clpz);
		}
	}

}


