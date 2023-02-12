package webShop

class StoreItem(override var id: Int, override var name: String, override var value: Int) extends Item with Logger{

  override def logAction(actionName: String, name: String): Unit = {
    println(s"$name $actionName")
  }

  def getId: Int = id
  def getName: String = name
  def getValue: Int = value
}

