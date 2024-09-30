package com.lzc.sdk;

import org.dromara.hutool.core.collection.CollUtil;
import org.dromara.hutool.core.collection.ListUtil;
import org.dromara.hutool.core.comparator.CompareUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class UtilSdkApplicationTests {


    @Test
    void contextLoads() {
    }

    @Test
    void sort() {
        List< String > list = ListUtil.of("banana", "apple", "orange");
        List < String > sortedList = CollUtil.sort(list, CompareUtil.natural());
        System.out.println(sortedList);
    }

}
