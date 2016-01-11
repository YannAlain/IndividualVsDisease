

public class Individual {
	// A gene is composed of 20 digits
	String gene = "00000000000000000000";
	int numInfection = 0;
	
	//initializes
	public Individual(String gene){
		this.gene = gene;	
		numInfection = 0;
	}
	
	public Individual(String gene, int a){
		this.gene = gene;	
		numInfection = a;
	}	
	
	// gets gene
	public String getGene(){
		return gene;
	}
	
	// gets number of infections
		public int getNumInfection(){
			return numInfection;
		}
	
}
