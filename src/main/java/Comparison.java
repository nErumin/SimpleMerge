public class Comparison {
	private Integer[][] C;
    private String[] Origin;
    private String[] Compared;
    private int[][] sameLineIndex = new int [2][];
    private int partition;

    Comparison() {
    	//Origin[0] = "MAJSIEJX";
    	//Compared[0] = "MAJZZEDI";
        partition = 0;

    }

    /**
     *
     * @param X: Left Panel
     * @param Y: Right Panel
     * @return LCS Length
     */
    public int lcsLength(String X, String Y) {
        int i, j;

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
     *
     * @param X
     * @param Y
     * @return Whether the two line are same
     */
    public boolean lineIsEqual(String X, String Y) {
    	if((X.length() == Y.length()) && (lcsLength(X,Y)==X.length())) {
    		return true;
    	} else return false;
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



    public static void main(String[] args) {
        Comparison c = new Comparison();
        String X = "";
        String Y = "321456789";
        boolean result = c.lineIsEqual(X, Y);

        //System.out.println();
    }
}
