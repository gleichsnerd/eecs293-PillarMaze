package maze_amg188;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Maze class
 * @author Adam Gleichser
 * Given a size of a system of pillars and a layout of planks between these pillars, 
 * Maze has the capabilities of finding the shortest path across this maze with the
 * ability of placing one extra plank at any point to reduce the path length.
 * 
 * Uses a modified A* algorithm.
 * Worst case: O(n^2)
 * Average: O(nlogn)
 */
public class Maze {

	//2 dimensional list of all the pillars in the system
	private ArrayList<ArrayList<PillarNode>> pillars;
	
	public class Test {
		
		/**
		 * Returns the pillar with the corresponding id based on its position in the array
		 * @param id - id of desired pillar
		 * @return PillarNode - desired PillarNode
		 */
		public PillarNode getPillarByID(ArrayList<ArrayList<PillarNode>> testPillars, int id) {
			PillarNode returnPillar;
			
			//Save old maze arrangement
			ArrayList<ArrayList<PillarNode>> oldPillars = pillars;
			
			//Set testing condition
			pillars = testPillars;
			
			//Conduct test
			returnPillar = Maze.this.getPillarByID(id);
			
			//Reset maze
			pillars = oldPillars;
			
			return returnPillar;
		}
		
		/**
		 * Calculates the heuristic value of the pillar by comparing position to the exit
		 * @param currentPillarID
		 * @param endPillarID
		 * @return int - H Value
		 */
		public int calculateHValue(ArrayList<ArrayList<PillarNode>> testPillars, int currentPillarID, int endPillarID) {
			int returnHValue;
			
			//Save old maze arrangement
			ArrayList<ArrayList<PillarNode>> oldPillars = pillars;
			
			//Set testing condition
			pillars = testPillars;
			
			//Conduct test
			returnHValue = Maze.this.calculateHValue(currentPillarID, endPillarID);
			
			//Reset maze
			pillars = oldPillars;
			
			return returnHValue;
		}
		
		/**
		 * Sorts the open set to find the node with the lowest g + h or p + h with
		 * preference going to g + h in a tie
		 * @param openSet
		 * @return PillarNode - cheapest pillar in openSet
		 */
		public PillarNode getLowestCostNode(Set<PillarNode> openSet) {
			PillarNode cheapestPillar = Maze.this.getLowestCostNode(openSet);
			return cheapestPillar;
		}
		
		/**
		 * Finds the shortest path after the algorithm is ran by following the trail of
		 * gParents and pParents
		 * @param node 
		 * @return ArrayList<PillarNode> - List of the pillars in the shortest path
		 */
		public ArrayList<PillarNode> getShortestPathFromNode (PillarNode node){
			ArrayList<PillarNode> shortestPath = Maze.this.getShortestPathFromNode(node);
			return shortestPath;
		}
		
		/**
		 * Links the neighbor to the currentPillar by setting currentPillar to either gParent or pParent
		 * depending on the step. Only does this when neighbor hasn't been traversed, i.e. it isn't in
		 * closedSet, and it is possible to traverse neighbor
		 * @param currentPillar
		 * @param neighbor
		 * @param closedSet
		 * @param tempGValue
		 * @param tempPValue
		 */
		public void linkNeighborToPath(PillarNode currentPillar, PillarNode neighbor, Set<PillarNode> closedSet, int tempGValue, int tempPValue) {
			Maze.this.linkNeighborToPath(currentPillar, neighbor, closedSet, tempGValue, tempPValue);
		}
		
		/**
		 * Helper that returns whether or not neighbor should be added to the openSet, i.e. if neighbor can
		 * be traversed and it's in neither the open nor the closed set
		 * @param neighbor
		 * @param openSet
		 * @param closedSet
		 * @param gValue
		 * @param pValue
		 * @return boolean - true if should be added, false otherwise
		 */
		public boolean shouldBeAddedToOpenSet(PillarNode neighbor, Set<PillarNode> openSet, Set<PillarNode> closedSet, int gValue, int pValue) {
			Boolean shouldBeAdded = Maze.this.shouldBeAddedToOpenSet(neighbor, openSet, closedSet, gValue, pValue);
			return shouldBeAdded;
		}
		
