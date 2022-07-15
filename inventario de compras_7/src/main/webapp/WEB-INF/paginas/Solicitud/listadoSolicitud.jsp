<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="es_MX" />

<section id="clientes">
    <div class="container">
        <div class="row">

            <div class="col-md-9">
                <div class="card">
                    <div class="card-header">
                        <h4>Listado de Solicitudes</h4>
                    </div>
                    <table class="table table-striped">
                        <thead class="thead-dark">
                            <tr>
                                <th>ID</th>
                                <th>Nombre</th>
                                <th></th>
                            </tr>

                        </thead>
                        <tbody>

                            <c:forEach var="solicitud" items="${solicitud}" varStatus="status">
                                <tr>
                                    <td>${solicitud.id}</td>
                               
                                    <td>${solicitud.producto.nombre}</td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/ServletSolicitud?accion=editar&id=${solicitud.id}"
                                               class ="btn btn-secondary">
                                                <i class="fas fa-angle-double-right"></i> Editar

                                            </a>  
                                        </td>    
                                    </tr>               
                            </c:forEach>

                        </tbody>
                    </table>
                </div>

            </div>
          <!-- Totales -->
          <div class="col-md-3">
             <div class="card text-center bg-success text-white mb-3">
                  <div class="card-body">
                      <h3>Total Solicitud</h3>
                      <h4 class="display-4">
                          <i class="fas fa-user"> </i>${totalSolicitudes}
                      </h4>
                  </div>
              </div>
          </div>
                      
          </div>
                      
 

        </div>    
</div>


</section>
<jsp:include page="agregarCliente.jsp" />