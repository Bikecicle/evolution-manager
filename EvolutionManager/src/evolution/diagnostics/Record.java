package evolution.diagnostics;

import java.io.Serializable;

import evolution.core.Genome;

public class Record implements Serializable {

	private static final long serialVersionUID = -8610307439831040887L;
	
	int gen;
	int id;
	double score;
	String details;
	
	int[] parentIds;
	
	public Record(int gen, Genome genome, Genome...parents) {
		this.gen = gen;
		id = genome.getId();
		score = genome.getScore();
		details = genome.toString();
		parentIds = new int[parents.length];
		for (int i = 0; i < parents.length; i++) {
			parentIds[i] = parents[i].getId();
		}
	}
}
