import javafx.application.Platform;
import javafx.scene.control.Button;
import org.junit.*;
import org.testfx.api.FxToolkit;
import static org.testfx.api.FxAssert.verifyThat;

import java.util.concurrent.TimeoutException;

import static org.junit.Assert.*;

public class MainWindowControllerTest {

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
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        FxToolkit.hideStage();
    }

    @Test
    public void comparePanel() {
    }

    @Test
    public void copyToLeft() {
    }

    @Test
    public void copyToRight() {
    }

    @Test
    public void openFile() {
        verifyThat("#loadButton", (Button b) -> !b.isDisabled() && b.getText().equals("Load"));
    }

    @Test
    public void editFile() {
        verifyThat("#editButton", (Button b) -> !b.isDisabled() && b.getText().equals("Edit"));
    }

    @Test
    public void saveFile() {
        verifyThat("#saveButton", (Button b) -> !b.isDisabled() && b.getText().equals("Save"));
    }

    @Test
    public void saveAsFile() {
    }

    @Test
    public void openFileRight() {
        verifyThat("#loadButtonRight", (Button b) -> !b.isDisabled() && b.getText().equals("Load"));
    }

    @Test
    public void editFileRight() {
        verifyThat("#editButtonRight", (Button b) -> !b.isDisabled() && b.getText().equals("Edit"));
    }

    @Test
    public void saveFileRight() {
        verifyThat("#saveButtonRight", (Button b) -> !b.isDisabled() && b.getText().equals("Save"));
    }

    @Test
    public void saveAsFileRight() {
    }

    @Test
    public void viewButtonAction() {
    }

    @Test
    public void findButton() {
    }

    @Test
    public void findButtonRight() {
    }

    @Test
    public void refreshButtonAction() {
    }

    @Test
    public void refreshButtonActionRight() {
    }

    @Test
    public void exitApp() {
    }
}
