Etudes2Score : Etudes2 {

var <>initBow=1.0, <>initString=0.9, <>initPerc=2.0, <>initPiano=2.8, <>initGagok=2.8, <>initFlute=1.2, <>initReed=0.8, <>adjString=0.8, <>adjPiano = 0.6;
var <>gagokOut = 2, number;


startEtudes2Score {arg bow, string, perc, piano, gagok, flute, reed, stringadj, pianoadj;
	//routine to start wind instrument-sound	
routineGagok = Etudes2Routine.new;
routineGagok.startOsc;
initBow = bow;
initString = string;
initPerc = perc;
initPiano = piano;
initGagok = gagok;
initFlute = flute;
initReed = reed;
adjString = stringadj;
adjPiano = pianoadj;

}

// play the score
sopranoScore { arg note, vel;
var justnote, volbow, mid;
	justnote = note.midijust(415);
	~step1.post; '/S'.postln;
	
	//noteOn
		
	if(~step1 == 1, 
		{volbow = ~globamp1*initBow;
		~globamp1 = volbow;
		mid = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(volbow.ampdb).linlin(0,1,0,127).round(1);
		control.control(0, 81, mid);
		control.control(0, 82, mid);
		control.control(0, 83, mid);
		~soprano.set(\globamp, volbow);
		~alto1.set(\globamp, volbow);
		~alto2.set(\globamp, volbow);
		
		~soprano.put(0, \gagaku, 0, [\bufnum, 20, \out, 0, \freq,  justnote, \amp, vel.linlin(0,127,0, 15.0), \midi, justnote, \window, 1.0, \base,  justnote, \start, rrand(0, 8720430)])};
	);
	if((~step1 > 1).and(~step1 < 92).and(~step1.odd), 
		{~soprano.set(\midi, justnote, \amp, vel.linlin(0,127,0, 15));}
	);
	if(~step1 == 15, 
		{~soprano.set(\base, justnote);}
	);
	if(~step1 == 27, 
		{~soprano.set(\base, justnote);}
	);
	if(~step1 == 37, 
		{~soprano.set(\base, justnote);}
	);
	if(~step1 == 47, 
		{~soprano.set(\base, justnote);}
	);
	if(~step1 == 75, 
		{~soprano.set(\base, justnote);}
	);
	if(~step1 == 83, 
		{~soprano.set(\base, justnote);}
	);
	if(~step1 == 89, 
		{~soprano.set(\base, justnote);}
	);
	if(~step1 == 91, 
		{~soprano.set(\gates, 0);}
	);
	if((~step1 >= 171).and(~step1 < 288).and(~step1.odd), 
		{~gagokAudio2.set(\amp, vel.linlin(0,127,0, 2.8));}
	);
	
	//noteoff
	
	
	//end noteOff
}

alto1Score { arg note, vel;  
var justnote, volreed, volflute, mid, mid2;
	justnote = note.midijust(415);
	~step2.post; '/A1'.postln;
	
		//noteOn
	if(~step2 == 15, 
		{~alto1.put(0, \bowedharmonics, 0, [\bufnum, 20, \freq, justnote, \amp, vel.linlin(0,127,0,1), \out, 0, \start, rrand(0, 8720430)]);
		~alto1_1 = ~alto1.objects[0];}
	);
	if((~step2 > 15).and(~step2 <= 101).and(~step2.odd), 
		{~alto1.set(\amp, vel.linlin(0,127,0,1));
		}
	);
	if(~step2 == 27, 
		{~alto1_1.set(\gates, 0);
		~alto1.add(\bowedharmonics, 0, [\bufnum, 20, \freq, justnote*2, \amp, vel.linlin(0,127,0,1), \out, 0, \start, rrand(0, 8720430)]);
		~alto1_2 = ~alto1.objects[1];}
	);
	if(~step2 == 37, 
		{~alto1_2.set(\gates, 0); 
		~alto1.add(\bowedharmonics, 0, [\bufnum, 20, \freq, justnote*2, \amp, vel.linlin(0,127,0,1), \start, rrand(0, 8720430)]);
		~alto1_1 = ~alto1.objects[2];}
	);
	if(~step2 == 47, 
		{~alto1_1.set(\gates, 0); 
		~alto1.add(\bowedharmonics, 0, [\bufnum, 20, \freq, justnote*2, \amp, vel.linlin(0,127,0,1),  \start, rrand(0, 8720430)]);
		~alto1_2 = ~alto1.objects[3];}
	);
	if(~step2 == 59, 
		{~alto1_2.set(\gates, 0); 
		~alto1.add(\bowedharmonics, 0, [\bufnum, 20, \freq, justnote*2, \amp, vel.linlin(0,127,0,1), \start, rrand(0, 8720430)]);
		~alto1_1 = ~alto1.objects[4];}
	);
	if(~step2 == 87, 
		{~alto1_1.set(\gates, 0); 
		~alto1.add(\bowedharmonics, 0, [\bufnum, 20, \freq, justnote*2, \amp, vel.linlin(0,127,0,1), \start, rrand(0, 8720430)]);
		~alto1_2 = ~alto1.objects[5];}
	);
	if(~step2 == 93, 
		{~alto1_2.set(\gates, 0); 
		~alto1.add(\bowedharmonics, 0, [\bufnum, 20, \freq, justnote, \amp, vel.linlin(0,127,0,1), \start, rrand(0, 8720430)]);}
	);	
	if(~step2 == 99, 
		{~alto1.set(\gates, 0);}
	);
	if(~step2 == 169, 
		{volflute = ~globamp3*initFlute;
		volreed = ~globamp3*initReed;
		mid = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(volflute.ampdb).linlin(0,1,0,127).round(1);
		mid2 = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(volreed.ampdb).linlin(0,1,0,127).round(1);
		control.control(0, 81, mid);
		control.control(0, 82, mid2);
		~gagokAudio2.set(\globamp, volflute); //flute
		~alto1.set(\globamp, volreed); //reed
		~gagokAudio2.set(\gates3, 1);//flute
		~alto1.set(\gates3, 1); //reed
		}
	);
	if((~step2 >= 169).and(~step2 < 268).and(~step2.odd), 
		{~alto1.set(\amp, vel.linlin(0,127,0, 1.5));
		}
	);

	
	//noteOff
	
	
	//end noteOff
}

alto2Score {arg note, vel; 
var justnote, volgagok, mid;
	justnote = note.midijust(415);
	~step3.post; '/A2'.postln;
	
	//noteOn
	if(~step3 == 13, 
		{~alto2.put(0, \bowedharmonics, 0, [\bufnum, 20, \freq, justnote, \amp, vel.linlin(0,127,0,1), \out, 0, \start, rrand(0, 8720430)]);
		~alto2_1 = ~alto2.objects[0];}
	);
	if(~step3 == 25, 
		{~alto2_1.set(\gates, 0); 
		~alto2.add(\bowedharmonics, 0, [\bufnum, 20, \freq, justnote*2, \amp, vel.linlin(0,127,0,1), \out, 0, \start, rrand(0, 8720430)]);
		~alto2_2 = ~alto2.objects[1];}
	);
	if(~step3 == 35, 
		{~alto2_2.set(\gates, 0); 
		~alto2.add(\bowedharmonics, 0, [\bufnum, 20, \freq, justnote*2, \amp, vel.linlin(0,127,0,1), \start, rrand(0, 8720430)]);
		~alto2_1 = ~alto2.objects[2];
		}
	);
	if(~step3 == 55, 
		{~alto2_1.set(\gates, 0); 
		~alto2.add(\bowedharmonics, 0, [\bufnum, 20, \freq, justnote*2, \amp, vel.linlin(0,127,0,1), \start, rrand(0, 8720430)]);
		~alto2_2 = ~alto2.objects[3];
		}
	);
	if(~step3 == 67, 
		{~alto2_2.set(\gates, 0); 
		~alto2.add(\bowedharmonics, 0, [\bufnum, 20, \freq, justnote*2, \amp, vel.linlin(0,127,0,1), \start, rrand(0, 8720430)]);
		~alto2_1 = ~alto2.objects[4];
		}
	);
	if(~step3 == 95, 
		{~alto2_1.set(\gates2, 0); 
		~alto2.add(\bowedharmonics, 0, [\bufnum, 20, \freq, justnote*2, \amp, vel.linlin(0,127,0,1), \start, rrand(0, 8720430)]);
		~alto2_2 = ~alto2.objects[5];
		}
	);
	if(~step3 == 111, 
		{~alto2_2.set(\gates2, 0); 
		~alto2.add(\bowedharmonics, 0, [\bufnum, 20, \freq, justnote, \amp, vel.linlin(0,127,0,1.0), \start, rrand(0, 8720430)]);
		~alto2_1 = ~alto2.objects[6];
		}
	);
	if(~step3 == 119, 
		{~alto2.set(\gates, 0);}
	);
	if(~step3 == 121,
		{routineGagok.osc.add;
		routineGagok.interval = 1.0;}
	);
	if(~step3 == 123,
		{volgagok = ~globamp1*initGagok;
		~globamp3 = volgagok;
		mid = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(volgagok.ampdb).linlin(0,1,0,127).round(1);
		control.control(0, 83, mid);
		~alto2.set(\globamp, volgagok);

		routineGagok.routines(gagokOut);}
	);
	if((~step3 >= 123).and(~step3 < 308).and(~step3.odd), 
		{~pitch = (justnote/332);
		~alto2.set(\vol, vel.linlin(0,127,0,1.0));}
	);
	if(~step3 == 163,
		{Routine({1.do({
		~alto2.set(\gates2, 0);
		0.2.yield;
		~env.newatkenv;
		~envelopeControl.env(~env.atkenvelope);})
		}).play;
		Routine({1.do({
		0.3.yield;
		~envelopeControl2.env(~env.atkenvelope);})
		}).play;
		Routine({1.do({
		0.2.yield;
		~envelopeControl3.env(~env.atkenvelope);})
		}).play;
		}
	); //attack gate
	if(~step3 == 183,
		{Routine({1.do({
		~alto2.set(\gates2, 0);
		0.2.yield;
		~env.newatkenv;
		~envelopeControl.env(~env.atkenvelope);})
		}).play;
		Routine({1.do({
		~delay1.yield;
		~envelopeControl2.env(~env.atkenvelope);})
		}).play;
		Routine({1.do({
		~delay2.yield;
		~envelopeControl3.env(~env.atkenvelope);})
		}).play;}
	); //attack gate
	if(~step3 == 195,
		{Routine({1.do({
		~alto2.set(\gates2, 1);
		rrand(0.67, 0.69).yield;
		~alto2.set(\gates2, 0);})
		}).play;
		
		Routine({1.do({
		0.2.yield;
		~env.newrelenv;
		~envelopeControl.env(~env.relenvelope);})
		}).play;}
	); //relese gate 
	if(~step3 == 197,
		{Routine({1.do({
		//~alto2.set(\gates2, 0);
		0.2.yield;
		~env.newatkenv;
		~envelopeControl.env(~env.atkenvelope);})
		}).play;
		
		Routine({1.do({
		~delay1.yield;
		~envelopeControl2.env(~env.atkenvelope);})
		}).play;
		
		Routine({1.do({
		~delay2.yield;
		~envelopeControl3.env(~env.atkenvelope);})
		}).play;
		}
	);
	if(~step3 == 203,
		{Routine({1.do({
		~alto2.set(\gates2, 0);
		0.2.yield;
		~env.newatkenv;
		~envelopeControl.env(~env.atkenvelope);})
		}).play;
		
		Routine({1.do({
		~delay1.yield;
		~envelopeControl2.env(~env.atkenvelope);})
		}).play;
		
		Routine({1.do({
		~delay2.yield;
		~envelopeControl3.env(~env.atkenvelope);})
		}).play;}
	); //attack gate	
	
	if(~step3 == 215,
		{Routine({1.do({
		~alto2.set(\gates2, 0);
		0.2.yield;
		~env.newatkenv;
		~envelopeControl.env(~env.atkenvelope);})
		}).play;
		
		Routine({1.do({
		~delay1.yield;
		~envelopeControl2.env(~env.atkenvelope);})
		}).play;
		
		Routine({1.do({
		~delay2.yield;
		~envelopeControl3.env(~env.atkenvelope);})
		}).play;}
	); //attack gate	
	if(~step3 == 257,
		{Routine({1.do({
		~alto2.set(\gates2, 0);
		0.2.yield;
		~env.newatkenv;
		~envelopeControl.env(~env.atkenvelope);})
		}).play;
		
		Routine({1.do({
		~delay1.yield;
		~envelopeControl2.env(~env.atkenvelope);})
		}).play;
		
		Routine({1.do({
		~delay2.yield;
		~envelopeControl3.env(~env.atkenvelope);})
		}).play;}
	); //attack gate	
	if(~step3 == 277,
		{Routine({1.do({
		~alto2.set(\gates2, 0);
		0.2.yield;
		~env.newatkenv;
		~envelopeControl.env(~env.atkenvelope);})
		}).play;
		
		Routine({1.do({
		~delay1.yield;
		~envelopeControl2.env(~env.atkenvelope);})
		}).play;
		
		Routine({1.do({
		~delay2.yield;
		~envelopeControl3.env(~env.atkenvelope);})
		}).play;}
	); //attack gate	
	if(~step3 == 291,
		{Routine({1.do({
		~alto2.set(\gates2, 0);
		0.2.yield;
		~env.newatkenv;
		~envelopeControl.env(~env.atkenvelope);})
		}).play;
		
		Routine({1.do({
		~delay1.yield;
		~envelopeControl2.env(~env.atkenvelope);})
		}).play;
		
		Routine({1.do({
		~delay2.yield;
		~envelopeControl3.env(~env.atkenvelope);})
		}).play;}
	); //attack gate	
	if(~step3 == 297,
		{Routine({1.do({
		~alto2.set(\gates2, 0);
		0.2.yield;
		~env.newatkenv;
		~envelopeControl.env(~env.atkenvelope);})
		}).play;
		
		Routine({1.do({
		~delay1.yield;
		~envelopeControl2.env(~env.atkenvelope);})
		}).play;
		
		Routine({1.do({
		~delay2.yield;
		~envelopeControl3.env(~env.atkenvelope);})
		}).play;}
	); //attack gate
	if(~step3 == 303,
		{Routine({1.do({
		~alto2.set(\gates2, 0);
		0.2.yield;
		~env.newatkenv;
		~envelopeControl.env(~env.atkenvelope);
		~alto2.set(\endgate, 1);})
		}).play;
		
		Routine({1.do({
		~delay1.yield;
		~envelopeControl2.env(~env.atkenvelope);})
		}).play;
		
		Routine({1.do({
		~delay2.yield;
		~envelopeControl3.env(~env.atkenvelope);})
		}).play;}
	); //attack gate	
		
	//noteOff
	
	if(~step3 == 162,
		{Routine({1.do({
		~alto2.set(\gates2, 1);
		0.2.yield;
		~env.newrelenv;
		~envelopeControl.env(~env.relenvelope);})
		}).play;
		
		Routine({1.do({
		0.3.yield;
		~env.newrelenv;
		~envelopeControl2.env(~env.relenvelope);})
		}).play;
		
		Routine({1.do({
		0.3.yield;
		~env.newrelenv;
		~envelopeControl3.env(~env.relenvelope);})
		}).play}
	); //relese gate 
	if(~step3 == 182,
		{Routine({1.do({
		~alto2.set(\gates2, 1);
		0.2.yield;
		~env.newrelenv;
		~envelopeControl.env(~env.relenvelope);})
		}).play;
		
		Routine({1.do({
		~delay1.yield;
		~env.newrelenv;
		~envelopeControl2.env(~env.relenvelope);})
		}).play;
		
		Routine({1.do({
		~delay2.yield;
		~env.newrelenv;
		~envelopeControl3.env(~env.relenvelope);})
		}).play;}
	); //relese gate
	
	if(~step3 == 202,
		{Routine({1.do({
		~alto2.set(\gates2, 1);
		0.2.yield;
		~env.newrelenv;
		~envelopeControl.env(~env.relenvelope);})
		}).play;
		
		~delay1 = rrand(0.0,0.6);
		Routine({1.do({
		~delay1.yield;
		~env.newrelenv;
		~envelopeControl2.env(~env.relenvelope);})
		}).play;
		
		~delay2 = rrand(0.0,0.6);
		Routine({1.do({
		~delay2.yield;
		~env.newrelenv;
		~envelopeControl3.env(~env.relenvelope);})
		}).play;}
	); //relese gate
	if(~step3 == 214,
		{Routine({1.do({
		~alto2.set(\gates2, 1);
		0.2.yield;
		~env.newrelenv;
		~envelopeControl.env(~env.relenvelope);})
		}).play;
		
		~delay1 = rrand(0.0,0.6);
		Routine({1.do({
		~delay1.yield;
		~env.newrelenv;
		~envelopeControl2.env(~env.relenvelope);})
		}).play;
		
		~delay2 = rrand(0.0,0.6);
		Routine({1.do({
		~delay2.yield;
		~env.newrelenv;
		~envelopeControl3.env(~env.relenvelope);})
		}).play;}
	); //relese gate 
	if(~step3 == 256,
		{Routine({1.do({
		~alto2.set(\gates2, 1);
		0.2.yield;
		~env.newrelenv;
		~envelopeControl.env(~env.relenvelope);})
		}).play;
		
		~delay1 = rrand(0.0,0.6);
		Routine({1.do({
		~delay1.yield;
		~env.newrelenv;
		~envelopeControl2.env(~env.relenvelope);})
		}).play;
		
		~delay2 = rrand(0.0,0.6);
		Routine({1.do({
		~delay2.yield;
		~env.newrelenv;
		~envelopeControl3.env(~env.relenvelope);})
		}).play;}
	); //relese gate 
	if(~step3 == 276,
		{Routine({1.do({
		~alto2.set(\gates2, 1);
		0.2.yield;
		~env.newrelenv;
		~envelopeControl.env(~env.relenvelope);})
		}).play;
		
		~delay1 = rrand(0.0,0.6);
		Routine({1.do({
		~delay1.yield;
		~env.newrelenv;
		~envelopeControl2.env(~env.relenvelope);})
		}).play;
		
		~delay2 = rrand(0.0,0.6);
		Routine({1.do({
		~delay2.yield;
		~env.newrelenv;
		~envelopeControl3.env(~env.relenvelope);})
		}).play;}
	); //relese gate 
	if(~step3 == 290,
		{Routine({1.do({
		~alto2.set(\gates2, 1);
		0.2.yield;
		~env.newrelenv;
		~envelopeControl.env(~env.relenvelope);})
		}).play;
		
		~delay1 = rrand(0.0,0.6);
		Routine({1.do({
		~delay1.yield;
		~env.newrelenv;
		~envelopeControl2.env(~env.relenvelope);})
		}).play;
		
		~delay2 = rrand(0.0,0.6);
		Routine({1.do({
		~delay2.yield;
		~env.newrelenv;
		~envelopeControl3.env(~env.relenvelope);})
		}).play;}
	); //relese gate
	if(~step3 == 296,
		{Routine({1.do({
		~alto2.set(\gates2, 1);
		0.2.yield;
		~env.newrelenv;
		~envelopeControl.env(~env.relenvelope);})
		}).play;
		
		~delay1 = rrand(0.0,0.6);
		Routine({1.do({
		~delay1.yield;
		~env.newrelenv;
		~envelopeControl2.env(~env.relenvelope);})
		}).play;
		
		~delay2 = rrand(0.0,0.6);
		Routine({1.do({
		~delay2.yield;
		~env.newrelenv;
		~envelopeControl3.env(~env.relenvelope);})
		}).play;}
	); //relese gate 
	if(~step3 == 302,
		{Routine({1.do({
		~alto2.set(\gates2, 0);
		0.2.yield;
		~env.newrelenv;
		~envelopeControl.env(~env.relenvelope);
		~alto2.set(\endgate, 0);})
		}).play;
		
		~delay1 = rrand(0.0,1.0);
		Routine({1.do({
		~delay1.yield;
		~env.newrelenv;
		~envelopeControl2.env(~env.relenvelope);})
		}).play;
		
		~delay2 = rrand(0.0,0.6);
		Routine({1.do({
		~delay2.yield;
		~env.newrelenv;
		~envelopeControl3.env(~env.relenvelope);})
		}).play;}
	); //relese gate 
	if(~step3 == 308,
		{Routine({1.do({
		0.2.yield;
		~env.newrelenv;
		~envelopeControl.env(~env.relenvelope);
		~alto2.set(\endgate, 0);})
		}).play;
		
		~delay1 = rrand(0.0,1.5);
		Routine({1.do({
		~delay1.yield;
		~env.newrelenv;
		~envelopeControl2.env(~env.relenvelope);})
		}).play;
		
		~delay2 = rrand(0.0,0.6);
		Routine({1.do({
		~delay2.yield;
		~env.newrelenv;
		~envelopeControl3.env(~env.relenvelope);})
		}).play;
		
		'FINAL FINAL FINAL!'.postln;
		}
	); //relese gate 
			
	//end noteOff
}

tenorScore {arg note, vel; 
	var justnote, volstring, volpiano, mid, mid2, fundnote, speed, c,c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,d,d1,d2,d3,d4,d5,d6,d7,d8,d9,d10,z,z1,z2,z3,z4,z5,z6,z7,z8,z9,z10;
	justnote = note.midijust(415);
	~step4.post; '/T'.postln;
	
	//noteOn
	
	if(~step4 == 23, 
		{
		volstring = ~globamp1*initString;
		~globamp4 = volstring;
		mid = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(volstring.ampdb).linlin(0,1,0,127).round(1);
		control.control(0, 84, mid);
		~tenor.set(\globamp, volstring);
		~tenor.add(\korea, 0, [\amp, vel.linlin(0,127,0,1.0), \lengh, 6, \bufnum, 8])}
	);
	if(~step4 == 25, 
		{~tenor.spawn([\amp, vel.linlin(0,127,0,1.0), \bufnum, 9], 1);}
	);
	if(~step4 == 31, 
		{~tenor.spawn([\amp, vel.linlin(0,127,0,1.0), \bufnum, 11], 1);}
	);
	if(~step4 == 33, 
		{~tenor.spawn([\amp, vel.linlin(0,127,0,1.0), \bufnum, 12], 1);}
	);
	if(~step4 == 37, 
		{~tenor.spawn([\amp, vel.linlin(0,127,0,1.0), \bufnum, 13], 1); 
		~tenor1 = ~tenor.objects[0];
		'SING: BEL! SING: BEL! SING: BEL! SING: BEL! SING: BEL! SING: BEL! SING: BEL! SING: BEL! SING: BEL!'.postln; 
		~legato = 1.5;
		~tenor1.set(\num, 13);}
	);
	if(~step4 == 41, {
		volpiano =  ~globamp4*initPiano;
		mid2 = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(volpiano.ampdb).linlin(0,1,0,127).round(1);
		control.control(0, 86, mid2);
		~piano.set(\globamp, volpiano);

		~tenor.spawn([\amp, vel.linlin(0,127,0,1.0), \bufnum, 14], 1); 
		fundnote = (justnote/2)-partials.frequencies.minItem;
		c = Pbind(\midinote, Pseq.new((partials.frequencies+fundnote).cpsmidi.round(1).postln, 1), \amp, Pseq.new(partials.magnitudes.linlin(0,12,0,127), 1), \legato, ~legato, \dur, Pseq.new(Array.fill(partials.frequencies.size, { 0.1677+rrand(-0.01,0.01);};), 1)); 
		z = c <> (type: \midi, midiout:  h);  
		1.do{z.next(Event.new).play}; 
	});
	if(~step4 == 51, 
		{~tenor.spawn([\amp, vel.linlin(0,127,0,1.0), \bufnum, 15], 1)}
	);
	if(~step4 == 55, 
		{~tenor.spawn([\amp, vel.linlin(0,127,0,1.0), \bufnum, 16], 1);}
	);
	if(~step4 == 59, 
		{~tenor.spawn([\amp, vel.linlin(0,127,0,1.0), \bufnum, 17], 1);}
	);
	if(~step4 == 63, 
		{~tenor.spawn([\amp, vel.linlin(0,127,0,1.0), \bufnum, 18], 1);}
	);
	if(~step4 == 67, 
		{~tenor.spawn([\amp, vel.linlin(0,127,0,1.0), \bufnum, 19], 1);}
	);
	if(~step4 == 71, 
		{~tenor.spawn([\amp, vel.linlin(0,127,0,1.0), \bufnum, 22], 1); 
		'SING: TA! SING: TA! SING: TA! SING: TA! SING: TA! SING: TA! SING: TA! SING: TA! SING: TA! SING: TA! SING: TA!'.postln; 
		~tenor1.set(\num, 11);}
	);
	if(~step4 == 75, 
		{~tenor.spawn([\amp, vel.linlin(0,127,0,1.0), \bufnum, 23], 1); 
		fundnote = (justnote/2)-partials.frequencies.minItem;
		c = Pbind(\midinote, Pseq.new((partials.frequencies+fundnote).cpsmidi.round(1).postln, 1), \amp, Pseq.new(partials.magnitudes.linlin(0,12,0,127), 1), \legato, ~legato, \dur, Pseq.new(Array.fill(partials.frequencies.size, { 0.19819+ rrand(-0.0118, 0.0118)};), 1)); 
		z = c <> (type: \midi, midiout:  h); 
		1.do{z.next(Event.new).play};}
	);
	if(~step4 == 85, 
		{~tenor.spawn([\amp, vel.linlin(0,127,0,1.0), \bufnum, 24], 1);}
	);
	if(~step4 == 87, 
		{~tenor.spawn([\amp, vel.linlin(0,127,0,1.0), \bufnum, 25], 1);}
	);		
	if(~step4 == 105, 
		{~tenor.spawn([\amp, vel.linlin(0,127,0,1.0), \bufnum, 26], 1);
		'SING: POI! SING: POI! SING: POI! SING: POI! SING: POI! SING: POI! SING: POI! SING: POI! SING: POI! SING: POI! SING: POI!'.postln; 
		~tenor1.set(\num, 12);}
	);
	if(~step4 == 109, {fundnote = (justnote/2)-partials.frequencies.minItem;
		c = Pbind(\midinote, Pseq.new((partials.frequencies+fundnote).cpsmidi.round(1).postln, 1), \amp, Pseq.new(partials.magnitudes.linlin(0,12,0,127), 1), \legato, ~legato, \dur, Pseq.new(Array.fill(partials.frequencies.size, { 0.181675+rrand(-0.01083,0.01083);};), 1)); 
		z = c <> (type: \midi, midiout:  h); 
		1.do{z.next(Event.new).play};}
	);
	if(~step4 == 175, 
		{Routine({1.do({
		~tenor.spawn([\amp, vel.linlin(0,127,0,0.5), \bufnum, 28], 1);
		rrand(0.24, 0.27).yield;
		~tenor.spawn([\amp, vel.linlin(0,127,0,0.5), \bufnum, 29], 1);});
		}).play;}
	);	
	if(~step4 == 179, 
		{~tenor.spawn([\amp, vel.linlin(0,127,0,0.25), \bufnum, 31], 1);}
	);	
	if(~step4 == 183, 
		{Routine({1.do({
		rrand(0.27, 0.29).yield;
		~tenor.spawn([\amp, vel.linlin(0,127,0,0.5), \bufnum, 33], 1);});
		}).play;}
	);	
	if(~step4 == 191, 
		{'SING: CHE! SING: CHE! SING: CHE! SING: CHE! SING: CHE! SING: CHE! SING: CHE! SING: CHE! SING: CHE! SING: CHE!'.postln;
		}
	);
	if(~step4 == 195, 
		{Routine({1.do({
		rrand(0.36,0.38).yield;
		~env.newatkenv;
		~envelopeControl.env(~env.atkenvelope);
		~alto2.set(\gates2, 0);
		~tenor.spawn([\amp, vel.linlin(0,127,0,1.0), \bufnum, 32], 1);})
		}).play;}
	);
	

if(~step4 == 197, {
		volstring = ~globamp3*adjString;
		~globamp4 = volstring;
		volstring = volstring*1.4;
		volpiano =  volstring*(1+adjPiano);
		mid = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(volstring.ampdb).linlin(0,1,0,127).round(1);
		mid2 = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(volpiano.ampdb).linlin(0,1,0,127).round(1);
		control.control(0, 84, mid);
		control.control(0, 86, mid2);
		~tenor.set(\globamp, volstring);
		~piano.set(\globamp, volpiano);
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 50], 1);
~tenor1.set(\num, 17);
});
if(~step4 == 199, {
Routine({1.do({
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 51], 1);
fundnote = (justnote/2)-partials.frequencies.minItem;
		c = Pbind(\midinote, Pseq.new((partials.frequencies+fundnote).cpsmidi.round(1).postln, 1), \amp, Pseq.new(partials.magnitudes.linlin(0,12,0,127), 1), \legato, ~legato, \dur, Pseq.new(Array.fill(partials.frequencies.size, { 0.128241+rrand(-0.007647,0.007647);};), 1)); 
		z = c <> (type: \midi, midiout:  h); 
		1.do{z.next(Event.new).play};
0.95775665409996.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 52], 1);
})}).play;
});
if(~step4 == 205, {
Routine({1.do({
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 53], 1);
0.096014210031192.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 54], 1);

})}).play;
});
if(~step4 == 207, {
Routine({1.do({
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 55], 1);
0.39121317876063.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 56], 1);
0.09959237934913.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 57], 1);
'SING: TEAS! SING: TEAS! SING: TEAS! SING: TEAS! SING: TEAS! SING: TEAS! SING: TEAS! SING: TEAS! SING: TEAS! SING: TEAS!'.postln;
~tenor1.set(\num, 15);
})}).play;
});
if(~step4 == 211, {
Routine({1.do({
fundnote = (justnote/2)-partials.frequencies.minItem;
		c = Pbind(\midinote, Pseq.new((partials.frequencies+fundnote).cpsmidi.round(1).postln, 1), \amp, Pseq.new(partials.magnitudes.linlin(0,12,0,127), 1), \legato, ~legato, \dur, Pseq.new(Array.fill(partials.frequencies.size, { 0.14534+rrand(-0.008667,0.008667);};), 1)); 
		z = c <> (type: \midi, midiout:  h); 
		1.do{z.next(Event.new).play};
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 58], 1);
0.10197782556108.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 59], 1);
0.1484940266942.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 60], 1);
})}).play;
});
if(~step4 == 213, {
Routine({1.do({
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 61], 1);
0.63751050014499.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 62], 1);
0.25405002157322.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 63], 1);
})}).play;
});
if(~step4 == 215, {
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 64], 1);
});
if(~step4 == 217, {
Routine({1.do({
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 65], 1);
0.11807958749178.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 66], 1);
'SING: SEN! SING: SEN! SING: SEN! SING: SEN! SING: SEN! SING: SEN! SING: SEN! SING: SEN! SING: SEN! SING: SEN! SING: SEN!'.postln;
~tenor1.set(\num, 16);
})}).play;
});
if(~step4 == 219, {
Routine({1.do({
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 67], 1);
fundnote = (justnote/2)-partials.frequencies.minItem;
		c = Pbind(\midinote, Pseq.new((partials.frequencies+fundnote).cpsmidi.round(1).postln, 1), \amp, Pseq.new(partials.magnitudes.linlin(0,12,0,127), 1), \legato, ~legato, \dur, Pseq.new(Array.fill(partials.frequencies.size, { 0.136256+rrand(-0.008125,0.008125);};), 1)); 
		z = c <> (type: \midi, midiout:  h); 
		1.do{z.next(Event.new).play};
0.87486239823452.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 68], 1);
0.31964979240198.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 69], 1);
})}).play;
});
if(~step4 == 221, {
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 70], 1);
});
if(~step4 == 231, {
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 71], 1);
});
if(~step4 == 235, {
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 72], 1);
});
if(~step4 == 239, {
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 73], 1);
'SING: TI! SING: TI! SING: TI! SING: TI! SING: TI! SING: TI! SING: TI! SING: TI! SING: TI! SING: TI! SING: TI! SING: TI!'.postln;
~tenor1.set(\num, 18);
});
if(~step4 == 243, {
Routine({1.do({
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 74], 1);
fundnote = (justnote/2)-partials.frequencies.minItem;
		c = Pbind(\midinote, Pseq.new((partials.frequencies+fundnote).cpsmidi.round(1).postln, 1), \amp, Pseq.new(partials.magnitudes.linlin(0,12,0,127), 1), \legato, ~legato, \dur, Pseq.new(Array.fill(partials.frequencies.size, { 0.121117+rrand(-0.007222,0.007222);};), 1)); 
		z = c <> (type: \midi, midiout:  h); 
		1.do{z.next(Event.new).play};
0.12821773389259.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 75], 1);
})}).play;
});
if(~step4 == 245, {
Routine({1.do({
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 76], 1);
0.028625354543461.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 77], 1);
2.4224206282403.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 78], 1);
})}).play;
});
if(~step4 == 247, {
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 79], 1);
'SING: CO! SING: CO! SING: CO! SING: CO! SING: CO! SING: CO! SING: CO! SING: CO! SING: CO! SING: CO! SING: CO! SING: CO!'.postln;
~tenor1.set(\num, 21);
});
if(~step4 == 251, {
Routine({1.do({
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 80], 1);
fundnote = (justnote/2)-partials.frequencies.minItem;
		c = Pbind(\midinote, Pseq.new((partials.frequencies+fundnote).cpsmidi.round(1).postln, 1), \amp, Pseq.new(partials.magnitudes.linlin(0,12,0,127), 1), \legato, ~legato, \dur, Pseq.new(Array.fill(partials.frequencies.size, { 0.103814+rrand(-0.00619, 0.00619);};), 1)); 
		z = c <> (type: \midi, midiout:  h); 
		1.do{z.next(Event.new).play};
0.14074132650535.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 81], 1);
})}).play;
});
if(~step4 == 253, {
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 82], 1);
});
if(~step4 == 257, {
Routine({1.do({
'SING: ME! SING: ME! SING: ME! SING: ME! SING: ME! SING: ME! SING: ME! SING: ME! SING: ME! SING: ME! SING: ME! SING: ME!'.postln;
~tenor1.set(\num, 23);
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 83], 1);
0.40612221758535.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 84], 1);
0.05426890132198.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 85], 1);
})}).play;
});
if(~step4 == 259, {
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 86], 1);
speed = 1.1; 
number = 23;
fundnote = (justnote/2)-partials.frequencies.minItem;
		c = Pbind(\midinote, Pseq.new((partials.frequencies+fundnote).cpsmidi.round(1).postln, 1), \amp, Pseq.new(partials.magnitudes.linlin(0,12,0,127), 1), \legato, ~legato, \dur, Pseq.new(Array.fill(partials.frequencies.size, {( 2.180094 / speed / number)+rrand(-0.12999 / speed / number, 0.12999 / speed / number);};), 1)); 
		z = c <> (type: \midi, midiout:  h); 
		1.do{z.next(Event.new).play};
});
if(~step4 == 263, {
Routine({1.do({
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 87], 1);
0.083490617418428.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 88], 1);
'SING: NE! SING: NE! SING: NE! SING: NE! SING: NE! SING: NE! SING: NE! SING: NE! SING: NE! SING: NE! SING: NE! SING: NE!'.postln;
~tenor1.set(\num, 24);
})}).play;
});
if(~step4 == 265, {
Routine({1.do({
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 89], 1);
0.022065377460584.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 90], 1);
})}).play;
});
if(~step4 == 267, {
Routine({1.do({
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 91], 1);
speed = 1.22; 
number = 24;
fundnote = (justnote/2)-partials.frequencies.minItem;
		c = Pbind(\midinote, Pseq.new((partials.frequencies+fundnote).cpsmidi.round(1).postln, 1), \amp, Pseq.new(partials.magnitudes.linlin(0,12,0,127), 1), \legato, ~legato, \dur, Pseq.new(Array.fill(partials.frequencies.size, {( 2.180094 / speed / number)+rrand(-0.12999 / speed / number, 0.12999 / speed / number);};), 1)); 
		z = c <> (type: \midi, midiout:  h); 
		1.do{z.next(Event.new).play};
0.039956224050248.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 92], 1);
})}).play;
});
if(~step4 == 269, {
Routine({1.do({
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 93], 1);
0.092436040713259.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 94], 1);
0.26060999865609.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 95], 1);
})}).play;
});
if(~step4 == 273, {
Routine({1.do({
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 96], 1);
0.027432631437483.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 97], 1);
0.50511823538149.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 98], 1);
})}).play;
});
if(~step4 == 275, {
Routine({1.do({
'SING: TI! SING: TI! SING: TI! SING: TI! SING: TI! SING: TI! SING: TI! SING: TI! SING: TI! SING: TI! SING: TI! SING: TI!'.postln;
~tenor1.set(\num, 29);
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 99], 1);
0.072159747911641.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 100], 1);
0.013119954165753.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 101], 1);
})}).play;
});
if(~step4 == 277, {
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 102], 1);
});
if(~step4 == 279, {
Routine({1.do({
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 103], 1);
speed = 1.52; 
number = 29;
fundnote = (justnote/2)-partials.frequencies.minItem;
		c = Pbind(\midinote, Pseq.new((partials.frequencies+fundnote).cpsmidi.round(1).reverse.postln, 1), \amp, Pseq.new(partials.magnitudes.linlin(0,12,0,127).reverse, 1), \legato, ~legato, \dur, Pseq.new(Array.fill(partials.frequencies.size, {( 2.180094 / speed / number)+rrand(-0.12999 / speed / number, 0.12999 / speed / number);};), 1)); 
		z = c <> (type: \midi, midiout:  h); 
		1.do{z.next(Event.new).play};
0.014909038824719.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 104], 1);
})}).play;
});
if(~step4 == 281, {
Routine({1.do({
'SING: IL! SING: IL! SING: IL! SING: IL! SING: IL! SING: IL! SING: IL! SING: IL! SING: IL! SING: IL! SING: IL! SING: IL!'.postln;
~tenor1.set(\num, 27);
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 105], 1);
0.10674871798499.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 106], 1);
})}).play;
});
if(~step4 == 285, {
Routine({1.do({
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 107], 1);
speed = 1.72; 
number = 27;
fundnote = (justnote/2)-partials.frequencies.minItem;
		c = Pbind(\midinote, Pseq.new((partials.frequencies+fundnote).cpsmidi.round(1).postln, 1), \amp, Pseq.new(partials.magnitudes.linlin(0,12,0,127), 1), \legato, ~legato, \dur, Pseq.new(Array.fill(partials.frequencies.size, {( 2.180094 / speed / number)+rrand(-0.12999 / speed / number, 0.12999 / speed / number);};), 1)); 
		z = c <> (type: \midi, midiout:  h); 
		1.do{z.next(Event.new).play};
0.069774301699686.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 108], 1);
0.212304712864.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 109], 1);
'SING: COR! SING: COR! SING: COR! SING: COR! SING: COR! SING: COR! SING: COR! SING: COR! SING: COR! SING: COR! SING: COR! SING: COR!'.postln;
~tenor1.set(\num, 28);
0.03995622405025.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 110], 1);
})}).play;
});
if(~step4 == 287, {
Routine({1.do({
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 111], 1);
0.21051562820504.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 112], 1);
speed = 1.96; 
number = 28;
fundnote = (justnote/2)-partials.frequencies.minItem;
		c5 = Pbind(\midinote, Pseq.new((partials.frequencies+fundnote).cpsmidi.round(1), 1), \amp, Pseq.new(partials.magnitudes.linlin(0,12,0,127), 1), \legato, ~legato, \dur, Pseq.new(Array.fill(partials.frequencies.size, {( 2.180094 / speed / number)+rrand(-0.12999 / speed / number, 0.12999 / speed / number);};), 1)); 
		d5 = Pbind(\midinote, Pseq.new((partials.frequencies+fundnote).cpsmidi.round(1).reverse, 1), \amp, Pseq.new(partials.magnitudes.linlin(0,12,0,127).reverse, 1), \legato, ~legato, \dur, Pseq.new(Array.fill(partials.frequencies.size, {( 2.180094 / speed / number)+rrand(-0.12999 / speed / number, 0.12999 / speed / number);};), 1)); 
		z5 = [c5, d5].choose.postln <> (type: \midi, midiout:  h); 
		1.do{z5.next(Event.new).play};
})}).play;
});
if(~step4 == 289, {
Routine({1.do({
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 113], 1);
0.061425239957843.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 114], 1);
'SING: POR! SING: POR! SING: POR! SING: POR! SING: POR! SING: POR! SING: POR! SING: POR! SING: POR! SING: POR! SING: POR! SING: POR!'.postln;
~tenor1.set(\num, 30);
})}).play;
});
if(~step4 == 291, {
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 115], 1);
});
if(~step4 == 293, {
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 116], 1);
speed = 2.27; 
number = 30;
fundnote = (justnote/2)-partials.frequencies.minItem;
		c1 = Pbind(\midinote, Pseq.new((partials.frequencies+fundnote).cpsmidi.round(1), 1), \amp, Pseq.new(partials.magnitudes.linlin(0,12,0,127), 1), \legato, ~legato, \dur, Pseq.new(Array.fill(partials.frequencies.size, {( 2.180094 / speed / number)+rrand(-0.12999 / speed / number, 0.12999 / speed / number);};), 1)); 
		d1 = Pbind(\midinote, Pseq.new((partials.frequencies+fundnote).cpsmidi.round(1).reverse, 1), \amp, Pseq.new(partials.magnitudes.linlin(0,12,0,127).reverse, 1), \legato, ~legato, \dur, Pseq.new(Array.fill(partials.frequencies.size, {( 2.180094 / speed / number)+rrand(-0.12999 / speed / number, 0.12999 / speed / number);};), 1)); 
		z1 = [c1, d1].choose.postln <> (type: \midi, midiout:  h); 
		1.do{z1.next(Event.new).play};
});
if(~step4 == 295, {
Routine({1.do({
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 117], 1);		
0.76871004180252.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 118], 1);
'SING: TAAIII! SING: TAAIII! SING: TAAIII! SING: TAAIII! SING: TAAIII! SING: TAAIII! SING: TAAIII! SING: TAAIII! SING: TAAIII! SING: TAAIII! SING: TAAIII! SING: TAAIII!'.postln;
~tenor1.set(\num, 33);
0.05605798598095.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 119], 1);
0.01252359261276.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 120], 1);
speed = 2.65; 
number = 33;
fundnote = (justnote/2)-partials.frequencies.minItem;
		c2 = Pbind(\midinote, Pseq.new((partials.frequencies+fundnote).cpsmidi.round(1), 1), \amp, Pseq.new(partials.magnitudes.linlin(0,12,0,127), 1), \legato, ~legato, \dur, Pseq.new(Array.fill(partials.frequencies.size, {( 2.180094 / speed / number)+rrand(-0.12999 / speed / number, 0.12999 / speed / number);};), 1)); 
		d2 = Pbind(\midinote, Pseq.new((partials.frequencies+fundnote).cpsmidi.round(1).reverse, 1), \amp, Pseq.new(partials.magnitudes.linlin(0,12,0,127).reverse, 1), \legato, ~legato, \dur, Pseq.new(Array.fill(partials.frequencies.size, {( 2.180094 / speed / number)+rrand(-0.12999 / speed / number, 0.12999 / speed / number);};), 1)); 
		z2 = [c2, d2].choose.postln <> (type: \midi, midiout:  h); 
		1.do{z2.next(Event.new).play};
