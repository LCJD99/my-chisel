package lab3

import chisel3._
import chisel3.util._

class ALU extends Module {
  val io = IO(new Bundle {
    val select = Input(UInt(3.W))
    val in_a = Input(SInt(32.W))
    val in_b = Input(SInt(32.W))
    val out_c = Output(SInt(32.W))
    val zero = Output(Bool())
    val overflow = Output(Bool())
    val carry = Output(Bool())
  })

  val sum = io.in_a +& io.in_b
  val diff = io.in_a -& io.in_b
  io.out_c := MuxLookup(io.select, 0.S(32.W))(Seq (
    0.U -> (sum),
    1.U -> (diff),
    2.U -> (~io.in_a),
    3.U -> (io.in_a & io.in_b),
    4.U -> (io.in_a | io.in_b),
    5.U -> (io.in_a ^ io.in_b),
    6.U -> (Mux(io.in_a < io.in_b, 1.S, 0.S)),
    7.U -> (Mux(io.in_a === io.in_b, 1.S, 0.S)),
  ))
  io.zero := Mux(io.select === 0.U, sum === 0.S, Mux(io.select === 1.U, diff === 0.S, false.B))
  io.carry := Mux(io.select === 0.U, sum(32), Mux(io.select === 1.U, io.in_a < io.in_b, false.B))
  io.overflow := Mux(io.select === 0.U, // 加法溢出判断
    (io.in_a(31) === io.in_b(31)) && (io.in_a(31) =/= sum(31)),
    Mux(io.select === 1.U, // 减法溢出判断
      (io.in_a(31) =/= io.in_b(31)) && (io.in_a(31) =/= diff(31)),
      false.B
    )
  )
}