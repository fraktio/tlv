package fi.ln.tlv

import java.io.{EOFException, BufferedInputStream, FileInputStream, File}
import collection.mutable

class Stream(file: File) {

  val fileInput = new FileInputStream(file)
  val bufferedStream = new BufferedInputStream(fileInput)

  def readByte: Int = {
    try
      bufferedStream.read() match {
        case -1 => throw new EOFException()
        case byte: Int => byte
      }
    catch {
      case e: EOFException => {
        close
        throw e
      }
    }
  }

  def readBytes(count: Int) = readBytesWhile((byteIndex: Int, byteValue: Int) => byteIndex < count)

  def readBytesWhile(hasMoreBytes: (Int, Int) => (Boolean)) = {
    val bytes = new mutable.ListBuffer[Int]
    do
      bytes += readByte
    while (hasMoreBytes(bytes.length, bytes.last))
    bytes.toIndexedSeq
  }

  def close = fileInput.close
}