'LONG: TOR! LONG: TOR! LONG: TOR! LONG: TOR! LONG: TOR! LONG: TOR! LONG: TOR! LONG: TOR! LONG: TOR! LONG: TOR! LONG: TOR! LONG: TOR!'.postln;
~tenor1.set(\num, 31);
})}).play;
});
if(~step4 == 297, {
Routine({1.do({
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 121], 1);
speed = [2.27, 2.65, 3.13].choose; 
number = 31;
fundnote = (justnote/2)-partials.frequencies.minItem;
		c3 = Pbind(\midinote, Pseq.new((partials.frequencies+fundnote).cpsmidi.round(1), 1), \amp, Pseq.new(partials.magnitudes.linlin(0,12,0,127), 1), \legato, ~legato, \dur, Pseq.new(Array.fill(partials.frequencies.size, {( 2.180094 / speed / number)+rrand(-0.12999 / speed / number, 0.12999 / speed / number);};), 1)); 
		d3 = Pbind(\midinote, Pseq.new((partials.frequencies+fundnote).cpsmidi.round(1).reverse, 1), \amp, Pseq.new(partials.magnitudes.linlin(0,12,0,127).reverse, 1), \legato, ~legato, \dur, Pseq.new(Array.fill(partials.frequencies.size, {( 2.180094 / speed / number)+rrand(-0.12999 / speed / number, 0.12999 / speed / number);};), 1)); 
		z3 = [c3, d3].choose.postln <> (type: \midi, midiout:  h); 
		1.do{z3.next(Event.new).play};
1.0138146400809.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 122], 1);
0.1341813494225.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 123], 1);
'LONG: TOR! LONG: TOR! LONG: TOR! LONG: TOR! LONG: TOR! LONG: TOR! LONG: TOR! LONG: TOR! LONG: TOR! LONG: TOR! LONG: TOR! LONG: TOR!'.postln;
~tenor1.set(\num, 32);
})}).play;
});
if(~step4 == 299, {
Routine({1.do({
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 124], 1);
0.48901647345079.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 125], 1);
speed = [2.27, 2.65, 3.13].choose; 
number = 32;
fundnote = (justnote/2)-partials.frequencies.minItem;
		c4 = Pbind(\midinote, Pseq.new((partials.frequencies+fundnote).cpsmidi.round(1), 1), \amp, Pseq.new(partials.magnitudes.linlin(0,12,0,127), 1), \legato, ~legato, \dur, Pseq.new(Array.fill(partials.frequencies.size, {( 2.180094 / speed / number)+rrand(-0.12999 / speed / number, 0.12999 / speed / number);};), 1)); 
		d4 = Pbind(\midinote, Pseq.new((partials.frequencies+fundnote).cpsmidi.round(1).reverse, 1), \amp, Pseq.new(partials.magnitudes.linlin(0,12,0,127).reverse, 1), \legato, ~legato, \dur, Pseq.new(Array.fill(partials.frequencies.size, {( 2.180094 / speed / number)+rrand(-0.12999 / speed / number, 0.12999 / speed / number);};), 1)); 
		z4 = [c4, d4].choose.postln <> (type: \midi, midiout:  h); 
		1.do{z4.next(Event.new).play};
0.05128709355704.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 126], 1);
0.53553267458387.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 127], 1);
'LONG: MEN! LONG: MEN! LONG: MEN! LONG: MEN! LONG: MEN! LONG: MEN! LONG: MEN! LONG: MEN! LONG: MEN! LONG: MEN! LONG: MEN! LONG: MEN!'.postln;
~tenor1.set(\num, 34);
})}).play;
});
if(~step4 == 301, {
Routine({1.do({
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 128], 1);
0.041148947156225.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 129], 1);
speed = [1.96, 2.27, 2.65, 3.13, 3.75].choose; 
number = 34;
fundnote = (justnote/2)-partials.frequencies.minItem;
		c5 = Pbind(\midinote, Pseq.new((partials.frequencies+fundnote).cpsmidi.round(1), 1), \amp, Pseq.new(partials.magnitudes.linlin(0,12,0,127), 1), \legato, ~legato, \dur, Pseq.new(Array.fill(partials.frequencies.size, {( 2.180094 / speed / number)+rrand(-0.12999 / speed / number, 0.12999 / speed / number);};), 1)); 
		d5 = Pbind(\midinote, Pseq.new((partials.frequencies+fundnote).cpsmidi.round(1).reverse, 1), \amp, Pseq.new(partials.magnitudes.linlin(0,12,0,127).reverse, 1), \legato, ~legato, \dur, Pseq.new(Array.fill(partials.frequencies.size, {( 2.180094 / speed / number)+rrand(-0.12999 / speed / number, 0.12999 / speed / number);};), 1)); 
		z5 = [c5, d5].choose.postln <> (type: \midi, midiout:  h); 
		1.do{z5.next(Event.new).play};
0.13477771097547.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 130], 1);
'LONG: MEN! LONG: MEN! LONG: MEN! LONG: MEN! LONG: MEN! LONG: MEN! LONG: MEN! LONG: MEN! LONG: MEN! LONG: MEN! LONG: MEN! LONG: MEN!'.postln;
~tenor1.set(\num, 37);
0.64407047722787.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 131], 1);
0.47530015773204.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 132], 1);
speed = [1.96, 2.27, 2.65, 3.13, 3.75].choose; 
number = 37;
fundnote = (justnote/2)-partials.frequencies.minItem;
		c1 = Pbind(\midinote, Pseq.new((partials.frequencies+fundnote).cpsmidi.round(1), 1), \amp, Pseq.new(partials.magnitudes.linlin(0,12,0,127), 1), \legato, ~legato, \dur, Pseq.new(Array.fill(partials.frequencies.size, {( 2.180094 / speed / number)+rrand(-0.12999 / speed / number, 0.12999 / speed / number);};), 1)); 
		d1 = Pbind(\midinote, Pseq.new((partials.frequencies+fundnote).cpsmidi.round(1).reverse, 1), \amp, Pseq.new(partials.magnitudes.linlin(0,12,0,127).reverse, 1), \legato, ~legato, \dur, Pseq.new(Array.fill(partials.frequencies.size, {( 2.180094 / speed / number)+rrand(-0.12999 / speed / number, 0.12999 / speed / number);};), 1)); 
		z1 = [c1, d1].choose.postln <> (type: \midi, midiout:  h); 
		1.do{z1.next(Event.new).play};

