public class Comparison {
	Integer[][] board;
	String[] origin;
	String[] compared;
	int[][] sameLineIndex = new int [2][];
	int partition;
	String[][] solution;


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
        this.board = new Integer[x.length()][y.length()];

        for (i = 0; i < x.length(); i++) {
            board[i][0] = 0;
        }
        for (j = 0; j < y.length(); j++) {
            board[0][j] = 0;
        }

        for (i = 1; i < x.length(); i++) {
            for (j = 1; j < y.length(); j++) {
                if (x.charAt(i) == y.charAt(j)) {
                    board[i][j] = board[i - 1][j - 1] + 1;
                } else {
                    board[i][j] = ((board[i][j - 1] > board[i - 1][j]) ? board[i][j - 1] : board[i - 1][j]);
                }
            }
        }
        return board[x.length() - 1][y.length() - 1];
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
    public int[][] lcsPanelLength(String[] x, String[] y) {
        // Add 0(null) string for counting
    	for (int i = x.length-2; i >= 0; i--) {
    		x[i+1] = x[i];
    	}
    	x[0] = "0";
    	for (int i = y.length-2; i >= 0; i--) {
    		y[i+1] = y[i];
    	}
    	y[0] = "0";
        this.board = new Integer[x.length][y.length];
        this.solution = new String[x.length][y.length];

        for (int i = 0; i < x.length; i++) {
            board[i][0] = 0;
        }
        for (int j = 0; j < y.length; j++) {
            board[0][j] = 0;
        }

        for (int i = 1; i < x.length; i++) {
            for (int j = 1; j < y.length; j++) {
                if (x[i] == y[j]) {
                	solution[i][j] = "diagonal";
                    board[i][j] = board[i - 1][j - 1] + 1;
                } else {
                	if((board[i][j - 1] > board[i - 1][j])) {
                		solution[i][j] = "left";
                		board[i][j] = board[i][j - 1];
                	}
                	else {
                		solution[i][j] = "up";
                		board[i][j] = board[i - 1][j];
                	}
                }
            }
        }
        int[][] solPair = new int[x.length][2];
        int i = x.length-1;
        int j = y.length-1;
        int k = 0;
        while(board[i][j] != 0) {
        	if(solution[i][j] == "diagonal") {
		    	solPair[k][0] = i - 1;
		    	solPair[k][1] = j - 1;
		    	i--;
		    	j--;
		    	k++;
        	} else {
        		if(solution[i][j] == "up") {
            		i--;	
        		} else {
        			j--;
        		}
        	}
        }
        for(i = 0; i < k; i++) {
            System.out.println(solPair[i][0] + " " + solPair[i][1]);
        }
    	return solPair;
    }
    
    public void stringFix(int[][] pair) {
    	int leftBtwStart;
    	int leftBtwEnd;
    	int rightBtwStart;
    	int rightBtwEnd;
    	
    	
    }

    public static void main(String[] args) {
        Comparison board = new Comparison(9,7);
        board.origin[0] = "1";
        board.origin[1] = null;
        board.origin[2] = "2";
        board.origin[3] = "3";
        board.origin[4] = " ";
        board.origin[5] = "4";
        board.origin[6] = "5";
        board.origin[7] = "6";
        board.compared[0] = "1";
        board.compared[1] = "2";
        board.compared[2] = "3";
        board.compared[3] = "4";
        board.compared[4] = "5";
        board.compared[5] = "6";
        board.stringFix(board.lcsPanelLength(board.origin, board.compared));


        //System.out.println();
    }
}
