import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class FindIslands {
    
    public static List<List<String>> findIsland(int[][] mat) {
        List<List<String>> res = new ArrayList<>();
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == 0) {
                    List<String> island = new ArrayList<>();
                    dfs(i, j, mat, island);
                    res.add(island);
                }
            }
        }
        return res;
    }

    private static void dfs(int row, int col, int[][] mat, List<String> island) {
        if (row < 0 || row >= mat.length || col < 0 || col >= mat[0].length || mat[row][col] != 0) {
            return;
        }
        mat[row][col] = 1;
        island.add(col + "," + row);
        dfs(row+1, col, mat, island);
        dfs(row-1, col, mat, island);
        dfs(row, col+1, mat, island);
        dfs(row, col-1, mat, island);

    }
    
    public static void main(String[] args) {    
        int[][] arr = new int[][]{
            {1,1,1,1,1,1},
            {1,0,0,0,1,1},
            {1,0,0,0,1,1},
            {1,1,0,1,1,1},
            {1,1,1,1,1,0}
        };
        System.out.println(findIsland(arr));

        int[][] arr2 = new int[][]{
            {1,1,1,1,1,1},
            {1,0,0,0,0,0},
            {1,0,0,0,0,0},
            {1,1,1,1,1,1},
            {1,1,1,1,1,1}
        };
        System.out.println(findIsland(arr2));


        int[][] arr3 = new int[][]{
            {1,1,1,1,1,1},
            {1,0,0,0,0,0},
            {1,0,0,0,0,0},
            {1,1,1,1,1,1},
            {1,1,0,0,0,0}
        };
        System.out.println(findIsland(arr3));

        int[][] arr4 = new int[][]{
            {1,1,1,1,1,1},
            {1,0,0,0,0,0},
            {1,0,0,0,0,0},
            {1,0,1,1,1,1},
            {1,1,1,0,0,0}
        };
        System.out.println(findIsland(arr4));
    }
}

