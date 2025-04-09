import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
//===========================================================
public class EliminacionGaussiana{
	//---------------------------------------------------
	public static void main(String[] args){
	double [][] A = new[5][5]; 
	eliminacionGaussiana(A);
	}
	//----------------------------------------------------
	public static double[] eliminacionGaussiana(double[][] M,double[]b){
		//pivote buscar menor elemento distinto de 0
		for (int i=0;i<M.length;i++){
			int indMenor = i;
			for(int p=i;p<M.length;p++){
				if((M[i][p]<=menor) &&  (M[i][p]!=0)){
					menor = M[i][p];
					indMenor = p;
				}
			}
			if(indMenor!=i){
				//intercambiar filas
				for(int c=0;c<M.length;c++){
					double tmp = M[i][c];
					M[i][c] = M[indMenor][c];
					M[indMenor][c] = tmp;
				}
				double tmp = b[i];
				b[i] = b[indMenor];
				b[indMenor] = tmp;
			}
			for(int j=i+1;j<M.length;j++){
				double mji = M[j][i]/M[i][i];
				//eliminacion por filas
			}
		}
		if(M[n-1][n-1]==0){
			return;
		}else{
			double X[] = new double[n];
			X[n-1] = b[n-1]/M[n-1][n-1]
			for(int i = n-2;i>=0;i--){
				double suma = 0;
				for(int j = i + 1;){
					suma+=(M[i][j]*X[j]);
				}
				X[i] = (b[i] -suma)/M[i][i];
			}
		}
		return X;
	}
	//--------------------------------------------------------
}
//=================================================================
