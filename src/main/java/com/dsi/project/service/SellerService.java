package com.dsi.project.service;


import com.dsi.project.model.Seller;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface SellerService {
     Seller getSellerById(Integer sellerId) ;
     List<Seller> getAllSellerService();
     boolean isNewSellerService(String email);
     void saveSellerService(Seller seller);
     void updateSellerService(Seller seller , Integer sellerId);
     Seller getSellerByEmail(String email);
//    public Page<Product> getSellerProducts(Pageable pageable);

}
