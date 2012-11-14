
package fi.ln.tlv

import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen
import collection.mutable.{ListBuffer, Stack}
import java.io.File

class ParserSpec extends FeatureSpec with GivenWhenThen {

  feature("Parse TLV file") {

    info("As a system receiving messages")
    info("I want to be able to parse TLV files")
    info("So that I can receive messages")

    scenario("Parser is given a valid TLV file") {
      given("a valid TLV file")
      val file = new File(getClass.getResource("/tlvtest.dat").getFile)

      when("file is parsed")
      val tlvs = new Parser().parseFile(file)

      then("a non-empty list of tag and value pairs should be returned")
      assert(!tlvs.isEmpty)

      and("the elements are valid")

      implicit def string2IndexedSeq(data: String) = data.toCharArray.toIndexedSeq.map((char: Char) => char.toInt)

      assert(tlvs === ListBuffer(
        new TLV(new Tag(IndexedSeq(0xFF, 0x03)), new Data("FOOBAR")),
        new TLV(new Tag(IndexedSeq(0xFF, 0xAA, 0x01)), new Data("OSKARI")),
        new TLV(new Tag(IndexedSeq(0x03)), new Data("X" * 300))
      ))
    }
  }

}