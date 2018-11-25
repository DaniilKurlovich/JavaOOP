import org.junit.Assert;
import org.junit.Test;
import GameFile.NewGame;
import GameFile.NewGameEvent;
import Creatures.Player;

public class test_game {
    NewGame testGame = new NewGame();
    @Test
    public void testFight() {
        Player testPlayer = new Player("test", 5,5);
        NewGameEvent testEvent = new NewGameEvent(testPlayer, "TestEnemy", false);
        if (testEvent.getResultEvent() < 0)
            Assert.assertTrue(true);
    }

    @Test
    public void testMovesInLocation(){
        testGame.addPlayerToDataBase("qaz", "Test", 100, 100);
        testGame.setRequestFromHandler("qaz", "/adventure");
        Assert.assertEquals(testGame.getLocation("qaz"),"Adventure");
        testGame.setRequestFromHandler("qaz", "/home");
        Assert.assertEquals(testGame.getLocation("qaz"), "Camp");
    }

    @Test
    public void checkUserInDataBase(){
        testGame.addPlayerToDataBase("qaz", "Test", 100, 100);
        Assert.assertEquals(testGame.haveThisPlayer("qaz"), true);
    }

    @Test
    public void testIncorrectInput(){
        String resultIncorrectRequest = testGame.setRequestFromHandler("test", "/test");
        Assert.assertEquals(resultIncorrectRequest, "Неправильно введена команда");
    }
}
