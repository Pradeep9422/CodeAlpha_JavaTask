import java.util.*;

class Stock {
    private String symbol;
    private double price;

    public Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPrice() {
        return price;
    }

    public void updatePrice(double newPrice) {
        this.price = newPrice;
    }
}

class Market {
    private Map<String, Stock> stocks = new HashMap<>();
    private Random random = new Random();

    public void addStock(String symbol, double price) {
        stocks.put(symbol, new Stock(symbol, price));
    }

    public void updateMarket() {
        for (Stock stock : stocks.values()) {
            double change = (random.nextDouble() - 0.5) * 10; // Random price fluctuation
            stock.updatePrice(Math.max(1, stock.getPrice() + change));
        }
    }

    public Stock getStock(String symbol) {
        return stocks.get(symbol);
    }

    public void displayMarket() {
        System.out.println("Current Market Prices:");
        for (Stock stock : stocks.values()) {
            System.out.println(stock.getSymbol() + " : $" + stock.getPrice());
        }
    }
}

class Portfolio {
    private Map<String, Integer> holdings = new HashMap<>();
    private double balance;
    private Market market;

    public Portfolio(double balance, Market market) {
        this.balance = balance;
        this.market = market;
    }

    public void buyStock(String symbol, int quantity) {
        Stock stock = market.getStock(symbol);
        if (stock != null) {
            double cost = stock.getPrice() * quantity;
            if (balance >= cost) {
                balance -= cost;
                holdings.put(symbol, holdings.getOrDefault(symbol, 0) + quantity);
                System.out.println("Bought " + quantity + " shares of " + symbol);
            } else {
                System.out.println("Insufficient balance to buy " + symbol);
            }
        } else {
            System.out.println("Stock not found.");
        }
    }

    public void sellStock(String symbol, int quantity) {
        if (holdings.getOrDefault(symbol, 0) >= quantity) {
            Stock stock = market.getStock(symbol);
            balance += stock.getPrice() * quantity;
            holdings.put(symbol, holdings.get(symbol) - quantity);
            if (holdings.get(symbol) == 0) holdings.remove(symbol);
            System.out.println("Sold " + quantity + " shares of " + symbol);
        } else {
            System.out.println("Not enough shares to sell.");
        }
    }

    public void displayPortfolio() {
        System.out.println("Portfolio Holdings:");
        for (Map.Entry<String, Integer> entry : holdings.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue() + " shares");
        }
        System.out.println("Balance: $" + balance);
    }
}

public class StockTradingPlatform {
    public static void main(String[] args) {
        Market market = new Market();
        market.addStock("AAPL", 150);
        market.addStock("GOOGL", 2800);
        market.addStock("AMZN", 3400);
        
        Portfolio portfolio = new Portfolio(10000, market);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            market.updateMarket();
            market.displayMarket();
            portfolio.displayPortfolio();
            
            System.out.println("Enter command (buy/sell/exit): ");
            String command = scanner.next();
            if (command.equals("exit")) break;
            
            System.out.print("Enter stock symbol: ");
            String symbol = scanner.next();
            
            System.out.print("Enter quantity: ");
            int quantity = scanner.nextInt();
            
            if (command.equals("buy")) {
                portfolio.buyStock(symbol, quantity);
            } else if (command.equals("sell")) {
                portfolio.sellStock(symbol, quantity);
            }
        }
        scanner.close();
    }
}
