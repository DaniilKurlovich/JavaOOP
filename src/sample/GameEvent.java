package sample;

public class GameEvent {
    // индикатор благоприятности события, то есть плохое если false, иначе хорошее
    private boolean statusEventIsGood;
    public GameEvent generateGoodEvent(){
        statusEventIsGood = true;
        return this;
    }
    public GameEvent generateBadEvent(){
        statusEventIsGood = false;
        return this;
    }
}
