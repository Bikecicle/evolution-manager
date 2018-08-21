package evolution.algorithms.select;

import java.util.List;

import evolution.core.Genome;
import evolution.core.Population;
import evolution.diagnostics.Log;
import evolution.diagnostics.Record;

public class ExemplarInject implements Selector{

	private static final long serialVersionUID = 3029697856899884848L;
	
	Selector subSelect;
	List<Genome> exemplars;
	
	public ExemplarInject(Selector subSelect, List<Genome> exemplars) {
		this.subSelect = subSelect;
		this.exemplars = exemplars;
	}

	@Override
	public Population nextGeneration(Population current, int nextSize, Log log) {
		Population next = subSelect.nextGeneration(current, nextSize, log);
		if (!exemplars.isEmpty()) {
			Genome target = next.remove((int) (Math.random() * next.size()));
			Genome exemplar = exemplars.get((int) (Math.random() * exemplars.size()));
			Genome child = target.breed(exemplar);
			next.add(child);
			if (log!= null)
				log.add(new Record(next.gen, child, target, exemplar));
		}
		return next;
	}
}
