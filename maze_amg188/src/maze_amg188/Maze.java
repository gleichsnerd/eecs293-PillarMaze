package maze_amg188;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Maze {

	ArrayList<ArrayList<PillarNode>> pillars;
	
	public Maze(int width, int height, Map<Integer, ArrayList<Integer>> plankLayout) {
		this.pillars = this.createMazePillars(width, height, plankLayout);
	}
	
	private PillarNode getPillarByID(int id) {
		int height = pillars.size();
		int y = (int) Math.floor(id/height);
		int x = id - y * height;
		
		return this.pillars.get(y).get(x);
	}
	
	private int calculateHValue(int currentPillarID, int endPillarID) {
		int hValue = -1;
		int height = pillars.size();
		int curY = (int) Math.floor(currentPillarID/height);
		int curX = currentPillarID - curY * height;
		int endY = (int) Math.floor(endPillarID/height);
		int endX = endPillarID - endY * height;
		
		hValue = (int)(Math.abs(curY - endY) + Math.abs(curX - endX));
		
		return hValue;
	}
	
	private PillarNode getLowestCostNode(Set<PillarNode> openSet) {
		PillarNode[] pillarArray = openSet.toArray(new PillarNode[openSet.size()]);
		PillarNode returnNode = null;
		
		for(PillarNode pillar: pillarArray) {
			if (returnNode == null) 
				returnNode = pillar;
			else {
				//TODO write formula for lowest cost node
				if (pillar.getHValue() + pillar.getGValue() < returnNode.getHValue() + returnNode.getGValue());

			}
				
		}
		
			
		return returnNode;
	}
	
	private ArrayList<PillarNode> getShortestPathFromNode (PillarNode node){
		ArrayList<PillarNode> shortestPath = new ArrayList<PillarNode>();
		//TODO write function to add all parents to an array list
		return shortestPath;
	}
	
	public ArrayList<PillarNode> findShortestPath (int startPillarID, int endPillarID) {
		ArrayList<PillarNode> shortestPath;
		Set<PillarNode> openSet;
		Set<PillarNode> closedSet;
		PillarNode currentPillar;
		
		//There are no pillars to traverse. Looks like it's curtains for Dr. Jones.
		this.throwExceptionIfMazeInvalid();
		
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

	
		while(openSet.size() > 0) {
			currentPillar = getLowestCostNode(openSet);
			
			if (currentPillar.getID() == endPillarID)
				return getShortestPathFromNode(currentPillar);
			
			openSet.remove(currentPillar);
			closedSet.add(currentPillar);
			
			this.
			
			for each neighbor pillarNode to currentNode
            if neighbor is accessible (i.e. in pillarNode.planks)
                tempGValue = currentNode gValue + 1 //i.e. takes one leap
                if currentNode pValue > 0 (i.e. we've used a plank to get here)
                    tempPValue = currentNode.pValue + 1
                else
                    tempPValue = 0
                end if
            else if currentNode.pValue is 0 (i.e. we haven't used a plank yet)
                tempGValue = 0
                tempPValue = gValue
            else
                impossible traversal, goto next neighbor
            end if

            if neighbor is in closedSet    
               continue to next neighbor
            else
                set neighbor's pValue and gValue to temp values
                if we're not incrementing pValue and either the pillar has no gParent or the gValue is less (i.e. there's a plankless way to get there)
                    set gParent to currentNode
                else we are incrementing pValue
                    set pParent to currentNode
                end if

                if neighbor is not in openSet
                    add neighbor to openSet
                end if

            end if
        end for
		}
		
		//If openSet is empty and we haven't already returned a path, then there is no path since we've traversed all pillarNodes
        return null;
		
		
	    if pillarSet has no nodes
	        return failure condition (i.e. null)    //There are no pillars to traverse. Looks like it's curtains for Dr. Jones.
	    else if start equals exit
	        return List(start)   //We're already at the exit, so we know the path
	    else
	        while openSet is not empty
	            currentNode = pillarNode in the openSet with the lowest g + h or p + h values

	            if currentNode == exit
	                return the path by either following gParent or pParent if pValue is less than gValue

	            remove currentNode from openSet
	            add currentNode to closedSet


	            for each neighbor pillarNode to currentNode
	                if neighbor is accessible (i.e. in pillarNode.planks)
	                    tempGValue = currentNode gValue + 1 //i.e. takes one leap
	                    if currentNode pValue > 0 (i.e. we've used a plank to get here)
	                        tempPValue = currentNode.pValue + 1
	                    else
	                        tempPValue = 0
	                    end if
	                else if currentNode.pValue is 0 (i.e. we haven't used a plank yet)
	                    tempGValue = 0
	                    tempPValue = gValue
	                else
	                    impossible traversal, goto next neighbor
	                end if

	                if neighbor is in closedSet    
	                   continue to next neighbor
	                else
	                    set neighbor's pValue and gValue to temp values
	                    if we're not incrementing pValue and either the pillar has no gParent or the gValue is less (i.e. there's a plankless way to get there)
	                        set gParent to currentNode
	                    else we are incrementing pValue
	                        set pParent to currentNode
	                    end if

	                    if neighbor is not in openSet
	                        add neighbor to openSet
	                    end if

	                end if
	            end for
	        end while

	        //If openSet is empty and we haven't already found a path, then there is no path since
	        //we've traversed all pillarNodes
	        return failure condition (i.e. null)
	    end if
		
		
		return shortestPath;
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
		//If pillar 2 is directly above or below pillar 1 or if pillar 2 is to the left or right and in the same row as pillar 1
		if (Math.abs(pillar1ID - pillar2ID) == height || (Math.abs(pillar1ID - pillar2ID) == 1 && Math.floor(pillar1ID/height) == Math.floor(pillar2ID/height)))
				return true;
		return false;
	}
	
	private void throwExceptionIfInputNull(Object...objects) throws NullPointerException {
		for(Object obj: objects) {
			if(obj == null)
				throw new NullPointerException("Error: Input is null");
		}
	}
	
	private void throwExceptionIfMazeInvalid() throws UninitializedObjectException {
		if(pillars == null || pillars.size() == 0)
			throw new UninitializedObjectException("Maze is either null or without pillars");
	}
}
