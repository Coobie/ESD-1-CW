package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.User;

@Stateless
public class UsersDaoImpl extends GenericDaoImpl<User, Long> implements UsersDao
{

    private static final Logger LOGGER = Logger.getLogger(UsersDaoImpl.class.getName());

    public List<User> findAll()
    {
        List<User> entityList = (List<User>) entityManager.createQuery("SELECT e FROM Users e", User.class)
                .getResultList();
        return entityList;
    }

    public List<User> findAllPagedFiltered(String searchTerm, long pageNumber, long pageSize)
    {
        List<User> entityList = null;
        try
        {
            TypedQuery<User> query = entityManager
                    .createQuery(findAllPagedFilteredJpql(false, searchTerm, pageNumber, pageSize), User.class);
            if (!searchTerm.isEmpty())
            {
                query.setParameter("term", "%" + searchTerm + "%");
            }
            query.setFirstResult(((int) pageNumber - 1) * (int) pageSize);
            query.setMaxResults((int) pageSize);
            entityList = query.getResultList();
        } catch (NoResultException e)
        {
//			LOGGER.info(e.getMessage());
        }
        return entityList;
    }

    public Long findAllPagedFilteredCount(String searchTerm, long pageNumber, long pageSize)
    {
        Long entityCount = Long.valueOf(0);
        try
        {
            Query query = entityManager.createQuery(findAllPagedFilteredJpql(true, searchTerm, pageNumber, pageSize));
            if (!searchTerm.isEmpty())
            {
                query.setParameter("term", "%" + searchTerm + "%");
            }
            entityCount = (Long) query.getSingleResult();
        } catch (NoResultException e)
        {
//			LOGGER.info(e.getMessage());
        }
        return entityCount;
    }

    private String findAllPagedFilteredJpql(boolean count, String searchTerm, long pageNumber, long pageSize)
    {
        return "SELECT " + (count ? "count(e)" : "e") + " FROM Users e"
                + (!searchTerm.isEmpty()
                ? " WHERE CAST(e.id as string) LIKE :term" + " OR UPPER(e.firstname) LIKE :term"
                + " OR UPPER(e.lastname) LIKE :term" + " OR UPPER(e.username) LIKE :term"
                : "");
    }

    public User findByName(String username)
    {
        User entity = null;

        try
        {
            DataBase db = new DataBase();
            //here sonoo is database name, root is username and password  
            //Statement stmt = con.createStatement();
            PreparedStatement stmt = db.getCon().prepareStatement("select e.* from users e where e.USERNAME = ?");
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            System.out.println(rs.toString());
            while (rs.next())
            {
                entity = new User(rs.getString("username"), rs.getString("password"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("usertype"));
                entity.setID(rs.getInt("id"));
            }

            stmt.close();
            db.getCon().close();

        } catch (Exception e)
        {
            System.out.println(e);
        }
        return entity;
    }

    public void addUser(String fn, String ln, String pass, String un, String r)
    {
        
        try
        {
            DataBase db = new DataBase();
            PreparedStatement stmt = db.getCon().prepareStatement("INSERT INTO users(USERNAME, PASSWORD, USERTYPE, FIRST_NAME, LAST_NAME) VALUES (?, ?, ?, ?, ?)");
            stmt.setString(1, un);
            stmt.setString(2, pass);
            stmt.setString(3, r);
            stmt.setString(4, fn);
            stmt.setString(5, ln);
            
            // execute insert SQL stetement
            stmt.executeUpdate();
            
            stmt.close();
            db.getCon().close();
            
        } catch (SQLException ex)
        {
            Logger.getLogger(UsersDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("FAILED IN CREATING USER");
        }
    }

    public User addCustomer(String fn, String ln, String pass, String un, String add)
    {
        this.addUser(fn, ln, pass, un, "CUSTOMER");
        User user = this.findByName(un);
        
        try
        {
            DataBase db = new DataBase();
            PreparedStatement stmt = db.getCon().prepareStatement("INSERT INTO customer(ADDRESS, USER_ID) VALUES (?, ?)");
            stmt.setString(1, add);
            stmt.setInt(2, user.getID());
            
            // execute insert SQL stetement
            stmt.executeUpdate();
            
            stmt.close();
            db.getCon().close();
            
        } catch (SQLException ex)
        {
            Logger.getLogger(UsersDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("FAILED IN CREATING CUSTOMER");
        }
        
        return user;
    }
    
    public void updateUser(User user) {
        try
        {
            DataBase db = new DataBase();
            PreparedStatement stmt = db.getCon().prepareStatement("UPDATE users set username = ?, password = ?, first_name = ?, last_name = ? WHERE id = ?");
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getFirstName());
            stmt.setString(4, user.getLastName());
            stmt.setInt(5, user.getID());
            
            // execute insert SQL stetement
            stmt.executeUpdate();
            
            stmt.close();
            db.getCon().close();
            
        } catch (SQLException ex)
        {
            Logger.getLogger(UsersDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("FAILED IN CREATING CUSTOMER");
        }
    }

}
