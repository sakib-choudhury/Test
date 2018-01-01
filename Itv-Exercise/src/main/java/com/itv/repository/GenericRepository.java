package com.itv.repository;

import java.util.List;

/**
 * Created by sakibchoudhury on 30/12/17.
 */
public interface GenericRepository {

    <T> T insert(T t);

    <T> T update(T t);

    <T> List<T> batchInsert(List<T> entityList);

}
