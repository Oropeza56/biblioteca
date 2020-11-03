/**
 * Práctica 5 -- Programación Orientada a Objetos
 * 
 * Piña Rossette Marco Antonio.
 * 
 */


import java.util.*;

class Alumno{
    String nombre;
    int numCuenta;
    ArrayList<Materia> materiasInscritas = new ArrayList<Materia>();

    Alumno(){
        super();
    }

    Alumno(String nombre, int numCuenta){
        super();
        this.nombre = nombre;
        this.numCuenta = numCuenta;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getName(){
        return this.nombre;
    }

    public void setNumCuenta(int numCuenta){
        this.numCuenta = numCuenta;
    }

    public int getNumCuenta(){
        return this.numCuenta;
    }
    
    public String getMateriasInscritas(){
        return materiasInscritas.toString();
    }
    
    public boolean agregarMateriaInscrita(Materia objMateria){
        materiasInscritas.add(objMateria);
        if(materiasInscritas.contains(objMateria)){
            return true;
        }
        return false;
    }

    public boolean removerMateriaInscrita(Materia objMateria){
        materiasInscritas.remove(objMateria);
        if(!materiasInscritas.contains(objMateria)){
            return true;
        }
        return false;
    }
}

class Materia{
    String id;
    String nombre;
    int cupo;
    ArrayList<Alumno> inscritos = new ArrayList<Alumno>();
    
    Materia(String id){
        super();
        this.id = id;
    }

    Materia(){
        super();
    }

    public void setId(String id){
        this.id = id;    
    }

    public String getId(){
        return this.id;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getNombre(){
        return this.nombre;
    }

    public void setCupo(int cupo){
        this.cupo = cupo;
    }

    public int getCupo(){
        return this.cupo;
    }

    public Boolean estaLleno(){
        if(this.cupo == 0){
            return true;
        }
        return false;
    }

    public boolean inscribir(Alumno newAlumno){
        inscritos.add(newAlumno);
        if (inscritos.contains(newAlumno)){
            this.cupo--;
            return true;
        }
        return false;
    }

    public boolean remover(Alumno removeAlumno){
        inscritos.remove(removeAlumno);
        if(!inscritos.contains(removeAlumno)){
            return true;
        }
        return false;
    }

    public boolean alumnoInscrito(Alumno alumn){
        if(inscritos.contains(alumn)){
            return true;
        }
        return false;
    }
}

class Asignaturas{
    ArrayList<Materia> materias = new ArrayList<Materia>();

    public boolean existe(Materia mat){
        if(materias.contains(mat)){
            return true;
        }
        return false;
    }

    public Materia buscarMateria(Materia mat){
        return materias.get(materias.indexOf(mat));
    }

    public boolean inscribir(Materia newMateria){
        materias.add(newMateria);
        if(materias.contains(newMateria)){
            return true;
        }
        return false;
    }
}

class ControlEscolar{
    
    private static boolean inscribir(Asignaturas materiasArray, Scanner sc){

        Alumno newAlumno = new Alumno();
        
        System.out.print("Introduzca su numero de cuenta: ");
        newAlumno.setNumCuenta(sc.nextInt());
        System.out.print("Introduzca su nombre: ");
        newAlumno.setNombre(sc.next());

        System.out.print("Introduzca el identificador de la materia: ");
        String matId = sc.next();

        Materia test = new Materia(matId);

        if(!materiasArray.existe(test)){
            System.out.print("Esa materia no esta en el sistema");
            return false;
        }
        
        if (!materiasArray.buscarMateria(test).estaLleno()){
            if (materiasArray.buscarMateria(test).inscribir(newAlumno)){
                newAlumno.agregarMateriaInscrita(materiasArray.buscarMateria(test));
                return true;
            }
        }
        return false;
    }

    private static boolean remover(Asignaturas materiasArray, Scanner sc){
        
        Alumno removeAlumno = new Alumno();
        
        System.out.print("Introduzca su numero de cuenta: ");
        removeAlumno.numCuenta = sc.nextInt();
        System.out.print("Introduzca su nombre: ");
        removeAlumno.nombre = sc.next();

        System.out.print("Introduzca el identificador de la materia a remover: ");
        String matId = sc.next();

        Materia test = new Materia(matId);

        if(!materiasArray.existe(test)){
            System.out.print("Esa materia no esta en el sistema");
            return false;
        }

        if (materiasArray.buscarMateria(test).remover(removeAlumno)){
            removeAlumno.removerMateriaInscrita(materiasArray.buscarMateria(test));
            return true;
        }
        return false;
    }
    
    private static void mostrar(Scanner sc){
        
        Alumno alu = new Alumno();

        System.out.println("Introduce los datos del alumno a mostrar");
        System.out.print("Nombre: ");
        alu.setNombre(sc.next());
        System.out.print("Numero de cuenta: ");
        alu.setNumCuenta(sc.nextInt());

        System.out.println(alu.getMateriasInscritas());
    }
    
    private static boolean agregarMateria(Asignaturas materiasArray, Scanner sc){
        
        System.out.println("Introduce los datos de la asignatura a agregar");

        Materia mat = new Materia();

        System.out.print("Nombre de la materia: ");
        mat.setNombre(sc.next());
        System.out.print("Identificador: ");
        mat.setId(sc.next());
        System.out.print("Cupo de la materia: ");
        mat.setCupo(sc.nextInt());

        if(materiasArray.existe(mat)){
            System.out.println("Esta materia ya esta en el sistema.");
            return false;
        }
        
        if(materiasArray.inscribir(mat)){
            return true;
        }
        return false;
    }

    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        Asignaturas materiasArray = new Asignaturas();

        for(;;){
            System.out.println("SISTEMA DE INSCRIPCIÓN");

            System.out.println("¿Que desea hacer?");
            System.out.println("1. Inscripcion.");
            System.out.println("2. Remover.");
            System.out.println("3. Mostrar materias inscritas.");
            System.out.println("4. Agregar materia al sistema.");
            System.out.println("5. Salir.");

            int opcion = sc.nextInt();
            
            switch(opcion){
                case 1:
                    if(inscribir(materiasArray, sc)){
                        System.out.println("Se pudo inscribir con exito.");
                        break;
                    }
                    System.out.println("No se pudo inscribir.");
                    break;
                case 2:
                    if(remover(materiasArray, sc)){
                        System.out.println("Se pudo inscribir con exito.");
                        break;
                    }
                    System.out.println("No se pudo inscribir.");
                    break;
                case 3:   
                    mostrar(sc);
                    break;
                case 4:
                    if(agregarMateria(materiasArray, sc)){
                        System.out.println("Se agreo la materia existosamente");
                        break;
                    }
                    System.out.println("No se pudo agregar la materia.");
                    break;
                case 5:
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("Introduzca una opcion valida.");
                    break;
            }
        }
    }
}