package com.hrms.ServiceImpl;

import com.hrms.Repo.EmpRepo;
import com.hrms.Service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class EmpServiceImpl implements UserDetailsService {

    @Autowired
    private EmpRepo empRepo;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return empRepo.findByEEmail(email).orElseThrow(()->new UsernameNotFoundException("User Not Found"));
    }
}
