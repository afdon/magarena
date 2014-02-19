[
    new MagicPermanentActivation(
        new MagicActivationHints(MagicTiming.Land),
        "Land"
    ) {

        @Override
        public Iterable<MagicEvent> getCostEvent(final MagicPermanent source) {
            return [new MagicTapEvent(source)];
        }

        @Override
        public MagicEvent getPermanentEvent(final MagicPermanent source,final MagicPayedCost payedCost) {
            return new MagicEvent(
                source,
                MagicTargetChoice.LAND_CARD_FROM_HAND,
                MagicGraveyardTargetPicker.PutOntoBattlefield,
                this,
                "PN puts a land card\$ from PN's hand onto the battlefield."
            );
        }

        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            event.processTargetCard(game, {
                final MagicCard card ->
                game.doAction(new MagicRemoveCardAction(card,MagicLocationType.OwnersHand));
                game.doAction(new MagicPlayCardAction(card,event.getPlayer()));
            });
        }
    }
]
