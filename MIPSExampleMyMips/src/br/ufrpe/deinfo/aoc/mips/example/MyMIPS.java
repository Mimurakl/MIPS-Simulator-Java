/*------------------------------------------------------------------------------
 *
 *   UNIVERSIDADE FEDERAL RURAL DE PERNAMBUCO - UFRPE (www.ufrpe.br)
 *   DEPARTAMENTO DE ESTAT�STICA E INFORM�TICA - DEINFO (www.deinfo.ufrpe.br)
 *
 *------------------------------------------------------------------------------
 *   Exemplo de utiliza��o do Simulador.
 *
 *   Copyright (C) 2015  Andr� Aziz Camilo de Araujo (andreaziz@deinfo.ufrpe.br)
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>. 
 *------------------------------------------------------------------------------
 */
package br.ufrpe.deinfo.aoc.mips.example;

import java.io.IOException;

import jline.console.ConsoleReader;
import br.ufrpe.deinfo.aoc.mips.MIPS;
import br.ufrpe.deinfo.aoc.mips.Simulator;
import br.ufrpe.deinfo.aoc.mips.State;


//R-type:  |  Opcode  |     rs     |    rt    |    rd    |   shamt   |   funct   |
//			  (6 bits)    (5 bits)    (5 bits)   (5 bits)    (5 bits)    (6 bits)


//I-type:  |  Opcode  |     rs     |    rt    |            immediate            |
//			  (6 bits)    (5 bits)    (5 bits)              (16 bits)
			

//J-type:  |  Opcode  |                    address                           |
//			  (6 bits)                     (26 bits)
			


public class MyMIPS implements MIPS {
	
	@SuppressWarnings("unused")
	private ConsoleReader console;
	
	public MyMIPS() throws IOException {
		this.console = Simulator.getConsole();
	}
	