		/**
		 * Determines if it's possible to access based on gValue and pValue
		 * @param gValue
		 * @param pValue
		 * @return boolean - true if it can be accessed, false otherwise
		 */
		public boolean neighborCanBeTraversed (int gValue, int pValue) {
			Boolean isTraversable = Maze.this.neighborCanBeTraversed(gValue, pValue);
			return isTraversable;
		}
		
		/**
		 * Determines if the gParent should be set, i.e. it's cheaper to go from current to
		 * neighbor this way
		 * @param neighbor
		 * @param gValue
		 * @return boolean - true if cheaper, false otherwise
		 */
		public boolean gParentShouldBeSet(PillarNode neighbor, int gValue) {
			Boolean shouldBeSet = Maze.this.gParentShouldBeSet(neighbor, gValue);
			return shouldBeSet;
		}
		
		/**
		 * Returns all neighboring pillars around currentPillar
		 * @param pillar
		 * @return ArrayList<PillarNode> - List of all neighbors
		 */
		public ArrayList<PillarNode> getNeighbors(ArrayList<ArrayList<PillarNode>> testPillars, PillarNode pillar) {
			ArrayList<PillarNode> neighbors;
			
			//Save old maze arrangement
			ArrayList<ArrayList<PillarNode>> oldPillars = pillars;
			
			//Set testing condition
			pillars = testPillars;
			
			//Conduct test
			neighbors = Maze.this.getNeighbors(pillar);
			
			//Reset maze
			pillars = oldPillars;
			
			return neighbors;
		}
		
		/**
		 * Calculates the tempGValue based on whether or not we can traverse nextPillar
		 * without using our extra plank
		 * @param currentPillar
		 * @param nextPillar
		 * @return int - tempGValue
		 */
		public int calculateGValue(PillarNode currentPillar, PillarNode nextPillar) {
			int returnGValue = Maze.this.calculateGValue(currentPillar, nextPillar);
			return returnGValue;
		}
			
		/**
		 * Calculates tempPValue based on whether or not we have to traverse nextPillar
		 * using a plank in our path
		 * @param currentPillar
		 * @param nextPillar
		 * @return int - tempPValue
		 */
		public int calculatePValue(PillarNode currentPillar, PillarNode nextPillar) {
			int pValue = Maze.this.calculatePValue(currentPillar, nextPillar);
			return pValue;
		}
		
		/**
		 * Creates the 2D matrix of maze pillars by initializing one for each position and assigning planks
		 * @param width
		 * @param height
		 * @param plankLayout
		 * @return
		 */
		public ArrayList<ArrayList<PillarNode>> createMazePillars(int width, int height, Map<Integer, ArrayList<Integer>> plankLayout) {
			ArrayList<ArrayList<PillarNode>> returnPillars = Maze.this.createMazePillars(width, height, plankLayout);
			return returnPillars;
		}
		
		/**
		 * Grabs the pillars accessible by natural planking and returns a list of ids
		 * @param x - x position
		 * @param y - y position
		 * @param width
		 * @param height
		 * @param plankLayout
		 * @return ArrayList<Integer> - list of accessible ids
		 */
		public ArrayList<Integer> getPillarsAccessibleByPlanks(int x, int y, int width, int height, Map<Integer, ArrayList<Integer>> plankLayout) {
			ArrayList<Integer> returnPillars = Maze.this.getPillarsAccessibleByPlanks(x, y, width, height, plankLayout);
			return returnPillars;
		}

		/**
		 * Checks if planks are between two adjoining pillars
		 * @param pillar1ID
		 * @param pillar2ID
		 * @param width
		 * @param height
		 * @return boolean - true if they are, false otherwise
		 */
		public boolean pillarsAreAdjoining(int pillar1ID, int pillar2ID, int width, int height) {
			return Maze.this.pillarsAreAdjoining(pillar1ID, pillar2ID, width, height);
		}
		
		/**
		 * Checks all inputs and throws exception if one is null
		 * @param objects
		 * @throws NullPointerException
		 */
		public void throwExceptionIfInputNull(Object...objects) throws NullPointerException {
			Maze.this.throwExceptionIfInputNull(objects);
		}
		
