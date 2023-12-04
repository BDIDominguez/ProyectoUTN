/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencias;

import entidades.Aplicacion;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Especializacion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import persistencias.exceptions.NonexistentEntityException;

/**
 *
 * @author Dario
 */
public class AplicacionJpaController implements Serializable {

    public AplicacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public AplicacionJpaController(){
        emf = Persistence.createEntityManagerFactory("ProyectoUTN");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Aplicacion aplicacion) {
        if (aplicacion.getEsp() == null) {
            aplicacion.setEsp(new ArrayList<Especializacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Especializacion> attachedEsp = new ArrayList<Especializacion>();
            for (Especializacion espEspecializacionToAttach : aplicacion.getEsp()) {
                espEspecializacionToAttach = em.getReference(espEspecializacionToAttach.getClass(), espEspecializacionToAttach.getId());
                attachedEsp.add(espEspecializacionToAttach);
            }
            aplicacion.setEsp(attachedEsp);
            em.persist(aplicacion);
            for (Especializacion espEspecializacion : aplicacion.getEsp()) {
                Aplicacion oldAplicOfEspEspecializacion = espEspecializacion.getAplic();
                espEspecializacion.setAplic(aplicacion);
                espEspecializacion = em.merge(espEspecializacion);
                if (oldAplicOfEspEspecializacion != null) {
                    oldAplicOfEspEspecializacion.getEsp().remove(espEspecializacion);
                    oldAplicOfEspEspecializacion = em.merge(oldAplicOfEspEspecializacion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Aplicacion aplicacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Aplicacion persistentAplicacion = em.find(Aplicacion.class, aplicacion.getId());
            List<Especializacion> espOld = persistentAplicacion.getEsp();
            List<Especializacion> espNew = aplicacion.getEsp();
            List<Especializacion> attachedEspNew = new ArrayList<Especializacion>();
            for (Especializacion espNewEspecializacionToAttach : espNew) {
                espNewEspecializacionToAttach = em.getReference(espNewEspecializacionToAttach.getClass(), espNewEspecializacionToAttach.getId());
                attachedEspNew.add(espNewEspecializacionToAttach);
            }
            espNew = attachedEspNew;
            aplicacion.setEsp(espNew);
            aplicacion = em.merge(aplicacion);
            for (Especializacion espOldEspecializacion : espOld) {
                if (!espNew.contains(espOldEspecializacion)) {
                    espOldEspecializacion.setAplic(null);
                    espOldEspecializacion = em.merge(espOldEspecializacion);
                }
            }
            for (Especializacion espNewEspecializacion : espNew) {
                if (!espOld.contains(espNewEspecializacion)) {
                    Aplicacion oldAplicOfEspNewEspecializacion = espNewEspecializacion.getAplic();
                    espNewEspecializacion.setAplic(aplicacion);
                    espNewEspecializacion = em.merge(espNewEspecializacion);
                    if (oldAplicOfEspNewEspecializacion != null && !oldAplicOfEspNewEspecializacion.equals(aplicacion)) {
                        oldAplicOfEspNewEspecializacion.getEsp().remove(espNewEspecializacion);
                        oldAplicOfEspNewEspecializacion = em.merge(oldAplicOfEspNewEspecializacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = aplicacion.getId();
                if (findAplicacion(id) == null) {
                    throw new NonexistentEntityException("The aplicacion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Aplicacion aplicacion;
            try {
                aplicacion = em.getReference(Aplicacion.class, id);
                aplicacion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The aplicacion with id " + id + " no longer exists.", enfe);
            }
            List<Especializacion> esp = aplicacion.getEsp();
            for (Especializacion espEspecializacion : esp) {
                espEspecializacion.setAplic(null);
                espEspecializacion = em.merge(espEspecializacion);
            }
            em.remove(aplicacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Aplicacion> findAplicacionEntities() {
        return findAplicacionEntities(true, -1, -1);
    }

    public List<Aplicacion> findAplicacionEntities(int maxResults, int firstResult) {
        return findAplicacionEntities(false, maxResults, firstResult);
    }

    private List<Aplicacion> findAplicacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Aplicacion.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Aplicacion findAplicacion(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Aplicacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getAplicacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Aplicacion> rt = cq.from(Aplicacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