0.0351853316263.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 133], 1);
'BREATH! BREATH! BREATH! BREATH! BREATH! BREATH! BREATH! BREATH! BREATH! BREATH! BREATH! BREATH!'.postln;
0.3822677554658.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 134], 1);
0.4305730412579.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 135], 1);
'LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI!'.postln;
~tenor1.set(\num, 35);
})}).play;
});

if(~step4 == 305, {
Routine({1.do({
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 136], 1);
speed = [1.96, 2.27, 2.65, 3.13, 3.75].choose;
speed = speed / (35/37); 
fundnote = (justnote/2)-partials.frequencies.minItem;
		c2 = Pbind(\midinote, Pseq.new((partials.frequencies+fundnote).cpsmidi.round(1), 1), \amp, Pseq.new(partials.magnitudes.linlin(0,12,0,127), 1), \legato, ~legato, \dur, Pseq.new(Array.fill(partials.frequencies.size, {( 2.180094 / speed / number)+rrand(-0.12999 / speed / number, 0.12999 / speed / number);};), 1)); 
		d2 = Pbind(\midinote, Pseq.new((partials.frequencies+fundnote).cpsmidi.round(1).reverse, 1), \amp, Pseq.new(partials.magnitudes.linlin(0,12,0,127).reverse, 1), \legato, ~legato, \dur, Pseq.new(Array.fill(partials.frequencies.size, {( 2.180094 / speed / number)+rrand(-0.12999 / speed / number, 0.12999 / speed / number);};), 1)); 
		z2 = [c2, d2].choose.postln <> (type: \midi, midiout:  h); 
		1.do{z2.next(Event.new).play};
0.028028992990472.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 137], 1);
0.11509777972683.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 138], 1);
'LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI!'.postln;
~tenor1.set(\num, 36);
0.54388173632576.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 139], 1);
speed = [1.96, 2.27, 2.65, 3.13, 3.75].choose; 
speed = speed / (36/37);
fundnote = (justnote/2)-partials.frequencies.minItem;
		c3 = Pbind(\midinote, Pseq.new((partials.frequencies+fundnote).cpsmidi.round(1), 1), \amp, Pseq.new(partials.magnitudes.linlin(0,12,0,127), 1), \legato, ~legato, \dur, Pseq.new(Array.fill(partials.frequencies.size, {( 2.180094 / speed / number)+rrand(-0.12999 / speed / number, 0.12999 / speed / number);};), 1)); 
		d4 = Pbind(\midinote, Pseq.new((partials.frequencies+fundnote).cpsmidi.round(1).reverse, 1), \amp, Pseq.new(partials.magnitudes.linlin(0,12,0,127).reverse, 1), \legato, ~legato, \dur, Pseq.new(Array.fill(partials.frequencies.size, {( 2.180094 / speed / number)+rrand(-0.12999 / speed / number, 0.12999 / speed / number);};), 1)); 
		z4 = [c4, d4].choose.postln <> (type: \midi, midiout:  h); 
		1.do{z4.next(Event.new).play};
