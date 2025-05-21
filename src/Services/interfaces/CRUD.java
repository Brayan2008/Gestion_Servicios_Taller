package Services.interfaces;

import java.util.HashMap;

public interface CRUD<T> {

    /**
     * Obtiene un T a partir de su ID
     * @param id Cadena ID a buscar
     * @return El valor T
     */
    T getByID(String id);

    /**
     * Obtiene un HashMap de los objetos T
     * @return un <code> HashMap </code>
     */
    HashMap<String, T> getList();

    /**
     * Agrega o sube un objecto generico
     * @param t El objeto generico
     * @return Devuelve <code>true</code> si es que el objecto se agrego correctamente, de lo contrario <code>false</code>
     */
    boolean create(T T);

    /**
     * Actualiza un objecto generico a partir de su id
     * @param t Tipo generico
     * @param id Id del generico
     * @return <code>true</code> si es que se logro modificar el objecto, de lo contrario <code>false</code>
     */
    boolean put(T t, String id);

    /**
     * Elimina un objecto generico por su id
     * @param id Identificador del objecto
     * @return Devulve <code>true</code> si se elimino, de lo contrario devuelve <code>false</code>
     */
    boolean delete(String id);

}
