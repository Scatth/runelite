package net.runelite.client.plugins.itemcraftcalc;

import net.runelite.client.ui.PluginPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

public class ItemCraftCalcPanel extends PluginPanel
{
    private JTextField searchField;
    private JTextArea resultArea;

    private Map<String, OsrsItem> itemMap;

    public ItemCraftCalcPanel()
    {
        setLayout(new BorderLayout());

        // Barra de pesquisa
        JPanel searchPanel = new JPanel(new BorderLayout());
        searchField = new JTextField();
        JButton searchButton = new JButton("Pesquisar");

        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(searchButton, BorderLayout.EAST);

        // Área de resultado
        resultArea = new JTextArea();
        resultArea.setEditable(false);

        // Adiciona ao painel
        add(searchPanel, BorderLayout.NORTH);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        // Ação de pesquisar (por enquanto só exibe o texto pesquisado)
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pesquisarItem();
            }
        });
        searchField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pesquisarItem();
            }
        });
        // Carrega o JSON uma vez ao inicializar
        loadItemsJson();
    }

    private void loadItemsJson()
    {
        try
        {
            InputStreamReader reader = new InputStreamReader(getClass().getResourceAsStream("/items-complete.json"));
            itemMap = new Gson().fromJson(reader, new TypeToken<Map<String, OsrsItem>>(){}.getType());
            reader.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            itemMap = null;
        }
    }

    private void pesquisarItem()
    {
        String termo = searchField.getText().trim().toLowerCase();
        if (termo.isEmpty())
        {
            resultArea.setText("Digite o nome de um item para pesquisar.");
            return;
        }
        if (itemMap == null)
        {
            resultArea.setText("Erro ao carregar base de dados dos itens!");
            return;
        }

        OsrsItem found = null;
        for (OsrsItem item : itemMap.values())
        {
            if (item.name != null && item.name.toLowerCase().contains(termo))
            {
                found = item;
                break;
            }
        }

        if (found != null)
        {
            StringBuilder sb = new StringBuilder();
            sb.append("Item: ").append(found.name).append("\n");
            if (found.crafted_from != null)
            {
                sb.append("Ingredientes:\n");
                for (OsrsItemIngredient ingr : found.crafted_from)
                {
                    sb.append(" - ").append(ingr.name)
                            .append(" x").append(ingr.quantity)
                            .append("\n");
                }
            }
            else
            {
                sb.append("Este item não tem receita de crafting na base.");
            }
            resultArea.setText(sb.toString());
        }
        else
        {
            resultArea.setText("Item não encontrado na base.");
        }
    }
}