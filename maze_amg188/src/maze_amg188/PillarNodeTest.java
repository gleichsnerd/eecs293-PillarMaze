package maze_amg188;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class PillarNodeTest {

	PillarNode node;
	PillarNode.Test tester;

	@Before
	public void cleanNodeAndTester() {
		this.node = null;
		this.tester = null;
	}
	
	@Test
	public void testPillarNodeConstructor() {
		node = new PillarNode(0);
		
		//Structured basis, data-flow
		//Good data
		assertEquals("ID should be set to the argument", 0, node.getID());
		assertEquals("hValue should initialize to -1", -1, node.getHValue());
		assertEquals("gValue should initialize to -1", -1, node.getGValue());
		assertEquals("pValue should initialize to -1", -1, node.getPValue());
		assertEquals("gParent should initialize to null", null, node.getGParent());
		assertEquals("pParent should initialize to null", null, node.getPParent());
		
		//Boundary
		node = new PillarNode(-1);
		assertEquals("ID should be set to the -1 argument", -1, node.getID());
		node = new PillarNode(1);
		assertEquals("ID should be set to the +1 argument", 1, node.getID());
	}
	
	@Test
	public void testPillarNodeSetGet() {
		PillarNode parent1 = new PillarNode(10);
		PillarNode parent2 = new PillarNode(20);
		node = new PillarNode(0);
		
		//Structured basis, data-flow, good data
		
		//ID - No setter so we can't change the order of the pillars
		assertEquals("ID should be set to the initial argument", 0, node.getID());
		
		//hValue
		node.setHValue(0);
		assertEquals("hValue should be set to new value", 0, node.getHValue());

		//gValue
		node.setGValue(1);
		assertEquals("gValue should be set to new value", 1, node.getGValue());
		
		//pValue
		node.setPValue(2);
		assertEquals("pValue should be set to new value", 2, node.getPValue());
		
		//gParent
		node.setGParent(parent1);
		assertEquals("gParent should be set to node parent1", parent1, node.getGParent());
		node.setGParent(null);
		assertNull("gParent should be settable to null", node.getGParent());
		
		//pParent
		node.setPParent(parent2);
		assertEquals("pParent should be set to node parent2", parent2, node.getPParent());
		node.setPParent(null);
		assertNull("pParent should be settable to null", node.getPParent());
	}
	
	@Test
	public void testCanAccess() {
		ArrayList<Integer> planks = new ArrayList<Integer>();
		node = new PillarNode(0);
		
		planks.add(1);
		planks.add(4);
		node.addPlanks(planks);
		
		//Structured basis, good data
		//True case
		assertTrue("Should be able to cross pillars in planks array", node.canAccess(1));
		assertTrue("Should be able to cross pillars in planks array", node.canAccess(4));
		
		//False case
		assertFalse("Shouldn't be able to cross pillars not in planks array", node.canAccess(10));
		
		//Boundary
		node = new PillarNode(0);
		assertFalse("Shouldn't be able to cross pillars since we have no planks", node.canAccess(1));
		
		//Stress Test, boundaries
		//I initially used Integer.MAX_VALUE, but I overloaded the heap and almost crashed my computer
		int topVal = 100000;
		for (int n = 0; n <= topVal; n++) {
			planks.add(n);
		}
		node.addPlanks(planks);
		assertTrue("Should be able to access any element between 0 and topVal", node.canAccess((int)(Math.floor(Math.random()*topVal))));			
	}
	
	@Test
	public void testCompareTo() {
		PillarNode node1 = new PillarNode(1);
		PillarNode node2 = new PillarNode(2);
		//Structured basis, data-flow, good data
		//True-True case
		node1.setHValue(0);
		node1.setGValue(2);
		node1.setPValue(1);
		
		node2.setHValue(1);
		node2.setGValue(2);
		node2.setPValue(1);
		
		assertEquals("node1 is cheaper than node2, result should reflect that", -1, node1.compareTo(node2));
		
		//False-True case
		node1.setHValue(1);
		node1.setGValue(1);
		node1.setPValue(5);
		
		node2.setHValue(1);
		node2.setGValue(2);
		node2.setPValue(1);
		
		assertEquals("node1 is preferrable over node2, result should reflect that", -1, node1.compareTo(node2));
		
		//Bad data
		node1.setHValue(-1);
		node1.setGValue(-1);
		node1.setPValue(0);
		
		node2.setHValue(1);
		node2.setGValue(0);
		node2.setPValue(-3);
		
		assertEquals("Ignores negative values and keep old values, but node2 should now be cheaper", 1, node1.compareTo(node2));
		
	}
	
	@Test
	public void testAddPlanks() {
		PillarNode node = new PillarNode(0);
		ArrayList<Integer> testPlanks = new ArrayList<Integer>();
				
		//Structured basis, data flow
		//Bad data
		try {
			node.addPlanks(null);
			fail("Null as input should throw a NullPointerException");
		} catch (NullPointerException e) {}
		
		//Good data
		testPlanks.add(1);
		testPlanks.add(2);
		testPlanks.add(3);
		testPlanks.add(1);
		
		node.addPlanks(testPlanks);
		assertTrue("Node should contain newly added plank", node.canAccess(1));
		assertTrue("Node should contain newly added plank", node.canAccess(2));
		assertTrue("Node should contain newly added plank", node.canAccess(3));
		
		//Stress test
		int topVal = 10000;
		for (int n = 0; n <= topVal; n++) {
			testPlanks.add(n);
		}
		node.addPlanks(testPlanks);
		assertTrue("Plank should be accessible if within range of 0 to " + topVal, node.canAccess(topVal / 2));
	}
	
	
	//Private methods, use tester
	
	@Test
	public void testAddPlank() {
		PillarNode node = new PillarNode(0);
		this.tester = node.new Test();
		ArrayList<Integer> testPlanks = new ArrayList<Integer>();
		
		//Structured basis, data flow
		//Good data, boundary
		assertEquals("testPlanks shouldn't have any planks yet", 0, testPlanks.size());
		assertTrue("Should be able to add a plank we don't have yet", tester.addPlank(testPlanks, 1));
		assertTrue("testPlanks should have the newly added plank", testPlanks.contains(1));
		assertTrue("Should be able to add a plank we don't have yet", tester.addPlank(testPlanks, 2));
		assertTrue("testPlanks should have the newly added planks", testPlanks.contains(2));
		assertFalse("Shouldn't be able to add a plank we already have", tester.addPlank(testPlanks, 1));
		
	}
	
	@Test
	public void testThrowExceptionIfInputNull() {
		//Structured basis
				//Data-flow
				//Boundary
				//Compound boundaries
				//Bad data
				//Good data
				//Stress Test
		
	}
	
	@Test
	public void testIsCheaperThan() {
		//Structured basis
				//Data-flow
				//Boundary
				//Compound boundaries
				//Bad data
				//Good data
				//Stress Test
	}
	
	@Test
	public void testIsPreferredTo() {
		//Structured basis
				//Data-flow
				//Boundary
				//Compound boundaries
				//Bad data
				//Good data
				//Stress Test
	}
}
