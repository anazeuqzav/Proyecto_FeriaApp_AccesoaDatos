package org.feria.feria;

import javax.xml.bind.annotation.*;

/**
 * Clase que representa una caseta de feria con sus propiedades
 * @author Ana Vázquez
 * @version 09/10/24
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "id", "nombre", "titular", "aforo", "tipoCaseta" })
public class CasetaFeria {

    // Atributos
    @XmlAttribute(name = "id")
    private int id;

    @XmlElement(name = "nombre")
    private String nombre;

    @XmlElement(name = "titular")
    private String titular;

    @XmlElement(name = "aforo")
    private int aforo;

    @XmlElement(name = "tipo")
    private String tipoCaseta;

    @XmlTransient
    private static int contador = 1;

    // Constructores
    public CasetaFeria() {
        this.id = contador++;
    }

    public CasetaFeria(String nombre, String titular, int aforo, String tipoCaseta) {
        this.id = contador++;
        this.nombre = nombre;
        this.titular = titular;
        this.aforo = aforo;
        this.tipoCaseta = tipoCaseta;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public int getAforo() {
        return aforo;
    }

    public void setAforo(int aforo) {
        this.aforo = aforo;
    }

    public String getTipoCaseta() {
        return tipoCaseta;
    }

    public void setTipoCaseta(String tipoCaseta) {
        this.tipoCaseta = tipoCaseta;
    }

    @Override
    public String toString() {

        return "          ^^^^^^^^^^^^^^^^^^^^^^^^^^^           \n" +
                "        /                             \\       \n" +
                "       /       Caseta de Feria         \\      \n" +
                "      /_________________________________\\     \n" +
                        formatearLinea("ID", String.valueOf(this.id)) + "\n" +
                        formatearLinea("Nombre", this.nombre) + "\n" +
                        formatearLinea("Titular", this.titular) + "\n" +
                        formatearLinea("Aforo", String.valueOf(this.aforo)) + "\n" +
                        formatearLinea("Tipo", this.tipoCaseta) + "\n" +
                "      |_________________________________|";
    }

    /**
     * Metodo para formatear las líneas de toString
     * @param atributo El nombre de cada atributo
     * @param valor El campo de cada atributo
     * @return Una cadena con la línea formateada según lo que ocupa el atributo y el valor y los espacios restantes
     * que le quedan a la caseta
     */
    private String formatearLinea(String atributo, String valor) {
        int espaciosRestantes = 30 - atributo.length() - valor.length();
        return "      | " + atributo + ": " + valor + " ".repeat(espaciosRestantes) + "|";
    }
}
