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
import modelo.DatosCom;
import java.util.ArrayList;
import modelo.Compras;
import modelo.ComprasDetalle;

/**
 *
 * @author pc01
 */
public class sr_compras extends HttpServlet {

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
            out.println("<title>Servlet sr_compras</title>");
            out.println("</head>");
            out.println("<body>");
            
            double Total = 0;
            for (ComprasDetalle comprasD : DatosCom.listador) {
                Total = Total + comprasD.getSubtotal();
            }
            if ("Pagar".equals(request.getParameter("btn_pagar"))) {                
                Compras compras = new Compras(Integer.parseInt(request.getParameter("txt_idCompras")), Integer.parseInt(request.getParameter("txt_no_orden_compra")), Integer.parseInt(request.getParameter("txt_idProveedor")), request.getParameter("fecha_orden"), request.getParameter("fecha_compra"));
                if (compras.agregar() > 0) {
                    DatosCom.listador.forEach(_item -> {
                        ComprasDetalle comprasD = new ComprasDetalle(_item.getIdcompra(), _item.getCantidad(), _item.getProducto(), _item.getMarca(), _item.getPrecio_venta(), _item.getSubtotal(), _item.getIdProducto());
                        comprasD.agregar();
                        comprasD.actualizarProd(_item.getIdProducto(), _item.getPrecio_venta(), _item.getCantidad());
                    });
                    DatosCom.listador.clear();
                    response.sendRedirect("ComprasDetalle.jsp");
                } else {
                    out.println("<h1>Error....................</h1>");
                    out.println("<a href ='ComprasDetalle.jsp'>Regresar</a>");
                }

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
