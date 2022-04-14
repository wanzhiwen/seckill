package com.wzw.seckill.utils;

import java.util.*;
import java.util.concurrent.locks.LockSupport;

/**
 * @author wanzhiwen
 * @Date 2022/3/31
 */
public class Test {

    public static boolean canReorderDoubled(int[] arr) {
        if(arr.length == 1 || arr.length == 0) return false;
        Arrays.sort(arr);
        int len = arr.length;
        if(len % 2 == 1) len--;
        Deque<Integer> queue = new LinkedList<>();
        int count = 0;
        while(count < len && arr[count] < 0) count++;
        int tmp = count;
        while(count < len){
            if(queue.isEmpty()){
                queue.add(arr[count] * 2);
            }else{
                int top = queue.peek();
                if(arr[count] == top){
                    queue.remove();
                }else if(arr[count] > top) return false;
                else queue.add(arr[count] * 2);
            }
            count++;
        }
        //return queue.isEmpty();
        if(!queue.isEmpty()) return false;
        count = tmp - 1;
        while(count >= 0){
            if(queue.isEmpty()){
                queue.add(arr[count] * 2);
            }else{
                int top = queue.peek();
                if(arr[count]== top){
                    queue.remove();
                }else if(arr[count] < top) return false;
                else queue.add(arr[count] * 2);
            }
            count--;
        }
        return queue.isEmpty();
    }

    public static void main(String[] args){
        Object o = new Object();
        LockSupport.park();
        int[] test = {2,1,2,6};
        System.out.println(canReorderDoubled(test));

    }
}
