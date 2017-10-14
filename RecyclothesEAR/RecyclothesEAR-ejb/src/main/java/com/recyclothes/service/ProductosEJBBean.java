package com.recyclothes.service;

import com.recyclothes.dao.FotoProductoDAO;
import com.recyclothes.dao.ProductoDAO;
import com.recyclothes.ejbclient.ProductosEJB;

/**
 * Created by Cesar on 17-07-2016.
 */
@Stateless
@Remote(ProductosEJB.class)
public class ProductosEJBBean implements ProductosEJB {

    @EJB
    ProductoDAO productoDAO;
    @EJB
    FotoProductoDAO fotoProductoDAO;

/*
    @Override
    public ProductoDTO obtenerProducto(Long idProducto) {
        return Translation.convertToDTO(productoDAO.obtenerProducto(idProducto));
    }

    @Asynchronous
    @Override
    public void registrarProducto(ProductoDTO productoDTO, List<FotoProductoDTO> fotoProductoDTOs) {
        boolean status = true;
        Long idFotoProductoPrincipal = 0L;
        Producto producto = Translation.convert(productoDTO);
        productoDAO.registrarProducto(producto);
        for(FotoProductoDTO fotoProductoDTO : fotoProductoDTOs) {
            FotoProducto fotoProducto = Translation.convert(fotoProductoDTO);
            fotoProducto.setIdProducto(producto.getIdProducto());
            fotoProductoDAO.registrarFotoProducto(fotoProducto);
            if( status  )            {
                idFotoProductoPrincipal = fotoProducto.getIdFotoProducto();
                producto.setIdFotoProducto(idFotoProductoPrincipal);
                productoDAO.actualizarProducto(producto);
                status = false;
            }
        }
    }

    @Override
    public ProductoDTO actualizarProducto(ProductoDTO productoDTO) {
        Producto producto1 = Translation.convert(productoDTO);
        producto1 = productoDAO.actualizarProducto(producto1);
        return Translation.convertToDTO(producto1);
    }

    @Override
    public Long obtenerTotalPrendas(CatalogoEnum catalogoEnum, TallaEnum tallaEnum, EstadoProductoEnum estadoProductoEnum) {
        return productoDAO.obtenerTotalPrendas(catalogoEnum, tallaEnum, estadoProductoEnum);
    }

    @Override
    public ResponseDTO servicioModificarProducto( CatalogoEnum catalogoEnum, TallaEnum tallaEnum, EstadoProductoEnum estadoProductoEnum) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setDetalleProductoDTOs(obtenerListaDetalleProducto(catalogoEnum, tallaEnum, estadoProductoEnum));
        responseDTO.setTotalPrendas(    obtenerTotalPrendas(catalogoEnum , tallaEnum , estadoProductoEnum ));
        return responseDTO;
    }
    @Override
    public List<DetalleProductoDTO> obtenerListaDetalleProducto(CatalogoEnum catalogoEnum, TallaEnum tallaEnum, EstadoProductoEnum estadoProductoEnum) {
        List<DetalleProductoDTO> detalleProductoDTOs = new ArrayList<>();
        List<Producto> productoList = productoDAO.obtenerProductos(catalogoEnum, tallaEnum, estadoProductoEnum);
        for(Producto producto : productoList)   {
            List<FotoProducto> fotoProductoList = fotoProductoDAO.obtenerFotosAdjuntosDelProducto(producto.getIdProducto());
            List<FotoProductoDTO> fotoProductoDTOs = Translation.converToDTO(fotoProductoList );
            ProductoDTO productoDTO = Translation.convertToDTO(producto);
            DetalleProductoDTO detalleProductoDTO = new DetalleProductoDTO();
            detalleProductoDTO.setProductoDTO(productoDTO);
            detalleProductoDTO.setFotoProductoDTOs(fotoProductoDTOs);
            detalleProductoDTOs.add(detalleProductoDTO);
        }
        return detalleProductoDTOs;
    }

    @Override
    public List<EstadisticaDTO> obtenerEstaditicaPorEstado(CatalogoEnum catalogoEnum){
        List<EstadisticaDTO> estadisticaDTOs = new ArrayList<>();
        for(EstadoProductoEnum estadoProductoEnum : EstadoProductoEnum.values()) {
            EstadisticaDTO estadisticaDTO = new EstadisticaDTO(catalogoEnum , estadoProductoEnum);
            for(TallaEnum tallaEnum : TallaEnum.values()) {
                Long totalProductos = productoDAO.obtenerTotalPrendas(catalogoEnum, tallaEnum, estadoProductoEnum);
                KeyValueDTO<TallaEnum, Long> keyValueDTO = new KeyValueDTO<>(tallaEnum ,totalProductos);
                estadisticaDTO.addKeyValueDTOs(keyValueDTO);
            }
            estadisticaDTOs.add(estadisticaDTO);
        }
        return estadisticaDTOs;
    }
*/
}
