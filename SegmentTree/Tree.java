package SegmentTree;

import java.util.Scanner;

public class Tree {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        int[] arr = {1, 5, 7, 3, 4, 6,9, 10, 23, 8};

        int n = arr.length;
        int[] seg = new int[4 * n];
        int[] lazy = new int[4 * n];

        build(seg, arr, 0, 0, arr.length-1);

        int q = sc.nextInt();

        while(q-- != 0){
            int type = sc.nextInt();

            if(type == 1){
                int l = sc.nextInt();
                int r = sc.nextInt();
                System.out.println(query(seg, 0, 0, n-1, l, r));
            }
            else if(type == 2){
                int pos = sc.nextInt();
                int val = sc.nextInt();
                updatePos(seg, 0, 0, n-1, pos, val);
            }else if(type == 3){
                int l = sc.nextInt();
                int r = sc.nextInt();
                int val = sc.nextInt();
                update(seg, lazy, 0, 0, n-1, l, r, val);
            }
        }


    }

    public static void build(int[] seg, int[] arr, int index, int low, int high){
        if(low == high){
            seg[index] = arr[low];
            return; 
        }
        int mid = (low + high )/ 2;
        build(seg, arr, 2*index + 1, low, mid);
        build(seg, arr, 2*index + 2, mid+1, high);
        seg[index] = Math.max(seg[2*index + 1], seg[2*index + 2]);
    }

    static int query(int[] seg, int index, int low, int high, int l, int r){
        if(low > r || high < l){
            return Integer.MIN_VALUE;
        }
        if(low >= l && high <= r){
            return seg[index];
        }
        int mid = (low + high) / 2;
        int left = query(seg, 2*index+1, low, mid, l, r);
        int right = query(seg, 2*index+2, mid+1, high, l, r);

        return Math.max(left, right);
    }

    static void push(int[] seg, int[] lazy, int index, int low, int high){
        if(lazy[index] != 0){
            seg[index] += lazy[index];

            if(low != high){
                lazy[2*index + 1] += lazy[index];
                lazy[2*index + 2] += lazy[index];
            }

            lazy[index] = 0;
        }
    }

    static void update(int[] seg, int[] lazy, int index, int low, int high, int l, int r, int val){
        push(seg, lazy, index, low, high);

        if(low > r || high < l) return;

        if(low >= l && high <= r){
            lazy[index] += val;
            push(seg, lazy, index, low, high);
            return;
        }

        int mid = (low + high) / 2;
    
        update(seg, lazy, 2*index + 1, low, mid, l, r, val);
        update(seg, lazy, 2*index + 2, mid+1, high, l, r, val);
    
        seg[index] = Math.max(seg[2*index + 1], seg[2*index + 2]);
    }

    static void updatePos(int[] seg, int index, int low, int high, int pos, int val){
        if(low == high){
            seg[index] = val;
            return;
        }
    
        int mid = (low + high) / 2;
    
        if(pos <= mid){
            updatePos(seg, 2*index + 1, low, mid, pos, val);
        } else {
            updatePos(seg, 2*index + 2, mid+1, high, pos, val);
        }
    
        seg[index] = Math.max(seg[2*index + 1], seg[2*index + 2]);
    }
    
}
