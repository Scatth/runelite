package net.runelite.client.plugins.itemcraftcalc;

import com.google.inject.Provides;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.swing.*;

import lombok.extern.slf4j.Slf4j;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.ClientToolbar;
import net.runelite.client.ui.NavigationButton;
import net.runelite.client.util.ImageUtil;

import java.awt.image.BufferedImage;

@Slf4j
@PluginDescriptor(
        name = "Item Craft Calculator",
        description = "Calcula o custo de produção de itens (beta)",
        tags = {"craft", "lucro", "alchemy", "cost", "calculo"}
)
public class ItemCraftCalcPlugin extends Plugin
{
    @Inject
    private ClientToolbar clientToolbar;

    private NavigationButton navButton;
    private ItemCraftCalcPanel panel;

    @Override
    protected void startUp()
    {
        panel = new ItemCraftCalcPanel();

        // Carregar o ícone do plugin como BufferedImage
        BufferedImage iconImage = null;
        try
        {
            iconImage = ImageIO.read(getClass().getResourceAsStream("/skill_icons_small/crafting.png"));
            // OU se preferir usar o utilitário (pode não existir em todas as versões):
            // iconImage = ImageUtil.loadImageResource(getClass(), "/skill_icons_small/crafting.png");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        navButton = NavigationButton.builder()
                .tooltip("Item Craft Calc")
                .icon(iconImage)
                .panel(panel)
                .build();

        clientToolbar.addNavigation(navButton);
        log.info("Item Craft Calculator started!");
    }

    @Override
    protected void shutDown()
    {
        clientToolbar.removeNavigation(navButton);
        log.info("Item Craft Calculator stopped!");
    }

    @Provides
    ItemCraftCalcConfig getConfig(ConfigManager configManager)
    {
        return configManager.getConfig(ItemCraftCalcConfig.class);
    }
}
