package com.mnnit.Hostel.service;

import com.mnnit.Hostel.database.UserRepository;
import com.mnnit.Hostel.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Qualifier("MyUserDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = repository.findByUsername(username);

        if(user == null) {
            throw new UsernameNotFoundException("Invalid Credentials!");
        }
        return new UserPrincipal(user);
    }

    public UserDetails registerUser (User user) throws Exception{

        user.setRole("ROLE_USER");

        try {
            repository.save(user);
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("Not Created");
        }
        return new UserPrincipal(user);
    }
}
