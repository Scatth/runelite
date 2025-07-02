package net.runelite.client.plugins.itemcraftcalc;

import net.runelite.client.ui.PluginPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ItemCraftCalcPanel extends PluginPanel
{
    private JTextField searchField;
    private JTextArea resultArea;

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
    }

    private void pesquisarItem()
    {
        String termo = searchField.getText().trim();
        if(termo.isEmpty())
        {
            resultArea.setText("Digite o nome de um item para pesquisar.");
        }
        else
        {
            // Aqui depois vamos pesquisar nas receitas e mostrar os cálculos
            resultArea.setText("Você pesquisou: " + termo + "\n\n(Em breve mostraremos as rotas e custos!)");
        }
    }
}
