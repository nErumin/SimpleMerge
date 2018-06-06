import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import org.junit.Test;

import javafx.application.Platform;
import org.fxmisc.richtext.InlineCssTextArea;
import org.testfx.api.FxRobot;
import org.junit.*;
import org.testfx.api.FxToolkit;;

import java.util.concurrent.TimeoutException;

import static org.testfx.api.FxAssert.verifyThat;


public class MainApplicationTest {
    FxRobot robot = new FxRobot();

    @BeforeClass
    public static void setUpClass() throws TimeoutException {
        FxToolkit.registerPrimaryStage();
    }

    @AfterClass
    public static void tearDownClass() throws TimeoutException {
        FxToolkit.hideStage();
        Platform.setImplicitExit(false);

    }

    @Before
    public void setup() throws Exception {
        FxToolkit.setupApplication(MainApplication.class);
        FxToolkit.showStage();
    }

    @After
    public void tearDown() throws Exception {
        FxToolkit.hideStage();
    }
    @Test
    public void existTest(){
        verifyThat("#textpane",(InlineCssTextArea a) ->a.isVisible());
        verifyThat("#toolbarleft",(ToolBar a) ->a.isVisible());
        verifyThat("#textpaneRight",(InlineCssTextArea a) ->a.isVisible());
        verifyThat("#toolbarright",(ToolBar a) ->a.isVisible());

    }

    @Test
    public void toolBarIsWell() {

        verifyThat("#editButton", (Button b) -> !b.isDisabled() && b.getText().equals("Edit"));
        verifyThat("#loadButton", (Button b) -> !b.isDisabled() && b.getText().equals("Load"));
        verifyThat("#saveButton", (Button b) -> !b.isDisabled() && b.getText().equals("Save"));


        verifyThat("#editButtonRight", (Button b) -> !b.isDisabled() && b.getText().equals("Edit"));
        verifyThat("#loadButtonRight", (Button b) -> !b.isDisabled() && b.getText().equals("Load"));
        verifyThat("#saveButtonRight", (Button b) -> !b.isDisabled() && b.getText().equals("Save"));
    }
    @Test
    public void compareTest() {

    }
    @Test
    public void disableTest(){
        verifyThat("#textpane",(InlineCssTextArea t) -> !t.isEditable() );
        verifyThat("#textpaneRight",(InlineCssTextArea t) -> !t.isEditable() );
    }
    @Test
    public void editTest(){


    }
    @Test
    public void loadTest(){

    }
    @Test
    public void saveTest(){

    }




}
