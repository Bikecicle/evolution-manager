package evolution.algorithms.select;

import java.io.Serializable;

import evolution.core.Population;
import evolution.diagnostics.Log;

public interface Selector extends Serializable {

	public abstract Population nextGeneration(Population current, int nextSize, Log log);
	
}
