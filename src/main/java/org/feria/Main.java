package org.feria;

import java.io.File;
import java.util.Scanner;

import static org.feria.ManejoArchivos.*;


public class Main {
    public static void main(String[] args) {

        File archivoCasetas = new File("casetas.txt");
        Scanner sc = new Scanner(System.in);
        int opcion;
        int id;

        boolean marshallingXML = false;
        boolean marshalingJSON = false;

        Casetas casetas = new Casetas(recuperaCasetas(archivoCasetas));

        System.out.println(casetas);

        //Menú
            System.out.println("""
                 _____________________________________________
                |            CASETAS FERIA DE JAÉN            |
                |---------------------------------------------|
                |  Elige una opción:                          |
                |    1. Marshalling casetas a XML             |
                |    2. Unmarshalling casetas de XML          |
                |    3. Mostrar la caseta numero X            |
                |    4. Marshalling casetas a JSON            |
                |    5. Unmarshalling casetas de JSON         |
                |    6. Mostrar la caseta número X desde JSON |
                |    7. Salir                                 |
                 _____________________________________________ """);
            opcion = sc.nextInt();

            switch (opcion){
                case 1:
                    marshalingCasetasXML(casetas);
                    marshallingXML = true;
                    break;
                case 2:
                    if(marshallingXML){
                        unmarshallingCasetasXML("casetas.xml");
                    } else {
                        System.out.println("No se puede realizar unmarshalling a XML porqué no ha hecho antes " +
                                "marshalling a XML");
                    }
                    break;
                case 3:
                    System.out.println("Introduce el ID que quieras buscar: ");
                    id = sc.nextInt();
                    //buscarCasetaIDXML("casetas.xml", id);


                    break;
                case 4:
                    marshalingCasetasJSON(casetas);
                    marshalingJSON = true;
                    break;
                case 5:
                    if(marshalingJSON){
                        unmarshallingCasetasJson("casetas.json");
                    } else {
                        System.out.println("No se puede realizar unmarshalling a JSON porqué no ha hecho antes " +
                                "marshalling a JSON");
                    }
                    break;
                case 6:
                    break;
                case 7:
                    System.out.println("Saliendo del programa...");
                    sc.close();
                    return;
                default:
                    System.out.println("Introduce un número de 1 al 6 para elegir una opción");
            }


    }
}