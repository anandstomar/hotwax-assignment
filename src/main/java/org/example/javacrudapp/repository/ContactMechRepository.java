package org.example.javacrudapp.repository;
import org.example.javacrudapp.model.ContactMech;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactMechRepository extends JpaRepository<ContactMech, Integer> {}