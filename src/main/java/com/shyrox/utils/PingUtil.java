package com.shyrox.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.bukkit.entity.Player;

public class PingUtil {
    private static Method getHandleMethod;
    private static Field pingField;

    public static int getPing(Player player) {
        try {
            if (getHandleMethod == null) {
                getHandleMethod = player.getClass().getDeclaredMethod("getHandle");
                getHandleMethod.setAccessible(true);
            }
            
			Object entityPlayer = getHandleMethod.invoke(player);
            if (pingField == null) {
                pingField = entityPlayer.getClass().getDeclaredField("ping");
                pingField.setAccessible(true);
            }
			
            int ping = pingField.getInt(entityPlayer);
            return ping > 0 ? ping : 0;
        } catch (Exception ex) {
            return 1;
        }
    }
}

