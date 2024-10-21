package lab3

import chisel3._
import chisel3.testers._
import chisel3.util._
import chiseltest._
import chiseltest.formal._
import chiseltest.simulator.WriteVcdAnnotation
import org.scalatest.flatspec.AnyFlatSpec

class ALUTest extends AnyFlatSpec with ChiselScalatestTester {
  "Test alu add" should "show correctly" in {
    test(new ALU()) { c =>
      // Test Add
      c.io.in_a.poke(1.S)
      c.io.in_b.poke(1.S)
      c.io.select.poke("b000".U)
      c.clock.step(1)
      c.io.out_c.expect(2.S)
      c.io.carry.expect(false.B)
      c.io.overflow.expect(false.B)
      c.io.zero.expect(false.B)

      c.io.in_a.poke((1 << 31).S)
      c.io.in_b.poke((1 << 31).S)
      c.io.select.poke("b000".U)
      c.clock.step(1)
      c.io.overflow.expect(true.B)

      c.io.in_a.poke(1.S)
      c.io.in_b.poke(1.S)
      c.io.select.poke("b001".U)
      c.clock.step(1)
      c.io.out_c.expect(0.S)
      c.io.carry.expect(false.B)
      c.io.overflow.expect(false.B)
      c.io.zero.expect(true.B)

    }
  }

}