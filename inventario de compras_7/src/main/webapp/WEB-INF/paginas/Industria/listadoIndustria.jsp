<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="es_MX" />

<section id="clientes">
    <div class="container">
        <div class="row">

            <div class="col-md-9">
                <div class="card">
                    <div class="card-header">
                        <h4>Listado de Industria</h4>
                    </div>
                    <table class="table table-striped">
                        <thead class="thead-dark">
                            <tr>
                                <th>Nombre</th>
                                <th>Descripción</th>
                                <th></th>
                            </tr>

                        </thead>
                        <tbody>

                            <c:forEach var="industria" items="${industria}" varStatus="status">
                                <tr>
                                    <td>${industria.nombre}</td>
                                    <td>${industria.detalle}</td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/ServletIndustria?accion=editar&id=${industria.id}"
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
                      <h3>Total Industria</h3>
                      <h4 class="display-4">
                          <i class="fas fa-user"> </i>${totalIndustrias}
                      </h4>
                  </div>
              </div>
          </div>
                      
          </div>
                      
 

        </div>    
</div>


</section>
<jsp:include page="agregarCliente.jsp" />