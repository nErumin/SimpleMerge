public class Comparison {

    private String X, Y;
    private int i_for, j_for;
    private Integer[][] c;

    Comparison(){
        X = "0" + "ACAYKP";
        Y = "0" + "CAPCAK";
        c = new Integer[X.length()][Y.length()];

    }
    public int lscLength(){
        for(i_for = 0; i_for < X.length(); i_for++){
            c[i_for][0] = 0;
        }
        for(j_for = 0; j_for < Y.length(); j_for++){
            c[0][j_for] = 0;
        }

        for(i_for = 1; i_for < X.length(); i_for++){
            for(j_for = 1; j_for < Y.length(); j_for++){
                if(X.charAt(i_for) == Y.charAt(j_for)){
                    c[i_for][j_for] = c[i_for-1][j_for-1] + 1;
                }
                else {
                c[i_for][j_for] = ((c[i_for][j_for - 1] > c[i_for - 1][j_for]) ? c[i_for][j_for - 1] : c[i_for - 1][j_for]);
                }
            }
        }
        return c[X.length() - 1][Y.length() - 1];
    }

    public void printDiff(Integer c[][], String X, String Y, int i, int j){
        if(i > 0 && j > 0 && X.charAt(i) == Y.charAt(j)){
            printDiff(c, X, Y, i-1, j-1);
            System.out.print(" " + X.charAt(i));
        }
        else if(j > 0 && (i == 0 || c[0][i-1] >= c[i-1][j])){
            printDiff(c, X, Y, i, j-1);
            System.out.print("+ " + Y.charAt(j));
        }
        else if(i > 0 && (j ==0 || c[i][j-1] < c[i-1][j])) {
            printDiff(c, X, Y, i-1, j);
            System.out.print("- " + X.charAt(i));
        }
        else{
            System.out.println("");
        }
    }
}

