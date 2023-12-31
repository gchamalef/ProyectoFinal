/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import com.mysql.cj.protocol.Resultset;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author pc01
 */
public class Menu {

    private String ruta;
    private int id;
    private String nombre;
    private int productos, marcas, empleados, puestos, clientes, proveedores, compras, ventas, usuarios;
    Conexion cn;

    public Menu(String ruta, int id, String nombre) {
        this.ruta = ruta;
        this.id = id;
        this.nombre = nombre;
    }

    public Menu() {
    }

    ;

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

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

    public int getProductos() {
        return productos;
    }

    public void setProductos(int productos) {
        this.productos = productos;
    }

    public int getMarcas() {
        return marcas;
    }

    public void setMarcas(int marcas) {
        this.marcas = marcas;
    }

    public int getEmpleados() {
        return empleados;
    }

    public void setEmpleados(int empleados) {
        this.empleados = empleados;
    }

    public int getPuestos() {
        return puestos;
    }

    public void setPuestos(int puestos) {
        this.puestos = puestos;
    }

    public int getClientes() {
        return clientes;
    }

    public void setClientes(int clientes) {
        this.clientes = clientes;
    }

    public int getProveedores() {
        return proveedores;
    }

    public void setProveedores(int proveedores) {
        this.proveedores = proveedores;
    }

    public int getCompras() {
        return compras;
    }

    public void setCompras(int compras) {
        this.compras = compras;
    }

    public int getVentas() {
        return ventas;
    }

    public void setVentas(int ventas) {
        this.ventas = ventas;
    }

    public int getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(int usuarios) {
        this.usuarios = usuarios;
    }

    public void LeerUrl(int i) {
        cn = new Conexion();
        cn.abrir_conexion();
        try {
            ResultSet consulta;
            PreparedStatement parametro;
            String query = "Select id,nombre,url from menu where id=" + i + ";";
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            consulta = parametro.executeQuery();
            while (consulta.next()) {
                setRuta(consulta.getString("url"));
                setNombre(consulta.getString("nombre"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            cn.cerrar_conexion();
        }
    }

    public void Pureba(String aux) {
        cn = new Conexion();
        cn.abrir_conexion();

        try {
            ResultSet consulta;
            PreparedStatement parametro;
            String query = "SELECT productos,marcas,empleados,puestos,clientes,proveedores,ventas,compras,usuarios FROM usuarios where usuario='" + aux + "';";
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            consulta = parametro.executeQuery();
            while (consulta.next()) {
                setProductos(consulta.getInt("productos"));
                setMarcas(consulta.getInt("marcas"));
                setEmpleados(consulta.getInt("empleados"));
                setPuestos(consulta.getInt("puestos"));
                setClientes(consulta.getInt("clientes"));
                setProveedores(consulta.getInt("proveedores"));
                setVentas(consulta.getInt("ventas"));
                setCompras(consulta.getInt("ventas"));
                setUsuarios(consulta.getInt("usuarios"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            cn.cerrar_conexion();
        }

    }
}
