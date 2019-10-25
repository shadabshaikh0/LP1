import java.util.*;

class Board{
	private String board[][];
	private int blankx,blanky;
	public Board(){
		this.board = new String[3][3];
	}
	public Board(Board b){
		this.board = b.board;
		this.blankx = b.blankx;
		this.blanky = b.blanky;
	}
	public void initBoard(){
		Scanner sc = new Scanner(System.in);
		for( int i=0;i<3;i++ ){
			for( int j=0;j<3;j++ ){
				board[i][j] = sc.next();
				if( board[i][j].equals("-") ){
					blankx = i;
					blanky = j;
				}
			}
		}
	}
	public String[][] getBoard(){
		return board;
	}

	public void setBoard( String[][] board ){
		for( int i=0;i<3;i++ ){
			for( int j=0;j<3;j++ ){
				this.board[i][j] = board[i][j];
			}
		}		
	}

    public int getBlankx()
    {
        return blankx;
    }
    
    public int getBlanky()
    {
        return blanky;
    }
     
    public void setBlankx(int x)
    {
        blankx= x;
    }
    
    public void setBlanky(int y)
    {
        blanky = y;
    }
    
    public void display()
    {
        for(int i=0; i<3; i++)
        {
            for(int j=0; j<3; j++)
            {
                System.out.print("\t"+board[i][j]);
            }
            System.out.println();
        }
    }

    public Board nextMove( int gn,Board goal ){
    	Board temp = new Board();
    	Board next = new Board();
    	int minfn = Integer.MAX_VALUE;
    	System.out.println("Possible Moves are:");
    	if( blanky > 0 ){
    		temp.setBoard(board);
    		temp.swap( blankx,blanky,blankx,blanky-1 );
    		int fn = temp.getHn( goal) + gn;
    		System.out.println( "Fn :"+ fn );
    		temp.display();
    		if( fn < minfn ){
    			minfn = fn;
    			next.setBoard(temp.board);
    			next.blankx = blankx;
    			next.blanky = blanky-1;
    		}
    	}
    	if( blanky < 2 ){
    		temp.setBoard(board);
    		temp.swap( blankx,blanky,blankx,blanky+1 );
    		int fn = temp.getHn( goal) + gn;
    		System.out.println( "Fn :"+ fn );
    		temp.display();
    		if( fn < minfn ){
    			minfn = fn;
    			next.setBoard(temp.board);
    			next.blankx = blankx;
    			next.blanky = blanky+1;
    		}
    	}
    	if( blankx > 0 ){
    		temp.setBoard(board);
    		temp.swap( blankx,blanky,blankx-1,blanky );
    		int fn = temp.getHn( goal) + gn;
    		System.out.println( "Fn :"+ fn );
    		temp.display();
    		if( fn < minfn ){
    			minfn = fn;
    			next.setBoard(temp.board);
    			next.blankx = blankx-1;
    			next.blanky = blanky;
    		}
    	}
    	if( blankx < 2 ){
    		temp.setBoard(board);
    		temp.swap( blankx,blanky,blankx+1,blanky);
    		int fn = temp.getHn( goal) + gn;
    		System.out.println( "Fn :"+ fn );
    		temp.display();
    		if( fn < minfn ){
    			minfn = fn;
    			next.setBoard(temp.board);
    			next.blankx = blankx+1;
    			next.blanky = blanky;
    		}
    	}
    	return next;
    }

    public void swap( int x1,int y1,int x2,int y2 ){
    	String temp = board[x1][y1];
    	board[x1][y1] = board[x2][y2];
    	board[x2][y2] = temp;
    }

    public boolean equals(Board b){
    	for( int i=0;i<3;i++ ){
    		for( int j=0;j<3;j++ ){
    			if( !this.board[i][j].equals( b.board[i][j] ) )
    				return false;
    		}
    	}
    	return true;
    }
    public int getHn(Board b){
    	int hn = 0;
    	for( int i=0;i<3;i++ ){
    		for( int j=0;j<3;j++ ){
    			if( !this.board[i][j].equals( b.board[i][j] ) )
    				hn++;
    		}
    	}
    	return hn;
    }
}

public class Practice{
	private int gn = 0;
	private Board start,goal;
	public void initStart(){
		System.out.println("Enter Start Board:");
		start = new Board();
		start.initBoard();
		System.out.println("Given Start Board is");
		start.display();
	}
	public void initGoal(){
		System.out.println("Enter goal Board:");
		goal = new Board();
		goal.initBoard();
		System.out.println("Given Goal Board is");
		goal.display();
	}

	public void solve(){
		Board curr = start;
		while(true){
			System.out.println("Board after "+gn+"Moves" );
			curr.display();
			if( curr.equals(goal) ){
				System.out.println("DONE");
				return;
			}
			gn++;
			curr = curr.nextMove(gn,goal);
		}
	}
	public static void main(String[] args) {
		Practice p = new Practice();
		p.initStart();
		p.initGoal();
		p.solve();
	}
}