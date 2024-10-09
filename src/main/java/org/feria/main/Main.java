package org.feria.main;

import org.feria.feria.Casetas;

import java.io.File;
import java.util.Scanner;

import static org.feria.manejoArchivos.ManejoArchivos.*;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String opcion;
        int id;

        boolean marshallingXML = false; // control de que se ha realizado el marshalling XML
        boolean marshalingJSON = false; // control de que se ha realizado el marshalling JSON

        File archivoCasetas = new File("casetas.txt");
        Casetas casetas = new Casetas(recuperaCasetas(archivoCasetas));

        do{
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
            opcion = sc.nextLine();

            switch (opcion){
                case "1":
                    marshalingCasetasXML(casetas);
                    marshallingXML = true;
                    break;
                case "2":
                    if(marshallingXML){
                        unmarshallingCasetasXMLFichero("casetas.xml");
                    } else {
                        System.out.println("No se puede realizar unmarshalling a XML por que no ha hecho antes " +
                                "marshalling a XML");
                    }
                    break;
                case "3":
                    if (marshallingXML){
                        System.out.println("Introduce el ID que quieras buscar: ");
                        id = sc.nextInt();
                        String casetaXML = buscarCasetaIDXML("casetas.xml", id);
                        if(casetaXML == null){
                            System.out.println("No se ha encontrado la caseta con el ID " + id );
                        } else{
                            unmarshallingCasetaXMLString(casetaXML);
                        }
                    } else {
                        System.out.println("No se puede recuperar una caseta por que no se ha hecho antes" +
                                "marshalling a XML");
                    }
                    break;
                case "4":
                    marshalingCasetasJSON(casetas);
                    marshalingJSON = true;
                    break;
                case "5":
                    if(marshalingJSON){
                        unmarshallingCasetasJSONFichero("casetas.json");
                    } else {
                        System.out.println("No se puede realizar unmarshalling a JSON porqué no ha hecho antes " +
                                "marshalling a JSON");
                    }
                    break;
                case "6":
                    if (marshalingJSON){
                        System.out.println("Introduce el ID que quieras buscar: ");
                        id = sc.nextInt();
                        String casetaJSON = buscarCasetaIDJSON("casetas.json", id);
                        if(casetaJSON == null){
                            System.out.println("No se ha encontrado la caseta con el ID " + id);
                        } else{
                            unmarshallingCasetaJSONString(casetaJSON);
                        }

                    } else {
                        System.out.println("No se puede recuperar una caseta por que no se ha hecho antes" +
                                "marshalling a XML");
                    }
                    break;
                case "7":
                    System.out.println("Saliendo del programa...");
                    sc.close();
                    return;
                default:
                    System.out.println("Introduce un número de 1 al 6 para elegir una opción");
            }

        } while (!opcion.equals("7"));
    }
}