	@Override
	public void execute(State state) throws Exception {
		
		Integer PC = state.getPC();
		Integer instructionCode = state.readInstructionMemory(PC);
		String instructionCodeBinary = this.decimalToBinary(instructionCode);
		String instructionCodeHexa = this.decimalToHexa(instructionCode);
		
		
//		String opcodeBin = this.decimalToBinary(instructionCode).substring(0, 6);
//		Integer opcodeDecimal = Integer.parseInt(opcodeBin, 2);
//		String opcodeHex = this.decimalToHexa(opcodeDecimal);
		Integer opcodeDecimal = (instructionCode>>26) & 0b00000000000000000000000000111111;
		String opcodeBin = this.decimalToBinary(opcodeDecimal);
		String opcodeHex = this.decimalToHexa(opcodeDecimal);
		
		
		Simulator.debug("Instruction Code (Hexa) = " + instructionCodeHexa);
		Simulator.debug("Instruction Code (Binary) = " + instructionCodeBinary);
		Simulator.debug("opcode (Binary) = " + opcodeBin);
		Simulator.debug("opcode (Hexa) = " + opcodeHex);
		Simulator.debug("opcode (Decimal) = " + opcodeDecimal);
		
		
		state.setPC(state.getPC() + 4);
		
		String rsBin = null;
		Integer rsDecimal = null;
		String rsHex = null;
		String rtBin = null;
		Integer rtDecimal = null;
		String rtHex = null;
		String rdBin = null;
		Integer rdDecimal = null;
		String rdHex = null;
		String immediateBin = null;
		Integer immediateDecimal = null;
		String immediateHex = null;
		String addressBin = null;
		Integer addressDecimal = null;
		String addressHex = null;
		String functBin = null;
		Integer functDecimal = null;
		String functHex = null;
		String shamtBin = null;
		Integer shamtDecimal = null;
		String shamtHex = null;
		
		if (opcodeDecimal==0){
			// R-Type
			// Funct
//			functBin = this.decimalToBinary(instructionCode).substring(26); // From 26 until the end
//			functDecimal = Integer.parseInt(functBin, 2);
//			functHex = this.decimalToHexa(functDecimal);
			functDecimal = instructionCode & 0b00000000000000000000000000111111;
			functBin = this.decimalToBinary(functDecimal); // From 26 until the end
			functHex = this.decimalToHexa(functDecimal);
			
			// rs
//			rsBin = this.decimalToBinary(instructionCode).substring(6, 11); // From 26 until the end
//			rsDecimal = Integer.parseInt(rsBin, 2);
//			rsHex = this.decimalToHexa(rsDecimal);
			rsDecimal = instructionCode>>21 & 0b00000000000000000000000000011111;
			rsBin = this.decimalToBinary(rsDecimal);
			rsHex = this.decimalToHexa(rsDecimal);
			// rt
//			rtBin = this.decimalToBinary(instructionCode).substring(11, 16); // From 26 until the end
//			rtDecimal = Integer.parseInt(rtBin, 2);
//			rtHex = this.decimalToHexa(rtDecimal);
			rtDecimal = instructionCode>>16 & 0b00000000000000000000000000011111;
			rtBin = this.decimalToBinary(rtDecimal); // From 26 until the end
			rtHex = this.decimalToHexa(rtDecimal);
//			
			// rd
//			rdBin = this.decimalToBinary(instructionCode).substring(16, 21); // From 26 until the end
//			rdDecimal = Integer.parseInt(rdBin, 2);
//			rdHex = this.decimalToHexa(rdDecimal);
			rdDecimal = instructionCode>>11 & 0b00000000000000000000000000011111;
			rdBin = this.decimalToBinary(rdDecimal); // From 26 until the end
			rdHex = this.decimalToHexa(rdDecimal);
			// shamt
//			shamtBin = this.decimalToBinary(instructionCode).substring(21, 26); // From 26 until the end
//			shamtDecimal = Integer.parseInt(shamtBin, 2);
//			shamtHex = this.decimalToHexa(shamtDecimal);
			shamtDecimal = instructionCode>>6 & 0b00000000000000000000000000011111;
			shamtBin = this.decimalToBinary(shamtDecimal); // From 26 until the end
			shamtHex = this.decimalToHexa(shamtDecimal);
		}else{
			if(opcodeDecimal==2 || opcodeDecimal==3){
				// J-Type
				// Address
//				addressBin = (this.decimalToBinary(instructionCode).substring(6)); // From 16 until the end
//				addressDecimal = Integer.parseInt(addressBin, 2)<<2; // According to the MIPS reference, "00" in the end
//				addressBin = addressDecimal.toBinaryString(addressDecimal);
//				addressHex = this.decimalToHexa(addressDecimal);
				addressDecimal = (instructionCode & 0b00000011111111111111111111111111)<<2; // According to the MIPS reference, "00" in the end
				addressBin = (this.decimalToBinary(addressDecimal)); // From 16 until the end
				addressHex = this.decimalToHexa(addressDecimal);
				
			}else{
				// I-Type
				// rs
//				rsBin = this.decimalToBinary(instructionCode).substring(6, 11); // From 26 until the end
//				rsDecimal = Integer.parseInt(rsBin, 2);
//				rsHex = this.decimalToHexa(rsDecimal);
				rsDecimal = instructionCode>>21;
				Simulator.debug("AQUI!!! = "+rsDecimal);
				rsDecimal = instructionCode>>21 & 0b00000000000000000000000000011111;
				rsBin = this.decimalToBinary(rsDecimal); // From 26 until the end
				rsHex = this.decimalToHexa(rsDecimal);
				// rt
//				rtBin = this.decimalToBinary(instructionCode).substring(11, 16); // From 26 until the end
//				rtDecimal = Integer.parseInt(rtBin, 2);
//				rtHex = this.decimalToHexa(rtDecimal);
				rtDecimal = instructionCode>>16 & 0b00000000000000000000000000011111;
				rtBin = this.decimalToBinary(rtDecimal); // From 26 until the end
				rtHex = this.decimalToHexa(rtDecimal);
				// immediate
//				immediateBin = this.decimalToBinary(instructionCode).substring(16); // From 16 until the end
//				if (immediateBin.substring(0, 1).equals("1")){
//					// immediate is negative
//					String tempImmediateBin = Integer.toBinaryString(Integer.parseInt(immediateBin,2)-1);
//					String result = "";
//					for (int i=0; i<tempImmediateBin.length();i++){
//						if (tempImmediateBin.substring(i, i+1).equals("1")){
//							result+="0";
//						}else{
//							result+="1";
//						}
//					}
//					
//					Simulator.debug("tempImmediateBin = "+tempImmediateBin);
//					immediateDecimal = Integer.parseInt(("-"+result), 2);
//				}else{
//					// immediate is positive
//					immediateDecimal = Integer.parseInt(("+"+immediateBin), 2);
//				}
				
//				immediateHex = this.decimalToHexa(immediateDecimal);
				
				Integer partialImmediate = instructionCode<<16;
				if (partialImmediate<0){
					immediateDecimal = instructionCode | 0b11111111111111110000000000000000;
				}else{
					immediateDecimal = instructionCode & 0b00000000000000001111111111111111;
				}
//				immediateDecimal = instructionCode & 0b00000000000000001111111111111111;
				immediateBin = this.decimalToBinary(immediateDecimal);
				immediateHex = this.decimalToHexa(immediateDecimal);
				//obs
			}
		}
		
		switch (opcodeDecimal){
		
			case 0: // R-Type
				if (instructionCode==0){
					Simulator.debug("Invalid Instruction (all 0)");
					Simulator.info("Invalid Instruction (all 0)");
					Simulator.error("End of File (Invalid Instruction, all 0) ");
					break;
				}
				Simulator.debug("-------------------");
				Simulator.debug("R-Type");
				Simulator.debug("rs (bin)= "+rsBin);
				Simulator.debug("rs (hex)= "+rsHex);
				Simulator.debug("rs (Dec)= "+rsDecimal);

				Simulator.debug("rt (bin)= "+rtBin);
				Simulator.debug("rt (hex)= "+rtHex);
				Simulator.debug("rt (Dec)= "+rtDecimal);

				Simulator.debug("rd (bin)= "+rdBin);
				Simulator.debug("rd (hex)= "+rdHex);
				Simulator.debug("rd (Dec)= "+rdDecimal);

				Simulator.debug("shamt (bin)= "+shamtBin);
				Simulator.debug("shamt (hex)= "+shamtHex);
				Simulator.debug("shamt (Dec)= "+shamtDecimal);
				Simulator.debug("-------------------");
				switch (functDecimal){
					case 32:
						Simulator.debug("Opcode = 0, Funct = 32 (add)");
						// R[rd] = R[rs] + R[rt]
						Integer result0_32 = state.readRegister(rsDecimal)+state.readRegister(rtDecimal);
						state.writeRegister(rdDecimal, result0_32);
						break;
					case 33:
						Simulator.debug("Opcode = 0, Funct = 33 (addu)");
						// R[rd] = R[rs] + R[rt] (Unsigned)
//						Integer result0_33 = ((int) ((Integer.toUnsignedLong(state.readRegister(rsDecimal)))+(Integer.toUnsignedLong(state.readRegister(rtDecimal)))));
						Integer result0_33 = (int)(state.readRegister(rsDecimal).longValue()+state.readRegister(rtDecimal).longValue());
						state.writeRegister(rdDecimal, result0_33);
						break;
					case 36:
						Simulator.debug("Opcode = 0, Funct = 36 (and)");
						// R[rd] = R[rs] & R[rt]
						Integer result0_36 = state.readRegister(rsDecimal) & state.readRegister(rtDecimal);
						state.writeRegister(rdDecimal, result0_36);
						break;
					case 8:
						Simulator.debug("Opcode = 0, Funct = 8 (jr)");
						// PC=R[rs]
						Integer result0_8 = state.readRegister(rsDecimal);
						state.setPC(result0_8);
						break;
					case 39:
						Simulator.debug("Opcode = 0, Funct = 39 (nor)");
						// R[rd] = ~ (R[rs] | R[rt])
						Integer result0_39 = ~(state.readRegister(rsDecimal) | state.readRegister(rtDecimal));
						state.writeRegister(rdDecimal, result0_39);
						break;
					case 37:
						Simulator.debug("Opcode = 0, Funct = 37 (or)");
						// R[rd] = R[rs] | R[rt]
						Integer result0_37 = (state.readRegister(rsDecimal) | state.readRegister(rtDecimal));
						state.writeRegister(rdDecimal, result0_37);
						break;
					case 42:
						Simulator.debug("Opcode = 0, Funct = 42 (slt)");
						// R[rd] = (R[rs] < R[rt]) ? 1 : 0
						Integer result0_42;
						if (state.readRegister(rsDecimal)<state.readRegister(rtDecimal)){
							result0_42 = 1;
						}else{
							result0_42 = 0;
						}
						state.writeRegister(rdDecimal, result0_42);
						break;
					case 43:
						Simulator.debug("Opcode = 0, Funct = 43 (sltu)");
						// R[rd] = (R[rs] < R[rt]) ? 1 : 0 (Unsigned)
						Integer result0_43;
						if (Integer.toUnsignedLong(state.readRegister(rsDecimal))<Integer.toUnsignedLong(state.readRegister(rtDecimal))){
							result0_43 = 1;
						}else{
							result0_43 = 0;
						}
						state.writeRegister(rdDecimal, result0_43);
						break;
					case 0:
						Simulator.debug("Opcode = 0, Funct = 0 (sll)");
						// R[rd] = R[rt] << shamt
						Integer result0_0 = state.readRegister(rtDecimal)<<shamtDecimal;
						state.writeRegister(rdDecimal, result0_0);
						break;
					case 2:
						Simulator.debug("Opcode = 0, Funct = 2 (srl)");
						// R[rd] = R[rt] >> shamt (use ">>>" instead of ">>")
						Integer result0_2 = state.readRegister(rtDecimal)>>>shamtDecimal;
						state.writeRegister(rdDecimal, result0_2);
						break;
					case 34:
						Simulator.debug("Opcode = 0, Funct = 34 (sub)");
						// R[rd] = R[rs] - R[rt]
						Integer result0_34 = (state.readRegister(rsDecimal) - state.readRegister(rtDecimal));
						state.writeRegister(rdDecimal, result0_34);
						break;
					case 35:
						Simulator.debug("Opcode = 0, Funct = 35 (subu)");
						// R[rd] = R[rs] - R[rt]
						Integer result0_35 = (int) ( Integer.toUnsignedLong(state.readRegister(rsDecimal)) - Integer.toUnsignedLong(state.readRegister(rtDecimal)));
						state.writeRegister(rdDecimal, result0_35);
						break;
					default:
						Simulator.debug("Opcode = 0, Invalid Funct!!!");
						break;
				}
				
				break;
				
				
				
			case 8: // I-Type
				Simulator.debug("-------------------");
				Simulator.debug("I-Type");
				Simulator.debug("rs (bin)= "+rsBin);
				Simulator.debug("rs (hex)= "+rsHex);
				Simulator.debug("rs (Dec)= "+rsDecimal);

				Simulator.debug("rt (bin)= "+rtBin);
				Simulator.debug("rt (hex)= "+rtHex);
				Simulator.debug("rt (Dec)= "+rtDecimal);

				Simulator.debug("immediate (bin)= "+immediateBin);
				Simulator.debug("immediate (hex)= "+immediateHex);
				Simulator.debug("immediate (Dec, Signed)= "+immediateDecimal);

				Simulator.debug("-------------------");
				Simulator.debug("opcode = 8 (addi)");
				// R[rt] = R[rs] + SignExtImm
//				Integer result8 = state.readRegister(rsDecimal)+getSignExtImmBin(immediateDecimal); // immediateDecimal = SignExtImm
//				state.writeRegister(rtDecimal, result8);
				Integer result8 = state.readRegister(rsDecimal)+immediateDecimal; // immediateDecimal = SignExtImm
				state.writeRegister(rtDecimal, result8);
				
				
				break;
			case 9: // I-Type
				Simulator.debug("-------------------");
				Simulator.debug("I-Type");
				Simulator.debug("rs (bin)= "+rsBin);
				Simulator.debug("rs (hex)= "+rsHex);
				Simulator.debug("rs (Dec)= "+rsDecimal);

				Simulator.debug("rt (bin)= "+rtBin);
				Simulator.debug("rt (hex)= "+rtHex);
				Simulator.debug("rt (Dec)= "+rtDecimal);

				Simulator.debug("immediate (bin)= "+immediateBin);
				Simulator.debug("immediate (hex)= "+immediateHex);
				Simulator.debug("immediate (Dec)= "+immediateDecimal);

				Simulator.debug("-------------------");
				
				Simulator.debug("opcode = 9 (addiu)");
				// R[rt] = R[rs] + SignExtImm
				Integer result9 = (int) ((Integer.toUnsignedLong(state.readRegister(rsDecimal)))+ immediateDecimal);//(this.getSignExtImmBin(immediateDecimal)));
				state.writeRegister(rtDecimal, result9);
				Simulator.debug("immediateBin = "+immediateBin);
			
				break;
			case 12: // I-Type
				Simulator.debug("-------------------");
				Simulator.debug("I-Type");
				Simulator.debug("rs (bin)= "+rsBin);
				Simulator.debug("rs (hex)= "+rsHex);
				Simulator.debug("rs (Dec)= "+rsDecimal);

				Simulator.debug("rt (bin)= "+rtBin);
				Simulator.debug("rt (hex)= "+rtHex);
				Simulator.debug("rt (Dec)= "+rtDecimal);

				Simulator.debug("immediate (bin)= "+immediateBin);
				Simulator.debug("immediate (hex)= "+immediateHex);
				Simulator.debug("immediate (Dec)= "+immediateDecimal);

				Simulator.debug("-------------------");
				
				Simulator.debug("opcode = 12 (andi)");
				// R[rt] = R[rs] & ZeroExtImm
//				Integer result12 = state.readRegister(rsDecimal) & (int) Long.parseLong(getZeroExtImmBin(immediateBin),2);
				Integer result12 = state.readRegister(rsDecimal) & getZeroExtImmBin(immediateDecimal);
				state.writeRegister(rtDecimal, result12);

				break;
			case 4: // I-Type
				Simulator.debug("-------------------");
				Simulator.debug("I-Type");
				Simulator.debug("rs (bin)= "+rsBin);
				Simulator.debug("rs (hex)= "+rsHex);
				Simulator.debug("rs (Dec)= "+rsDecimal);

				Simulator.debug("rt (bin)= "+rtBin);
				Simulator.debug("rt (hex)= "+rtHex);
				Simulator.debug("rt (Dec)= "+rtDecimal);

				Simulator.debug("immediate (bin)= "+immediateBin);
				Simulator.debug("immediate (hex)= "+immediateHex);
				Simulator.debug("immediate (Dec)= "+immediateDecimal);

				Simulator.debug("-------------------");
				
				Simulator.debug("opcode = 4 (beq)");
				// if(R[rs]==R[rt]) { PC=PC+4+BranchAddr }
				
				Integer result4;
				Simulator.debug("rsDecimal = "+state.readRegister(rsDecimal));
				Simulator.debug("rtDecimal = "+state.readRegister(rtDecimal)); 
				Simulator.debug("rsDecimal==rtDecimal = "+(state.readRegister(rsDecimal)==state.readRegister(rtDecimal)));
				Integer tempBeqResult = (state.readRegister(rsDecimal))-state.readRegister(rtDecimal);
				Simulator.debug("rsDecimal-rtDecimal = " + tempBeqResult);
				if(tempBeqResult==0){
					Simulator.debug("Entrou no if!");
					result4 = state.getPC() + (int) Long.parseLong(this.getBranchAddrBin(immediateBin),2);
					
					Simulator.debug("oldImmediate = "+immediateDecimal);
					Simulator.debug("newImmediate = "+Long.parseLong(this.getBranchAddrBin(immediateBin),2));
					Simulator.debug("newPC = "+result4);
					state.setPC(result4);
				}else{
					//nothing...
				}
				
				
				
				break;
			case 5: // I-Type
				Simulator.debug("-------------------");
				Simulator.debug("I-Type");
				Simulator.debug("rs (bin)= "+rsBin);
				Simulator.debug("rs (hex)= "+rsHex);
				Simulator.debug("rs (Dec)= "+rsDecimal);

				Simulator.debug("rt (bin)= "+rtBin);
				Simulator.debug("rt (hex)= "+rtHex);
				Simulator.debug("rt (Dec)= "+rtDecimal);

				Simulator.debug("immediate (bin)= "+immediateBin);
				Simulator.debug("immediate (hex)= "+immediateHex);
				Simulator.debug("immediate (Dec)= "+immediateDecimal);

				Simulator.debug("-------------------");
				
				Simulator.debug("opcode = 5 (bne)");
				// if(R[rs]!=R[rt]) { PC=PC+4+BranchAddr }
				Integer tempBneResult = (state.readRegister(rsDecimal))-state.readRegister(rtDecimal);
				Simulator.debug("rsDecimal-rtDecimal = " + tempBneResult);
				if(tempBneResult!=0){
					result4 = state.getPC() + (int) Long.parseLong(this.getBranchAddrBin(immediateBin),2);
					
					Simulator.debug("oldImmediate = "+immediateDecimal);
					Simulator.debug("newImmediate = "+Long.parseLong(this.getBranchAddrBin(immediateBin),2));
					Simulator.debug("newPC = "+result4);
					state.setPC(result4);
				}else{
					//nothing...
				}
				
				break;
			case 36: // I-Type
				Simulator.debug("-------------------");
				Simulator.debug("I-Type");
				Simulator.debug("rs (bin)= "+rsBin);
				Simulator.debug("rs (hex)= "+rsHex);
				Simulator.debug("rs (Dec)= "+rsDecimal);

				Simulator.debug("rt (bin)= "+rtBin);
				Simulator.debug("rt (hex)= "+rtHex);
				Simulator.debug("rt (Dec)= "+rtDecimal);

				Simulator.debug("immediate (bin)= "+immediateBin);
				Simulator.debug("immediate (hex)= "+immediateHex);
				Simulator.debug("immediate (Dec)= "+immediateDecimal);

				Simulator.debug("-------------------");
				
				Simulator.debug("opcode = 36 (lbu)");
				// R[rt]={24’b0,M[R[rs]+SignExtImm](7:0)} (Unsigned)
//				Integer result36 = (0b00000000000000000000000011111111 & (state.readWordDataMemory(state.readRegister(rsDecimal))+immediateDecimal));
				Integer result36 = 0b00000000000000000000000011111111 & ((state.readWordDataMemory(state.readRegister(rsDecimal)+immediateDecimal)));
				
				state.writeRegister(rtDecimal, result36);
				break;
			case 37: // I-Type
				Simulator.debug("-------------------");
				Simulator.debug("I-Type");
				Simulator.debug("rs (bin)= "+rsBin);
				Simulator.debug("rs (hex)= "+rsHex);
				Simulator.debug("rs (Dec)= "+rsDecimal);

				Simulator.debug("rt (bin)= "+rtBin);
				Simulator.debug("rt (hex)= "+rtHex);
				Simulator.debug("rt (Dec)= "+rtDecimal);

				Simulator.debug("immediate (bin)= "+immediateBin);
				Simulator.debug("immediate (hex)= "+immediateHex);
				Simulator.debug("immediate (Dec)= "+immediateDecimal);

				Simulator.debug("-------------------");
				
				Simulator.debug("opcode = 37 (lhu)");
				// R[rt]={16’b0,M[R[rs]+SignExtImm](15:0)} (Unsigned)
				Integer result37 = (0b00000000000000001111111111111111 & state.readWordDataMemory(state.readRegister(rsDecimal)+immediateDecimal));
				state.writeRegister(rtDecimal, result37);
				break;
			case 15: // I-Type
				Simulator.debug("-------------------");
				Simulator.debug("I-Type");
				Simulator.debug("rs (bin)= "+rsBin);
				Simulator.debug("rs (hex)= "+rsHex);
				Simulator.debug("rs (Dec)= "+rsDecimal);

				Simulator.debug("rt (bin)= "+rtBin);
				Simulator.debug("rt (hex)= "+rtHex);
				Simulator.debug("rt (Dec)= "+rtDecimal);

				Simulator.debug("immediate (bin)= "+immediateBin);
				Simulator.debug("immediate (hex)= "+immediateHex);
				Simulator.debug("immediate (Dec)= "+immediateDecimal);

				Simulator.debug("-------------------");
				
				Simulator.debug("opcode = 15 (lui)");
				// R[rt] = {imm, 16’b0}
				Integer result15 = (immediateDecimal<<16) & 0b11111111111111110000000000000000;
				Simulator.debug("luiResult = "+result15);
				Simulator.debug("luiDestinationRegister = "+rtDecimal);
				state.writeRegister(rtDecimal, result15);
				break;
			case 35: // I-Type
				Simulator.debug("-------------------");
				Simulator.debug("I-Type");
				Simulator.debug("rs (bin)= "+rsBin);
				Simulator.debug("rs (hex)= "+rsHex);
				Simulator.debug("rs (Dec)= "+rsDecimal);

				Simulator.debug("rt (bin)= "+rtBin);
				Simulator.debug("rt (hex)= "+rtHex);
				Simulator.debug("rt (Dec)= "+rtDecimal);

				Simulator.debug("immediate (bin)= "+immediateBin);
				Simulator.debug("immediate (hex)= "+immediateHex);
				Simulator.debug("immediate (Dec)= "+immediateDecimal);

				Simulator.debug("-------------------");
				
				Simulator.debug("opcode = 35 (lw)");
				// R[rt] = M[R[rs]+SignExtImm]
//				Integer result35 = state.readWordDataMemory(rsDecimal+getSignExtImmBin(immediateBin));
				Integer result35 = state.readWordDataMemory(state.readRegister(rsDecimal)+immediateDecimal);
				state.writeRegister(rtDecimal, result35);
				break;
			case 13: // I-Type
				Simulator.debug("-------------------");
				Simulator.debug("I-Type");
				Simulator.debug("rs (bin)= "+rsBin);
				Simulator.debug("rs (hex)= "+rsHex);
				Simulator.debug("rs (Dec)= "+rsDecimal);

				Simulator.debug("rt (bin)= "+rtBin);
				Simulator.debug("rt (hex)= "+rtHex);
				Simulator.debug("rt (Dec)= "+rtDecimal);

				Simulator.debug("immediate (bin)= "+immediateBin);
				Simulator.debug("immediate (hex)= "+immediateHex);
				Simulator.debug("immediate (Dec)= "+immediateDecimal);

				Simulator.debug("-------------------");
				
				Simulator.debug("opcode = 13 (ori)");
				// R[rt] = R[rs] | ZeroExtImm
//				Integer result13 = (state.readRegister(rsDecimal) | (int) Long.parseLong((this.getZeroExtImmBin(immediateBin)),2));
				Integer result13 = (state.readRegister(rsDecimal) | this.getZeroExtImmBin(immediateDecimal));
				state.writeRegister(rtDecimal, result13);
				break;
			case 10: // I-Type
				Simulator.debug("-------------------");
				Simulator.debug("I-Type");
				Simulator.debug("rs (bin)= "+rsBin);
				Simulator.debug("rs (hex)= "+rsHex);
				Simulator.debug("rs (Dec)= "+rsDecimal);

				Simulator.debug("rt (bin)= "+rtBin);
				Simulator.debug("rt (hex)= "+rtHex);
				Simulator.debug("rt (Dec)= "+rtDecimal);

				Simulator.debug("immediate (bin)= "+immediateBin);
				Simulator.debug("immediate (hex)= "+immediateHex);
				Simulator.debug("immediate (Dec)= "+immediateDecimal);

				Simulator.debug("-------------------");
				
				Simulator.debug("opcode = 10 (slti)");
				// R[rt] = (R[rs] < SignExtImm)? 1 :0
				Integer result10;
				if (state.readRegister(rsDecimal)<(immediateDecimal)){  // CORRIGIR!!! immediateDecimal = SignExtImm
					result10 = 1;
				}else{
					result10 = 0;
				}
				state.writeRegister(rtDecimal, result10);
				
				
				break;
			case 11: // I-Type
				Simulator.debug("-------------------");
				Simulator.debug("I-Type");
				Simulator.debug("rs (bin)= "+rsBin);
				Simulator.debug("rs (hex)= "+rsHex);
				Simulator.debug("rs (Dec)= "+rsDecimal);

				Simulator.debug("rt (bin)= "+rtBin);
				Simulator.debug("rt (hex)= "+rtHex);
				Simulator.debug("rt (Dec)= "+rtDecimal);

				Simulator.debug("immediate (bin)= "+immediateBin);
				Simulator.debug("immediate (hex)= "+immediateHex);
				Simulator.debug("immediate (Dec)= "+immediateDecimal);
				Simulator.debug("-------------------");
				
				Simulator.debug("opcode = 11 (sltiu)");
				// R[rt] = (R[rs] < SignExtImm) ? 1 :0 (Unsigned)
				Integer result11;
				if (Integer.toUnsignedLong(state.readRegister(rsDecimal))<Integer.toUnsignedLong(immediateDecimal)){ 
					result11 = 1;
				}else{
					result11 = 0;
				}
				state.writeRegister(rtDecimal, result11);
				break;
			case 40: // I-Type
				Simulator.debug("-------------------");
				Simulator.debug("I-Type");
				Simulator.debug("rs (bin)= "+rsBin);
				Simulator.debug("rs (hex)= "+rsHex);
				Simulator.debug("rs (Dec)= "+rsDecimal);

				Simulator.debug("rt (bin)= "+rtBin);
				Simulator.debug("rt (hex)= "+rtHex);
				Simulator.debug("rt (Dec)= "+rtDecimal);

				Simulator.debug("immediate (bin)= "+immediateBin);
				Simulator.debug("immediate (hex)= "+immediateHex);
				Simulator.debug("immediate (Dec)= "+immediateDecimal);

				Simulator.debug("-------------------");
				
				Simulator.debug("opcode = 40 (sb)");
				// M[R[rs]+SignExtImm](7:0) = R[rt](7:0)
//				Integer result40 = (state.readRegister(rtDecimal)>>24) & 0b00000000000000000000000011111111;
				Integer result40 = (state.readRegister(rtDecimal)) & 0b00000000000000000000000011111111;
				state.writeByteDataMemory(state.readRegister(rsDecimal)+immediateDecimal, result40);
				break;
			case 41: // I-Type
				Simulator.debug("-------------------");
				Simulator.debug("I-Type");
				Simulator.debug("rs (bin)= "+rsBin);
				Simulator.debug("rs (hex)= "+rsHex);
				Simulator.debug("rs (Dec)= "+rsDecimal);

				Simulator.debug("rt (bin)= "+rtBin);
				Simulator.debug("rt (hex)= "+rtHex);
				Simulator.debug("rt (Dec)= "+rtDecimal);

				Simulator.debug("immediate (bin)= "+immediateBin);
				Simulator.debug("immediate (hex)= "+immediateHex);
				Simulator.debug("immediate (Dec)= "+immediateDecimal);

				Simulator.debug("-------------------");
				
				Simulator.debug("opcode = 41 (sh)");
				// M[R[rs]+SignExtImm](15:0) = R[rt](15:0)
				Integer result41 = state.readRegister(rtDecimal) & 0b00000000000000001111111111111111; 
				state.writeHalfwordDataMemory((state.readRegister(rsDecimal)+immediateDecimal), result41);
				Simulator.debug("Mudou Sim!!");
				break;
			case 43: // I-Type
				Simulator.debug("-------------------");
				Simulator.debug("I-Type");
				Simulator.debug("rs (bin)= "+rsBin);
				Simulator.debug("rs (hex)= "+rsHex);
				Simulator.debug("rs (Dec)= "+rsDecimal);

				Simulator.debug("rt (bin)= "+rtBin);
				Simulator.debug("rt (hex)= "+rtHex);
				Simulator.debug("rt (Dec)= "+rtDecimal);

				Simulator.debug("immediate (bin)= "+immediateBin);
				Simulator.debug("immediate (hex)= "+immediateHex);
				Simulator.debug("immediate (Dec)= "+immediateDecimal);

				Simulator.debug("-------------------");
				Simulator.debug("opcode = 43 (sw)");
				// M[R[rs]+SignExtImm] = R[rt]
				Integer result43 = state.readRegister(rtDecimal); 
				state.writeWordDataMemory((state.readRegister(rsDecimal)+immediateDecimal), result43);
				break;
				
			case 32: // I-Type
				Simulator.debug("-------------------");
				Simulator.debug("I-Type");
				Simulator.debug("rs (bin)= "+rsBin);
				Simulator.debug("rs (hex)= "+rsHex);
				Simulator.debug("rs (Dec)= "+rsDecimal);

				Simulator.debug("rt (bin)= "+rtBin);
				Simulator.debug("rt (hex)= "+rtHex);
				Simulator.debug("rt (Dec)= "+rtDecimal);

				Simulator.debug("immediate (bin)= "+immediateBin);
				Simulator.debug("immediate (hex)= "+immediateHex);
				Simulator.debug("immediate (Dec)= "+immediateDecimal);

				Simulator.debug("-------------------");
				
				Simulator.debug("opcode = 32 (lb)"); 
				// R[rt] = M[R[rs]+SignExtImm]{7:0}
//				Integer result35 = state.readWordDataMemory(rsDecimal+getSignExtImmBin(immediateBin));
//				Integer result32 = ((state.readWordDataMemory(state.readRegister(rsDecimal)+immediateDecimal))>>24) & 0b00000000000000000000000011111111;
//				state.writeRegister(rtDecimal, result32);
				
				Integer partial32 = (state.readWordDataMemory(state.readRegister(rsDecimal)+immediateDecimal));
				Simulator.debug("partial32 = "+partial32);
				Integer partial32Immediate = partial32<<24;
				Simulator.debug("partial32Immediate = "+partial32Immediate);
				
				if (partial32Immediate<0){
					partial32 = partial32 | 0b11111111111111111111111100000000;
				}else{
					partial32 = partial32 & 0b00000000000000000000000011111111;
				}
//				Integer result32 = 0b00000000000000000000000011111111 & (partial32);
				Integer result32 =  (partial32);
				
				state.writeRegister(rtDecimal, result32);
				
				break;
				
			case 2: // J-Type
				Simulator.debug("-------------------");
				Simulator.debug("J-Type");
				// PC=JumpAddr
				
				Simulator.debug("address (bin)= "+addressBin);
				Simulator.debug("address (hex)= "+addressHex);
				Simulator.debug("address (Dec)= "+addressDecimal);
				Simulator.debug("-------------------");
				
				Simulator.debug("opcode = 2 (j)");
				// JumpAddr = PC+4[31:28], address, 2’b0
				state.setPC((getJumpAddr(addressDecimal, state.getPC())));
				break;
			case 3: // J-Type
				
				Simulator.debug("-------------------");
				Simulator.debug("J-Type");

				Simulator.debug("address (bin)= "+addressBin);
				Simulator.debug("address (hex)= "+addressHex);
				Simulator.debug("address (Dec)= "+addressDecimal);
				Simulator.debug("-------------------");
				
				Simulator.debug("opcode = 3 (jal)");
				// R[31]=PC+8;PC=JumpAddr
				state.writeRegister(31, state.getPC());
				state.setPC((getJumpAddr(addressDecimal, state.getPC())));
				break;
			default:
				Simulator.debug("Invalid Opcode");
				break;
				
		}
	}
	
