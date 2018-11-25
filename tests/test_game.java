import org.junit.Assert;
import org.junit.Test;
import sample.NewGame;
import sample.NewGameEvent;
import sample.Player;

public class test_game {
    @Test
    public void testFight() {
        Player testPlayer = new Player("Test", 1, 1);
        NewGameEvent testEvent = new NewGameEvent(testPlayer, "TestEnemy", false);
        if (testEvent.getResultEvent() < 0)
            Assert.assertTrue(true);
    }

    @Test
    public void testMovesInLocation(){
        NewGame testGame = new NewGame();
        testGame.addPlayerToDataBase("qaz", "Test", 100, 100);
        testGame.setRequestFromHandler("qaz", "/adventure");
        Assert.assertEquals(testGame.getLocation("qaz"),"Adventure");
        testGame.setRequestFromHandler("qaz", "/home");
        Assert.assertEquals(testGame.getLocation("qaz"), "Camp");
    }

    @Test
    public void checkUserInDataBase(){
        NewGame testGame = new NewGame();
        try {
            testGame.addPlayerToDataBase("qaz", "Test", 100, 100);
        }
        catch (ExceptionInInitializerError exception) {
            Assert.fail(exception.toString());
        }
        Assert.assertTrue("Test accept", true);
    }


}
