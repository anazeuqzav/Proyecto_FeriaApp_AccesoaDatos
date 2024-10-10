package org.feria.manejoArchivos;

import org.feria.feria.Casetas;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

/**
 *
 * Clase que implementa varios métodos estáticos para serializar y deserializar objetos desde ficheros XML
 * @author Ana Vázquez
 * @version 1.0. 09/10/24
 */
public class ManejoXML {

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
    public static void unmarshallingCasetasXMLFichero(File ficheroXML) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Casetas.class);

            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            Casetas casetasFromXml = (Casetas) unmarshaller.unmarshal(ficheroXML);

            System.out.println("Objeto Casetas después de deserializar:");
            System.out.println(casetasFromXml.toString());

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método que busca por ID un objeto casetaFeria en un fichero XML
     * @param ficheroXML ficheroXML donde se encuentran los objetos casetasFerias
     * @param id identificador de la caseta a buscar
     * @return String con la caseta en formato XML, si no se encuentra devuelve null
     */
    public static String buscarCasetaIDXML(File ficheroXML, int id) {
        StringBuilder casetaXML = new StringBuilder();
        boolean idEncontrado = false; // controla que se ha encontrado el ID

        try (BufferedReader br = new BufferedReader(new FileReader(ficheroXML))) {
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
}
