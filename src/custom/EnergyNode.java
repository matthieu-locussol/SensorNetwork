package custom;

import javax.swing.tree.DefaultMutableTreeNode;

public class EnergyNode extends DefaultMutableTreeNode {

	private static final long serialVersionUID = -5837805659553664789L;
	
	public EnergyNode(String s) {
		super(s);
	}
	
	public EnergyNode(int i) {
		super(Integer.toString(i));
	}

	public EnergyNode() {
		super();
	}

	public boolean equals(Object o) {
		boolean isEquals = false;
		if(o instanceof EnergyNode) {
			EnergyNode other = (EnergyNode) o;
			
			Object value1 = getUserObject();
			Object value2 = other.getUserObject();
			
			isEquals = value1.equals(value2);
		}
		return isEquals;
	}
	
	public int hashCode() {
		return getUserObject().hashCode();
	}
	
}
