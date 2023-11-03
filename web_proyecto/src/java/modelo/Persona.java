/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author pc01
 */
abstract class Persona {
    private int id, genero;
    private String nombres, apellidos, telefono, fecha_nacimiento;
    public Persona(){}

    public Persona(int id, int genero, String nombres, String apellidos, String telefono, String fecha_nacimiento) {
        this.id = id;
        this.genero = genero;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGenero() {
        return genero;
    }

    public void setGenero(int genero) {
        this.genero = genero;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }
    
    public int agregar() { return 0; }
    public int modificar() { return 0; }
    public int eliminar() { return 0;}
    public DefaultTableModel leer() {
        DefaultTableModel tabla = new DefaultTableModel();
        return tabla;
    }
}
