module AttendanceControlMaven {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires java.sql;
    requires java.persistence;
    requires java.naming;
    // Hibernate
    requires org.hibernate.orm.core;
    requires net.bytebuddy;
    requires java.xml.bind;
    requires com.sun.xml.bind;
    requires com.fasterxml.classmate;
    requires spring.beans;


    opens com.JGG.AttendanceControl;
    opens com.JGG.AttendanceControl.Entity;
    opens com.JGG.AttendanceControl.controller;
    opens view;

    exports com.JGG.AttendanceControl.controller;
}