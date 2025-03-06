package com.example.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Course;


@Repository
public interface SelectRepository extends ElasticsearchRepository<Course , Integer>{
    List<Course> findByDetail(String detail , Pageable pageable);

    @Query("""
            {
            "query":{
                "match":{
                    "detail":{
                        "query" : "?0",,
                        "operator" : "or"
                    }
                }
            }
        }
        """)
    List<Course> findByKeywords(String detail , Pageable pageable);


}
