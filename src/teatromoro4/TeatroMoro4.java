
package teatromoro4;


import java.util.*;




public class TeatroMoro4 {

    
    // Variables estáticas
    // defino colores para optimizar visualizacion de texto del programa   
    public static String rojo       ="\033[31m"; 
    public static String verde      ="\033[32m"; 
    public static String azul       ="\033[34m"; 
    public static String celeste    ="\033[36m"; 
    public static String reset      ="\u001B[0m";   
    
    public static String nombreTeatro = "TEATRO MORO";
    
    
    // Crea una nueva ArrayList para almacenar cadenas
    static List<String>     ubicacion           =new ArrayList<>();
    static List<Integer>    costoUnitario       =new ArrayList<>();
    static List<Double>     descuentoAplicado   =new ArrayList<>();
    static List<Double>     costoFinal          =new ArrayList<>();
    
    
    
    public static void main(String[] args) {
      
        
         bienvenida(); // metodo que imprime una bienvenida en pantalla
        
        // Variables de input de usuario desde teclado
        Scanner teclado = new Scanner(System.in);
        
        // Definicion de variables locales
     boolean encontrado = false; 
     int opcion;
             
     while(encontrado==false){
        
         
         boolean continua;
        do{ 
        try{
            continua = false;
            System.out.println(celeste+"[MENU]"+reset);
            System.out.println("Presiona 1 si quieres "+verde+"[Comprar Entrada]"+reset);
            System.out.println("Presiona 2 si quieres "+verde+"[Resumen ventas]"+reset);
            System.out.println("Presiona 3 si quieres "+verde+"[Boleta]"+reset);
            System.out.println("Presiona 4 si quieres "+verde+"[Ingresos totales]"+reset);
            System.out.println("Presiona 5 si quieres "+verde+"[Salir]"+reset);
            System.out.print("--->Ingrese opcion: ");
        
        opcion = teclado.nextInt();
             

        
        switch(opcion){
            case 1 -> fxComprarEntrada();
            case 2 -> resumenVentas();
            case 3 -> generadorBoleta();
            case 4 -> ingresosTotales();
            case 5 -> {
                encontrado = true; // para salir del bucle while
                System.out.println(celeste+"Gracias por su compra!"+reset);
                System.out.println("");
                }
            // control de errores
            default -> System.out.println(rojo+"[ERROR] Ingrese una opcion valida!"+reset); 
        } // fin switch
         
        // control de errores por error de ingreso de datos por parte del usuario
                } catch(InputMismatchException ex){
           System.out.println(rojo+"[ERROR] Ingrese un numero entero"+reset);
           teclado.next();
           continua = true;
         }
        }while(continua);
      
        
     }
     
     
        
    }
    
    
    public static void generadorBoleta(){
        
        if (ubicacion.isEmpty()){
            System.out.println(rojo+"[ERROR] Aun no se han comprado entradas!"+reset);
        }else{
        
        for(int i=0; i < ubicacion.size(); i++){
        
        System.out.println("""
                           -----------------------------------------
                           ************   TEATRO MORO   ************
                           -----------------------------------------""");
        System.out.println("*Ubicacion: " + ubicacion.get(i) );
        System.out.println("*Costo Base: $" + costoUnitario.get(i) );
        
        if(descuentoAplicado.get(i) == 0.1){
                System.out.println("*Descuento Aplicado: 10%");
            } else if(descuentoAplicado.get(i) == 0.15){
                System.out.println("*Descuento Aplicado: 15%");
            } else {
                System.out.println("*Descuento Aplicado: 0%");
            }
        
        System.out.println("*Costo Final: $" + costoFinal.get(i) );
        
        System.out.println("""
                           -----------------------------------------
                             Gracias por su visita al Teatro Moro
                           -----------------------------------------
                           
                           
                           """);
        }
        }
        
        
    }
    
    
    public static void bienvenida(){
        
    // Despliegue menu principal
        System.out.println(rojo+"*******************************");
        System.out.println(rojo+"********* "+nombreTeatro+" *********");              
        System.out.println(rojo+"*******************************"); 
        System.out.println(rojo+"------ TICKETERA VIRTUAL ------"+reset); 
        System.out.println("");
               
    }
    
    
    public static void fxComprarEntrada(){
        // Variables de input de usuario desde teclado
     Scanner teclado2 = new Scanner(System.in);
     
     // Variables locales
     String opcionZona, edad;
     boolean encontra2 = false, bucleEdad = false;
        
     
     
     
      do{
        
        System.out.print("Ingresa la ubicacion deseada "+verde+"(VIP, Platea, Balcon)"+reset+": ");
                       
        opcionZona = teclado2.nextLine();
              
         switch (opcionZona) {
             case "VIP" -> {
                 ubicacion.add("VIP");
                 costoUnitario.add(30000);
                 encontra2 = true; 
                 Entrada.entradaA++;  
                }
             case "Platea" -> {   
                 ubicacion.add("Platea");
                 costoUnitario.add(20000);
                 encontra2 = true;
                 Entrada.entradaB++;
                }  
             case "Balcon" -> {  
                 ubicacion.add("Balcon");
                 costoUnitario.add(15000);
                 encontra2 = true;
                 Entrada.entradaC++;
                 }
             default -> System.out.println(rojo+"[ERROR] Entrada no valida. Reintente..."+reset);
             } // fin switch
     }while(encontra2 == false);
        
     do{
        System.out.println("Presione "+rojo+"E"+reset+" si es [E]studiante o "+rojo+"T"+reset+" si es [T]ercera edad");
         System.out.print("de lo contrario, presione "+rojo+"N"+reset+": ");
        edad = teclado2.nextLine().toUpperCase();
        
        // Aplica descuentos
        switch(edad){
            case "E" -> {
                descuentoAplicado.add(0.10);
                bucleEdad = true;
                    } 
            case "T" -> {
                descuentoAplicado.add(0.15);
                bucleEdad = true;
                    }
            case "N" -> {
                descuentoAplicado.add(0.0);
                bucleEdad = true;
                    }
            default -> System.out.println(rojo+"[ERROR] Ingrese lo solicitado..."+reset);
            
            }// fin switch
        }while(bucleEdad == false);
      
     
     // Calculo para definir el costo final, y lo agrega a la Lista definida para ello
     int indice = ubicacion.size();
     int costo = costoUnitario.get(indice-1);
     double dcto = descuentoAplicado.get(indice-1);
     double costoF = costo - (costo * dcto);
     costoFinal.add(costoF);
     
    }
     
