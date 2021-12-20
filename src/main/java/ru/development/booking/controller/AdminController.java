//package ru.development.booking.controller;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import ru.development.booking.dto.AdminUserDto;
//import ru.development.booking.mapper.UserMapper;
//import ru.development.booking.model.User;
//import ru.development.booking.service.UserService;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping(value = "/api/v1/admin")
//public class AdminController {
//
//    private final UserService userService;
//
//    @PreAuthorize("hasRole('ADMIN')")
//    @GetMapping(value = "/users/{id}")
//    public ResponseEntity<AdminUserDto> getUserById(@PathVariable(name = "id") Long id) {
//        User user = userService.findById(id);
//
//        AdminUserDto result = UserMapper.toAdminUserDto(user);
//
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }
//}
