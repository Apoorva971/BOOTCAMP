package com.HybernatePart3Assignment.jpa3.Hybernate3Assignment.Controller;

import com.HybernatePart3Assignment.jpa3.Hybernate3Assignment.Entities.AuthorUnidirectional;
import com.HybernatePart3Assignment.jpa3.Hybernate3Assignment.Repository.AuthorUnidirectionalRepository;
import com.HybernatePart3Assignment.jpa3.Hybernate3Assignment.Services.UnidirectionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UnidirectionalController {
    @Autowired
    AuthorUnidirectionalRepository authorUnidirectionalRepository;

    @Autowired
    UnidirectionalService unidirectionalService;

    @GetMapping("/unidirectional")
    public void createSampleData() {
        AuthorUnidirectional authorUnidirectional = unidirectionalService.createData();
        authorUnidirectionalRepository.save(authorUnidirectional);

    }

}
/*
/////////////////////////////OUTPUT////////////////////////////////
mysql> select *from author_unidirectional;
+----+--------------+
| id | name         |
+----+--------------+
| 92 | Apoorva Garg |
+----+--------------+

 */