		/**
		 * Throws an exception if this.pillars is null or 0x0
		 * @throws UninitializedObjectException
		 */
		public void throwExceptionIfMazeInvalid(ArrayList<ArrayList<PillarNode>> testPillars) throws UninitializedObjectException {
			//Save old maze arrangement
			ArrayList<ArrayList<PillarNode>> oldPillars = pillars;
			
			//Set testing condition
			pillars = testPillars;
			
			//Conduct test
			Maze.this.throwExceptionIfMazeInvalid();
			
			//Reset maze
			pillars = oldPillars;
		}
	}
	
	/**
	 * Constructor for maze class, initialize with size and plank layout in the form of a 
	 * map between a pillar id and all the pillars it is immediately connected with
	 * @param width - number of pillars wide
	 * @param height - number of pillars tall
	 * @param plankLayout - Map of ids to connected pillar ids
	 */
	public Maze(int width, int height, Map<Integer, ArrayList<Integer>> plankLayout) {
		this.pillars = this.createMazePillars(width, height, plankLayout);
	}
	
	//Getter Method
	/**
	 * Getter method for the 2D ArrayList of pillars
	 * @return ArrayList<ArrayList<PillarNode>> - Essentially the maze of 
	 */
	public ArrayList<ArrayList<PillarNode>> getPillars() {
		return this.pillars;
	}
	
	/**
	 * Method to find the shortest path given a start and end pillar id. Throws an error if
	 * the maze hasn't been initialized or is a 0x0 matrix
	 * @param startPillarID
	 * @param endPillarID
	 * @return ArrayList<PillarNode> - A list of all the pillars in the shortest path
	 * @throws UninitializedObjectException
	 */
	public ArrayList<PillarNode> findShortestPath (int startPillarID, int endPillarID) throws UninitializedObjectException {
		ArrayList<PillarNode> neighbors; //List to hold all neighboring pillars
		Set<PillarNode> openSet;	//List of pillars we can traverse to
		Set<PillarNode> closedSet; 	//List of pillars traversed
		PillarNode currentPillar;   //Current pillar we are at
		int tempGValue;				//Temporary plank-less cost
		int tempPValue;				//Temporary planked cost
		
		//There are no pillars to traverse. Looks like it's curtains for Dr. Jones.
		this.throwExceptionIfMazeInvalid();
		
		//Get the first pillar in the path and set as the start
		currentPillar = this.getPillarByID(startPillarID);
		currentPillar.setHValue(this.calculateHValue(currentPillar.getID(), endPillarID));
		currentPillar.setGValue(0);
		currentPillar.setPValue(0);
		currentPillar.setGParent(null);
		currentPillar.setPParent(null);
		
		//Start the algorithm with the starting point as the only element in the set
		openSet = new HashSet<PillarNode>();
		openSet.add(this.getPillarByID(startPillarID));
		//Start the closed set as the empty set
		closedSet = new HashSet<PillarNode>(); 
	
		//While there are still nodes to traverse
		while(!openSet.isEmpty()) {
			//Get the pillar with the lowest g + h or p + h cost, preference going to g + h in a tie
			currentPillar = getLowestCostNode(openSet);
			
			//If we have found the end, return the shortest path
			if (currentPillar.getID() == endPillarID)
				return getShortestPathFromNode(currentPillar);
			
			//Acknowledge that we're currently traversing this pillar
			openSet.remove(currentPillar);
			closedSet.add(currentPillar);
			
			neighbors = this.getNeighbors(currentPillar);
			
			//For each of the neighbors, calculate temp G and P and assign its H value
			for (PillarNode neighbor: neighbors) {
				tempGValue = this.calculateGValue(currentPillar, neighbor);
				tempPValue = this.calculatePValue(currentPillar, neighbor);
				neighbor.setHValue(this.calculateHValue(neighbor.getID(), endPillarID));
				
				//Links current pillar to either gParent or pParent depending on the situation, set G and P 
				this.linkNeighborToPath(currentPillar, neighbor, closedSet, tempGValue, tempPValue);
				//If the neighbor isn't in the closed or open set, add it to the open set
				if(shouldBeAddedToOpenSet(neighbor, openSet, closedSet, tempGValue, tempPValue))
					openSet.add(neighbor);
			}
		}
		
		//If openSet is empty and we haven't already returned a path, then there is no path since we've traversed all pillarNodes
	    return null;
	}

