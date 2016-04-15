package com.asv.edu.sort.fork;

import java.util.concurrent.RecursiveAction;

/**
 * @author alexandrov
 * @since 15.04.2016
 */
public class MergeSortActon extends RecursiveAction {

    private int[] up;
    private int[] down;
    private int left;
    private int right;

    public int[] result;

    public MergeSortActon(int[] up, int[] down, int left, int right) {
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
    }

    @Override
    protected void compute() {
        if (left == right) {
            down[left] = up[left];
            result = down;
            return;
        }

        int middle = (int) ((left + right) * 0.5);

        // разделяй и сортируй
        MergeSortActon lAction = new MergeSortActon(up, down, left, middle);
        MergeSortActon rAction = new MergeSortActon(up, down, middle + 1, right);


        invokeAll(lAction, rAction);


        int[] l_buff = lAction.result;
        int[] r_buff = rAction.result;

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
        this.result = target;
    }

}
