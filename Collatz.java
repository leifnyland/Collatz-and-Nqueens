import java.util.Scanner;

public class Collatz 
{
	private int maxLen;
	private int maxNum;
	private int[] lookupTable;
	private final int TABLE_SIZE = Integer.MAX_VALUE/16;

	public Collatz()
	{
		maxLen = 0;
		maxNum = 0;
		lookupTable = new int[TABLE_SIZE];
	}
	public void calculateMax(int a, int b)
	{
		int tempLen;
		maxLen = maxNum = 0;
		for (int i = a; i <= b; i++) 
		{
			tempLen = collatzLength(i);
			if (tempLen > maxLen)
			{
				maxLen = tempLen;
				maxNum = i;
			}
		}
	}

	private int collatzLength(long x)
	{
		if (x < TABLE_SIZE)
		{
			if (lookupTable[(int)x] != 0) return lookupTable[(int)x];
			else if (x == 1) return lookupTable[1] = 1;
			else if (x % 2 == 0) return lookupTable[(int)x] = collatzLength(x/2) + 1;
			else return lookupTable[(int)x] = collatzLength(3*x + 1) + 1;	
		}
		else
		{
			if (x % 2 == 0) return collatzLength(x/2) + 1;
			else return collatzLength(3 * x + 1) + 1;
		}
	}

	public static void main(String[] args)
	{
		Scanner keyboard = new Scanner(System.in);
		int a, b;
		Collatz collatz = new Collatz();
		System.out.print("Enter the range: ");
		a = keyboard.nextInt();
		b = keyboard.nextInt();
		keyboard.close();

		collatz.calculateMax(a, b);

		System.out.printf("The number with the maximum Collatz length in the range: %d\n", collatz.maxNum);
		System.out.printf("Its Collatz length: %d\n", collatz.maxLen);
	}
}