0.2021665664632.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 140], 1);
'LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI!'.postln;
~tenor1.set(\num, 38);
0.15624672688304.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 141], 1);
0.0220653774606.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 142], 1);
speed = [1.96, 2.27, 2.65, 3.13, 3.75].choose; 
speed = speed / (38/37);
fundnote = (justnote/2)-partials.frequencies.minItem;
		c5 = Pbind(\midinote, Pseq.new((partials.frequencies+fundnote).cpsmidi.round(1), 1), \amp, Pseq.new(partials.magnitudes.linlin(0,12,0,127), 1), \legato, ~legato, \dur, Pseq.new(Array.fill(partials.frequencies.size, {( 2.180094 / speed / number)+rrand(-0.12999 / speed / number, 0.12999 / speed / number);};), 1)); 
		d5 = Pbind(\midinote, Pseq.new((partials.frequencies+fundnote).cpsmidi.round(1).reverse, 1), \amp, Pseq.new(partials.magnitudes.linlin(0,12,0,127).reverse, 1), \legato, ~legato, \dur, Pseq.new(Array.fill(partials.frequencies.size, {( 2.180094 / speed / number)+rrand(-0.12999 / speed / number, 0.12999 / speed / number);};), 1)); 
		z5 = [c5, d5].choose.postln <> (type: \midi, midiout:  h); 
		1.do{z5.next(Event.new).play};
})}).play;
});
if(~step4 == 307, {
Routine({1.do({
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 143], 1);
'LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI!'.postln;
~tenor1.set(\num, 41);
0.020872654354607.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 144], 1);
0.31010800755416.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 145], 1);
speed = [1.72, 1.96, 2.27, 2.65, 3.13, 3.75, 4.55].choose;   
speed = speed / (41/37);
fundnote = (justnote/2)-partials.frequencies.minItem;
		c6 = Pbind(\midinote, Pseq.new((partials.frequencies+fundnote).cpsmidi.round(1), 1), \amp, Pseq.new(partials.magnitudes.linlin(0,12,0,127), 1), \legato, ~legato, \dur, Pseq.new(Array.fill(partials.frequencies.size, {( 2.180094 / speed / number)+rrand(-0.12999 / speed / number, 0.12999 / speed / number);};), 1)); 
		d6 = Pbind(\midinote, Pseq.new((partials.frequencies+fundnote).cpsmidi.round(1).reverse, 1), \amp, Pseq.new(partials.magnitudes.linlin(0,12,0,127).reverse, 1), \legato, ~legato, \dur, Pseq.new(Array.fill(partials.frequencies.size, {( 2.180094 / speed / number)+rrand(-0.12999 / speed / number, 0.12999 / speed / number);};), 1)); 
		z6 = [c6, d6].choose.postln <> (type: \midi, midiout:  h); 
		1.do{z6.next(Event.new).play};
