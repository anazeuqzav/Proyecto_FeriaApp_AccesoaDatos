package org.feria.feria;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

/**
 * Clase que contiene un arrayList de objetos casetasFeria
 *
 * @author Ana Vázquez
 */
@XmlRootElement(name = "casetas")
@XmlAccessorType(XmlAccessType.FIELD)
public class Casetas {

    // Atributos
    @XmlElement(name = "caseta")
    private ArrayList <CasetaFeria> listaCasetas = new ArrayList<>();

    // Constructor
    public Casetas() {
    }
    public Casetas(ArrayList<CasetaFeria> listaCasetas) {
        this.listaCasetas = listaCasetas;
    }

    // Get y set
    public ArrayList<CasetaFeria> getListaCasetas() {
        return listaCasetas;
    }

    public void setListaCasetas(ArrayList<CasetaFeria> listaCasetas) {
        this.listaCasetas = listaCasetas;
    }

    // Métodos

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

    @Override
    public String toString() {
        StringBuilder datosCaseta = new StringBuilder();
        for(CasetaFeria caseta : listaCasetas){
            datosCaseta.append(caseta.toString() + "\n");
        }
        return datosCaseta.toString();
    }
}
