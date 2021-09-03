package com.tenancy.MultitenantJPAandJdbcTemplate.AplicationLayer.JDBC.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.tenancy.MultitenantJPAandJdbcTemplate.MultitenantJpaAndJdbcTemplateApplication;
import com.tenancy.MultitenantJPAandJdbcTemplate.AplicationLayer.JDBC.Dao.UserDAO;
import com.tenancy.MultitenantJPAandJdbcTemplate.AplicationLayer.JDBC.Model.User;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("multitenant")

public class UserController<HttpResponse>{

    @Autowired
    private UserDAO userDao;


    @GetMapping("users/{id}")
	public ResponseEntity<List<User>> getArticleById(@PathVariable("id") Integer id) {
        System.out.println("the id "+id);
		List<User> user = userDao.getAllUsers();
		return new ResponseEntity<List<User>>(user, HttpStatus.OK);
    }
    
    @GetMapping("/restart")
    void restart() throws UnsupportedOperationException, IOException {



      Thread restartThread = new Thread(() -> {
        try {
            Thread.sleep(1000);
            MultitenantJpaAndJdbcTemplateApplication.restart();
        } catch (InterruptedException ignored) {
        }
    });
    restartThread.setDaemon(false);
    restartThread.start();



  }

}