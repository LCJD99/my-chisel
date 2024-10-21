package lab1

import chisel3._
import _root_.circt.stage.ChiselStage
import chisel3.util.MuxLookup

class MUX extends Module{
  val io = IO(new Bundle {
    val x0 = Input(UInt(2.W))
    val x1 = Input(UInt(2.W))
    val x2 = Input(UInt(2.W))
    val x3 = Input(UInt(2.W))
    val y  = Input(UInt(2.W))
    val f  = Output(UInt(2.W))
  })

  io.f := MuxLookup(io.y, 0.U(2.W))( Seq(
    0.U -> io.x0,
    1.U -> io.x1,
    2.U -> io.x2,
    3.U -> io.x3
  ))
}