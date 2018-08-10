package evolution.core;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import evolution.algorithms.normalize.Polynomial;
import evolution.algorithms.select.Selector;
import evolution.algorithms.select.SingleGenNorm;
import evolution.diagnostics.Log;

public class EvolutionManager {

	Population pop;
	Selector selector;
	Log log;

	public EvolutionManager(Population initPop, Selector selector, boolean logging) {
		this.pop = initPop;
		this.selector = selector;
		if (logging) {
			log = new Log();
			log.recordPopulation(initPop);
		}
	}

	public EvolutionManager(Population initPop) {
		this.pop = initPop;
		this.selector = new SingleGenNorm(new Polynomial(1));
	}

	public void runGenerations(int maxGen) {
		while (pop.gen < maxGen) {
			pop = selector.nextGeneration(pop, pop.size(), log);
		}
	}

	public void runScoreThreshold(int score) {
		while (pop.getFittest().getScore() < score) {
			pop = selector.nextGeneration(pop, pop.size(), log);
		}
	}
	
	public Population nextGeneration() {
		runGenerations(1);
		return pop;
	}

	public boolean saveLog(String filename) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
			out.writeObject(log);
			System.out.println("Wrote " + log.maxGen() + " generations of data to file");
			out.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public Genome getResult() {
		return pop.getFittest();
	}
	
	public Log getLog() {
		return log;
	}
	
	public Population getPop() {
		return pop;
	}

}