    public static void resumenVentas(){
        
        // Si la lista esta vacia, controla posible error
        // Se podria usar cualquiera de las listas, lo que importa es el tamaño e indice
        // ya que en teoria, son todas del mismo tamaño
        if (ubicacion.isEmpty()){
            System.out.println(rojo+"[ERROR] Aun no se han comprado entradas!"+reset);
        }else{
        
            System.out.println(verde+"** [RESUMEN VENTAS] **"+reset);
            
            for(int i=0; i < ubicacion.size(); i++){
        
                System.out.println("Ubicacion: " + ubicacion.get(i) );
                System.out.println("Costo Base: " + costoUnitario.get(i) );
                System.out.println("Descuento Aplicado: " + descuentoAplicado.get(i) );
                System.out.println("Costo Final: " + costoFinal.get(i) );
                System.out.println("");
                }
        } // fin else
                
    } // Fin resumenVentas()
        


    public static void ingresosTotales(){
        
        Entrada.entradasVendidas();
        
        double costo = 0.0;
        // Si la lista no esta vacia, usa un FOR para sumar el ingreso total
        if(!ubicacion.isEmpty()){
        for(int i=0; i < ubicacion.size(); i++){
            costo += costoFinal.get(i);
        }
        System.out.println(rojo + ">> Ingresos Totales acumulados: " + azul + costo + reset);
        }
    }
    

    
} // Fin clase TeatroMoro4



class Entrada {
    
    // Variables de instancia (global)
    static int entradaA = 0;
    static int entradaB = 0;
    static int entradaC = 0;
    
     
    public static void entradasVendidas(){
        boolean hayEntrada = false;
        
        if(entradaA != 0){
            System.out.println(TeatroMoro4.rojo+"[VIP]:      " + TeatroMoro4.reset + entradaA);
            hayEntrada = true;
        } 
        
        if (entradaB != 0){
            System.out.println(TeatroMoro4.rojo+"[Platea]:   " + TeatroMoro4.reset + entradaB);
            hayEntrada = true;
        } 
        
        if (entradaC != 0){
            System.out.println(TeatroMoro4.rojo+"[Balcon]:   " + TeatroMoro4.reset + entradaC);
            hayEntrada = true;
        }
        
        if(hayEntrada == false){
            System.out.println(TeatroMoro4.rojo+"[ERROR] Aun no compra entradas!"+TeatroMoro4.reset);
        }
        
        int cantidadEntradasVendidas = entradaA + entradaB + entradaC;
        
        if(cantidadEntradasVendidas>0){
            System.out.println(TeatroMoro4.azul+"[TOTAL]:    " + cantidadEntradasVendidas +TeatroMoro4.reset);
        }
        
    } // Fin metodo entradasVendidas()
    
} // Fin clase Entrada