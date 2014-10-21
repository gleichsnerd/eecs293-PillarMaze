package maze_amg188;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class MazeTest {
	
	@Before 
	void createMazes() {
		Maze nullMaze;
		Maze singlePillarMaze;
		Maze optimizableMaze;
		Maze optimizedMaze;
		Maze impossibleMaze;
		Maze plankDepMaze;
		Maze intensiveMaze;
		//TODO Create 0x0 maze
		//TODO Create 1x1 maze
		//TODO Create 4x4 Optimizable maze
		//TODO Create 4x4 Optimized maze
		//TODO Create 4x4 Impossible maze
		//TODO Create 4x4 Plank-Dependent maze
		//TODO Create int.MAX x int.MAX maze
	}
	
	@Test 
	void testConstructor() {
		
	}
	
	@Test 
	void testPillarGetter() {
		
	}
	
	@Test 
	void testFindShortestPath() throws UninitializedObjectException{
		
		Maze maze;
		/*
		 * 0- 1- 2  3
		 *       |
		 * 4  5  6 -7
		 *          | 
		 * 8  9  10-11
		 *        |
		 * 12 13 14-15
		 */
		Map<Integer, ArrayList<Integer>> plankLayout = new HashMap<Integer, ArrayList<Integer>>();
		ArrayList<Integer> planks = new ArrayList<Integer>();
		ArrayList<PillarNode> shortestPath;
		planks.add(14);
		plankLayout.put(15, planks);
		
		planks = new ArrayList<Integer>();
		planks.add(15); 
		planks.add(10);
		plankLayout.put(14, planks);
		
		planks = new ArrayList<Integer>();
		planks.add(14); 
		planks.add(11);
		plankLayout.put(10, planks);
		
		planks = new ArrayList<Integer>();
		planks.add(7); 
		planks.add(10);
		plankLayout.put(11, planks);
		
		planks = new ArrayList<Integer>();
		planks.add(11); 
		planks.add(6);
		plankLayout.put(7, planks);
		
		planks = new ArrayList<Integer>();
		planks.add(7); 
		planks.add(2);
		plankLayout.put(6, planks);
		
		planks = new ArrayList<Integer>();
		planks.add(1); 
		planks.add(6);
		plankLayout.put(2, planks);
		
		planks = new ArrayList<Integer>();
		planks.add(2); 
		planks.add(0);
		plankLayout.put(1, planks);
		
		planks = new ArrayList<Integer>();
		planks.add(1);
		plankLayout.put(0, planks);
		
		maze = new Maze(4, 4, plankLayout);
		shortestPath = maze.findShortestPath(15, 0);
		
		for(PillarNode node: shortestPath) {
			System.out.println(node.getID());
		}
	}
	
	@Test 
	void testGetPillarByID() {
		
	}
	
	@Test 
	void testCalculateHValue() {
		
	}
	
	@Test 
	void testGetLowestCostNode() {
		
	}
	
	@Test 
	void testGetShortestPathFromNode() {
		
	}
	
	@Test 
	void testLinkNeighborToPath() {
		
	}
 
	@Test 
	void testShouldBeAddedToOpenSet() {
		
	}
	
	@Test 
	void testNeighborCanBeTraversed() {
		
	}
	
	@Test 
	void testGParentShouldBeSet() {
		
	}
	
	@Test 
	void testGetNeighbors() {
		
	}
	
	@Test 
	void testCalculateGValue() {
		
	}
	
	@Test 
	void testCalculatePValue() {
		
	}
	
	@Test 
	void testCreateMazePillars() {
		
	}
	
	@Test 
	void testGetPillarsAccessibleByPlanks() {
		
	}
	
	@Test 
	void testPillarsAreAdjoining() {
		
	}

	@Test 
	void testThrowExceptionIfInputNull() {
		
	}
	
	@Test 
	void testThrowExceptionIfMazeInvalid() {
		
	}

}
