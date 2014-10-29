package maze_amg188;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class MazeTest {
	
	Maze nullMaze;
	Maze singlePillarMaze;
	Maze optimizableMaze;
	Maze optimizedMaze;
	Maze impossibleMaze;
	Maze plankDependentMaze;
	Maze intensiveMaze;
	
	Maze.Test tester;
	
	@Before
	public void emptyAllVariables() {
		this.nullMaze = null;
		this.singlePillarMaze = null;
		this.optimizableMaze = null;
		this.optimizedMaze = null;
		this.impossibleMaze = null;
		this.plankDependentMaze = null;
		this.intensiveMaze = null;
		this.tester = null;
	}
	
	/**
	 * Creates a maze of every type
	 */
	private void createAllMazes() {	
		//Create 0x0 maze
		this.nullMaze = new Maze(0, 0, this.emptyPlankLayout());
		//Create 1x1 maze
		this.singlePillarMaze = new Maze(1, 1, this.emptyPlankLayout());
		//Create 4x4 Optimizable maze
		this.optimizableMaze = new Maze(4, 4, this.optimizablePlankLayout());
		//Create 4x4 Optimized maze
		this.optimizedMaze = new Maze(4, 4, this.optimizedPlankLayout());
		//Create 4x4 Impossible maze
		this.impossibleMaze = new Maze(4, 4, this.emptyPlankLayout());
		//Create 4x4 Plank-Dependent maze
		this.plankDependentMaze = new Maze(4, 4, this.plankDependentPlankLayout());
		//Create N-1 x N-1 maze
	    int N = 1001;
		this.intensiveMaze = new Maze(N, N, this.intensivePlankLayout(N));
		
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
		ArrayList<PillarNode> results = new ArrayList<PillarNode>();
		int[] correctIDs;
		int topVal;
		this.createAllMazes();

		//Structured basis
		//Case 1: Null or 0-Maze (Bad data, boundary)
		try {
			this.nullMaze.findShortestPath(0, 0);
			fail("Exception should be thrown on invalid maze");
		} catch (UninitializedObjectException e) {}

		//Case 2: 1 Pillar Maze (Good data, boundary)
		results = this.singlePillarMaze.findShortestPath(0, 0);
		correctIDs = new int[]{0};
		for (int i = 0; i < correctIDs.length; i++) {
			if (results.get(i).getID() != correctIDs[i])
				fail("Actual and expected IDs do not match");
		}
		
		//Case 3: Optimized maze
		results = this.optimizedMaze.findShortestPath(15, 0);
		correctIDs = new int[]{0, 1, 2, 3, 7, 11, 15};
		for (int i = 0; i < correctIDs.length; i++) {
			if (results.get(i).getID() != correctIDs[i])
				fail("Actual and expected IDs do not match");
		}
		
		//Case 4: Optimizable maze
		results = this.optimizableMaze.findShortestPath(15, 0);
		correctIDs = new int[]{0, 1, 2, 6, 7, 11, 15};
		for (int i = 0; i < correctIDs.length; i++) {
			if (results.get(i).getID() != correctIDs[i])
				fail("Actual and expected IDs do not match");
		}
		
		//Case 5: Plank Dependent maze
		results = this.plankDependentMaze.findShortestPath(15, 0);
		correctIDs = new int[]{0, 1, 2, 3, 7, 11, 15};
		for (int i = 0; i < correctIDs.length; i++) {
			if (results.get(i).getID() != correctIDs[i])
				fail("Actual and expected IDs do not match");
		}
		
		//Case 6: Intensive maze (stress test)
		results = this.intensiveMaze.findShortestPath(1000000, 0);
		topVal = 1001;
		try {
			for(int i = 0; i < results.size(); i++) {
				if (i < topVal)
					assertEquals("Path should extend across entire first row", i, results.get(i));
				else
					assertEquals("Path should run down left side of maze", (i - topVal + 1) * 1000, results.get(i));
			}
		} catch (NullPointerException e) {
			fail("Test can't find path of large maze");
		}
		
		
		//Case 7: Impossible maze
		results = this.optimizedMaze.findShortestPath(15, 0);
		assertNull("Shortest path should be unattainable", results);
	}
	
	//Private class methods, use this.tester and Maze.Test class
	
	@Test 
	public void testGetPillarByID() throws UninitializedObjectException {
		this.optimizableMaze = new Maze(4, 4, this.optimizablePlankLayout());
		ArrayList<ArrayList<PillarNode>> pillars = optimizableMaze.getPillars();
		tester = this.optimizableMaze.new Test();
		
		//Structured basis, data-flow, good data
		//Boundary
		assertEquals("First pillar should have an ID of 0", pillars.get(0).get(0), tester.getPillarByID(pillars, 0));
		assertEquals("Last pillar should have an ID of 15", pillars.get(3).get(3), tester.getPillarByID(pillars, 15));
				
		//Bad data
		pillars = null;
		try {
			tester.getPillarByID(pillars, 0);
			fail("Null set of pillars should net no pillars");
		} catch (UninitializedObjectException e) {}
		
		//Stress Test
		this.intensiveMaze = new Maze(1000, 1000, this.intensivePlankLayout(1000));
		pillars = this.intensiveMaze.getPillars();
		assertEquals("Large pillar set shouldn't mess up the last element being the last", pillars.get(999).get(999), tester.getPillarByID(pillars, 999999));
	}
	
	@Test 
	public void testCalculateHValue() throws UninitializedObjectException {
		this.optimizableMaze = new Maze(4, 4, this.optimizablePlankLayout());
		ArrayList<ArrayList<PillarNode>> testPillars = optimizableMaze.getPillars();
		tester = this.optimizableMaze.new Test();
		
		/*
		 * 0  1  2  3
		 *          
		 * 4  5  6  7
		 *           
		 * 8  9  10 11
		 *          
		 * 12 13 14 15
		 */
		
		//Structured basis, Data-flow, good data
		//Good data
		assertEquals("Exit pillar should have an H of 0", 0, tester.calculateHValue(testPillars, 0, 0));
		assertEquals("Pillar one diagonal away should have an H of 1", 1, tester.calculateHValue(testPillars, 1, 0));
		assertEquals("Pillar two diagonal away should have an H of 2", 2, tester.calculateHValue(testPillars, 2, 0));
		assertEquals("Pillar three diagonal away should have an H of 3", 3, tester.calculateHValue(testPillars, 3, 0));
		assertEquals("Pillar four diagonal away should have an H of 4", 4, tester.calculateHValue(testPillars, 7, 0));
		assertEquals("Pillar five diagonal away should have an H of 5", 5, tester.calculateHValue(testPillars, 11, 0));
		assertEquals("Pillar six diagonal away should have an H of 6", 6, tester.calculateHValue(testPillars, 15, 0));
		
		//Bad data
		try {
			tester.calculateHValue(null, 0, 0);
			fail("Exception should be thrown for null pillars");
		} catch (UninitializedObjectException e) {}
		
		assertEquals("Negative indeces should result in the same h as their positive counterparts", 1, tester.calculateHValue(testPillars, 1, 0));
				
	}
	
	@Test 
	public void testGetLowestCostNode() {
		Set<PillarNode> pillarSet = new HashSet<PillarNode>();
		PillarNode node1, node2, node3;
		this.optimizableMaze = new Maze(4, 4, this.optimizablePlankLayout());
		tester = this.optimizableMaze.new Test();
		
		//Structured basis, data-flow
		//Good data
		//Boundary - 1 node
		node1 = new PillarNode(0);
		node1.setHValue(0);
		node1.setGValue(2);
		
		pillarSet.add(node1);
		assertEquals("Cheapest node should be the only node", node1, tester.getLowestCostNode(pillarSet));
		
		//Boundary - 2 nodes
		node2 = new PillarNode(1);
		node2.setHValue(1);
		node2.setGValue(2);
		
		pillarSet.add(node2);
		assertEquals("Cheapest node should be node1", node1, tester.getLowestCostNode(pillarSet));
		
		//Boundary - 3 nodes, tie between 1 and 3
		node3 = new PillarNode(2);
		node3.setHValue(0);
		node3.setPValue(2);
		node3.setGValue(3);
		pillarSet.add(node3);
		assertEquals("Cheapest node should still be node1", node1, tester.getLowestCostNode(pillarSet));
		
		//Bad data
		pillarSet = new HashSet<PillarNode>();
		try {
			tester.getLowestCostNode(pillarSet);
			fail("Exception should be thrown for empty set");
		} catch (IllegalArgumentException e) {}
	}
	
	@Test 
	public void testGetShortestPathFromNode() throws UninitializedObjectException {
		ArrayList<ArrayList<PillarNode>> testPillars;
		ArrayList<PillarNode> results;
		this.optimizableMaze = new Maze(4, 4, this.optimizablePlankLayout());
		testPillars = optimizableMaze.getPillars();
		tester = this.optimizableMaze.new Test();
		int[] correctIDs;
		/*
		 * 0- 1- 2  3
		 *       |
		 * 4  5  6 -7
		 *          | 
		 * 8  9  10-11
		 *        |
		 * 12 13 14-15
		 */
		
		//Structured basis, dataflow
		//Good data
		//Boundary: 0 parents
		results = tester.getShortestPathFromNode(this.tester.getPillarByID(testPillars, 0));
		correctIDs = new int[]{0};
		for (int i = 0; i < correctIDs.length; i++) {
			if (results.get(i).getID() != correctIDs[i])
				fail("Actual and expected IDs do not match");
		}
		
		//Boundary: 1 parent
		tester.getPillarByID(testPillars, 0).setPParent(tester.getPillarByID(testPillars, 1));
		results = tester.getShortestPathFromNode(this.tester.getPillarByID(testPillars, 0));
		correctIDs = new int[]{0, 1};
		for (int i = 0; i < correctIDs.length; i++) {
			if (results.get(i).getID() != correctIDs[i])
				fail("Actual and expected IDs do not match");
		}
		
		//Boundary: 2 parents, 1G and 1P
		tester.getPillarByID(testPillars, 1).setGParent(tester.getPillarByID(testPillars, 2));
		results = tester.getShortestPathFromNode(this.tester.getPillarByID(testPillars, 0));
		correctIDs = new int[]{0, 1, 2};
		for (int i = 0; i < correctIDs.length; i++) {
			if (results.get(i).getID() != correctIDs[i])
				fail("Actual and expected IDs do not match");
		}
		
		//Bad data
		try {
			results = tester.getShortestPathFromNode(null);
			fail("Exception should be thrown on null pillar");
		} catch (NullPointerException e) {}
	}
	
	@Test 
	public void testLinkNeighborToPath() {
		ArrayList<ArrayList<PillarNode>> testPillars;
		PillarNode current, neighbor;
		int tempG, tempP;
		Set<PillarNode> closedSet = new HashSet<PillarNode>();
		this.optimizableMaze = new Maze(4, 4, this.optimizablePlankLayout());
		testPillars = optimizableMaze.getPillars();
		tester = this.optimizableMaze.new Test();
		
		//Structured basis, data flow
		//-Case 1: Can't be traversed
		current = new PillarNode(0);
		neighbor = new PillarNode(0);
		tempG = -1;
		tempP = -1;
		tester.linkNeighborToPath(current, neighbor, closedSet, tempG, tempP);
		assertNull("Neighbor should have no parents", neighbor.getGParent());
		assertNull("Neighbor should have no parents", neighbor.getPParent());
		
		//-Case 2: Is in closed set
		closedSet.add(neighbor);
		tester.linkNeighborToPath(current, neighbor, closedSet, tempG, tempP);
		assertNull("Neighbor should have no parents", neighbor.getGParent());
		assertNull("Neighbor should have no parents", neighbor.getPParent());
		
		//-Case 3: Can be traversed and isn't in closed set
		closedSet = new HashSet<PillarNode>();
		current.setPValue(0);
		current.setGValue(0);
		//-Case 3a: tempPValue - currentPillar.getPValue() == 1
		tempP = 1;
		tester.linkNeighborToPath(current, neighbor, closedSet, tempG, tempP);
		assertNull("Neighbor should have no g parent", neighbor.getGParent());
		assertEquals("Neighbor should have a p parent", current, neighbor.getPParent());
		assertEquals("Neighbor should have a pvalue of 1", 1, neighbor.getPValue());
		assertEquals("Neighbor should have a gvalue of -1", -1, neighbor.getGValue());
		
		//-Case 3b: gParentShouldBeSet
		tempP = -1;
		tempG = 1;
		neighbor = new PillarNode(1);
		tester.linkNeighborToPath(current, neighbor, closedSet, tempG, tempP);
		assertNull("Neighbor should have no p parent", neighbor.getPParent());
		assertEquals("Neighbor should have a g parent", current, neighbor.getGParent());
		assertEquals("Neighbor should have a gvalue of 1", 1, neighbor.getGValue());
		assertEquals("Neighbor should have a pvalue of -1", -1, neighbor.getPValue());
		
		//-Case 3c: != 1 and parent shouldn't be set
		tempP = 0;
		tempG = 2;
		tester.linkNeighborToPath(current, neighbor, closedSet, tempG, tempP);
		//All values should be the same
		assertNull("Neighbor should have no p parent", neighbor.getPParent());
		assertEquals("Neighbor should have a g parent", current, neighbor.getGParent());
		assertEquals("Neighbor should have a gvalue of 1", 1, neighbor.getGValue());
		assertEquals("Neighbor should have a pvalue of -1", -1, neighbor.getPValue());
			
		//Bad data
		//current = null
		try {
			tester.linkNeighborToPath(null, neighbor, closedSet, tempG, tempP);
			fail("Exception should have been thrown for null field");
		} catch (NullPointerException e) {}
		//neighbor = null
		try {
			tester.linkNeighborToPath(current, null, closedSet, tempG, tempP);
			fail("Exception should have been thrown for null field");
		} catch (NullPointerException e) {}
		//closedSet = null
		try {
			tester.linkNeighborToPath(current, neighbor, null, tempG, tempP);
			fail("Exception should have been thrown for null field");
		} catch (NullPointerException e) {}
		
	}
 
	@Test 
	public void testShouldBeAddedToOpenSet() {
		ArrayList<ArrayList<PillarNode>> testPillars;
		PillarNode neighbor;
		int tempG, tempP;
		Set<PillarNode> closedSet = new HashSet<PillarNode>();
		Set<PillarNode> openSet = new HashSet<PillarNode>();
		this.optimizableMaze = new Maze(4, 4, this.optimizablePlankLayout());
		testPillars = optimizableMaze.getPillars();
		tester = this.optimizableMaze.new Test();
		
		//Structured basis, dataflow, good data
		//Conditionals
		//-can be traversed
		//-Not in openSet
		//-Not in closeSet
		
		//Case 1: TTT
		tempG = 1;
		tempP = 1;
		neighbor = new PillarNode(1);
		assertTrue("Method should indicate neighbor should be added to openset", this.tester.shouldBeAddedToOpenSet(neighbor, openSet, closedSet, tempG, tempP));

		//Case 2: TTF
		closedSet.add(neighbor);
		assertFalse("Method should indicate neighbor should not be added to openset", this.tester.shouldBeAddedToOpenSet(neighbor, openSet, closedSet, tempG, tempP));
		
		//Case 3: TFT
		closedSet = new HashSet<PillarNode>();
		openSet.add(neighbor);
		assertFalse("Method should indicate neighbor should not be added to openset", this.tester.shouldBeAddedToOpenSet(neighbor, openSet, closedSet, tempG, tempP));
		
		//Case 4: FTT
		openSet = new HashSet<PillarNode>();
		tempG = -1;
		tempP = -1;
		assertFalse("Method should indicate neighbor should not be added to openset", this.tester.shouldBeAddedToOpenSet(neighbor, openSet, closedSet, tempG, tempP));
		
		
		//Bad data
		//neighbor = null
		try {
			tester.shouldBeAddedToOpenSet(null, openSet, closedSet, tempG, tempP);
			fail("Exception should have been thrown for null field");
		} catch (NullPointerException e) {}
		//openSet = null
		try {
			tester.shouldBeAddedToOpenSet(neighbor, null, closedSet, tempG, tempP);
			fail("Exception should have been thrown for null field");
		} catch (NullPointerException e) {}
		//closedSet = null
		try {
			tester.shouldBeAddedToOpenSet(neighbor, openSet, null, tempG, tempP);
			fail("Exception should have been thrown for null field");
		} catch (NullPointerException e) {}
		
	}
	
	@Test 
	public void testNeighborCanBeTraversed() {
		int gValue, pValue;
		this.optimizableMaze = new Maze(4, 4, this.optimizablePlankLayout());
		tester = this.optimizableMaze.new Test();
		
		//Structured basis, data flow, good data
		//Case 1: gValue >= 0
		//Boundary
		gValue = 0;
		pValue = -1;
		assertTrue("Neighbor should be traversable", this.tester.neighborCanBeTraversed(gValue, pValue));
				
		//Case 2: pValue >= 0
		//Boundary
		gValue = -1;
		pValue = 0;
		assertTrue("Neighbor should be traversable", this.tester.neighborCanBeTraversed(gValue, pValue));
		
		//Case 3: gValue < 0 && pValue < 0
		//Boundary
		gValue = -1;
		pValue = -1;
		assertFalse("Neighbor shouldn't be traversable", this.tester.neighborCanBeTraversed(gValue, pValue));
	
	}
	
	@Test 
	public void testGParentShouldBeSet() {
		int gValue = 0;
		PillarNode current= new PillarNode(0);
		PillarNode neighbor = new PillarNode(1);
		this.optimizableMaze = new Maze(4, 4, this.optimizablePlankLayout());
		tester = this.optimizableMaze.new Test();
		//Structured basis, dataflow
		//Case 1: gParent == null
		assertTrue("New node shouldn't have a gparent", tester.gParentShouldBeSet(neighbor, gValue));
		//Case 2: neighbor.gValue < 0
		assertTrue("New node should have an initial gVal of -1", tester.gParentShouldBeSet(neighbor, gValue));
		//Case 3: gValue < neighbor.gValue (boundary)
		neighbor.setGValue(3);
		gValue = 2;
		assertTrue("Node should have a higher gValue than input", tester.gParentShouldBeSet(neighbor, gValue));
		//Case 4: All false
		neighbor.setGParent(current);
		gValue = 4;
		assertFalse("Neighbor shouldn't meet any passing conditions", tester.gParentShouldBeSet(neighbor, gValue));
		
	}
	
	@Test 
	public void testGetNeighbors() throws UninitializedObjectException {
		ArrayList<ArrayList<PillarNode>> testPillars;
		PillarNode node;
		ArrayList<PillarNode> results;
		int[] correctIDs;
		this.optimizableMaze = new Maze(4, 4, this.optimizablePlankLayout());
		testPillars = optimizableMaze.getPillars();
		tester = this.optimizableMaze.new Test();
		
		//Structured basis, dataflow, good data
		//Case 1: Top of pillars
		//Case 1a: Left of maze
		node = testPillars.get(0).get(0);
		results = tester.getNeighbors(testPillars, node);
		correctIDs = new int[]{4, 1};
		for (int i = 0; i < correctIDs.length; i++) {
			if (results.get(i).getID() != correctIDs[i])
				fail("Actual and expected IDs do not match");
		}
		//Case 1b: Middle of maze
		node = testPillars.get(0).get(2);
		results = tester.getNeighbors(testPillars, node);
		correctIDs = new int[]{6, 1, 3};
		for (int i = 0; i < correctIDs.length; i++) {
			if (results.get(i).getID() != correctIDs[i])
				fail("Actual and expected IDs do not match");
		}
		//Case 1c: Right of maze
		node = testPillars.get(0).get(3);
		results = tester.getNeighbors(testPillars, node);
		correctIDs = new int[]{7, 2};
		for (int i = 0; i < correctIDs.length; i++) {
			if (results.get(i).getID() != correctIDs[i])
				fail("Actual and expected IDs do not match");
		}
		//Case 2: Middle of maze
		//Case 2a: Left of maze
		node = testPillars.get(2).get(0);
		results = tester.getNeighbors(testPillars, node);
		correctIDs = new int[]{4, 12, 9};
		for (int i = 0; i < correctIDs.length; i++) {
			if (results.get(i).getID() != correctIDs[i])
				fail("Actual and expected IDs do not match");
		}
		//Case 2b: Middle of maze
		node = testPillars.get(2).get(2);
		results = tester.getNeighbors(testPillars, node);
		correctIDs = new int[]{6, 14, 9, 11};
		for (int i = 0; i < correctIDs.length; i++) {
			if (results.get(i).getID() != correctIDs[i])
				fail("Actual and expected IDs do not match");
		}
		//Case 2c: Right of maze
		node = testPillars.get(2).get(3);
		results = tester.getNeighbors(testPillars, node);
		correctIDs = new int[]{7, 15, 10};
		for (int i = 0; i < correctIDs.length; i++) {
			if (results.get(i).getID() != correctIDs[i])
				fail("Actual and expected IDs do not match");
		}
		//Case 3: Bottom of maze
		//Case 3a: Left of maze
		node = testPillars.get(3).get(0);
		results = tester.getNeighbors(testPillars, node);
		correctIDs = new int[]{8, 13};
		for (int i = 0; i < correctIDs.length; i++) {
			if (results.get(i).getID() != correctIDs[i])
				fail("Actual and expected IDs do not match");
		}
		//Case 3b: Middle of maze
		node = testPillars.get(3).get(2);
		results = tester.getNeighbors(testPillars, node);
		correctIDs = new int[]{10, 13, 15};
		for (int i = 0; i < correctIDs.length; i++) {
			if (results.get(i).getID() != correctIDs[i])
				fail("Actual and expected IDs do not match");
		}
		//Case 3c: Right of maze
		node = testPillars.get(3).get(3);
		results = tester.getNeighbors(testPillars, node);
		correctIDs = new int[]{11, 14};
		for (int i = 0; i < correctIDs.length; i++) {
			if (results.get(i).getID() != correctIDs[i])
				fail("Actual and expected IDs do not match");
		}
		
		//Bad data
		try {
			this.tester.getNeighbors(testPillars, null);
			fail("Exception should be thrown on null node");
		} catch (NullPointerException e) {}
	}
	
	@Test 
	public void testCalculateGValue() {
		PillarNode current, neighbor;
		ArrayList<Integer> accessiblePillars = new ArrayList<Integer>();
		
		this.optimizableMaze = new Maze(4, 4, this.optimizablePlankLayout());
		tester = this.optimizableMaze.new Test();
		
		current = new PillarNode(0);
		neighbor = new PillarNode(1);
		
		//Structured basis, dataflow
		//Case 1: GValue >= 0 (Boundary)
		current.setGValue(0);
		//Case 1a: current can access next
		accessiblePillars.add(1);
		current.addPlanks(accessiblePillars);
		assertEquals("GValue should be 0 + 1", 1, tester.calculateGValue(current, neighbor));
		//Case 1b: current PValue == 0
		current = new PillarNode(0);
		current.setGValue(0);
		current.setPValue(0);
		assertEquals("GValue should be -1 since route is killed", -1, tester.calculateGValue(current, neighbor));
		//Case 2: GValue < 0
		current.setGValue(-1);
		assertEquals("GValue should be -1 since node is unaccessible", -1, tester.calculateGValue(current, neighbor));
		
		//Bad data
		try {
			this.tester.calculateGValue(current, null);
			fail("Exception should be thrown on null node");
		} catch (NullPointerException e) {}
		try {
			this.tester.calculateGValue(null, neighbor);
			fail("Exception should be thrown on null node");
		} catch (NullPointerException e) {}
	}
	
	@Test 
	public void testCalculatePValue() {
		PillarNode current, neighbor;
		ArrayList<Integer> accessiblePillars = new ArrayList<Integer>();
		
		this.optimizableMaze = new Maze(4, 4, this.optimizablePlankLayout());
		tester = this.optimizableMaze.new Test();
		
		current = new PillarNode(0);
		neighbor = new PillarNode(1);
		
		//Structured basis, dataflow
		//Case 1: PValue >= 0 (Boundary)
		current.setPValue(1);
		//Case 1a: current can access next
		accessiblePillars.add(1);
		current.addPlanks(accessiblePillars);
		//Case 1ai: pValue > 0
		assertEquals("PValue should be 1 + 1", 2, tester.calculatePValue(current, neighbor));
		//Case 1aii: pValue <= 0
		current.setPValue(0);
		assertEquals("PValue should be 0", 0, tester.calculatePValue(current, neighbor));
		//Case 1b: current PValue == 0
		current = new PillarNode(0);
		current.setGValue(0);
		current.setPValue(0);
		assertEquals("PValue should be 1 since we have to use plank", 1, tester.calculatePValue(current, neighbor));
		//Case 2: PValue < 0
		current.setPValue(-1);
		//For some reason it returns 1
		assertEquals("PValue should be -1 since node is unaccessible", -1, tester.calculatePValue(current, neighbor));
		
		//Bad data
		try {
			this.tester.calculatePValue(current, null);
			fail("Exception should be thrown on null node");
		} catch (NullPointerException e) {}
		try {
			this.tester.calculatePValue(null, neighbor);
			fail("Exception should be thrown on null node");
		} catch (NullPointerException e) {}
	}
	
	@Test 
	public void testCreateMazePillars() {
		ArrayList<ArrayList<PillarNode>> results;
		this.optimizableMaze = new Maze(4, 4, this.optimizablePlankLayout());
		tester = this.optimizableMaze.new Test();
		
		//Structured basis, good data
		results = tester.createMazePillars(4, 4, this.optimizedPlankLayout());
		assertEquals("Pillars should equal input size", 4, results.size());
		assertEquals("Pillars should equal input size", 4, results.get(0).size());
		assertEquals("First pillar should index 0", 0, results.get(0).get(0).getID());
		assertEquals("Last pillar should index ", 15, results.get(3).get(3).getID());
				
		//Boundary
		results = tester.createMazePillars(1, 1, this.emptyPlankLayout());
		assertEquals("Pillars should equal input size", 1, results.size());
		assertEquals("Pillars should equal input size", 1, results.get(0).size());
		assertEquals("First pillar should index 0", 0, results.get(0).get(0).getID());
		assertEquals("Last pillar should index 0", 0, results.get(0).get(0).getID());
		
		//Bad data
		try {
			results = tester.createMazePillars(4, 4, null);
			fail("Exception should be thrown on null input");
		} catch(NullPointerException e) {}
		
		results = tester.createMazePillars(-1, -1, this.optimizablePlankLayout());
		assertEquals("Pillars with negative size input should be empty", 0, results.size());
		
	}
	
	@Test 
	public void testGetPillarsAccessibleByPlanks() {
		ArrayList<Integer> results;
		int[] correctIDs;
		this.optimizableMaze = new Maze(4, 4, this.optimizablePlankLayout());
		tester = this.optimizableMaze.new Test();
		
		//Structured basis, dataflow, good data
		results = tester.getPillarsAccessibleByPlanks(0, 0, 4, 4, this.optimizedPlankLayout());
		correctIDs = new int[]{1};
		for (int i = 0; i < correctIDs.length; i++) {
			if (results.get(i) != correctIDs[i])
				fail("Actual and expected IDs do not match");
		}

		results = tester.getPillarsAccessibleByPlanks(3, 3, 4, 4, this.optimizedPlankLayout());
		correctIDs = new int[]{11};
		for (int i = 0; i < correctIDs.length; i++) {
			if (results.get(i) != correctIDs[i])
				fail("Actual and expected IDs do not match");
		}
		
		results = tester.getPillarsAccessibleByPlanks(3, 0, 4, 4, this.optimizedPlankLayout());
		correctIDs = new int[]{7, 2};
		for (int i = 0; i < correctIDs.length; i++) {
			if (results.get(i) != correctIDs[i])
				fail("Actual and expected IDs do not match");
		}
		
		//Bad data
		try {
			tester.getPillarsAccessibleByPlanks(1, 1, 1, 1, this.optimizablePlankLayout());
			fail("Exception should be thrown on mismatched sizes");
		} catch (IllegalArgumentException e) {}
		
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
		tester = this.optimizableMaze.new Test();
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
		tester = this.optimizableMaze.new Test();
		
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
	
	/**
	 * Pregenerated empty plank layout
	 * @return
	 */
	private Map<Integer, ArrayList<Integer>> emptyPlankLayout() {
		Map<Integer, ArrayList<Integer>> emptyPlankLayout = new HashMap<Integer, ArrayList<Integer>>();
		return emptyPlankLayout;
	}
	
	/**
	 * Pregenerated optimizable plank layout
	 * @return
	 */
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
	
	/**
	 * Pregenterated optimized plank layout
	 * @return
	 */
	private Map<Integer, ArrayList<Integer>> optimizedPlankLayout() {
		Map<Integer, ArrayList<Integer>> plankLayout = new HashMap<Integer, ArrayList<Integer>>();
		ArrayList<Integer> planks = new ArrayList<Integer>();
		
		/*
		 * 0- 1- 2- 3
		 *          |
		 * 4  5  6  7
		 *          | 
		 * 8  9  10 11
		 *          |
		 * 12 13 14 15
		 */
		
		planks.add(11);
		plankLayout.put(15, planks);
		
		planks = new ArrayList<Integer>();
		planks.add(7); 
		planks.add(15);
		plankLayout.put(11, planks);
		
		planks = new ArrayList<Integer>();
		planks.add(3); 
		planks.add(11);
		plankLayout.put(7, planks);
		
		planks = new ArrayList<Integer>();
		planks.add(7); 
		planks.add(2);
		plankLayout.put(3, planks);
		
		planks = new ArrayList<Integer>();
		planks.add(1); 
		planks.add(3);
		plankLayout.put(2, planks);
		
		planks = new ArrayList<Integer>();
		planks.add(0); 
		planks.add(2);
		plankLayout.put(1, planks);
		
		planks = new ArrayList<Integer>();
		planks.add(1);
		plankLayout.put(0, planks);
		
		return plankLayout;
	}
	
	/**
	 * Pregenerated plank dependent layout
	 * @return
	 */
	private Map<Integer, ArrayList<Integer>> plankDependentPlankLayout() {
		Map<Integer, ArrayList<Integer>> plankLayout = new HashMap<Integer, ArrayList<Integer>>();
		ArrayList<Integer> planks = new ArrayList<Integer>();
		
		/*
		 * 0  1- 2- 3
		 *          |
		 * 4  5  6  7
		 *          | 
		 * 8  9  10 11
		 *          |
		 * 12 13 14 15
		 */
		
		planks.add(11);
		plankLayout.put(15, planks);
		
		planks = new ArrayList<Integer>();
		planks.add(7); 
		planks.add(15);
		plankLayout.put(11, planks);
		
		planks = new ArrayList<Integer>();
		planks.add(3); 
		planks.add(11);
		plankLayout.put(7, planks);
		
		planks = new ArrayList<Integer>();
		planks.add(7); 
		planks.add(2);
		plankLayout.put(3, planks);
		
		planks = new ArrayList<Integer>();
		planks.add(1); 
		planks.add(3);
		plankLayout.put(2, planks);
		
		planks = new ArrayList<Integer>();
		planks.add(0); 
		planks.add(2);
		plankLayout.put(1, planks);
		
		return plankLayout;
	}
	
	/**
	 * Pregenerated intensive plank layout, takes variable size
	 * @param N
	 * @return
	 */
	private Map<Integer, ArrayList<Integer>> intensivePlankLayout(int N) {
		Map<Integer, ArrayList<Integer>> plankLayout = new HashMap<Integer, ArrayList<Integer>>();
		ArrayList<Integer> planks = new ArrayList<Integer>();
		
		/*
		 * 0- 1- 2- ...-N
		 *              |
		 * ........ ... 2N
		 *              |
		 * ........ ... 3N
		 * .............|.
		 * .............NN
		 */
		
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < N; x++) {
				planks = new ArrayList<Integer>();

				if(y == 0) {
					planks.add(x - 1); 
					planks.add(x + 1);
				}
				
				if(x % (N-1) == 0) {
					planks.add(x - N); 
					planks.add(x + N);
				}
				plankLayout.put(x, planks);
			}
		}
		
		return plankLayout;
	}
	
}
