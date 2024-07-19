package com.example.smartcontactmanager.repository;

import com.example.smartcontactmanager.model.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ContactRepository extends JpaRepository<Contact,Integer> {


    @Query("from Contact as c where c.user.userId=:userId")
    Page<Contact> findContactByUserId(int userId, Pageable pageable);


}
