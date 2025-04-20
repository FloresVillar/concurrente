import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
//===========================================================
public class EliminacionGaussiana{
	//---------------------------------------------------
	public static void main(String[] args){
	//double [][] A = new[5][5];
	double A [][] = {{4,2,3},{5,4,7},{1,10,0}};
	double b [] = {3,2,1};
	double X [] = EliminacionGaussiana(A,b);
	Imprimir(X);
	}
	//----------------------------------------------------
	public static double[] EliminacionGaussiana(double[][] M,double[]b){
		//pivote buscar menor elemento distinto de 0
		int n = M.length;
		for (int i=0;i<M.length;i++){
			int indMenor = i;
			double menor = M[i][i];
			for(int p=i;p<M.length;p++){
				if((M[i][p]<=menor) &&  (M[i][p]!=0)){
					menor = M[i][p];
					indMenor = p;
				}
			}
			if(indMenor!=i){
				//intercambiar filas , paralelizar
				final int indMen = indMenor;
				final int ii=i;
				ExecutorService poolIntercambiar = Executors.newFixedThreadPool(2);
				poolIntercambiar.submit(()->{
					for(int c=0;c<M.length;c++){
						double tmp = M[ii][c];
						M[ii][c] = M[indMen][c];
						M[indMen][c] = tmp;
					}
				});
				poolIntercambiar.submit(()->{
					double tmp = b[ii];
					b[ii] = b[indMen];
					b[indMen] = tmp;
				});
				poolIntercambiar.shutdown();
				try{
					poolIntercambiar.awaitTermination(Long.MAX_VALUE,TimeUnit.NANOSECONDS);
				}catch(InterruptedException e){
					Thread.currentThread().interrupt();
				}
			}
			ExecutorService poolEliminacion = Executors.newFixedThreadPool(4);
			final int ii=i;
			for(int j=ii+1;j<M.length;j++){//paralelizar este for
				final int jj=j;
				double mji = M[j][ii]/M[ii][ii];
				//eliminacion por filas
				poolEliminacion.submit(()->{
				for(int k=0;k<M.length;k++){
					M[jj][k] = M[jj][k] - mji*M[ii][k];
				}
				b[jj] = b[jj] - mji*b[ii];
				});
			}
			poolEliminacion.shutdown();
			try{
				poolEliminacion.awaitTermination(Long.MAX_VALUE,TimeUnit.NANOSECONDS);
			}catch(InterruptedException e){
				Thread.currentThread().interrupt();
			}
		}
		if(M[n-1][n-1]==0){
			return null;
		}else{
			double [] X = new double[n];
			X[n-1] = b[n-1]/M[n-1][n-1];
			for(int i = n-2;i>=0;i--){
				double suma = 0;
				for(int j = i + 1;j<n;j++){
					suma+=(M[i][j]*X[j]);
				}
				X[i] = (b[i] -suma)/M[i][i];
			}
			return X;
		}
	}
	//--------------------------------------------------------
	public static void Imprimir(double[]V){
		for(int i=0;i<V.length;i++){
			System.out.print(V[i]+"--");
		}
		System.out.println();
	}
}
//=================================================================
