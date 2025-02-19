package com.lzc.sdk.util;

/**
 * @author luzhicheng@cmss.chinamobile.com
 * @date 2024/10/9 11:28
 */
public class Knapsack {
    /**
     * 设第 i件物品体积为 w，价值为 v，根据第 i 件物品是否添加到背包中
     * @param W  体积
     * @param N  价值
     * @param weights
     * @param values
     * @return  dp[i][j] 表示前 i 件物品体积不超过 j 的情况下能达到的最大价值
     */
    public int knapsack(int W, int N, int[] weights, int[] values) {
        int[][] dp = new int[N + 1][W + 1];
        for (int i = 1; i <= N; i++) {
            int w = weights[i - 1], v = values[i - 1];
            for (int j = 1; j <= W; j++) {
                if (j >= w) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - w] + v);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[N][W];
    }

    public static void main(String[] args) {
        int W = 50; // 背包最大承重
        int N = 3; // 物品数量
        int[] weights = {10, 20, 30}; // 物品的重量
        int[] values = {60, 100, 120}; // 物品的价值

        Knapsack knapsack = new Knapsack();
        int maxValue = knapsack.knapsack(W, N, weights, values);
        System.out.println("最大价值: " + maxValue); // 期望输出 220
    }
}
