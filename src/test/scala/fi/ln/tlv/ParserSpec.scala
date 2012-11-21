
package fi.ln.tlv

import element.PrimitiveElement
import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen
import collection.mutable.{ListBuffer, Stack}
import java.io.File
import fi.ln.tlv.element.PrimitiveElement

class ParserSpec extends FeatureSpec with GivenWhenThen {

  feature("Parse TLV file") {

    info("As a system receiving messages")
    info("I want to be able to parse TLV files")
    info("So that I can receive messages")

    scenario("Parser is given a valid TLV file") {
      given("a valid TLV file")
      val file = new File(getClass.getResource("/tlvtest.dat").getFile)

      when("file is parsed")
      val tlv = new Parser().parseFile(file)

      then("a non-empty list of tag and value pairs should be returned")
      assert(!tlv.isEmpty)

      and("the elements are valid")

      implicit def string2IndexedSeq(data: String) = data.toCharArray.toIndexedSeq.map((char: Char) => char.toInt)

      assert(tlv === TLV(
        new PrimitiveElement(new Tag(IndexedSeq(0xFF, 0x03)), new Data("FOOBAR")),
        new PrimitiveElement(new Tag(IndexedSeq(0xFF, 0xAA, 0x01)), new Data("OSKARI")),
        new PrimitiveElement(new Tag(IndexedSeq(0x03)), new Data("X" * 300))
      ))
    }

    scenario("Parser is given a constructed TLV file") {
      given("a valid constructed TLV file")
      val file = new File(getClass.getResource("/bertlv_with_constructed_small").getFile)

      when("file is parsed")
      val tlvs = new Parser().parseFile(file)

      then("a non-empty list of tag and value pairs should be returned")
      assert(!tlvs.isEmpty)

      implicit def string2IndexedSeq(data: String) = data.toCharArray.toIndexedSeq.map((char: Char) => char.toInt)
/*
      assert(tlvs === new TLV(ListBuffer(
        new PrimitiveElement(new Tag(IndexedSeq(0xDF, 0xAA, 0x01)), new Data("ppppp")),
        new PrimitiveElement(new Tag(IndexedSeq(0xDF, 0xAA, 0x01)), new Data("ppppp")),
        new ConstructedElement(new Tag(IndexedSeq(0xFF, 0xAA, 0x01)), new TLV(ListBuffer(
          new PrimitiveElement(new Tag(IndexedSeq(0xDF, 0xAA, 0x02)), new Data("kkkkk")),
          new PrimitiveElement(new Tag(IndexedSeq(0xDF, 0xAA, 0x03)), new Data("sssss")),
          new ConstructedElement(new Tag(IndexedSeq(0xFF, 0xAA, 0x02)), new TLV(ListBuffer(
            new PrimitiveElement(new Tag(IndexedSeq(0xDF, 0xAA, 0x02)), new Data("kkkkk")),
            new PrimitiveElement(new Tag(IndexedSeq(0xDF, 0xAA, 0x03)), new Data("sssss"))
          )))
        ))),
        new ConstructedElement(new Tag(IndexedSeq(0xFF, 0xAA, 0x01)), new TLV(ListBuffer(
          new PrimitiveElement(new Tag(IndexedSeq(0xDF, 0xAA, 0x02)), new Data("kkkkk")),
          new PrimitiveElement(new Tag(IndexedSeq(0xDF, 0xAA, 0x03)), new Data("sssss")),
          new ConstructedElement(new Tag(IndexedSeq(0xFF, 0xAA, 0x02)), new TLV(ListBuffer(
            new PrimitiveElement(new Tag(IndexedSeq(0xDF, 0xAA, 0x02)), new Data("kkkkk")),
            new PrimitiveElement(new Tag(IndexedSeq(0xDF, 0xAA, 0x03)), new Data("sssss"))
          )))
        ))),
        new PrimitiveElement(new Tag(IndexedSeq(0xDF, 0xAA, 0x04)), new Data("rrrrr")),
        new PrimitiveElement(new Tag(IndexedSeq(0xDF, 0xAA, 0x04)), new Data("rrrrr"))
      )))
*/
    }
  }

}