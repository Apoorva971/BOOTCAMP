package com.HybernatePart3Assignment.jpa3.Hybernate3Assignment.Controller;

import com.HybernatePart3Assignment.jpa3.Hybernate3Assignment.Entities.AuthorWithoutTable;
import com.HybernatePart3Assignment.jpa3.Hybernate3Assignment.Repository.AuthorWithoutTableRepository;
import com.HybernatePart3Assignment.jpa3.Hybernate3Assignment.Services.WithoutTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WithoutTableController {

    @Autowired
    AuthorWithoutTableRepository authorWithoutTableRepository;

    @Autowired
    WithoutTableService withoutTableService;

    @GetMapping("/withouttable")
    public void createSampleData() {
        AuthorWithoutTable authorWithoutTable = withoutTableService.createData();
        authorWithoutTableRepository.save(authorWithoutTable);
    }

}
/*
/////////////////////////////////OUTPUT///////////////////////////
mysql> select *from author_without_table ;
+----+--------------+
| id | name         |
+----+--------------+
| 75 | Apoorva garg |
| 95 | Apoorva garg |
+----+--------------+

 */