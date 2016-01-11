import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class city {
	
	public static void main(String[] args){
		
		ArrayList<Individual> test1 = new ArrayList<Individual>();
		test1.addAll(createPopulation(10));
		/*
		for(int i = 0; i < test1.size();i++){
			System.out.println(test1.get(i).getGene() + " " + test1.get(i).getGene().length());
			System.out.println(Arrays.toString(resistanceTester(test1.get(i), 7)));
			System.out.println(numInfected(resistanceTester(test1.get(i), 7)));
		}
		*/
		ArrayList<Individual> test2 = new ArrayList<Individual>();
		test2.add(new Individual("00000000000000000000"));
		test2.add(new Individual("11111111111111111111"));
		test2.add(new Individual("11112222333334444555"));
		test2.add(new Individual("11111112222222333333"));

		ArrayList<Individual> test5 = new ArrayList<Individual>();
		test5.add(new Individual("263"));
		test5.add(new Individual("111222"));
		test5.add(new Individual("00000000000000000000"));
		test5.add(new Individual("1111111111111111222222233333333"));
		test5.add(new Individual("111111111111111122222222222222333333333333333"));
		test5.add(new Individual("222222223323333"));
		
		ArrayList<Individual> test3 = new ArrayList<Individual>();
		ArrayList<Individual> test4 = new ArrayList<Individual>();
		test4.addAll(infection(test5));
		test3.addAll(nextGeneration(test2));
		for(int i = 0; i < test3.size(); i++){
			//System.out.println(test3.get(i).getGene());
		}
		for(int i = 0; i < test4.size(); i++){
		//	System.out.println(test4.get(i).getGene());
		}
		
		menu();
	}
	
	public static void menu(){
		ArrayList<ArrayList<Individual>> time = new ArrayList<ArrayList<Individual>>();
		ArrayList<Individual> currentGene = new ArrayList<Individual>();
		ArrayList<Individual> nextGene = new ArrayList<Individual>();
		Scanner command = new Scanner(System.in); int comInt;
		int popSize;
		int generation = 0;
		boolean switch1 = true;
		
		System.out.println("Time to start.");
		System.out.println("What is the size of the population?");
		popSize = command.nextInt();
		currentGene.addAll(createPopulation(popSize));
		time.add(currentGene);
		
		//Menu
		while(switch1 == true){
			System.out.println("0: quit program");
			System.out.println("1: view current generation");
			System.out.println("2: next generation");
			System.out.println("3: view time (not implemented yet)");
		
			comInt = command.nextInt();
			
			if(comInt == 3){
				System.out.println("It is not ready yet");
			}
			if(comInt == 0){
				break;
			}
			if(comInt == 1){
				System.out.println("Generation " + generation);
				for(int i = 0; i < currentGene.size(); i++){
					System.out.println("Size: " + currentGene.get(i).getGene().length() + " " + i + ": " + currentGene.get(i).getGene());
				}
			}
			if(comInt == 2){
					nextGene.addAll(nextGeneration(currentGene));
					time.add(nextGene);
					for(int i = 0; i < currentGene.size(); i++){
						currentGene.set(i, nextGene.get(i));
					}
					for(int i = 0; i < nextGene.size(); i++){
						nextGene.remove(0);
					}
					generation++;
			}
		
		}
	}
	
	
 	public static ArrayList<Individual> createPopulation(int numPop){
		String gene = "00000000000000000000";
		ArrayList<Individual> population = new ArrayList<Individual>();
		for(int i = 0; i < numPop; i++){
			Individual newLife = new Individual(geneControl.mutation(gene));
			population.add(newLife);
		}
		return population;
	}	
	
	public static ArrayList<Individual> createPopulation(){
		String gene = "00000000000000000000";
		ArrayList<Individual> population = new ArrayList<Individual>();
		for(int i = 0; i < 50; i++){
			Individual newLife = new Individual(geneControl.mutation(gene));
			population.add(newLife);
		}
		return population;
	}
	
	public static boolean[] resistanceTester(Individual gene){		
		int[] geneResistance = geneControl.resistanceCalculator(gene.getGene());
		boolean[] resisted = new boolean[9];
		int res = 0;
		Random rand = new Random();
		for(int i = 0; i < 9; i++ ){
			res = rand.nextInt(101);
			if(res < geneResistance[+1]){
				resisted[i] = false;
				continue;
			}
			resisted[i] = true;
		}
		return resisted;
	}
	
	public static boolean[] resistanceTester(Individual gene, int numDisese){		
		int[] geneResistance = geneControl.resistanceCalculator(gene.getGene());
		boolean[] resisted = new boolean[numDisese];
		int res = 0;
		Random rand = new Random();

		for(int i = 0; i < numDisese; i++ ){
			res = rand.nextInt(101);
			if(res < geneResistance[i]){
				resisted[i] = false;
				continue;
			}
			resisted[i] = true;
		}
		return resisted;
	}
	
	// true means he resisted, false means he is infected
	public static int numInfected(boolean[] resisted){
		int numInfec = 0;
		for(int i = 0; i < resisted.length; i++){
			if(resisted[i] == true){
				numInfec++;
			}
		}
		return numInfec;
	}

	// Selects the Individuals with the least number of infections
	public static ArrayList<Individual> selection(ArrayList<Individual> previousGeneration){

		ArrayList<Individual> nextGeneration = new ArrayList<Individual>();
		
		
		for(int i = 0; i < 10; i++ ){
			for(int j = 0; j < previousGeneration.size(); j++){
				if(nextGeneration.size() >= (previousGeneration.size()/2)){
					break;					
				}
				Individual patient = new Individual(previousGeneration.get(j).getGene(), numInfected(resistanceTester(previousGeneration.get(j))));
				if(patient.getNumInfection() == i){
					nextGeneration.add(new Individual (previousGeneration.get(j).getGene()));
				}		
			}	
		}
		return nextGeneration;
	}
	
	public static ArrayList<Individual> infection(ArrayList<Individual> patient){
		ArrayList<Individual> InfecGene = new ArrayList<Individual>();
		ArrayList<Individual> SurGene = new ArrayList<Individual>();

		for(int i = 0; i < patient.size(); i++){
			InfecGene.add(new Individual(patient.get(i).getGene(), numInfected(resistanceTester(patient.get(i)))));
		}
		SurGene.addAll(selection(InfecGene));
		return SurGene;
	}
	
	public static ArrayList<Individual> doublePopulation(ArrayList<Individual> currentPopulation){
		
		ArrayList<Individual> newGeneration = new ArrayList<Individual>();
		for(int i = 0; i < currentPopulation.size(); i++){
			Individual newLife = new Individual(geneControl.mutation(currentPopulation.get(i).getGene()));
			newGeneration.add(newLife);
		}
		return newGeneration;
	}
	
	public static ArrayList<Individual> doublePopulation(ArrayList<Individual> currentPopulation, int numMuta){
		
		ArrayList<Individual> newGeneration = new ArrayList<Individual>();
		//for(int i = 0; i < currentPopulation.size(); i++){
		//	newGeneration.add(new Individual(currentPopulation.get(i).getGene()));
		//}
		for(int i = 0; i < currentPopulation.size(); i++){
			Individual newLife = new Individual(geneControl.mutation(currentPopulation.get(i).getGene(), numMuta));
			newGeneration.add(newLife);
		}
		return newGeneration;
	}
	
	public static ArrayList<Individual> nextGeneration(ArrayList<Individual> prevGene){
		ArrayList<Individual> nextGen = new ArrayList<Individual>();
		nextGen.addAll(infection(prevGene));
		nextGen.addAll(doublePopulation(nextGen));
		return nextGen;
	}
	

}
	
	
	
	
	
	

