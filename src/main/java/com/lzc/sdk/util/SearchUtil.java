package com.lzc.sdk.util;

import java.util.*;

/**
 * @author luzhicheng@cmss.chinamobile.com
 * @date 2024/9/30 10:36
 */
public class SearchUtil {
    /*
    1、二分查找
     */
    public static int binarySearch1(int[] arr, int len, int target) {
        /*初始化左右搜索边界*/
        int left = 0, right = len - 1;
        int mid;
        while (left <= right) {
            /*中间位置：两边界元素之和/2向下取整*/
            mid = (left + right) / 2;
            /*arr[mid]大于target，即要寻找的元素在左半边，所以需要设定右边界为mid-1，搜索左半边*/
            if (target < arr[mid]) {
                right = mid - 1;
                /*arr[mid]小于target，即要寻找的元素在右半边，所以需要设定左边界为mid+1，搜索右半边*/
            } else if (target > arr[mid]) {
                left = mid + 1;
                /*搜索到对应元素*/
            } else if (target == arr[mid]) {
                return mid;
            }
        }
        /*搜索不到返回-1*/
        return -1;
    }

    private void dfs(TreeNode root,int level,List<List<Integer>> results){
        //terminal 已下探到最底部节点
        if(results.size()==level){ // or root == null or node alread visited
            results.add(new ArrayList<>());
            return;
        }
        // process current level node here.
        results.get(level).add(root.val); // 记录当前节点已被访问

        //drill down   if node not visited
        if(root.left!=null){
            dfs(root.left,level+1,results);
        }
        if(root.right!=null){
            dfs(root.right,level+1,results);
        }
    }

    public List<List<Integer>> bfs(TreeNode root) {
        List<List<Integer>> allResults = new ArrayList<>();
        if (root == null) {
            return allResults;
        }
        Queue<TreeNode> nodes = new LinkedList<>();
        //将根节点入队列
        nodes.add(root);
        while (!nodes.isEmpty()) {
            //每次循环开始时：队列中的元素的个数其实就是当前这一层节点的个数
            int size = nodes.size();
            List<Integer> results = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                //从队列中取出每一个节点（取出这一层的每个节点）
                TreeNode node = nodes.poll();
                results.add(node.val);
                //将该节点的左右子节点入队列
                if (node.left != null) {
                    nodes.add(node.left);
                }
                if (node.right != null) {
                    nodes.add(node.right);
                }
            }
            allResults.add(results);
        }
        return allResults;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


}



