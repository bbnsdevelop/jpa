package br.com.bbnsdevelop.hibernate.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.bbnsdevelop.hibernate.entities.Review;

@Repository
public class ReviewRepository {
	

	@Autowired
	private EntityManager entityManager;
	
	
	public Review findById(Long id) {
		return entityManager.find(Review.class, id);
	}
	
	public List<Review> findAll() {
		TypedQuery<Review> query = entityManager.createQuery("Select e From Review e", Review.class);
		return query.getResultList();
	}
	
	
	@Transactional
	public Review save(Review review) {
		
		if(review.getId() == null) {
			entityManager.persist(review);
		}else {
			entityManager.merge(review);
		}		
		return review;
	}
	
	public void deleteById(Long id) {
		Review review = this.findById(id);
		if(review != null){
			entityManager.remove(review);			
		}else {
			throw new IllegalArgumentException("Review não encontrado");
		}
	}

}
