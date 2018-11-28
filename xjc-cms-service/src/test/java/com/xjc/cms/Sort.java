package com.xjc.cms;

import java.util.*;

/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * <p>
 * Created by xiangjiangcheng on 2018/11/28 15:27.
 */
public class Sort {

    public static void main(String[] args) {
        // removeArrayRepeat();
        // removeListRepeat();
        sort1();
        sort2();
    }


    public static void removeArrayRepeat() {
        Integer[] arr = {1, 2, 3, 4, 2, 5, 3};

        Integer[] lastArr = new Integer[arr.length];
        int count = 1;
        if (arr.length == 1) {
            lastArr[count] = arr[0];
        } else {
            lastArr[0] = arr[0];
            count++;

            for (int i = 1; i < arr.length; i++) {
                Boolean existFlag = false;
                for (int j = 0; j < count; j++) {
                    if (arr[i] == lastArr[j]) {
                        existFlag = true;
                        break;
                    }
                }
                if (!existFlag) {
                    lastArr[count - 1] = arr[i];
                    count++;
                }

            }
        }


        System.out.println("result:" + Arrays.toString(lastArr));
    }

    public static void removeListRepeat() {

        List<Integer> list = new ArrayList<>();
        list.add(4);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(2);

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < list.size(); i++) {
            set.add(list.get(i));
        }
        System.out.println(":" + set.toArray());
    }


    /**
     * 选择排序
     */
    public static void sort1() {
        int[] a = {1,3,4,2,10,8};
        int temp;
        for (int i = 0; i < a.length; i++) {
            for (int j = i; j < a.length; j++) {
                if (a[i] > a[j]) {
                    // 交换位置
                    temp = a[j];
                    a[j] = a[i];
                    a[i] = temp;
                 }
            }
        }
        System.out.println("冒泡排序：" + Arrays.toString(a));
    }

    /**
     * 选择排序 优化
     *
     * 每次循环，找到最小的放到最前面
     */
    public static void sort2() {
        int[] a = {1,3,4,2,10,8};
        
        // 下标
        int index = 0;
        // 临时值
        int temp;
        for (int i = 0; i < a.length; i++) {

            for (int j = i; j < a.length; j++) {
                if (a[i] > a[j]) {
                    // 记住下标
                    index = j;
                }
            }
            // 交换位置
            temp = a[index];
            a[index] = a[i];
            a[i] = temp;
            
        }
        System.out.println("选择排序：" + Arrays.toString(a));
    }

    /**
     * 冒泡排序
     *
     * 每两个比较 前一个比后一个大，则交换位置。每次循环会找到一个最小的放在后面，
     */
    private static void  sort3() {
        int[] a = {41, 23, 89, 24, 46, 30, 40 ,90, 2};

        // 交换
        int temp;

        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < a.length - 1 -i; j++) {
                if (a[j] > a[j + 1]) {
                    // 前一个比后一个大 交换
                    temp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = temp;
                }
            }
        }
        System.out.println("冒泡排序:" + Arrays.toString(a));
    }

    /**
     * 快速排序
     */

}
