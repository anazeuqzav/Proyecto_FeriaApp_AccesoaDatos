package org.feria;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringWriter;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class ManejoArchivos {

    public static ArrayList<CasetaFeria> recuperaCasetas(File archivo) {

        ArrayList<CasetaFeria> listaCasetas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {

            String linea;
            while ((linea = br.readLine()) != null) {
                String[] atributos = linea.split(" - "); //guarda cada línea en un array de líneas dividido segun los atributos

                //asigna cada atributo a una variable
                String nombre = atributos[0];
                String titular = atributos[1];
                int aforo = parseInt(atributos[2]);
                String tipoCaseta = atributos[3];

                //crea una caseta con los atributos y lo añade a la lista de casetas
                CasetaFeria caseta = new CasetaFeria(nombre, titular, aforo, tipoCaseta);
                listaCasetas.add(caseta);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaCasetas;
    }

    public static void marshalingCasetasXML(Casetas casetas) {
        String xmlFilePath = "casetas.xml";

        try {
            // Crear un contexto JAXB para la clase Usuario
            JAXBContext jaxbContext = JAXBContext.newInstance(Casetas.class);

            // Crear un marshaller para convertir el objeto a XML
            // este es objeto que nos permite guardar el contenido en un XML
            Marshaller marshaller = jaxbContext.createMarshaller();

            // Formatear el XML para que sea más legible
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // Serializar el objeto Usuario a una cadena XML
            StringWriter sw = new StringWriter(); //Clase que permite almacenar buffer de texto, y sirve para escribir flujo de texto en un String
            marshaller.marshal(casetas, sw); // lo guarda en la cadena StringWriter para luego mostrarlo por pantalla
            String xmlString = sw.toString(); // crea un string con el StringWriter.toString

            // Serializar el objeto Usuario a un fichero
            marshaller.marshal(casetas, new File(xmlFilePath));  //lo guarda en el fichero

            // Mostrar el XML resultante
            System.out.println("Objeto Casetas serializado a XML");
            System.out.println(xmlString);

        } catch (JAXBException e) {
            e.printStackTrace();

        }
    }

    public static void unmarshallingCasetasXML(String xmlFilePath) {

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Casetas.class);

            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            Casetas casetasFromXml = (Casetas) unmarshaller.unmarshal(new File(xmlFilePath));

            // Mostramos el usuario recuperado del XML
            System.out.println("Objeto Casetas después de deserializar:");
            System.out.println(casetasFromXml.toString());

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static void marshalingCasetasJSON(Casetas casetas) {
        String jsonFilePath = "casetas.json";

        try {

            //Creamos un objeto que utiliza la biblioteca Jackson para convertir objetos Java a formatos como JSON
            ObjectMapper objectMapper = new ObjectMapper();

            // Serializar el objeto Usuario a una cadena JSON
            String jsonString = objectMapper.writeValueAsString(casetas); //convierte un objeto a una cadena en formato json y lo muestra

            // Mostrar el JSON resultante
            System.out.println("Objeto Casetas serializado a JSON:");
            System.out.println(jsonString);

            // Serializar el objeto Usuario a un fichero JSON
            objectMapper.writeValue(new File(jsonFilePath), casetas);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void unmarshallingCasetasJson(String jsonFilePath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            // Deserializar el JSON a un objeto Usuario
            Casetas usuarioFromXml = objectMapper.readValue(new File(jsonFilePath), Casetas.class);

            // Mostramos el usuario recuperado del XML
            System.out.println("Objeto Usuario después de deserializar:");
            System.out.println(usuarioFromXml.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String buscarCasetaIDXML(String archivo, int id) {
        StringBuilder datosCaseta = new StringBuilder();
        boolean idEncontrado = false; // controla que se ha encontrado el ID

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;

            // Lee el archivo xml linea por linea
            while ((linea = br.readLine()) != null) {
                // Si encuentra el id introducido
                if (linea.contains("id=" + "\"" + id + "\"")) {
                    idEncontrado = true;
                    // Agrega la etiqueta de apertura del elemento raiz
                    datosCaseta.append("<casetas>\n");
                    datosCaseta.append(linea).append("\n");
                    // Guarda las líneas correspondientes a los datos de la caseta
                    for (int i = 0; i < 5; i++) {
                        linea = br.readLine();
                        datosCaseta.append(linea).append("\n");
                    }
                }
            }
            // Agrega la etiqueta de cierre del elemento raiz
            datosCaseta.append("</casetas>");

            if (!idEncontrado) {
                return "No se encontró ninguna caseta con el ID " + id;
            }

        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return datosCaseta.toString();
    }

    public static void unmarshallingCasetaXMLString(String casetaXML){
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Casetas.class);

            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            // Usamos un StringReader en lugar de un archivo para leer la cadena XML
            StringReader reader = new StringReader(casetaXML);

            Casetas casetasFromXml = (Casetas) unmarshaller.unmarshal(reader);

            // Mostramos el objeto recuperado del XML
            System.out.println("Objeto Casetas después de deserializar:");
            System.out.println(casetasFromXml.toString());

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }


}