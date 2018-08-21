package evolution.algorithms.select;

import evolution.core.Population;
import evolution.diagnostics.Log;

public class SurvivalThreshold implements Selector {
	
	private static final long serialVersionUID = -7208766436101528470L;
	
	Selector subSelect;
	double survivalCf; // Fraction of population to survive each generation

	public SurvivalThreshold(Selector subSelect, double survivalCf) {
		this.subSelect = subSelect;
		this.survivalCf = survivalCf;
	}

	@Override
	public Population nextGeneration(Population current, int nextSize, Log log) {
		int survivorCount = (int) (current.size() * survivalCf);
		Population survivors = new Population();
		Population next = new Population(current.gen + 1);
		for (int i = 0 ; i < survivorCount; i++) {
			survivors.add(current.get(i));
		}
		next.addAll(survivors);
		next.addAll(subSelect.nextGeneration(survivors, nextSize - survivorCount, log));
		
		return next;
	}
}
