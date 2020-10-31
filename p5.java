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
	ArrayList<Libros> librosPrestados= new ArrayList<Libros>();

	Alumno(){
		super();
	}

	Alumno(String nombre, int numCuenta){
		super();
		this.nombre= nombre;
		this.numCuenta=numCuenta;
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

	

}

class Bibliotecario{

public boolean agregarLIbrosPrestados( Libros objLibros){
		librosPrestados.add(objLibros);
		if (librosPrestados.contains(objLibros)) {
			return true;
		}

		return false;
	}

	public boolean entregarLibro (Libros objLibros){
		librosPrestados.remove(objLibros);
		if (!librosPrestados.contains(objLibros)) {
			return true;
		}
		return false;
	}


}


class SistemaPrestamo{

}
