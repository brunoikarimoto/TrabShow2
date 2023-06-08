package principal.daos;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import jakarta.persistence.*;
import principal.modelos.Ingresso;
import principal.modelos.Pessoa;
import principal.util.Prompt;

public abstract class GenericDao<T> implements DAO<T> {
	
	private Class<T> persistedClass;
	protected EntityManager em;
	
	@SuppressWarnings("unchecked")
	protected GenericDao() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		persistedClass = (Class<T>) pt.getActualTypeArguments()[0];
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("TesteShow");
		em = emf.createEntityManager();
	}
	
	public T buscarPorId(int id) {
		return em.find(persistedClass, id);
	}
	public List<T> listar(){
		String query = "from " + persistedClass.getName();
		return em.createQuery(query, persistedClass).getResultList();
	}
	public T salvar(T entidade) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(entidade);
		em.flush();
		tx.commit();
		return entidade;
	}
	public void excluir(int id) {
		T entidade = buscarPorId(id);
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		T mergedEntity = em.merge(entidade);
		em.remove(mergedEntity);
		em.flush();
		tx.commit();
	}
	public T atualizar(T entidade) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.merge(entidade);
		em.flush();
		tx.commit();
		return entidade;
	}

	public int buscarPorCpf(String cpf) {
		int p = -1;
		
		
		try {			
			p = (int)em.createQuery("SELECT p.id FROM Pessoa p WHERE p.cpf = :CPF").setParameter("CPF", cpf).getSingleResult();
		}
		catch(Exception e) {
			Prompt.imprimir("Não encontrado");
		}
		
		return p;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> listaIngressoCPF(String cpf){
		List<T> lista = null;
		
		try {
			lista = em.createQuery("SELECT i FROM Ingresso i WHERE i.cpf_cliente = :CPF").setParameter("CPF", cpf).getResultList();
		}
		catch(Exception e) {
			Prompt.imprimir("Nenhum encontrado. naruto");
		}
		
		return lista;
	}
}
