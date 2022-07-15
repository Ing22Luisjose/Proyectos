<div class="modal fade" id="agregarProductoModal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            
            <div class="modal-header bg-info text-white">
                <h5 class="modal-title">Agregar Producto</h5>
                <button clase="clse" data-dismiss="modal">
                    <span>&times;</span>
                </button>
        </div>
            
            <form action="${pageContext.request.contextPath}/ServletProducto?accion=insertar"
                  method="POST" class="was-validated">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="id">Id Producto</label>
                        <input type="text" class="form-control" name="id" required>
                    </div>
                    <div class="form-group">
                        <label for="id_categoria">Id Categoria</label>
                        <input type="text" class="form-control" name="id_categoria" required>
                    </div><!-- comment -->
                    <div class="form-group">
                        <label for="id_producto">Id Producto</label>
                        <input type="text" class="form-control" name="id_producto" required>
                    </div>
                    <div class="form-group">
                        <label for="a">Nombre</label>
                        <input type="text" class="form-control" name="nombre" required>
                    </div>
                    <div class="form-group">
                        <label for="saldo">Descripción</label>
                        <input type="number" class="form-control" name="descripcion" required>
                    </div>
                    <div class="form-group">
                         <label for="saldo">Cantidad</label>
                         <input type="number" class="form-control" name="cantidad" required>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn-primary" type="submit">Guardar Producto</button>
                   
                    </div>
            
            </form> 
            
        </div>
    </div>
    
</div>