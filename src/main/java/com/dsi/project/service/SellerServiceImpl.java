package com.dsi.project.service;

import com.dsi.project.model.Seller;
import com.dsi.project.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    private SellerRepository sellerRepository;

    @Override
    public List<Seller> getAllSellerService() {
        return sellerRepository.getAllSeller();
    }

    @Override
    public boolean isNewSellerService(String email) {
        return !sellerRepository.findSellerByEmail(email).isEmpty();
    }

    @Override
    public void saveSellerService(Seller seller) {
        sellerRepository.save(seller);
    }


    @Override
    public void updateSellerService(Seller seller) {

    }

    @Override
    public List<Seller> getSellerByEmail(String email) {

        return sellerRepository.findSellerByEmail(email);
    }

}
