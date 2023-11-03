package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import javax.swing.table.DefaultTableModel;

public class Proveedor {

    private Conexion cn;
    private int id;
    private String proveedor, nit, direccion, telefono;

    public Proveedor() {
    }

    public Proveedor(int id, String proveedor, String nit, String direccion, String telefono) {
        this.id = id;
        this.proveedor = proveedor;
        this.nit = nit;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public DefaultTableModel leer() {
        DefaultTableModel tabla = new DefaultTableModel();

        try {

            cn = new Conexion();

            cn.abrir_conexion();
            String query = "SELECT idProveedor as id, proveedor, nit, direccion, telefono FROM proveedores";
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);

            String encabezado[] = {"id", "proveedor", "nit", "direccion", "telefono"};

            tabla.setColumnIdentifiers(encabezado);
            String datos[] = new String[5];
            while (consulta.next()) {
                datos[0] = consulta.getString("id");
                datos[1] = consulta.getString("proveedor");
                datos[2] = consulta.getString("nit");
                datos[3] = consulta.getString("direccion");
                datos[4] = consulta.getString("telefono");
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
            cn = new Conexion();

            PreparedStatement parametro;
            String query = "INSERT INTO proveedores(proveedor,nit,direccion,telefono)VALUES(?,?,?,?)";
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);

            parametro.setString(1, getProveedor());
            parametro.setString(2, getNit());
            parametro.setString(3, getDireccion());
            parametro.setString(4, getTelefono());
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
            cn = new Conexion();

            PreparedStatement parametro;
            String query = "update proveedores set proveedor=?,nit=?,direccion=?,telefono=? where idProveedor =?";
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);

            parametro.setString(1, getProveedor());
            parametro.setString(2, getNit());
            parametro.setString(3, getDireccion());
            parametro.setString(4, getTelefono());
            parametro.setInt(5, getId());

            retorno = parametro.executeUpdate();

            cn.cerrar_conexion();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            retorno = 0;

        }
        return retorno;

    }

    public int eliminar() {

        int retorno = 0;
        try {
            cn = new Conexion();

            PreparedStatement parametro;
            String query = "delete from proveedores where idProveedor = ?;";
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);

            parametro.setInt(1, getId());

            retorno = parametro.executeUpdate();

            cn.cerrar_conexion();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return retorno;

    }
}
