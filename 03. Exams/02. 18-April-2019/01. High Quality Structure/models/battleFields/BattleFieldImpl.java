package models.battleFields;

import models.battleFields.interfaces.Battlefield;
import models.cards.interfaces.Card;
import models.players.interfaces.Player;

public class BattleFieldImpl implements Battlefield {
    private static final int BEGINNER_HP_INCREMENT = 40;
    private static final int BEGINNER_CARD_DMG_INCREMENT = 30;

    @Override
    public void fight(Player attackPlayer, Player enemyPlayer) {
        if (attackPlayer.isDead() || enemyPlayer.isDead()) {
            throw new IllegalArgumentException("Player is dead!");
        }

        increaseBeginnerHP(attackPlayer);
        increaseBeginnerHP(enemyPlayer);

        applyBonusHP(attackPlayer);
        applyBonusHP(enemyPlayer);

        while (!(attackPlayer.isDead() && enemyPlayer.isDead())) {
            enemyPlayer.takeDamage(getDeckDamage(attackPlayer));
//            if (enemyPlayer.isDead()) {
//                throw new IllegalArgumentException("Player is dead!");
//            }
            attackPlayer.takeDamage(getDeckDamage(enemyPlayer));
        }
    }

    private boolean playerIsDead (Player player) {
        return player.isDead();
    }

    private void increaseBeginnerHP (Player player) {
        if (player.getClass().getSimpleName().equals("Beginner")) {
            player.setHealth(player.getHealth() + BEGINNER_HP_INCREMENT);
            player.getCardRepository()
                    .getCards()
                    .forEach(card -> card.setDamagePoints(card.getDamagePoints() + BEGINNER_CARD_DMG_INCREMENT));
        }
    }

    private void applyBonusHP(Player player) {
        player.setHealth(player.getHealth() +
                player.getCardRepository()
                .getCards().stream()
                .mapToInt(Card::getHealthPoints).sum());
    }

    private int getDeckDamage(Player player) {
        return player.getCardRepository().getCards().stream().mapToInt(Card::getDamagePoints).sum();
    }
}
