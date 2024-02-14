package com.dsi.project.repository;

import com.dsi.project.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {

    public List<User> findUserByEmail(String email);

    // jQL??
    @Query("select  user from User user")
    public List<User> getAllUser();

    // native querry language
//    @Query(value = "select * from User", nativeQuery = true)
//    public List<User> getAllUserNative();



//    @Query("select u From User u where u.name =:name and u.email=:email")
//    public  List<User> getUserByNameAndEmail(@Param("name") String name, @Param("email") String email);
//

}
