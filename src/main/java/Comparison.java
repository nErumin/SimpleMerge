public class Comparison {

    private int a;

    Comparison() {

    }

    /**
     *
     * @param X
     * @param Y
     * @return
     */
    public int lscLength(Integer C[][], String X, String Y) {
        int i, j;
        //Integer[][] C = new Integer[X.length()][Y.length()];

        for (i = 0; i < X.length(); i++) {
            C[i][0] = 0;
        }
        for (j = 0; j < Y.length(); j++) {
            C[0][j] = 0;
        }

        for (i = 1; i < X.length(); i++) {
            for (j = 1; j < Y.length(); j++) {
                if (X.charAt(i) == Y.charAt(j)) {
                    C[i][j] = C[i - 1][j - 1] + 1;
                } else {
                    C[i][j] = ((C[i][j - 1] > C[i - 1][j]) ? C[i][j - 1] : C[i - 1][j]);
                }
            }
        }
        return C[X.length() - 1][Y.length() - 1];
    }

    /**
     * @param C
     * @param X
     * @param Y
     * @param i
     * @param j
     */
    public void printDiff(Integer C[][], String X, String Y, int i, int j) {
        if (i > 0 && j > 0 && X.charAt(i) == Y.charAt(j)) {
            printDiff(C, X, Y, i - 1, j - 1);
            System.out.println(" " + X.charAt(i));
        } else if (j > 0 && (i == 0 || C[0][i - 1] >= C[i - 1][j])) {
            printDiff(C, X, Y, i, j - 1);
            System.out.println("+ " + Y.charAt(j));
        } else if (i > 0 && (j == 0 || C[i][j - 1] < C[i - 1][j])) {
            printDiff(C, X, Y, i - 1, j);
            System.out.println("- " + X.charAt(i));
        } else {
            System.out.println("");
        }
    }

    /**
     * @param C: C[0..m,0..n]
     * @param X: X[1..m]
     * @param Y: Y[1..n]
     * @param i: location
     * @param j: location
     * @return
     */
    public String backTrackAll(Integer C[][], String X, String Y, int i, int j) {
        if (i == 0 || j == 0) {
            return "";
        } else if (X.charAt(i) == Y.charAt(j)) {
            return ""; ////
        } else {
            String R = "";
            if (C[i][j - 1] >= C[i - 1][j]) {
                R += backTrackAll(C, X, Y, i, j - 1).toString();
            }
            if (C[i - 1][j] >= C[i][j - 1]) {
                R += backTrackAll(C, X, Y, i - 1, j).toString();
            }
            return R;
        }
    }

    public static void main(String[] args) {

        String X = "0" + "XMJYAUZ";
        String Y = "0" + "MZJAWXU";
        Integer[][] C = new Integer[X.length()][Y.length()];

        Comparison comp = new Comparison();
        int a = comp.lscLength(C, X,Y);
        System.out.println(a);
        comp.printDiff(C,X,Y,1,1);


        //System.out.println();
    }
}
