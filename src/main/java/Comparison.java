public class Comparison {
	Integer[][] C;
	String[] Origin;
	String[] Compared;
	int[][] sameLineIndex = new int [2][];
	int partition;

    private int a;


    Comparison() {
    	//Origin[0] = "MAJSIEJX";
    	//Compared[0] = "MAJZZEDI";
        partition = 0;

    }

    /**
     * Calculate LCS length
     * @param X: Left Panel String
     * @param Y: Right Panel String
     * @return LCS Length
     */
    public int lcsLength(String X, String Y) {
        int i, j;

        // Add 0(null) string for counting
        X = "0" + X;
        Y = "0" + Y;
        this.C = new Integer[X.length()][Y.length()];

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
     * After checking string, return True / False
     * @param X: Left Panel String
     * @param Y: Right Panel String
     * @return Whether the two line are same
     */
    public boolean lineIsEqual(String X, String Y) {
        // Check two strings using a lcsLength function
        // Two strings length should be same.
    	if((X.length() == Y.length()) && (lcsLength(X,Y) == X.length())) {
    		return true;
    	} else return false; // The other case, false
    }

    /**
     * Don't Care right now
     * It is not finished
     * @param nowOriginPos
     * @param nowComparedPos
     * @param X
     * @param Y
     */
    public void figureSameIndex(int nowOriginPos, int nowComparedPos, String[] X, String[] Y) {
    	int i=nowOriginPos,j=nowComparedPos;
    	if(this.lineIsEqual(X[i], Y[j])) {
    		sameLineIndex[0][partition] =  nowOriginPos;
        	while(this.lineIsEqual(X[i], Y[j])) {
        		i++;
        		j++;
        	}
        	sameLineIndex[1][partition] = i;
        	partition++;
    	}
    }

    /**
     * test for LCS by strings
     * */
    public int lcsPanelLength(String[] X, String[] Y) {
        // Add 0(null) string for counting
    	for(int i=X.length; i>=0; i++) {
    		X[i+1] = X[i];
    	}
    	X[0] = "0";
    	for(int i=X.length; i>=0; i++) {
    		Y[i+1] = Y[i];
    	}
    	Y[0] = "0";
        this.C = new Integer[X.length][Y.length];

        for (int i = 0; i < X.length; i++) {
            C[i][0] = 0;
        }
        for (int j = 0; j < Y.length; j++) {
            C[0][j] = 0;
        }

        for (int i = 1; i < X.length; i++) {
            for (int j = 1; j < Y.length; j++) {
                if (X[i] == Y[j]) {
                    C[i][j] = C[i - 1][j - 1] + 1;
                } else {
                    C[i][j] = ((C[i][j - 1] > C[i - 1][j]) ? C[i][j - 1] : C[i - 1][j]);
                }
            }
        }
    	return C[X.length - 1][Y.length - 1];
    }

    public static void main(String[] args) {
        Comparison c = new Comparison();
        String X = "asdf";
        String Y = "Computer";
        boolean result = c.lineIsEqual(X, Y);


        //System.out.println();
    }
}
