/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication12;

/**
 *
 * @author abtpst
 */
public class Board 
{
    public final int rows = 5;
	public final int columns = 4;
	public char board[][] = new char[rows][columns];
	
	public Board() 
        {
		initializeBoard();
		showContent();
	}
	
        // initialize the board with blank values
	public void initializeBoard() {
		for (int i = 0; i < rows; i++)
			for (int j = 0; j < columns; j++)
				board[i][j] = ' ';
	}
	
        public void Copy(char [][] a)
        {    		for (int row=0; row<5; row++)
                            for (int col=0; col<4; col++)
                                board[row][col]=a[row][col];
                            }

        // display the current board
	public void showContent() 
        {
		System.out.println(" 0  1  2  3");
		for (int j = 0; j < rows; j++) {
			for (int k = 0; k < columns; k++)
				System.out.print("[" + board[j][k] + "]");
			System.out.println();
		}
		
	}

        // perform PUSH operation
	public boolean insert(int column, char currentPlayer) 
        {
		if (column > 3 || column < 0 || board[0][column] != ' ')
			return false;
		else { 
			for (int i = rows-1; i >= 0; i--)
				if (board[i][column] == ' ') {
					board[i][column] = currentPlayer;
					break;
				}
			return true;
			}
	}
      
      // perform POP operation  
      public boolean Pop(int c, char currentPlayer)
      {
                 if(board[4][c]==currentPlayer)
                  {
                      int i=4;
                      while(i>0)
                      {
                          board[i][c]=board[i-1][c];
                          i--;
                          if(board[i][c]==' ')
                              break;
                      }
                      if(i==0)
                          board[0][c]=' ';
                      return true;
                  }
                  else
                  {System.out.println("Removal is not possible in this column.");
                  
                   showContent();
                   return false;
                  }
      }
      
      // restore the board after the most recent POP
       public void PopRestore(int c, char r)
        {
            int i=4;
            while(board[i][c]!=' ')
                    i--;
            while(i<4)
            {
                board[i][c]=board[i+1][c];
                i++;
            }
            board[4][c]=r;
        }
       
       // restore the board after the most recent PUSH
	public void remove(int column) {
		for (int i = 0; i < rows; i++) {
			if (board[i][column] != ' ') {
				board[i][column] = ' ';
				break;
			}
		}
	}
	
        // check for victory
	public char isFinished() {
		//check for win horizontally
		for (int row=0; row<rows; row++) 
		    for (int col=0; col<columns-3; col++)
		    	if (board[row][col] != ' ' &&
		    		board[row][col] == board[row][col+1] &&  
					board[row][col] == board[row][col+2] && 
					board[row][col] == board[row][col+3]) 
					return board[row][col];
		//check for win vertically
		for (int row = 0; row < rows-3; row++)
		    for (int col = 0; col < columns; col++)
				if (board[row][col] != ' ' &&
					board[row][col] == board[row+1][col] &&
					board[row][col] == board[row+2][col] &&
					board[row][col] == board[row+3][col])
					return board[row][col];
		//check for win diagonally (upper left to lower right)
		for (int row = 0; row < rows-3; row++) 
		    for (int col = 0; col < columns-3; col++) 
				if (board[row][col] != ' ' &&
					board[row][col] == board[row+1][col+1] &&
					board[row][col] == board[row+2][col+2] &&
					board[row][col] == board[row+3][col+3]) 
					return board[row][col];
		//check for win diagonally (lower left to upper right)
		for (int row = 3; row < rows; row++) 
		    for (int col = 0; col < columns-3; col++) 
				if (board[row][col] != ' ' &&
					board[row][col] == board[row-1][col+1] &&
					board[row][col] == board[row-2][col+2] &&
					board[row][col] == board[row-3][col+3])
					return board[row][col];
		return ' ';
	}
	
        // check if the game is tied (the board is full)
	public boolean isTie() 
        {
		for (int j = 0; j < columns; j++)
				if (board[0][j] == ' ')
					return false;
		return true;
	}
	
        // check whether a PUSH move is valid
	public boolean isLegalMove(int column) {
		if (column > 3 || column < 0 || board[0][column] != ' ')
			return false;
		return true;
	}
	
