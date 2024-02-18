package com.dsi.project.service;


import com.dsi.project.model.Seller;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface SellerService {
    public List<Seller> getAllSellerService();
    public boolean isNewSellerService(String email);
    public void saveSellerService(Seller seller);
    public void updateSellerService(Seller seller);
    public List<Seller> getSellerByEmail(String email);
}
