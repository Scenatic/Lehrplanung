package de.lehrplanung.planung.dao;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

public abstract class GenericDAO<T> {

private final String UNIT_NAME = "SWPROJEKT_SEMESTER_EJB";

	
	@PersistenceContext(unitName=UNIT_NAME)
	private EntityManager em;
	
	private Class<T> entityClass;
	
	public GenericDAO(){}
	
	public GenericDAO(Class<T> entityClass){
		this.entityClass = entityClass;
	}
	
	public void save(T entity){
		
		this.em.persist(entity);
		
	}
	
	public T update(T entity){
		return em.merge(entity);
	}
	
	public T find(int entityId) {
		return em.find(entityClass, entityId);
	}
	
	@SuppressWarnings({"unchecked","rawtypes"})
	public List<T> findAll(){
		CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		return em.createQuery(cq).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	protected List<T> findListResult(String namedQuery, Map<String, Object> parameters){
		List<T> result = null;
		try{
			Query query = em.createNamedQuery(namedQuery);
			if (parameters != null && !parameters.isEmpty()){
				populateQueryParameters(query,parameters);
			}
			result = (List<T>) query.getResultList();
		} catch (Exception e){
			System.out.println("Fehler bei der Query: "+e.getMessage());
		}
		return result;
	}
	
	private void populateQueryParameters(Query query, Map<String, Object> parameters){
		for (Entry<String, Object> entry : parameters.entrySet()){
			query.setParameter(entry.getKey(),  entry.getValue());
		}
	}
	
}