	//Private methods
	
	/**
	 * Returns the pillar with the corresponding id based on its position in the array
	 * @param id - id of desired pillar
	 * @return PillarNode - desired PillarNode
	 */
	private PillarNode getPillarByID(int id) {
		int height = pillars.size();
		int y = (int) Math.floor(id/height);	//Find which base multiple of 4 the id is
		int x = id - y * height;
		
		return this.pillars.get(y).get(x);
	}
	
	/**
	 * Calculates the heuristic value of the pillar by comparing position to the exit
	 * @param currentPillarID
	 * @param endPillarID
	 * @return int - H Value
	 */
	private int calculateHValue(int currentPillarID, int endPillarID) {
		int hValue = -1;
		int height = pillars.size();
		int curY = (int) Math.floor(currentPillarID/height);
		int curX = currentPillarID - curY * height;
		int endY = (int) Math.floor(endPillarID/height);
		int endX = endPillarID - endY * height;
		
		//HValue is the theoretical minimum steps to exit
		hValue = Math.abs(curY - endY) + Math.abs(curX - endX);
		
		return hValue;
	}
	
	/**
	 * Sorts the open set to find the node with the lowest g + h or p + h with
	 * preference going to g + h in a tie
	 * @param openSet
	 * @return PillarNode - cheapest pillar in openSet
	 */
	private PillarNode getLowestCostNode(Set<PillarNode> openSet) {
		PillarNode[] pillarArray;
		this.throwExceptionIfInputNull(openSet);
		//Create an array from the set so we can sort it and return the cheapest node
		pillarArray = openSet.toArray(new PillarNode[openSet.size()]);
		Arrays.sort(pillarArray);
	
		return pillarArray[0];
	}
	
	/**
	 * Finds the shortest path after the algorithm is ran by following the trail of
	 * gParents and pParents
	 * @param node 
	 * @return ArrayList<PillarNode> - List of the pillars in the shortest path
	 */
	private ArrayList<PillarNode> getShortestPathFromNode (PillarNode node){
		ArrayList<PillarNode> shortestPath = new ArrayList<PillarNode>();
		PillarNode currentNode = node;
		this.throwExceptionIfInputNull(node);
		//Start the path with the ending node
		shortestPath.add(currentNode);
		//While there are still parents to follow
		while (currentNode.getGParent() != null || currentNode.getPParent() != null) {
			//If pParent is null, then it must be a regular step, otherwise grab PParent because it's shortest
			if (currentNode.getPParent() != null)
				shortestPath.add(currentNode.getPParent());
			else
				shortestPath.add(currentNode.getGParent());
			currentNode = shortestPath.get(shortestPath.size() - 1);
		}
		
		return shortestPath;
	}
	
	/**
	 * Links the neighbor to the currentPillar by setting currentPillar to either gParent or pParent
	 * depending on the step. Only does this when neighbor hasn't been traversed, i.e. it isn't in
	 * closedSet, and it is possible to traverse neighbor
	 * @param currentPillar
	 * @param neighbor
	 * @param closedSet
	 * @param tempGValue
	 * @param tempPValue
	 */
	private void linkNeighborToPath(PillarNode currentPillar, PillarNode neighbor, Set<PillarNode> closedSet, int tempGValue, int tempPValue) {
		this.throwExceptionIfInputNull(currentPillar, neighbor, closedSet);
		
		//If we can actually get to neighbor and it's not in closed set
		if(this.neighborCanBeTraversed(tempGValue, tempPValue) && !closedSet.contains(neighbor)) {
			//If we're following a path in which we used our plank
			if(tempPValue - currentPillar.getPValue() == 1) {
				neighbor.setGValue(tempGValue);
				neighbor.setPValue(tempPValue);
				neighbor.setPParent(currentPillar);
			//If it's a regular path but this way is cheaper
			} else if (this.gParentShouldBeSet(neighbor, tempGValue)) {
				neighbor.setGValue(tempGValue);
				neighbor.setPValue(tempPValue);
				neighbor.setGParent(currentPillar);
			}
		}
	}
	
