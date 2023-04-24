# Real time stocks.

We're working on a startup that is a Stock Manager! Currently, we're being 
asked to develop a screen where you can see the stocks that the business
has deemed as featured and where we can show how their prices fluctuate.

## Description

### Stock

A stock consists of a Ticker (it's stock market identifier like AAPL),
it's current price (a double), and the name of the company behind that stock.

### Scope

A teammate has already worked on the repository to retrieve
the featured stocks, our task is to focus on the UI and connect
the layers of the app in a scalable, maintainable way
(using a presentation pattern like MVVM, MVP, VIPER, MVC).

Take a look at `real-time-stocks-mockup.png` for an idea of how the screen might look like.

### Acceptance criteria:
* We can see the stocks in a RecyclerView and show how their prices fluctuate every 10 seconds.
* Display the list in a way that efficiently handles the stock updates.
* Write code that is maintainable and scalable.
