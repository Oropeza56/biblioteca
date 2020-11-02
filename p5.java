import java.util.*;
import java.io.*;

class Libro implements Serializable{

	String nombreL;
	String autor;
	String editorial;

	Libro(){
		super ();
	}	

	Libro (String nombreL, String autor, String editorial){
		super();
		this.nombreL= nombreL;
		this.autor = autor;
		this.editorial = editorial;
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

	public void setEditorial (String editorial){
		this.editorial= editorial;
	}

	public String getEditorial(){
		return this.editorial;
	}

	public String toString(){
		return nombreL+" | "+autor+" | "+editorial;
	}
}

class Alumno implements Serializable{
	String nombre;
	int numCuenta;
	String password;
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

	public void setPass(String password){
		this.password = password;	
	}

	public String getPass(){
		return this.password;
	}

	public int getNumLibros(){
		return this.librosPrestados.size();
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
		
		if(librosPrestados.contains(newLibro)){
			System.out.println("Sí");
			return true;
		}
		return false;
	}
	
	public boolean removeLibroPrestado(Libro removeLibro){
		librosPrestados.remove(removeLibro);
		
		if(!librosPrestados.contains(removeLibro)) return true;
		return false;
	}

	public String toString(){
		return nombre+" | "+numCuenta+" | "+dias+" | "+password+" | "+librosPrestados;
	}
}

class Bibliotecario{
	
	ArrayList<Libro> libreria = new ArrayList<Libro>();

	Bibliotecario(){
		super();
		Libro l1 = new Libro("calculo1", "pepe1", "pepinillo1");
		Libro l2 = new Libro("calculo2", "pepe2", "pepinillo2");
		Libro l3 = new Libro("calculo3", "pepe3", "pepinillo3");

		this.libreria.add(l1);
		this.libreria.add(l2);
		this.libreria.add(l3);
	}

	private int buscarLibro(String nombre){
		System.out.println("Entré");
		int i = 0;
		while(i < libreria.size()){
			System.out.println(libreria.get(i));
			if(libreria.get(i).getNombreL().equals(nombre)){
				return i;
			}
			++i;
		} return -1;
	}
	
	public boolean agregarLibrosPrestados(String nombreL, Alumno alumno){
		int i = buscarLibro(nombreL);
		System.out.println(i);
		if(i != -1){
			if(alumno.getNumLibros() <= 3){
				return alumno.addLibroPrestado(libreria.get(i));
			}
		}
		return false;
	}

