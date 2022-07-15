<div class="modal fade" id="agregarProveedorModal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            
            <div class="modal-header bg-info text-white">
                <h5 class="modal-title">Agregar Proveedor</h5>
                <button clase="clse" data-dismiss="modal">
                    <span>&times;</span>
                </button>
        </div>
            
            <form action="${pageContext.request.contextPath}/ServletProveedor?accion=insertar"
                  method="POST" class="was-validated">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="id">Id Proveedor</label>
                        <input type="text" class="form-control" name="id" required>
                    </div>
                    <div class="form-group">
                        <label for="a">Nombre</label>
                        <input type="text" class="form-control" name="nombre" required>
                    </div>
                    <div class="form-group">
                        <label for="saldo">Descripción</label>
                        <input type="number" class="form-control" name="descripcion" required>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn-primary" type="submit">Guardar Proveedor</button>
                   
                    </div>
            
            </form> 
            
        </div>
    </div>
    
</div>