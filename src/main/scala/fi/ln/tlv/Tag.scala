package fi.ln.tlv

class Tag(var tag: IndexedSeq[Int]) {
  override def toString = tag.map[String, IndexedSeq[String]]((charValue: Int) => "%02X".format(charValue)).mkString
}

object Tag {

  final val SECOND_BYTE_INDICATOR = 0x1F // 0001 1111
  final val NEXT_BYTE_INDICATOR = 0x80  // 1000 0000

  def hasMoreBytes(byteIndex: Int, byteValue: Int) = byteIndex match {
    case 1 => secondTagByteExits(byteValue)
    case _ => nextTagByteExists(byteValue)
  }

  private[this] def secondTagByteExits(tagByte: Int) = (tagByte & SECOND_BYTE_INDICATOR) == SECOND_BYTE_INDICATOR

  private[this] def nextTagByteExists(tagByte: Int) = (tagByte >= NEXT_BYTE_INDICATOR)

}

