package Controllers.interfaces;

import java.util.HashMap;

public interface CRUD<T> {

    default void getList(HashMap<String, T> lista){
        for (int i = 0; i < lista.size(); i++){
            System.out.println(lista.get(i));
        }
    };

    /**
     * Agrega o sube un objecto generico
     * @param t El objeto generico
     * @return Devuelve <code>true</code> si es que el objecto se agrego correctamente, de lo contrario <code>false</code>
     */
    void create(T T);

    /**
     * Actualiza un objecto generico a partir de su id
     * @param t Tipo generico
     * @param id Id del generico
     * @return <code>true</code> si es que se logro modificar el objecto, de lo contrario <code>false</code>
     */
    void put(T t, String id);

    /**
     * Elimina un objecto generico por su id
     * @param id Identificador del objecto
     * @return Devulve <code>true</code> si se elimino, de lo contrario devuelve <code>false</code>
     */
    boolean delete(String id);

}
