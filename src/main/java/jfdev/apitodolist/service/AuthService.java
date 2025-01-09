package jfdev.apitodolist.service;

import jfdev.apitodolist.model.user.User;
import jfdev.apitodolist.model.user.dto.UserRegisterRequest;
import jfdev.apitodolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username);
    }

    public User registerUser (UserRegisterRequest request){
     if(userRepository.findByEmail(request.getEmail()) != null){
         throw new UsernameNotFoundException("Email already in use");
     }
     String passCrypt = passwordEncoder.encode(request.getPassword());
     User user = new User();
     user.setEmail(request.getEmail());
     user.setPassword(passCrypt);
     user.setName(request.getName());
     return userRepository.save(user);
    }
}
