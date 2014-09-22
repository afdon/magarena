[
    new MagicPermanentActivation(
        new MagicActivationHints(MagicTiming.Removal),
        "+Counters"
    ) {

        @Override
        public Iterable<MagicEvent> getCostEvent(final MagicPermanent source) {
            return [new MagicPayManaCostEvent(source, "{2}{G}{G}")];
        }

        @Override
        public MagicEvent getPermanentEvent(final MagicPermanent source, final MagicPayedCost payedCost) {
            return new MagicEvent(
                source,
                this,
                "PN puts X +1/+1 counters on SN, where X is its power."
            );
        }

        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            final MagicPermanent creature = event.getPermanent();
            final int amount = creature.getPower();
            game.doAction(new MagicChangeCountersAction(creature,MagicCounterType.PlusOne,amount));
            game.logAppendMessage(event.getPlayer(),"(X="+amount+")");
        }
    }
]
