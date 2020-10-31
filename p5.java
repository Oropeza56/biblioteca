import java.util.*;

class Libro{

	String nombreL;
	String autor;
	String editorial;
	int pag;
	int añoP;

	Libro(){
		super ();
	}	

	Libro (String nombreL, String autor, String editorial, int pag, int añoP ){
		super();
		this.nombreL= nombreL;
		this.autor = autor;
		this.editorial = editorial;
		this.pag= pag;
		this.añoP=añoP;
	}

	public void setNombreL (String nombreL){
		this.nombreL =nombreL;
	}

	public String getNombreL (){
		return this.nombreL;
	}

	public void setAutor (String autor){
		this.autor=autor;
	}

	public String getAutor(){
		return this.autor;
	}

	public void setEditorial (Stirng editorial){
		this.editorial= editorial;
	}

	public String getEditorial(){
		return this.editorial;
	}

	public void setPag (int pag){
		this.pag= pag;
	}

	public int getPag (){
		return this.pag;
	}

	public void setAñop (int añoP){
		this.añoP= añoP;
	}

	public int getAñop (){
		return this.añoP;
	}
}

class Alumno{
	String nombre;
	int numCuenta;
	int dias;
	ArrayList<Libro> librosPrestados= new ArrayList<Libro>();

	Alumno(){
		super();
	}

	Alumno(String nombre, int numCuenta, int dias){
		super();
		this.nombre= nombre;
		this.numCuenta=numCuenta;
		this.dias=dias;
	}

	public void setNombre (String nombre){
		this.nombre=nombre;
	}

	public String getNombre (){
		return this.nombre;
	}

	public void setNumCuenta (int numCuenta){
		this.numCuenta=numCuenta;
	}

	public int getNumCuenta(){
		return this.numCuenta;
	}

	public void setDias (int dias){
		this.dias=dias;
	}

	public int getDias(){
		return this.dias;
	}

	public boolean addLibroPrestado(Libro newLibro){
		librosPrestados.add(newLibro);
		
		if(librosPrestados.contains(newLibro)) return true;
		return false;
	}
	
	public boolean removeLibroPrestado(Libro removeLibro){
		librosPrestados.remove(removeLibro);
		
		if(!librosPrestados.contains(removeLibro)) return true;
		return false;
	}
	
	public String allLibrosPrestados(){
		return librosPrestados.toString();
	}
}

class Bibliotecario{
	
	Alumno alumno;
	
	Bibliotecario(String nombreA, int numCuentaA, int diasA){
		alumno = new Alumno(nombreA, numCuentaA, diasA);
	}	
	
	public boolean agregarLIbrosPrestados(Libro objLibros){
		return alumno.addLibroPrestado(objLibros);
	}

	public boolean entregarLibro (Libro objLibros){
		return alumno.removeLibroPrestado(objLibros);
	}

	public String sancionLibro (int diasA){
		if (diasA> 7) {
			return "Ya tienes una multa, deberas pagar $20 pesos";
		}
		else{ 
			return "Lo entregaste a tiempo";
		}
	}
}


class SistemaPrestamo{
	public static void main(String args[]){
		
	}
}
