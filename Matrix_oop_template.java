import java.util.Scanner;

public class Matrix_oop_templatee 
{
    public static void main(String[] args)
    {

        // Define variables for rows and columns selection
        int row = 0;
        int col = 0;

        // Create Scanner object
        Scanner input = new Scanner(System.in);

        // TODO: Get rows and columns and validate the inputs
		do 
		{
			System.out.println("Enter a number of rows: ");
			row=input.nextInt();
			System.out.println("Enter a number of columns: ");
			col=input.nextInt();

		} 
		while (row>5 || col >5 || row<1 || col<1);
		
        // Create a matrix object using the Matrix class 
        Matrix m1 = new Matrix(row, col);

        // Print the newly created matrix, m1
        m1.printMatrix();

        // Create matrix objects for the transposed, column summed, 
        // and reversed rows results:
        Matrix tmat, cmat, rmat, armat, anmat;

        // Display menu, get user's menu selection, and call the 
        // appropriate instance method in the Matrix class
        String userInput = " ";
        do 
        {
            printMenu();
            userInput = input.next().toUpperCase();

            switch(userInput) 
            {
            case("T"): 
                tmat = m1.transpose();
                tmat.printMatrix();
                break; 
            case("C"): 
                cmat = m1.columnSum();
                cmat.printMatrix();
                break; 
            case("R"): 
                rmat = m1.reverseRows();
                rmat.printMatrix();
                break; 
            case("P"): 
                m1.printMatrix();
                break;
            case("AR"):
                armat=m1.add(m1.reverseRows());
                armat.printMatrix();
                break;
            case("AN"):
                System.out.print("\nEnter a value you would like to add to each element: ");
            	int num = input.nextInt();
            	m1.setNum(num);
            	anmat = m1.add();
            	anmat.printMatrix();
            	break;
            case("Q"): 
                System.out.println("\n  Exiting.\n");
                input.close();
                break;
            default:
                System.out.println("\n  Error, invalid input.");
                break;
            }
        } while (!userInput.equals("Q"));
    }
    
    public static void printMenu() 
    {
        System.out.println("\n");
        System.out.println("______________________________________________________________________\n");
        System.out.println("  T transpose   - Rows become colums (and vice versa)");
        System.out.println("  C columnSum   - Caclulate the sum of the values in each column");
        System.out.println("  R reverseRows - Reverse all elements in every row of the matrix");
        System.out.println("  P printMatrix - Print the original matrix");
        System.out.println("  AR addReverse - Add the reverse to the original matrix");
        System.out.println("  AN addNumeber - Add a number to the original matrix");
        System.out.println("  Q quit        - Exit the program");
        System.out.println("______________________________________________________________________\n");

        System.out.print("\n  Enter: T, C, R, P, AR, AN or Q =>  ");
    }     
}


class Matrix  
{
    int[][] mat;  // mat is declared as an instance variable (note no 'static' keyword)
    
    // Constructor that initializes the matrix to 0 1 2, then 10 11 12, then 20 21 22, ...
    Matrix(int row, int col) 
    {
        mat = new int [row][col];

        // TODO: Initialize mat
		for (int i=0; i<row; i++)
		{
			for (int j=0; j<col; j++)
			{
				mat[i][j]=i*10+j;
			}
		}
    }
    
    // Constructor that initializes the entire matrix to the value passed in.
    Matrix(int row, int col, int value) 
    {
        mat = new int [row][col];

        // TODO: Initialize mat with value
        for (int i=0; i<row; i++)
		{
			for (int j=0; j<col; j++)
			{
				mat[i][j]=value;
			}
		}
    }

    void printMatrix() 
    {
        // TOOD: Print out matrix mat
        for (int i=0; i<mat.length; i++)
		{
			for (int j=0; j<mat[i].length; j++)
			{
				System.out.printf("%3d", mat[i][j]);
			}
			System.out.print("\n");
		}
    }

    Matrix transpose() 
    {
        // Create the matrix, mtran, that will hold the results
        Matrix mtran = new Matrix(mat[0].length, mat.length);

        // TODO: Transpose matrix mat into matrix mtran and return mtran
        int trow = mat.length;
		int tcol = mat[0].length;
		int[][] transposed = new int[tcol][trow];
		for(int i = 0; i < tcol; i++) {
			for (int j = 0; j < trow; j++) {
				transposed[i][j] = mat[j][i];
			}
		}
		mtran.setMatrix(transposed);
        return mtran;
    }

    Matrix columnSum() 
    {
        // Create the matrix, mcolsum, that will hold the results-this matrix has 1 row
        // Initialize each element of mcolsum to 0 using the appropriate constructor
        Matrix mcolsum = new Matrix(1, mat[0].length, 0);

        // TODO: Sum the columns of matrix mat into matrix mcolsum and return mcolsum
        int ccol=mat[0].length;
		int[][]sum=new int [1][ccol];
		for (int i=0; i<mat.length; i++)
		{
			for (int j=0; j<mat[i].length; j++)
			{
				sum[0][j]+=mat[i][j];
			}
		}
		mcolsum.setMatrix(sum);
        return mcolsum;
    }

    Matrix reverseRows() 
    {
        // Create the matrix, mrev, that will hold the results
        Matrix mrev = new Matrix(mat.length, mat[0].length);

        // TODO: Reverse the rows of matrix mat into matrix mrev and return mrev
        int rrow=mat.length;
		int rcol=mat[0].length;
		int[][] reverse=new int [rrow][rcol];
		int value=rcol-1;
		for (int i=0; i<rcol; i++)
		{
			for (int j=0; j<rrow; j++)
			{
				reverse[j][i]=mat[j][value];
			}
			value--;
		}
		mrev.setMatrix(reverse);
        return mrev;
    }

    Matrix add(Matrix matrix)
    {
        int[][] matrixArray = matrix.getMatrix();
        int arow=mat.length;
        int acol=mat[0].length;
        for (int i=0; i<arow; i++)
        {
            for (int j = 0; j<acol; j++)
            {
                mat[i][j] = mat[i][j] + matrixArray[i][j];
            }
        }
        return this;
    }
    void setMatrix(int[][] matrix)
    {
    	mat=matrix;
    }
    int[][] getMatrix()
    {
        return mat;
    }
    private int numToAdd=0;
    public void setNum(int numToAdd)
    {
        this.numToAdd=numToAdd;
    }
    Matrix add() {
    //int[][] matrixArray = matrix.getMatrix();
            int arow=mat.length;
            int acol=mat[0].length;
            for (int i=0; i<arow; i++)
            {
                for (int j = 0; j<acol; j++)
                {
                    mat[i][j] = mat[i][j] + this.numToAdd;
                }
            }
        return this;
    }
}
