package com.matthew.plugin.core.events.disables;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WeatherChangeListener implements Listener {

    @EventHandler
    public void weatherChange(WeatherChangeEvent e) {
        e.setCancelled(e.toWeatherState());
    }
}
