/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author pc01
 */
public class Conexion {

    public Connection conexionBD;
    //jdbc:mysql://localhost:3306/?user=root
    private final String puerto = "3306";
    private final String bd = "bc7jousq0ggm33vuf0ic";
    private final String urlConexion = String.format("jdbc:mysql://bc7jousq0ggm33vuf0ic-mysql.services.clever-cloud.com:%s/%s?serverTimezone=UTC", puerto, bd);
    private final String usuario = "u07whaz4ykd1dnnh";
    private final String contra = "FZE0eKyfGy5w4VT1Ttk7";
    private final String jdb = "com.mysql.cj.jdbc.Driver";

    public void abrir_conexion() {
        try {
            Class.forName(jdb);
            conexionBD = DriverManager.getConnection(urlConexion, usuario, contra);
            System.out.println("Conexion Exitosa...");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Error" + ex.getMessage());
        }
    }

    public void cerrar_conexion() {
        try {
            if (conexionBD != null) {
                conexionBD.close();
                System.out.println("Conexión cerrada.");
            }
        } catch (SQLException ex) {
            System.out.println("Error al cerrar la conexión: " + ex.getMessage());
        }
    }
}
