def NONARTIFACT_CREATURE_YOU_CONTROL=new MagicPermanentFilterImpl() {
    public boolean accept(final MagicGame game,final MagicPlayer player,final MagicPermanent target) {
        return !target.hasType(MagicType.Artifact) && target.isCreature() && target.isController(player);
    } 
};

def TARGET_NONARTIFACT_CREATURE_YOU_CONTROL = new MagicTargetChoice(
    NONARTIFACT_CREATURE_YOU_CONTROL,
    "target nonartifact creature you control"
);

[
    new MagicAtUpkeepTrigger() {
        @Override
        public MagicEvent executeTrigger(final MagicGame game,final MagicPermanent permanent,final MagicPlayer player) {
            return new MagicEvent(
                permanent,
                player,
                TARGET_NONARTIFACT_CREATURE_YOU_CONTROL,
                this,
                "Destroy target nonartifact creature\$ of PN's choice. It can't be regenerated."
            );
        }

        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
             event.processTargetPermanent(game, {
                game.doAction(MagicChangeStateAction.Set(it,MagicPermanentState.CannotBeRegenerated));
                game.doAction(new MagicDestroyAction(it));
            });
        }
    }
]
