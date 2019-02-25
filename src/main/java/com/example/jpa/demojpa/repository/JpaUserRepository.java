package com.example.jpa.demojpa.repository;

import com.example.jpa.demojpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaUserRepository extends JpaRepository<User,Long>{

    @Query("from user where user_name like concat('%',?1,'%') and note like concat('',?2,'%')")
    public List<User> getUsers(String userName, String note);

    List<User> findUsersByUserNameIsLike(String userName);

    User getUsersById(Long id);




}
