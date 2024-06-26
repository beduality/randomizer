package com.luisfuturist.core.phases;

import com.luisfuturist.core.features.CustomLoginMessagesFeature;
import com.luisfuturist.core.features.CustomPlayerChatMessagesFeature;
import com.luisfuturist.core.features.DeathMessagesFeature;
import com.luisfuturist.core.models.Phase;

public class GlobalPhase extends Phase {

    public GlobalPhase() {
        super("Global");
        setTimed(false);
        setAllowJoin(true);
        setAllowSpectate(false);
        addFeatures(
                new CustomLoginMessagesFeature(),
                new CustomPlayerChatMessagesFeature(),
                new DeathMessagesFeature());
    }
}
