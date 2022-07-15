<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="es_MX" />

<section id="clientes">
    <div class="container">
        <div class="row">

            <div class="col-md-9">
                <div class="card">
                    <div class="card-header">
                        <h4>Listado de Proveedores</h4>
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

                            <c:forEach var="proveedor" items="${proveedor}" varStatus="status">
                                <tr>
                                    <td>${proveedor.nombre}</td>
                                    <td>${proveedor.descripcion}</td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/ServletProveedor?accion=editar&id=${proveedor.id}"
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
                      <h3>Total Proveedores</h3>
                      <h4 class="display-4">
                          <i class="fas fa-user"> </i>${totalProveedores}
                      </h4>
                  </div>
              </div>
          </div>
                      
          </div>
                      
 

        </div>    
</div>


</section>
<jsp:include page="agregarCliente.jsp" />