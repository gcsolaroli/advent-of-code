package it.imolab.adventOfCode._2021

final class Day16_Test extends TestSuite:

  test("answer 1") {
    Day16.parseHex("D2FE28") `shouldBe` Day16.parseBits("110 100 10111 11110 00101 000")
    Day16.bitsToInt(Day16.parseBits("110")) `shouldBe` 6
    Day16.BITS_Packet.parse("D2FE28")._1 `shouldBe` Day16.BITS_LiteralValue(6, 4, BigInt(2021))
    Day16.BITS_Packet.parse("38006F45291200")._1 `shouldBe` Day16.BITS_Operator(1, 6, List(Day16.BITS_LiteralValue(6, 4, BigInt(10)), Day16.BITS_LiteralValue(2, 4, BigInt(20))))
    Day16.BITS_Packet.parse("EE00D40C823060")._1 `shouldBe` Day16.BITS_Operator(7, 3, List(Day16.BITS_LiteralValue(2, 4, BigInt(1)), Day16.BITS_LiteralValue(4, 4, BigInt(2)), Day16.BITS_LiteralValue(1, 4, BigInt(3))))
    Day16.BITS_Packet.parse("8A004A801A8002F478")._1 `shouldBe` Day16.BITS_Operator(4, 2, List(Day16.BITS_Operator(1, 2, List(Day16.BITS_Operator(5, 2, List(Day16.BITS_LiteralValue(6, 4, BigInt(15))))))))
    Day16.versionSum(Day16.BITS_Packet.parse("8A004A801A8002F478")._1) `shouldBe` 16
    Day16.BITS_Packet.parse("620080001611562C8802118E34")._1 `shouldBe` Day16.BITS_Operator(3, 0, List(
      Day16.BITS_Operator(0, 0, List(Day16.BITS_LiteralValue(0, 4, BigInt(10)), Day16.BITS_LiteralValue(5, 4, BigInt(11)))),
      Day16.BITS_Operator(1, 0, List(Day16.BITS_LiteralValue(0, 4, BigInt(12)), Day16.BITS_LiteralValue(3, 4, BigInt(13))))
    ))
    Day16.versionSum(Day16.BITS_Packet.parse("620080001611562C8802118E34")._1) `shouldBe` 12
    Day16.versionSum(Day16.BITS_Packet.parse("C0015000016115A2E0802F182340")._1) `shouldBe` 23
    Day16.versionSum(Day16.BITS_Packet.parse("A0016C880162017C3686B18A3D4780")._1) `shouldBe` 31
  }

  test("answer 2") {
    Day16.BITS_Packet.parse("C200B40A82")._1.value `shouldBe` 3
    Day16.BITS_Packet.parse("04005AC33890")._1.value `shouldBe` 54
    Day16.BITS_Packet.parse("880086C3E88112")._1.value `shouldBe` 7
    Day16.BITS_Packet.parse("CE00C43D881120")._1.value `shouldBe` 9
    Day16.BITS_Packet.parse("D8005AC2A8F0")._1.value `shouldBe` 1
    Day16.BITS_Packet.parse("F600BC2D8F")._1.value `shouldBe` 0
    Day16.BITS_Packet.parse("9C005AC2F8F0")._1.value `shouldBe` 0
    Day16.BITS_Packet.parse("9C0141080250320F1802104A08")._1.value `shouldBe` 1
  }