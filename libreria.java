public class Libreria{
	private String nombreLibro;
	private String id;
	private int numDeEjemp;
	private String seccion;

	public String getNombreLibro (){
		return nombreLibro;
	}

	public String getId(){
		return id;
	}

	public int getnumDeEjemp(){
		return numDeEjemp;
	}

	public Libreria(String nombreLibro, String id, int numDeEjemp){
		super ();
		this.nombreLibro = nombreLibro;
		this.id = id;
		this numDeEjemp= numDeEjemp;
	}


	ArrayList <Libro> loteLibros= new ArrayList<Libro>();
}
