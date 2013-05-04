[
    new MagicWhenDamageIsDealtTrigger() {
        @Override
        public MagicEvent executeTrigger(final MagicGame game,final MagicPermanent permanent,final MagicDamage damage) {
            final int amount=damage.getDealtAmount();
            return (damage.getSource()==permanent.getEnchantedCreature()) ?
                new MagicEvent(
                    permanent,
                    amount,
                    this,
                    "PN gains " + amount + " life.") 
                :
                MagicEvent.NONE;
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            game.doAction(new MagicChangeLifeAction(event.getPlayer(),event.getRefInt()));
        }
    }
]
