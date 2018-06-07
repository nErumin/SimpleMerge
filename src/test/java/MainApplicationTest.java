import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

import org.fxmisc.richtext.InlineCssTextArea;
import org.testfx.api.FxRobot;
import org.junit.*;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.robot.Motion;

import java.util.concurrent.TimeoutException;

import static org.testfx.api.FxAssert.verifyThat;


public class MainApplicationTest extends ApplicationTest {
    @BeforeClass
    public static void setUpClass() throws TimeoutException {
        FxToolkit.registerPrimaryStage();
    }
    @Before
    public void setup() throws Exception {
        FxToolkit.setupApplication(MainApplication.class);
        FxToolkit.showStage();
    }
    @Test
    public void panelExistenceTest() {
        verifyThat("#textpane", InlineCssTextArea::isVisible);
        verifyThat("#textpaneRight", InlineCssTextArea::isVisible);

        verifyThat("#toolbarleft", ToolBar::isVisible);
        verifyThat("#toolbarright", ToolBar::isVisible);
    }
    @Test
    public void initialToolbarStateTest() {
        verifyThat("#editButton", (Button b) -> !b.isDisabled() && b.getText().equals("Edit"));
        verifyThat("#loadButton", (Button b) -> !b.isDisabled() && b.getText().equals("Load"));
        verifyThat("#saveButton", (Button b) -> !b.isDisabled() && b.getText().equals("Save"));
        verifyThat("#saveAsButton", (Button b) -> !b.isDisabled() && b.getText().equals("SaveAs"));
        verifyThat("#refreshButton", (Button b) -> b.isDisabled() && b.getText().equals("Refresh"));


        verifyThat("#editButtonRight", (Button b) -> !b.isDisabled() && b.getText().equals("Edit"));
        verifyThat("#loadButtonRight", (Button b) -> !b.isDisabled() && b.getText().equals("Load"));
        verifyThat("#saveButtonRight", (Button b) -> !b.isDisabled() && b.getText().equals("Save"));
        verifyThat("#saveAsButtonRight", (Button b) -> !b.isDisabled() && b.getText().equals("SaveAs"));
        verifyThat("#refreshButtonRight", (Button b) -> b.isDisabled() && b.getText().equals("Refresh"));
    }
    @Test
    public void initialTextAreaStateTest(){
        verifyThat("#textpane",(InlineCssTextArea t) -> !t.isEditable() );
        verifyThat("#textpaneRight",(InlineCssTextArea t) -> !t.isEditable() );
    }
    @Test
    public void compareTest() {
        FxRobot robot = new FxRobot();
        InlineCssTextArea leftPanel = lookup("#textpane").query();
        InlineCssTextArea rightPanel = lookup("#textpaneRight").query();

        robot.interact(() -> {
            robot.sleep(500);
            leftPanel.clear();
            rightPanel.clear();

            leftPanel.appendText("1\n1\n1\n1\n2\n3");
            rightPanel.appendText("\n\n1\n\n\n2\n3");

            robot.clickOn("#compareButton", Motion.DIRECT,
                MouseButton.PRIMARY);
        });

        Assert.assertThat(leftPanel.getText(), is(equalTo("\n\n1\n1\n1\n1\n2\n3\n")));
    }
    @Test
    public void editTest() {
        FxRobot robot = new FxRobot();
        InlineCssTextArea leftPanel = lookup("#textpane").query();
        InlineCssTextArea rightPanel = lookup("#textpaneRight").query();

        verifyThat("#editButton", button -> !button.isDisabled());
        verifyThat("#editButtonRight", button -> !button.isDisabled());

        robot.interact(() -> {
            robot.sleep(500);
            leftPanel.clear();
            rightPanel.clear();

            verifyThat(leftPanel, panel -> !panel.isEditable());
            verifyThat(rightPanel, panel -> !panel.isEditable());

            robot.clickOn("#editButton", Motion.DIRECT,
                            MouseButton.PRIMARY);

            robot.clickOn("#editButtonRight", Motion.DIRECT,
                            MouseButton.PRIMARY);

            robot.clickOn(leftPanel, Motion.DIRECT,
                MouseButton.PRIMARY);

            robot.type(KeyCode.S);
        });


        verifyThat(leftPanel, InlineCssTextArea::isEditable);
        verifyThat(rightPanel, InlineCssTextArea::isEditable);
        verifyThat("#editButton", Button::isDisabled);
        verifyThat("#editButtonRight", Button::isDisabled);

        Assert.assertThat(leftPanel.getText(), is(equalTo("s")));
    }
    @Test
    public void mergeToRightTest() {
        FxRobot robot = new FxRobot();
        InlineCssTextArea leftPanel = lookup("#textpane").query();
        InlineCssTextArea rightPanel = lookup("#textpaneRight").query();

        robot.interact(() -> {
            robot.sleep(1000);
            leftPanel.clear();
            rightPanel.clear();

            leftPanel.appendText("1\n1\n1\n1\n2\n3");
            rightPanel.appendText("\n\n1\n\n\n2\n3");

            robot.clickOn("#compareButton", Motion.DIRECT,
                MouseButton.PRIMARY);

            robot.moveTo(leftPanel)
                 .moveBy(0, 150)
                 .clickOn(MouseButton.PRIMARY);

            robot.clickOn("#copytorightButton", Motion.DIRECT,
                          MouseButton.PRIMARY);
        });

        verifyThat("#copytoleftButton", Button::isDisabled);
        verifyThat("#copytorightButton", Button::isDisabled);
        verifyThat("#compareButton",
            (Button button) -> !button.isDisabled());
    }
    @Test
    public void findTest() {
        FxRobot robot = new FxRobot();
        InlineCssTextArea leftPanel = lookup("#textpane").query();
        InlineCssTextArea rightPanel = lookup("#textpaneRight").query();

        robot.interact(() -> {
            robot.sleep(500);
            leftPanel.clear();
            rightPanel.clear();

            leftPanel.appendText("Hello!");

            robot.clickOn("#findButton", Motion.DIRECT,
                MouseButton.PRIMARY);
        });

        lookup("#compareButton").query().setDisable(false);
        verifyThat("#copytoleftButton", Button::isDisabled);
        verifyThat("#copytorightButton", Button::isDisabled);
    }
    @Test
    public void viewTest() {
        FxRobot robot = new FxRobot();
        InlineCssTextArea leftPanel = lookup("#textpane").query();
        InlineCssTextArea rightPanel = lookup("#textpaneRight").query();

        robot.interact(() -> {
            robot.sleep(500);
            leftPanel.clear();
            rightPanel.clear();

            leftPanel.appendText("1\n1\n1\n1\n2\n3");
            rightPanel.appendText("\n\n1\n\n\n2\n3");

            robot.clickOn("#compareButton", Motion.DIRECT,
                MouseButton.PRIMARY);

            robot.clickOn("#viewButton", Motion.DIRECT,
                MouseButton.PRIMARY);
        });

        verifyThat("#copytoleftButton", (Button button) -> !button.isDisabled());
        verifyThat("#copytorightButton", (Button button) -> !button.isDisabled());
        verifyThat("#compareButton", Button::isDisabled);
    }
    @Test
    public void loadTest() {
        FxRobot robot = new FxRobot();

        robot.interact(() -> {
            robot.sleep(500);
            robot.clickOn("#loadButton", Motion.DIRECT,
                MouseButton.PRIMARY);

            robot.type(KeyCode.ESCAPE);
        });

        verifyThat("#refreshButton", Button::isDisabled);
    }
    @Test
    public void saveTest() {
        FxRobot robot = new FxRobot();
        robot.interact(() -> {
            robot.sleep(500);
            robot.clickOn("#saveButtonRight", Motion.DIRECT,
                MouseButton.PRIMARY);

            robot.type(KeyCode.ESCAPE);
        });

        verifyThat("#refreshButton", Button::isDisabled);
    }
}
