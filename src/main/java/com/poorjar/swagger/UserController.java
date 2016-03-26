package com.poorjar.swagger;

import com.poorjar.swagger.dataaccess.User;
import com.poorjar.swagger.dataaccess.UserRepository;
import io.swagger.annotations.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
@EnableAutoConfiguration
public class UserController {
    private static Logger LOGGER = Logger.getLogger(UserController.class);
    @Autowired
    private UserRepository userRepository;

    @ApiOperation(value = "getUsers", nickname = "getUsers")
    @RequestMapping(path = "/users/all", method = RequestMethod.GET, produces = "application/json")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success", response = List.class),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Failure")})
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    /**
     * Creates users with the provided info.
     *
     * @param users The users to be created.
     * @return Collection of newly created users.
     */
    @RequestMapping(value = "/users/create", method = RequestMethod.POST)
    public Collection<User> createUsers(@RequestBody Collection<User> users) {
        LOGGER.error(users);
        return userRepository.save(users);
    }


    /**
     * Deletes a specific user.
     *
     * @param userId The id of the user to be deleted.
     */
    @RequestMapping(path = "/users/delete", method = RequestMethod.DELETE)
    public void deleteUser(@RequestBody long userId) {
        LOGGER.error(userId);
        userRepository.delete(userId);
    }

}
