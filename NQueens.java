import java.util.Scanner;

public class NQueens
{
	private int[] Ldiag;
	private int[] Rdiag;
	private int[] columns;
	private int arrangements;
	private int n;

	public NQueens(int n)
	{
		this.n = n;
		// each queen has info column, \ diagonal, / diagonal
		Ldiag = new int[2*n];
		Rdiag = new int[2*n];
		columns = new int[n];
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
				remove_queen(row, col);
			}
		}
		return sum;
	}

	private void insert_queen(int row, int col)
	{
		columns[col]++;
		Ldiag[Ldiag(row, col)]++;
		Rdiag[Rdiag(row, col)]++;
	}

	private void remove_queen(int row, int col)
	{
		columns[col]--;
		Ldiag[Ldiag(row, col)]--;
		Rdiag[Rdiag(row, col)]--;
	}

	private int Ldiag(int row, int col)
	{
		return row - col + n;
	}

	private int Rdiag(int row, int col)
	{
		return row + col;
	}

	private boolean can_insert(int row, int col)
	{
		return (columns[col] == 0 && Ldiag[Ldiag(row, col)] == 0 && Rdiag[Rdiag(row, col)] == 0);
	}

	public static void main(String[] args)
	{
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Enter the number of queens: ");
		NQueens calcPositions = new NQueens(keyboard.nextInt());
		System.out.printf("The number of valid arrangements is %d\n", calcPositions.getArrangements());
	}

}