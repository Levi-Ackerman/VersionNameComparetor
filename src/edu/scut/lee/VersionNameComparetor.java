package edu.scut.lee;

/**
 * Created by Administrator on 2016/1/25 025.
 */
public class VersionNameComparetor {
    public static final int INVALID_INPUT = Integer.MIN_VALUE;

    public static void main(String[] args) {
        assertTrue(Compare("4.20.2.9", "4.5.1") > 0);
        assertTrue(Compare("4.0.2.9", "4.0.2.9") == 0);
        assertTrue(Compare("4.2.2.9", "4.5.2.8") < 0);
        assertTrue(Compare("4.20.2.9", "7") < 0);
        assertTrue(Compare(null, "4.2.3.1") == INVALID_INPUT);
        assertTrue(Compare(null, null) == INVALID_INPUT);
        assertTrue(Compare("4a.2.9", null) == INVALID_INPUT);
        assertTrue(Compare("9", " ") == INVALID_INPUT);
        assertTrue(Compare(" ", " ") == INVALID_INPUT);
        assertTrue(Compare("4a.2.9", "7") == INVALID_INPUT);
        assertTrue(Compare("4.2.9", "c") == INVALID_INPUT);
        assertTrue(Compare("4.", "4.") == INVALID_INPUT);
        assertTrue(Compare("4.20.2.9", ".....") == INVALID_INPUT);
        assertTrue(Compare("4.20.2.9", "1.....") == INVALID_INPUT);
        assertTrue(Compare("4.20.2.", ".2....") == INVALID_INPUT);
        assertTrue(Compare("4..2.9", "1.2....") == INVALID_INPUT);
        assertTrue(Compare(".2.9", "1..3...") == INVALID_INPUT);
        assertTrue(Compare("4.20.2.9", "...5..8") == INVALID_INPUT);
    }

    public static int Compare(String versionName1, String versionName2) {
        if (versionName1 != null && versionName2 != null) {
            if (versionName1.length() > 0 && versionName2.length() > 0) {
                if (versionName1.charAt(versionName1.length() - 1) != '.' && versionName2.charAt(versionName2.length() - 1) != '.') {
                    //split会忽略掉字符串末尾的连续的“.”，因此要单独检测末尾为“.”的非法输入，中间连续为“.”的非法输入由Integer转化的时候来捕获
                    String[] names1 = versionName1.split("\\."); //这里不能用.分隔，不然只会返回空数组
                    String[] names2 = versionName2.split("\\.");
                    int count = Math.min(names1.length, names2.length);
                    for (int i = 0; i < count; i++) {
                        try {
                            int num1 = Integer.parseInt(names1[i]);
                            int num2 = Integer.parseInt(names2[i]);
                            if (num1 != num2) {
                                return num1 - num2;
                            }
                        } catch (Exception e) {
                            return INVALID_INPUT;
                        }
                    }
                    return names1.length - names2.length;
                }
            }
        }
        return INVALID_INPUT;
    }

    public static void assertTrue(boolean express) {
        System.out.println(express);
    }
}
