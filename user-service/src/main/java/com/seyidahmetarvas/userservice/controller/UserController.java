package com.seyidahmetarvas.userservice.controller;

import com.seyidahmetarvas.userservice.common.base.RestResponse;
import com.seyidahmetarvas.userservice.dto.request.UserSaveRequest;
import com.seyidahmetarvas.userservice.dto.request.UserUpdateRequest;
import com.seyidahmetarvas.userservice.dto.response.UserDto;
import com.seyidahmetarvas.userservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "GET request for all users", description = "Returns all users in database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "401", description = "Unauthorized<br>-Token invalid (wrong or expired token)"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping
    public ResponseEntity<RestResponse<List<UserDto>>> getAllUsers() {
        return ResponseEntity.ok(RestResponse.of(userService.getAllUsers(), "All users listed."));
    }

    @Operation(summary = "GET request for a user by id", description = "Returns a user by id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "401", description = "Unauthorized<br>-Token invalid (wrong or expired token)"),
            @ApiResponse(responseCode = "404", description = "Not Found<br>-User not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<UserDto>> getUserById(@PathVariable @Positive Long id) {
        return ResponseEntity.ok(RestResponse.of(userService.getUserById(id), "User listed."));
    }

    @Operation(summary = "POST request to save a user", description = "Saves a user to database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request<br>- E-mail already exists"),
            @ApiResponse(responseCode = "401", description = "Unauthorized<br>-Token invalid (wrong or expired token)"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping
    public ResponseEntity<RestResponse<UserDto>> saveUser(@Valid @RequestBody UserSaveRequest request) {
        return ResponseEntity.ok(RestResponse.of(userService.createUser(request), "User saved."));
    }

    @Operation(summary = "PUT request to update a user", description = "Updates a user in database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "401", description = "Unauthorized<br>-Token invalid (wrong or expired token)"),
            @ApiResponse(responseCode = "404", description = "Not Found<br>-User not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PutMapping("/{debugId}")
    public ResponseEntity<RestResponse<UserDto>> updateUser(@PathVariable Long debugId, @Valid @RequestBody UserUpdateRequest request) {
        return ResponseEntity.ok(RestResponse.of(userService.updateUser(request), "User updated."));
    }

    @Operation(summary = "DELETE request to delete a user", description = "Deletes a user from database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "401", description = "Unauthorized<br>-Token invalid (wrong or expired token)"),
            @ApiResponse(responseCode = "404", description = "Not Found<br>-User not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<RestResponse<UserDto>> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(RestResponse.empty("User deleted."));
    }
}
