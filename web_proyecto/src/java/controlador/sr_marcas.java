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
import modelo.Marca;

/**
 *
 * @author pc01
 */
public class sr_marcas extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    Marca marca;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet sr_marcas</title>");
            out.println("</head>");
            out.println("<body>");
            //boton agregar
            if ("Agregar".equals(request.getParameter("btn_agregar"))) {
                marca = new Marca(Integer.parseInt(request.getParameter("txt_idmarca")), (request.getParameter("txt_marca")));
                if (marca.agregar() > 0) {
                    response.sendRedirect("marcas.jsp");
                } else {
                    out.println("<h1>Error........</h1>");
                    out.println("<a href='marcas.jsp'>regresar</a>");
                }
            }
            //boton modificar
            if ("Modificar".equals(request.getParameter("btn_modificar"))) {
                marca = new Marca(Integer.parseInt(request.getParameter("txt_idmarca")), (request.getParameter("txt_marca")));
                if (marca.modificar() > 0) {
                    response.sendRedirect("marcas.jsp");
                } else {
                    out.println("<h1>Error........</h1>");
                    out.println("<a href='marcas.jsp'>regresar</a>");
                }
            }
            //boton eliminar
            if ("Eliminar".equals(request.getParameter("btn_eliminar"))) {
                marca = new Marca(Integer.parseInt(request.getParameter("txt_idmarca")), (request.getParameter("txt_marca")));
                if (marca.eliminar() > 0) {
                    response.sendRedirect("marcas.jsp");
                } else {
                    out.println("<h1>Error........</h1>");
                    out.println("<a href='marcas.jsp'>regresar</a>");
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
