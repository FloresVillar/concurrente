//======================================================
public class BusquedaDfsBfs {
	//---------------------------------------------
	public static void main(String[] args){
		
	}
	//---------------------------------------------
}
//===================================================

class Arista{
	int datoOrigen;
	int datoDestino;
	float peso;
	

}
//===================================================

class Vertice{
	int datoOrigen;
	arista arist;
	vertice sgteVertice;
}
//==================================================
class Grafo{
	Vertice pGrafo;
	int n;
	int ar;
	//-----------------------------
	Grafo(){
		pGrafo = null;
		n = 0;
		ar = 0;
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
	boolean existeArtista(int x, int y){
		Vertice p = pGrafo;
		Arista a;
		while(p!=null){
			if(p.DatoOrigen == x){
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
}
//======================================================
