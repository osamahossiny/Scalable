package com.example.demo.Service;

import com.example.demo.Repository.AppUserRepository;
import com.example.demo.Model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.param.CustomerCreateParams;

@Service
public class AppUserService {

    private AppUserRepository appUserRepository;

    @Autowired
    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public List<AppUser> getUsers(){
        return appUserRepository.findAll();
    }

    public void addNewUser(AppUser appUser) {
        Optional<AppUser> userByEmail = appUserRepository.findUserByEmail(appUser.getEmail());

        if (userByEmail.isPresent()){
            throw new IllegalStateException("A user with this email already exists.");
        }
        Stripe.apiKey = System.getenv().get("STRIPE_API_KEY");
        CustomerCreateParams params =
                CustomerCreateParams.builder()
                        .setName(appUser.getUserName())
                        .setEmail(appUser.getEmail())
                        .build();
        try {
            Customer customer = Customer.create(params);
            appUser.setStripeCustomerId(customer.getId());
              appUserRepository.save(appUser);
        } catch (StripeException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteUser(Long userId) {
        boolean exists = appUserRepository.existsById(userId);
        if (!exists) {
            throw new IllegalStateException("User with id "+ userId + " does not exist.");
        }
        try {
            Customer customer = Customer.retrieve(appUserRepository.findById(userId).get().getStripeCustomerId());
            customer.delete();
        } catch (StripeException e) {
            throw new RuntimeException(e);
        }
        appUserRepository.deleteById(userId);
    }

    public AppUser getUserById(Long userId) {
        return appUserRepository.findById(userId).orElseThrow(() ->
            new IllegalStateException("User with id " + userId + " does not exist")
        );
    }
}
