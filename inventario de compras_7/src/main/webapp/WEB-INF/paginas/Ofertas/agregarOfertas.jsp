<div class="modal fade" id="agregarOfertaModal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            
            <div class="modal-header bg-info text-white">
                <h5 class="modal-title">Agregar Oferta</h5>
                <button clase="clse" data-dismiss="modal">
                    <span>&times;</span>
                </button>
        </div>
            
            <form action="${pageContext.request.contextPath}/ServletOfertas?accion=insertar"
                  method="POST" class="was-validated">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="id">Id Oferta</label>
                        <input type="text" class="form-control" name="id" required>
                    </div>
                    <div class="form-group">
                        <label for="id_proveedor">Id Proveedor</label>
                        <input type="text" class="form-control" name="id_proveedor" required>
                    </div><!-- comment -->
                    <div class="form-group">
                        <label for="id_solicitud">Id Solicitud</label>
                        <input type="text" class="form-control" name="id_solicitud" required>
                    </div>
                    <div class="form-group">
                        <label for="a">Adjudicado</label>
                        <input type="text" class="form-control" name="adjudicado" required>
                    </div>
                    <div class="form-group">
                        <label for="saldo">Precio</label>
                        <input type="number" class="form-control" name="precio" required>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn-primary" type="submit">Guardar Oferta</button>
                   
                    </div>
            
            </form> 
            
        </div>
    </div>
    
</div>