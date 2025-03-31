import java.lang.System;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.Random;
import java.util.Scanner;
//=============================================
public class Ordenamientos{
	//--------------------------------------
	public static void main(String[] args){
		int A[] = {10,4,3,4,5,6,14,25,8,90};
		Random rand = new Random();
		Scanner in = new Scanner(System.in);
		System.out.println("cantidad de elementos");
		int n = in.nextInt();
		int M[] = new int[n];
		for(int i=0;i<n;i++){
			M[i]=rand.nextInt(1,100);
		}
		//burbujaSerial(A);
		imprimir("",M,"\n");
		burbujaParalela(M);
	}
	//---------------------------------------
	public static void burbujaSerial(int []A){
		for(int i=0;i<A.length;i++){
			for(int j=0;j<A.length-i-1;j++){
				if(A[j]>A[j+1]){
					int aux = A[j];
					A[j] = A[j+1];
					A[j+1] = aux;
				}
			}
		}
	}
	//---------------------------------------
	public static void burbujaParalela(int []A){
		Thread hilos[] = new Thread[4];
		int n=A.length;
		int h =(int)n/4;
		int [][] arr = new int[4][];
		for(int i=0;i<arr.length;i++){
			if(i==3){
				arr[i]=new int[h+n%4];
			}else{
				arr[i]=new int[h];
			}
			System.arraycopy(A,i*h,arr[i],0,arr[i].length);
			imprimir("",arr[i],"\t");
		}
		System.out.println();
		for(int ii =0;ii<hilos.length;ii++){
			final int i=ii;
			hilos[i]= new Thread(new Runnable(){
				public void run(){
					burbujaSerial(arr[i]);
				}
			});
		}
		for(Thread hil :hilos){
			hil.start();
		}
		for(Thread hil:hilos){
			try{
				hil.join();
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		for(int i=0;i<arr.length;i++){
			imprimir("",arr[i],"\t");
		}
		System.out.println();
		//ensamblar de acuerdo a analisis previo
		int[] M12 = ensamblar(arr[0],arr[1]);
		int[] M123 = ensamblar(M12,arr[2]);
		int[] M1234 = ensamblar(M123,arr[3]);
	}
	//---------------------------------------
	public static int[] ensamblar(int[]A, int[]B){
		int AB[] = new int[A.length + B.length];
		imprimir("",A,"\t");
		imprimir("",B,"\t");
		int posA = 0;
		int posB = 0;
		for(int i=0;i<AB.length;i++){
			if(A[posA]<B[posB]){
				AB[i] = A[posA];
				posA++ ;
				if(posA==A.length){
					for(int j=i+1;j<AB.length;j++){
						AB[j] = B[posB];
						posB++;
					}
					break;
				}
			}else{
				AB[i] = B[posB];
				posB++;
				if(posB==B.length){
					for(int j=i+1;j<AB.length;j++){
						AB[j]=A[posA];
						posA++;
					}
					break;
				}
			}
		}
		imprimir("=>\t",AB,"\n");
		return AB;
	}
	//----------------------------------------
	public static void imprimir(String Before,int[]A,String After){
		System.out.print(Before);
		for(int i=0;i<A.length;i++){
			if(i==A.length-1){
				System.out.print(A[i]);
			}else{
				System.out.print(A[i]+"-");
			}
		}
		System.out.print(After);
	}
	//--------------------------------------
}
//==================================================
