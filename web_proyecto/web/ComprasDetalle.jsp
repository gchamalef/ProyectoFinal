<%-- 
    Document   : ComprasDetalle
    Created on : 4 nov 2023, 10:25:38
    Author     : pc01
--%>

<%@page import="modelo.Proveedor"%>
<%@page import="java.lang.String"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.ComprasDetalle"%>
<%@page import="modelo.Producto"%>
<%@page import="modelo.DatosCom"%>
<%@page import="javax.swing.table.DefaultTableModel"%>
<%@page import="java.util.HashMap"%>
<%@page import="modelo.Compras"%>
<%@page import="java.text.SimpleDateFormat"%>  
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="Styles/compras.css">


        <title>Compras Detalle</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">

        <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-beta.1/dist/css/select2.min.css" rel="stylesheet" />
        <script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-beta.1/dist/js/select2.min.js"></script>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/css/select2.min.css" rel="stylesheet" /> 
        <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js"></script>
        <style>
            body{
                background:linear-gradient(45deg, #405de6, #5851db, #833ab4, #c13584, #e1306c, #fd1d1d);
                font-family: Segoe UI;
            }
        </style>
    </head>
    <body>

        <%
            HttpSession misession = (HttpSession) request.getSession();
            if (misession.getAttribute("inicio") == "iniciado") {
                HttpSession usuario = (HttpSession) request.getSession();
                String aux2 = (String) usuario.getAttribute("user2");
                Menu menu2 = new Menu();
                menu2.Pureba(aux2);
                if (menu2.getEmpleados() == 1) {
                    %>

        <%@include file="header.jsp" %>

        <div id="cuadrox">
            <%@include file="clientes_ventas.jsp" %>
            <!--  Inicia formulario de compras  -->
            <form action="sr_compras" method="post">
                <div id="xd">
                    <button name="btn_pagar" id="btn_pagar"  value="Pagar" >Terminar la compra</button>
                </div>
                <br>
                <label id="tituloins" for="lbl_id_Compras" Style="color: white;">ID Compra</label>
                <%
                    Compras compra = new Compras();
                    compra.leer();
                    int idcompra = compra.getIdcompra() + 1;
                    out.println("<input class='corto' type='text' name='txt_idCompras' id='txt_idCompras' value ='" + idcompra + "' readonly>");
                %>

                <label id="tituloins" for="lbl_noorden"  Style="color: white;">No. Orden de compra</label>

                <%
                    out.println("<input class='corto' type='text' name='txt_no_orden_compra' id='txt_no_orden_compra'  value ='" + (compra.getNo_orden() + 1) + "' readonly>");
                %>


                <label id="titulofp" Style="color: white;">Fecha de Ingreso</label>

                <%
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    Date date = new Date();

                    String fecha = date.toString();
                    out.println("<input class='fechap' type='text' name='fecha_compra' id='fecha_compra' value='" + fecha + "' readonly>");

                %>

                <label id="tituloins" Style="color: white;">Fecha de la orden</label>
                <input class='fechaorden' type='date' name='fecha_orden' id='fecha_factura' required>


                <input type="text" Style="display:none;" name="txt_idProveedor" id="txt_idProveedor"  readonly>

            </form>
            <br>


            <!--  Inicia formulario de Proveedores  -->
            <form  method="post">
                <label id="tituloins" for="lbl_idproveedores"  Style="color: white;">Nit:</label>

                <select class="SelectProveedores" id="drop_proveedores1" name="drop_proveedores1" >
                    <%            Compras compras = new Compras();
                        HashMap<String, String> droproveedores = compras.ListaP();
                        out.println("<option value='0'>Seleccione</option>");
                        for (String i : droproveedores.keySet()) {
                            out.println("<option value='" + i + "'>" + droproveedores.get(i) + "</option>");
                        }
                    %>
                </select>

                <select style="display: none;" class="SelectProveedoresI" id="drop_proveedoresI" name="drop_proveedoresI" >
                    <%
                        HashMap<String, String> droproveedoresI = compras.ListaPC();
                        out.println("<option value='0'>Seleccione</option>");
                        for (String i : droproveedoresI.keySet()) {
                            out.println("<option value='" + i + "'>" + droproveedoresI.get(i) + "</option>");
                        }
                    %>
                </select>


                <label id="tituloins" Style="color: white;">Proveedor:</label>

                <input class='proveedores' type="text"  name="text_nombreP" id="txt_nombreP"  required readonly>

            </form>    

            <br>

            <!--  Inicia formulario de ComprasDetalle  -->
            <form class="formulario_vd">
                <label id="tituloins" for="lbl_Producto" Style="color: white;">Producto</label>

                <select class="producto" name="drop_producto" id="drop_producto" >
                    <%
                        Producto producto = new Producto();
                        HashMap<String, String> dropproductos = producto.ListaProductos();
                        out.println("<option value='0'>Seleccione</option>");

                        for (String i : dropproductos.keySet()) {
                            out.println("<option value='" + i + "'>" + dropproductos.get(i) + "</option>");

                        }
                    %>
                </select>
                <label id="tituloins" for="lbl_PrecioUnitario"  Style="color: white;">Precio Unitario</label>
                <select  name="drop_precio" Style="display:none;" id="drop_precio"  disabled>
                    <%
                        HashMap<String, String> dropPrecio = producto.PrescioVenta();
                        out.println("<option value='0'>0.00</option>");
                        for (String i : dropPrecio.keySet()) {
                            out.println("<option value='" + i + "'><p name='txt_precio'>" + dropPrecio.get(i) + "<P></option>");

                        }
                    %>
                </select>
                <input class='corto1' type="text"  name="txt_precio" id="txt_precio"  required >

                <label id="tituloins" for="lbl_Cantidad" Style="color: white;" >Cantidad</label>
                <input  class='corto1' type="number"  name="txt_cantidad" id="txt_cantidad"  required>
                <br>
                <br>
                <button name="btn_agregar" id="btn_agregar"  value="agregar" >Agregar producto</button>
                <input type="hidden" style="display: none;" name="txt_idProd" id="txt_idProd" value="">

                <br>
                <br>

            </form>  
        </div>


        <!-- Tabla Compras detalle  -->
        <%
            if ("agregar".equals(request.getParameter("btn_agregar"))) {
                Producto prod = new Producto();
                DefaultTableModel tabla = new DefaultTableModel();
                tabla = prod.agregarProducto2(Integer.valueOf(request.getParameter("drop_producto")), Double.valueOf(request.getParameter("txt_precio")));
                for (int t = 0; t < tabla.getRowCount(); t++) {
                    ComprasDetalle comprasD = new ComprasDetalle();
                    String str1 = (String) tabla.getValueAt(t, 1);
                    String str2 = (String) tabla.getValueAt(t, 2);
                    String str3 = (String) tabla.getValueAt(t, 3);
                    comprasD.setProducto(str1);
                    comprasD.setIdProducto(Integer.valueOf(request.getParameter("txt_idProd")));
                    comprasD.setCantidad(Integer.valueOf(request.getParameter("txt_cantidad")));
                    comprasD.setMarca(str2);
                    double r = Double.valueOf(str3);
                    comprasD.setPrecio_venta(r);
                    double Sub = (Integer.valueOf(request.getParameter("txt_cantidad")) * (Double.valueOf(request.getParameter("txt_precio"))));
                    comprasD.setSubtotal(Sub);
                    comprasD.setIdcompra(idcompra);
                    DatosCom.listador.add(comprasD);
                }
            }
        %>
        <br>
        <div id="main-container-productos">    
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Cantidad</th>
                        <th>Producto</th>
                        <th>Marca</th>
                        <th>Precio Unitario</th>
                        <th>SubTotal</th>

                    </tr>
                </thead>
                <tbody id="tbl_comprasDetalle">
                    <%
                        for (ComprasDetalle comprasD : DatosCom.listador) {
                            out.println("<tr>");
                            out.println("<td>" + comprasD.getIdProducto() + "</td>");
                            out.println("<td>" + comprasD.getCantidad() + "</td>");
                            out.println("<td>" + comprasD.getProducto() + "</td>");
                            out.println("<td>" + comprasD.getMarca() + "</td>");
                            out.println("<td>" + comprasD.getPrecio_venta() + "</td>");
                            out.println("<td>" + comprasD.getSubtotal() + "</td>");
                            out.println("</tr>");
                        }
                    %>
                </tbody>
                <tfoot>

                    <tr>
                        <th></th>
                        <th></th>
                        <th></th>
                        <th>Total</th>
                            <%
                                double Total = 0;
                                for (ComprasDetalle comprasD : DatosCom.listador) {
                                    Total = Total + comprasD.getSubtotal();
                                }
                                out.println("<th>" + Total + "</th>");
                            %>


                    </tr>

                </tfoot>

                <!--  Finaliza tabla ventasDetalle  -->

            </table>
        </div>      
        <br>
        <br>

        <script type="text/javascript">
            $("select[name=drop_producto]").change(function () {
                var combo4 = document.getElementById("drop_producto");
                var selected4 = combo4.options[combo4.selectedIndex].value;
                document.getElementById("txt_idProd").value = selected4;
                $('select[name=drop_precio]').val($(this).val());
                var combo = document.getElementById("drop_precio");
                var selected = combo.options[combo.selectedIndex].text;
                document.getElementById("txt_precio").value = selected;
            });

            $("select[name=drop_proveedores1]").change(function () {
                var combo = document.getElementById("drop_proveedores1");
                var selected = combo.options[combo.selectedIndex].value;
                document.getElementById("txt_nombreP").value = selected;
                $('select[name=drop_proveedoresI]').val($(this).val());
                var combo2 = document.getElementById("drop_proveedoresI");
                var selected2 = combo2.options[combo2.selectedIndex].text;
                document.getElementById("txt_idProveedor").value = selected2;
            });

        </script>

        <script>
            $(document).ready(function () {

                $("#myInput").on("keyup", function () {
                    var value = $(this).val().toLowerCase();
                    $("#tbl_comprasDetalle tr").filter(function () {
                        $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1);
                    });
                });
            });
        </script>
        <script>
            $(document).ready(function () {
                $('.SelectProveedores').select2();
            });
            $(document).ready(function () {
                $('.producto').select2();
            });


        </script>
    </body>
</html>
<%}else{
            response.sendRedirect("index.jsp");
        }
    } else {
        response.sendRedirect("login.jsp");
    }

%>