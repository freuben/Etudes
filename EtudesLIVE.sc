EtudesLIVE  : Etudes {var <>etudes1, <>etudes2, <>etudes3;

etudes1MIDI {arg mainvol=0;
etudes1 = Etudes1Score.new; 'buffers1'.postln;
etudes1.startEtudes1; 
Routine({1.do({
2.8.yield;
etudes1.vocoder; //for real performance
etudes1.genenv;
0.1.yield;
etudes1.buffers; 'buffers2'.postln;
2.8.yield;
etudes1.load;
2.0.yield;
this.etudes1begining;
//this.etudes1MIDIStart(0.5,1.8,1.8, 3.0, 1.8,1.1,4.0,2.1,2.0,2.1, 2.3,3.1,3.0, 7.0); 
});
s.volume_(mainvol);
control.control(0, 8, ControlSpec(-90, 6, \db, units: " dB").unmap(mainvol).linlin(0,1,0,127).round(1));
'done'.postln}).play

}

etudes2MIDI {arg mainvol=0;
etudes2 = Etudes2Score.new; 'start'.postln;
etudes2.startEtudes2; 
Routine({1.do({
1.6.yield;
etudes2.buffers; 'buffers1'.postln;
2.6.yield;
etudes2.buffers2; 'buffers2'.postln;
4.0.yield;
etudes2.buffers3; 'buffers3'.postln;
1.0.yield;
etudes2.startproxys; 'nodeproxys'.postln;
1.0.yield;
etudes2.load;
1.0.yield;
this.etudes2begining;
//this.etudes2MIDIStart(0.9,0.9,3.0,2.8, 1.0, 1.1,0.8,0.6, 0.5); 
});
s.volume_(mainvol);
control.control(0, 8, ControlSpec(-90, 6, \db, units: " dB").unmap(mainvol).linlin(0,1,0,127).round(1));
'done'.postln;}).play;
}

etudes3MIDI {arg mainvol=0;
var doc;
etudes3 = Etudes3Score.new;
etudes3.startEtudes3; 'buffers1'.postln;
Routine({1.do({
1.2.yield;
etudes3.buffers; 'buffers2'.postln;
1.2.yield;
etudes3.genenv;
etudes3.startproxys; 'nodeproxys'.postln;
1.0.yield;
etudes3.load;
1.0.yield;
this.etudes3begining;
//this.etudes3MIDIStart(1.2, 1.2, 2.3, 3.0,0.7, 1.0, 1.0); 

});
s.volume_(mainvol);
control.control(0, 8, ControlSpec(-90, 6, \db, units: " dB").unmap(mainvol).linlin(0,1,0,127).round(1));
'done'.postln;}).play;
}


	etudes1begining {
		etudes1.begining;
		this.stepstart;
}

	etudes2begining {
		etudes2.begining;
		this.stepstart;
}

	etudes3begining {
		etudes3.begining;
		this.stepstart;
}

	etudes1finish {
		SystemClock.clear;
		etudes1.finish;
		doc.close;
		etudes1 = nil;
}

	etudes2finish {
		SystemClock.clear;
		etudes2.finish;
		etudes2 = nil;		
}

	etudes3finish {
		SystemClock.clear;
		etudes3.finish;
		doc.close;
		etudes3 = nil;		
}

	etudes1clearproxys {
		SystemClock.clear;
		etudes1.clearproxys;
}

	etudes2clearproxys {
		SystemClock.clear;
		etudes2.clearproxys;
}

	etudes3clearproxys {
		SystemClock.clear;
		etudes3.clearproxys;
}

etudes1MIDIStart {arg initVocoder = 0.75, initLowband=1.5, initGaterev=2.0, initGateperc= 2.0, initVoc2=2.0, initHighband = 2.0, initPiano = 2.0, initOrchfirst=2.0, initBow=2.0, initOrchords=2.0, initOrchmel = 2.0, initCel=2.0, initAntony = 2.0, initBombo = 3.0;
var inter, source,inter2, source2,inter3, source3;
var spec1, spec,spec2,spec3,spec4,spec5,spec6,spec7, gatespec, ampspec,ampspec2;

//specs for midicontroller
gatespec = ControlSpec(1.2, 10, 'lin');
spec = ControlSpec(0, 10, 'lin');
spec1 = ControlSpec(0.001, 10, \exponential);
spec2 = ControlSpec(0.004, 1, 'lin');
spec3 = ControlSpec(1023, 1010, 'lin');
spec4 = ControlSpec(1200, 500, 'lin');
spec5 = ControlSpec(1, 0, 'lin');
spec6 = ControlSpec(0, 15, 'lin');
ampspec = ControlSpec(0.ampdb, 1.ampdb, \db, units: " dB");
ampspec2 = ControlSpec(-90, 6, \db, units: " dB");

//clocks
this.clocks;

etudes1.startEtudes1Score(initVocoder, initLowband, initGaterev, initGateperc, initVoc2, initHighband, initPiano, initOrchfirst, initBow, initOrchords, initOrchmel, initCel, initAntony, initBombo);

this.document(documentPath ++ "/Lyrics/Etudes1.rtf");
	
MIDIIn.noteOn = {arg src, chan, num, vel; 	

	//midiPad
	if(src == midiPad, 
		{if(num == 37, 
			{if((~step5 >= 173).and(~step5 < 400), 
			{ ~bombocount.next; 
			~bassPiano.stop; 
			~bassPiano.start;}
		);
			if((~step5 >= 173).and(~step5 < 303), 
				{s.sendMsg("/s_new", "percussion", ~bombonode, 0, 2004, \out, 4, \lengh, 1.0, \bufnum, 15, \amp, vel/127, \globamp, ~globamp5*0.9);}
			);}
		);
		if(num == 53, {
		n.sendMsg("/midi", num, vel);
		});
		
	});
	
	
	//piano1

	if(src == traveler, 
		{
				//key division - SOPRANO
				if(num >= 60, 
					{SystemClock.sched(0.0,{ arg time;  
					freez1 = time; 
					 nil
					});
					
					
					if((now - freez1) >= 0.1,
					{routine1on.next; 
					
					//soprano noteOn messeges
					~step1 = step1on;
					etudes1.sopranoScore(num, vel);
		
					});
					
				});
				
		
		
				//key division - TENOR
				if(num < 60, 
					{SystemClock.sched(0.0,{ arg time;  
					freez4 = time; 
					 nil
					});
					
					if((now - freez4) >= 0.1,
					{routine4on.next; 
								
					//tenor noteOn messeges
					~step4 = step4on;
					etudes1.tenorScore((num + 12), vel);
						
			});
					};
				);
		
			};
	); //end piano1
	
	
	//piano2
	if(src == midiSportInA, 
		{
		
				//key division - ALTO1
				if(num >= 58, 
					{SystemClock.sched(0.0,{ arg time;  
					freez2 = time; 
					 nil
					});
					
					if((now - freez2) >= 0.1,
					{
					routine2on.next; 
				
					//alto1 noteOn messeges
					~step2 = step2on;
					etudes1.alto1Score(num, vel);
					
					});
					};
				); 
		
				//key division - BASS
				if(num < 58, 
					{SystemClock.sched(0.0,{ arg time;  
					freez5 = time; 
					 nil
					});
					
					if((now - freez5) >= 0.1,
					{
					//justnote5 = (num + 12).midijust(415);
					routine5on.next; 
				
					//bass noteOn messeges
					
					~step5 = step5on;
					etudes1.bassScore((num + 12), vel);
					
					});
					};
				);
	
			};
		); //end piano2
		
		
		//piano3
	if(src == midiSportInB, 
		{SystemClock.sched(0.0,{ arg time;  
					freez3 = time; 
					 nil
					});
					
					if((now - freez3) >= 0.1,
			{
			//justnote3 = (num + 12).midijust(415);
			routine3on.next;
			
			//alto2 noteOn messeges
			~step3 = step3on;
			etudes1.alto2Score((num + 12), vel);
			
			});
			
			};
		); //end piano3
		
 
}; //end noteOn

MIDIIn.noteOff = {arg src, chan, num, vel; 

		//piano1
		
	if(src == traveler, 
		{
		
				//key division - SOPRANO
				if((num >= 60).and(step1off != (step1on+1)), 
					{routine1off.next; 
					
					//soprano noteOff messeges
					~step1 = step1off;
					etudes1.sopranoScore(num, vel);
					
			
					});
				
		
				//key division - TENOR
				if((num < 60).and(step4off != (step4on + 1)), 
					{routine4off.next; 
				
					 //tenor noteOff messeges
					 ~step4 = step4off;
					etudes1.tenorScore((num + 12), vel);
				 	 
					};
				); 
		
			};
		); //end piano1
		
		//piano2
	if(src == midiSportInA, 
		{
		
				//key division - ALTO1
				if((num >= 58).and(step2off != (step2on+1)), 
					{routine2off.next; 
			
					//alto1 noteOff messeges
					~step2 = step2off;
					etudes1.alto1Score(num, vel);
				
					};
				); 
	
				//key division - BASS
				if((num < 58).and(step5off != (step5on+1)), 
					{routine5off.next; 
		
					 //bass noteOff messeges
					~step5 = step5off;
					etudes1.bassScore((num + 12), vel);
					
					};
				); 
		
			};
		);//end piano2
		
		
		//piano3
	if((src == midiSportInB).and(step3off != (step3on+1)),
			{routine3off.next;
			
			//alto2 noteOff messeges
			~step3 = step3off;
			etudes1.alto2Score((num + 12), vel);
			
			};
		); //end piano3
		

}; //end noteOff

//midi controller
	
MIDIIn.control = { | port, chan, num, val |
	

	if(port == behringer, {
	
//parameter control
	
	if(num == 1,
		{s.sendMsg("/n_set", 1017, "specgate", gatespec.map(val/127.0));}
	); //gaterev (44)
	if (num == 2,
		{s.sendMsg("/n_set", 1025, "specgate", spec.map(val/127.0));}
	); //pianogate (79 al principio, por ahi de 68 cuando van los solos...)
	if(num == 3,
		{s.sendMsg("/n_set", 2005, "white", spec2.map((val+1)/127.0), "bins", spec3.map((val+1)/127.0), "amp", spec4.map((val+1)/127.0) );}
	); //white noise (0)
	if(num == 4,
		{s.sendMsg("/n_set", 1002, "specgate", spec6.map(val/127.0), "vol", spec5.map(val/127.0));}
	); // pianogate as bass	(51)
	if(num == 5,
		{s.sendMsg("/n_set", 1018, "specgate", spec.map(val/127.0));}
	); //gateperc (44)
	if(num == 6,
		{s.sendMsg("/n_set", 2001, "amp", spec.map(val/127.0));}
	); //microphone (13)

	if(num == 8, {
	s.volume_(ampspec2.map(val/127.0).round(0.1));
	}); //master volume
	
//start

	if(num == 65,
		{etudes1.load;}
	);
	if(num == 66,
		{s.sendMsg(\n_set, 1014, \gates, 0); s.sendMsg(\n_set, 1015, \gates, 0);}
	);
	
//end
		
	if(num == 90, 
		{s.sendMsg("n_set", 1031, \gates, 0);
		'no gates'.postln}
	);	
	
	if(num == 92, 
		{Routine({'finish'.postln; 
			s.sendMsg("n_set", 2000, \gates, 0); 
			s.sendMsg("n_set", 2001, \gates, 0); 
			s.sendMsg("n_set", 2002, \gates, 0); 
			s.sendMsg("n_set", 2003, \gates, 0); 
			s.sendMsg("n_set", 2004, \gates, 0); 
			3.01.yield; 
			this.etudes1finish;
		}).play; }
	);
	
//volume control
	
	if(num == 81, 
		{~globamp1 = (ampspec.map(val/127.0).dbamp); 
			if((~step1 >= 1).and(~step1 < 51), 
				{s.sendMsg(\n_set, 1014, \globamp, ~globamp1);} //vocoder - por ahora
			);
			if((~step1 >= 51).and(~step1 <= 64),
				{s.sendMsg(\n_set, 1017, \globamp, ~globamp1);} //gatereverb
			);
			if((~step1 >= 65).and(~step1 <= 100),
				{s.sendMsg(\n_set, 1024, \globamp, ~globamp1);} //bandpass *4
			);
			if((~step1 >= 102).and(~step1 < 109),
				{s.sendMsg(\n_set, 1004, \globamp, ~globamp1); //bowedhigh
				s.sendMsg(\n_set, 1005, \globamp, ~globamp1);
				s.sendMsg(\n_set, 1006, \globamp, ~globamp1);}
			);
			if((~step1 >= 109).and(~step1 < 123),
				{s.sendMsg(\n_set, 1024, \globamp, ~globamp1);} //bandpass *4
			);
			if((~step1 >= 123).and(~step1 <= 132),
				{s.sendMsg(\n_set, 1017, \globamp, ~globamp1);} //gatereberb
			);
			if((~step1 >= 133).and(~step1 < 145),
				{s.sendMsg(\n_set, 1004, \globamp, ~globamp1); //bowedhigh
				s.sendMsg(\n_set, 1005, \globamp, ~globamp1);}
			);
			if((~step1 >= 145).and(~step1 < 153),
				{s.sendMsg(\n_set, 3003, \globamp, ~globamp1);} //pianorch
			);
			if((~step1 >= 153).and(~step1 < 167),
				{s.sendMsg(\n_set, 1004, \globamp, ~globamp1);} //bowedhigh
			);
			if((~step1 >= 158).and(~step1 < 167),
				{s.sendMsg(\n_set, 1006, \globamp, ~globamp1);} //bowedhigh
			);
			if((~step1 >= 166).and(~step1 <= 167),
				{s.sendMsg(\n_set, 1006, \globamp, ~globamp1);} //bowedhigh
			);
			if((~step1 >= 167).and(~step1 < 199),
				{s.sendMsg(\n_set, 1024, \globamp, ~globamp1);} //bandpass * 4
			);
			if((~step1 >= 199).and(~step1 <= 600),
				{s.sendMsg(\n_set, 1003, \globamp, ~globamp1);} //bowedhigh
			);
			if((~step1 >= 205).and(~step1 <= 600),
				{s.sendMsg(\n_set, 1004, \globamp, ~globamp1);} //bowedhigh
			);
			if((~step1 >= 211).and(~step1 <= 600),
				{s.sendMsg(\n_set, 1005, \globamp, ~globamp1);} //bowedhigh
			);
			if((~step1 >= 225).and(~step1 <= 600),
				{s.sendMsg(\n_set, 1006, \globamp, ~globamp1);} //bowedhigh
			);
				
		};
	);
	
	if(num == 82, 
		{~globamp2 = (ampspec.map(val/127.0).dbamp); 
			if((~step2 >= 1).and(~step2 <= 68),
				{s.sendMsg(\n_set, 1015, \globamp, ~globamp2);}//vocoder
			);
			if((~step2 >= 69).and(~step2 <= 130),
				{s.sendMsg("n_set", 1018, \globamp, ~globamp2);}//percgates * 1.1
			);
			if((~step2 >= 131).and(~step2 <= 159),
				{s.sendMsg("n_set", 1028, \globamp, ~globamp2);} //pianorch * 1.5
			);
			if((~step2 >= 147).and(~step2 <= 162),
				{s.sendMsg("n_set", 1029, \globamp, ~globamp2);} //pianorch * 1.5
			);
			if((~step2 >= 163).and(~step2 < 243),
				{s.sendMsg("n_set", 1017, \globamp, ~globamp2);} //gatereb
			);
			if((~step2 >= 243).and(~step2 <= 368),
				{s.sendMsg("n_set", 1030, \globamp, ~globamp2);} //antony * 1.5
			);
		}
	);
	
	if(num == 83, 
		{~globamp3 = (ampspec.map(val/127.0).dbamp);
			if((~step3 >= 51).and(~step3 <= 122),
				{s.sendMsg(\n_set, 1015, \globamp, ~globamp3);} //vocoder
			);
			if((~step3 >= 131).and(~step3 <= 510),
				{s.sendMsg(\n_set, 1031, \globamp, ~globamp3);} //percgate * 1.2
			);
		
		
		}
	);
	
	if(num == 84, 
		{~globamp4 = (ampspec.map(val/127.0).dbamp);
			if((~step4 >= 61).and(~step4 <= 112),
				{s.sendMsg(\n_set, 1014, \globamp, ~globamp4);} //vocoder
			);
			if((~step4 >= 113).and(~step4 <= 142),
				{s.sendMsg(\n_set, 2002, \globamp, ~globamp4);} //pianorch chords * 1
			);
			if((~step4 >= 143).and(~step4 <= 170),
				{s.sendMsg(\n_set, 2002, \globamp, ~globamp4);} //pianorch melodic * 2
			);
			if((~step4 >= 171).and(~step4 <= 336),
				{s.sendMsg(\n_set, 2002, \globamp, ~globamp4);} //celesta * 1.4
			);
		}
	);
	
	if(num == 85, 
		{~globamp5 = (ampspec.map(val/127.0).dbamp);
		
			if((~step5 >= 1).and(~step5 < 79), 
				{s.sendMsg(\n_set, 1011, \globamp, ~globamp5);} //bandpass * 2.0
			);
			if((~step5 >= 93).and(~step5 < 101), 
				{s.sendMsg(\n_set, 1024, \globamp, ~globamp5);} //bandpass * 2.0
			);
			if((~step5 >= 115).and(~step5 < 125), 
				{s.sendMsg(\n_set, 1024, \globamp, ~globamp5);} //bandpass * 2.0
			);
			if((~step5 >= 173).and(~step5 <= 175), 
				{s.sendMsg(\n_set, 1010, \globamp, ~globamp5); //perc * 1.2
				s.sendMsg(\n_set, 1011, \globamp, ~globamp5);} //perc * 1.2
			);
		}
	);
	
	if(num == 86, 
		{
		s.sendMsg(\n_set, 1811, \globamp, ~globamp6 = ampspec.map(val/127.0).dbamp);}
	);
	
//computer 2 network

	if((num == 87).or(num == 88).or(num == 7), 
		{n.sendMsg("/midi", num, val);}
	); 
	if(num == 70, 
		{control.control(0, 87, 108);
		control.control(0, 88, 118);
		n.sendMsg("/midi", num, val);}
	);
	if(num == 78, 
		{control.control(0, 87, 95);
		control.control(0, 88, 93);
		n.sendMsg("/midi", num, val);}
	);
	if(num == 71, 
		{control.control(0, 87, 29);
		control.control(0, 88, 64);
		n.sendMsg("/midi", num, val);}
	);
	if(num == 79, 
		{inter = rrand(32, 108); source = rrand(32, 118);
		control.control(0, 87, inter);
		control.control(0, 88, source);
		n.sendMsg("/midi", num, inter);
		n.sendMsg("/midi", num-10, source);
		}
	);
	if(num == 72, 
		{inter2 = rrand(0, 10); source2 = rrand(0, 10);
		control.control(0, 87, inter2);
		control.control(0, 88, source2);
		n.sendMsg("/midi", num, inter2);
		n.sendMsg("/midi", num-10, source2);
		}
	);
	if(num == 80, 
		{inter3 = rrand(32, 29); source3 = rrand(32, 29);
		control.control(0, 87, inter3);
		control.control(0, 88, source3);
		n.sendMsg("/midi", num, inter3);
		n.sendMsg("/midi", num-20, source3);
		}
	);
	
});
		
};

}


etudes2MIDIStart {arg initBow=1.0, initString=0.9, initPerc=2.0,initPiano=2.8, initGagok=2.8, initFlute=1.2, initReed=0.8, adjString=0.8, adjPiano=0.8;
var inter, source,inter2, source2,inter3, source3;
var spec,spec1,spec2,spec3,spec4;

//specs for midi controller
spec = ControlSpec(0.1, 4, 'lin');
spec1 = ControlSpec(0.ampdb, 1.ampdb, \db, units: " dB");
spec2 = ControlSpec(0.01, 50.0, 'lin');
spec3 = ControlSpec(0.0, 1.0, 'lin');
spec4 = ControlSpec(-90, 6, \db, units: " dB");

this.clocks;

etudes2.startEtudes2Score(initBow, initString, initPerc, initPiano, initGagok, initFlute, initReed, adjString, adjPiano);

Document.allDocuments.at(0).front;
	
MIDIIn.noteOn = {arg src, chan, num, vel; 		
	
	//midiPad
	
	if(src == midiPad, {
if(num == 53, {
		n.sendMsg("/midi", num, vel);
		});
});


	//piano3

	if(src == midiSportInB, 
		{
				//key division - SOPRANO
				if(num >= 60, 
					{SystemClock.sched(0.0,{ arg time;  
					freez1 = time; 
					 nil
					});
					
					
					if((now - freez1) >= 0.1,
					{routine1on.next; 
					
					//soprano noteOn messeges
					~step1 = step1on;
					etudes2.sopranoScore(num, vel);
		
					});
					
				});
				
		
		
				//key division - TENOR
				if(num < 60, 
					{SystemClock.sched(0.0,{ arg time;  
					freez4 = time; 
					 nil
					});
					
					if((now - freez4) >= 0.1,
					{routine4on.next; 
								
					//tenor noteOn messeges
					~step4 = step4on;
					etudes2.tenorScore((num + 12), vel);
						
			});
					};
				);
		
			};
	); //end piano3
	
	
	//piano1
	if(src == traveler, 
		{
		
				//key division - ALTO1
				if(num >= 58, 
					{SystemClock.sched(0.0,{ arg time;  
					freez2 = time; 
					 nil
					});
					
					if((now - freez2) >= 0.1,
					{
					routine2on.next; 
				
					//alto1 noteOn messeges
					~step2 = step2on;
					etudes2.alto1Score(num, vel);
					
					});
					};
				); 
		
				//key division - BASS
				if(num < 58, 
					{SystemClock.sched(0.0,{ arg time;  
					freez5 = time; 
					 nil
					});
					
					if((now - freez5) >= 0.1,
					{
					//justnote5 = (num + 12).midijust(415);
					routine5on.next; 
				
					//bass noteOn messeges
					
					~step5 = step5on;
					etudes2.bassScore((num + 12), vel);
					
					});
					};
				);
	
			};
		); //end piano1
		
		
		//piano2
	if(src == midiSportInA, 
		{SystemClock.sched(0.0,{ arg time;  
					freez3 = time; 
					 nil
					});
					
					if((now - freez3) >= 0.1,
			{
			//justnote3 = (num + 12).midijust(415);
			routine3on.next;
			
			//alto2 noteOn messeges
			~step3 = step3on;
			etudes2.alto2Score((num + 12), vel);
			
			});
			
			};
		); //end piano2
		
 
}; //end noteOn

MIDIIn.noteOff = {arg src, chan, num, vel; 

		//piano3
		
	if(src == midiSportInB, 
		{
		
				//key division - SOPRANO
				if((num >= 60).and(step1off != (step1on+1)), 
					{routine1off.next; 
					
					//soprano noteOff messeges
					~step1 = step1off;
					etudes2.sopranoScore(num, vel);
					
			
					});
				
		
				//key division - TENOR
				if((num < 60).and(step4off != (step4on + 1)), 
					{routine4off.next; 
				
					 //tenor noteOff messeges
					 ~step4 = step4off;
					etudes2.tenorScore((num + 12), vel);
				 	 
					};
				); 
		
			};
		); //end piano3	
		
		//piano1
	if(src == traveler, 
		{
		
				//key division - ALTO1
				if((num >= 58).and(step2off != (step2on+1)), 
					{routine2off.next; 
			
					//alto1 noteOff messeges
					~step2 = step2off;
					etudes2.alto1Score(num, vel);
				
					};
				); 
	
				//key division - BASS
				if((num < 58).and(step5off != (step5on+1)), 
					{routine5off.next; 
		
					 //bass noteOff messeges
					~step5 = step5off;
					etudes2.bassScore((num + 12), vel);
					
					};
				); 
		
			};
		);//end piano 1
		
		
		//piano2
	if((src == midiSportInA).and(step3off != (step3on+1)),
			{routine3off.next;
			
			//alto2 noteOff messeges
			~step3 = step3off;
			etudes2.alto2Score((num + 12), vel);
			
			};
		); //end piano2
		

}; //end noteOff

	
//midi controller
	
MIDIIn.control = { | port, chan, num, val |
	
	if(port == behringer, {
	
//start
	
	if(num == 65,
		{etudes2.startproxys; 'nodeproxys'.postln;}
	);
	if(num == 73,
		{etudes2.load;}
	);


//parameter control
	
	if(num == 1,
		{~alto2.set(\overtone, spec2.map(val/127.0))} //overtones (29) 
	); 
	if(num == 2,
		{~alto2.set(\noise, spec3.map(val/127.0))} //noise (7) 
	); 
	if(num == 3,
		{~alto2.set(\noise2, spec3.map(val/127.0))} //noise2 (25) 
	); 
	if(num == 4,
		{~alto2.set(\sinamp, spec3.map(val/127.0))} //sinamp (63) 
	); 
	if(num == 5,
		{~legato = spec.map(val/127.0)} //legato piano (29) 
	); 
	if(num == 8, {
	s.volume_(spec4.map(val/127.0).round(0.1));
	}); //master volume
	
//volume control
	
	if(num == 81,
		{~sorpano.set(\globamp, ~globamp1 = spec1.map(val/127.0).dbamp);
		~gagokAudio2.set(\globamp, spec1.map(val/127.0).dbamp);} //volume soprano
	); 
	if(num == 82,
		{~alto1.set(\globamp, spec1.map(val/127.0).dbamp);}  //volume alto1
	); 
	if(num == 83,
		{~alto2.set(\globamp, ~globamp3 = spec1.map(val/127.0).dbamp);}  //volume alto2
	); 	
	if(num == 84,
		{~tenor.set(\globamp, ~globamp4 = spec1.map(val/127.0).dbamp);}  //volume tenor
	);
	if(num == 85,
		{~bass.set(\globamp, spec1.map(val/127.0).dbamp);}  //volume bass
	);  
	if(num == 86,
		{~piano.set(\globamp, spec1.map(val/127.0).dbamp);}  //volume bass
	);
	
//computer 2 network

	if((num == 87).or(num == 88).or(num == 7), 
		{n.sendMsg("/midi", num, val);}
	); 
	if(num == 70, 
		{control.control(0, 87, 108);
		control.control(0, 88, 118);
		n.sendMsg("/midi", num, val);}
	);
	if(num == 78, 
		{control.control(0, 87, 95);
		control.control(0, 88, 93);
		n.sendMsg("/midi", num, val);}
	);
	if(num == 71, 
		{control.control(0, 87, 29);
		control.control(0, 88, 64);
		n.sendMsg("/midi", num, val);}
	);
	if(num == 79, 
		{inter = rrand(32, 108); source = rrand(32, 118);
		control.control(0, 87, inter);
		control.control(0, 88, source);
		n.sendMsg("/midi", num, inter);
		n.sendMsg("/midi", num-10, source);
		}
	);
	if(num == 72, 
		{inter2 = rrand(0, 10); source2 = rrand(0, 10);
		control.control(0, 87, inter2);
		control.control(0, 88, source2);
		n.sendMsg("/midi", num, inter2);
		n.sendMsg("/midi", num-10, source2);
		}
	);
	if(num == 80, 
		{inter3 = rrand(32, 29); source3 = rrand(32, 29);
		control.control(0, 87, inter3);
		control.control(0, 88, source3);
		n.sendMsg("/midi", num, inter3);
		n.sendMsg("/midi", num-20, source3);
		}
	);

//free buffers and nodes	
	  
	if(num == 92, 
		{Routine({'finish'.postln; 
			~gagokAudio2.stop(2.0);
			~alto1.stop(2.0);
			~alto2.set(\endgate, 0); 
			3.01.yield; 
			this.etudes2finish;
		}).play; 
		}
	);
	
});
	
};
	
}

etudes3MIDIStart {arg initSines=1.0, initCel=1.4, initSteps=2.4, initHarp=3, initHighFFT=0.65, initLowFFT= 1.0, initMainFFT=1.2, initGlass=1.5, initKnock=1.0, initSlam=1.0, initSines2=1.0, initPiano=1.0;
var inter, source,inter2, source2,inter3, source3;
var spec1, spec2, spec3, spec4;

//specs for midi controller
spec1 = ControlSpec(0.ampdb, 1.ampdb, \db, units: " dB");
spec2 = ControlSpec(0.0, 1.0, 'lin');
spec3 = ControlSpec(0.0001, 1.0, 'exp');
spec4 = ControlSpec(-90, 6, \db, units: " dB");

this.clocks;

etudes3.startEtudes3Score(initSines, initCel, initSteps, initHarp, initHighFFT, initLowFFT, initMainFFT, initGlass, initKnock, initSlam, initSines2, initPiano);
	
this.document(documentPath ++ "/Lyrics/Etudes3.rtf");
	
MIDIIn.noteOn = {arg src, chan, num, vel; 	

	//midiPad
if(src == midiPad, {
		if(num == 37, {
			Routine({
				1.do({var tileBuf = (29..48).choose, snowBuf = (49..68).choose;
				~alto2.spawn([\vol, vel.linlin(0,127,0, 1/3), \rate, 1.0.randDifMul, \soundbuf, tileBuf, \fftbufA, 23, \fftbufB, 24], 0);
				~alto2.spawn([\vol, vel.linlin(0,127,0, 2/3), \rate, 0.8.randDifMul, \soundbuf, tileBuf, \fftbufA, 25, \fftbufB, 26], 1);
				0.01.yield;
				~alto2.spawn([\vol, vel.linlin(0,127,0, 0.15), \rate, 0.65.randDifMul, \soundbuf, snowBuf, \fftbufA, 27, \fftbufB, 28], 0);
				});
			}).play
		});
		if(num == 36, {
			Routine({
				1.do({var tileBuf = (29..48).choose, snowBuf = (69..88).choose;
				~alto2.spawn([\vol, vel.linlin(0,127,0, 1/3), \rate, 0.95.randDifMul, \soundbuf, tileBuf, \fftbufA, 23, \fftbufB, 24], 0);
				~alto2.spawn([\vol,  vel.linlin(0,127,0, 2/3), \rate, 0.75.randDifMul, \soundbuf, tileBuf, \fftbufA, 25, \fftbufB, 26], 1);
				0.01.yield;
				~alto2.spawn([\vol, vel.linlin(0,127,0, 0.15), \rate, 0.7.randDifMul, \soundbuf, snowBuf, \fftbufA, 27, \fftbufB, 28], 0);
				});
			}).play
		});
		if(num == 53, {
			n.sendMsg("/midi", num, vel);
		});

	});
	
	
	//piano2

	if(src == midiSportInA, 
		{
				//key division - SOPRANO
				if(num >= 60, 
					{SystemClock.sched(0.0,{ arg time;  
					freez1 = time; 
					 nil
					});
					
					
					if((now - freez1) >= 0.1,
					{routine1on.next; 
					
					//soprano noteOn messeges
					~step1 = step1on;
					etudes3.sopranoScore(num, vel);
		
					});
					
				});
				
		
		
				//key division - TENOR
				if(num < 60, 
					{SystemClock.sched(0.0,{ arg time;  
					freez4 = time; 
					 nil
					});
					
					if((now - freez4) >= 0.1,
					{routine4on.next; 
								
					//tenor noteOn messeges
					~step4 = step4on;
					etudes3.tenorScore((num + 12), vel);
						
			});
					};
				);
		
			};
	); //end piano2
	
	
	//piano3
	if(src == midiSportInB, 
		{
		
				//key division - ALTO1
				if(num >= 58, 
					{SystemClock.sched(0.0,{ arg time;  
					freez2 = time; 
					 nil
					});
					
					if((now - freez2) >= 0.1,
					{
					routine2on.next; 
				
					//alto1 noteOn messeges
					~step2 = step2on;
					etudes3.alto1Score(num, vel);
					
					});
					};
				); 
		
				//key division - BASS
				if(num < 58, 
					{SystemClock.sched(0.0,{ arg time;  
					freez5 = time; 
					 nil
					});
					
					if((now - freez5) >= 0.1,
					{
					//justnote5 = (num + 12).midijust(415);
					routine5on.next; 
				
					//bass noteOn messeges
					
					~step5 = step5on;
					etudes3.bassScore((num + 12), vel);
					
					});
					};
				);
	
			};
		); //end piano3
		
		
		//piano1
	if(src == traveler, 
		{SystemClock.sched(0.0,{ arg time;  
					freez3 = time; 
					 nil
					});
					
					if((now - freez3) >= 0.1,
			{
			//justnote3 = (num + 12).midijust(415);
			routine3on.next;
			
			//alto2 noteOn messeges
			~step3 = step3on;
			etudes3.alto2Score((num + 12), vel);
			
			});
			
			};
		); //end piano1
		
 
}; //end noteOn

MIDIIn.noteOff = {arg src, chan, num, vel; 

		//piano2
		
	if(src == midiSportInA, 
		{
		
				//key division - SOPRANO
				if((num >= 60).and(step1off != (step1on+1)), 
					{routine1off.next; 
					
					//soprano noteOff messeges
					~step1 = step1off;
					etudes3.sopranoScore(num, vel);
					
			
					});
				
		
				//key division - TENOR
				if((num < 60).and(step4off != (step4on + 1)), 
					{routine4off.next; 
				
					 //tenor noteOff messeges
					 ~step4 = step4off;
					etudes3.tenorScore((num + 12), vel);
				 	 
					};
				); 
		
			};
		); //end piano2	
		
		//piano3
	if(src == midiSportInB, 
		{
		
				//key division - ALTO1
				if((num >= 58).and(step2off != (step2on+1)), 
					{routine2off.next; 
			
					//alto1 noteOff messeges
					~step2 = step2off;
					etudes3.alto1Score(num, vel);
				
					};
				); 
	
				//key division - BASS
				if((num < 58).and(step5off != (step5on+1)), 
					{routine5off.next; 
		
					 //bass noteOff messeges
					~step5 = step5off;
					etudes3.bassScore((num + 12), vel);
					
					};
				); 
		
			};
		);//end piano 3
		
		
		//piano1
	if((src == traveler).and(step3off != (step3on+1)),
			{routine3off.next;
			
			//alto2 noteOff messeges
			~step3 = step3off;
			etudes3.alto2Score((num + 12), vel);
			
			};
		); //end piano1
		

}; //end noteOff

//midi controller
	
MIDIIn.control = { | port, chan, num, val |
	
	if(port == behringer, {
	
//start
	
	if(num == 65,
		{etudes3.startproxys; 'nodeproxys'.postln;}
	);
	if(num == 73,
		{etudes3.load;}
	);


//parameter control
	
	if(num == 1,
		{~sorpano.set(\pianoVol, spec2.map(val/127.0))} //pianoVol (63) 
	); 
	if(num == 2,
		{~sorpano.set(\thresh, spec3.map(val/127.0))} //celesta thresh (63) 
	); 
	if(num == 8, {
	s.volume_(spec4.map(val/127.0).round(0.1));
	}); //master volume
	
	
	
//volume control
	
	if(num == 81,
		{~sorpano.set(\globamp, ~globamp1 = spec1.map(val/127.0).dbamp);
		~fft1.set(\globamp, spec1.map(val/127.0).dbamp)} //volume soprano
	); 
	if(num == 82,
		{~alto1.set(\globamp,  ~globamp2 = spec1.map(val/127.0).dbamp);
		~fft2.set(\globamp, spec1.map(val/127.0).dbamp)}  //volume alto1
	); 
	if(num == 83,
		{~alto2.set(\globamp, spec1.map(val/127.0).dbamp);
		~fft3.set(\globamp, spec1.map(val/127.0).dbamp)}  //volume alto2
	); 	
	if(num == 84,
		{~tenor.set(\globamp, spec1.map(val/127.0).dbamp);
		~fft4.set(\globamp, spec1.map(val/127.0).dbamp)}  //volume tenor
	);
	if(num == 85,
		{~fft5.set(\globamp, spec1.map(val/127.0).dbamp)}  //volume bass
	);  
	if(num == 86,
		{~fft6.set(\globamp, ~globamp6 = spec1.map(val/127.0).dbamp)}  //volume piano
	);
	
//computer 2 network

	if((num == 87).or(num == 88).or(num == 7), 
		{n.sendMsg("/midi", num, val);}
	); 
	if(num == 70, 
		{control.control(0, 87, 108);
		control.control(0, 88, 118);
		n.sendMsg("/midi", num, val);}
	);
	if(num == 78, 
		{control.control(0, 87, 95);
		control.control(0, 88, 93);
		n.sendMsg("/midi", num, val);}
	);
	if(num == 71, 
		{control.control(0, 87, 29);
		control.control(0, 88, 64);
		n.sendMsg("/midi", num, val);}
	);
	if(num == 79, 
		{inter = rrand(32, 108); source = rrand(32, 118);
		control.control(0, 87, inter);
		control.control(0, 88, source);
		n.sendMsg("/midi", num, inter);
		n.sendMsg("/midi", num-10, source);
		}
	);
	if(num == 72, 
		{inter2 = rrand(0, 10); source2 = rrand(0, 10);
		control.control(0, 87, inter2);
		control.control(0, 88, source2);
		n.sendMsg("/midi", num, inter2);
		n.sendMsg("/midi", num-10, source2);
		}
	);
	if(num == 80, 
		{inter3 = rrand(32, 29); source3 = rrand(32, 29);
		control.control(0, 87, inter3);
		control.control(0, 88, source3);
		n.sendMsg("/midi", num, inter3);
		n.sendMsg("/midi", num-20, source3);
		}
	);

//free buffers and nodes	
	  
	if(num == 92, 
		{this.etudes3finish}
	);
	
});
	
};

}

etudes1rehearsalA {
		etudes1.rehearsalA;
		this.stepstart;
}

etudes1rehearsalB {
		etudes1.rehearsalB;
		this.stepstart;
}

etudes1rehearsalC {
		etudes1.rehearsalC;
		this.stepstart;
}

etudes1rehearsalD {
		etudes1.rehearsalD;
		this.stepstart;
}

etudes1rehearsalF {
		etudes1.rehearsalF;
		this.stepstart;
}

etudes1rehearsalH {
		etudes1.rehearsalH;
		this.stepstart;
}

etudes2rehearsalA {
		etudes2.rehearsalA;
		this.stepstart;
}

etudes2rehearsalB {
		etudes2.rehearsalB;
		this.stepstart;
}

etudes2rehearsalC {
		etudes2.rehearsalC;
		this.stepstart;
}

etudes2rehearsalD {
		etudes2.rehearsalD;
		this.stepstart;
}

etudes2rehearsalE {
		etudes2.rehearsalE;
		this.stepstart;
}

etudes2rehearsalF {
		etudes2.rehearsalF;
		this.stepstart;
}

etudes3rehearsalA {
		etudes3.rehearsalA;
		this.stepstart;
}

etudes3rehearsalB {
		etudes3.rehearsalB;
		this.stepstart;
}

etudes3rehearsalC {
		etudes3.rehearsalC;
		this.stepstart;
}

etudes3rehearsalD {
		etudes3.rehearsalD;
		this.stepstart;
}

etudes3rehearsalFinal {
		etudes3.rehearsalFinal;
		this.stepstart;
}

}