package com.RestAssignment2.rest.webservices.RestAssignment2.Controller;

import com.RestAssignment2.rest.webservices.RestAssignment2.MyClasses.User;
import com.RestAssignment2.rest.webservices.RestAssignment2.Services.UserServices;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
public class HelloMessageController {

    @Autowired
    MessageSource messageSource;
    @Autowired
    UserServices userServices;

    @ApiOperation(value = "Internationalize with a morning message.")
    @GetMapping("/i18nDemos/{id}")
    public String shows(@PathVariable int id, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        User usersModel = userServices.getOneUser(id);
        String param[] = {usersModel.getName()};
        return messageSource.getMessage("good.morning.message", param, locale);
    }


    @ApiOperation(value = "Internationalize to a particular user.")
    @GetMapping("/i18nDemo/{id}")
    public String show(@PathVariable int id, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        User user = userServices.getOneUser(id);
        String param[] = {user.getName()};
        return messageSource.getMessage("user.name", param, locale);
    }

    @ApiOperation(value = "To print a welcome message.")
    @GetMapping("/hello")
    public String print() {
        return "Hey how are you!!!!";
    }


    @ApiOperation(value = "to get all users.")
    @GetMapping("/alluser")
    public List<User> getAllUser() {
        return userServices.getAll();
    }

    @ApiOperation(value = "To filter all values except id and name.")
    @GetMapping("/filtering")
    public MappingJacksonValue dFilter() {
        List<User> list = userServices.getAll();
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name");
        FilterProvider filters = new SimpleFilterProvider().addFilter("ignoring", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(list);
        mapping.setFilters(filters);
        return mapping;
    }

    @ApiOperation(value = "To add a user.")
    @PostMapping("/users")
    public void addUser(@RequestBody User user) {
        User user1 = userServices.addUser(user);
    }

    @ApiOperation(value = "To delete a user, and also link to all employees")
    @GetMapping("/users/delete/{id}")
    public EntityModel<User> deleteOne(@PathVariable int id) {
        User user = userServices.deleteUser(id);
        EntityModel<User> entityModel = new EntityModel<>(user);
        WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUser());
        entityModel.add(link.withRel("All-Employees"));
        return entityModel;
    }
}
