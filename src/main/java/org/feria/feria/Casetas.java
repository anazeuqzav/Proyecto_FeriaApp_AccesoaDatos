package org.feria.feria;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
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
    @Override
    public String toString() {
        StringBuilder datosCaseta = new StringBuilder();
        for(CasetaFeria caseta : listaCasetas){
            datosCaseta.append(caseta.toString() + "\n");
        }
        return datosCaseta.toString();
    }
}
