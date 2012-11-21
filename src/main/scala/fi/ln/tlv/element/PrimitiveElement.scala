package fi.ln.tlv.element

import fi.ln.tlv.{Data, Tag, Element}

class PrimitiveElement(val tag: Tag, val data: Data) extends Element {
  override def toString = "Tag: %s\nLength: %s\nData: %s\n".format(tag, data.size, data)

  override def equals(other: Any) = other match {
    case other: PrimitiveElement => tag == other.tag && data == other.data
    case _ => false
  }
}
