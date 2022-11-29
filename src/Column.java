import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;

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

	public Column(int[][] matrix) throws Exception {
		this(matrix[0].length);
		for(int i = 0; i < matrix.length; i++){
			this.addRow(matrix[i]);
		}
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

		for (Node currColumn : this) {
			str += ((Column) currColumn).getSize() + " ";
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
}