	// calculate the utility value 
        public int evaluateContent() 
        {
            int val=0;    
            int val2=0;
            int val3=0;
            int val4=0;
            int v;
            int vB;
            int vR;
            
            int j;
            int i;
            
            for(i=0;i<5;i++)
            {
                for(j=0;j<4;j++)
                {
                   if((board[i][j]==3))
                    {
                        val++;
                    }
            
                }
            }
            
            for(i=0;i<5;i++)
            {
                for(j=0;j<3;j++)
                {
                    if((board[i][j]==board[i][j+1])&&(board[i][j+1]==3))
                    {
                        val2++;
                    }
            
                }
            }
            
            for(j=0;j<4;j++)
            {
                for(i=0;i<4;i++)
                {
                    if((board[i][j]==board[i+1][j])&&(board[i+1][j]==3))
                    {
                        val2++;
                    }
                }
            }
            
            for(i=0;i<5;i++)
            {
                for(j=0;j<2;j++)
                {
                    if((board[i][j]==board[i][j+1])&&(board[i][j+1]==board[i][j+2])&&(board[i][j+2]==3))
                    {
                        val3++;
                    }
            
                }
            }
            
            for(j=0;j<4;j++)
            {
                for(i=0;i<3;i++)
                {
                    if((board[i][j]==board[i+1][j])&&(board[i+1][j]==board[i+2][j])&&(board[i+2][j]==3))
                    {
                        val3++;
                    }
                }
            }
            
            for(i=0;i<5;i++)
                {
                    if((board[i][0]==board[i][1])&&(board[i][1]==board[i][2])&&(board[i][2]==board[i][3])&&(board[i][3]==3))
                    {
                        
                        val4++;
                    }
                }
            
            for(j=0;j<4;j++)
            {
                for(i=0;i<2;i++)
                {
                    if((board[i][j]==board[i+1][j])&&(board[i+1][j]==board[i+2][j])&&(board[i+2][j]==board[i+3][j])&&(board[i+3][j]==3))
                    {
                        val4++;
                    }
                }
            }
            
            vB=val2*2+val3*3+val+val4*4;
            ////////////////////////
            int valr=0;    
            int valr2=0;
            int valr3=0;
            int valr4=0;
            
             for(i=0;i<5;i++)
            {
                for(j=0;j<4;j++)
                {
                   if((board[i][j]==3))
                    {
                        valr--;
                    }
            
                }
            }
            
            for(i=0;i<5;i++)
            {
                for(j=0;j<3;j++)
                {
                    if((board[i][j]==board[i][j+1])&&(board[i][j+1]==3))
                    {
                        valr2--;
                    }
            
                }
            }
            
            for(j=0;j<4;j++)
            {
                for(i=0;i<4;i++)
                {
                    if((board[i][j]==board[i+1][j])&&(board[i+1][j]==3))
                    {
                        valr2--;
                    }
                }
            }
            
            for(i=0;i<5;i++)
            {
                for(j=0;j<2;j++)
                {
                    if((board[i][j]==board[i][j+1])&&(board[i][j+1]==board[i][j+2])&&(board[i][j+2]==3))
                    {
                        valr3--;
                    }
            
                }
            }
            
            for(j=0;j<4;j++)
            {
                for(i=0;i<3;i++)
                {
                    if((board[i][j]==board[i+1][j])&&(board[i+1][j]==board[i+2][j])&&(board[i+2][j]==3))
                    {
                        valr3--;
                    }
                }
            }
            
            for(i=0;i<5;i++)
                {
                    if((board[i][0]==board[i][1])&&(board[i][1]==board[i][2])&&(board[i][2]==board[i][3])&&(board[i][3]==3))
                    {
                        
                        valr4--;
                    }
                }
            
            for(j=0;j<4;j++)
            {
                for(i=0;i<2;i++)
                {
                    if((board[i][j]==board[i+1][j])&&(board[i+1][j]==board[i+2][j])&&(board[i+2][j]==board[i+3][j])&&(board[i+3][j]==3))
                    {
                        valr4--;
                    }
                }
            }
                        
            
            
            vR=valr2*2+valr3*3+valr+valr4*4;
            
            v=vB+vR;
            
            return v;
        }
}
