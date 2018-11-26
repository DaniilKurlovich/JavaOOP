//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import org.junit.Assert;
import org.junit.Test;
import GameFile.NewGame;
import GameFile.NewGameEvent;
import Creatures.Player;

public class test_game {
    public test_game() {
    }

    @Test
    public void testFight() {
        Player testPlayer = new Player("Test", 1, 1);
        NewGameEvent testEvent = new NewGameEvent(testPlayer, "TestEnemy", false);
        if (testEvent.getResultEvent() < 0) {
            Assert.assertTrue(true);
        }

    }

    @Test
    public void testMovesInLocation() {
        NewGame testGame = new NewGame();
        testGame.addPlayerToDataBase("qaz", "Test", 100, 100);
        testGame.setRequestFromHandler("qaz", "/adventure");
        Assert.assertEquals(testGame.getLocation("qaz").getNameLocation(), "Adventure");
        testGame.setRequestFromHandler("qaz", "/home");
        Assert.assertEquals(testGame.getLocation("qaz").getNameLocation(), "Camp");
    }

    @Test
    public void checkUserInDataBase() {
        NewGame testGame = new NewGame();
        testGame.addPlayerToDataBase("qaz", "Test", 100, 100);
        Assert.assertEquals(testGame.haveThisPlayer("qaz"), true);
    }

    @Test
    public void checkOnNotExistingLocation() {
        NewGame testgame = new NewGame();
        Assert.assertEquals(testgame.getInfoAboutLocation("somethinglocation"), "Error");
    }

    @Test
    public void checkOnGettingReward() {
        Player player = new Player("test", 10, 10);
        player.setReward(10);
        Assert.assertEquals((long)player.getMoneyInfo(), 10L);
    }

    @Test
    public void checkOnChangedHPPlayer() {
        Player player = new Player("test", 10, 10);
        player.SetDamage(5);
        Assert.assertEquals((long)player.GetHealpoint(), 10L);
        player.SetDamage(-5);
        Assert.assertEquals((long)player.GetHealpoint(), 15L);
    }

    @Test
    public void startPlayWithoutPlayer(){
        NewGame testGame = new NewGame();
        Assert.assertEquals(testGame.setRequestFromHandler("qaz", "/adventure"),
                "Персонаж не был создан, введите /help");
    }

}
