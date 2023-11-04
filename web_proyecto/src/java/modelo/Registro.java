/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author pc01
 */
public class Registro {

    private String usuario;
    private String password;
    private String email;
    private Conexion cn;
    private int id;

    public Registro() {
    }

    public Registro(String usuario, String password, String email, int id) {
        this.usuario = usuario;
        this.password = password;
        this.email = email;
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int registrar() {
        int retorno = 0;
        cn = new Conexion();
        cn.abrir_conexion();
        try {
            PreparedStatement parametro;

            String query = ("INSERT INTO usuarios (usuario,contra,correo, productos, marcas, empleados, puestos, clientes, proveedores, compras, ventas, usuarios) VALUES ( ? , ?, ?, 0, 0 , 0 , 0 , 0, 0, 0, 1, 0);");
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getUsuario());
            parametro.setString(2, getEmail());
            parametro.setString(3, getPassword());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            retorno = 0;
        }
        return retorno;
    }
}