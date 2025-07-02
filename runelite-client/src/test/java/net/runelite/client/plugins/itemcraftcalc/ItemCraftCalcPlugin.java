package net.runelite.client.plugins.itemcraftcalc;

import com.google.inject.Provides;
import javax.inject.Inject;
import javax.swing.JPanel;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.ClientToolbar;
import net.runelite.client.ui.NavigationButton;

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
        navButton = NavigationButton.builder()
                .tooltip("Item Craft Calc")
                .icon(null) // pode colocar um ícone depois
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
