The patterns I plan to implement are Strategy and Observer: 

observer will give me an advantage because when implemented this will allow for my dataPanel to be notified whenever data is changed from statsPanel or detailsPanel
  some interfaces that will be implemented are Observer and Subject - The DataPanel class implements the Subject interface, holding a list of Observer instances (like DetailsPanel and StatsPanel).
  Observer - The DetailsPanel and StatsPanel classes implement the Observer interface, updating themselves whenever DataPanel calls notifyObservers().
  some advantages of this is that DataPanel does not need to know the details of the other panels, yet the observer is notified and will update themselves.

Strategy Pattern will include a FilterStrategy interface, this allows for different strategies to be used as well as allows for interchangeability such as my classes FilterByGrowth and FilterByUSA
  the DataPanel will use different strategies to modify and display the data based on what the user selects
  some advantages of this makes the DataPanel closed meaning there is no need to change the DataPanel to add new filters however can extend the filter option with addition of newfilter strategies
