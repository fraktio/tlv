package fi.ln.tlv

import collection.mutable.ListBuffer

class TLV(private val elements: ListBuffer[Element]) {

  def this() = this(ListBuffer[Element]())

  override def toString = "TLV: %s".format(elements)

  def +=(element: Element) = elements += element
  def isEmpty = elements.isEmpty

  override def equals(other: Any) = other match {
    case other: TLV => elements == other.elements
    case _ => false
  }
}

object TLV {
  def apply(elements: Element*) = {
    val tlv = new TLV()
    tlv.elements ++= elements
    tlv
  }
}