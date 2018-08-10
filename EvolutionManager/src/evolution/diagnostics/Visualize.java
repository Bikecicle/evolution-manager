package evolution.diagnostics;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import evolution.diagnostics.Log;
import processing.core.PApplet;

public class Visualize extends PApplet {

	public static final int viewWidth = 800;
	public static final int viewHeight = 600;
	public static final int borderWidth = 20;

	public static final int nodeRadius = 5;

	public static List<Node> nodes;
	public static List<Edge> edges;
	
	public static String logPath;

	public static void main(String[] args) {
		nodes = new ArrayList<Node>();
		edges = new ArrayList<Edge>();
		logPath = args[0];
		PApplet.main("planner.internal.core.Visualize");
	}

	public void settings() {
		size(viewWidth, viewHeight);
	}

	public void setup() {
		Log log = null;
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(logPath));
			log = (Log) in.readObject();
			in.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		double maxScore = log.maxScore();
		double minScore = log.minScore();
		double scoreRatio = (viewHeight - 2 * borderWidth) / (maxScore - minScore);
		double genCount = log.maxGen();
		double genRatio = (viewWidth - 2 * borderWidth) / (genCount - 1);

		for (Record record : log) {
			double score = record.score;
			int id = record.id;
			int x = (int) (record.gen * genRatio + borderWidth);
			int y = viewHeight - (int) ((score - minScore) * scoreRatio) - borderWidth;
			nodes.add(new Node(x, y, id, record));
			for (int i = 0; i < record.parentIds.length; i++) {
				edges.add(new Edge(record.parentIds[i], record.id));
			}
		}
	}

	public void draw() {
		for (Node node : nodes) {
			node.render();
		}
		for (Edge edge : edges) {
			edge.render();
		}
	}

	public void mousePressed() {
		for (Node node : nodes) {
			if (Math.sqrt(Math.pow(node.x - mouseX, 2) + Math.pow(node.y - mouseY, 2)) < nodeRadius * 2) {
				System.out.println(node.record.details);
			}
		}
	}

	private class Node {
		public int x;
		public int y;
		public int id;
		public Record record;

		public Node(int x, int y, int id, Record record) {
			this.x = x;
			this.y = y;
			this.id = id;
			this.record = record;
		}

		public void render() {
			ellipse(x, y, nodeRadius, nodeRadius);
		}
	}

	private class Edge {
		public int parentId;
		public int childId;

		public Edge(int parentId, int childId) {
			this.parentId = parentId;
			this.childId = childId;
		}

		public void render() {
			int x1 = 0;
			int y1 = 0;
			int x2 = 0;
			int y2 = 0;
			for (Node node : nodes) {
				if (node.id == parentId) {
					x1 = node.x;
					y1 = node.y;
				} else if (node.id == childId) {
					x2 = node.x;
					y2 = node.y;
				}
			}
			line(x1, y1, x2, y2);
		}
	}
}
