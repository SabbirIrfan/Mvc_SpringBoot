package com.dsi.project.repository;

import com.dsi.project.model.Seller;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellerRepository extends CrudRepository<Seller, Integer> {

     List<Seller> findSellerByEmail(String email);


    @Query("select  seller from Seller seller")
     List<Seller> getAllSeller();





//    @Query("select u From Seller u where u.name =:name and u.email=:email")
//      List<Seller> getUserByNameAndEmail(@Param("name") String name, @Param("email") String email);
//

}
