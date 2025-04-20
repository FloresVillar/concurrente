//======================================================
public class BusquedaDfsBfs {
	//---------------------------------------------
	public static void main(String[] args){
		Grafo g=new Grafo();
                g.insertarVertice(0);
                g.insertarVertice(1);
                g.insertarVertice(2);
                g.insertarVertice(3);
                g.insertarVertice(4);
                g.insertarVertice(5);
                g.insertarVertice(6);
                g.insertarVertice(7);
    		g.insertarArista(0,6,53);
    		g.insertarArista(0,1,32);
    		g.insertarArista(0,2,29);
    		g.insertarArista(0,5,60);
    		g.insertarArista(4,3,34);
    		g.insertarArista(5,3,18);
    		g.insertarArista(5,4,40);
    		g.insertarArista(6,4,51);
    		g.insertarArista(7,4,46);
    		g.insertarArista(7,0,31);
    		g.insertarArista(7,6,25);
    		g.insertarArista(7,1,21);
    		g.imprimirGrafo(); 
	}
	//---------------------------------------------
}
//===================================================

class Arista{
	int datoOrigen;
	int datoDestino;
	float peso;
	Arista sgteArista;
	Vertice vdest;

}
//===================================================

class Vertice{
	int datoOrigen;
	Arista arist;
	Vertice sgteVertice;
}
//==================================================
class Grafo{
	Vertice pGrafo;
	int n;
	int nar;
	//-----------------------------
	Grafo(){
		pGrafo = null;
		n = 0;
		nar = 0;
	}
	//-----------------------------
	int lon(){
		return n;
	}
	//---------------------------
	void insertarVertice(int x){
		if(!existeVertice(x)){
			Vertice p , aux;
			p = new Vertice();
			n++;
			p.datoOrigen = x;
			p.arist = null;
			if(pGrafo ==null){
				p.sgteVertice = pGrafo;
				pGrafo = p;
			}
			else{
				aux = pGrafo;
				while(aux.sgteVertice!=null){
					aux = aux.sgteVertice;
				}
				aux.sgteVertice = p;
				p.sgteVertice = null;
			}
		}
		else{
			System.out.println("ya existe");
		}
	}
	//----------------------------
	boolean existeVertice(int x){
		Vertice p = pGrafo;
		while(p!=null){
			if(p.datoOrigen == x){
				return true;
			}
			p = p.sgteVertice;
		}
		return false;
	}
	//---------------------------------

	Vertice buscarPosV(int  dato){
		Vertice p = pGrafo;
		if(p==null){
			return null;
		}
		else{
			while(p!=null){
				if(p.datoOrigen == dato){
					return p;
				}
				p = p.sgteVertice;
			}

		}
		return null;
	}
	//---------------------------------
	Arista existeArtista(int x, int y){
		Vertice p = pGrafo;
		Arista a;
		while(p!=null){
			if(p.datoOrigen == x){
				a = p.arist;
				while(a!=null){
					if(a.datoDestino == y){
						return a;
					}
					a = a.sgteArista;
				}
			}
		}
		return null;
	}
	//-----------------------------------------
	void insertarArista(int x,int y,float peso){
		Vertice p,q;
		Arista a,b;
		p = buscarPosV(x);
		q = buscarPosV(y);
		nar++;
		if(p!=null && q!=null){
			a = new Arista();
			a.datoOrigen = x;
			a.datoDestino = y;
			a.peso = peso;
			a.sgteArista = p.arist;
			a.vdest = q;
			p.arist = a;
			b = new Arista();
			b.datoOrigen = y;
			b.datoDestino = x;
			b.peso = peso ;
			b.sgteArista = q.arist;
			b.vdest = p;
			q.arist=b;
		}
	}
	//-----------------------------------------
	void imprimirGrafo(){
		Vertice p;
		Arista a;
		p = pGrafo;
		if(p == null){
			System.out.println("grafo vacio");
		}else{
			System.out.println("vertice | arista");
			while(p!=null){
				System.out.print(p.datoOrigen+"\t|");
				a = p.arist;
				while(a!=null){
					System.out.print(" "+a.datoDestino+" ");
					a = a.sgteArista;
				}
				System.out.println();
				p = p.sgteVertice;
			}
		}
	}
	//----------------------------------------
}
//====================================================
