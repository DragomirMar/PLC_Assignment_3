package webShop

object MainApp {

  def readCSV(filename: String): List[StoreItem] = {
    var items: List[StoreItem] = List()
    val bufferedSource = io.Source.fromFile(filename)
    for(line <- bufferedSource.getLines.drop(1)) {
      val cols = line.split(",").map(_.trim)
      val item: StoreItem = new StoreItem(cols(0).toInt, cols(1), cols(2).toInt)
      items = items :+ item
    }
    bufferedSource.close
    items
  }

  def main(args: Array[String]): Unit = {
    val database: Database = new Database()
    val items = readCSV("data.csv") // list with all item from the csv file

    //adding every item from items to database
    items.foreach(database.store(_))

    println("--- SUM UP ---")
    println(database.sumUp())

    println("--- FILTERED BY ASUS ---")
    database.filterByName("ASUS", items.toArray).foreach(item => println(item.getName))
    println(database.filterByName("ASUS", items.toArray).length)

    println("--- FILTERED BY Lenovo ---")
    database.filterByName("Lenovo", items.toArray).foreach(item => println(item.getName))
    println(database.filterByName("Lenovo", items.toArray).length)

    println("--- FILTERED BY HP ---")
    database.filterByName("HP", items.toArray).foreach(item => println(item.getName))
    println(database.filterByName("HP", items.toArray).length)

    println("--- SORTED ITEMS ---")
    database.sortByValueDesc().foreach(item => println(item.getName + ' ' + item.getValue))

    println("----------------")

    database.higherThan(800)


    println("===============")
    database.filterByName1("HP", database.getStoreItems())
  }
}
