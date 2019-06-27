package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ID;
	

	private String password;
	private String usertype;
	private String firstName;
	private String lastName;
	
	@Column(unique = true)
	private String username;
	
	public User(){
            
	}
        
        public User(User u){
            this.username = u.username;
            this.ID = u.ID;
            this.firstName = u.firstName;
            this.lastName = u.lastName;
            this.password = u.password;
            this.usertype = u.usertype;
	}
	
	public User(String username, String password, String firstName, String lastName, String usertype){
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
                this.usertype = usertype;
		
	}
        
        

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	
}