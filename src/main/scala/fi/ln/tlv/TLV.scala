package fi.ln.tlv

class TLV(tag: Tag, data: Data) {
  override def toString = "Tag: %s\nLength: %s\nData: %s\n".format(tag, data.size, data)
}
