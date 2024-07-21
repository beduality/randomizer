package com.luisfuturist.randomizer.phases;

import com.luisfuturist.core.Bed;
import com.luisfuturist.core.features.BoardFeature;
import com.luisfuturist.core.features.LobbyTimerFeature;
import com.luisfuturist.core.features.SpawnFeature;
import com.luisfuturist.core.models.User;
import com.luisfuturist.core.phases.LobbyPhase;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class RandomizerLobbyPhase extends LobbyPhase {

    private LobbyTimerFeature countdownFeature = new LobbyTimerFeature() {
        @Override
        public void onFinish() {
            super.onFinish();

            getGame().finishCurrentPhase();
        }
    };
    private BoardFeature boardFeature = new BoardFeature();

    private void setupBoard() {
        var displayName = Component.text().color(NamedTextColor.BLUE)
                .append(Component.text("Randomizer")).asComponent();

        boardFeature.getObjective().displayName(displayName);

        var playersNum = getGame().getPlayers().size();
        var maxPlayers = 2;

        boardFeature.setLines(new String[] {
                "Waiting for players",
                playersNum + "/" + maxPlayers
        });
    }

    @Override
    public void onCreate() {
        setName("RandomizerLobby");
        setTimed(false);

        addFeatures(boardFeature, countdownFeature);

        var lobbyLocation = Bed.locationManager.getLocation("lobby");

        if (lobbyLocation != null) {
            var spawnFeature = new SpawnFeature();
            spawnFeature.setLocation(lobbyLocation);
            addFeature(spawnFeature);
        } else {
            Bed.plugin.getLogger().warning("Randomizer | Spawn feature in " + getName()
                    + " phase is not available due to the lack of location config.");
        }
        
        setupBoard();
    }

    @Override
    public void onStart() {
        super.onStart();

        countdownFeature.start();

        getGame().getPlayers().forEach(user -> {
            countdownFeature.showBossBar(user);
        });
    }

    @Override
    public void onFinish() {
        super.onFinish();

        getGame().getPlayers().forEach(user -> {
            countdownFeature.hideBossBar(user);
        });
    }

    @Override
    public void onJoin(User user) {
        super.onJoin(user);
        countdownFeature.showBossBar(user);
    }

    @Override
    public void onLeave(User user) {
        super.onLeave(user);
        countdownFeature.hideBossBar(user);
    }
}
