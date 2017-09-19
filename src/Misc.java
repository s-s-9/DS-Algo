public class Misc {
	//binary search
	public static int binarySearch(int[] list, int value) {
		//if list is empty return -1
		if(list.length==0) {
			return -1;
		}
		//see if the list is sorted
		int previous = list[0];
		for(int i = 1; i<list.length; i++) {
			if(list[i]<previous) {
				System.out.println("List is not sorted");
				return -1;
			}
			previous = list[i];
		}
		int start = 0;
		int end = list.length-1;
		while(start<=end) {
			int mid = (start+end)/2;
			if(list[mid]==value) {
				return mid;
			}
			if(value<list[mid]) {
				end = mid-1;
			}
			else {
				start = mid+1;
			}
		}
		return -1;
	}
	//sieve
	public static int[] getPrimes(int limit) {
		//if limit>10^6 return null
		if(limit>1000000) {
			System.out.println("What are you gonna do with that much primes?!");
			return null;
		}
		boolean[] sieve = new boolean[limit+1];
		//0 and 1 are not primes
		sieve[0] = true;
		sieve[1] = true;
		for(int i = 2; i<=Math.sqrt(limit); i++) {
			if(sieve[i]==false) {
				for(int j = i+i; j<=limit; j+=i) {
					sieve[j] = true;
				}
			}
		}
		int found = 0;
		for(int i = 0; i<=limit; i++) {
			if(sieve[i]==false) {
				found++;
			}
		}
		int[] primes = new int[found];
		int index = 0;
		for(int i = 0; i<=limit; i++) {
			if(sieve[i]==false) {
				primes[index] = i;
				index++;
			}
		}
		return primes;
	}
}
