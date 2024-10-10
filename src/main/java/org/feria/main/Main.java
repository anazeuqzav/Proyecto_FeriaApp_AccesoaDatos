package org.feria.main;

import org.feria.feria.Casetas;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

import static org.feria.feria.Casetas.recuperaCasetas;
import static org.feria.manejoArchivos.ManejoJSON.*;
import static org.feria.manejoArchivos.ManejoXML.*;

/**
 * Clase principal con un menú y diferentes opciones para trabajar con ficheros XML y JSON
 * @author Ana Vazquez
 * @version 10/10/24
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        int id;

        File archivoCasetas = new File("casetas.txt");
        Casetas casetas = new Casetas(recuperaCasetas(archivoCasetas));

        File archivoXML = new File("casetas.xml"); // Para controlar que se ha creado el archivo xml
        File archivoJSON = new File ("casetas.json"); // Para controlar que se ha creado el archivo json

        do {
            try {
                System.out.println("""
                         _____________________________________________
                        |       MENÚ CASETAS FERIA DE JAÉN            |
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

                switch (opcion) {
                    case 1:
                        marshalingCasetasXML(casetas);
                        break;

                    case 2:
                        if (archivoXML.exists()) {
                            unmarshallingCasetasXMLFichero(archivoXML);
                        } else {
                            System.out.println("No se puede deserializar por que no ha hecho antes " +
                                    "marshalling a XML");
                        }
                        break;

                    case 3:

                        if (archivoXML.exists()) {
                            System.out.println("Introduce el ID que quieras buscar: ");
                            id = sc.nextInt();

                            String casetaXML = buscarCasetaIDXML(archivoXML, id);

                            if (casetaXML == null) {
                                System.out.println("No se ha encontrado la caseta con el ID " + id);
                            } else {
                                unmarshallingCasetaXMLString(casetaXML);
                            }

                        } else {
                            System.out.println("No se puede recuperar una caseta por que no se ha hecho antes " +
                                    "marshalling a XML");
                        }
                        break;

                    case 4:
                        marshalingCasetasJSON(casetas);
                        break;

                    case 5:
                        if (archivoJSON.exists()) {
                            unmarshallingCasetasJSONFichero(archivoJSON);
                        } else {
                            System.out.println("No se puede deserializar porque  no ha hecho antes " +
                                    "marshalling a JSON");
                        }
                        break;

                    case 6:
                        if (archivoJSON.exists()) {
                            System.out.println("Introduce el ID que quieras buscar: ");
                            id = sc.nextInt();

                            String casetaJSON = buscarCasetaIDJSON(archivoJSON, id);

                            if (casetaJSON == null) {
                                System.out.println("No se ha encontrado la caseta con el ID " + id);
                            } else {
                                unmarshallingCasetaJSONString(casetaJSON);
                            }

                        } else {
                            System.out.println("No se puede recuperar una caseta por que no se ha hecho antes" +
                                    "marshalling a XML");
                        }
                        break;

                    case 7:
                        System.out.println("Saliendo del programa...");
                        sc.close();
                        break;

                    default:
                        System.out.println("Introduce un número de 1 al 7 para elegir una opción");
                }

            } catch (InputMismatchException e) {
                System.out.println("Introduce un número del 1 al 7 para elegir una opción");
                sc.next();
            }

        } while (opcion != 7);
    }
}