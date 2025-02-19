package com.lzc.sdk.util;

import java.util.Arrays;

/**
 * @author luzhicheng@cmss.chinamobile.com
 * @date 2024/9/30 10:36
 */
public class SortUtil {
    /*
    1、冒泡排序
     */
    public static void bubbleSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;

                }
            }
        }
    }

    /*
    2、堆排序
     */
    public static void heapSort(int[] nums) {
        buildMaxHeap(nums, nums.length);
        for (int i = nums.length - 1; i >= 0; i--) {
            int temp = nums[0];
            nums[0] = nums[i];
            nums[i] = temp;
            maxHeapify(nums, i, 0);
        }
    }

    private static void maxHeapify(int[] nums, int size, int index) {
        int leftIndex = 2 * index + 1;
        int rightIndex = leftIndex + 1;
        int largestIndex = index;
        if (leftIndex < size && nums[leftIndex] > nums[largestIndex]) {
            largestIndex = leftIndex;
        }
        if (rightIndex < size && nums[rightIndex] > nums[largestIndex]) {
            largestIndex = rightIndex;
        }
        if (largestIndex != index) {
            int temp = nums[index];
            nums[index] = nums[largestIndex];
            nums[largestIndex] = temp;
            maxHeapify(nums, size, largestIndex);
        }
    }

    private static void buildMaxHeap(int[] nums, int size) {
        for (int i = size / 2 - 1; i >= 0; i--) {
            maxHeapify(nums, size, i);
        }
    }


    /*
    3、插入排序
     */
    public static void insertionSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int current = nums[i];
            int j = i - 1;
            while (j >= 0 && current < nums[j]) {
                nums[j + 1] = nums[j];
                j--;
            }
            nums[j + 1] = current;
        }
    }

    /*
    4、归并排序
     */
    public static void mergeSort(int[] nums) {
        sort(nums, 0, nums.length - 1, new int[nums.length]);
    }

    private static void sort(int[] nums, int left, int right, int[] aux) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) / 2;
        sort(nums, left, mid, aux);
        sort(nums, mid + 1, right, aux);
        merge(nums, left, mid, right, aux);
    }

    private static void merge(int[] nums, int left, int mid, int right, int[] aux) {
        int auxIndex = left;
        int i = left, j = mid + 1;
        while (i <= mid || j <= right) {
            if (i > mid) {
                aux[auxIndex++] = nums[j++];
            } else if (j > right) {
                aux[auxIndex++] = nums[i++];
            } else if (nums[i] <= nums[j]) {
                aux[auxIndex++] = nums[i++];
            } else {
                aux[auxIndex++] = nums[j++];
            }
        }
        for (int k = left; k <= right; k++) {
            nums[k] = aux[k];
        }
    }

    /*
   4、快速排序
    */
    public static void quickSort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    private static void quickSort(int[] nums, int from, int to) {
        if (from >= to) {
            return;
        }
        int baseIndex = getPartitionIndex(nums, from, to);
        quickSort(nums, from, baseIndex - 1);
        quickSort(nums, baseIndex + 1, to);
    }

    private static int getPartitionIndex(int[] nums, int from, int to) {
        int baseIndex = from;
        int partitionIndex = baseIndex;
        for (int cursor = baseIndex + 1; cursor <= to; cursor++) {
            if (nums[cursor] <= nums[baseIndex]) {
                partitionIndex++;
                int temp = nums[partitionIndex];
                nums[partitionIndex] = nums[cursor];
                nums[cursor] = temp;
            }
        }
        int temp = nums[partitionIndex];
        nums[partitionIndex] = nums[baseIndex];
        nums[baseIndex] = temp;
        return partitionIndex;
    }

    /*
  5、选择排序
   */
    public static void selectionSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = nums[i];
            nums[i] = nums[minIndex];
            nums[minIndex] = temp;
        }
    }

    /*
 6、希尔排序
  */
    public static void shellSort(int[] nums) {
        int h = nums.length;
        while (h != 1) {
            h = h / 3 + 1;
            for (int i = h; i < nums.length; i++) {
                int current = nums[i];
                int j = i - h;
                while (j >= 0 && nums[j] > current) {
                    nums[j + h] = nums[j];
                    j = j - h;
                }
                nums[j + h] = current;
            }
        }
    }

    /*
 7、归并排序
  */
    public static void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            // 找到中点
            int middle = left + (right - left) / 2;

            // 递归排序左半部分
            mergeSort(array, left, middle);
            // 递归排序右半部分
            mergeSort(array, middle + 1, right);

            // 合并已排序的部分
            merge(array, left, middle, right);
        }
    }

    // 合并两部分的方法
    public static void merge(int[] array, int left, int middle, int right) {
        // 计算两个子数组的大小
        int n1 = middle - left + 1;
        int n2 = right - middle;

        // 创建临时数组
        int[] L = new int[n1];
        int[] R = new int[n2];

        // 将数据复制到临时数组
        for (int i = 0; i < n1; i++) {
            L[i] = array[left + i];
        }
        for (int j = 0; j < n2; j++) {
            R[j] = array[middle + 1 + j];
        }

        // 合并临时数组
        int i = 0, j = 0;
        int k = left;

        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                array[k] = L[i];
                i++;
            } else {
                array[k] = R[j];
                j++;
            }
            k++;
        }

        // 复制剩余元素
        while (i < n1) {
            array[k] = L[i];
            i++;
            k++;
        }
        while (j < n2) {
            array[k] = R[j];
            j++;
            k++;
        }
    }

}



