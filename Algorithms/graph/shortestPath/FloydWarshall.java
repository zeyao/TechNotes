

public class FloydWarshall {

    //input connection will be like [0,1,40],[0,2,34] means con[0] -> con[1] and distance is con[2]
    public int[][] shortestPath(int[][] connections, int n) {
        int[][] mat = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    mat[i][j] = 0;
                }
                else {
                    mat[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        for (int[] con : connections) {
            mat[con[0]][con[1]] = con[2];
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0 ; i < n; i++) {
                for (int j = 0 ; j < n; j++) {
                    long sum = (long)mat[i][k] + (long)mat[k][j];
                    if (sum > (long)Integer.MAX_VALUE) { //handle int overflow;
                        continue;
                    }
                    mat[i][j] = Math.min(mat[i][j], mat[i][k]+ mat[k][j]);
                }
            }
        }
        return mat;
    }

    public static void main(String[] args) {
        FloydWarshall fw = new FloydWarshall();
        int[][] connections = new int[][]{{0,1,2},{1,2,3},{0,2,6}};
        int[][] mat = fw.shortestPath(connections, 3);
        for (int i = 0 ; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                System.out.println(i + " -> " + j + " : " + mat[i][j]);
            }
        }
    }
}