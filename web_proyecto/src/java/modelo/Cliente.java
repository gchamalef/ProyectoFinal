/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author pc01
 */
public class Cliente {

    private Conexion cn;
    private int id, genero;
    private String nombres, apellidos, nit, telefono, correo_electronico, fechaingreso;

    public Cliente() {
    }

    public Cliente(int id, int genero, String nombres, String apellidos, String nit, String telefono, String correo_electronico, String fechaingreso) {
        this.id = id;
        this.genero = genero;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.nit = nit;
        this.telefono = telefono;
        this.correo_electronico = correo_electronico;
        this.fechaingreso = fechaingreso;
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

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo_electronico() {
        return correo_electronico;
    }

    public void setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }

    public String getFechaingreso() {
        return fechaingreso;
    }

    public void setFechaingreso(String fechaingreso) {
        this.fechaingreso = fechaingreso;
    }

    public DefaultTableModel leer() {
        DefaultTableModel tabla = new DefaultTableModel();
        try {
            cn = new Conexion();
            cn.abrir_conexion();
            String query = "SELECT idCliente as id, nombres, apellidos, NIT, case when genero = 0 then 'Hombre' when genero = 1 then 'Mujer' end as genero, telefono, correo_electronico, fechaingreso FROM clientes;";
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            String encabezado[] = {"id", "nombres", "apellidos", "NIT", "genero", "telefono", "correo_electronico", "fechaingreso"};
            tabla.setColumnIdentifiers(encabezado);
            String datos[] = new String[8];
            while (consulta.next()) {
                datos[0] = consulta.getString("id");
                datos[1] = consulta.getString("nombres");
                datos[2] = consulta.getString("apellidos");
                datos[3] = consulta.getString("NIT");
                datos[4] = consulta.getString("genero");
                datos[5] = consulta.getString("telefono");
                datos[6] = consulta.getString("correo_electronico");
                datos[7] = consulta.getString("fechaingreso");
                tabla.addRow(datos);

            }

            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return tabla;
    }

    public int agregar() {
        int retorno = 0;
        try {
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "INSERT INTO clientes (nombres,apellidos,NIT,genero,telefono,correo_electronico,fechaingreso) VALUES(?,?,?,?,?,?,?);";
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setString(1, this.getNombres());
            parametro.setString(2, this.getApellidos());
            parametro.setString(3, this.getNit());
            parametro.setInt(4, this.getGenero());
            parametro.setString(5, this.getTelefono());
            parametro.setString(6, this.getCorreo_electronico());
            parametro.setString(7, this.getFechaingreso());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return retorno;
    }

    public int modificar() {
        int retorno = 0;
        try {
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "update clientes set  nombres=?,apellidos=?,NIT=?,genero=?,telefono=?,correo_electronico=?, fechaingreso=? where idCliente = ? ;";
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setString(1, this.getNombres());
            parametro.setString(2, this.getApellidos());
            parametro.setString(3, this.getNit());
            parametro.setInt(4, this.getGenero());
            parametro.setString(5, this.getTelefono());
            parametro.setString(6, this.getCorreo_electronico());
            parametro.setString(7, this.getFechaingreso());
            parametro.setInt(8, this.getId());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return retorno;
    }

    public int eliminar() {
        int retorno = 0;
        try {
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "DELETE from clientes where idCliente=?;";
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setInt(1, this.getId());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return retorno;
    }

    public int Buscar_cliente(String nit) throws SQLException {
        int retorno = 1;
        PreparedStatement parametro;
        cn = new Conexion();
        String query = "SELECT nombres,NIT FROM clientes where nit = ?;";
        cn.abrir_conexion();
        parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
        parametro.setString(1, nit);
        parametro.executeUpdate();
        ResultSet consulta = parametro.executeQuery(query);
        consulta = cn.conexionBD.createStatement().executeQuery(query);
        while (consulta.next()) {
            retorno = retorno + 1;
            setNombres(consulta.getString("nombres"));
            setNit(consulta.getString("NIT"));

        }
        return retorno;
    }

    private int aux(String nit) throws SQLException {
        int retorno = 0;
        cn = new Conexion();

        if (retorno == 1) {
            cn.cerrar_conexion();
            return 1;
        } else {
            cn.cerrar_conexion();
            return 1;
        }
    }

}
