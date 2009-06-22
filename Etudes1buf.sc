Etudes1buf : Etudes1 {
			
	loadbuf {
Routine({		
		1.do{ var time = 0.25;
s.sendMsg("/g_new", 2000, 1, 1);
s.sendMsg("/g_new", 2001, 1, 1);
s.sendMsg("/g_new", 2002, 1, 1);
s.sendMsg("/g_new", 2003, 1, 1);
s.sendMsg("/g_new", 2004, 1, 1);
s.sendMsg("/g_new", 2005, 1, 1);
s.sendMsg("/s_new", "playthatbuf", 1002, 1, 2000, \out, 16);
s.sendMsg("/s_new", "playthatbuf", 1003, 1, 2000, \out, 18);
s.sendMsg("/s_new", "playthatbuf", 1004, 1, 2000, \out, 20);
s.sendMsg("/s_new", "playthis", 1005, 1, 2001, \out, 22);
s.sendMsg("/s_new", "playthis", 1006, 1, 2001, \out, 24);
s.sendMsg("/s_new", "playthis", 1007, 1, 2001, \out, 26);
s.sendMsg("/s_new", "playthis", 1008, 1, 2001, \out, 28);
'synths'.postln;
		 time.yield;	
~fourier = MPFourierBPF(24,20,22);
'fourier_1'.postln;
		time.yield;
~fourier.startsynth(24, 20, 22);
'fourier_2'.postln;
		time.yield;
~fourier2 = MPFourierBPF2(40,36,38);
'fourier2_1'.postln;
		time.yield;
~fourier2.startsynth(40, 36, 38);
'fourier2_2'.postln;
		time.yield;
~fourier.time = 2.0;
'fourier_4'.postln;
		time.yield;
~fourier2.time = 2.0;
'fourier2_4'.postln;
		time.yield;
s.sendMsg("/s_new", "transpose", 1012, 1, 2004, \in, 26, \out, 32, \gates, 0);
s.sendMsg("/s_new", "transpose", 1013, 1, 2004, \in, 28, \out, 34, \gates, 0);
s.sendMsg(\b_alloc, 100, 2048);
~fourier.getfreq;
~fourier.threshold = 1.4;
		'READY TO START'.postln;

}; 
		
		}).play;
}


	midicontrolbuf {arg ipAddress = "169.254.80.149";
n = NetAddr(ipAddress, 57120); 

MIDIIn.noteOn = { arg src, chan, num, vel; 	

	if(src == 112951606, 
		{if(num == 37, 
			{if((~step5 >= 173).and(~step5 < 400), 
			{ ~bombocount.next; 
			~bass.stop; 
			~bass.start;}
		);
			if((~step5 >= 173).and(~step5 < 303), 
				{s.sendMsg("/s_new", "percussion", ~bombonode, 0, 2004, \out, 4, \lengh, 1.0, \bufnum, 15, \amp, vel/127, \globamp, ~globamp5);}
			);}
		);}
	);
 
};
	
MIDIIn.control = { | port, chan, num, val |
	var spec1, spec,spec2,spec3,spec4,spec5,spec6, gatespec, ampspec;

	gatespec = ControlSpec(1.2, 10, 'lin');
	spec = ControlSpec(0, 10, 'lin');
	spec1 = ControlSpec(0.001, 10, \exponential);
	spec2 = ControlSpec(0.004, 1, 'lin');
	spec3 = ControlSpec(1023, 1010, 'lin');
	spec4 = ControlSpec(1200, 500, 'lin');
	spec5 = ControlSpec(1, 0, 'lin');
	spec6 = ControlSpec(0, 15, 'lin');
	ampspec = ControlSpec(0.ampdb, 1.ampdb, \db, units: " dB");
	
//parameter control
	
	if(num == 1,
		{s.sendMsg("/n_set", 1017, "specgate", gatespec.map(val/127.0));}
	); //gaterev (44)
	if (num == 2,
		{s.sendMsg("/n_set", 1025, "specgate", spec.map(val/127.0));}
	); //pianogatebuf (79 al principio, por ahi de 68 cuando van los solos...)
	if(num == 3,
		{s.sendMsg("/n_set", 2005, "white", spec2.map((val+1)/127.0), "bins", spec3.map((val+1)/127.0), "amp", spec4.map((val+1)/127.0) );}
	); //white noise (0)
	if(num == 4,
		{s.sendMsg("/n_set", 1002, "specgate", spec6.map(val/127.0), "vol", spec5.map(val/127.0));}
	); // pianogatebuf as bass	(51)
	if(num == 5,
		{s.sendMsg("/n_set", 1018, "specgate", spec.map(val/127.0));}
	); //gateperc (44)
	if(num == 6,
		{s.sendMsg("/n_set", 2001, "amp", spec.map(val/127.0).postln);}
	); //microphone (13)
	if(num == 7,
		{s.sendMsg("/n_set", 2000, "amp", spec.map(val/127.0).postln);}
	); //pianoruido (26)
	
//start
	if(num == 65,
		{~etudes1.loadbuf;}
	);
	if(num == 66,
		{s.sendMsg(\n_set, 1014, \gates, 0); s.sendMsg(\n_set, 1015, \gates, 0);}
	);
	
//end
		
	if(num == 72, 
		{s.sendMsg("n_set", 1031, \gates, 0);
		'no gates'.postln}
	);	
	
	if(num == 80, 
		{Routine({var counter; 
			counter = 0; 
			'finish'.postln; 
			s.sendMsg("n_set", 2000, \gates, 0); 
			s.sendMsg("n_set", 2001, \gates, 0); 
			s.sendMsg("n_set", 2002, \gates, 0); 
			s.sendMsg("n_set", 2003, \gates, 0); 
			s.sendMsg("n_set", 2004, \gates, 0); 
			3.01.yield; 
			s.freeAll; 
			150.do{ counter = counter + 1; s.sendMsg("b_free", counter); 0.01.yield;}; 
			0.01. yield; 'done'.postln; 
			s.queryAllNodes;
		}).play; //add this line after s.freeAll for performance: 100.do{ counter = counter + 1; s.sendMsg("b_free", counter); 0.01.yield;};
		}
	);
	
//volume control
	
	if(num == 81, 
		{~globamp1 = (ampspec.map(val/127.0).dbamp); 
			if((~step >= 1).and(~step < 51), 
				{s.sendMsg(\n_set, 1014, \globamp, ~globamp1);} //vocoder - por ahora
			);
			if((~step >= 51).and(~step <= 64),
				{s.sendMsg(\n_set, 1017, \globamp, ~globamp1);} //gatereverb
			);
			if((~step >= 65).and(~step <= 100),
				{s.sendMsg(\n_set, 1024, \globamp, ~globamp1);} //bandpass
			);
			if((~step >= 102).and(~step < 109),
				{s.sendMsg(\n_set, 1004, \globamp, ~globamp1); //bowedhigh
				s.sendMsg(\n_set, 1005, \globamp, ~globamp1);
				s.sendMsg(\n_set, 1006, \globamp, ~globamp1);}
			);
			if((~step >= 109).and(~step < 123),
				{s.sendMsg(\n_set, 1024, \globamp, ~globamp1);} //bandpass
			);
			if((~step >= 123).and(~step <= 132),
				{s.sendMsg(\n_set, 1017, \globamp, ~globamp1);} //gatereberb
			);
			if((~step >= 133).and(~step < 145),
				{s.sendMsg(\n_set, 1004, \globamp, ~globamp1); //bowedhigh
				s.sendMsg(\n_set, 1005, \globamp, ~globamp1);}
			);
			if((~step >= 145).and(~step < 153),
				{s.sendMsg(\n_set, 3003, \globamp, ~globamp1);} //pianorch
			);
			if((~step >= 153).and(~step < 167),
				{s.sendMsg(\n_set, 1004, \globamp, ~globamp1);} //bowedhigh
			);
			if((~step >= 158).and(~step < 167),
				{s.sendMsg(\n_set, 1006, \globamp, ~globamp1);} //bowedhigh
			);
			if((~step >= 166).and(~step <= 167),
				{s.sendMsg(\n_set, 1006, \globamp, ~globamp1);} //bowedhigh
			);
			if((~step >= 167).and(~step < 199),
				{s.sendMsg(\n_set, 1024, \globamp, ~globamp1);} //bandpass
			);
			if((~step >= 199).and(~step <= 600),
				{s.sendMsg(\n_set, 1003, \globamp, ~globamp1);} //bowedhigh
			);
			if((~step >= 205).and(~step <= 600),
				{s.sendMsg(\n_set, 1004, \globamp, ~globamp1);} //bowedhigh
			);
			if((~step >= 211).and(~step <= 600),
				{s.sendMsg(\n_set, 1005, \globamp, ~globamp1);} //bowedhigh
			);
			if((~step >= 225).and(~step <= 600),
				{s.sendMsg(\n_set, 1006, \globamp, ~globamp1);} //bowedhigh
			);
				
		};
	);
	
	if(num == 82, 
		{~globamp2 = (ampspec.map(val/127.0).dbamp); 
			if((~step2 >= 1).and(~step2 <= 68),
				{s.sendMsg(\n_set, 1015, \globamp, ~globamp2);}
			);
			if((~step2 >= 69).and(~step2 <= 130),
				{s.sendMsg("n_set", 1018, \globamp, ~globamp2);}
			);
			if((~step2 >= 131).and(~step2 <= 159),
				{s.sendMsg("n_set", 1028, \globamp, ~globamp2);}
			);
			if((~step2 >= 147).and(~step2 <= 162),
				{s.sendMsg("n_set", 1029, \globamp, ~globamp2);}
			);
			if((~step2 >= 163).and(~step2 < 243),
				{s.sendMsg("n_set", 1017, \globamp, ~globamp2);}
			);
			if((~step2 >= 243).and(~step2 <= 368),
				{s.sendMsg("n_set", 1030, \globamp, ~globamp2);}
			);
		}
	);
	
	if(num == 83, 
		{~globamp3 = (ampspec.map(val/127.0).dbamp);
			if((~step3 >= 51).and(~step3 <= 122),
				{s.sendMsg(\n_set, 1015, \globamp, ~globamp3*2);}
			);
			if((~step3 >= 131).and(~step3 <= 510),
				{s.sendMsg(\n_set, 1031, \globamp, ~globamp3);}
			);
		
		
		}
	);
	
	if(num == 84, 
		{~globamp4 = (ampspec.map(val/127.0).dbamp)*1.5;
			if((~step4 >= 61).and(~step4 <= 112),
				{s.sendMsg(\n_set, 1014, \globamp, ~globamp4*2);}
			);
			if((~step4 >= 113).and(~step4 <= 336),
				{s.sendMsg(\n_set, 2002, \globamp, ~globamp4);}
			);
		}
	);
	
	if(num == 85, 
		{~globamp5 = (ampspec.map(val/127.0).dbamp);
		
			if((~step5 >= 1).and(~step5 < 79), 
				{s.sendMsg(\n_set, 1011, \globamp, ~globamp5);}
			);
			if((~step5 >= 93).and(~step5 < 101), 
				{s.sendMsg(\n_set, 1024, \globamp, ~globamp5);}
			);
			if((~step5 >= 115).and(~step5 < 125), 
				{s.sendMsg(\n_set, 1024, \globamp, ~globamp5);}
			);
			if((~step5 >= 173).and(~step5 <= 175), 
				{s.sendMsg(\n_set, 1010, \globamp, ~globamp5);
				s.sendMsg(\n_set, 1011, \globamp, ~globamp5);}
			);
		}
	);
	
	if(num == 86, 
		{~globamp6 = (ampspec.map(val/127.0).dbamp);}
	);
	
//computer 2 network

	if((num == 87).or(num == 88).or(num == 8), 
		{n.sendMsg("/midi", num, val);}
	); 
		
};

~control = MIDIOut(0, MIDIClient.destinations.at(1).uid);

}

//rehearsal marks
principiobuf {
	~step = 0;
	~step2 = 0;
	~step3 = 0;
	~step4 = 0;
	~step5 = 0;
	
	~control.control(0, 1, 44); //gaterev
	~control.control(0, 2, 79); //pianogatebuf
	~control.control(0, 3, 0); //piano noise
	~control.control(0, 4, 51); //pianogatebuf as bass
	~control.control(0, 5, 44); //gateperc
	~control.control(0, 7, 13); //vol mic
	~control.control(0, 8, 26); //vol pianoruido
	//knobs
	~control.control(0, 81, 63); //chan1 (soprano)
	~control.control(0, 82, 63); //chan2 (alto1)
	~control.control(0, 83, 63); //chan3 (alto2)
	~control.control(0, 84, 63); //chan4 (tenor)
	~control.control(0, 85, 63); //chan5 (bass)
	~control.control(0, 86, 63); //chan6 (piano)
	//sliders

	
s.sendMsg("/s_new", "vocoderenv", 1014, 1, 2005, \inbus, 16, \vocbus, 32, \out, 0, \amp, 150, \qrq, 0.001, \pitch, 42.663256630967.postln, \rqs, ~fourier.rq2, \centerfreq, ~fourier.cutfreq, \white, 0.04, \bins, 1020, \gates, 0);
s.sendMsg("/s_new", "gating2buf", 1017, 1, 2003, \out, 0, \start, rrand(0,6060560), \bufnum, 5, \soundbuf, 3, \oct, 10, \gates, 0); //for soprano
s.sendMsg("/s_new", "vocoderenv", 1015, 1, 2005, \inbus, 18, \vocbus, 34, \out, 1, \amp, 150, \qrq, 0.005, \pitch, 41.663256630967.postln, \rqs, ~fourier.rq2, \centerfreq, ~fourier.cutfreq, \white, 0.04, \bins, 1020, \window, 3.0, \gates, 0); 
s.sendMsg("/s_new", "gating", 1018, 1, 2003, \out, 1, \bufnum, 7, \soundbuf, 8, \gates, 0, \start, rrand(0, 6399418), \oct, 0.5); //for alto1 (with 1017 too)
}

rehearsalAbuf {
	~step = 32;
	~step2 = 32;
	~step3 = 18;
	~step4 = 28;//not yet implemented
	~step5 = 18; //not yet implemented
	
	~control.control(0, 1, 44); //gaterev
	~control.control(0, 2, 79); //pianogatebuf
	~control.control(0, 3, 0); //piano noise
	~control.control(0, 4, 51); //pianogatebuf as bass
	~control.control(0, 5, 44); //gateperc
	~control.control(0, 7, 13); //vol mic
	~control.control(0, 8, 26); //vol pianoruido
	//knobs
	~control.control(0, 81, 63); //chan1 (soprano)
	~control.control(0, 82, 63); //chan2 (alto1)
	~control.control(0, 83, 63); //chan3 (alto2)
	~control.control(0, 84, 63); //chan4 (tenor)
	~control.control(0, 85, 63); //chan5 (bass)
	~control.control(0, 86, 63); //chan6 (piano)
	//sliders

	
	s.sendMsg("/s_new", "vocoderenv", 1014, 1, 2005, \inbus, 16, \vocbus, 32, \out, 0, \amp, 150, \qrq, 0.001, \pitch, 42.663256630967.postln, \rqs, ~fourier.rq2, \centerfreq, ~fourier.cutfreq, \white, 0.04, \bins, 1020, \gates, 0); //for performance
	s.sendMsg("/s_new", "gating2buf", 1017, 1, 2003, \out, 0, \start, rrand(0,6060560), \bufnum, 5, \soundbuf, 3, \gates, 0, \oct, 10); //for soprano
		s.sendMsg("/s_new", "vocoderenv", 1015, 1, 2005, \inbus, 18, \vocbus, 34, \out, 1, \amp, 150, \qrq, 0.005, \pitch, 41.663256630967.postln, \rqs, ~fourier.rq2, \centerfreq, ~fourier.cutfreq, \white, 0.04, \bins, 1020, \window, 3.0, \gates, 0);  //start vocoder
	s.sendMsg("/s_new", "gating", 1018, 1, 2003, \out, 1, \bufnum, 7, \soundbuf, 8, \gates, 0, \start, rrand(0, 6399418), \oct, 0.5);  //for alto1 (with 1017 too)
}

rehearsalBbuf {
	~step = 54;
	~step2 = 68;
	~step3 = 54;
	~step4 = 64; //not yet implemented
	~step5 = 36; //not yet implemented
	
~control.control(0, 1, 44); //gaterev
	~control.control(0, 2, 79); //pianogatebuf
	~control.control(0, 3, 0); //piano noise
	~control.control(0, 4, 51); //pianogatebuf as bass
	~control.control(0, 5, 44); //gateperc
	~control.control(0, 7, 13); //vol mic
	~control.control(0, 8, 26); //vol pianoruido
	//knobs
	~control.control(0, 81, 63); //chan1 (soprano)
	~control.control(0, 82, 63); //chan2 (alto1)
	~control.control(0, 83, 63); //chan3 (alto2)
	~control.control(0, 84, 63); //chan4 (tenor)
	~control.control(0, 85, 63); //chan5 (bass)
	~control.control(0, 86, 63); //chan6 (piano)
	//sliders

	
	s.sendMsg("/s_new", "gating2buf", 1017, 1, 2003, \out, 0, \start, rrand(0,6060560), \bufnum, 5, \soundbuf, 3, \gates, 0, \oct, 10); //for soprano
	s.sendMsg("/s_new", "gating", 1018, 1, 2003, \out, 1, \bufnum, 7, \soundbuf, 8, \gates, 0, \start, rrand(0, 6399418), \oct, 0.5); //for alto1 (with 1017)
	s.sendMsg("/s_new", "vocoderenv", 1015, 1, 2005, \inbus, 18, \vocbus, 34, \out, 2, \amp, 150, \qrq, 0.001, \transp, 10.015082956259*8, \rqs, ~fourier.rq2, \centerfreq, ~fourier.cutfreq, \white, 0.04, \bins, 1020, \window, 3.0, \gates, 0, \globamp, ~globamp3);  //for alto2
	s.sendMsg("/s_new", "vocoderenv", 1014, 1, 2005, \inbus, 16, \vocbus, 32, \out, 3, \amp, 150, \qrq, 0.001, \pitch, 42.663256630967.postln, \rqs, ~fourier.rq2, \centerfreq, ~fourier.cutfreq, \white, 0.04, \bins, 1020, \gates, 0, \globamp, ~globamp4); //for tenor
}

rehearsalCbuf {
	~step = 64;
	~step2 = 78;
	~step3 = 64;
	~step4 = 74; //not yet implemented
	~step5 = 46; //not yet implemented
	
~control.control(0, 1, 44); //gaterev
	~control.control(0, 2, 79); //pianogatebuf
	~control.control(0, 3, 0); //piano noise
	~control.control(0, 4, 51); //pianogatebuf as bass
	~control.control(0, 5, 44); //gateperc
	~control.control(0, 7, 13); //vol mic
	~control.control(0, 8, 26); //vol pianoruido
	//knobs
	~control.control(0, 81, 63); //chan1 (soprano)
	~control.control(0, 82, 63); //chan2 (alto1)
	~control.control(0, 83, 63); //chan3 (alto2)
	~control.control(0, 84, 63); //chan4 (tenor)
	~control.control(0, 85, 63); //chan5 (bass)
	~control.control(0, 86, 63); //chan6 (piano)
	//sliders

	
	s.sendMsg("/s_new", "gating2buf", 1017, 1, 2003, \out, 0, \start, rrand(0,6060560), \bufnum, 5, \soundbuf, 3, \gates, 0, \oct, 10);
//	~fourier2.getfreq; //for soprano
	s.sendMsg("/s_new", "gating", 1018, 1, 2003, \out, 1, \bufnum, 7, \soundbuf, 8, \gates, 0, \start, rrand(0, 6399418), \oct, 0.5);  //for alto1 (with 1017)
	s.sendMsg("/s_new", "vocoderenv", 1015, 1, 2005, \inbus, 18, \vocbus, 34, \out, 2, \amp, 150, \qrq, 0.001, \transp, 10.015082956259*8, \rqs, ~fourier.rq2, \centerfreq, ~fourier.cutfreq, \white, 0.04, \bins, 1020, \window, 3.0, \gates, 0, \globamp, ~globamp3);  //for alto2
	s.sendMsg("/s_new", "vocoderenv", 1014, 1, 2005, \inbus, 16, \vocbus, 32, \out, 0, \amp, 150, \qrq, 0.001, \pitch, 42.663256630967.postln, \rqs, ~fourier.rq2, \centerfreq, ~fourier.cutfreq, \white, 0.04, \bins, 1020, \gates, 0, \globamp, ~globamp4); //for tenor

			}
			
rehearsalDbuf {
	~step = 86;
	~step2 = 116;
	~step3 = 108;
	~step4 = 112; //not yet implemented
	~step5 = 78; //not yet implemented
	
~control.control(0, 1, 44); //gaterev
	~control.control(0, 2, 79); //pianogatebuf
	~control.control(0, 3, 0); //piano noise
	~control.control(0, 4, 51); //pianogatebuf as bass
	~control.control(0, 5, 44); //gateperc
	~control.control(0, 7, 13); //vol mic
	~control.control(0, 8, 26); //vol pianoruido
	//knobs
	~control.control(0, 81, 63); //chan1 (soprano)
	~control.control(0, 82, 63); //chan2 (alto1)
	~control.control(0, 83, 63); //chan3 (alto2)
	~control.control(0, 84, 63); //chan4 (tenor)
	~control.control(0, 85, 63); //chan5 (bass)
	~control.control(0, 86, 63); //chan6 (piano)
	//sliders

	
	~fourier2.getfreq;
	s.sendMsg("/s_new", "playthis", 1019, 1, 2001, \out, 40); 
	s.sendMsg("/s_new", "playthatbuf", 1020, 1, 2000, \out, 36); 
	s.sendMsg("/s_new", "playthis", 1021, 1, 2001, \out, 38); 
	s.sendMsg("/s_new", "transpenv", 1024, 0, 2003, \in, 42, \out, 0, \gates, 0); //for soprano
	s.sendMsg("/s_new", "gating2buf", 1017, 1, 2003, \out, 0, \start, rrand(0,6060560), \bufnum, 5, \soundbuf, 3, \gates, 0, \oct, 10);
	s.sendMsg("/s_new", "gating", 1018, 1, 2003, \out, 1, \bufnum, 7, \soundbuf, 8, \gates, 0, \start, rrand(0, 6399418), \oct, 0.5);  //for alto1 (with 1017)
	s.sendMsg("/s_new", "vocoderenv", 1015, 1, 2005, \inbus, 18, \vocbus, 34, \out, 2, \amp, 150, \qrq, 0.001, \transp, 10.015082956259*8, \rqs, ~fourier.rq2, \centerfreq, ~fourier.cutfreq, \white, 0.04, \bins, 1020, \window, 3.0, \gates, 0, \globamp, ~globamp3);  //for alto2
			}
			
rehearsalEbuf {
	~step = 108;
	~step2 = 138;
	~step3 = 130;
	~step4 = 128; //not yet implemented
	~step5 = 100; //not yet implemented
	
~control.control(0, 1, 44); //gaterev
	~control.control(0, 2, 79); //pianogatebuf
	~control.control(0, 3, 0); //piano noise
	~control.control(0, 4, 51); //pianogatebuf as bass
	~control.control(0, 5, 44); //gateperc
	~control.control(0, 7, 13); //vol mic
	~control.control(0, 8, 26); //vol pianoruido
	//knobs
	~control.control(0, 81, 63); //chan1 (soprano)
	~control.control(0, 82, 63); //chan2 (alto1)
	~control.control(0, 83, 63); //chan3 (alto2)
	~control.control(0, 84, 63); //chan4 (tenor)
	~control.control(0, 85, 63); //chan5 (bass)
	~control.control(0, 86, 63); //chan6 (piano)
	//sliders

	
	s.sendBundle(0.1, ["n_free", 1003], ["n_free", 1004], ["n_free", 1005], ["n_free", 1006]);
	~fourier2.getfreq;
	s.sendMsg("/s_new", "playthis", 1019, 1, 2001, \out, 40); 
	s.sendMsg("/s_new", "playthatbuf", 1020, 1, 2000, \out, 36); 
	s.sendMsg("/s_new", "playthis", 1021, 1, 2001, \out, 38); 
	s.sendMsg("/s_new", "transpenv", 1024, 0, 2003, \in, 42, \out, 0, \gates, 0); //for soprano
	s.sendMsg("/s_new", "gating2buf", 1017, 1, 2003, \out, 0, \start, rrand(0,6060560), \bufnum, 5, \soundbuf, 3, \gates, 0, \oct, 10);
	s.sendMsg("/s_new", "gating", 1018, 1, 2003, \out, 1, \bufnum, 7, \soundbuf, 8, \gates, 0, \start, rrand(0, 6399418), \oct, 0.5);  //for alto1 (with 1017)
	s.sendMsg("s_new", "pianorch", 1028, 0, 2004, \out, 1, \bufnum, 30, \globamp, ~globamp2, \gates, 0);
	s.sendMsg("b_allocRead", 19, documentPath ++ "/samples/chords/chord5do_si_la.aif"); //for alto1
	s.sendBundle(0.1, ["n_free", 1008],["n_free", 1013]);
	s.sendMsg("/b_alloc", 9, 2048, 1); 
	s.sendMsg("/b_alloc", 6, 2048, 1); //for alto2

	}
	
}
