package com.asv.edu.sort;

import com.asv.edu.sort.fork.MergeSortActon;

import java.util.concurrent.ForkJoinPool;

/**
 * @author alexandrov
 * @since 15.04.2016
 */
public class Sort {

    /**
     * Сортирует массив, используя рекурсивную сортировку слиянием
     * up - указатель на массив, который нужно сортировать
     * down - указатель на массив с, как минимум, таким же размером как у 'up', используется как буфер
     * left - левая граница массива, передайте 0, чтобы сортировать массив с начала
     * right - правая граница массива, передайте длину массива - 1, чтобы сортировать массив до последнего элемента
     * возвращает: указатель на отсортированный массив. Из-за особенностей работы данной реализации
     * отсортированная версия массива может оказаться либо в 'up', либо в 'down'
     **/
    public static int[] merge_sort(int[] up, int[] down, int left, int right) {
        if (left == right) {
            down[left] = up[left];
            return down;
        }

        int middle = (int) ((left + right) * 0.5);

        // разделяй и сортируй
        int[] l_buff = merge_sort(up, down, left, middle);
        int[] r_buff = merge_sort(up, down, middle + 1, right);

        // слияние двух отсортированных половин
        int[] target = l_buff == up ? down : up;

        int width = right - left, l_cur = left, r_cur = middle + 1;
        for (int i = left; i <= right; i++) {
            if (l_cur <= middle && r_cur <= right) {
                if (l_buff[l_cur] < r_buff[r_cur]) {
                    target[i] = l_buff[l_cur];
                    l_cur++;
                } else {
                    target[i] = r_buff[r_cur];
                    r_cur++;
                }
            } else if (l_cur <= middle) {
                target[i] = l_buff[l_cur];
                l_cur++;
            } else {
                target[i] = r_buff[r_cur];
                r_cur++;
            }
        }
        return target;
    }
    
    public static int[] forkJoinSort(int[] up, int[] down, int left, int right) {
        MergeSortActon mergeSortActon = new MergeSortActon(up, down, left, right);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(mergeSortActon);

        return mergeSortActon.result;
    }

}
