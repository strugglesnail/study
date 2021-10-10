package com.strugglesnail.study.hashCode;

import java.util.*;

/**
 * @auther strugglesnail
 * @date 2020/10/31 11:02
 * @desc
 */
public class HashCode {

    /**
     * 计算hashCode值
     * @param str 字符串
     * @param multiplier 扰动因子
     * @return
     */
    public static Integer hashCode(String str, Integer multiplier) {
        int hash = 0;
        for (int i = 0; i < str.length(); i++) {
            hash = multiplier * hash + str.charAt(i); // 字符的ASCII值
        }
        return hash;
    }

    /**
     * 计算Hash碰撞概率
     * @param multiplier 扰动因子
     * @param hashCodeList hashCode列表
     * @return
     */
    private static RateInfo hashCollisionRate(Integer multiplier, List<Integer> hashCodeList) {
        int maxHash = hashCodeList.stream().max(Integer::compareTo).get(); // 最大hashCode值
        int minHash = hashCodeList.stream().min(Integer::compareTo).get(); // 最小hashCode值

        int collisionCount = (int) (hashCodeList.size() - hashCodeList.stream().distinct().count()); // 碰撞数量（hashCode相等的数量）
        double collisionRate = (collisionCount * 1.0) / hashCodeList.size(); // 碰撞率

        return new RateInfo(maxHash, minHash, multiplier, collisionCount, collisionRate);
    }

    /**
     * 模拟扰动因子的碰撞概率
     * @param strList   存储的数据
     * @param multipliers 扰动因子
     * @return
     */
    public static List<RateInfo> collisionRateList(Set<String> strList, Integer... multipliers) {
        List<RateInfo> rateInfoList = new ArrayList<>();
        for (Integer multiplier : multipliers) {
            List<Integer> hashCodeList = new ArrayList<>();
            for (String str : strList) {
                Integer hashCode = hashCode(str, multiplier);
                hashCodeList.add(hashCode);
            }
            rateInfoList.add(hashCollisionRate(multiplier, hashCodeList));
        }
        return rateInfoList;
    }

    public static Map<Integer, Integer> hashArea(List<Integer> hashCodeList) {
        Map<Integer, Integer> statistics = new LinkedHashMap<>();
        int start = 0;
        for (long i = 0x80000000; i <= 0x7fffffff; i += 67108864) {
            long min = i;
            long max = min + 67108864;
            // 筛选出每个格子里的哈希值数量，java8流统计；https://bugstack.cn/itstack-demo-any/2019/12/10/%E6%9C%89%E7%82%B9%E5%B9%B2%E8%B4%A7-Jdk1.8%E6%96%B0%E7%89%B9%E6%80%A7%E5%AE%9E%E6%88%98%E7%AF%87(41%E4%B8%AA%E6%A1%88%E4%BE%8B).html
            int num = (int) hashCodeList.parallelStream().filter(x -> x >= min && x < max).count();
            statistics.put(start++, num);
        }
        return statistics;
    }

    public static Map<Integer, Integer> hashArea(Set<String> strList, Integer multiplier){
        List<Integer> hashCodeList = new ArrayList<>();
        for (String str : strList) {
            Integer hashCode = hashCode(str, multiplier);
            hashCodeList.add(hashCode);
        }
        return hashArea(hashCodeList);
    }
}
