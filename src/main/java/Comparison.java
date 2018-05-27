public class Comparison {
	Integer[][] C;
	String[] Origin;
	String[] Compared;

    Comparison() {
    	Origin[0] = "0" + "MAJSIEJX";
    	Compared[0] = "0" + "MAJZZEDI";
    	String X = Origin[0];
        String Y = Compared[0];
        C = new Integer[X.length()][Y.length()];

    }

    /**
     *
     * @param X
     * @param Y
     * @return
     */
    public int lcsLength(Integer C[][], String X, String Y) {
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

    public boolean lineIsEqual(String X, String Y) {
    	if(lcsLength(C,X,Y)==X.length()) {
    		return true;
    	} else return false;
    }

    public static void main(String[] args) {

        Comparison comp = new Comparison();
        

        //System.out.println();
    }
}
