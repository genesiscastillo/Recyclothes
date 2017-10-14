package com.recyclothes.remote;

import com.recyclothes.ejbclient.*;
import org.jboss.ejb.client.EJBClient;
import org.jboss.ejb.client.EJBClientContext;
import org.jboss.ejb.client.PropertiesBasedEJBClientConfiguration;
import org.jboss.ejb.client.StatelessEJBLocator;
import org.jboss.ejb.client.remoting.ConfigBasedEJBClientContextSelector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 *  * Class EJBLocator is the class to connect with EJB
 *  *
 *  * @author Tapas Bose
 *  * @since 1.0
 *  
 */
public class EJBLocator {

    private static final Map<Class<?>, String> mapaLogicas  = new HashMap<Class<?> , String>(){
        {
            put( ClienteEJB.class 	            , "ejb:RecyclothesEAR-ear/RecyclothesEAR-ejb/ClienteEJBBean!com.recyclothes.ejbclient.ClienteEJB" );
            put( DetalleReservaEJB.class        , "ejb:RecyclothesEAR-ear/RecyclothesEAR-ejb/DetalleReservaEJBBean!com.recyclothes.ejbclient.DetalleReservaEJB" );
            put( FotoProductoEJB.class 	        , "ejb:RecyclothesEAR-ear/RecyclothesEAR-ejb/FotoProductoEJBBean!com.recyclothes.ejbclient.FotoProductoEJB" );
            put( ProductosEJB.class 	        , "ejb:RecyclothesEAR-ear/RecyclothesEAR-ejb/ProductosEJBBean!com.recyclothes.ejbclient.ProductosEJB" );
            put( ReservaEJB.class 	            , "ejb:RecyclothesEAR-ear/RecyclothesEAR-ejb/ReservaEJBBean!com.recyclothes.ejbclient.ReservaEJB" );
            put( ActualizacionAccesoEJB.class   , "ejb:RecyclothesEAR-ear/RecyclothesEAR-ejb/ActualizacionAccesoEJBBean!com.recyclothes.ejbclient.ActualizacionAccesoEJB" );
        }
    };

    public static void main(String... args) throws  Exception{
        ProductosEJB productosEJB =  EJBLocator.locateEJB(ProductosEJB.class);
        //System.out.println("HOLA = "+ productosEJB.obtenerProducto(22L));
        //ProductoDTO productoDTO = productosEJB.obtenerProducto(22L);
        //System.out.println("HOLA = "+ productoDTO);
    }

    public static <T> T locateEJB(Class<?> clazz) throws NamingException {
        String jndi = mapaLogicas.get(clazz);
        Properties clientProperties = new Properties();
        clientProperties.put("remote.connections", "default");
        clientProperties.put("remote.connection.default.port", "8080");
        clientProperties.put("remote.connection.default.host", "localhost");//web-babycaprichitos.rhcloud.com
        //clientProperties.put("remote.connection.default.username", "adminypWiBTH");//adminypWiBTH
        //clientProperties.put("remote.connection.default.password", "UhYxgrHVQyh-");//UhYxgrHVQyh-
        clientProperties.put("remote.connection.default.connect.options.org.xnio.Options.SASL_POLICY_NOANONYMOUS", "false");
       // clientProperties.put("remote.connection.default.connect.options.org.xnio.Options.SASL_POLICY_NOPLAINTEXT", "false");
        clientProperties.put("remote.connectionprovider.create.options.org.xnio.Options.SSL_ENABLED", "true");//false

        EJBClientContext.setSelector(new ConfigBasedEJBClientContextSelector(new PropertiesBasedEJBClientConfiguration(clientProperties)));
        Properties properties = new Properties();
        properties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        properties.put("jboss.naming.client.ejb.context", "true");
        Context context = new InitialContext(properties);
        return (T) context.lookup(jndi);
    }
    public static <T> T locateEJBStateless(Class<T> viewType, String appName, String moduleName, String beanName, String distinctName) {
        Properties properties = new Properties();
        properties.put("endpoint.name", "client-endpoint");
        properties.put("remote.connectionprovider.create.options.org.xnio.Options.SSL_ENABLED", "false");
        properties.put("remote.connections", "default");
        properties.put("remote.connection.default.port", "8080");
        properties.put("remote.connection.default.host", "localhost");
        properties.put("remote.connection.default.username", "genesis");
        properties.put("remote.connection.default.password", "genesis");
        properties.put("remote.connection.default.connect.options.org.xnio.Options.SASL_POLICY_NOANONYMOUS", "false");
        EJBClientContext.setSelector(new ConfigBasedEJBClientContextSelector(new PropertiesBasedEJBClientConfiguration(properties)));
        StatelessEJBLocator<T> locator = new StatelessEJBLocator<T>(viewType, appName, moduleName, beanName, distinctName);
        T ejb = EJBClient.createProxy(locator);
        return ejb;
    }
}