package com.wtf;

import com.strugglesnail.study.hashCode.FileUtils;
import com.strugglesnail.study.hashCode.HashCode;
import com.strugglesnail.study.hashCode.RateInfo;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    private Set<String> words;

    @Before
    public void before() {
//        "abc".hashCode();
        // 读取文件，103976个英语单词库.txt
        words = FileUtils.readWordList("D:\\work\\IDEA\\IdeaProject\\study\\src\\main\\resources\\103976个英语单词库.txt");
    }

    @Test
    public void test_collisionRate() {
        System.out.println("单词数量：" + words.size());
        List<RateInfo> rateInfoList = HashCode.collisionRateList(words, 2, 3, 5, 7, 17, 31, 32, 33, 39, 41, 199);
        for (RateInfo rate : rateInfoList) {
            System.out.println(String.format("乘数 = %4d, 最小Hash = %11d, 最大Hash = %10d, 碰撞数量 =%6d, 碰撞概率 = %.4f%%", rate.getMultiplier(), rate.getMinHash(), rate.getMaxHash(), rate.getCollisionCount(), rate.getCollisionRate() * 100));
        }
    }

    @Test
    public void test_hashArea() {
        System.out.println(HashCode.hashArea(words, 2).values());
        System.out.println(HashCode.hashArea(words, 7).values());
        System.out.println(HashCode.hashArea(words, 31).values());
        System.out.println(HashCode.hashArea(words, 32).values());
        System.out.println(HashCode.hashArea(words, 199).values());
    }

    @Test
    public void test_hashMap() {
        List<String> list = new ArrayList<>();
        list.add("jlkk");
        list.add("lopi");
        list.add("jmdw");
        list.add("e4we");
        list.add("io98");
        list.add("nmhg");
        list.add("vfg6");
        list.add("gfrt");
        list.add("alpo");
        list.add("vfbh");
        list.add("bnhj");
        list.add("zuio");
        list.add("iu8e");
        list.add("yhjk");
        list.add("plop");
        list.add("dd0p");
        for (String key : list) {
            int hash = key.hashCode() ^ (key.hashCode() >>> 16);
            System.out.println(
                    "字符串：" + key
                            + "\n\thashCode：" + Integer.toBinaryString(key.hashCode())
                            + "\n\tBit值：" + Integer.toBinaryString(hash)
                            + " \n\tIdx(16)：" + ((16 - 1) & hash) + " " + Integer.toBinaryString(hash & 16)
                            + " \n\tIdx(32)：" + ((32 - 1) & hash) + " " + Integer.toBinaryString((32 - 1) & hash)
            );
        }
    }
}