	/**
	 * Helper that returns whether or not neighbor should be added to the openSet, i.e. if neighbor can
	 * be traversed and it's in neither the open nor the closed set
	 * @param neighbor
	 * @param openSet
	 * @param closedSet
	 * @param gValue
	 * @param pValue
	 * @return boolean - true if should be added, false otherwise
	 */
	private boolean shouldBeAddedToOpenSet(PillarNode neighbor, Set<PillarNode> openSet, Set<PillarNode> closedSet, int gValue, int pValue) {
		this.throwExceptionIfInputNull(neighbor, openSet, closedSet);
		//If we can access it but we've never seen it before
		if (this.neighborCanBeTraversed(gValue, pValue) && !openSet.contains(neighbor) && !closedSet.contains(neighbor))
			return true;
		return false;
	}
	
	/**
	 * Determines if it's possible to access based on gValue and pValue
	 * @param gValue
	 * @param pValue
	 * @return boolean - true if it can be accessed, false otherwise
	 */
	private boolean neighborCanBeTraversed (int gValue, int pValue) {
		//If gValue and pValue aren't negative, then we can get there
		if (gValue >= 0 || pValue >=0)
			return true;
		return false;
	}
	
	/**
	 * Determines if the gParent should be set, i.e. it's cheaper to go from current to
	 * neighbor this way
	 * @param neighbor
	 * @param gValue
	 * @return boolean - true if cheaper, false otherwise
	 */
	private boolean gParentShouldBeSet(PillarNode neighbor, int gValue) {
		this.throwExceptionIfInputNull(neighbor);
		//If neighbor hasn't been pathed yet or it's cheaper to go this way
		if (neighbor.getGParent() == null || neighbor.getGValue() < 0 || gValue < neighbor.getGValue())
			return true;
		return false;
	}
	
	/**
	 * Returns all neighboring pillars around currentPillar
	 * @param pillar
	 * @return ArrayList<PillarNode> - List of all neighbors
	 */
	private ArrayList<PillarNode> getNeighbors(PillarNode pillar) {
		ArrayList<PillarNode> neighbors = new ArrayList<PillarNode>();
		
		this.throwExceptionIfInputNull(pillar);
		
		int height = pillars.size();
		int width = pillars.get(0).size();
		int id = pillar.getID();
		int y = (int) Math.floor(id/height);
		int x = id - y * height;
		
		//If we're at the bottom of the maze
		if (y + 1 == height) {
			neighbors.add(this.getPillarByID(id - width));
		//If we're at the top of the maze
		} else if (y == 0) {
			neighbors.add(this.getPillarByID(id + width));
		} else {
			neighbors.add(this.getPillarByID(id - width));
			neighbors.add(this.getPillarByID(id + width));
		}
		
		//If we're on the far right of the maze
		if (x + 1 == width) {
			neighbors.add(this.getPillarByID(id - 1));
		//If we're on the far left of the maze
		} else if (x == 0) {
			neighbors.add(this.getPillarByID(id + 1));
		} else {
			neighbors.add(this.getPillarByID(id - 1));
			neighbors.add(this.getPillarByID(id + 1));
		}
		
		return neighbors;
	}
	
	/**
	 * Calculates the tempGValue based on whether or not we can traverse nextPillar
	 * without using our extra plank
	 * @param currentPillar
	 * @param nextPillar
	 * @return int - tempGValue
	 */
	private int calculateGValue(PillarNode currentPillar, PillarNode nextPillar) {
		int gValue = -1;
		
		this.throwExceptionIfInputNull(currentPillar, nextPillar);
		
		//If we can access currentPillar without a plank
		if (currentPillar.getGValue() >= 0) {
			//If we can access nextPillar without a plank
			if (currentPillar.canAccess(nextPillar.getID()))
				gValue = currentPillar.getGValue() + 1;
			//If we have used a plank before, kill this route
			else if (currentPillar.getPValue() == 0) 
				gValue = -1;
		}
		return gValue;
	}
		
