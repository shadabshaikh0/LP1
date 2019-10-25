#include<stdio.h>
#include "omp.h"
int q[1000];
int visited[7];
int local_q;

void bfs( int adjmatrix[7][7],int first,int last,int q[] ,int n_nodes){
	if( first == last )
		return;
	int curr = q[first++];
	printf("%d ",curr );
	omp_set_num_threads(3);
	#pragma omp parallel for shared(visited)
	for (int i = 0; i < n_nodes; ++i)
	{
		if( adjmatrix[curr][i] ==1 && visited[i] == 0 ){
			q[last++] = i;
			visited[i] = 1;
		}
	}
	bfs( adjmatrix,first,last,q,n_nodes );
}

int main(){
	int first = -1;
	int last =0;
	int n_nodes =7;
	for( int i=0;i<n_nodes;i++ ){
		visited[i] = 0;
	}
	int adjmatrix[7][7] = {
													{0,  1  ,1  ,0  ,0  ,0  ,0},
													{1	,0	,1	,1	,0	,0	,0},
													{1	,1	,0	,0	,1	,0	,0},
													{0	,1	,0	,0	,1	,0	,0},
													{0	,0	,1	,1	,0	,1	,0},
													{0	,0	,0	,0	,1	,0	,1},
													{0	,0	,0	,0	,0	,1	,0}
													};
	int start =1;
	q[last++] = start;
	first++;
	visited[start]=1;
	int cur;
	bfs( adjmatrix,first,last,q,n_nodes );

	return 0;
}