	public static void main(String[] args) {
		try {
			Simulator.setMIPS(new MyMIPS());
			Simulator.setLogLevel(Simulator.LogLevel.INFO);
			Simulator.start();
		} catch (Exception e) {		
			e.printStackTrace();
		}		
	}
	
	public String decimalToBinary(Integer x){
		String result = Integer.toBinaryString(x);
		while (result.length()!=32){
			result = "0" + result;
		}
		return result;
	}
	
	public String decimalToHexa(Integer x){
		String result = Integer.toHexString(x);
		while (result.length()!=8){
			result = "0" + result;
		}
		return result;
	}
	
	public String immediateDecimalToBinary(Integer x){
		String result = Integer.toBinaryString(x);
		if(result.substring(0,1).equals("0")){
			while (result.length()!=32){
				result = "0" + result;
			}
		}else{
			while (result.length()!=32){
				result = "1" + result;
			}
		}
		return result;
	}
	
	public String immidiateDecimalToHexa(Integer x){
		String result = Integer.toHexString(x);
		while (result.length()!=8){
			result = "0" + result;
		}
		return result;
	} 
	
//	public String getSignExtImmBin(String immediateBin){
//		String result;
//	
//		if (immediateBin.substring(0, 1).equals("0")){
//			result = "0000000000000000"+immediateBin;
//		}else{
//			result = "1111111111111111"+immediateBin;
//		}
//		return result;
//	}
	
//	public Integer getSignExtImmBin(Integer immediate){
//		//SignExtImm = { 16{immediate[15]}, immediate
//		Integer result;
//	
//		if (immediate>=0){
//			result =  (0b0000000000000000 + immediate);
//		}else{
//			result = (0b1111111111111111+immediate);
//		}
//		return result;
//	}
	
	
//	public String getZeroExtImmBin(String immediateBin){
//		return "0000000000000000"+immediateBin;
//	}
	
	public Integer getZeroExtImmBin(Integer immediate){
		return (0b00000000000000001111111111111111 & immediate);
	}
	
	
	public String getBranchAddrBin(String immediateBin){
		String result;
		if(immediateBin.subSequence(0, 1).equals("0")){
			result = "00000000000000"+immediateBin+"00";
		}else{
			result = "11111111111111" + immediateBin+"00";
		}
		return result;
	}
	
	public Integer getJumpAddr(Integer addressDecimal, Integer PC){
//		// JumpAddr = PC+4[31:28], address, 2’b0
		Integer result = (PC & 0b11110000000000000000000000000000)+addressDecimal; //JumpAddress already in Address
		return result;
	}
	
}
