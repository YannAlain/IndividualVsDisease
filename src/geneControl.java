import java.util.ArrayList;
import java.util.Random;

public class geneControl {
	// used for testing purposes
	public static void main(String[] args){
		String test1 = "00000000000000000000";
		for(int i = 0; i < sequenceCounter(test1).size(); i++){
			System.out.println(sequenceCounter(test1).get(i).getString());
		}
		
	}
	
	// counts the number of each digit present in the string
	public static int[] digitCounter(String gene){

		int[] numberOfDigit = {0,0,0,0,0,0,0,0,0,0};
		for(int i = 0; i < gene.length(); i++){
				if( gene.charAt(i) == '0'){
					numberOfDigit[0]++;
			    }
				if( gene.charAt(i) == '1'){
					numberOfDigit[1]++;
			    }
				if( gene.charAt(i) == '2'){
					numberOfDigit[2]++;
			    }
				if( gene.charAt(i) == '3'){
					numberOfDigit[3]++;
			    }
			    if( gene.charAt(i) == '4'){
			    	numberOfDigit[4]++;
			    }
			    if( gene.charAt(i) == '5'){
			    	numberOfDigit[5]++;
			    }
			    if( gene.charAt(i) == '6'){
			    	numberOfDigit[6]++;
			    }
			    if( gene.charAt(i) == '7'){
			    	numberOfDigit[7]++;
			    }
			    if( gene.charAt(i) == '8'){
			    	numberOfDigit[8]++;
			    }
			    if( gene.charAt(i) == '9'){
			    	numberOfDigit[9]++;
			    }
		}
		return numberOfDigit;
	}

	// makes a list of all sequences
	public static ArrayList<Series> sequenceCounter(String gene){
		ArrayList<Series> arrayListOfSequence = new ArrayList<Series>();
		char currentNum = gene.charAt(0);
		int currentSerie = 1;
		
		for(int i = 0; i < gene.length()-1; i++){
			if (gene.charAt(i) == gene.charAt(i+1)){
				currentSerie++;
			}
			else{
				Series inter = new Series(Character.getNumericValue(currentNum), currentSerie);
				arrayListOfSequence.add(inter);
				currentSerie = 1;
				currentNum = gene.charAt(i+1);
			}
		}
		Series inter = new Series(Character.getNumericValue(currentNum), currentSerie);
		arrayListOfSequence.add(inter);
		//try a custom .equals to compare
		return arrayListOfSequence;
	}

	// resistance in percentage 0 to 100
	public static int[] resistanceCalculator(String gene){
		int[] digit = digitCounter(gene);
		ArrayList<Series> sequences = sequenceCounter(gene);
		int[] resistance = {0,0,0,0,0,0,0,0,0,0}; 
		for(int i = 1; i < 10; i++){
			resistance[i] = digit[i];
		}
		int a;
		for(int i = 0; i < sequences.size(); i++ ){
				a = (sequences.get(i).getGeneNum());
				resistance[a] = (int) (resistance[a] + Math.pow((sequences.get(i)).getGeneNumLength(), 2));
		}
		return resistance;
	}
	
	// changes some of the digits of the string (from 1 to 9)
	public static String mutation(String gene){
		Random rand = new Random();
		String newGene ="";
		int n;
		int a;
		int b = 1;
		for(int i =0; i < gene.length(); i++){
			if(gene.charAt(i)!= 0 ){
				n = rand.nextInt(100);
				if( n >= 95){
					a = rand.nextInt(9)+1;
					newGene = newGene + a;
				}
				else{
					newGene = newGene + gene.charAt(i);
				}
			}
			else{
				n = rand.nextInt(100);
				if( n >= 80){
					a = rand.nextInt(9)+1;
					newGene = newGene + a;
				}
				else{
					newGene = newGene + gene.charAt(i);
				}
			}
		}
		n = rand.nextInt(100);
		if(n >= (int)((Math.pow(gene.length(), 2))/6) && b == 1){
			newGene = newGene + 0;
		}
		return newGene;
	}

	// same but user inputs upper limit
	public static String mutation(String gene, int numMut){
		Random rand = new Random();
		String newGene ="";
		int n;
		int a;
		for(int i =0; i < gene.length(); i++){
			if(gene.charAt(i)!= 0 ){
				n = rand.nextInt(100);
				if( n >= 95){
					a = rand.nextInt(9)+1;
					newGene = newGene + a;
				}
				else{
					newGene = newGene + gene.charAt(i);
				}
			}
			else{
				n = rand.nextInt(100);
				if( n >= 80){
					a = rand.nextInt(9)+1;
					newGene = newGene + a;
				}
				else{
					newGene = newGene + gene.charAt(i);
				}
			}
		}
		// think about adding gene growth
		return newGene;
	}
	
}
