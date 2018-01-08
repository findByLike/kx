package com.example.demo.repository;

import com.example.demo.domain.Movie;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by Administrator on 2018-01-03.
 */
@Mapper
public interface MovieRepository {
    void saveMovie(Movie movie);
}
