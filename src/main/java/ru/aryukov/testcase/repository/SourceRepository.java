package ru.aryukov.testcase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.aryukov.testcase.dao.Source;
import ru.aryukov.testcase.dao.SourceStatistic;

import java.util.List;

public interface SourceRepository extends JpaRepository<Source, Integer> {

    List<Source> findAllByTaxGroupA(String taxGroup);

//    @Query( "SELECT " +
//            "    new ru.aryukov.testcase.dao.SourceStatistic(ds.row, ds.col, COUNT(ds)) " +
//            "FROM " +
//            " source_data AS ds " +
//            "GROUP BY " +
//            "ds.districtRfC, ds.regionRfD")

}
