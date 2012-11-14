package fi.ln.tlv

trait IndexedSequenceable {
  def toIndexedSeq: IndexedSeq[Int]

  override def equals(other: Any) = other match {
    case other: IndexedSequenceable => other.toIndexedSeq == toIndexedSeq
    case _ => false
  }
}
