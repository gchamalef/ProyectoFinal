/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Datos;
import java.util.ArrayList;
import modelo.Ventas;
import modelo.VentasDetalle;

/**
 *
 * @author pc01
 */
public class sr_ventas extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet sr_ventas</title>");
            out.println("</head>");
            out.println("<body>");

            double Total = 0;
            for (VentasDetalle ventasD : Datos.listador) {
                Total = Total + ventasD.getSubtotal();
            }
            if ("Pagar".equals(request.getParameter("btn_pagar"))) {
                Ventas ventas = new Ventas(Integer.parseInt(request.getParameter("txt_idVentas")), Integer.parseInt(request.getParameter("txt_nofactura")), request.getParameter("txt_serie"), Integer.parseInt(request.getParameter("txt_idCliente")), Integer.parseInt(request.getParameter("txt_idEmpleado")), request.getParameter("fecha_factura"));
                if (ventas.agregar() > 0) {
                    
                    Datos.listador.forEach(_item -> {
                        VentasDetalle ventasD = new VentasDetalle( _item.getCantidad(), _item.getProducto(), _item.getMarca(), _item.getPrecio_venta(), _item.getSubtotal(), _item.getIdVenta());
                        ventasD.agregar();
                    });
                    Datos.listador.clear();

                    response.sendRedirect("VentasDetalle.jsp");
                } else{
                    out.println("<h1>Error....................</h1>");
                    out.println("<a href ='VentasDetalle.jsp'>Regresar</a>");
                }
                Datos.listador.forEach(_item -> {
                    VentasDetalle ventasD = new VentasDetalle(_item.getCantidad(), _item.getProducto(), _item.getMarca(), _item.getPrecio_venta(), _item.getSubtotal(), _item.getIdVenta());
                    ventasD.agregar();
                });
                Datos.listador.clear();
            }

            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
