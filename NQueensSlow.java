import java.util.Scanner;

public class NQueensSlow
{
	private int[][] queens;
	private final int COL = 0;
	private final int DIAG1 = 1;
	private final int DIAG2 = 2;
	private int arrangements;
	private int n;

	public NQueensSlow(int n)
	{
		this.n = n;
		// each queen has info column, \ diagonal, / diagonal
		queens = new int[n][3];
		arrangements = check_row(0);
	}

	public int getArrangements()
	{
		return arrangements;
	}

	private int check_row(int row)
	{
		int sum = 0;
		for (int col = 0; col < n; col++)
		{
			if (can_insert(row, col)) 
			{
				if (row == n - 1) 
				{
					sum += 1;
					continue;
				}
				insert_queen(row, col);
				sum += check_row(row + 1);
			}
		}
		return sum;
	}

	private void insert_queen(int row, int col)
	{
		queens[row][COL] = col;
		queens[row][DIAG1] = row - col;
		queens[row][DIAG2] = row + col;
	}

	private boolean can_insert(int row, int col)
	{
		for (int i = 0; i < row; i++)
		{
			if (queens[i][COL] == col) return false;
			if (queens[i][DIAG1] == row - col) return false;
			if (queens[i][DIAG2] == row + col) return false;
		}
		return true;
	}

	public static void main(String[] args)
	{
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Enter the number of queens: ");
		NQueensSlow calcPositions = new NQueensSlow(keyboard.nextInt());
		System.out.printf("The number of valid arrangements is %d\n", calcPositions.getArrangements());
	}

}