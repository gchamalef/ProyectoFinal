<%-- 
    Document   : registro
    Created on : 4 nov 2023, 08:10:28
    Author     : pc01
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <link rel="stylesheet" href="Styles/login.css">
        <title>Login</title>
    </head>
    <body>

        <div id="cuadro2" >
            <form action="sr_registro" method="post">
                <p id="titulo"><strong>Registrarse</strong></p>             
                <hr style="border-color:white;">
                <br>
                <label id="subtitulo1">Correo</label>
                <br/>
                <input class="nombre" type="email" name="txt_correo" placeholder="ejemplo1@email.com" required>
                <br/><br/>
                <label id="subtitulo1">Usuario</label>
                <br/>
                <input class="nombre" type="text" name="txt_nom" placeholder="Nombre" required>
                <br/><br/>
                <label id="subtitulo2">Contraseña</label>
                <br/>
                <input class="contraseña" type="password" name="txt_pass" id="pass1" placeholder="password" required>

                <br/><br/><br/>


                <input id="boton" type="submit" name="btn_ingresar" value="Sing up">
            </form>
            <br/>
            <p> ¿Ya tienes una cuenta? <a href='login.jsp'>Log in</a></p>
        </div>


        <script src="http://code.jquery.com/jquery-latest.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

    </body>
</html>