0.18487208142652.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 146], 1);
'LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI!'.postln;
~tenor1.set(\num, 43);
0.46396928822526.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 147], 1);
0.01431267727173.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 148], 1);
speed = [1.72, 1.96, 2.27, 2.65, 3.13, 3.75, 4.55].choose;  
speed = speed / (43/37);
fundnote = (justnote/2)-partials.frequencies.minItem;
		c7 = Pbind(\midinote, Pseq.new((partials.frequencies+fundnote).cpsmidi.round(1), 1), \amp, Pseq.new(partials.magnitudes.linlin(0,12,0,127), 1), \legato, ~legato, \dur, Pseq.new(Array.fill(partials.frequencies.size, {( 2.180094 / speed / number)+rrand(-0.12999 / speed / number, 0.12999 / speed / number);};), 1)); 
		d7 = Pbind(\midinote, Pseq.new((partials.frequencies+fundnote).cpsmidi.round(1).reverse, 1), \amp, Pseq.new(partials.magnitudes.linlin(0,12,0,127).reverse, 1), \legato, ~legato, \dur, Pseq.new(Array.fill(partials.frequencies.size, {( 2.180094 / speed / number)+rrand(-0.12999 / speed / number, 0.12999 / speed / number);};), 1)); 
		z7 = [c7, d7].choose.postln <> (type: \midi, midiout:  h); 
		1.do{z7.next(Event.new).play};
})}).play;
});
if(~step4 == 309, {
Routine({1.do({
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 149], 1);
'LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI!'.postln;
~tenor1.set(\num, 45);
0.31130073066014.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 150], 1);
0.21349743596998.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 151], 1);
speed = [1.72, 1.96, 2.27, 2.65, 3.13, 3.75, 4.55].choose;  
speed = speed / (45/37);
fundnote = (justnote/2)-partials.frequencies.minItem;
		c8 = Pbind(\midinote, Pseq.new((partials.frequencies+fundnote).cpsmidi.round(1), 1), \amp, Pseq.new(partials.magnitudes.linlin(0,12,0,127), 1), \legato, ~legato, \dur, Pseq.new(Array.fill(partials.frequencies.size, {( 2.180094 / speed / number)+rrand(-0.12999 / speed / number, 0.12999 / speed / number);};), 1)); 
		d8 = Pbind(\midinote, Pseq.new((partials.frequencies+fundnote).cpsmidi.round(1).reverse, 1), \amp, Pseq.new(partials.magnitudes.linlin(0,12,0,127).reverse, 1), \legato, ~legato, \dur, Pseq.new(Array.fill(partials.frequencies.size, {( 2.180094 / speed / number)+rrand(-0.12999 / speed / number, 0.12999 / speed / number);};), 1)); 
		z8 = [c8, d8].choose.postln <> (type: \midi, midiout:  h); 
		1.do{z8.next(Event.new).play};
