// See LICENSE for license details.
package freechips.rocketchip.util

import Chisel._

class Tracer(val tracerName: String) extends Module {
  val io = new Bundle {
    val signal = Bool(INPUT)
  }
  val signal = io.signal
  val prev = RegNext(signal)
  val (t, _) = Counter(true.B, 1 << 24)
  when(prev =/= signal) {
    printf("[%d] ", t)
    printf(s"Tracer $tracerName toggled ")
    printf("from %b to %b.\n", prev, signal)
  }
}
