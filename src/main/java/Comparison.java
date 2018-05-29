public class Comparison {
<<<<<<< HEAD
	Integer[][] C;
	String[] Origin;
	String[] Compared;
	int[][] sameLineIndex = new int [2][];
	int partition;
=======

    private int a;
>>>>>>> srs_requirement_wjh

    Comparison() {
    	Origin[0] = "0" + "MAJSIEJX";
    	Compared[0] = "0" + "MAJZZEDI";
    	String X = Origin[0];
        String Y = Compared[0];
        C = new Integer[X.length()][Y.length()];
        partition = 0;

    }

    /**
     *
     * @param X
     * @param Y
     * @return LCS Length
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

    /*
     *
     * @param X
     * @param Y
     * @return Whether the two line are same
     */
    public boolean lineIsEqual(String X, String Y) {
    	if(lcsLength(C,X,Y)==X.length()) {
    		return true;
    	} else return false;
    }

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

        Comparison comp = new Comparison();
        


        //System.out.println();
    }
}