0.11569414127982.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 152], 1);
'LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI!'.postln;
~tenor1.set(\num, 47);
0.03041443920243.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 153], 1);

})}).play;
});
if(~step4 == 311, {
Routine({1.do({
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 154], 1);
speed = [1.72, 1.96, 2.27, 2.65, 3.13, 3.75, 4.55].choose; 
speed = speed / (47/37);
fundnote = (justnote/2)-partials.frequencies.minItem;
		c9 = Pbind(\midinote, Pseq.new((partials.frequencies+fundnote).cpsmidi.round(1), 1), \amp, Pseq.new(partials.magnitudes.linlin(0,12,0,127), 1), \legato, ~legato, \dur, Pseq.new(Array.fill(partials.frequencies.size, {( 2.180094 / speed / number)+rrand(-0.12999 / speed / number, 0.12999 / speed / number);};), 1)); 
		d9 = Pbind(\midinote, Pseq.new((partials.frequencies+fundnote).cpsmidi.round(1).reverse, 1), \amp, Pseq.new(partials.magnitudes.linlin(0,12,0,127).reverse, 1), \legato, ~legato, \dur, Pseq.new(Array.fill(partials.frequencies.size, {( 2.180094 / speed / number)+rrand(-0.12999 / speed / number, 0.12999 / speed / number);};), 1)); 
		z9 = [c9, d9].choose.postln <> (type: \midi, midiout:  h); 
		1.do{z9.next(Event.new).play};
0.30295166891829.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 155], 1);
'LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI!'.postln;
~tenor1.set(\num, 49);
0.02981807764944.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 156], 1);
speed = [1.72, 1.96, 2.27, 2.65, 3.13, 3.75, 4.55].choose; 
speed = speed / (49/37);
fundnote = (justnote/2)-partials.frequencies.minItem;
		c10 = Pbind(\midinote, Pseq.new((partials.frequencies+fundnote).cpsmidi.round(1), 1), \amp, Pseq.new(partials.magnitudes.linlin(0,12,0,127), 1), \legato, ~legato, \dur, Pseq.new(Array.fill(partials.frequencies.size, {( 2.180094 / speed / number)+rrand(-0.12999 / speed / number, 0.12999 / speed / number);};), 1)); 
		d10 = Pbind(\midinote, Pseq.new((partials.frequencies+fundnote).cpsmidi.round(1).reverse, 1), \amp, Pseq.new(partials.magnitudes.linlin(0,12,0,127).reverse, 1), \legato, ~legato, \dur, Pseq.new(Array.fill(partials.frequencies.size, {( 2.180094 / speed / number)+rrand(-0.12999 / speed / number, 0.12999 / speed / number);};), 1)); 
		z10 = [c10, d10].choose.postln <> (type: \midi, midiout:  h); 
		1.do{z10.next(Event.new).play};
