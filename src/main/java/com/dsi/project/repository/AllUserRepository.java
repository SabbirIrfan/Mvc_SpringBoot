package com.dsi.project.repository;

import com.dsi.project.model.AllUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AllUserRepository extends CrudRepository<AllUser, String> {
    public AllUser findByEmail(String email);
}
