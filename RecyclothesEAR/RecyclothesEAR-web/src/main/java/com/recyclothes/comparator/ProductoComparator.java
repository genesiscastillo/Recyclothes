package com.recyclothes.comparator;

import com.recyclothes.common.dto.ProductoDTO;

import java.util.Comparator;

/**
 * Created by Cesar on 16-08-2016.
 */
public class ProductoComparator implements Comparator<ProductoDTO> {

    @Override
    public int compare(ProductoDTO productoDTO1 , ProductoDTO productoDTO2) {
        return productoDTO2.getFecIngreso().compareTo( productoDTO1.getFecIngreso() );
    }
}
