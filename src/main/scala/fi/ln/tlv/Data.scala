package fi.ln.tlv

class Data(var value: IndexedSeq[Int]) {
  override def toString = value.map[String,IndexedSeq[String]]((char: Int) => "%c".format(char)).mkString

  def size = value.size
}

