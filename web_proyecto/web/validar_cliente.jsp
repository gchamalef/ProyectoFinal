<%-- 
    Document   : validar_cliente
    Created on : 4 nov 2023, 14:36:24
    Author     : pc01
--%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="modelo.Conexion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        PreparedStatement parametro;
        Conexion cn = new Conexion();
        String nombre = request.getParameter("txt_nit");
        String query = "SELECT * FROM clientes where nit = '" + nombre + "';";
        ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
        while (consulta.next()) {
            if (consulta.next()) {
                out.println("<div id='Success'>Cliente Existente</div>");
            } else {
                out.println("<div id='Error'>Cliente no existe</div>");
            }

    %>
</html>
