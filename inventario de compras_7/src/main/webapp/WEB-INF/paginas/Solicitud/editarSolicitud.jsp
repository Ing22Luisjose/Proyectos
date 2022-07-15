<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" 
              integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" 
              crossorigin="anonymous"> 

        <script src="https://kit.fontawesome.com/3556f97ea7.js" crossorigin="anonymous"></script>

        <title>EDITAR SOLICITUD</title>
    </head>
    <body>
    <jsp:include page="../comunes/cabecero.jsp" />
        <form action="${pageContext.request.contextPath}/ServletSolicitud?accion=modificar&id=${solicitud.id}"
              method="POST" class="was-validated">
              <jsp:include page="../comunes/botonesNavegacionEdicion.jsp" />
            <section id="details">
                <div class="container">
                    <div class="row">
                        <div class="col">
                            <div class="card">
                                <div class="card-header">
                                    <h4>Editar Solicitud</h4>
                                </div>
                                <div class="card-body">
                                    <div class="form-group">
                                        <label for="cantidad">Cantidad de la Solicitud</label>
                                        <input type="text" class="form-control" name="cantidad" required value="${solicitud.proveedor.cantidad}">
                                    </div><!-- comment -->
                                    <div class="form-group">
                                        <label for="adjudicaco">Fecha Final</label>
                                        <input type="text" class="form-control" name="adjudicado" required value="${solicitud.fecha_final}">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </form>

    </body>
</html>
