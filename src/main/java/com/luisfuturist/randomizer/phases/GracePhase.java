package com.luisfuturist.randomizer.phases;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import com.luisfuturist.core.Constants;
import com.luisfuturist.core.features.HealthFeature;
import com.luisfuturist.core.features.NoPveFeature;
import com.luisfuturist.core.features.RemovePotionEffectsFeature;
import com.luisfuturist.core.models.Phase;
import com.luisfuturist.randomizer.features.RandomizerFeature;

public class GracePhase extends Phase {

    public GracePhase() {
        super("Grace");
        addFeatures(
                new NoPveFeature(),
                new HealthFeature(),
                new RemovePotionEffectsFeature(),
                new RandomizerFeature());
        setDuration(Constants.TPS * 60 * 5);
        setAllowJoin(true);
    }

    public void resetPlayer(Player player) {
        player.setGameMode(GameMode.SURVIVAL);
        player.getInventory().clear();
    }

    @Override
    public void onStart() {
        super.onStart();

        for (var player : getGame().getPlayers()) {
            resetPlayer(player);
        }
    }

    @Override
    public void onJoin(Player player) {
        resetPlayer(player);
    }
}