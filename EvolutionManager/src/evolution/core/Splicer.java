package evolution.core;

public class Splicer {
	
	public static final float WEIGHT = 0.5f;
	
	public static Object chooseObject(Object o1, Object o2) {
		if (Math.random() < WEIGHT) {
			return o1;
		}
		return o2;
	}
	
	public static float[] chooseRange(float[] r1, float[] r2) {
		float[] r3 = new float[2];
		r3[0] = Math.random() > WEIGHT ? r1[0] : r2[0];
		r3[1] = Math.random() > WEIGHT ? r1[1] : r2[1];
		
		if (r3[0] > r3[1]) {
			float a = r3[0];
			r3[0] = r3[1];
			r3[1] = a;
		}
		return r3;
	}
	
	public static void randomizeRange(float[] range, float min, float max) {
		range[0] = (float) (Math.random() * (max - min) + min);
		range[1] = (float) (Math.random() * (max - min) + min);
		
		if (range[0] > range[1]) {
			float a = range[0];
			range[0] = range[1];
			range[1] = a;
		}
	}
	
	public static void mutateRange(float[] range, float min, float max, float rate) {
		range[0] = (float) (Math.random() < rate ? Math.random() * (max - min) + min : range[0]);
		range[1] = (float) (Math.random() < rate ? Math.random() * (max - min) + min : range[1]);
		
		if (range[0] > range[1]) {
			float a = range[0];
			range[0] = range[1];
			range[1] = a;
		}
	}
	
	public static void mutateRange(float[] range, float factor, float rate) {
		range[0] = (float) (Math.random() < rate ? Math.random() * factor * range[0] : range[0]);
		range[1] = (float) (Math.random() < rate ? Math.random() * factor * range[0] : range[1]);
		
		if (range[0] > range[1]) {
			float a = range[0];
			range[0] = range[1];
			range[1] = a;
		}
	}
	
	public static void mutateRangeS(float[] range, float min, float max, float step, float rate) {
		range[0] = (float) (Math.random() < rate ? (Math.random() * 2 - 1) * step + range[0] : range[0]);
		if (range[0] > max)
			range[0] = max;
		if (range[0] < min)
			range[0] = min;
		range[1] = (float) (Math.random() < rate ? (Math.random() * 2 - 1) * step + range[1] : range[1]);
		if (range[1] > max)
			range[1] = max;
		if (range[1] < min)
			range[1] = min;
		
		if (range[0] > range[1]) {
			float a = range[0];
			range[0] = range[1];
			range[1] = a;
		}
	}
	
	public static float[] spliceFloat(float[] strt, float[] strt2, int limit) {
		int a = (int) (Math.random() * limit);
		int b = (int) (Math.random() * limit);
		if (a > b) {
			int t = a;
			a = b;
			b = t;
		}
		float[] g3 = new float[strt.length];
		for (int i = 0; i < strt.length; i++) {
			if (i < a) {
				g3[i] = strt[i];
			} else if (i < b) {
				g3[i] = strt2[i];
			} else {
				g3[i] = strt[i];
			}
		}
		return g3;
	}
	
	public static int[] spliceInt(int[] strt, int[] strt2) {
		int a = (int) (Math.random() * strt.length);
		int b = (int) (Math.random() * strt.length);
		if (a > b) {
			int t = a;
			a = b;
			b = t;
		}
		int[] g3 = new int[strt.length];
		for (int i = 0; i < strt.length; i++) {
			if (i < a) {
				g3[i] = strt[i];
			} else if (i < b) {
				g3[i] = strt2[i];
			} else {
				g3[i] = strt[i];
			}
		}
		return g3;
	}

	public static int chooseInt(int a, int b) {
		if (Math.random() < WEIGHT)
			return a;
		return b;
	}
}
