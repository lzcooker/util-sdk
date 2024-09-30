package com.lzc.sdk.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//@SpringBootTest(classes = com.lzc.sdk.util.SortUtil.class)
@ExtendWith(MockitoExtension.class)
class SortUtilTests {


    @Test
    void sort() {
        int[] nums = new int[]{6, 3, 7, 4, 7, 9, 4, 1};

//        SortUtil.bubbleSort(nums);
//        SortUtil.shellSort(nums);
//        SortUtil.insertionSort(nums);
//        SortUtil.mergeSort(nums);
//        SortUtil.quickSort(nums);
        SortUtil.heapSort(nums);
//        SortUtil.selectionSort(nums);
        List<Integer> list = Arrays.stream(nums)
                .boxed() // 将 int 转换为 Integer
                .collect(Collectors.toList());
        System.out.println(list);
    }

}
