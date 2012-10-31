package fi.ln.tlv

import java.io.{EOFException, File}
import collection.mutable.ListBuffer

class Parser {

  def parseFile(file: File) = {
    val stream = new Stream(file)

    val tlvs = new ListBuffer[TLV]

    try while (true) tlvs += readTLV(stream) catch {
      case e: EOFException =>
    }

    tlvs
  }

  def readTLV(stream: Stream) = new TLV(
    new Tag(stream.readBytesWhile(Tag.hasMoreBytes)),
    new Data(stream.readBytes(Length.fromBytes(stream.readBytesWhile(Length.getHasMoreBytes()))))
  )
}
