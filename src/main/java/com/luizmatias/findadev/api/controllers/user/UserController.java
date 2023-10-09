package com.luizmatias.findadev.api.controllers.user;

import com.luizmatias.findadev.api.dtos.mappers.UserDTOMapper;
import com.luizmatias.findadev.api.dtos.requests.UpdateUserDTO;
import com.luizmatias.findadev.api.dtos.responses.UserDTO;
import com.luizmatias.findadev.domain.entities.User;
import com.luizmatias.findadev.domain.exceptions.ResourceNotFoundException;
import com.luizmatias.findadev.domain.usecases.user.DeleteUserInteractor;
import com.luizmatias.findadev.domain.usecases.user.GetAllUsersInteractor;
import com.luizmatias.findadev.domain.usecases.user.GetUserInteractor;
import com.luizmatias.findadev.domain.usecases.user.UpdateUserInteractor;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(UserController.USERS_PATH)
public class UserController {

    public static final String USERS_PATH = "/users";

    final GetAllUsersInteractor getAllUsersInteractor;
    final GetUserInteractor getUserInteractor;
    final UpdateUserInteractor updateUserInteractor;
    final DeleteUserInteractor deleteUserInteractor;

    public UserController(
            GetAllUsersInteractor getAllUsersInteractor,
            GetUserInteractor getUserInteractor,
            UpdateUserInteractor updateUserInteractor,
            DeleteUserInteractor deleteUserInteractor
    ) {
        this.getAllUsersInteractor = getAllUsersInteractor;
        this.getUserInteractor = getUserInteractor;
        this.updateUserInteractor = updateUserInteractor;
        this.deleteUserInteractor = deleteUserInteractor;
    }

    @GetMapping(path = {"/", ""})
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = getAllUsersInteractor.getAllUsers().stream().map(UserDTOMapper::toUserDTO).toList();
        return ResponseEntity.ok(users);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") @NotNull @Min(1) @Max(Long.MAX_VALUE) Long id) throws ResourceNotFoundException {
        User user = getUserInteractor.getUser(id);
        return ResponseEntity.ok(UserDTOMapper.toUserDTO(user));
    }

    @Transactional
    @PutMapping(path = "/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("id") @NotNull @Min(1) @Max(Long.MAX_VALUE) Long id, @RequestBody @Valid UpdateUserDTO updateUserDTO) throws ResourceNotFoundException {
        User user = updateUserDTO.toUser();
        user = updateUserInteractor.updateUser(id, user);
        return ResponseEntity.ok(UserDTOMapper.toUserDTO(user));
    }

    @Transactional
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable("id") @NotNull @Min(1) @Max(Long.MAX_VALUE) Long id) throws ResourceNotFoundException {
        User user = getUserInteractor.getUser(id);
        deleteUserInteractor.deleteUser(user);
        return ResponseEntity.ok(UserDTOMapper.toUserDTO(user));
    }

}