	public boolean entregarLibro (Libro objLibros, Alumno alumno){
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

class Files{

	public void escribirArchivo(String nombre, ArrayList<Alumno> alumno){
		try{
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(nombre));
			os.writeObject(alumno);
			os.close();
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
	}

	public void escribirArchivoL(String nombre, ArrayList<Libro> libros){
		try{
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(nombre));
			os.writeObject(libros);
			os.close();
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
	}

	public ArrayList<Alumno> leerArchivo(String nombre){
		ArrayList<Alumno> alu = new ArrayList<Alumno>();

		try{
			ObjectInputStream is = new ObjectInputStream(new FileInputStream(nombre));
			alu = (ArrayList<Alumno>)is.readObject();
			is.close();
			return alu;
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		}
		return alu;
	}

	public ArrayList<Libro> leerArchivoL(String nombre){
		ArrayList<Libro> libreria = new ArrayList<Libro>();
		
		try{
			ObjectInputStream is = new ObjectInputStream(new FileInputStream(nombre));
			libreria = (ArrayList<Libro>)is.readObject();
			is.close();
			return libreria;
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		}
		return libreria;
	}
}

class SistemaPrestamo{
	ArrayList<Alumno> alumnosAlta = new ArrayList<Alumno>();

	private boolean iniciarSesion(int numCuenta, String pass, Alumno alu){
	
		for (Alumno alumno : alumnosAlta){
			if(alumno.numCuenta == numCuenta){
				if(alumno.password.equals(pass)){
					alu.setNombre(alumno.getNombre());
					alu.setNumCuenta(alumno.getNumCuenta());
					alu.setDias(alumno.getDias());
					alu.setPass(alumno.getPass());
					return true;
				}
			}
		}
		return false;
	}

	private boolean darAltaAlu(Alumno alu){
		if(!alumnosAlta.contains(alu)){
			alumnosAlta.add(alu);
			return true;
		}
		return false;
	}

	private void menu(Alumno alumno){
		Scanner sc = new Scanner(System.in);
		Bibliotecario biblio = new Bibliotecario();
		biblio.libreria = new Files().leerArchivoL("librosdisp.bin");
		
		System.out.println("\nHola "+alumno.getNombre()+", binevenido a la biblioteca.");
		System.out.println(alumno);

		System.out.println("\nEliga una opción:\n");
		System.out.println("1. Préstamo de libro.");
		System.out.println("2. Devolución de libro.");
		System.out.println("3. Renovación de libro.");
		System.out.println("4. Cerrar Sesion");
		for(;;){	
			switch(sc.nextInt()){
				case 1:
					System.out.print("Nombre del libro que busca: ");
					String nombreL = sc.next().toLowerCase();
					if(biblio.agregarLibrosPrestados(nombreL, alumno)){
						System.out.println("Listo, se le prestó el libro de "+nombreL+".");
						break;
					}
					System.out.println("No se pudo prestar el liro debido a problemas del sistema.");
					break;
				case 2:
					System.out.print("Introduzca los datos del libro a regresar.");
					System.out.print("Nombre: ");
					String nombre = sc.next().toLowerCase();
					System.out.print("Autor: ");
					String autor = sc.next().toLowerCase();
					System.out.print("Editorial: ");
					String editorial = sc.next().toLowerCase();

					Libro libro = new Libro(nombre, autor, editorial);

					if(biblio.entregarLibro(libro, alumno)){
						System.out.println("Se pudo regresar el libro con exito.");
						break;
					}
					System.out.println("No se pudo regresar el libro con exito.");
					break;
				case 3:
					break;
				case 4:
					new Files().escribirArchivoL("librosdisp.bin", biblio.libreria);
					break;
			}
			break;
		}
	}

	public static void main(String args[]){
		SistemaPrestamo sys = new SistemaPrestamo();
		Scanner sc = new Scanner(System.in);
		Alumno alu = new Alumno();
		Files file = new Files();


		sys.alumnosAlta = file.leerArchivo("alumnos.bin");

		for(;;){
			System.out.println("\nHola, bienvenido a la biblioteca.");
			System.out.println("Por favor inicia sesión o registrate.\n");

			System.out.println("1. Iniciar sesión.");
			System.out.println("2. Registrarse en el sistema.");
			System.out.println("3. Salir");
			int opcion = sc.nextInt();

			switch(opcion){
				case 1:
					System.out.print("Introduzca su numero de cuenta: ");
					int numCuenta = sc.nextInt();
					System.out.print("\nIntroduzca su contraseña: ");
					String pass = sc.next();

					if(sys.iniciarSesion(numCuenta, pass, alu)){
						sys.menu(alu);
						break;
					} else {
						System.out.println("Datos incorrectos o no está en el sistema.");
						break;
					}
				case 2:
					System.out.print("Escriba su nombre: ");
					alu.setNombre(sc.next());
					System.out.print("Escriba su numero de cuenta: ");
					alu.setNumCuenta(sc.nextInt());
					System.out.print("Escriba su nueva contraseña: ");
					alu.setPass(sc.next());

					if(sys.darAltaAlu(alu)){
						System.out.println("Se creó su usuario correctamemte.");
						sys.menu(alu);
						break;
					} else {
						System.out.println("No se pudo crear porque ya existe.");
						break;
					}
				case 3:
					sc.close();

					file.escribirArchivo("alumnos.bin", sys.alumnosAlta);

					System.exit(1);
				default:
					System.out.println("Introduzca una opción válida.");
			}
		}
	}
}
