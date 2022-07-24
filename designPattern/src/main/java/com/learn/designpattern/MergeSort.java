package com.learn.designpattern;

import java.util.Arrays;

/**
 * 归并排序
 */
public class MergeSort {


    static int[] temp;

    public static  void sortView(int[] counts) {
        temp = new int[counts.length];
        sort(counts, 0, counts.length - 1);
        System.out.println(Arrays.toString(counts));
    }

    public static void sort(int[] array, int begin, int end) {
        if (begin == end) {
            return;
        }
        int mid = begin + (end - begin) / 2;
        sort(array, begin, mid);
        sort(array, mid + 1, end);
        merge(array, begin, mid, end);

        System.out.println(Arrays.toString(array));

    }

    private static void merge(int[] array, int lo, int mid, int end) {
        for (int i1 = lo; i1 <= end; i1++) {
            temp[i1] = array[i1];
        }

        int i = lo;
        int j = mid + 1;
        for (int p = lo; p <= end; p++) {
            // 左边的拍偶像
            if (i == mid + 1) {
                array[p] = temp[j++];
            } else if (j == end + 1) {
                array[p] = temp[i++];
            } else if (temp[j] < temp[i]) {
                array[p] = temp[j++];
            } else {
                array[p] = temp[i++];
            }
        }


    }

    public static void main(String[] args) {
        int[] arrys={1,5,3,2,4};
        sortView(arrys) ;
    }

}
