package fi.ln.tlv

object Length {

  private final val MSB_SET_INDICATOR = 0x80 // 1000 0000

  private def isSingleByte(lengthByte: Int) = (lengthByte < MSB_SET_INDICATOR)

  private def calculateByteCount(firstLengthByte: Int) = (firstLengthByte - MSB_SET_INDICATOR)

  private def fromSingleByte(byte: Int) = byte

  private def fromMultipleBytes(bytes: Seq[Int]) = (0 /: bytes)((result, byte) => (result << 8) | byte)

  def getHasMoreBytes() = {
    var length = 0
    (byteIndex: Int, byteValue: Int) => byteIndex match {
      case 1 => {
        length = calculateByteCount(byteValue)
        !isSingleByte(byteValue)
      }
      case _ => byteIndex <= length
    }
  }

  def fromBytes(bytes: Seq[Int]): Int = bytes.size match {
    case 1 => fromSingleByte(bytes.last)
    case _ => fromMultipleBytes(bytes.tail)
  }
}
