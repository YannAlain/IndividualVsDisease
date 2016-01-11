
public class Series {
	private int geneNum;
	private int geneNumLength;
	
	// initializes Series first int is allele, second is the length of group
	public Series(int geneNum, int geneNumLength){
		this.geneNum = geneNum;
		this.geneNumLength = geneNumLength;
	}
	
	// gets allele num
	public int getGeneNum(){
		return geneNum;
	}
	
	// gets gene length
	public int getGeneNumLength(){
		return geneNumLength;
	}
	
	//@Override
	public String getString(){
		String phrase = "Gene: " + geneNum + "  Length: " + geneNumLength;
		return phrase;
	}
	
	public static boolean areEqual (Series numero1, Series numero2){
		if(numero1.getGeneNum() != numero2.getGeneNum()){
			return false;
		}
		if(numero1.getGeneNumLength() != numero2.getGeneNumLength()){
			return false;
		}
		else{
			return true;
		}
	}
	
}
