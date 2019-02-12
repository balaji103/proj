package com.his.ar.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.his.ar.entity.UserEntity;

@Repository("arUserRegistraionDao")
public interface ARUserRegistrationDAO extends JpaRepository<UserEntity, Serializable> {

}
