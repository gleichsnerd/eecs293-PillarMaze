package maze_amg188;

import java.util.ArrayList;

public class PillarNode {
	
	int xPos;
	int yPos;
	int hValue; //Heuristic value, steps away from the exit
	int gValue; //Number of steps between start node and this node without using planks
    int pValue; //Number of steps between start node and this node with using a plank
    PillarNode gParent; //Parent node when plank is not used
    PillarNode pParent; //Parent node when plank is used
    ArrayList<PillarNode> planks; //List of all immediately accessible nodes from the current node
 
	public PillarNode(int hValue, int gValue, int pValue) {
		this.hValue = hValue;
		this.gValue = gValue;
		this.pValue = pValue;
		
		this.gParent = null;
		this.pParent = null;
		this.planks = new ArrayList<PillarNode>();
	}
	
	public int getHValue() {
		return this.hValue;
	}
	
	public int getGValue() {
		return this.gValue;
	}
	
	public int getPValue() {
		return this.pValue;
	}
	
	public void setHValue(int hValue) {
		this.hValue = hValue;
	}
	
	public void setGValue(int gValue) {
		this.gValue = gValue;
	}
	
	public void setPValue(int pValue) {
		this.pValue = pValue;
	}
	
	public PillarNode getGParent() {
		return this.gParent;
	}
	
	public PillarNode getPParent() {
		return this.pParent;
	}
	
	public void setGParent(PillarNode gParent) {
		this.gParent = gParent;
	}
	
	public void setPParent(PillarNode pParent) {
		this.pParent = pParent;
	}
	
	public ArrayList<PillarNode> getPlanks() {
		return this.planks;
	}
	
	public boolean isAccessible(PillarNode pillar) {
		if(this.planks.contains(pillar))
			return true;
		return false;
	}
	
	public boolean addPlank(PillarNode pillar) {
		this.throwExceptionForNullInput(pillar);
		if(this.planks.contains(pillar))
			return false;
	
		planks.add(pillar);
		return true;
	}
	
	public void throwExceptionForNullInput(Object...objects) throws NullPointerException {
		for(Object obj: objects) {
			if(obj == null)
				throw new NullPointerException("Error: Input is null");
		}
	}

}