0.5629653060214.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 157], 1);
'LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI!'.postln;
~tenor1.set(\num, 51);
0.02564354677852.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 158], 1);
speed = [1.72, 1.96, 2.27, 2.65, 3.13, 3.75, 4.55].choose; 
speed = speed / (51/37);
fundnote = (justnote/2)-partials.frequencies.minItem;
		c1 = Pbind(\midinote, Pseq.new((partials.frequencies+fundnote).cpsmidi.round(1), 1), \amp, Pseq.new(partials.magnitudes.linlin(0,12,0,127), 1), \legato, ~legato, \dur, Pseq.new(Array.fill(partials.frequencies.size, {( 2.180094 / speed / number)+rrand(-0.12999 / speed / number, 0.12999 / speed / number);};), 1)); 
		d1 = Pbind(\midinote, Pseq.new((partials.frequencies+fundnote).cpsmidi.round(1).reverse, 1), \amp, Pseq.new(partials.magnitudes.linlin(0,12,0,127).reverse, 1), \legato, ~legato, \dur, Pseq.new(Array.fill(partials.frequencies.size, {( 2.180094 / speed / number)+rrand(-0.12999 / speed / number, 0.12999 / speed / number);};), 1)); 
		z1 = [c1, d1].choose.postln <> (type: \midi, midiout:  h); 
		1.do{z1.next(Event.new).play};
0.27432631437485.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 159], 1);
'LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI!'.postln;
~tenor1.set(\num, 53);
0.2439118751724.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 160], 1);
speed = [1.72, 1.96, 2.27, 2.65, 3.13, 3.75, 4.55].choose; 
speed = speed / (53/37);
fundnote = (justnote/2)-partials.frequencies.minItem;
		c2 = Pbind(\midinote, Pseq.new((partials.frequencies+fundnote).cpsmidi.round(1), 1), \amp, Pseq.new(partials.magnitudes.linlin(0,12,0,127), 1), \legato, ~legato, \dur, Pseq.new(Array.fill(partials.frequencies.size, {( 2.180094 / speed / number)+rrand(-0.12999 / speed / number, 0.12999 / speed / number);};), 1)); 
		d2 = Pbind(\midinote, Pseq.new((partials.frequencies+fundnote).cpsmidi.round(1).reverse, 1), \amp, Pseq.new(partials.magnitudes.linlin(0,12,0,127).reverse, 1), \legato, ~legato, \dur, Pseq.new(Array.fill(partials.frequencies.size, {( 2.180094 / speed / number)+rrand(-0.12999 / speed / number, 0.12999 / speed / number);};), 1)); 
		z2 = [c2, d2].choose.postln <> (type: \midi, midiout:  h); 
		1.do{z2.next(Event.new).play};
~tenor1.set(\num, 55);
})}).play;
});
if(~step4 == 313, {
Routine({1.do({
'LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI!'.postln;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 161], 1);
speed = [1.72, 1.96, 2.27, 2.65, 3.13, 3.75, 4.55].choose; 
speed = speed / (55/37);
fundnote = (justnote/2)-partials.frequencies.minItem;
		c3 = Pbind(\midinote, Pseq.new((partials.frequencies+fundnote).cpsmidi.round(1), 1), \amp, Pseq.new(partials.magnitudes.linlin(0,12,0,127), 1), \legato, ~legato, \dur, Pseq.new(Array.fill(partials.frequencies.size, {( 2.180094 / speed / number)+rrand(-0.12999 / speed / number, 0.12999 / speed / number);};), 1)); 
		d3 = Pbind(\midinote, Pseq.new((partials.frequencies+fundnote).cpsmidi.round(1).reverse, 1), \amp, Pseq.new(partials.magnitudes.linlin(0,12,0,127).reverse, 1), \legato, ~legato, \dur, Pseq.new(Array.fill(partials.frequencies.size, {( 2.180094 / speed / number)+rrand(-0.12999 / speed / number, 0.12999 / speed / number);};), 1)); 
		z3 = [c3, d3].choose.postln <> (type: \midi, midiout:  h); 
		1.do{z3.next(Event.new).play};
~tenor1.set(\num, 57);
0.023258100566562.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 162], 1);
speed = [1.72, 1.96, 2.27, 2.65, 3.13, 3.75, 4.55].choose; 
speed = speed / (57/37);
fundnote = (justnote/2)-partials.frequencies.minItem;
		c4 = Pbind(\midinote, Pseq.new((partials.frequencies+fundnote).cpsmidi.round(1), 1), \amp, Pseq.new(partials.magnitudes.linlin(0,12,0,127), 1), \legato, ~legato, \dur, Pseq.new(Array.fill(partials.frequencies.size, {( 2.180094 / speed / number)+rrand(-0.12999 / speed / number, 0.12999 / speed / number);};), 1)); 
		d4 = Pbind(\midinote, Pseq.new((partials.frequencies+fundnote).cpsmidi.round(1).reverse, 1), \amp, Pseq.new(partials.magnitudes.linlin(0,12,0,127).reverse, 1), \legato, ~legato, \dur, Pseq.new(Array.fill(partials.frequencies.size, {( 2.180094 / speed / number)+rrand(-0.12999 / speed / number, 0.12999 / speed / number);};), 1)); 
		z4 = [c4, d4].choose.postln <> (type: \midi, midiout:  h); 
		1.do{z4.next(Event.new).play};
0.002981807764944.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 163], 1);
~tenor1.set(\num, 61);
0.059039793745888.randDifMul(0.01).yield;
'LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI!'.postln;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 164], 1);
speed = [1.72, 1.96, 2.27, 2.65, 3.13, 3.75, 4.55].choose; 
speed = speed / (61/37);
fundnote = (justnote/2)-partials.frequencies.minItem;
		c6 = Pbind(\midinote, Pseq.new((partials.frequencies+fundnote).cpsmidi.round(1), 1), \amp, Pseq.new(partials.magnitudes.linlin(0,12,0,127), 1), \legato, ~legato, \dur, Pseq.new(Array.fill(partials.frequencies.size, {( 2.180094 / speed / number)+rrand(-0.12999 / speed / number, 0.12999 / speed / number);};), 1)); 
		d6 = Pbind(\midinote, Pseq.new((partials.frequencies+fundnote).cpsmidi.round(1).reverse, 1), \amp, Pseq.new(partials.magnitudes.linlin(0,12,0,127).reverse, 1), \legato, ~legato, \dur, Pseq.new(Array.fill(partials.frequencies.size, {( 2.180094 / speed / number)+rrand(-0.12999 / speed / number, 0.12999 / speed / number);};), 1)); 
		z6 = [c6, d6].choose.postln <> (type: \midi, midiout:  h); 
		1.do{z6.next(Event.new).play};
~tenor1.set(\num, 63);
0.45562022648342.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 165], 1);
speed = [1.72, 1.96, 2.27, 2.65, 3.13, 3.75, 4.55].choose; 
speed = speed / (63/37);
fundnote = (justnote/2)-partials.frequencies.minItem;
		c7 = Pbind(\midinote, Pseq.new((partials.frequencies+fundnote).cpsmidi.round(1), 1), \amp, Pseq.new(partials.magnitudes.linlin(0,12,0,127), 1), \legato, ~legato, \dur, Pseq.new(Array.fill(partials.frequencies.size, {( 2.180094 / speed / number)+rrand(-0.12999 / speed / number, 0.12999 / speed / number);};), 1)); 
		d7 = Pbind(\midinote, Pseq.new((partials.frequencies+fundnote).cpsmidi.round(1).reverse, 1), \amp, Pseq.new(partials.magnitudes.linlin(0,12,0,127).reverse, 1), \legato, ~legato, \dur, Pseq.new(Array.fill(partials.frequencies.size, {( 2.180094 / speed / number)+rrand(-0.12999 / speed / number, 0.12999 / speed / number);};), 1)); 
		z7 = [c7, d7].choose.postln <> (type: \midi, midiout:  h); 
		1.do{z7.next(Event.new).play};
0.16578851173088.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 166], 1);
~tenor1.set(\num, 67);
0.09303240226625.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 167], 1);
speed = [1.72, 1.96, 2.27, 2.65, 3.13, 3.75, 4.55].choose; 
speed = speed / (67/37);
fundnote = (justnote/2)-partials.frequencies.minItem;
		c9 = Pbind(\midinote, Pseq.new((partials.frequencies+fundnote).cpsmidi.round(1), 1), \amp, Pseq.new(partials.magnitudes.linlin(0,12,0,127), 1), \legato, ~legato, \dur, Pseq.new(Array.fill(partials.frequencies.size, {( 2.180094 / speed / number)+rrand(-0.12999 / speed / number, 0.12999 / speed / number);};), 1)); 
		d9 = Pbind(\midinote, Pseq.new((partials.frequencies+fundnote).cpsmidi.round(1).reverse, 1), \amp, Pseq.new(partials.magnitudes.linlin(0,12,0,127).reverse, 1), \legato, ~legato, \dur, Pseq.new(Array.fill(partials.frequencies.size, {( 2.180094 / speed / number)+rrand(-0.12999 / speed / number, 0.12999 / speed / number);};), 1)); 
		z9 = [c9, d9].choose.postln <> (type: \midi, midiout:  h); 
		1.do{z9.next(Event.new).play};
0.00655997708288.randDifMul(0.01).yield;
'LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI!'.postln;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 168], 1);
~tenor1.set(\num, 71);
0.24391187517238.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 169], 1);
speed = [1.72, 1.96, 2.27, 2.65, 3.13, 3.75, 4.55].choose; 
speed = speed / (71/37);
fundnote = (justnote/2)-partials.frequencies.minItem;
		c1 = Pbind(\midinote, Pseq.new((partials.frequencies+fundnote).cpsmidi.round(1), 1), \amp, Pseq.new(partials.magnitudes.linlin(0,12,0,127), 1), \legato, ~legato, \dur, Pseq.new(Array.fill(partials.frequencies.size, {( 2.180094 / speed / number)+rrand(-0.12999 / speed / number, 0.12999 / speed / number);};), 1)); 
		d1 = Pbind(\midinote, Pseq.new((partials.frequencies+fundnote).cpsmidi.round(1).reverse, 1), \amp, Pseq.new(partials.magnitudes.linlin(0,12,0,127).reverse, 1), \legato, ~legato, \dur, Pseq.new(Array.fill(partials.frequencies.size, {( 2.180094 / speed / number)+rrand(-0.12999 / speed / number, 0.12999 / speed / number);};), 1)); 
		z1 = [c1, d1].choose.postln <> (type: \midi, midiout:  h); 
		1.do{z1.next(Event.new).play};
