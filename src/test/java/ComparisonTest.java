import org.junit.Assert;
import org.junit.Test;

public class ComparisonTest {

    private Comparison c = new Comparison();
    private String X, Y;
    private Integer[][] C;


    /**
     *
     */
    @Test
    public void sameStringTest(){
        X = "0123456789";
        Y = "0123456789";
        C = new Integer[X.length()][Y.length()];
        int result = c.lscLength(C,X,Y);
        Assert.assertEquals(9, result);
    }

    /**
     *
     */
    @Test
    public void nomalCaseTest(){
        X = "0123456789";
        Y = "0321456789";
        C = new Integer[X.length()][Y.length()];
        int result = c.lscLength(C,X,Y);
        Assert.assertEquals(7, result);
    }

    /**
     *
     */
    @Test
    public void nullLeftStringTest(){
        X = "0";
        Y = "0321456789";
        C = new Integer[X.length()][Y.length()];
        int result = c.lscLength(C,X,Y);
        Assert.assertEquals(0, result);
    }

    /**
     *
     */
    @Test
    public void nullRightStringTest(){
        X = "0123456789";
        Y = "0";
        C = new Integer[X.length()][Y.length()];
        int result = c.lscLength(C,X,Y);
        Assert.assertEquals(0, result);
    }


    /**
     *
     */
    @Test
    public void nullStringTest(){
        X = "0";
        Y = "0";
        C = new Integer[X.length()][Y.length()];
        int result = c.lscLength(C,X,Y);
        Assert.assertEquals(0, result);
    }

    /**
     *
     */
    @Test
    public void shortLeftStringTest(){
        X = "0123456789";
        Y = "02345";
        C = new Integer[X.length()][Y.length()];
        int result = c.lscLength(C,X,Y);
        Assert.assertEquals(4, result);
    }

    /**
     *
     */
    @Test
    public void shortRightStringTest(){
        X = "02345";
        Y = "0123456789";
        C = new Integer[X.length()][Y.length()];
        int result = c.lscLength(C,X,Y);
        Assert.assertEquals(4, result);
    }
}
