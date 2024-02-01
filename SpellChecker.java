
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		if (str.length() == 0){
			return "";
		}else{
			str = str.substring(1);
		}
		return str;
		
	}

	public static int levenshtein(String word1, String word2) {
		int distance = 0;
		word1=word1.toLowerCase();
		word2=word2.toLowerCase();

		if (word2.length() == 0){
			distance= word1.length();
			return distance;
		}
		if (word1.length()== 0){
			distance= word2.length();
			return distance;
		}
		if (word1.charAt(0)==word2.charAt(0)){
			distance = levenshtein(tail(word1), tail(word2));
			return distance;
		}
		else{
			int temp1= levenshtein(tail(word1), word2);
			int temp2 = levenshtein(word1, tail(word2));
			int temp3 = levenshtein(tail(word1), tail(word2));
			distance = Math.min(Math.min(temp1,temp2),temp3) +1;
			return distance;
		}
	}


	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);

		for (int i=0; i<3000; i++){
			dictionary[i]=in.readString();
		}

		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		int distance = levenshtein(word, dictionary[0]);
		int index = 0;
		
		for (int i=1; i < dictionary.length; i++){
			if (levenshtein(word, dictionary[i])<=distance){
				distance = (levenshtein(word, dictionary[i]));
				index = i;
			} 
			
		}
		if (distance <= threshold){
			return dictionary[index];
	} 
		return word;

}
}
