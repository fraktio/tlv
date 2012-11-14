package fi.ln.tlv

class TLV(val tag: Tag, val data: Data) {
  override def toString = "Tag: %s\nLength: %s\nData: %s\n".format(tag, data.size, data)

  override def equals(other: Any) = other match {
    case other: TLV => tag == other.tag && data == other.data
    case _ => false
  }
}
