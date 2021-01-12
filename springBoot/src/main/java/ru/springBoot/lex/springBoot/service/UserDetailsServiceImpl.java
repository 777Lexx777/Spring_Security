package ru.springBoot.lex.springBoot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.springBoot.lex.springBoot.model.User;
import ru.springBoot.lex.springBoot.repository.UserRepository;
import ru.springBoot.lex.springBoot.security.SecurityUser;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
       User user = userRepository.findByName(name).orElseThrow(() -> new UsernameNotFoundException("user doesn't exists"));
       return SecurityUser.fromUser(user);
    }
}