0.0131199541658.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 170], 1);
~tenor1.set(\num, 75);
0.2492791291493.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 171], 1);
speed = [1.72, 1.96, 2.27, 2.65, 3.13, 3.75, 4.55].choose; 
speed = speed / (75/37);
fundnote = (justnote/2)-partials.frequencies.minItem;
		c3 = Pbind(\midinote, Pseq.new((partials.frequencies+fundnote).cpsmidi.round(1), 1), \amp, Pseq.new(partials.magnitudes.linlin(0,12,0,127), 1), \legato, ~legato, \dur, Pseq.new(Array.fill(partials.frequencies.size, {( 2.180094 / speed / number)+rrand(-0.12999 / speed / number, 0.12999 / speed / number);};), 1)); 
		d3 = Pbind(\midinote, Pseq.new((partials.frequencies+fundnote).cpsmidi.round(1).reverse, 1), \amp, Pseq.new(partials.magnitudes.linlin(0,12,0,127).reverse, 1), \legato, ~legato, \dur, Pseq.new(Array.fill(partials.frequencies.size, {( 2.180094 / speed / number)+rrand(-0.12999 / speed / number, 0.12999 / speed / number);};), 1)); 
		z3 = [c3, d3].choose.postln <> (type: \midi, midiout:  h); 
		1.do{z3.next(Event.new).play};
0.0065599770828999.randDifMul(0.01).yield;
'LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI! LONG: TI!'.postln;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 172], 1);
~tenor1.set(\num, 79);
0.0858760636303.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 173], 1);
speed = [1.72, 1.96, 2.27, 2.65, 3.13, 3.75, 4.55].choose; 
speed = speed / (79/37);
fundnote = (justnote/2)-partials.frequencies.minItem;
		c5 = Pbind(\midinote, Pseq.new((partials.frequencies+fundnote).cpsmidi.round(1), 1), \amp, Pseq.new(partials.magnitudes.linlin(0,12,0,127), 1), \legato, ~legato, \dur, Pseq.new(Array.fill(partials.frequencies.size, {( 2.180094 / speed / number)+rrand(-0.12999 / speed / number, 0.12999 / speed / number);};), 1)); 
		d5 = Pbind(\midinote, Pseq.new((partials.frequencies+fundnote).cpsmidi.round(1).reverse, 1), \amp, Pseq.new(partials.magnitudes.linlin(0,12,0,127).reverse, 1), \legato, ~legato, \dur, Pseq.new(Array.fill(partials.frequencies.size, {( 2.180094 / speed / number)+rrand(-0.12999 / speed / number, 0.12999 / speed / number);};), 1)); 
		z5 = [c5, d5].choose.postln <> (type: \midi, midiout:  h); 
		1.do{z5.next(Event.new).play};
~tenor1.set(\num, 81);
0.4747037961791.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 174], 1);
0.0202762928016.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 175], 1);
0.2844644607757.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 176], 1);
0.0208726543546.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 177], 1);
0.530165420607.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 178], 1);
0.2295991979007.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 179], 1);
0.0357816931793.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 180], 1);
0.2737299528218.randDifMul(0.01).yield;
~tenor.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 181], 1);

})}).play;
});



	
	//noteOff		
	
	if(~step4 == 214, 
		{~alto2.set(\endgate, 1);
		}
	); 
	//end noteOff
	
}

bassScore {arg note, vel; 
var justnote, volperc, mid;
	justnote = note.midijust(415); 
	~step5.post; '/B'.postln;
	
	
	//noteOn
	
	if(~step5 == 39, 
		{volperc =  ~globamp4*initPerc;
		mid = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(volperc.ampdb).linlin(0,1,0,127).round(1);
		control.control(0, 85, mid);
		~bass.set(\globamp, volperc);
		~bass.put(0, \korea, 0, [\amp, vel.linlin(0,127,0, 1.0), \lengh, 2, \bufnum, 277]);
		~bass.put(1, \korea, 0, [\amp, vel.linlin(0,127,0, 1.0), \lengh, 2, \bufnum, 278]);
		}
	);
	if(~step5 == 49, 
		{~bass.spawn([\amp, vel.linlin(0,127,0, 1.0), \bufnum, 279], 0); 
		~bass.spawn([\amp, vel.linlin(0,127,0, 1.0), \bufnum, 280], 1);
		}
	);
	if(~step5 == 71, 
		{~bass.spawn([\amp, vel.linlin(0,127,0, 1.0), \bufnum, 281], 0); 
		}
	);	
	if(~step5 == 77, 
		{~bass.spawn([\amp, vel.linlin(0,127,0, 1.0), \bufnum, 282], 0); 
		~bass.spawn([\amp, vel.linlin(0,127,0, 1.0), \bufnum, 283], 1);
		}
	);	
	if(~step5 == 117, 
		{~bass.spawn([\amp, vel.linlin(0,127,0, 1.0), \bufnum, 284], 0); }
	);
	if(~step5 == 125,
		{~bass.spawn([\amp, vel.linlin(0,127,0,1.0), \lengh, 6, \bufnum, 32], 0)};
	);

if(~step5 == 133, {
		volperc =  (~globamp3*adjString)*initPerc;
		mid = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(volperc.ampdb).linlin(0,1,0,127).round(1);
		control.control(0, 85, mid);
		~bass.set(\globamp, volperc);
~bass.spawn([\amp, vel.linlin(0,127,0, 1.0), \bufnum, 263], 1);
});
if(~step5 == 135, {
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 276], 1);
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 270], 1);
});
if(~step5 == 137, {
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 200], 1);
});
if(~step5 == 139, {
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 201], 1);
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 264], 1);
});
if(~step5 == 143, {
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 265], 1);
});
if(~step5 == 145, {
Routine({1.do({
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 202], 1);
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 266], 1);
0.40135132516144.randDifMul.yield;
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 203], 1);
0.17950482744962.randDifMul.yield;
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0)/2.1.randDif(0.1), 'bufnum', 204], 1);
0.20991926665205.randDifMul.yield;
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0)/3.1.randDif(0.1), 'bufnum', 205], 1);
})}).play;
});
if(~step5 == 155, {
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 206], 1);
});
if(~step5 == 161, {
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 207], 1);
});
if(~step5 == 169, {
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 267], 1);
});
if(~step5 == 171, {
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 208], 1);
});
if(~step5 == 175, {
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 209], 1);
});
if(~step5 == 181, {
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 268], 1);
});
if(~step5 == 183, {
Routine({1.do({
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 210], 1);
0.89096416016522.randDifMul.yield;
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 211], 1);
})}).play;
});
if(~step5 == 185, {
Routine({1.do({
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 212], 1);
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 269], 1);
0.089454232948315.randDifMul.yield;
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0)/2.1.randDif(0.1), 'bufnum', 213], 1);
0.062021601510835.randDifMul.yield;
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0)/3.1.randDif(0.1), 'bufnum', 214], 1);
})}).play;
});
if(~step5 == 187, {
Routine({1.do({
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 215], 1);
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 270], 1);
0.096014210031192.randDifMul.yield;
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 216], 1);
})}).play;
});
if(~step5 == 193, {
Routine({1.do({
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 217], 1);
0.07275610946463.randDifMul.yield;
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 218], 1);
})}).play;
});
if(~step5 == 195, {
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 219], 1);
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 271], 1);
});
if(~step5 == 197, {
Routine({1.do({
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 220], 1);
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 272], 1);
1.0907452804165.randDifMul.yield;
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 221], 1);
0.4645656497782.randDifMul.yield;
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 222], 1);
1.1074434039002.randDifMul.yield;
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 223], 1);
0.8915605217182.randDifMul.yield;
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 224], 1);
0.4508493340595.randDifMul.yield;
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 225], 1);
0.050094370451.randDifMul.yield;
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 226], 1);
0.0357816931794.randDifMul.yield;
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0)/2.1.randDif(0.1), 'bufnum', 227], 1);
0.0333962469673.randDifMul.yield;
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0)/3.1.randDif(0.1), 'bufnum', 228], 1);
0.0339926085204.randDifMul.yield;
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0)/4.1.randDif(0.1), 'bufnum', 229], 1);
})}).play;
});
if(~step5 == 199, {
Routine({1.do({
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 230], 1);
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 273], 1);
4.6098748046032.randDifMul.yield;
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 231], 1);
})}).play;
});
if(~step5 == 201, {
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 232], 1);
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 274], 1);
});
if(~step5 == 203, {
Routine({1.do({
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 233], 1);
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 275], 1);
0.30295166891829.randDifMul.yield;
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 234], 1);
0.33336610812073.randDifMul.yield;
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 235], 1);
0.34410061607452.randDifMul.yield;
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 236], 1);
0.71623022513946.randDifMul.yield;
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 237], 1);
0.2761153990338.randDifMul.yield;
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 238], 1);
0.5247981666302.randDifMul.yield;
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 239], 1);
0.1729448503667.randDifMul.yield;
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 240], 1);
0.2880426300936.randDifMul.yield;
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 241], 1);
0.0387635009443.randDifMul.yield;
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 242], 1);
0.050690732004.randDifMul.yield;
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0)/2.1.randDif(0.1), 'bufnum', 243], 1);
0.0232581005666.randDifMul.yield;
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0)/3.1.randDif(0.1), 'bufnum', 244], 1);
0.0322035238614.randDifMul.yield;
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 245], 1);
0.0834906174184.randDifMul.yield;
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 246], 1);
0.3691478013.randDifMul.yield;
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 247], 1);
0.0369744162853.randDifMul.yield;
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 248], 1);
0.5534235211736.randDifMul.yield;
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 249], 1);
0.2480864060433.randDifMul.yield;
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 250], 1);
0.057250709087.randDifMul.yield;
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 251], 1);
0.4681438190961.randDifMul.yield;
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 252], 1);
0.2379482596426.randDifMul.yield;
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 253], 1);
1.0817998571216.randDifMul.yield;
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 254], 1);
0.848622489903.randDifMul.yield;
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 255], 1);
0.2468936829374.randDifMul.yield;
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 256], 1);
0.0638106861698.randDifMul.yield;
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 257], 1);
0.0268362698845.randDifMul.yield;
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 258], 1);
0.014312677271699.randDifMul.yield;
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0), 'bufnum', 259], 1);
0.0131199541657.randDifMul.yield;
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0)/2.1.randDif(0.1), 'bufnum', 260], 1);
0.020872654354601.randDifMul.yield;
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0)/3.1.randDif(0.1), 'bufnum', 261], 1);
0.0178908465897.randDifMul.yield;
~bass.spawn(['amp', vel.linlin(0,127,0, 1.0)/4.1.randDif(0.1), 'bufnum', 262], 1);
})}).play;
});
			
	//noteOff	
	
	//end noteOff

}

}
