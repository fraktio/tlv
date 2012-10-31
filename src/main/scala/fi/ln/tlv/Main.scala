package fi.ln.tlv

import java.io.File

object Main extends App {
  println(new Parser().parseFile(new File("tlvtest.dat")).mkString)
}
