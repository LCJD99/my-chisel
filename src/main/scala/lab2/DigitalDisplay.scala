package lab2

import chisel3._
import chisel3.util._

class DigitalDisplay extends Module {
  val io = IO(new Bundle{
    val in  = Input(UInt(8.W))
    val out = Output(UInt(7.W))
    val out_num = Output(UInt(4.W))
  })

  val encoder = Module(new PriorityEncoderWithValid)
  val decoder = Module(new SevenSegmentDisplay)

  encoder.io.in := io.in
  decoder.io.in := Cat(0.U(1.W), encoder.io.out)
  decoder.io.valid := encoder.io.valid
  io.out := decoder.io.out
  io.out_num := Cat(encoder.io.out, encoder.io.valid)
}