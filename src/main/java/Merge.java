
public class Merge {
	String[] Compare;
	String[] Origin;
	int changeIndex;
	
	Merge(){
		
	}
	 /**
	  * 
	  * */
	void changeLine(int changeIndex) {
		Origin[changeIndex] = Compare[changeIndex];
	}
	
}
