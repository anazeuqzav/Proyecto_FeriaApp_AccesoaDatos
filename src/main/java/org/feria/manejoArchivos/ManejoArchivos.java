package org.feria.manejoArchivos;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.feria.feria.CasetaFeria;
import org.feria.feria.Casetas;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

/**
 *
 * Clase que implementa varios métodos estáticos para serializar y deserializar objetos
 * @author Ana Vázquez
 * @version 1.0. 09/10/24
 */
public class ManejoArchivos {

    /**
     * Método que recupera objetos Casetas de un archivo txt
     * @param archivo fichero donde se encuentras las casetas
     * @return devuelve un ArrayList de tipo CasetaFeria donde se encuentran todos los objetos recuperados del
     * fichero txt
     */
    public static ArrayList<CasetaFeria> recuperaCasetas(File archivo) {

        ArrayList<CasetaFeria> listaCasetas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Guarda cada línea en un array de Strings separando por propiedad del objeto
                String[] atributos = linea.split(" - ");

                // Asigna cada atributo a una variable
                String nombre = atributos[0];
                String titular = atributos[1];
                int aforo = parseInt(atributos[2]);
                String tipoCaseta = atributos[3];

                // Crea una casetaFeria con los atributos y lo añade a la lista de casetas
                CasetaFeria caseta = new CasetaFeria(nombre, titular, aforo, tipoCaseta);
                listaCasetas.add(caseta);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaCasetas;
    }

    /**
     * Método que serializa los objetos Casetas de a un archivo XML
     * @param casetas objeto Casetas que contiene un ArrayList de casetasFeria
     */
    public static void marshalingCasetasXML(Casetas casetas) {
        String ficheroXML = "casetas.xml";

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Casetas.class);

            Marshaller marshaller = jaxbContext.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            marshaller.marshal(casetas, new File(ficheroXML));

            System.out.println("Objeto Casetas serializado a XML en: " + ficheroXML);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método que deserializa los objetos Casetas contenidos en un fichero XML
     * @param ficheroXML String con el nombre del fichero que contiene los objetos Casetas
     */
    public static void unmarshallingCasetasXMLFichero(String ficheroXML) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Casetas.class);

            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            Casetas casetasFromXml = (Casetas) unmarshaller.unmarshal(new File(ficheroXML));

            System.out.println("Objeto Casetas después de deserializar:");
            System.out.println(casetasFromXml.toString());

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método que busca por ID un objeto casetaFeria en un fichero XML
     * @param archivo ficheroXML donde se encuentran los objetos casetasFerias
     * @param id identificador de la caseta a buscar
     * @return String con la caseta en formato XML, si no se encuentra devuelve null
     */
    public static String buscarCasetaIDXML(String archivo, int id) {
        StringBuilder casetaXML = new StringBuilder();
        boolean idEncontrado = false; // controla que se ha encontrado el ID

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                // Si encuentra el id introducido
                if (linea.contains("id=" + "\"" + id + "\"")) {
                    idEncontrado = true;
                    // Agrega la etiqueta de apertura del elemento raiz
                    casetaXML.append("<casetas>\n");
                    casetaXML.append(linea).append("\n");
                    // Guarda las líneas correspondientes a los datos de la casetaFeria encontrada
                    for (int i = 0; i < 5; i++) {
                        linea = br.readLine();
                        casetaXML.append(linea).append("\n");
                    }
                }
            }
            // Agrega la etiqueta de cierre del elemento raiz
            casetaXML.append("</casetas>");
            // Si no encuentra el ID devuelve null
            if (!idEncontrado) {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return casetaXML.toString();
    }

    /**
     * Método que deserializa un objeto Caseta desde un String. Se utiliza cuando se quiere deserializar solo
     * una caseta encontrada a través del ID en el archivo XML.
     * @param casetaXML String con la caseta en formato XML
     */
    public static void unmarshallingCasetaXMLString(String casetaXML) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Casetas.class);

            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            StringReader reader = new StringReader(casetaXML);

            Casetas casetasFromXml = (Casetas) unmarshaller.unmarshal(reader);

            System.out.println("Objeto Casetas después de deserializar:");
            System.out.println(casetasFromXml.toString());

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método que deseariliza el objeto Casetas a un fichero JSON
     * @param casetas objeto Casetas que contiene un arrayList de casetasFeria
     */
    public static void marshalingCasetasJSON(Casetas casetas) {
        String jsonFilePath = "casetas.json";

        try {
            ObjectMapper objectMapper = new ObjectMapper();

            objectMapper.writerWithDefaultPrettyPrinter();

            System.out.println("Objeto Casetas serializado a JSON en " + jsonFilePath);

            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(jsonFilePath), casetas);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método que deserializa el objeto Casetas contenidos en un fichero JSON
     * @param ficheroJSON nombre o ruta del fichero JSON que contiene el objeto Casetas
     */
    public static void unmarshallingCasetasJSONFichero(String ficheroJSON) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            Casetas usuarioFromXml = objectMapper.readValue(new File(ficheroJSON), Casetas.class);

            System.out.println("Objeto Usuario después de deserializar:");
            System.out.println(usuarioFromXml.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para buscar una casetaFeria concreta por ID en un archivo JSON
     * @param archivo fichero JSON
     * @param id ID de la casetaFeria a buscar
     * @return un String con la casetaFeria en formato JSON, devuelve null si no encuentra el ID
     */
    public static String buscarCasetaIDJSON(String archivo, int id) {
        StringBuilder casetaJSON = new StringBuilder();
        boolean idEncontrado = false; // controla que se ha encontrado el ID

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;

            while ((linea = br.readLine()) != null) {

                if (linea.contains("\"id\" : " + id + ",")) {
                    idEncontrado = true;
                    // Añade la etiqueta raiz tipo JSON
                    casetaJSON.append("{\n").append( "  \"listaCasetas\" : [ {\n");
                    casetaJSON.append(linea).append("\n");
                    // Guarda las líneas correspondientes a los datos de la caseta
                    for (int i = 0; i < 4; i++) {
                        linea = br.readLine();
                        casetaJSON.append(linea).append("\n");
                    }
                }
            }
            // Agrega la etiqueta de cierre del elemento raiz JSON
            casetaJSON.append("} ]\n").append("}");

            if (!idEncontrado) {
                return null;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return casetaJSON.toString();
    }

    /**
     * Método que deserializa una casetaFeria desde un String con el formato JSON. Se utiliza para deserializar
     * solo un objeto al buscarlo por ID.
     * @param casetaJSON String con formato JSON
     */
    public static void unmarshallingCasetaJSONString(String casetaJSON){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Casetas usuarioFromXml = objectMapper.readValue(casetaJSON, Casetas.class);
            System.out.println("Objeto Usuario después de deserializar:");
            System.out.println(usuarioFromXml.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
