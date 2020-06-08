package CapaDatos;

import CapaNegocio.Entidades.Vuelo;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

//Clase que realiza las consultas a la tabla vuelo de la bd
public class VueloJpaController implements Serializable {

    public VueloJpaController() {
        this.emf = Persistence.createEntityManagerFactory("ReservaHotelesVuelosPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    //actualizar un registro de vuelo
    public void edit(Vuelo vuelo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            vuelo = em.merge(vuelo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    //encontrar y devolver los vuelos que coinciden con la busqueda
    public List<Vuelo> findVueloEntities(String ciudadOrigen, String ciudadDestino, String fechaPartida, int pasajeros) {

        EntityManager em = getEntityManager();

        try {

            Query nativeQuery = em.createNativeQuery("SELECT * FROM Vuelo WHERE ciudadOrigen = ? AND ciudadDestino = ? AND "
                    + "fechaPartida = ? AND asientosDisp >= ?", Vuelo.class);

            nativeQuery.setParameter(1, ciudadOrigen);
            nativeQuery.setParameter(2, ciudadDestino);
            nativeQuery.setParameter(3, fechaPartida);
            nativeQuery.setParameter(4, pasajeros);

            return nativeQuery.getResultList();

        } finally {
            em.close();
        }
    }

    //encontrar un vuelo por su id
    public Vuelo findVuelo(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Vuelo.class, id);
        } finally {
            em.close();
        }
    }

}
