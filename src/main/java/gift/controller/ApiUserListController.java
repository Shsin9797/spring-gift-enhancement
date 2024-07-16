package gift.controller;

import gift.dto.UserResponseDto;
import gift.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApiUserListController {
    UserService userService;

    public ApiUserListController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/list")
    public List<UserResponseDto> UserList() {
        return userService.getAll();
    }
}