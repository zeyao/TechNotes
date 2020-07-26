import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class FindRectangle {
    
    /** 给一个坐标里面有且只有一个长方体，找到这个长方体左上角坐标和长，宽
        {
        {1,1,1,1,1,1},
        {1,0,0,0,1,1},
        {1,0,0,0,1,1},
        {1,1,1,1,1,1},
        {1,1,1,1,1,1}
        }
        那么返回{1,1,3,2}  坐标为（1,1），长为3，高为2

        **/
   
    public static List<Integer> find1(int[][] mat) {
        for (int i = 0 ; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == 0) {
                    return findLength1(i, j, mat);
                }
            }
        }
        return new ArrayList<>();
    }

    private static List<Integer> findLength1(int i, int j, int[][] mat) {
        List<Integer> res = new ArrayList<>();
        res.add(i);
        res.add(j);
        int l = 0;
        int w = 0;
        while (j < mat[0].length && mat[i][j] == 0) {
            j++;
            l++;
        }
        j--;
        while (i < mat.length && mat[i][j] == 0) {
            w++;
            i++;
        }
        i--;
        res.add(l);
        res.add(w);
        return res;
    }

    /** 如果有多个正方体，返回他们
        {
        {1,1,1,1,1,1},
        {1,0,0,0,1,1},
        {1,0,0,0,1,1},
        {1,1,1,1,1,1},
        {1,1,1,1,1,1}
        }
        那么返回{1,1,3,2}  坐标为（1,1），长为3，高为2

**/
    public static List<List<Integer>> findMultiple(int[][] mat) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0 ; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == 0) {
                    List<Integer> list = findLength2(i, j, mat);
                    res.add(list);
                }
            }
        }
        return res;
    }

    private static List<Integer> findLength2(int i, int j, int[][] mat) {
        int startRow = i;
        int startCol = j;

        List<Integer> res = new ArrayList<>();
        res.add(i);
        res.add(j);
        int l = 0;
        int w = 0;
        while (j < mat[0].length && mat[i][j] == 0) {
            j++;
            l++;
        }
        j--;
        while (i < mat.length && mat[i][j] == 0) {
            w++;
            i++;
        }
        i--;
        res.add(l);
        res.add(w);

        for (int a = startRow; a <= i; a++) {
            for (int b = startCol; b <= j; b++) {
                mat[a][b] = 1;
            }
        }
        return res;
    }


        /** 返回他们左上右下坐标
        {
        {1,1,1,1,1,1},
        {1,1,0,0,1,1},
        {1,1,0,0,1,1},
        {1,1,1,1,1,1},
        {1,1,1,1,1,1}
        }
        那么返回{1,2,2,3}  坐标为（1,1），（2，3）
    
**/
    public static List<Integer> findCorner(int[][] mat) {
        for (int i = 0 ; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == 0) {
                    return findCornerHelper(i, j, mat);
                }
            }
        }
        return new ArrayList<>();
    }

    private static List<Integer>findCornerHelper(int i, int j, int[][] mat) {
        List<Integer> res = new ArrayList<>();
        res.add(i);
        res.add(j);
        while (i < mat.length && mat[i][j] == 0) {
            i++;
        }
        i--;
        while (j < mat[0].length && mat[i][j] == 0) {
            j++;
        }
        j--;
        res.add(i);
        res.add(j);
        return res;
    }

    // find multiple same, mark the 0 -> 1; if not allow to mark then use a Set mark as visited;
    
    public static void main(String[] args) {    
        int[][] arr = new int[][]{
            {1,1,1,1,1,1},
            {1,0,0,0,1,1},
            {1,0,0,0,1,1},
            {1,1,1,1,1,1},
            {1,1,1,1,1,1}
        };
        System.out.println(find1(arr));

        int[][] arr2 = new int[][]{
            {1,1,1,1,1,1},
            {1,0,0,0,0,0},
            {1,0,0,0,0,0},
            {1,1,1,1,1,1},
            {1,1,1,1,1,1}
        };
        System.out.println(find1(arr2));
        System.out.println(findMultiple(arr2));

        int[][] arr3 = new int[][]{
            {1,1,1,1,1,1},
            {1,0,0,0,0,0},
            {1,0,0,0,0,0},
            {1,1,1,1,1,1},
            {1,1,0,0,0,0}
        };
        System.out.println(findMultiple(arr3));

        System.out.println(findCorner(arr));
        int[][] arr4 = new int[][]{
            {1,1,1,1,1,1},
            {1,0,0,0,0,0},
            {1,0,0,0,0,0},
            {1,1,1,1,1,1},
            {1,1,1,1,1,1}
        };
        System.out.println(findCorner(arr4));
    }
}

