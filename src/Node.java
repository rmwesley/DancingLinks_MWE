public class Node implements Iterable<Node> {
	private Node upNode;
	private Node downNode;
	private Node leftNode;
	private Node rightNode;
	private Column column;

	public Node() {
		upNode = this;
		downNode = this;
		leftNode = this;
		rightNode = this;
		column = null;
	}

	@Override
	public String toString() {
		String str = this.column.getSize() + " ";
		for (Node node : this){
			str += node.column.getSize() + " ";
		}
		return str;
	}

	@Override
	public java.util.Iterator<Node> iterator(){
		return new java.util.Iterator<Node>(){
			private Node current = Node.this;

			@Override
			public boolean hasNext(){
				return current.getR() != Node.this;
			}
			@Override
			public Node next(){
				if (!hasNext()) {
					throw new java.util.NoSuchElementException();
				}
				current = current.getR();
				return current;
			}
		};
	}

	public Column getColumn(){
		return this.column;
	}

	public void setColumn(Column column){
		this.column = column;
	}

	public Node getR(){
		return this.rightNode;
	}

	public Node getD(){
		return this.downNode;
	}

	public Node getL(){
		return this.leftNode;
	}

	public Node getU(){
		return this.upNode;
	}

	void removeHoriz() {
		this.rightNode.leftNode = this.leftNode;
		this.leftNode.rightNode = this.rightNode;
	}

	void removeVert() {
		this.downNode.upNode = this.upNode;
		this.upNode.downNode = this.downNode;
	}
	
	void restoreVert() {
		this.downNode.upNode = this;
		this.upNode.downNode = this;
	}

	void restoreHoriz() {
		this.rightNode.leftNode = this;
		this.leftNode.rightNode = this;
	}

	//Create an horizontal link between nodes
	public void linkD(Node other) {
		this.downNode = other;
		other.upNode = this;
	}

	//Create a vertical link between nodes
	public void linkR(Node other) {
		this.rightNode = other;
		other.leftNode = this;
	}
	void addHoriz(Node other) {
		other.rightNode = this.rightNode;
		other.leftNode = this;
	}

	void addVert(Node other) {
		other.downNode = this.downNode;
		other.upNode = this;
	}
}
