HumanVelocities {

//soprano velocities
	sopranovel {var noteOns, noteOffs, array;
	noteOns =
		[ 
		59,
		63,
		58,
		65,
		64,
		63,
		61,
		66,
		59,
		62,
		73,
		74,
		65,
		66,
		67,
		51,
		61,
		65,
		56,
		66,
		56,
		64,
		58,
		71,
		61,
		77,
		57,
		61,
		68,
		68,
		46,
		52,
		68,
		65,
		71,
		66,
		65,
		67,
		62,
		72,
		74,
		60,
		62,
		57,
		65,
		63,
		64,
		55,
		66,
		69,
		65,
		67,
		68,
		66,
		64,
		63,
		67,
		64,
		72,
		63,
		66,
		68,
		63,
		65,
		70,
		41,
		40,
		53,
		48,
		62,
		64,
		54,
		72,
		67,
		66,
		61,
		62,
		56,
		68,
		68,
		64,
		67,
		59,
		67,
		68,
		65,
		70,
		70,
		59,
		54,
		59,
		51,
		72,
		68,
		63,
		73,
		65,
		65,
		61,
		71,
		76,
		71,
		78,
		68,
		74,
		70,
		59,
		68,
		74,
		61,
		71,
		71,
		63,
		70,
		70,
		66,
		55,
		63,
		67,
		64,
		62,
		63,
		61,
		69,
		68,
		63,
		71,
		73,
		53,
		55,
		65,
		68,
		68,
		65,
		54,
		44,
		61,
		63,
		64,
		55,
		59,
		70,
		67,
		78,
		58,
		66,
		58,
		72,
		72,
		69,
		72,
		61,
		68,
		66,
		73,
		70,
		71,
		70,
		65,
		70,
		68,
		70,
		60,
		68,
		68,
		63,
		65,
		61,
		58,
		64,
		70,
		68,
		61,
		67,
		64,
		68,
		70,
		71,
		64,
		70,
		55 ];
		
		noteOffs = Array.fill(noteOns.size+1, 0);
		array = [noteOffs] ++ [noteOns];
		^array.flop.flat;
		
		}

	alto1vel {var noteOns, noteOffs, array;
	noteOns =	
		[ 
		59,
		60,
		44,
		46,
		51,
		59,
		54,
		58,
		64,
		61,
		59,
		58,
		50,
		55,
		60,
		44,
		49,
		50,
		38,
		47,
		53,
		59,
		60,
		51,
		55,
		47,
		55,
		56,
		54,
		66,
		69,
		68,
		71,
		65,
		61,
		64,
		63,
		67,
		46,
		60,
		58,
		64,
		57,
		54,
		50,
		57,
		67,
		68,
		58,
		67,
		66,
		69,
		71,
		69,
		63,
		65,
		53,
		51,
		63,
		64,
		67,
		71,
		58,
		62,
		64,
		61,
		67,
		55,
		57,
		63,
		61,
		65,
		66,
		57,
		64,
		63,
		64,
		68,
		61,
		66,
		63,
		59,
		57,
		60,
		60,
		48,
		49,
		72,
		68,
		79,
		75,
		54,
		59,
		55,
		61,
		63,
		66,
		62,
		68,
		67,
		70,
		65,
		70,
		69,
		64,
		69,
		65,
		71,
		78,
		63,
		71,
		55,
		65,
		59,
		71,
		63,
		61,
		61,
		67,
		59,
		60,
		68,
		68,
		70,
		73,
		59,
		63,
		68,
		66,
		67,
		65,
		63,
		67,
		63,
		64,
		71,
		61,
		68,
		67,
		68,
		65,
		63,
		62,
		66,
		57,
		58,
		65,
		63,
		50,
		52,
		61,
		63,
		69,
		70,
		59,
		56,
		58,
		61,
		58,
		70,
		66,
		64,
		57,
		63,
		59,
		68,
		61,
		58,
		63,
		63,
		71,
		78,
		68,
		71,
		53,
		68,
		67,
		72,
		70,
		55,
		66,
		66,
		57,
		56 ];
		
		noteOffs = Array.fill(noteOns.size+1, 0);
		array = [noteOffs] ++ [noteOns];
		^array.flop.flat;

	}
	
	alto2vel {var noteOns, noteOffs, array;
	noteOns =				
		[ 
		52,
		55,
		59,
		59,
		61,
		54,
		54,
		65,
		53,
		58,
		62,
		61,
		60,
		60,
		52,
		50,
		66,
		61,
		54,
		59,
		71,
		61,
		61,
		64,
		51,
		67,
		58,
		65,
		65,
		67,
		59,
		49,
		54,
		52,
		66,
		64,
		55,
		58,
		70,
		68,
		65,
		68,
		66,
		59,
		59,
		67,
		67,
		66,
		67,
		42,
		68,
		77,
		67,
		51,
		58,
		67,
		72,
		71,
		68,
		69,
		64,
		66,
		67,
		63,
		65,
		63,
		55,
		57,
		67,
		67,
		65,
		61,
		58,
		65,
		61,
		63,
		59,
		65,
		59,
		61,
		65,
		58,
		64,
		50,
		71,
		63,
		66,
		66,
		62,
		71,
		77,
		71,
		63,
		68,
		62,
		67,
		73,
		60,
		53,
		63,
		77,
		67,
		67,
		67,
		70,
		66,
		71,
		66,
		65,
		71,
		60,
		72,
		67,
		70,
		61,
		62,
		65,
		70,
		67,
		73,
		77,
		67,
		69,
		71,
		71,
		63,
		68,
		68,
		63,
		70,
		70,
		70,
		70,
		63,
		59,
		69,
		62,
		63,
		66,
		65,
		71,
		65,
		68,
		67,
		70,
		67,
		65,
		66,
		62,
		65,
		63,
		75,
		70,
		67,
		71,
		66,
		77,
		74,
		66,
		67,
		70,
		63,
		63,
		73,
		55,
		59,
		70,
		76,
		78,
		70,
		69,
		71,
		69,
		68,
		66,
		65,
		70,
		56,
		71,
		73,
		71,
		70,
		59,
		64,
		73,
		65,
		73,
		71 ];
		noteOffs = Array.fill(noteOns.size+1, 0);
		array = [noteOffs] ++ [noteOns];
		^array.flop.flat;

	}
	
	tenorvel {var noteOns, noteOffs, array;
	noteOns =	
		[ 
		48,
		58,
		49,
		47,
		43,
		40,
		53,
		53,
		66,
		67,
		58,
		65,
		59,
		41,
		51,
		54,
		55,
		44,
		69,
		67,
		63,
		50,
		47,
		50,
		46,
		56,
		59,
		58,
		57,
		55,
		59,
		61,
		51,
		55,
		62,
		67,
		54,
		47,
		54,
		65,
		65,
		59,
		61,
		46,
		63,
		63,
		59,
		54,
		51,
		58,
		64,
		75,
		65,
		70,
		66,
		63,
		54,
		61,
		65,
		63,
		59,
		55,
		59,
		63,
		61,
		56,
		61,
		70,
		59,
		67,
		71,
		57,
		55,
		57,
		60,
		58,
		48,
		61,
		63,
		48,
		60,
		65,
		69,
		66,
		66,
		55,
		53,
		65,
		55,
		63,
		63,
		61,
		67,
		54,
		49,
		62,
		64,
		60,
		65,
		70,
		70,
		63,
		47,
		60,
		55,
		59,
		48,
		60,
		57,
		54,
		56,
		47,
		58,
		68,
		57,
		54,
		57,
		51,
		56,
		66,
		59,
		40,
		44,
		54,
		57,
		69,
		55,
		59,
		51,
		57,
		57,
		55,
		61,
		54,
		65,
		57,
		56,
		67,
		73,
		75,
		64,
		58,
		66,
		63,
		65,
		65,
		70,
		61,
		65,
		56,
		56,
		57,
		65,
		68,
		71,
		70,
		68,
		67,
		55,
		63,
		62,
		63,
		61,
		63,
		67,
		58,
		68,
		67 ];
		
		noteOffs = Array.fill(noteOns.size+1, 0);
		array = [noteOffs] ++ [noteOns];
		^array.flop.flat;

 }
 
	bassvel {var noteOns, noteOffs, array;
	noteOns =	
		[ 
		42,
		44,
		35,
		43,
		48,
		29,
		43,
		63,
		55,
		42,
		46,
		45,
		50,
		49,
		46,
		54,
		58,
		51,
		43,
		43,
		48,
		53,
		35,
		33,
		45,
		58,
		65,
		54,
		42,
		53,
		49,
		54,
		43,
		45,
		36,
		58,
		59,
		48,
		45,
		46,
		50,
		43,
		44,
		49,
		47,
		44,
		46,
		42,
		50,
		43,
		54,
		40,
		55,
		62,
		59,
		61,
		50,
		50,
		63,
		52,
		53,
		51,
		61,
		52,
		56,
		50,
		63,
		50,
		55,
		59,
		59,
		59,
		51,
		47,
		58,
		57,
		59,
		57,
		51,
		53,
		51,
		60,
		58,
		63,
		62,
		46,
		57,
		61,
		63,
		57,
		57,
		53,
		53,
		59,
		50,
		53,
		47,
		53,
		51,
		59,
		61,
		57,
		54,
		52,
		47,
		53,
		61,
		54,
		57,
		52,
		61,
		65,
		53,
		50,
		53,
		59,
		61,
		61,
		61,
		56,
		57,
		63,
		66,
		65,
		63,
		52,
		54,
		57,
		57,
		53,
		55,
		70,
		51 ];
		
		noteOffs = Array.fill(noteOns.size+1, 0);
		array = [noteOffs] ++ [noteOns];
		^array.flop.flat;
}


}