	/**
	 * Calculates tempPValue based on whether or not we have to traverse nextPillar
	 * using a plank in our path
	 * @param currentPillar
	 * @param nextPillar
	 * @return int - tempPValue
	 */
	private int calculatePValue(PillarNode currentPillar, PillarNode nextPillar) {
		int pValue = -1;
		
		this.throwExceptionIfInputNull(currentPillar, nextPillar);
		
		//If we can use the plank
		if (currentPillar.getPValue() >= 0) {
			//But we don't have to
			if (currentPillar.canAccess(nextPillar.getID())) {
				//If we're on a planked path
				if (currentPillar.getPValue() > 0)
					pValue = currentPillar.getPValue() + 1;
				else
					pValue = 0; 
			//If we have to use our plank
			} else if (currentPillar.getPValue() == 0)
				pValue = currentPillar.getGValue() + 1;
		}
		
		return pValue;
	}
	
	/**
	 * Creates the 2D matrix of maze pillars by initializing one for each position and assigning planks
	 * @param width
	 * @param height
	 * @param plankLayout
	 * @return
	 */
	private ArrayList<ArrayList<PillarNode>> createMazePillars(int width, int height, Map<Integer, ArrayList<Integer>> plankLayout) {
		ArrayList<ArrayList<PillarNode>> returnPillars = new ArrayList<ArrayList<PillarNode>>();
		ArrayList<Integer> accessiblePillars;
		ArrayList<PillarNode> tempArrayList;
		PillarNode newPillar;
		
		this.throwExceptionIfInputNull(plankLayout);
		
		//For each row
		for (int y = 0; y < height; y++) {
			tempArrayList = new ArrayList<PillarNode>();
			returnPillars.add(y, tempArrayList);
			//For each column
			for (int x = 0; x < width; x++) {
				newPillar = new PillarNode(y * height + x);
				accessiblePillars = getPillarsAccessibleByPlanks(x, y, width, height, plankLayout);
				newPillar.addPlanks(accessiblePillars);
				returnPillars.get(y).add(x, newPillar);
			}
		}
		
		return returnPillars;

	}
	
	/**
	 * Grabs the pillars accessible by natural planking and returns a list of ids
	 * @param x - x position
	 * @param y - y position
	 * @param width
	 * @param height
	 * @param plankLayout
	 * @return ArrayList<Integer> - list of accessible ids
	 */
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
		this.throwExceptionIfInputNull(plankLayout);
		
		if (plankLayout.size() > width * height) {
			//If we have more pillars in the layout than possible, throw an exception
			throw new IllegalArgumentException("plankLayout contains more pillars than the maze can hold");
		} 
		
		//If there are planks, grab 'em!
		if (plankLayout.containsKey(currentPillarID)) {
			returnPillars = plankLayout.get(currentPillarID);
			
			//Check that each plank is adjoining the current pillar
			for (Integer accessiblePillar: returnPillars) {
				if (!pillarsAreAdjoining(currentPillarID, accessiblePillar, width, height)) {
					throw new IllegalArgumentException("plankLayout contains an invalid configuration");
				}
			}
		}
		
		return returnPillars;
	}

	/**
	 * Checks if planks are between two adjoining pillars
	 * @param pillar1ID
	 * @param pillar2ID
	 * @param width
	 * @param height
	 * @return boolean - true if they are, false otherwise
	 */
	private boolean pillarsAreAdjoining(int pillar1ID, int pillar2ID, int width, int height) {
		//If pillar 2 is directly above or below pillar 1 or if pillar 2 is to the left or right and in the same row as pillar 1
		if (Math.abs(pillar1ID - pillar2ID) == height || (Math.abs(pillar1ID - pillar2ID) == 1 && Math.floor(pillar1ID/height) == Math.floor(pillar2ID/height)))
				return true;
		return false;
	}
	
	/**
	 * Checks all inputs and throws exception if one is null
	 * @param objects
	 * @throws NullPointerException
	 */
	private void throwExceptionIfInputNull(Object...objects) throws NullPointerException {
		for(Object obj: objects) {
			if(obj == null)
				throw new NullPointerException("Error: Input is null");
		}
	}
	
	/**
	 * Throws an exception if this.pillars is null or 0x0
	 * @throws UninitializedObjectException
	 */
	private void throwExceptionIfMazeInvalid() throws UninitializedObjectException {
		if(pillars == null || pillars.size() == 0)
			throw new UninitializedObjectException("Maze is either null or without pillars");
	}
	
	
}
