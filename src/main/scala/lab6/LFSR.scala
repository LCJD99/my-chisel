package lab6


import chisel3._
import chisel3.util._

class LFSR extends Module {
  val io = IO(new Bundle {
    val in = Input(Bool())
    val value = Output(UInt(8.W))
  })

  val shiftReg = RegInit(0.U(8.W))
  
  shiftReg := Cat(io.in, shiftReg(7, 1))
  io.value := shiftReg
}