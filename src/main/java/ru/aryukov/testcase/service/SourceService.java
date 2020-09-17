package ru.aryukov.testcase.service;

import ru.aryukov.testcase.dto.SourceDTO;

import java.util.List;

public interface SourceService {

    List<SourceDTO> makePivotTable(String row, String col);
}
