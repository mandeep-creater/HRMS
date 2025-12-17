package com.hrms.ServiceImpl;

import com.hrms.Entity.Employee;
import com.hrms.enums.Role;
import com.hrms.Exceptions.ExceptionHandler;
import com.hrms.Repo.EmpRepo;
import com.hrms.Response.AuthenticationResponse;
import com.hrms.Response.EmpRegistrationRequest;
import com.hrms.Response.EmpRegistrationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.hrms.Mapper.EmpMapper.toEntity;
import static com.hrms.Mapper.EmpMapper.toResponse;
@Service
public class AuthenticationServiceImpl {
    @Autowired
    private EmpRepo empRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    public EmpRegistrationResponse register(EmpRegistrationRequest request, Role role){

        if (empRepo.findByEEmail(request.getEmail()).isPresent()) {
            // Handle user already exists, e.g. throw an exception or return null or some error response
            //throw new RuntimeException("User already exists");
            ExceptionHandler.throwUserExists(request.getEmail());
        }
//        User user = new User();
//        user.setEmail(request.getEmail());
//        user.setName(request.getName());
//        user.setAddress(request.getAddress());
//        user.setGender(request.getGender());
//        user.setPhoneNumber(request.getPhoneNumber());
//        user.setPassword(passwordEncoder.encode(request.getPassword()));
//        user.setRole(Role.USER);

        final Employee emp = toEntity(request);
        emp.setePassword(passwordEncoder.encode(request.getPassword()));
        emp.setRole(role);
        final Employee savedUser =empRepo.save(emp);
        return toResponse(savedUser);


    }


    public AuthenticationResponse authenticate(Employee request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        Employee emp = empRepo.findByEEmail(request.geteEmail())
                .orElseThrow(() -> ExceptionHandler.notFound(Employee.class, request.geteEmail()));


        String accessToken = jwtService.generateAccessToken(emp);

        return new AuthenticationResponse(accessToken, "User login was successful");
    }

}


