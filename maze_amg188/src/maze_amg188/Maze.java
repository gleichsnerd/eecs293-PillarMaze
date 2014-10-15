package maze_amg188;

import java.util.ArrayList;
import java.util.Map;

public class Maze {

	ArrayList<ArrayList<PillarNode>> pillars;
	
	public Maze(int width, int height, Map<Integer, ArrayList<Integer>> plankLayout) {
		this.pillars = this.createMazePillars(width, height, plankLayout);
	}
	
	private ArrayList<ArrayList<PillarNode>> createMazePillars(int width, int height, Map<Integer, ArrayList<Integer>> plankLayout) {
		ArrayList<ArrayList<PillarNode>> returnPillars = new ArrayList<ArrayList<PillarNode>>();
		ArrayList<Integer> accessiblePillars;
		PillarNode newPillar;
		
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				newPillar = new PillarNode(y * height + x, -1, -1, -1);
				accessiblePillars = getPillarsAccessibleByPlanks(x, y, width, height, plankLayout);
				newPillar.addPlanks(accessiblePillars);
				returnPillars.get(y).add(newPillar);
				
			}
		}
		
		return returnPillars;

	}
	
//	private int calculateHValue()
	
	private ArrayList<Integer> getPillarsAccessibleByPlanks(int x, int y, int width, int height, Map<Integer, ArrayList<Integer>> plankLayout) {
		ArrayList<Integer> returnPillars = new ArrayList<Integer>(); //List of all pillars accessible, identified by number
		Integer currentPillarID = new Integer(y * height + x);
		/* Note: Single number id is found by row * height + column, resulting in the following layout
		 * row _0__1__2__3    <- column
		 *   0| 0  1  2  3
		 *   1| 4  5  6  7
		 *   2| 8  9  10 11
		 *   3| 12 13 14 15
		 */
		
		if (plankLayout.size() > width * height) {
			//If we have more pillars in the layout than possible, throw an exception
			throw new IllegalArgumentException("plankLayout contains more pillars than the maze can hold");
		} 
		
		if (returnPillars.contains(currentPillarID)) {
			returnPillars = plankLayout.get(currentPillarID);
			
			for (Integer accessiblePillar: returnPillars) {
				if (!pillarsAreAdjoining(currentPillarID, accessiblePillar, width, height)) {
					throw new IllegalArgumentException("plankLayout contains an invalid configuration");
				}
			}
		}
		
		return returnPillars;
		
	}

	private boolean pillarsAreAdjoining(int pillar1ID, int pillar2ID, int width, int height) {
		if (Math.abs(pillar1ID - pillar2ID) == height || (Math.abs(pillar1ID - pillar2ID) == 1 && Math.floor(pillar1ID/height) == Math.floor(pillar2ID/height)))
				return true;
		return false;
	}
}
