package com.HybernatePart3Assignment.jpa3.Hybernate3Assignment.Controller;

import com.HybernatePart3Assignment.jpa3.Hybernate3Assignment.Entities.AuthorBidirectional;
import com.HybernatePart3Assignment.jpa3.Hybernate3Assignment.Repository.AuthorBidirectionalRepository;
import com.HybernatePart3Assignment.jpa3.Hybernate3Assignment.Services.BidirectionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BidirectionalController {
    @Autowired
    AuthorBidirectionalRepository authorBidirectionalRepository;

    @Autowired
    BidirectionalService bidirectionalService;

        @GetMapping("/bidirectional")
    public void createSampleData() {
        AuthorBidirectional authorBidirectional = bidirectionalService.createData();
        authorBidirectionalRepository.save(authorBidirectional);
    }

}
/*
////////////////////////OUTPUT//////////////////////
mysql> select *from author_bidirectional;;
+----+--------------+
| id | name         |
+----+--------------+
| 83 | Apoorva Garg |
+----+--------------+

 */