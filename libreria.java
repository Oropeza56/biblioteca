public class Libreria{
	ArrayList <Libro> loteLibros= new ArrayList<Libro>();
	
	private boolean insertarLibro(Libro newLibro){
		loteLibros.add(newLibro);
		
		if(loteLibros.contains(newLibro)) return true;
		return false;
	}
	
	private boolean sacarLibro(Libro oldLibro){
		loteLibros.remove(oldLibro);
		
		if(!loteLibros.contains(oldLibro)) return true;
		
		return false;
	}
}
