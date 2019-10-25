

public class Practice{
	static int arr[] = { 3,5,6,9,1,2,0,-1 };

	static int prune( int depth,int node,boolean isMax,int alpha,int beta ){
		if( depth == 3 ){
			return arr[node];
		}
		if( isMax ){
			int best = Integer.MIN_VALUE;
			for( int i=0;i<2;i++ ){
				int a = prune(depth+1,node*2+i,false,alpha,beta);
				best = Math.max(best,a);
				alpha = Math.max(alpha,best);
				if( beta <= alpha )
					break;
			}	
			return best;
		}
		else{
			int best = Integer.MAX_VALUE;
			for( int i=0;i<2;i++ ){
				int a = prune(depth+1,node*2+i,true,alpha,beta);
				best = Math.min(best,a);
				beta = Math.	min(beta,best);
				if( beta <= alpha )
					break;
			}	
			return best;

		}
	} 

	public static void main(String[] args) {
		
		int ans  = prune( 0,0,true,Integer.MIN_VALUE,Integer.MAX_VALUE );	
		System.out.println(ans);
	}	
}
