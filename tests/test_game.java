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
        testGame.setRequestFromHandler("123", "/start");
        testGame.setRequestFromHandler("123", "/adventure");
        Assert.assertEquals(testGame.getLocation("123"), "Adventure");
        testGame.setRequestFromHandler("123", "/home");
        Assert.assertEquals(testGame.getLocation("123"), "Camp");
    }

    @Test
    public void checkUserInDataBase() {
        NewGame testGame = new NewGame();
        testGame.setRequestFromHandler("123", "/start");
        Assert.assertTrue(testGame.isPlayerInDB("123"));
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
        Assert.assertEquals(testGame.setRequestFromHandler("412", "/adventure"),
                "Персонаж не был создан, введите /help");
    }

}
