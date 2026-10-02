package knnyght.modid;

import eu.midnightdust.lib.config.MidnightConfig;
import knnyght.modid.config.ModConfig;
import net.fabricmc.api.ModInitializer;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.item.consume.ClearAllEffectsConsumeEffect;
import net.minecraft.util.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KnnyghtSBetterFireResistanceVisuals implements ModInitializer {
	public static final String MOD_ID = "knnyghts-better-fire-resistance-visuals";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static boolean itemClearsEffects(ItemStack item) {
		ConsumableComponent component = item.getComponents().get(DataComponentTypes.CONSUMABLE);
		if (component == null) return false;
		return component.onConsumeEffects().contains(ClearAllEffectsConsumeEffect.INSTANCE);
	}

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		MidnightConfig.init(MOD_ID, ModConfig.class);
		LOGGER.info("Hello Fabric world!");
	}

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}
