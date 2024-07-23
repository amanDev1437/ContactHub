package com.example.smartcontactmanager.repository;

import com.example.smartcontactmanager.model.Contact;
import com.example.smartcontactmanager.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ContactRepository extends JpaRepository<Contact,Integer> {


    @Query("from Contact as c where c.user.userId=:userId")
    Page<Contact> findContactByUserId(int userId, Pageable pageable);

    @Query("from Contact as c where c.user.userId=:userId")
    List<Contact> findAllContact(int userId);

    //search
    List<Contact> findByNameContainingAndUser(String keywords, User user);





}
