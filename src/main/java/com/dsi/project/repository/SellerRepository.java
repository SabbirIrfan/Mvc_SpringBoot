package com.dsi.project.repository;

import com.dsi.project.model.Seller;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellerRepository extends CrudRepository<Seller, Integer> {

    public List<Seller> findSellerByEmail(String email);

    // jQL??
    @Query("select  seller from Seller seller")
    public List<Seller> getAllSeller();

    // native querry language
//    @Query(value = "select * from Seller", nativeQuery = true)
//    public List<Seller> getAllUserNative();
    public Seller findByEmail(String  Email);



//    @Query("select u From Seller u where u.name =:name and u.email=:email")
//    public  List<Seller> getUserByNameAndEmail(@Param("name") String name, @Param("email") String email);
//

}
