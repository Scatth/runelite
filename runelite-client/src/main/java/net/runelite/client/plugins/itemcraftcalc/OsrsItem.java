package net.runelite.client.plugins.itemcraftcalc;

import java.util.List;

// Classe para um item do osrsbox-db
public class OsrsItem {
    public int id;
    public String name;
    public Boolean members;
    public Boolean tradeable;
    public Boolean stackable;
    public Integer buy_limit;      // Limite de compra no GE
    public Integer ge_price;       // Preço do Grand Exchange
    public Integer highalch;       // Valor de High Alch
    public Integer lowalch;        // Valor de Low Alch
    public Integer store_price;    // Preço de loja NPC
    public Double weight;
    public String examine;         // Texto de examine no jogo
    public List<OsrsItemIngredient> crafted_from; // Lista de ingredientes, se item for craftable
    // Tem outros campos no JSON, mas esses são os mais úteis para crafting/lucro

    // Para debug, pode sobrescrever toString()
    @Override
    public String toString() {
        return name + " (id=" + id + ")";
    }
}
