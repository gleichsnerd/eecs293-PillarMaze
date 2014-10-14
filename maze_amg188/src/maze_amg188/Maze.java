package maze_amg188;

import java.util.ArrayList;

public class Maze {

	Array<ArrayList<PillarNode>> pillars;
	
	public Maze(int width, int height, ArrayList<ArrayList<PillarNode>> planks) {
		this.pillars = this.createMazePillars(width, height, planks);
	}
	
	private ArrayList<ArrayList<PillarNode>> createMazePillars(int width, int height, ArrayList<ArrayList<PillarNode>> planks) {
		ArrayList<ArrayList<PillarNode>> returnPillars = new ArrayList<ArrayList<PillarNode>>();
		
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if ()
			}
		}
		
		return returnPillars;

	}
	
	private ArrayList<>getPillarsAccessibleByPlanks

}
