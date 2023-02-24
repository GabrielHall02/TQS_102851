package org.example;

import java.util.ArrayList;
import java.util.List;

public class StocksPortfolio {
    private List<Stock> stocks;
    private IStockMarketService stockmarket;

    public StocksPortfolio(IStockMarketService stockmarket) {
        this.stockmarket = stockmarket;
        this.stocks = new ArrayList<>();
    }

    public void addStock(Stock stock) {
        this.stocks.add(stock);
    }

    public double getTotalValue() {
        double total = 0.0;
        for (Stock s : this.stocks) {
            total += s.getQuantity() * this.stockmarket.lookUpPrice(s.getLabel());
        }
        return total;
    }
}
