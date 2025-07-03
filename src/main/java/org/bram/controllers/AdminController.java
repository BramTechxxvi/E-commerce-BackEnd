package org.bram.controllers;

import org.bram.dtos.response.ApiResponse;
import org.bram.services.AdminServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admins")
public class AdminController {

    private final AdminServicesImpl adminServices;

    @Autowired
    public AdminController(AdminServicesImpl adminServices) {
        this.adminServices = adminServices;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/ban/{id}")
    public ResponseEntity<ApiResponse> banUser(@PathVariable("id") String id) {
        ApiResponse response = adminServices.banUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/unBan/{id}")
    public ResponseEntity<ApiResponse> unBanUser(@PathVariable("id") String id) {
        ApiResponse response = adminServices.unBanUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
