<div class="modal fade" id="agregarIndustriaModal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            
            <div class="modal-header bg-info text-white">
                <h5 class="modal-title">Agregar Industria</h5>
                <button clase="clse" data-dismiss="modal">
                    <span>&times;</span>
                </button>
        </div>
            
            <form action="${pageContext.request.contextPath}/ServletIndustria?accion=insertar"
                  method="POST" class="was-validated">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="id">Id Industria</label>
                        <input type="text" class="form-control" name="id" required>
                    </div>
                    <div class="form-group">
                        <label for="a">Nombre</label>
                        <input type="text" class="form-control" name="nombre" required>
                    </div>
                    <div class="form-group">
                        <label for="detalle">Detalle</label>
                        <input type="number" class="form-control" name="detalle" required>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn-primary" type="submit">Guardar Industria</button>
                   
                    </div>
            
            </form> 
            
        </div>
    </div>
    
</div>