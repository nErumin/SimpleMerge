public class Comparison {
	Integer[][] c;
	String[] origin;
	String[] compared;
	int[][] sameLineIndex = new int [2][];
	int partition;

    private int a;


    Comparison() {
    	//origin[0] = "MAJSIEJx";
    	//compared[0] = "MAJZZEDI";
        partition = 0;

    }
    Comparison(int orgLength, int compLength) {
    	origin = new String[orgLength];
        compared = new String[compLength];
        
    }

    /**
     * calculate LcS length
     * @param x: Left Panel String
     * @param y: Right Panel String
     * @return LcS Length
     */
    public int lcsLength(String x, String y) {
        int i, j;

        // Add 0(null) string for counting
        x = "0" + x;
        y = "0" + y;
        this.c = new Integer[x.length()][y.length()];

        for (i = 0; i < x.length(); i++) {
            c[i][0] = 0;
        }
        for (j = 0; j < y.length(); j++) {
            c[0][j] = 0;
        }

        for (i = 1; i < x.length(); i++) {
            for (j = 1; j < y.length(); j++) {
                if (x.charAt(i) == y.charAt(j)) {
                    c[i][j] = c[i - 1][j - 1] + 1;
                } else {
                    c[i][j] = ((c[i][j - 1] > c[i - 1][j]) ? c[i][j - 1] : c[i - 1][j]);
                }
            }
        }
        return c[x.length() - 1][y.length() - 1];
    }

    /**
     * After checking string, return True / False
     * @param x: Left Panel String
     * @param y: Right Panel String
     * @return Whether the two line are same
     */
    public boolean lineIsEqual(String x, String y) {
        // check two strings using a lcsLength function
        // Two strings length should be same.
    	if((x.length() == y.length()) && (lcsLength(x,y) == x.length())) {
    		return true;
    	} else return false; // The other case, false
    }

    /**
     * Don't care right now
     * It is not finished
     * @param noworiginPos
     * @param nowcomparedPos
     * @param x
     * @param y
     */
    public void figureSameIndex(int noworiginPos, int nowcomparedPos, String[] x, String[] y) {
    	int i=noworiginPos,j=nowcomparedPos;
    	if (this.lineIsEqual(x[i], y[j])) {
    		sameLineIndex[0][partition] =  noworiginPos;
        	while (this.lineIsEqual(x[i], y[j])) {
        		i++;
        		j++;
        	}
        	sameLineIndex[1][partition] = i;
        	partition++;
    	}
    }

    /**
     * test for LcS by strings
     * */
    public int lcsPanelLength(String[] x, String[] y) {
        // Add 0(null) string for counting
    	for (int i = x.length-2; i >= 0; i--) {
    		x[i+1] = x[i];
    	}
    	x[0] = "0";
    	for (int i = x.length-2; i >= 0; i--) {
    		y[i+1] = y[i];
    	}
    	y[0] = "0";
        this.c = new Integer[x.length][y.length];

        for (int i = 0; i < x.length; i++) {
            c[i][0] = 0;
        }
        for (int j = 0; j < y.length; j++) {
            c[0][j] = 0;
        }

        for ( int i = 1; i < x.length; i++) {
            for (int j = 1; j < y.length; j++) {
                if (x[i] == y[j]) {
                    c[i][j] = c[i - 1][j - 1] + 1;
                    System.out.println(i + "&");
                } else {
                    c[i][j] = ((c[i][j - 1] > c[i - 1][j]) ? c[i][j - 1] : c[i - 1][j]);
                }
            }
        }
    	return c[x.length - 1][y.length - 1];
    }

    public static void main(String[] args) {
        Comparison c = new Comparison(8,8);
        String x = "asdf";
        String y = "computer";
        c.compared[0] = "Something";
        c.origin[0] = "Something";
        c.compared[1] = "Anything";
        c.origin[1] = "Something";
        c.compared[2] = "Somethingasdf";
        c.origin[2] = "Something";
        c.compared[3] = "Something";
        c.origin[3] = "Something";
        c.compared[4] = "Something3";
        c.origin[4] = "Something3";
        c.compared[5] = "Something1";
        c.origin[5] = "Something2";
        int b = c.lcsPanelLength(c.compared, c.origin);
        System.out.println(b);
        boolean result = c.lineIsEqual(x, y);


        //System.out.println();
    }
}
