import java.util.HashSet;
import java.util.LinkedList;

public class Main {
	
	public static void main(String[] args) throws Exception {

	// A test of the Dancing links algorithm
		int[][] M = new int[8][7];

		M[0] = new int[] {0, 0, 1, 0, 1, 1, 0};
		M[1] = new int[] {1, 0, 0, 1, 0, 0, 0};
		M[2] = new int[] {0, 1, 1, 0, 0, 1, 0};
		M[3] = new int[] {0, 0, 0, 1, 0, 1, 1};
		M[4] = new int[] {0, 1, 0, 0, 0, 0, 1};
		M[5] = new int[] {0, 0, 0, 1, 1, 0, 1};
		M[6] = new int[] {0, 0, 0, 0, 0, 0, 1};
		M[7] = new int[] {0, 0, 0, 0, 0, 1, 1};

		Column column = new Column(M);
		System.out.println('\n' + visualizeMatrix(M));
		System.out.println("Column:\n" + column);
		System.out.println("Nodes:");
		System.out.println(column.getL().getD());
		System.out.println(column.getR().getU());
		System.out.println(column.getR().getR().getU());

		System.out.println(column.getR().getClass());
		System.out.println(column.getL().getD().getClass());
	}

	// Function to visualize a matrix
	public static String visualizeMatrix(int[][] M) {
		String S = new String();
		for (int i = 0; i < M.length; i++) {
			for (int j = 0; j < M[0].length; j++) {
				S += String.valueOf(M[i][j]) + " ";
			}
			S += "\n";
		}
		return S;
	}
}
