package com.dsi.project.service;


import com.dsi.project.model.Seller;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface SellerService {
    public Seller getSellerById(Integer sellerId) ;
        public List<Seller> getAllSellerService();
    public boolean isNewSellerService(String email);
    public void saveSellerService(Seller seller);
    public void updateSellerService(Seller seller , Integer sellerId);
    public List<Seller> getSellerByEmail(String email);

}
