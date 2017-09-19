import java.util.ArrayList;

public class HashTable<T1, T2> {
	class KVPair{
		T1 key;
		T2 value;
		//constructor
		KVPair(T1 key, T2 value){
			this.key = key;
			this.value = value;
		}
	}
	//our hash table is just an array of linked lists
	ArrayList<KVPair>[] hashtable;
	//constructor
	HashTable(){
		hashtable = (ArrayList<KVPair>[]) new ArrayList[40];
	}
	//modify the table
	public void put(T1 key, T2 value) {
		//create a key value pair
		KVPair kv = new KVPair(key, value);
		//hash the key
		int hashed = hash(key);
		//search for this key in the list of hashtable[hashed] index
		ArrayList<KVPair> list = hashtable[hashed];
		//if this is a completely new key, make a list for this index
		if(list==null) {
			ArrayList<KVPair> l = new ArrayList<KVPair>();
			l.add(kv);
			hashtable[hashed] = l;
		}
		//if there is a list at this index, either update value of an existing key or add new key-value pair
		else {
			//search for entries with the exact same key
			for(int i = 0; i<list.size(); i++) {
				//save the current element
				KVPair current = list.get(i);
				//if the key exists in the table then update value of the key
				if(current.key==key) {
					current.value = value;
					return;
				}
			}
			//key was not found in the table, so append this key value pair at the end of the list
			list.add(kv);
		}
	}
	//retrieve a value given a key
	public T2 get(T1 key) {
		int operations = 0;
		//hash the key
		int hashed = hash(key);
		//search for this key in the list of hashtable[hashed] index
		ArrayList<KVPair> list = hashtable[hashed];
		//search this list
		if(list==null) {
			return null;
		}
		for(int i = 0; i<list.size(); i++) {
			operations++;
			KVPair kv = list.get(i);
			if(kv.key==key) {
				System.out.println("operations: " + operations);
				return kv.value;
			}
		}
		System.out.println("operations: " + operations);
		return null;
	}
	public int hash(T1 key) {
		int result = 0;
		if(key instanceof String) {
			String skey = (String) key;
			for(int i = 0; i<skey.length(); i++) {
				result = (result + (skey.charAt(i)*10 + i)) % 40;
			}
		}
		//System.out.println("Hash of " + key + ": " + result);
		return result;
	}
	//see if a key exists in the table
	public boolean exists(T1 key) {
		//hash the key
		int hashed = hash(key);
		ArrayList<KVPair> list = hashtable[hashed];
		if(list==null) {
			return false;
		}
		else {
			for(int i = 0; i<list.size(); i++) {
				KVPair kv = list.get(i);
				if(kv.key==key) {
					return true;
				}
			}
			return false;
		}
	}
	//print the entire table
	public void print() {
		for(int i = 0; i<40; i++) {
			ArrayList<KVPair> list = hashtable[i];
			if(list!=null) {
				//System.out.println("New list...");
				for(int j = 0; j<list.size(); j++) {
					KVPair kv = list.get(j);
					System.out.println(kv.key + ": " + kv.value);
				}
			}
		}
	}
}
