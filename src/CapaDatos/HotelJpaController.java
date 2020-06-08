package CapaDatos;

import CapaNegocio.Entidades.Hotel;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

//Clase que realiza las consultas a la tabla hotel de la bd
public class HotelJpaController implements Serializable {

    public HotelJpaController() {
        this.emf = Persistence.createEntityManagerFactory("ReservaHotelesVuelosPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    //actualizar un registro de hotel
    public void edit(Hotel hotel) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            hotel = em.merge(hotel);
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

    //encontrar y devolver los hoteles que coinciden con la busqueda
    public List<Hotel> findHotelEntities(String ciudadDestino, int totalPersonas) {

        EntityManager em = getEntityManager();

        try {
            Query nativeQuery = em.createNativeQuery("SELECT * FROM Hotel WHERE ciudad = ? AND disponibilidad >= ?", Hotel.class);

            nativeQuery.setParameter(1, ciudadDestino);
            nativeQuery.setParameter(2, totalPersonas);

            return nativeQuery.getResultList();

        } finally {
            em.close();
        }
    }

    //encontrar un hotel por su id
    public Hotel findHotel(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Hotel.class, id);
        } finally {
            em.close();
        }
    }

}
