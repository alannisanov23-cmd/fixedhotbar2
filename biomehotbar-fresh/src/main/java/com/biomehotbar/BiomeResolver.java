package com.biomehotbar;

import net.minecraft.client.Minecraft;

/** Maps the biome under the player to a Theme. Only reads the biome id, never touches rendering. */
final class BiomeResolver {
    private BiomeResolver() {}

    static Theme current(Minecraft mc) {
        if (mc.level == null || mc.player == null) return Theme.MEADOW;
        // Holder#getRegisteredName() gives "minecraft:plains" style ids without touching renamed key APIs.
        String full = mc.level.getBiome(mc.player.blockPosition()).getRegisteredName();
        int colon = full.indexOf(':');
        return fromPath(colon >= 0 ? full.substring(colon + 1) : full);
    }

    // Order matters: specific names before generic ones (crimson_forest before forest, frozen_ocean before ocean).
    static Theme fromPath(String p) {
        if (p.contains("deep_dark")) return Theme.DEEP_DARK;
        if (p.contains("lush_caves")) return Theme.LUSH;
        if (p.contains("dripstone")) return Theme.DRIPSTONE;
        if (p.contains("mushroom")) return Theme.MUSHROOM;
        if (p.contains("cherry")) return Theme.CHERRY;
        if (p.contains("pale_garden")) return Theme.PALE;
        if (p.contains("crimson")) return Theme.CRIMSON;
        if (p.contains("warped")) return Theme.WARPED;
        if (p.contains("soul_sand")) return Theme.SOUL;
        if (p.contains("basalt")) return Theme.BASALT;
        if (p.contains("nether")) return Theme.NETHER;
        if (p.contains("end") && !p.contains("beach") && !p.contains("bend")) return Theme.END;
        if (p.contains("frozen") || p.contains("snowy") || p.contains("ice") || p.contains("grove")
                || p.contains("jagged")) return Theme.SNOW;
        if (p.contains("ocean") || p.contains("river") || p.contains("beach")) return Theme.WATER;
        if (p.contains("swamp") || p.contains("mangrove")) return Theme.SWAMP;
        if (p.contains("jungle") || p.contains("bamboo")) return Theme.JUNGLE;
        if (p.contains("badlands")) return Theme.BADLANDS;
        if (p.contains("desert")) return Theme.DESERT;
        if (p.contains("savanna")) return Theme.SAVANNA;
        if (p.contains("flower_forest") || p.contains("meadow") || p.contains("plains")) return Theme.MEADOW;
        if (p.contains("forest") || p.contains("taiga")) return Theme.FOREST;
        if (p.contains("peaks") || p.contains("windswept") || p.contains("stony") || p.contains("hills")) return Theme.MOUNTAIN;
        return Theme.MEADOW;
    }
}
