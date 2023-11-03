package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class Proveedor {

    private int idproveedor;
    private String proveedor, nit, direccion, telefono;
    Conexion cn;

    public Proveedor() {
    }

    ;

    public Proveedor(int idproveedor, String proveedor, String nit, String direccion, String telefono) {
        this.idproveedor = idproveedor;
        this.proveedor = proveedor;
        this.nit = nit;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public int getIdproveedor() {
        return idproveedor;
    }

    public void setIdproveedor(int idproveedor) {
        this.idproveedor = idproveedor;
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

            String query = "SELECT e.idproveedor,e.proveedor,e.nit,e.direccion,e.telefono FROM proveedores as e;";
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            String encabezado[] = {"idproveedor", "proveedor", "nit", "direccion", "telefono"};
            tabla.setColumnIdentifiers(encabezado);
            String datos[] = new String[5];
            while (consulta.next()) {
                datos[0] = consulta.getString("idproveedor");
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

        int retorno;
        try {
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "INSERT INTO proveedores (idproveedor, proveedor, nit, direccion, telefono) VALUES (?, ?, ?, ?, ?);";
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setInt(1, getIdproveedor());
            parametro.setString(2, getProveedor());
            parametro.setString(3, getNit());
            parametro.setString(4, getDireccion());
            parametro.setString(5, getTelefono());

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
            String query = "update proveedores set proveedor=?,nit=?,direccion=?,telefono=? where idproveedor =? ;";
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getProveedor());
            parametro.setString(2, getNit());
            parametro.setString(3, getDireccion());
            parametro.setString(4, getTelefono());
            parametro.setInt(5, getIdproveedor());
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
            String query = "delete from proveedores where idproveedor=?;";
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setInt(1, this.getIdproveedor());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return retorno;
    }

}
