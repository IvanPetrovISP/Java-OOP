package core;

import common.ConstantMessages;
import core.interfaces.ManagerController;
import models.battleFields.BattleFieldImpl;
import models.battleFields.interfaces.Battlefield;
import models.cards.MagicCard;
import models.cards.TrapCard;
import models.cards.interfaces.Card;
import models.players.Advanced;
import models.players.Beginner;
import models.players.interfaces.Player;
import repositories.CardRepositoryImpl;
import repositories.PlayerRepositoryImpl;
import repositories.interfaces.CardRepository;
import repositories.interfaces.PlayerRepository;

import static common.ConstantMessages.*;


public class ManagerControllerImpl implements ManagerController {
    private PlayerRepository playerRepository;
    private CardRepository cardRepository;
    private Battlefield battlefield;

    public ManagerControllerImpl() {
       //TODO:IMPLEMENT ME
        this.playerRepository = new PlayerRepositoryImpl();
        this.cardRepository = new CardRepositoryImpl();
        this.battlefield = new BattleFieldImpl();
    }

    @Override
    public String addPlayer(String type, String username) {
        if (("Beginner").equals(type)) {
            this.playerRepository.add(new Beginner(new CardRepositoryImpl(), username));
        } else if (Advanced.class.getSimpleName().equals(type)){
            this.playerRepository.add(new Advanced(new CardRepositoryImpl(), username));
        }

       return String.format(SUCCESSFULLY_ADDED_PLAYER,
               type, username);
    }

    @Override
    public String addCard(String type, String name) {
        if (("Magic").equals(type)) {
            this.cardRepository.add(new MagicCard(name));
        } else if (("Trap").equals(type)){
            this.cardRepository.add(new TrapCard(name));
        }
        return String.format(SUCCESSFULLY_ADDED_CARD,
                type, name);
    }

    @Override
    public String addPlayerCard(String username, String cardName) {
//        if (this.playerRepository.find(username) == null) {
//            throw new IllegalArgumentException("Player cannot be null");
//        }
        this.playerRepository
                .find(username)
                .getCardRepository()
                .add(this.cardRepository.find(cardName));

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_PLAYER_WITH_CARDS,
                cardName, username);
    }

    @Override
    public String fight(String attackUser, String enemyUser) {
        Player attacker = this.playerRepository.find(attackUser);
        Player enemy = this.playerRepository.find(enemyUser);
        this.battlefield.fight(attacker, enemy);

        return String.format(ConstantMessages.FIGHT_INFO,
                attacker.getHealth(), enemy.getHealth());
    }
    @Override
    public String report() {
        StringBuilder result = new StringBuilder();
        for (Player player : this.playerRepository.getPlayers()) {
            result.append(String.format(ConstantMessages.PLAYER_REPORT_INFO,
                    player.getUsername(),
                    player.getHealth(),
                    player.getCardRepository().getCount()));
            result.append(System.lineSeparator());
            for (Card card :player.getCardRepository().getCards()) {
                result.append(String.format(ConstantMessages.CARD_REPORT_INFO,
                        card.getName(),
                        card.getDamagePoints()));
                result.append(System.lineSeparator());
            }
            result.append(ConstantMessages.DEFAULT_REPORT_SEPARATOR);
            result.append(System.lineSeparator());
        }

        return result.toString().trim();
    }
}
