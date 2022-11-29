import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;

//public class Column extends Node implements Iterable<Column>{
public class Column extends Node {
	private int size;
	private String name;

	public Column() {
		super();
		this.setColumn(this);
		size = 0;
		name = new String();
	}

	public Column(int length) {
		this();
		Column currColumn = this;
		for(int i = 0; i < length; i++){
			currColumn.setName("" + i);

			Column nextColumn = new Column();
			currColumn.linkR(nextColumn);
			currColumn = nextColumn;
		}
		currColumn.linkR(this);
	}

	public void addRow(int[] vector) throws Exception {
		Column currColumn = this;
		Node firstNode = new Node();
		Node currNode = firstNode;
		Node prevNode = currNode;

		for(int index=0; index < vector.length; index++){
			currColumn = currColumn.getR();
			if(vector[index] == 0) continue;

			currColumn.increment();
			currColumn.getU().linkD(currNode);
			currNode.linkD(currColumn);
			currNode.setColumn(currColumn);

			prevNode = currNode;
			currNode = new Node();
			prevNode.linkR(currNode);
		}
		currColumn = currColumn.getR();
		prevNode.linkR(firstNode);
		if(currColumn != this){
			throw new Exception("Differ in length");
		}
	}
	public Column(int[][] matrix) throws Exception {
		this(matrix[0].length);
		for(int i = 0; i < matrix.length; i++){
			this.addRow(matrix[i]);
		}
	}

	@Override
	public Column getR(){
		return (Column) super.getR();
	}

	@Override
	public Column getL(){
		return (Column) super.getL();
	}

	@Override
	public String toString(){
		String str = "";

		//for (Column currColumn : this) str += currColumn.getSize() + " ";
		for (Column currColumn = this.getR();
				currColumn != this;
				currColumn = currColumn.getR()){
			str += currColumn.getSize() + " ";
		}
		return str;
	}

	public String getName(){
		return this.name;
	}

	public int getSize(){
		return this.size;
	}

	public void setSize(int size){
		this.size = size;
	}

	public void setName(String name){
		this.name = name;
	}

	public void increment(){
		this.size++;
	}

	public void decrement(){
		this.size--;
	}

	public void cover(){
		this.removeHoriz();
		for (Node x = this.getD(); x != this; x = x.getD()) {
			for (Node y = x.getR(); y != x; y = y.getR()) {
				y.removeVert();
				y.getColumn().size--;
			}
		}
	}

	public void uncover(){
		for (Node x = this.getU(); x != this; x = x.getU()) {
			for (Node y = x.getL(); y != x; y = y.getL()) {
				y.restoreVert();
				y.getColumn().size++;
			}
		}
		this.restoreHoriz();
	}

	//Searches the column with the least amount of possible cover nodes
	public Column leastCovers() {
		Column argmin = this;
		int i=0;

		for (Column currColumn = (Column) this.getR();
				currColumn != this;
				currColumn = (Column) currColumn.getR()) {
				//currColumn = (Column) currColumn.rightNode) {
			System.out.println(currColumn);
			System.out.println("" + i);
			i++;
			if (currColumn.getSize() < argmin.getSize()) {
				argmin = currColumn;
			}
		}
		return argmin;
	}

	// Return a set of all Solutions to the problem,
	// where each solution is comprised of a set of nodes
	HashSet<HashSet<Node>> exactCover() {
		HashSet<HashSet<Node>> setOfSolutions =
		   	new HashSet<HashSet<Node>>();
		return setOfSolutions;
	}

	//Verifies if this is solved
	boolean isSolved() {
		return this.getR() == this;
	}

	/*
	@Override
	public Iterator<Column> iterator(){
		return new Iterator<Column>(){
			private Column currNode = Column.this;

			@Override
			public boolean hasNext(){
				return currNode.getR() != Column.this;
			}
			@Override
			public Column next(){
				if (!hasNext()) throw new NoSuchElementException();
				currNode = currNode.getR();
				return currNode;
			}
		};
	}
	*/
}
