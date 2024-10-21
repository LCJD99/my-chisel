package lab6

import chisel3._
import chisel3.util._

class RandomNum extends Module {
  val io = IO(new Bundle {
    val low = Output(UInt(8.W)) 
    val high = Output(UInt(8.W)) 
  })
  
  val lfsr = Module(new LFSR)
  val display1 = Module(new SevenSegmentDisplay)
  val display2 = Module(new SevenSegmentDisplay)
  lfsr.clock := clock
  val v = lfsr.io.value

  lfsr.io.in := Mux(v =/= 0.U, (v(0) ^ v(2) ^ v(3) ^ v(4)), 1.U)
  display1.io.valid := 1.U
  display1.io.in := v(3, 0)
  display2.io.valid := 1.U
  display2.io.in := v(7, 4)
  io.low := display1.io.out
  io.high := display2.io.out

}