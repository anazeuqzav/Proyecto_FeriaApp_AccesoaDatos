package org.feria;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

@XmlRootElement(name = "casetas")
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

    @Override
    public String toString() {
        StringBuilder datosCaseta = new StringBuilder();
        for(CasetaFeria caseta : listaCasetas){
            datosCaseta.append(caseta.toString() + "\n");
        }
        return datosCaseta.toString();
    }


}
