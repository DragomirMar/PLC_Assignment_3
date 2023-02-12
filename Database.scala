package webShop

class Database extends ShoppingCart {
  private var storedItems: Array[StoreItem] = Array.empty

  def delete(id: Int): Array[StoreItem] = {
    storedItems.filterNot(_.getId == id)
  }

  def search(name: String): Array[StoreItem] = {
    val item = new StoreItem(0,name, 0)

    if(storedItems.exists(_.getName == name)) item.logAction("found", name)
    else
      item.logAction("not found", name)

    storedItems.filter(_.getName == name)
  }

  def sortByValueAsc(): Array[StoreItem] = {
    storedItems.sortWith(_.getValue < _.getValue)
  }

  def sortByValueDesc(): Array[StoreItem] = {
    storedItems.sortWith(_.getValue > _.getValue)
  }

  def store(item: StoreItem): Array[StoreItem] = {
    item.logAction("stored", item.getName)
    storedItems = storedItems :+ item
    storedItems
  }

  def sumUp(): Int = {
    storedItems.foldLeft(0)(_ + _.getValue)
  }

  def filterByName(name: String, items: Array[StoreItem]): Array[StoreItem] = {
    items.filter(_.getName.contains(name)).sortWith(_.getValue < _.getValue)
  }

  def getStoreItems(): Array[StoreItem] = {
    storedItems
  }

  def doForEach(f: StoreItem => Unit,
                items: Array[StoreItem]): Array[StoreItem] = {
    for (el <- items) f(el)
    items
  }

  def filterByName1(name: String, items: Array[StoreItem]): Array[StoreItem] = {
    val returnvalue = items.filter(item => item.getName.contains(name))

    doForEach(item => item.logAction("found", item.getName), returnvalue)
  }

  def higherThan(value: Int): Array[StoreItem] = {
    val filtered = sortByValueAsc().filter(el => el.getValue > value)
    doForEach(item => item.logAction("found", item.getName), filtered)
  } //=======RABOTI======

}
