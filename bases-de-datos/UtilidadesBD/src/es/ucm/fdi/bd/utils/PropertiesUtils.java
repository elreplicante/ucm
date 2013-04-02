package es.ucm.fdi.bd.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 
 * Métodos de utilería para trabajar con la clase {@link Properties}
 * 
 * @author Iván Martinez Ortiz
 *
 */
public abstract class PropertiesUtils {

  /**
   * Inicializa una instancia de {@link Properties} con las propiedades que se 
   * encuentra en {@literal is}. Este método se encarga de cerrar el flujo {@literal is}
   * 
   * @param is {@link InputStream} de donde se leerán las propiedades
   * 
   * @return la instancia de {@link Properties} con las propiedades leídas.
   * 
   * @throws NullPointerException si {@literal is} es {@literal null}.
   * 
   * @throws RuntimeException si ocurre algún error durante la lectura
   */
  public static Properties cargaProperties(InputStream is) {
    if (is == null) {
      throw new NullPointerException("is no puede ser nulo");
    }
    
    Properties properties = new Properties();
    try {
      properties.load(is);
    } catch (IOException e) {
      throw new RuntimeException("Error cargando propiedades", e);
    } finally {
      try {
        is.close();
      } catch (IOException e) {
        
      }
    }
    return properties;
  }
  
  /**
   * Inicializa una instancia de {@link Properties} con las propiedades
   * que se encuentran en un {@literal recurso} accesible por el {@link ClassLoader}.
   * 
   * @param recurso recurso a cargar.
   * 
   * @return la instancia de {@link Properties} con las propiedades leídas.
   * 
   * @see ClassLoader#getResourceAsStream(String)
   * @see #cargaProperties(InputStream)
   */
  public static Properties cargaProperties(String recurso) {
    ClassLoader cl = PropertiesUtils.class.getClassLoader();
    InputStream is = cl.getResourceAsStream(recurso);
    return cargaProperties(is);
  }
}
