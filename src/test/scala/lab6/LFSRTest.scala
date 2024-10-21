package lab6

import chisel3._
import chisel3.testers._
import chisel3.util._
import chiseltest._
import chiseltest.formal._
import chiseltest.simulator.WriteVcdAnnotation
import org.scalatest.flatspec.AnyFlatSpec

class LFSRTest extends AnyFlatSpec with ChiselScalatestTester {
  "Test " should "show correctly" in {
    test(new LFSR()) { c =>
      // Test Add
      c.io.in.poke(1.U)
      c.clock.step(1)
      c.io.value.expect("b10000000".U)
      c.io.in.poke(0.U)
      c.clock.step(1)
      c.io.value.expect("b01000000".U)
      c.io.in.poke(1.U)
      c.clock.step(1)
      c.io.value.expect("b10100000".U)
    }
  }

}