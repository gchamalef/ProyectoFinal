/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author pc01
 */
public class VentasDetalle {

    Conexion cn;
    private int idVenta, cantidad;
    private String producto, marca;
    private double precio_venta, subtotal;

    public VentasDetalle() {
    }

    ;

    public VentasDetalle(int cantidad, String producto, String marca, double precio_venta, double subtotal, int idVenta) {
        this.cantidad = cantidad;
        this.producto = producto;
        this.marca = marca;
        this.precio_venta = precio_venta;
        this.subtotal = subtotal;
        this.idVenta = idVenta;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(double precio_venta) {
        this.precio_venta = precio_venta;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public int agregar() {
        int retorno = 0;
        try {
            PreparedStatement parametro;

            cn = new Conexion();
            String query = "INSERT INTO ventas_detalle(idVenta,producto,cantidad,precio_unitario)VALUES(?,?,?,?);";
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setInt(1, getIdVenta());
            parametro.setString(2, getProducto());
            parametro.setInt(3, getCantidad());
            parametro.setDouble(4, getPrecio_venta());

            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            retorno = 0;
        }

        return retorno;
    }

}
