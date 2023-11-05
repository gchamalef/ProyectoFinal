/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author pc01
 */
public class Empleado extends Persona {

    private int idPuesto;
    private String dpi, direccion, fecha_inicio_labores, fechaingreso;
    private Conexion cn;

    public Empleado() {
    }

    public Empleado(int idPuesto, String dpi, String direccion, String fecha_inicio_labores, String fechaingreso, int id, int genero, String nombres, String apellidos, String telefono, String fecha_nacimiento) {
        super(id, genero, nombres, apellidos, telefono, fecha_nacimiento);
        this.idPuesto = idPuesto;
        this.dpi = dpi;
        this.direccion = direccion;
        this.fecha_inicio_labores = fecha_inicio_labores;
        this.fechaingreso = fechaingreso;
    }

    public int getIdPuesto() {
        return idPuesto;
    }

    public void setIdPuesto(int idPuesto) {
        this.idPuesto = idPuesto;
    }

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFecha_inicio_labores() {
        return fecha_inicio_labores;
    }

    public void setFecha_inicio_labores(String fecha_inicio_labores) {
        this.fecha_inicio_labores = fecha_inicio_labores;
    }

    public String getFechaingreso() {
        return fechaingreso;
    }

    public void setFechaingreso(String fechaingreso) {
        this.fechaingreso = fechaingreso;
    }

    @Override
    public DefaultTableModel leer() {
        DefaultTableModel tabla = new DefaultTableModel();
        try {
            cn = new Conexion();
            cn.abrir_conexion();
            String query = "SELECT e.idEmpleado as id,e.nombres,e.apellidos,e.dirreccion,e.telefono,e.DPI,case when e.genero = 0 then 'Hombre' when e.genero = 1 then 'Mujer' end as genero,e.fecha_nacimiento,e.fecha_inicio_labores,e.fechaingreso,p.puesto,p.idPuesto FROM empleados as e inner join puestos as p on e.idPuesto = p.idPuesto order by e.idEmpleado;";
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            String encabezado[] = {"id", "nombres", "apellidos", "dirreccion", "telefono", "DPI", "genero", "fecha_nacimiento", "fecha_inicio_labores", "fechaingreso", "puesto", "idPuesto"};
            tabla.setColumnIdentifiers(encabezado);
            String datos[] = new String[12];
            while (consulta.next()) {
                datos[0] = consulta.getString("id");
                datos[1] = consulta.getString("nombres");
                datos[2] = consulta.getString("apellidos");
                datos[3] = consulta.getString("dirreccion");
                datos[4] = consulta.getString("telefono");
                datos[5] = consulta.getString("DPI");
                datos[6] = consulta.getString("genero");
                datos[7] = consulta.getString("fecha_nacimiento");
                datos[8] = consulta.getString("fecha_inicio_labores");
                datos[9] = consulta.getString("fechaingreso");
                datos[10] = consulta.getString("puesto");
                datos[11] = consulta.getString("idPuesto");
                tabla.addRow(datos);
            }

            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return tabla;

    }

    @Override
    public int agregar() {
        int retorno = 0;
        try {
            PreparedStatement parametro;

            cn = new Conexion();
            String query = "INSERT INTO empleados(nombres,apellidos,dirreccion,telefono,DPI,genero,fecha_nacimiento,idPuesto,fecha_inicio_labores,fechaingreso)VALUES(?,?,?,?,?,?,?,?,?,?);";
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getNombres());
            parametro.setString(2, getApellidos());
            parametro.setString(3, getDireccion());
            parametro.setString(4, getTelefono());
            parametro.setString(5, getDpi());
            parametro.setInt(6, getGenero());
            parametro.setString(7, getFecha_nacimiento());
            parametro.setInt(8, getIdPuesto());
            parametro.setString(9, getFecha_inicio_labores());
            parametro.setString(10, getFechaingreso());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            retorno = 0;
        }

        return retorno;
    }

    @Override
    public int modificar() {
        int retorno = 0;
        try {
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "update empleados set nombres=?, apellidos=?, dirreccion=?, telefono=?, DPI=?, genero=?, fecha_nacimiento=?, idPuesto=?,fecha_inicio_labores=?,fechaingreso=? where idEmpleado=?;";
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setString(1, this.getNombres());
            parametro.setString(2, this.getApellidos());
            parametro.setString(3, this.getDireccion());
            parametro.setString(4, this.getTelefono());
            parametro.setString(5, this.getDpi());
            parametro.setInt(6, this.getGenero());
            parametro.setString(7, this.getFecha_nacimiento());
            parametro.setInt(8, this.getIdPuesto());
            parametro.setString(9, this.getFecha_inicio_labores());
            parametro.setString(10, this.getFechaingreso());
            parametro.setInt(11, this.getId());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return retorno;
    }

    @Override
    public int eliminar() {
        int retorno = 0;
        try {
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "delete from empleados where idEmpleado=?;";
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

    public HashMap ListaEmpleados() {
        HashMap<String, String> drop = new HashMap();

        try {
            String query = "Select idEmpleado as id,nombres from empleados";
            cn = new Conexion();
            cn.abrir_conexion();
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            while (consulta.next()) {
                drop.put(consulta.getString("id"), consulta.getString("nombres"));
            }

            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return drop;
    }
}
