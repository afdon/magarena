package magic.test;

import magic.model.MagicDuel;
import magic.model.MagicGame;
import magic.model.MagicPlayer;
import magic.model.phase.MagicMainPhase;

class TestDelayedTrigger extends TestGameBuilder {
    public MagicGame getGame() {
        final MagicDuel duel=createDuel();
        final MagicGame game=duel.nextGame();
        game.setPhase(MagicMainPhase.getFirstInstance());
        final MagicPlayer player=game.getPlayer(0);
        final MagicPlayer opponent=game.getPlayer(1);

        MagicPlayer P = player;

        P.setLife(13);
        addToLibrary(P, "Forest", 20);
        createPermanent(game,P, "Forest", false, 8);
        createPermanent(game,P, "Chromatic Lantern", false, 1);
        createPermanent(game,P, "Duskmantle Guildmage", false, 1);
        createPermanent(game,P, "Vizkopa Guildmage",false,1);

        P = opponent;

        P.setLife(13);
        addToLibrary(P, "Forest", 20);
        createPermanent(game,P,"Rupture Spire",false,5);

        return game;
    }
}
