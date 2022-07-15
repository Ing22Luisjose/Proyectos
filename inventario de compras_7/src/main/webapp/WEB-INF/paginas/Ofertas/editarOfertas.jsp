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

        <title>EDITAR OFERTAS</title>
    </head>
    <body>
    <jsp:include page="../comunes/cabecero.jsp" />
        <form action="${pageContext.request.contextPath}/ServletOfertas?accion=modificar&id=${oferta.id}"
              method="POST" class="was-validated">
              <jsp:include page="../comunes/botonesNavegacionEdicion.jsp" />
            <section id="details">
                <div class="container">
                    <div class="row">
                        <div class="col">
                            <div class="card">
                                <div class="card-header">
                                    <h4>Editar Oferta</h4>
                                </div>
                                <div class="card-body">
                                    <div class="form-group">
                                        <label for="adjudicaco">Adjudicado</label>
                                        <input type="text" class="form-control" name="adjudicado" required value="${oferta.adjudicado}">
                                    </div>
                                    <div class="form-group">
                                        <label for="precio">Precio</label>
                                        <input type="text" class="form-control" name="precio" required value="${oferta.precio}">
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
