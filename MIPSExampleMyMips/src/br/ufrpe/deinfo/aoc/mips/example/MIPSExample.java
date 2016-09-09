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



public class MIPSExample{
	
	@SuppressWarnings("unused")
	private ConsoleReader console;
	
	public MIPSExample() throws IOException {
		this.console = Simulator.getConsole();
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
}