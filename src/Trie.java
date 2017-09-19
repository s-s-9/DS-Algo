public class Trie {
	TrieNode root;
	//constructor
	Trie(){
		root = new TrieNode();
	}
	//add a word
	public void addWord(String s) {
		//convert to lowercase
		s = s.toLowerCase();
		//save the size
		int ssize = s.length();
		//start from the root
		TrieNode current = root;
		//this will hold the current letter we are inserting
		int index = 0;
		//loop until all the letters are added
		while(index<ssize) {
			//save the next character
			char c = s.charAt(index);
			//discard non-letters
			if(c<'a' || c>'z') {
				index++;
				continue;
			}
			//if there is already an edge from the current node to the next letter, then increment frequency, else create new node
			if(current.next[c-'a']==null) {
				//create the node
				TrieNode node = new TrieNode(c);
				//establish the edge
				current.next[c-'a'] = node;
				//make this the current node
				current = node;
			}
			else {
				//increment frequency
				current.next[c-'a'].frequency++;
				//make this the current node
				current = current.next[c-'a'];
			}
			index++;
		}
		//note that the current node denotes the end of the word
		current.endOfWord = true;
	}
	//print the entire tree
	public void printTree() {
		//start printing from the root
		prTr(root, "");
	}
	public void prTr(TrieNode root, String sofar) {
		if(root==null) {
			return;
		}
		for(int i = 0; i<26; i++) {
			prTr(root.next[i], (root.value=='?')? sofar : sofar + root.value);
		}
		if(root.endOfWord) {
			System.out.println(sofar + root.value);
		}
	}
	//search a word in the trie
	public boolean searchWord(String s) {
		//save the size
		int ssize = s.length();
		//start matching from the root
		TrieNode current = root;
		//start searching from the first index
		int index = 0;
		while(index<ssize) {
			//store the current character
			char c = s.charAt(index);
			//non-letter characters are not appreciated
			if(c<'a' || c>'z') {
				return false;
			}
			if(current.next[c - 'a']==null) {
				return false;
			}
			current = current.next[c - 'a'];
			index++;
		}
		if(current.endOfWord) {
			return true;
		}
		else {
			return false;
		}
	}
}

class TrieNode {
	//each node will have a value and a frequency that indicates how many times it occurred
	char value;
	int frequency;
	boolean endOfWord;
	//and 26 pointers to 26 letters
	TrieNode[] next;
	//constructor
	TrieNode(char value){
		this.value = value;
	}
	//default constructor (for root)
	TrieNode() {
		this.value = '?';
	}
	//init block
	{
		this.next = new TrieNode[26];
		this.frequency = 1;
		this.endOfWord = false;
	}
}
