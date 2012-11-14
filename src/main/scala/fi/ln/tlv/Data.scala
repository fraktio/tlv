package fi.ln.tlv

class Data(val value: IndexedSeq[Int]) extends IndexedSequenceable {
  override def toString = value.map[String,IndexedSeq[String]]((char: Int) => "%c".format(char)).mkString

  def size = value.size

  def toIndexedSeq = value
}

