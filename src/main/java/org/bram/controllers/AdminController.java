package org.bram.controllers;

import org.bram.dtos.response.ApiResponse;
import org.bram.services.AdminServicesImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admins")
public class AdminController {

    private final AdminServicesImpl adminServices;

    public AdminController(AdminServicesImpl adminServices) {
        this.adminServices = adminServices;
    }

    @PostMapping("/ban/{id}")
    public ResponseEntity<ApiResponse> banUser


//        /**
//         * Only accessible to logged-in users with ADMIN role
//         */
//        @PreAuthorize("hasRole('ADMIN')")
//        @PostMapping("/ban/{userId}")
//        public ResponseEntity<ApiResponse> banUser(@PathVariable String userId) {
//            ApiResponse response = adminServices.banUser(userId);
//            return ResponseEntity.ok(response);
//        }
//
//        @PreAuthorize("hasRole('ADMIN')")
//        @PostMapping("/unban/{userId}")
//        public ResponseEntity<ApiResponse> unbanUser(@PathVariable String userId) {
//            ApiResponse response = adminServices.unBanUser(userId);
//            return ResponseEntity.ok(response);

}
