[
    new MagicSpellCardEvent() {
        @Override
        public MagicEvent getEvent(final MagicCardOnStack cardOnStack, final MagicPayedCost payedCost) {
            return new MagicEvent(
                cardOnStack,
                MagicColorChoice.ALL_INSTANCE,
                this,
                "Choose a color\$. " +
                "Creatures PN controls have protection from the chosen color until end of turn."
            );
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            final MagicAbility protection = event.getChosenColor().getProtectionAbility();
            game.filterPermanents(event.getPlayer(),MagicTargetFilterFactory.CREATURE_YOU_CONTROL) each {
                game.doAction(new MagicGainAbilityAction(it, protection));
            }
        }
    }
]
