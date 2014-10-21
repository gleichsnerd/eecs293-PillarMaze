package maze_amg188;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class MazeTest {
	
	Maze nullMaze;
	Maze singlePillarMaze;
	Maze optimizableMaze;
	Maze optimizedMaze;
	Maze impossibleMaze;
	Maze plankDepMaze;
	Maze intensiveMaze;
	
	Maze.Test tester;
	
	@Before
	public void emptyAllVariables() {
		this.nullMaze = null;
		this.singlePillarMaze = null;
		this.optimizableMaze = null;
		this.optimizedMaze = null;
		this.impossibleMaze = null;
		this.plankDepMaze = null;
		this.intensiveMaze = null;
		this.tester = null;
	}
	
	private void createAllMazes() {
		Map<Integer, ArrayList<Integer>> emptyPlankLayout = new HashMap<Integer, ArrayList<Integer>>();
		//Create 0x0 maze
		this.nullMaze = new Maze(0, 0, emptyPlankLayout);
		//Create 1x1 maze
		this.singlePillarMaze = new Maze(1, 1, emptyPlankLayout);
		//TODO Create 4x4 Optimizable maze
		this.optimizableMaze = new Maze(4, 4, this.optimizablePlankLayout());
		//TODO Create 4x4 Optimized maze
		//TODO Create 4x4 Impossible maze
		//TODO Create 4x4 Plank-Dependent maze
		//TODO Create int.MAX x int.MAX maze
		
	}
	
	private void createTester(Maze maze) {
		this.tester = maze.new Test();
	}
	
	
	@Test 
	public void testConstructor() {
		
		//Bad Data
		this.optimizableMaze = new Maze(-1, -1, this.optimizablePlankLayout());
		assertNull("Negative size values shouldn't create a maze", this.optimizableMaze.getPillars());
		try {
			this.optimizableMaze = new Maze(4, 4, null);
			fail("NullPointerException not thrown on null plank layout");
		} catch (NullPointerException e) {
			assertTrue("NullPointerException should have been thrown", true);
		}
		
		this.createAllMazes();

		//Boundary, Compound boundaries, Stress Test
		assertNotNull("0x0 maze should still a maze, albeit not solvable", this.nullMaze.getPillars());
		assertNotNull("Max size maze should still be a maze", this.intensiveMaze.getPillars());
		
		//Structured basis, data-flow, good data
		assertNotNull("Maze with valid parameters should be createable and have pillars", this.optimizableMaze.getPillars());
		
	}
	
	@Test 
	public void testPillarGetter() {
		
		this.createAllMazes();
		
		//Structured basis, data-flow, good data
		assertNotNull("A NxN maze should return pillars", this.optimizableMaze.getPillars());
		//Boundary
		assertEquals("A 0x0 maze should return empty pillars", new ArrayList<ArrayList<PillarNode>>(), this.nullMaze.getPillars());
		//Boundary, stress test
		assertNotNull("A Max x Max maze should return pillars", this.intensiveMaze.getPillars());
		//Bad Data
		this.nullMaze = new Maze(-1, -1, new HashMap<Integer, ArrayList<Integer>>());
		assertNull("An invalid maze has no pillars", this.nullMaze.getPillars());
		
	}
	
	@Test 
	public void testFindShortestPath() throws UninitializedObjectException{
		this.createAllMazes();

		//Structured basis
		
		
				//Data-flow
				//Boundary
				//Compound boundaries
				//Bad data
				//Good data
				//Stress Test
	}
	
	//Private class methods, use this.tester and Maze.Test class
	
	@Test 
	public void testGetPillarByID() {
		//Structured basis
				//Data-flow
				//Boundary
				//Compound boundaries
				//Bad data
				//Good data
				//Stress Test
	}
	
	@Test 
	public void testCalculateHValue() {
		//Structured basis
				//Data-flow
				//Boundary
				//Compound boundaries
				//Bad data
				//Good data
				//Stress Test
	}
	
	@Test 
	public void testGetLowestCostNode() {
		//Structured basis
				//Data-flow
				//Boundary
				//Compound boundaries
				//Bad data
				//Good data
				//Stress Test
	}
	
	@Test 
	public void testGetShortestPathFromNode() {
		//Structured basis
				//Data-flow
				//Boundary
				//Compound boundaries
				//Bad data
				//Good data
				//Stress Test
	}
	
	@Test 
	public void testLinkNeighborToPath() {
		//Structured basis
				//Data-flow
				//Boundary
				//Compound boundaries
				//Bad data
				//Good data
				//Stress Test
	}
 
	@Test 
	public void testShouldBeAddedToOpenSet() {
		//Structured basis
				//Data-flow
				//Boundary
				//Compound boundaries
				//Bad data
				//Good data
				//Stress Test
	}
	
	@Test 
	public void testNeighborCanBeTraversed() {
		//Structured basis
				//Data-flow
				//Boundary
				//Compound boundaries
				//Bad data
				//Good data
				//Stress Test
	}
	
	@Test 
	public void testGParentShouldBeSet() {
		//Structured basis
				//Data-flow
				//Boundary
				//Compound boundaries
				//Bad data
				//Good data
				//Stress Test
	}
	
	@Test 
	public void testGetNeighbors() {
		//Structured basis
				//Data-flow
				//Boundary
				//Compound boundaries
				//Bad data
				//Good data
				//Stress Test
	}
	
	@Test 
	public void testCalculateGValue() {
		//Structured basis
				//Data-flow
				//Boundary
				//Compound boundaries
				//Bad data
				//Good data
				//Stress Test
	}
	
	@Test 
	public void testCalculatePValue() {
		//Structured basis
				//Data-flow
				//Boundary
				//Compound boundaries
				//Bad data
				//Good data
				//Stress Test
	}
	
	@Test 
	public void testCreateMazePillars() {
		//Structured basis
				//Data-flow
				//Boundary
				//Compound boundaries
				//Bad data
				//Good data
				//Stress Test
	}
	
	@Test 
	public void testGetPillarsAccessibleByPlanks() {
		//Structured basis
				//Data-flow
				//Boundary
				//Compound boundaries
				//Bad data
				//Good data
				//Stress Test
	}
	
	@Test 
	public void testPillarsAreAdjoining() {
		//Structured basis
				//Data-flow
				//Boundary
				//Compound boundaries
				//Bad data
				//Good data
				//Stress Test
	}

	@Test 
	public void testThrowExceptionIfInputNull() {
		this.createAllMazes();
		this.createTester(this.optimizableMaze);
		//Structured basis, data-flow
		//Good data, true case
		try {
			this.tester.throwExceptionIfInputNull("a", "b", "c");
		} catch (NullPointerException e) {
			fail("Non-null objects shouldn't trigger an exception");
		}
		
		//Bad data, false case
		try {
			this.tester.throwExceptionIfInputNull("a", "b", null);
			fail("A null element should result in an exception");
		} catch (NullPointerException e) {}
	}
	
	@Test 
	public void testThrowExceptionIfMazeInvalid() {
		this.createAllMazes();
		this.createTester(this.optimizableMaze);
		
		//Structured basis, data-flow
		//Good data, true case
		try {
			this.tester.throwExceptionIfMazeInvalid(this.optimizableMaze.getPillars());
		} catch (UninitializedObjectException e) {
			fail("Maze is valid, exception shouldn't be thrown");
		}
		
		//Bad data, false cases
		try {
			this.tester.throwExceptionIfMazeInvalid(null);
			fail("Null pillars should have triggered exception");
		} catch (UninitializedObjectException e) {}
		
		try {
			this.tester.throwExceptionIfMazeInvalid(new ArrayList<ArrayList<PillarNode>>());
			fail("0x0 pillars should have triggered exception");
		} catch (UninitializedObjectException e) {}
	}
	
	private Map<Integer, ArrayList<Integer>> optimizablePlankLayout() {
		Map<Integer, ArrayList<Integer>> plankLayout = new HashMap<Integer, ArrayList<Integer>>();
		ArrayList<Integer> planks = new ArrayList<Integer>();
		
		/*
		 * 0- 1- 2  3
		 *       |
		 * 4  5  6 -7
		 *          | 
		 * 8  9  10-11
		 *        |
		 * 12 13 14-15
		 */
		
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
		
		return plankLayout;
	}
	
	private Map<Integer, ArrayList<Integer>> badPlankLayout() {
		Map<Integer, ArrayList<Integer>> plankLayout = new HashMap<Integer, ArrayList<Integer>>();
		ArrayList<Integer> planks = new ArrayList<Integer>();
		
		/*
		 * 0- 1- 2  3
		 *       |
		 * 4  5  6 -7
		 *       |  | 
		 * 8  9  |10-11
		 *       ||
		 * 12 13 14-15
		 * **Plank between 14 and 6 is invalid**
		 */
		
		planks.add(14);
		plankLayout.put(15, planks);
		
		planks = new ArrayList<Integer>();
		planks.add(15); 
		planks.add(10);
		planks.add(6);
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
		planks.add(14);
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
		
		return plankLayout;
	}

}
