package fi.ln.tlv

import org.scalatest.FunSuite

class TagSuite extends FunSuite {
  trait TestSets {
    val tag1 = new Tag(List(0xAA).toIndexedSeq)
    val tag2 = new Tag(List(0xAA).toIndexedSeq)
    val tag3 = new Tag(List(0xBB).toIndexedSeq)
  }

  test("tag should be equal to itself") {
    new TestSets {
      assert(tag1 === tag1)
    }
  }

  test("tag should be equal to another tag with same data") {
    new TestSets {
      assert(tag1 === tag2)
    }
  }

  test("tag should not equal to another tag with different data") {
    new TestSets {
      assert(tag1 != tag3)
    }
  }
}