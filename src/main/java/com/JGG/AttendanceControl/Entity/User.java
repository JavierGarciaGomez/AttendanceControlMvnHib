package com.JGG.AttendanceControl.Entity;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.persistence.*;


@Entity
@Table(name="users")
public class User {
    @Id
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="lastName")
    private String lastName;

    @Column(name="user")
    private String user;

    @Column(name="pass")
    private String pass;

    @Column(name="isActive")
    private boolean isActive;

    public User() {
    }


    public User(int id) {
        this.id = id;
    }

    public User(String user) {
        this.user = user;
    }

    public User(String userName, String pass) {
        this.user = userName;
        this.pass = pass;
    }

    public User(int id, String name, String lastName, String user, String pass, boolean isActive) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.user = user;
        this.pass = pass;
        this.isActive = isActive;
    }

    // GETTERS and SETTERS
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    // Another getters
    public User getUser(String username) throws SQLException {
        ConnectionDB connectionDB = new ConnectionDB();
        String sql = "SELECT * FROM users WHERE user = '" + this.user+"'";
        ResultSet resultSet = connectionDB.executeQuery(sql);
        resultSet.next();
        int id=resultSet.getInt(1);
        String name=resultSet.getString(2);
        String lastName=resultSet.getString(3);
        String userName=resultSet.getString(4);
        String pass=resultSet.getString(5);
        boolean isActive=resultSet.getBoolean(6);
        User user = new User(id, name, lastName, userName, pass, isActive);
        connectionDB.closeConnection();

        return user;
    }

    public int getMaxID() throws SQLException {
        ConnectionDB connectionDB = new ConnectionDB();
        String sql = "SELECT MAX(ID) FROM USERS";
        ResultSet resultSet = connectionDB.executeQuery(sql);
        resultSet.next();
        int maxId=resultSet.getInt(1);
        connectionDB.closeConnection();
        return maxId;
    }


    public ObservableList<String> getUsersNames() throws SQLException {
        ObservableList<String> userNames = FXCollections.observableArrayList();

        // SQL
        ConnectionDB connectionDB = new ConnectionDB();
        String sql = "SELECT user FROM users";
        PreparedStatement preparedStatement = connectionDB.getConnection().prepareStatement(sql);
        System.out.println(preparedStatement);
        ResultSet resultSet = preparedStatement.executeQuery();

        // Loop the resultset
        while(resultSet.next()){
            userNames.add(resultSet.getString(1));
        }
        userNames.sort((s1, s2) -> s1.compareTo(s2));
        return userNames;
    }


    // CRUD
    public void addUser() throws SQLException {
        ConnectionDB connectionDB = new ConnectionDB();
        String sql = "INSERT INTO users VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connectionDB.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, this.id);
        preparedStatement.setString(2, this.name);
        preparedStatement.setString(3, this.lastName);
        preparedStatement.setString(4, this.user);
        preparedStatement.setString(5, this.pass);
        preparedStatement.setBoolean(6, this.isActive);

        System.out.println(preparedStatement);
        boolean isSuccessful = preparedStatement.execute();


        connectionDB.closeConnection();

    }


    // Another methods
    public boolean checkLogin() throws SQLException {
        ConnectionDB connectionDB = new ConnectionDB();
        String sql = "SELECT * FROM users WHERE user = '" + user + "' AND pass ='" + pass + "'";
        ResultSet resultSet = connectionDB.executeQuery(sql);
        boolean isUser = resultSet.next();
        connectionDB.closeConnection();
        return isUser;
    }

    // TODO test hibernate
    public boolean checkLogin2() {
        SessionFactory factory = new Configuration().configure().addAnnotatedClass(User.class).buildSessionFactory();
        Session session = factory.getCurrentSession();

        session.beginTransaction();
        Query query = session.createQuery("from User where user=:user and pass=:pass");
        query.setParameter("user", user);
        query.setParameter("pass", pass);
        User tempUser = (User) query.getSingleResult();
        System.out.println(tempUser);
        return false;



                /*session.beginTransaction();
        User user = session.get(User.class, 1);
        System.out.println(user);
        session.getTransaction().commit();

        System.out.println(user);
        */

    }


    public boolean checkAvailableId() throws SQLException {
        ConnectionDB connectionDB = new ConnectionDB();
        String sql = "SELECT * FROM users WHERE id = " + this.id;
        ResultSet resultSet = connectionDB.executeQuery(sql);
        boolean isAvailable = !resultSet.next();
        connectionDB.closeConnection();
        return isAvailable;
    }

    public boolean checkAvailableUser() throws SQLException {
        ConnectionDB connectionDB = new ConnectionDB();
        String sql = "SELECT * FROM users WHERE user = '" + this.user+"'";
        System.out.println(sql);
        ResultSet resultSet = connectionDB.executeQuery(sql);
        boolean isAvailable = !resultSet.next();
        connectionDB.closeConnection();
        return isAvailable;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", user='" + user + '\'' +
                ", pass='" + pass + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
