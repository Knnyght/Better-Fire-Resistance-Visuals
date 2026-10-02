package knnyght.modid.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

import static knnyght.modid.KnnyghtSBetterFireResistanceVisuals.MOD_ID;

public class ModMenuEntry implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> ModConfig.getScreen(parent, MOD_ID);
    }
}
