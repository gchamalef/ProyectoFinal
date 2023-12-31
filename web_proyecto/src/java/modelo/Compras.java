/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 *
 * @author pc01
 */
public class Compras {

    private Conexion cn;
    private int idcompra, no_orden, idproveedor;
    private String fecha_orden, fecha_ingreso;

    public Compras() {
    }

    public Compras(int idcompra, int no_orden, int idproveedor, String fecha_orden, String fecha_ingreso) {
        this.idcompra = idcompra;
        this.no_orden = no_orden;
        this.idproveedor = idproveedor;
        this.fecha_orden = fecha_orden;
        this.fecha_ingreso = fecha_ingreso;
    }

    public int getIdcompra() {
        return idcompra;
    }

    public void setIdcompra(int idcompra) {
        this.idcompra = idcompra;
    }

    public int getNo_orden() {
        return no_orden;
    }

    public void setNo_orden(int no_orden) {
        this.no_orden = no_orden;
    }

    public int getIdproveedor() {
        return idproveedor;
    }

    public void setIdproveedor(int idproveedor) {
        this.idproveedor = idproveedor;
    }

    public String getFecha_orden() {
        return fecha_orden;
    }

    public void setFecha_orden(String fecha_orden) {
        this.fecha_orden = fecha_orden;
    }

    public String getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(String fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public int leer() {
        try {
            String query = "Select idcompra,no_orden_compra from compras;";
            cn = new Conexion();
            cn.abrir_conexion();
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            while (consulta.next()) {
                setIdcompra(consulta.getInt("idcompra"));
                setNo_orden(consulta.getInt("no_orden_compra"));
            }
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return 1;
    }

    public int agregar() {
        int retorno = 0;
        try {
            PreparedStatement parametro;

            cn = new Conexion();
            String query = "INSERT INTO compras(no_orden_compra,idproveedor,fecha_orden,fechaingreso)VALUES(?,?,?,?);";
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setInt(1, getNo_orden());
            parametro.setInt(2, getIdproveedor());
            parametro.setString(3, getFecha_orden());
            parametro.setString(4, getFecha_ingreso());

            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            retorno = 0;
        }

        return retorno;
    }

    public int modificar() {
        int retorno = 0;
        try {
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "update compras set no_orden_compra=?, idproveedor=?, fecha_orden=?, fechaingreso=? where idcompra=?;";
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setInt(1, getNo_orden());
            parametro.setInt(2, getIdproveedor());
            parametro.setString(3, getFecha_orden());
            parametro.setString(4, getFecha_ingreso());
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
            String query = "delete from compras where idcompra=?;";
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setInt(1, this.getIdcompra());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return retorno;
    }

    public HashMap ListaP() {
        HashMap<String, String> drop = new HashMap();
        try {
            String query = "Select NIT as nit,proveedor from proveedores";
            cn = new Conexion();
            cn.abrir_conexion();
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            while (consulta.next()) {
                drop.put(consulta.getString("proveedor"), consulta.getString("nit"));
            }
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return drop;
    }

    public HashMap ListaPC() {
        HashMap<String, String> drop = new HashMap();
        try {
            String query = "Select idproveedor as id,proveedor from proveedores;";
            cn = new Conexion();
            cn.abrir_conexion();
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            while (consulta.next()) {
                drop.put(consulta.getString("proveedor"), consulta.getString("id"));
            }
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return drop;
    }
}
