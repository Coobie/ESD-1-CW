package dao;

import java.util.List;

import javax.ejb.Local;

import model.User;

@Local
public interface UsersDao extends GenericDao<User, Long> {

	public List<User> findAll();
	
	public List<User> findAllPagedFiltered(String searchTerm, long pageNumber, long pageSize);

	public Long findAllPagedFilteredCount(String searchTerm, long pageNumber, long pageSize);

	public User findByName(String name);

}