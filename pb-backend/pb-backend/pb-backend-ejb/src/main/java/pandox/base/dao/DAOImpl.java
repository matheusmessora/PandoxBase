package pandox.base.dao;

import java.lang.reflect.ParameterizedType;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Stateless
@LocalBean
public class DAOImpl<T> implements DAO<T> {

	@Inject
	private EntityManager em;

	private final Class<T> oClass;

	@SuppressWarnings("unchecked")
	public DAOImpl() {
		this.oClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Override
	public T findById(Long id) {
		return em.find(oClass, id);
	}

	@Override
	public T save(T entity) {
		em.persist(entity);
		
		return entity;
	}

}
