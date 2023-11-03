<!DOCTYPE html>

<%@page import="modelo.Proveedor"%>
<%@page import="javax.swing.table.DefaultTableModel"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Proveedores</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
    </head>
    
          <body style="background-color:#FFFFE0;">

      
        <h1><center><u>Formulario Proveedores</u></center></h1>
   
        
        
        <div class="container"> 
            
            <!-- The Modal -->
  <div class="modal" id="modal_proveedores">
    <div class="modal-dialog">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">Formulario Proveedores</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        
        <!-- Modal body -->
        <div class="modal-body">
            
            
            
            
            <form action="sr_proveedorees" method="post " class="form-group">
                
                
                
                <label for="lbl_id" ><b>ID</b></label>
                <input type="text" name="txt_id" id="txt_id" class="form-control" value="0" readonly>
                
                               
                <label for="lbl_proveedores" ><b>Proveedor</b></label>
                <input type="text" name="txt_proveedor" id="txt_proveedor" class="form-control" placeholder="Nombre Proveedor" required>
                
                
                               

                <label for="lbl_nit" ><b>Nit:</b></label>
                <input type="text" name="txt_nit" id="txt_nit" class="form-control" placeholder="NIT Proveedor" required>

                
                
                <label for="lbl_direccion" ><b>Direccion:</b></label>
                <input type="text" name="txt_direccion" id="txt_direccion" class="form-control" placeholder="Direccion Proveedor" required>

                
                <label for="lbl_telefono" ><b>Telefono:</b></label>
                <input type="text" name="txt_telefono" id="txt_telefono" class="form-control" placeholder="Numero Proveedor" required>

                <br>

                
                <button name="btn_agregar" id="btn_agregar" value ="agregar" class="btn btn-primary btn-lg">Agregar</button>

                
                <button name="btn_modificar" id="btn_modificar" value ="modificar" class="btn btn-success btn-lg">Modificar</button>
               

                <button name="btn_eliminar" id="btn_eliminar" value ="eliminar" class="btn btn-danger btn-lg">Eliminar</button>

                
            </form>
        
            </div>
        
        <!-- Modal footer -->
        <div class="modal-footer">
          <button type="button" class="btn btn-danger" data-dismiss="modal">Cerrar</button>
        </div>
        
      </div>
    </div>
  </div>
      

      <br>

                                    <br>

                                                    <br>
      
  <table class="table table-hover">
    <thead>
      <tr>
          <th><u>Nombre Proveedor</u></th>
        <th>  <u>Numero de Nit</u></th>
      <th><u>Direccion del Proveedor</u></th>
       <th><u>Numero de Teléfono Proveedor</u></th>
      </tr>
    </thead>
    <tbody id= "tbl_proveedores">
         

        <% 

        Proveedor proveedor = new Proveedor();
        
    DefaultTableModel tabla = new DefaultTableModel();
    tabla= proveedor.leer();
       
     for (int t=0;t<tabla.getRowCount();t++){
            out.println("<tr data-id=" + tabla.getValueAt(t,0)+ ">");
            out.println("<td>" + tabla.getValueAt(t,1) + "</td>");
            out.println("<td>" + tabla.getValueAt(t,2) + "</td>");
            out.println("<td>" + tabla.getValueAt(t,3) + "</td>");
            out.println("<td>" + tabla.getValueAt(t,4) + "</td>");
            out.println("</tr>");
    
    
            }

        
        %>
    </tbody>
  </table>
    
                    <br>

                                    <br>


                                    <button type="button" class="btn btn-warning" data-toggle="modal" data-target="#modal_proveedores"onclick="limpiar()"><b>Nuevo Proveedor</b></button>

   
        </div>
         <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"> 
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script> 
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>  

<script type= "text/javascript">
   
   function limpiar(){
    $("#txt_id").val(0);
        $("#txt_proveedor").val('');
        $("#txt_direccion").val('');
        $("#txt_nit").val('');
        $("#txt_telefono").val('');
    
    }
   
   
        $('#tbl_proveedores').on('click','tr td',function(evt){
        var target,id,proveedores,nit,direcion,telefono;
        target= $(event.target);
        
        
        id=target.parent().data('id');  
        proveedores=target.parent("tr").find("td").eq(0).html();
        nit=target.parent("tr").find("td").eq(1).html();
        direccion=target.parent("tr").find("td").eq(2).html();
        telefono=target.parent("tr").find("td").eq(3).html();
      
        
        
        $("#txt_id").val(id);
        $("#txt_proveedor").val(proveedores);
        $("#txt_nit").val(nit)
        $("#txt_direccion").val(direccion);
        $("#txt_telefono").val(telefono);

        $("#modal_proveedores").modal('show');

        

        });
        

</script>
    </body>
</html>