package ru.aryukov.testcase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.aryukov.testcase.dao.SourceStatistic;
import ru.aryukov.testcase.dto.SourceDTO;
import ru.aryukov.testcase.repository.SourceRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SourceServiceImpl implements SourceService {
    private SourceRepository sourceRepository;
    @PersistenceContext
    private EntityManager em;

    @Autowired
    public SourceServiceImpl(SourceRepository sourceRepository) {
        this.sourceRepository = sourceRepository;
    }

    @Override
    public List<SourceDTO> makePivotTable(String row, String col) {

        String firstPart = "SELECT sd.rowid as id, sd.";
        String secondPart = " as row_r, sd.";
        String therdPart = " as col_r, count(*) as val_r FROM source_data as sd GROUP BY sd.";
        String fourthPart = ", sd.";
        Query query = em.createNativeQuery(firstPart + row + secondPart + col + therdPart + row + fourthPart + col, SourceStatistic.class);
        List<SourceStatistic> resultList = query.getResultList();
        List<SourceDTO> collect = resultList.stream().map(source -> SourceDTO.builder()
                .row(source.getCol_r())
                .col(source.getRow_r())
                .val(source.getVal_r())
                .build()).collect(Collectors.toList());
        return collect;
    }
}
