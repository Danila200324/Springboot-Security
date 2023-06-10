package com.example.springbootsecurity.Config;

import com.example.springbootsecurity.CustomerRepository;
import com.example.springbootsecurity.Entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetails implements UserDetailsService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String userName = null;
        String password = null;
        List<GrantedAuthority> authorityList = null;
        List<Customer> customers = customerRepository.findByEmail(username);
        if(customers.size() == 0){
            throw new UsernameNotFoundException("There is no user with email " + username);
        }
        else{
            userName = customers.get(0).getEmail();
            password = customers.get(0).getPassword();
            authorityList = new ArrayList<>();
            authorityList.add(new SimpleGrantedAuthority(customers.get(0).getRole()));
        }
        return new User(userName, password, authorityList);
    }
}
