package fi.ln.tlv

import element.PrimitiveElement
import java.io.{EOFException, File}
import collection.mutable.ListBuffer

class Parser {

  def parseFile(file: File) = {
    val stream = new Stream(file)

    val tlv = TLV()

    try while (true) tlv += readElement(stream) catch {
      case e: EOFException =>
    }

    tlv
  }

  def readElement(stream: Stream) = new PrimitiveElement(
    new Tag(stream.readBytesWhile(Tag.hasMoreBytes)),
    new Data(stream.readBytes(Length.fromBytes(stream.readBytesWhile(Length.getHasMoreBytes()))))
  )
}
