package com.HybernatePart3Assignment.jpa3.Hybernate3Assignment.Controller;


import com.HybernatePart3Assignment.jpa3.Hybernate3Assignment.Repository.AuthorManyToManyRepository;
import com.HybernatePart3Assignment.jpa3.Hybernate3Assignment.Services.ManyToManyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ManyToManyController {
    @Autowired
    AuthorManyToManyRepository authorManyToManyRepository;

    @Autowired
    ManyToManyService manyToManyService;

    @GetMapping("/manytomany")
    public void createSampleData()
    {
        manyToManyService.createData();
    }

}
/*
///////////////OUTPUT//////////////////////////
mysql> select *from author_many_to_many ;
+----+------------------+
| id | name             |
+----+------------------+
| 88 | Apoorva Garg     |
| 91 | Shubhanshi Tyagi |
+----+------------------+

 */