package maze_amg188;

import java.util.ArrayList;

/**
 * PillarNode class
 * @author Adam Gleichsner
 * Node class to represent a pillar in a Jones maze
 */
public class PillarNode implements Comparable<PillarNode> {
	
	private int id; //Unique number based on position in the maze
	private int hValue; //Heuristic value, steps away from the exit
	private int gValue; //Number of steps between start node and this node without using planks
	private int pValue; //Number of steps between start node and this node with using a plank
	private PillarNode gParent; //Parent node when plank is not used
	private PillarNode pParent; //Parent node when plank is used
	private ArrayList<Integer> planks; //List of all immediately accessible nodes from the current node
 
	/**
	 * Constructor for PillarNode
	 * We initialize all other variables as base conditions or impossible states, in the
	 * case of the values
	 * @param id - unique id based on position in the maze
	 */
	public PillarNode(int id) {
		this.id = id;
		this.hValue = -1;
		this.gValue = -1;
		this.pValue = -1;
		
		this.gParent = null;
		this.pParent = null;
		this.planks = new ArrayList<Integer>();
	}
	
	//Public methods
	
	//Getters and setters
	/**
	 * Getter method for the id
	 * @return int - id
	 */
	public int getID() {
		return this.id;
	}
	
	/**
	 * Getter method for the heuristic value
	 * @return int - hValue
	 */
	public int getHValue() {
		return this.hValue;
	}
	
	/**
	 * Getter method for the plank-less cost
	 * @return int - gValue
	 */
	public int getGValue() {
		return this.gValue;
	}
	
	/**
	 * Getter method for the planked cost
	 * @return int - pValue
	 */
	public int getPValue() {
		return this.pValue;
	}
	
	/**
	 * Getter method for plank-less parent
	 * @return PillarNode - gParent
	 */
	public PillarNode getGParent() {
		return this.gParent;
	}
	
	/**
	 * Getter method for planked parent
	 * @return PillarNode - pParent
	 */
	public PillarNode getPParent() {
		return this.pParent;
	}
	
	/**
	 * Setter method for heuristic value
	 * @param hValue - Value to set to
	 */
	public void setHValue(int hValue) {
		this.hValue = hValue;
	}
	
	/**
	 * Setter method for plank-less cost
	 * @param gValue - Value to set to
	 */
	public void setGValue(int gValue) {
		this.gValue = gValue;
	}
	
	/**
	 * Setter method for planked cost
	 * @param pValue - Value to set to
	 */
	public void setPValue(int pValue) {
		this.pValue = pValue;
	}
	
	/**
	 * Setter method for plank-less parent
	 * @param gParent - new parent node
	 */
	public void setGParent(PillarNode gParent) {
		this.gParent = gParent;
	}
	
	/**
	 * Setter method for planked parent
	 * @param pParent - new parent node
	 */
	public void setPParent(PillarNode pParent) {
		this.pParent = pParent;
	}
	
	/**
	 * Method that returns whether or not the pillar can be accessed
	 * via existing planks from this pillar
	 * @param pillar - pillar to travel to
	 * @return boolean - true if possible, false otherwise
	 * @throws NullPointerException - if input is null
	 */
	public boolean canAccess(int pillarID) {
		if(this.planks.contains(pillarID))
			return true;
		return false;
	}
	
	/**
	 * Method to add a list of pillars that are accessible by plank to
	 * the planks list
	 * @param accessiblePillars - list of pillars to add
	 * @throws NullPointerException - if input is null
	 */
	public void addPlanks(ArrayList<Integer> accessiblePillars) {
		this.throwExceptionIfInputNull(accessiblePillars);
		//For each of the accessible pillars, add to planks
		for (Integer pillarID: accessiblePillars) {
			this.addPlank(pillarID);
		}
	}
	
	
	/**
	 * Overridden method compareTo to accurately put a priority on pillars with the
	 * lowest g + h values or p + h values, with preference going to the g + h value
	 * in the case of a tie
	 */
	@Override
	public int compareTo(PillarNode otherNode) {
		//If p + h and g + h are outright smaller or if g + h is equal to the other node's p + h
		if (this.isCheaperThan(otherNode) || this.isPreferredTo(otherNode))
			return -1;
		//Otherwise, we must be greater
		return 1;
	}
	
	//Private methods
	/**
	 * Private helper that adds a plank 
	 * @param pillarID
	 * @return
	 * @throws NullPointerException - if input is null
	 */
	private boolean addPlank(int pillarID) {
		this.throwExceptionIfInputNull(pillarID);
		//If we already have a plank, then don't add another
		if(this.planks.contains(pillarID))
			return false;

		planks.add(pillarID);
		return true;
	}

	/**
	 * Helper method that will check a series of inputs and throw an exception
	 * if any are null
	 * @param objects - collection of objects to check if null
	 * @throws NullPointerException
	 */
	private void throwExceptionIfInputNull(Object...objects) throws NullPointerException {
		for(Object obj: objects) {
			if(obj == null)
				throw new NullPointerException("Error: Input is null");
		}
	}
	
	/**
	 * Private helper that compares two nodes and returns whether or not the node
	 * is cheaper than the other.
	 * @param otherNode - Node to compare against
	 * @return boolean - true if this node is cheaper, false otherwise
	 */
	private boolean isCheaperThan(PillarNode otherNode) {
		int hDiff = this.hValue - otherNode.getHValue();	//Calculate ahead for simplicity
		//If this g + h value is less than the other's g + h and p + h or
		//this p + h is less than the other's g + h and p + h
		if ((this.gValue + hDiff < otherNode.getGValue() &&
				this.gValue + hDiff < otherNode.getPValue()) ||
				(this.pValue + hDiff < otherNode.getGValue() &&
				this.pValue + hDiff < otherNode.getPValue()))
			return true;
		return false;
	}
	
	/**
	 * Private helper that compares two nodes and, if this g + h equals the other's
	 * p + h, returns true, noting that this node is preferred.
	 * @param otherNode - Node to compare against
	 * @return boolean - true if equal and preferred, false otherwise
	 */
	private boolean isPreferredTo(PillarNode otherNode) {
		int hDiff = this.hValue - otherNode.getHValue();
		//If g + h equals other's p + h and this p + h isn't outright less than
		//the other's, give priority to this node
		if (this.gValue + hDiff == otherNode.getPValue() &&
				this.gValue + hDiff == otherNode.getPValue() &&
				this.pValue + hDiff >= otherNode.getGValue() &&
				this.pValue + hDiff >= otherNode.getPValue())
			return true;
		return false;
	}
	
	/**
	 * @author Adam Gleichsner
	 * Helper subclass to allow us to test the private methods
	 */
	public class Test {
		
	}
}
