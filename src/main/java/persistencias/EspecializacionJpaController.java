/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencias;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Aplicacion;
import entidades.Especializacion;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import persistencias.exceptions.NonexistentEntityException;

/**
 *
 * @author Dario
 */
public class EspecializacionJpaController implements Serializable {

    public EspecializacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public EspecializacionJpaController(){
        emf = Persistence.createEntityManagerFactory("ProyectoUTN");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Especializacion especializacion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Aplicacion aplic = especializacion.getAplic();
            if (aplic != null) {
                aplic = em.getReference(aplic.getClass(), aplic.getId());
                especializacion.setAplic(aplic);
            }
            em.persist(especializacion);
            if (aplic != null) {
                aplic.getEsp().add(especializacion);
                aplic = em.merge(aplic);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Especializacion especializacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Especializacion persistentEspecializacion = em.find(Especializacion.class, especializacion.getId());
            Aplicacion aplicOld = persistentEspecializacion.getAplic();
            Aplicacion aplicNew = especializacion.getAplic();
            if (aplicNew != null) {
                aplicNew = em.getReference(aplicNew.getClass(), aplicNew.getId());
                especializacion.setAplic(aplicNew);
            }
            especializacion = em.merge(especializacion);
            if (aplicOld != null && !aplicOld.equals(aplicNew)) {
                aplicOld.getEsp().remove(especializacion);
                aplicOld = em.merge(aplicOld);
            }
            if (aplicNew != null && !aplicNew.equals(aplicOld)) {
                aplicNew.getEsp().add(especializacion);
                aplicNew = em.merge(aplicNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = especializacion.getId();
                if (findEspecializacion(id) == null) {
                    throw new NonexistentEntityException("The especializacion with id " + id + " no longer exists.");
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
            Especializacion especializacion;
            try {
                especializacion = em.getReference(Especializacion.class, id);
                especializacion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The especializacion with id " + id + " no longer exists.", enfe);
            }
            Aplicacion aplic = especializacion.getAplic();
            if (aplic != null) {
                aplic.getEsp().remove(especializacion);
                aplic = em.merge(aplic);
            }
            em.remove(especializacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Especializacion> findEspecializacionEntities() {
        return findEspecializacionEntities(true, -1, -1);
    }

    public List<Especializacion> findEspecializacionEntities(int maxResults, int firstResult) {
        return findEspecializacionEntities(false, maxResults, firstResult);
    }

    private List<Especializacion> findEspecializacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Especializacion.class));
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

    public Especializacion findEspecializacion(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Especializacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getEspecializacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Especializacion> rt = cq.from(Especializacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
