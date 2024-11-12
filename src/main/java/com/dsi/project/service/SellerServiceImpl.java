package com.dsi.project.service;

import com.dsi.project.model.Seller;
import com.dsi.project.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public void updateSellerService(Seller seller, Integer sellerId) {

        Optional<Seller> sellerOptional = sellerRepository.findById(sellerId);
        System.out.println(sellerId);
        if(sellerOptional.isPresent()){
           Seller updatedSeller =  sellerOptional.get();
           updatedSeller.setEmail(seller.getEmail());
           updatedSeller.setName(seller.getName());
           sellerRepository.save(updatedSeller);

           return;
        }

        System.out.println( "could not update");


    }

    @Override
    public Seller getSellerByEmail(String email) {
        List<Seller> sellers = sellerRepository.findSellerByEmail(email);
        if( !sellers.isEmpty()){
            return sellers.getFirst();
        }
        return null;
    }

//    @Override
//    public Page<Product> getSellerProducts(Pageable pageable) {
//        return sellerRepository.getSellerProducts();
//    }

    @Override
    public Seller getSellerById(Integer sellerId) {
        Optional<Seller> sellerOptional = sellerRepository.findById(sellerId);
        System.out.println("i am here ");
         if(sellerOptional.isPresent()){
             System.out.println("found seller");
             return sellerOptional.get();
         } else{
           return  null;
         }
    }

}
