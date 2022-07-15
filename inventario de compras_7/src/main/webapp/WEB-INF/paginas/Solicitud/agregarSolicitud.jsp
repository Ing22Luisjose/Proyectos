<div class="modal fade" id="agregarSolicitudModal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            
            <div class="modal-header bg-info text-white">
                <h5 class="modal-title">Agregar Solicitud</h5>
                <button clase="clse" data-dismiss="modal">
                    <span>&times;</span>
                </button>
        </div>
            
            <form action="${pageContext.request.contextPath}/ServletSolicitud?accion=insertar"
                  method="POST" class="was-validated">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="id">Id Solicitud</label>
                        <input type="text" class="form-control" name="id" required>
                    </div>
                    <div class="form-group">
                        <label for="id_proveedor">Id Industria</label>
                        <input type="text" class="form-control" name="id_industria" required>
                    </div><!-- comment -->
                    <div class="form-group">
                        <label for="id_solicitud">Id Producto</label>
                        <input type="text" class="form-control" name="id_producto" required>
                    </div>
                    <div class="form-group">
                        <label for="a">Fecha Final</label>
                        <input type="text" class="form-control" name="fecha_final" required>
                    </div>
                    <div class="form-group">
                        <label for="saldo">Cantidad</label>
                        <input type="number" class="form-control" name="cantidad" required>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn-primary" type="submit">Guardar Solicitud</button>
                   
                    </div>
            
            </form> 
            
        </div>
    </div>
    
</div>