package org.feria.manejoArchivos;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.feria.feria.Casetas;

import java.io.*;

import static java.lang.Integer.parseInt;

/**
 *
 * Clase que implementa varios métodos estáticos para serializar y deserializar objetos desde ficheros JSON
 * @author Ana Vázquez
 * @version 1.0. 09/10/24
 */
public class ManejoJSON {

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
    public static void unmarshallingCasetasJSONFichero(File ficheroJSON) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            Casetas usuarioFromXml = objectMapper.readValue(ficheroJSON, Casetas.class);

            System.out.println("Objeto Casetas después de deserializar:");
            System.out.println(usuarioFromXml.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para buscar una casetaFeria concreta por ID en un archivo JSON
     * @param ficheroJSON fichero JSON
     * @param id ID de la casetaFeria a buscar
     * @return un String con la casetaFeria en formato JSON, devuelve null si no encuentra el ID
     */
    public static String buscarCasetaIDJSON(File ficheroJSON, int id) {
        StringBuilder casetaJSON = new StringBuilder();
        boolean idEncontrado = false; // controla que se ha encontrado el ID

        try (BufferedReader br = new BufferedReader(new FileReader(ficheroJSON))) {
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
