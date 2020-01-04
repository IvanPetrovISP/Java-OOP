package cardPower;

import enums.RankPower;
import enums.SuitPower;

public class Card {
    private SuitPower suit;
    private RankPower rank;

    public Card(SuitPower suit, RankPower rank){
        this.suit = suit;
        this.rank = rank;
    }

    private int getPower(){
        return this.suit.getPower() + this.rank.getPower();
    }

    @Override
    public String toString() {
        return String.format("Card name: %s of %s; Card power: %d",
                this.rank, this.suit, this.getPower());
    }
}
