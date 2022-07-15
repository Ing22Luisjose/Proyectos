package org.diplomado.apiservlet.webapp.headers.services;

import org.diplomado.apiservlet.webapp.headers.models.Producto;

import java.util.Arrays;
import java.util.List;

public class ProductoServiceimpl implements ProductoService {
    @Override
    public List<Producto> listar() {
        return Arrays.asList(new Producto(1L,"notebook", "computacion",175000),
                 new Producto(2L, "mesa escritorio", "oficina", 100000),
                 new Producto(3L, "teclado numerico", "computacion",4000));


    }


}
