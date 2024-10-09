package org.feria;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD) // Acceder por campo casi siempre será asi
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

    public CasetaFeria(int id, String nombre, String titular, int aforo, String tipoCaseta) {
        this.id = id;
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
    // Función para alinear los textos
    String formatearLinea(String etiqueta, String valor) {
        int espaciosRestantes = 30 - etiqueta.length() - valor.length();
        return "      | " + etiqueta + ": " + valor + " ".repeat(espaciosRestantes) + "|";
    }

}
