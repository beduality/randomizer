package io.github.beduality.core.features;

import org.bukkit.entity.Player;

import io.github.beduality.core.models.Feature;
import io.github.beduality.core.models.User;

public class HealthFeature extends Feature {

    private void heal(Player player) {
        player.setHealth(20.0);
        player.setSaturation(20.0f);
    }

    @Override
    public void onEnable() {
        getPhase().getGame().getPlayers().forEach(user -> {
            heal(user.getPlayer());
        });
    }

    @Override
    public void onJoin(User user) {
        heal(user.getPlayer());
    }
}
