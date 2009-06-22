Etudes1 : EtudesMain {
	var fourier, fourier2;
	
	startEtudes1 {this.init;
s.sendBundle(2.6, [\b_allocRead, 3, documentPath ++ "/samples/source/ruido.aif"], [\b_allocRead, 4, documentPath ++ "/samples/source/Se_la_Mia.aif"], [\b_allocRead, 8, documentPath ++ "/samples/source/chopinoise2.aif"], [\b_allocRead, 30, documentPath ++ "/samples/anthony/Anothonysine.aiff"], [\b_allocRead, 31, documentPath ++ "/samples/celesta/celestahigh.aif"], [\b_allocRead, 32, documentPath ++ "/samples/source/harmonic.aiff"], ["/b_alloc", 7,2048,1], ["/b_alloc", 5,2048,1]);
}
	
//vocoder synth

	vocoder {
var makeVocoderDef2= { |numbands = 20, inChannels = 1,
hiBand = 10000|
SynthDef(\vocoderenv, { | inbus = 6, vocbus = 12,  amp = 1, gates = 1, pitch = 43, out = 0, centerfreq = 1000, rqs = 0.1, fftbuf = 100, white = 0.004, bins = 1023, window = 0.1, transp = 2, qrq = 0.001, atk = 0.476, dec = 0.399, sus = 0.980, rel = 2.33, crv = -4,globamp=0.5, adjVol=2|
var sig, centerFreqs, splitFilt, bandamps, rq, osci, phases;
var sig2, inSplit, lowBand, env, signal, signal2, signal3, signal4, chain;
env = EnvGen.ar(Env.adsr(atk, dec, sus, rel, 0.8, crv), gates);
lowBand = pitch.midicps * 2; 
// analysis phase
sig2 = In.ar(inbus, inChannels).softclip;
sig = In.ar(vocbus, 1)+(PitchShift.ar((sig2*6.5), window, transp, 0, 0.01, mul: white));
chain = FFT(fftbuf, sig);
chain = PV_MinMagN(chain, bins);
sig = IFFT(chain);
rq = Array.geom(numbands, 0.001, (0.005/ 0.001) **
(numbands-1).reciprocal);
centerFreqs = Array.geom(numbands, lowBand, (hiBand / lowBand) **
(numbands-1).reciprocal);
splitFilt = BPF.ar(sig, centerFreqs, rq);
bandamps = Amplitude.kr(splitFilt).lag(0.2);
phases = Array.fill( numbands, {rrand(0,pi)});
osci = Array.fill( numbands, {rrand(1, 0.3)});
// resynthesis phase
inSplit = sig2.asArray.collect({ |channel| BPF.ar(channel,
LFNoise1.kr(4, (1..numbands), centerFreqs), SinOsc.kr(osci,  phases, qrq, rq+qrq), bandamps) });
signal = (Mix.ar(inSplit.flop) * amp)*env;
signal2 = BRF.ar(signal, centerfreq, rqs);
Out.ar(out, ((signal)*globamp*adjVol));
});
};

makeVocoderDef2.value.send(s);

}
	
//envelope generator and initial volumes					
	genenv {

	//envelopes	
	//vocoder
	makenv1 = Array.fill(9, {var env1, env2, env3, env4, arrenv;
env1 = [[rrand(0.45, 0.55), rrand(0.35, 0.45), rrand(0.35, 0.01)].wchoose([1,5,1].normalizeSum), 
rrand(0.398, 0.403), rrand(0.98, 1.05), rrand(1.8,2.6), [-4, -3].wchoose([5,4].normalizeSum)];//normal
env2 = [rrand(1.1, 2.55),rrand(0.398, 0.403), rrand(0.9, 1.0), rrand(3.6,3.8), [4, 3].wchoose([1.5,1].normalizeSum)]; //slow attack
env3 = [rrand(0.01, 0.09), rrand(0.1, 0.01), rrand(0.98, 1.05), rrand(1.18,1.26), [-2, -3].choose]; //fast attack
env4 = [rrand(0.1, 0.4), rrand(0.3, 0.1), rrand(0.95, 1.0), rrand(3.5,3.1), [1, 2].choose];
//medium rise linear
arrenv = [env1,env2, env3, env4].wchoose([7,1,1,0.5].normalizeSum); //weighted choose
});
	//gates
	makenv2 = Array.fill(16, {var env1, env2, env3, env4, arrenv;
env1 = [[rrand(0.045, 0.055), rrand(0.035, 0.045), rrand(0.035, 0.01), rrand(0.02, 0.01)].wchoose([4,4,1,1].normalizeSum), 
rrand(0.0398, 0.01), rrand(0.95, 1.00), rrand(1.8,2.1), [-4, -3].wchoose([5,4].normalizeSum)];//normal
env2 = [rrand(1.1, 2.55),rrand(0.198, 0.1403), rrand(0.95, 1.0), rrand(3.6,4.4), [4, 3].wchoose([1,1].normalizeSum)]; //slow attack
env3 = [rrand(0.1, 0.5), rrand(0.1, 0.3), rrand(0.95, 1.0), rrand(1.8,2.6), [-2, -3].choose]; //fast attack
env4 = [rrand(0.1, 0.4), rrand(0.398, 0.1403), rrand(0.95, 1.0), rrand(1.5,2.1), [-6, -7].choose];
//medium rise cubic
arrenv = [env1,env2, env3, env4].wchoose([7,2,4,5].normalizeSum); //weighted choose	
});
	//bandpass
	makenv3 = Array.fill(11, {var env1, env2, env3, env4, arrenv;
env1 = [[rrand(0.41, 0.53), rrand(0.33, 0.43), rrand(0.1, 0.2)].wchoose([5,4,1].normalizeSum), 
rrand(0.38, 0.43), rrand(0.95, 1.0), rrand(1.7,2.7), [-4, -3].wchoose([4,3].normalizeSum)];//normal
env2 = [rrand(1.9, 2.65), rrand(0.298, 0.303), rrand(0.9, 1.0), rrand(1.63,2.82), [4, 3].wchoose([1,1].normalizeSum)]; //slow attack
env4 = [rrand(0.1, 0.2), rrand(0.2, 0.1), rrand(0.95, 1.2), rrand(1.5, 2.0), [-6, -7].choose];
//medium rise cubic
arrenv = [env1,env2, env4].wchoose([7,2,1].normalizeSum); //weighted choose
});
	//bandinstrument
	makenv4 = Array.fill(8, {var env1, env2, env3, env4, arrenv;
	env1 = [[rrand(0.45, 0.55), rrand(0.35, 0.45), rrand(0.35, 0.01)].wchoose([1,5,1].normalizeSum), 
rrand(0.398, 0.403), rrand(0.98, 1.05), rrand(4.8,3.6), [-4, -3].wchoose([5,4].normalizeSum)];//normal
env2 = [rrand(1.1, 2.55),rrand(0.398, 0.403), rrand(0.9, 1.0), rrand(4.6,3.8), [4, 3].wchoose([1.5,1].normalizeSum)]; //slow attack
env3 = [rrand(0.01, 0.05), rrand(0.1, 0.01), rrand(0.98, 1.05), rrand(6.18,5.26), [-2, -3].choose]; //fast attack
env4 = [rrand(0.1, 0.4), rrand(0.3, 0.1), rrand(0.95, 1.0), rrand(8.5,9.1), [1, 2].choose];
//medium rise linear
arrenv = [env1,env2, env3, env4].wchoose([6,4,2,1].normalizeSum); //weighted choose
});
	
	//vocoder2
	makenv5 = Array.fill(10, {var env1, env2, env3, env4, arrenv; 
env1 = [[rrand(0.45, 0.55), rrand(0.35, 0.45), rrand(0.35, 0.01)].wchoose([1,5,1].normalizeSum), 
rrand(0.398, 0.403), rrand(0.98, 1.05), rrand(1.8,2.6), [-4, -3].wchoose([5,4].normalizeSum)];//normal
env2 = [rrand(1.1, 2.55),rrand(0.398, 0.403), rrand(0.9, 1.0), rrand(3.6,3.8), [4, 3].wchoose([1.1,1].normalizeSum)]; //slow attack
env3 = [rrand(0.3, 0.01), rrand(0.1, 0.01), rrand(0.98, 1.05), rrand(1.18,1.26), [-2, -3].choose]; //fast attack
env4 = [rrand(0.1, 0.4), rrand(0.3, 0.1), rrand(0.95, 1.0), rrand(3.5,3.1), [1, 2].choose];
//medium rise linear
arrenv = [env1,env2, env3, env4].wchoose([5,3,2,1].normalizeSum); //weighted choice
});

	//pianorch
	makenv6 = Array.fill(16, {var env1, env2, env3, env4, arrenv;
	env1 = [[rrand(0.45, 0.55), rrand(0.35, 0.45), rrand(0.35, 0.01)].wchoose([1,5,1].normalizeSum), 
rrand(0.398, 0.403), rrand(0.98, 1.05), rrand(1.8,2.6), [-4, -3].wchoose([5,4].normalizeSum)];//normal
env2 = [rrand(1.1, 2.55),rrand(0.398, 0.403), rrand(0.9, 1.0), rrand(3.6,3.8), [-4, -3].wchoose([1.5,1].normalizeSum)]; //slow attack
env3 = [rrand(0.01, 0.09), rrand(0.1, 0.01), rrand(0.98, 1.05), rrand(1.18,1.26), [-2, -3].choose]; //fast attack
env4 = [rrand(0.1, 0.4), rrand(0.3, 0.1), rrand(0.95, 1.0), rrand(3.5,3.1), [-1, -2].choose];
//medium rise linear
arrenv = [env1,env2, env3, env4].wchoose([5,1,3,0.5].normalizeSum); //weighted choose
});

	
atk = 0;
dec = 1;
sus = 2;
rel = 3;
crv = 4;

}
			
	load {
Routine({		
		1.do{ var time = 0.25;
s.sendMsg("/g_new", 2000, 1, 1);
s.sendMsg("/g_new", 2001, 1, 1);
s.sendMsg("/g_new", 2002, 1, 1);
s.sendMsg("/g_new", 2003, 1, 1);
s.sendMsg("/g_new", 2004, 1, 1);
s.sendMsg("/g_new", 2005, 1, 1);
s.sendMsg("/s_new", "playthat", 1002, 1, 2000, \out, 16);
s.sendMsg("/s_new", "playthat", 1003, 1, 2000, \out, 18);
//s.sendMsg("/s_new", "playthatbuf", 1004, 1, 2000, \out, 20);
s.sendMsg("/s_new", "playthis", 1005, 1, 2001, \out, 22);
s.sendMsg("/s_new", "playthis", 1006, 1, 2001, \out, 24);
s.sendMsg("/s_new", "playthis", 1007, 1, 2001, \out, 26);
s.sendMsg("/s_new", "playthis", 1008, 1, 2001, \out, 28);
'synths'.postln;
		 time.yield;	
//fourier = MPFourierBPF(24,20,22);

fourier = FFTFilter(256, 255, 0, 1, 2); 
		time.yield;
fourier.startEtudeSynth;
fourier.getarrays;
		time.yield;
fourier2 = FFTFilter(256, 255, 10, 11, 12);
		time.yield;
fourier2.startEtudeSynth2;
fourier2.getarrays;
		time.yield;
fourier.ffttime(2.0);
		time.yield;
fourier2.ffttime(2.0);
		time.yield;
s.sendMsg("/s_new", "transpose", 1012, 1, 2004, \in, 26, \out, 32, \gates, 0);
s.sendMsg("/s_new", "transpose", 1013, 1, 2004, \in, 28, \out, 34, \gates, 0);
s.sendMsg(\b_alloc, 100, 2048);
fourier.threshEtudes(2.4);
fourier.getQ;
s.sendMsg("/s_new", "pianoOut", 1811, 1, 2005, \globamp, ~globamp6);
		'READY TO START'.postln;

}; 
		
		}).play;
}
		

	
	buffers {s.sendBundle(2.6,[\b_allocRead, 13, documentPath ++ "/samples/chords/chord1re_fa_do.aif"], [\b_allocRead, 14, documentPath ++ "/samples/chords/chord2do_re.aif"], ["b_allocRead", 130, documentPath ++ "/samples/chords/chord4do_sol_mi_re.aif"], ["b_allocRead", 131, documentPath ++ "/samples/chords/chord6do_sharp.aif"], ["b_allocRead", 132, documentPath ++ "/samples/melodic/pianorch_si_flat.aif"], [\b_allocRead, 133, documentPath ++ "/samples/melodic/pianorch_do.aif"], [\b_allocRead, 134, documentPath ++ "/samples/melodic/pianorch_si_flat2.aif"], [\b_allocRead, 135, documentPath ++ "/samples/melodic/pianorch_la_flat.aif"], [\b_allocRead, 136, documentPath ++ "/samples/melodic/pianorch_la.aif"], [\b_allocRead, 137, documentPath ++ "/samples/chords/pianorch_si_sol_re_la.aif"], [\b_allocRead, 138, documentPath ++ "/samples/chords/pianorch_la_re_si_fa.aif"])
	
	}
	
	closebuffers {
	s.sendBundle(0.1,["b_close", 20], ["b_close", 18], ["b_close", 17], ["b_close", 16]);
	}


	midicontrol {arg ipAddress = "169.254.80.149";
	
var inter, source,inter2, source2,inter3, source3;
var spec1, spec,spec2,spec3,spec4,spec5,spec6,spec7, gatespec, ampspec,ampspec2;
	
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
	
n = NetAddr(ipAddress, 57120); 

MIDIIn.noteOn = { arg src, chan, num, vel; 	

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
 
};
	
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
		{this.load;}
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
			this.finish;
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

vocStart {s.sendMsg("/n_set", 2005, "white", 1, "bins", 1010, "amp", 0.0, "gates", 0);
}


clearproxys { fourier.stoptask;
			fourier2.stoptask;
			fourier.stoptask2;
			fourier2.stoptask2;
			s.freeAll;
			this.closebuffers; 


}

finish {		this.clearproxys;
			s.bufFreeRange(0,150);
			s.queryAllNodes;
			currentEnvironment.clear;
}

	//write Synth Defs 
	*initClass {
		
	SynthDef.writeOnce("playthis", {arg out = 16, amp = 0.6;
var signal = AudioIn.ar(1)*amp;
Out.ar(out, signal*amp);});

	//SynthDef.writeOnce("playthis", {arg out = 16, amp = 0.6;
//var signal = PlayBuf.ar(1, 4, 1, loop: 1)*amp;
//Out.ar(out, signal*amp);});
			
	SynthDef.writeOnce("playthat", {arg inputAmp = 2, out = 18; 
var signal = AudioIn.ar(2)*inputAmp; 
Out.ar(out, signal);
});
	
	
	SynthDef.writeOnce("transpenv", {  
arg velo = 0, midi = 415, amp2 = 1, gates = 1, in = 22, out = 0, atk, dec, sus, rel, crv, globamp, adjVol=2.0;
var signal,signal4,signal5, shift, amp, cero, env;
env = EnvGen.ar(Env.adsr(atk, dec, sus, rel, 0.8, crv), gates);
shift = midi/415; 
signal = PitchShift.ar(In.ar(in,1), 0.1, shift, 0, 0.004);
cero = if(velo == 0, {0;}, {velo+62;});	
amp = cero/127;
signal4 = signal * Lag.kr(amp, 1.6);
signal5 = (signal4*amp2)*env;
	Out.ar(out, signal5*globamp*adjVol);
});


	SynthDef.writeOnce("transpose", {  
arg velo = 0, midi = 440, gates = 0, amp2 = 1, in = 14, out = 12;
var signal,signal4,signal5, shift, amp, env;
env = EnvGen.kr(Env.perc(0.0001, 0.35, 1),gates, doneAction: 0);
shift = midi/415; 
signal = PitchShift.ar(In.ar(in, 1), 0.1, shift, 0, 0.004);
amp = velo/127;
signal4 = signal * (Lag.kr(amp, 1.6)* amp2);
signal5 = signal4;
	Out.ar(out, signal5);
});

	SynthDef.writeOnce("gating", { arg out=0, bufnum=0, justfreq = 830, amp = 1, soundbuf = 1, oct = 2, specgate = 6.23, gates = 0, start = 0.0, atk, dec, sus, rel, crv, globamp, adjVol=1.0;
	var source1, chain, signal, signal2,signal3, signal4, signal5, pitches, inputAmps, threshhold, gate, env, rate, env2;
	rate = Impulse.kr(10);
	source1 = BPF.ar(PlayBuf.ar(1, soundbuf, 1, 1.0, start, 1),justfreq*7, 2);
	chain = FFT(bufnum, source1);
	chain = PV_MagAbove(chain, specgate); 
	signal = 0.5 * IFFT(chain);
	pitches = Pitch.kr(signal, ampThreshold: 0, median: 7);
inputAmps = Amplitude.kr(signal);
threshhold = 0.0001;
gate = Lag.kr(inputAmps > threshhold, 0.01);
	env = EnvGen.kr(Env.perc(TRand.kr(0.01, 0.05, rate), TRand.kr(0.25,0.45, rate), 0.5, -5), gate, doneAction: 0);
	env2 = EnvGen.ar(Env.adsr(atk, dec, sus, rel, 0.8, crv), gates);
	signal2 = BPF.ar((PlayBuf.ar(1, soundbuf, pitches/(415/oct), 1.0, start, 1)*2), justfreq*7, TRand.kr(2,0.01, rate));
	signal3 = (Mix.ar(signal2)).softclip*env;	
	signal4 = Mix.ar(signal3)*gate;
	signal5 = signal4*env2;
	Out.ar(out, (signal5*Lag.kr(amp, 1.6))*globamp*adjVol);
});


	SynthDef.writeOnce("gating2", { arg out=0, bufnum=0, justfreq = 830, wet = 0.7, oct = 2, gates = 0, specgate = 3.5, inputAmp = 1.0, amp = 1.0, start = 0.0, atk, dec, sus, rel, crv, globamp, adjVol=2;
	var source1, chain, signal, env2, y;
	source1 = BPF.ar((AudioIn.ar(2)*inputAmp),justfreq*7, 2);
	chain = FFT(bufnum, source1);
	chain = PV_MagAbove(chain, specgate); 
	signal = IFFT(chain);
	env2 = EnvGen.ar(Env.adsr(atk, dec, sus, rel, 1.0, crv), gates);
	Mix.fill(5,{ y = AllpassN.ar(signal, 0.05,0.05.rand, 1)});
	Out.ar(out, (((y*wet)*env2)*Lag.kr(amp, 1.6))*globamp*adjVol);
});


	SynthDef.writeOnce("pianogate",{arg out=0, bufnum=0, justfreq = 830, oct = 2, gates = 0, specgate = 5.875, start = 0.0, inputAmp = 1.0;
var inputAmps, threshhold, gate, signal, rate;
var source1, chain, signal2, env2, v, w;
	source1 = BPF.ar((AudioIn.ar(2)*inputAmp),(justfreq*7)*oct, 2);
	chain = FFT(bufnum, source1);
	chain = PV_MagAbove(chain, specgate); 
	signal = 0.5 * IFFT(chain);
	env2 = EnvGen.ar(Env.adsr(0.4, 0.4, 1, 2.5, 0.8, -4), gates);
	signal2 = (signal * env2)*12;
rate = Impulse.kr(10);
inputAmps = Amplitude.kr(signal2);
# v, w = Pitch.kr(signal, ampThreshold: 0.001, median: 7); 
SendTrig.kr(rate, 0, v.cpsmidi.round(1));
SendTrig.kr(rate, 1, inputAmps);
});


	SynthDef.writeOnce("pianorch", {arg out = 0, amp = 1, gates = 1, bufnum = 16, start = 0.0, rel = 2.5, crv = -4, globamp, adjVol=1.0;
var in, signal, signal2, env;
env =  EnvGen.ar(Env.asr(0.01, 1, rel, 1.0, crv), gates, doneAction: 2); 
in = PlayBuf.ar(1,bufnum, 1.0,1.0, start, loop: 0);
signal = in * env;
signal2 = signal * amp;
Out.ar(out, (signal2*globamp*adjVol));
});	


	SynthDef.writeOnce("gatingmic", { arg out=0, bufnum=0, justfreq = 830, amp = 1, soundbuf = 1, oct = 2, specgate = 6.23, gates = 0, detectbuf = 9, thresh = 0.1, start = 0.0, adjVol = 1.0, atk, dec, sus, rel, crv, globamp;
	var source1, chain, signal, signal2,signal3,signal4, pitches, env, rate, env2, detect,source2;
	rate = Impulse.kr(10);
	source1 = BPF.ar(PlayBuf.ar(1, soundbuf, 1, 1.0, start, 1),justfreq*7, 2);
	chain = FFT(bufnum, source1);
	chain = PV_MagAbove(chain, specgate); 
	signal = 0.5 * IFFT(chain);
	pitches = Pitch.kr(signal, ampThreshold: 0, median: 7);
	source2= AudioIn.ar(1)*1.31622776601684; 
	detect= PV_JensenAndersen.ar(FFT(detectbuf,source2), threshold: thresh);
	env = EnvGen.kr(Env.perc(TRand.kr(0.01, 0.05, rate), TRand.kr(0.25,0.45, rate), 0.5, -5), detect, doneAction: 0);
	env2 = EnvGen.ar(Env.adsr(atk, dec, sus, rel, 0.8, crv), gates);
	signal2 = BPF.ar((PlayBuf.ar(1, soundbuf, pitches/(415/oct), 1.0, start, 1)*2), justfreq*7, TRand.kr(3,1.0, rate));
	signal3 = (Mix.ar(signal2*2))*env;	
	signal4 = (signal3 * Lag.kr(amp, 1.6))*0.875;
	Out.ar(out, (signal4*env2)*globamp*adjVol);
});


	SynthDef.writeOnce("bowedpiano", {arg out = 0, amp = 1, bufnum = 16, globamp, adjVol=2.0;
var in, signal, signal2, env, atk, caos, dec;
caos = ((CuspL.ar(2, 1.2, 1.8, 0.3)).abs);
atk = (caos * 0.2) + 1;
dec = (caos * 0.6) +2.8;
env = EnvGen.kr(Env.perc(atk, dec, 1, -5), 1, doneAction: 2);
in = PlayBuf.ar(1,bufnum, BufRateScale.kr(bufnum), loop: 1);  
signal = in * env;
signal2 = signal * amp;
Out.ar(out, signal2*globamp*adjVol);
});	

	SynthDef.writeOnce("celestapure", {|pitch = 440, amplitude = 0.5, out = 0, globamp, inputAmp = 1, adjVol=1.4|
var env, env2, signal, vol, signal2;
env = EnvGen.kr(Env.perc(0.0001, 0.35, 1),1, doneAction: 0);
env2 = EnvGen.kr(Env.linen(0.0001, 1.9, 0.001, 1),1, doneAction: 2);
vol = (pitch/440).sqrt * amplitude;
signal =	Klank.ar(`[[866.700604, 816.110913, 766.459488, 723.324936, 687.941053, 571.619871, 541.390254, 497.778836, 440.777164, 399.120127, 306.889295, 266.425749, 150.200487, 122.724922, 95.7782989, 50.0718679]*(pitch/440), [0.000178, 0.000125, 0.000147, 0.00024, 0.000121, 0.0000963, 0.000153, 0.0000523, 0.015221, 0.0000634, 0.00142, 0.000276, 0.000272, 0.000641, 0.000171, 0.00028344], [0.09, 0.09, 0.11, 0.07, 0.13, 0.27, 0.23, 1.78, 1.84, 1.81, 1.84, 0.22, 1.84, 0.17, 1.46, 1.83]
], Decay.ar((AudioIn.ar(2)*inputAmp)*env));
signal2 = signal * env2 * vol;
Out.ar(out, (signal2 * 0.75)*globamp*adjVol);
});		

	SynthDef.writeOnce("bowedlong", { arg bufnum = 0, out = 1, gates = 1, amp = 1, globamp;
var env, atk, caos, signal, dec;
caos = ((CuspL.ar(2, 1.2, 1.8, 0.3)).abs);
atk = (caos * 0.2) + 1;
dec = (caos * 0.6) +2.8;
env =  EnvGen.ar(Env.adsr(atk, 0.001, 1, dec, 1, -5), gates, doneAction: 0); 
signal = DiskIn.ar(1, bufnum)*env;
Out.ar(out, (signal*amp)*globamp);
});		

	SynthDef.writeOnce("celesta", {arg out = 0, amp = 1, gates = 1, gates2 = 1, bufnum = 14, rate = 1, start = 0.0, globamp, adjVol=1.4;
var in, signal, signal2, env, env2;
env2 = EnvGen.ar(Env.adsr(0.1, 0.4, 1, 0.5, 0.8, -4), gates2);
env =  EnvGen.ar(Env.adsr(0.01, 0.4, 1, 1.9, 0.8, -4), gates, doneAction: 2); 
in = PlayBuf.ar(1,bufnum, rate, 1, start, 1);  
signal = in * env;
signal2 = signal * Lag.kr(amp, 1.6);
Out.ar(out, (signal2 * env2)*globamp*adjVol);
});		

SynthDef.writeOnce("bandinstrument", {arg vol, cut, time = 3, anabuf = 30, midi = 373.5, out = 18, gates = 1, oct = 1, dec, sus, rel, crv, globamp, inputAmp = 1, adjVol = 1.5;
var signal, in, amplitude, signal2, pitch, in2, noisecontrol, noiseiness, ampnoise, env, atk, freq = 4;
atk = (((CuspL.ar(5, 0.9, 1.8, 0.3)).abs)*1.4);
env = EnvGen.ar(Env.adsr(atk, dec, sus, rel, 0.8, crv), gates);
in = PlayBuf.ar(1,anabuf, (midi/373.5), loop: 1);
amplitude = Amplitude.kr(in);
ampnoise = 1;
freq = 4;
noiseiness = SinOsc.kr((amplitude*time), 0, 75, 125);
pitch = Pitch.kr(in, ampThreshold: 0.02, median: 7 )/((66.midicps+18.49543598193)*oct);
in2 = PitchShift.ar((AudioIn.ar(2)*inputAmp), 2+(amplitude*2), 2,0,0.004); 
signal = 
BPF.ar(in2, SinOsc.kr(freq,0, 1.799652, 294.185547)*pitch, noiseiness*cut, LFNoise1.kr(1, 0.05876, 0.037782)*ampnoise) +
BPF.ar(in2, SinOsc.kr(freq,0, 3.1345219999999, 588.586853)*pitch, noiseiness*cut, LFNoise1.kr(1, 0.039791, 0.052352)*ampnoise) +
BPF.ar(in2, SinOsc.kr(freq,0, 10.958313, 878.889099)*pitch, noiseiness*cut, LFNoise1.kr(1, 0.079872, 0.00237)*ampnoise) +
BPF.ar(in2, SinOsc.kr(freq,0, 7.5338139999999, 1176.838623)*pitch, noiseiness*cut, LFNoise1.kr(1, 0.040652, 0.004278)*ampnoise) +
BPF.ar(in2, SinOsc.kr(freq,0, 8.244385, 1471.385498)*pitch, noiseiness*cut, LFNoise1.kr(1, 0.029647, 0.011265)*ampnoise) +
BPF.ar(in2, SinOsc.kr(freq,0, 12.759643, 1763.432129)*pitch, noiseiness*cut, LFNoise1.kr(1,  0.020349, 0.003619)*ampnoise) +
BPF.ar(in2, SinOsc.kr(freq,0, 16.206543, 2055.528564)*pitch, noiseiness*cut, LFNoise1.kr(1,  0.017095, 0.000931)*ampnoise) +
BPF.ar(in2, SinOsc.kr(freq,0, 15.138183, 2352.275391)*pitch, noiseiness*cut, LFNoise1.kr(1, 0.008189, 0.001148)*ampnoise) +
BPF.ar(in2, SinOsc.kr(freq,0, 16.656006, 2648.459717)*pitch, noiseiness*cut, LFNoise1.kr(1, 0.010325, 0.000638)*ampnoise); 
signal2 = signal * 1000 * Lag.kr(vol, 1.6);
Out.ar(out, (signal2 * env)*globamp*adjVol);
});	

	SynthDef.writeOnce(\mcBus,
	{	|in = 18, out = 0|
		var y;
		y = In.ar(in,1);
		Out.ar(out, y);
});

	SynthDef.writeOnce("pianogatemic",{arg out=0, bufnum=0, justfreq = 830, gates = 0, specgate = 5.875, vol = 1, oct = 1, inputAmp = 1.0;
var inputAmps, threshhold, gate, signal, rate;
var source1, chain, signal2, env2, v, w;
	source1 = BPF.ar((AudioIn.ar(2)*inputAmp),(justfreq*7)*oct, 2);
	chain = FFT(bufnum, source1);
	chain = PV_MagAbove(chain, specgate); 
	signal = 0.5 * IFFT(chain);
	env2 = EnvGen.ar(Env.adsr(0.01, 0.01, 1, 0.1, 1, -4), gates);
	signal2 = (((signal*12)+AudioIn.ar(1, vol)) * env2);
rate = Impulse.kr(10);
inputAmps = Amplitude.kr(signal2);
# v, w = Pitch.kr(signal, ampThreshold: 0.001, median: 7); 
SendTrig.kr(rate, 0, v.cpsmidi.round(1));
SendTrig.kr(rate, 1, inputAmps);
});

	SynthDef.writeOnce("percussion", {arg out = 0, amp = 1, bufnum = 16, lengh = 2.5,  globamp, adjVol=1.0;
var in, signal, signal2, env;
env =  EnvGen.kr(Env.new( [0, 1, 1, 0], [0.00001, lengh, 0.1]),1, doneAction: 2);
in = PlayBuf.ar(1,bufnum, BufRateScale.kr(bufnum), loop: 0);  
signal = in * env;
signal2 = signal * amp;
Out.ar(out, signal2*globamp*adjVol);
});

//Etudes1buf Synths:
		
	SynthDef.writeOnce("playthatbuf", {arg amp = 2, out = 18; 
var signal = PlayBuf.ar(1,3,1, loop: 1)*amp; 
Out.ar(out, signal);});

SynthDef.writeOnce("playthisbuf", {arg out = 16, amp = 0.6;
var signal = PlayBuf.ar(1, 4, 1, loop: 1)*amp;
Out.ar(out, signal*amp);});

	SynthDef.writeOnce("gating2buf", { arg out=0, bufnum=0, justfreq = 830, wet = 0.7, soundbuf = 3, oct = 2, gates = 0, specgate = 3.5, amp = 1.0, start = 0.0, atk, dec, sus, rel, crv, globamp, adjVol=2.0;
	var source1, chain, signal, env2, y;
	source1 = BPF.ar(PlayBuf.ar(1, soundbuf, oct, 0.8, start, 1),justfreq*7, 2);
	chain = FFT(bufnum, source1);
	chain = PV_MagAbove(chain, specgate); 
	signal = IFFT(chain);
	env2 = EnvGen.ar(Env.adsr(atk, dec, sus, rel, 1.0, crv), gates);
	Mix.fill(5,{ y = AllpassN.ar(signal, 0.05,0.05.rand, 1)});
	Out.ar(out, (((y*wet)*env2)*Lag.kr(amp, 1.6))*globamp*adjVol);
});

	SynthDef.writeOnce("pianogatebuf",{arg out=0, bufnum=0, justfreq = 830, soundbuf = 3, oct = 2, gates = 0, specgate = 5.875, start = 0.0;
var inputAmps, threshhold, gate, signal, rate;
var source1, chain, signal2, env2, v, w;
	source1 = BPF.ar(PlayBuf.ar(1, soundbuf, 1, 1.0, start, loop: 1),(justfreq*7)*oct, 2);
	chain = FFT(bufnum, source1);
	chain = PV_MagAbove(chain, specgate); 
	signal = 0.5 * IFFT(chain);
	env2 = EnvGen.ar(Env.adsr(0.4, 0.4, 1, 2.5, 0.8, -4), gates);
	signal2 = (signal * env2)*12;
rate = Impulse.kr(10);
inputAmps = Amplitude.kr(signal2);
# v, w = Pitch.kr(signal, ampThreshold: 0.001, median: 7); 
SendTrig.kr(rate, 0, v.cpsmidi.round(1));
SendTrig.kr(rate, 1, inputAmps);
});


	SynthDef.writeOnce("celestapurebuf", {|pitch = 440, amp = 0.5, out = 0, globamp|
var env, env2, signal, vol, signal2;
env = EnvGen.kr(Env.perc(0.0001, 0.35, 1),1, doneAction: 0);
env2 = EnvGen.kr(Env.linen(0.0001, 1.9, 0.001, 1),1, doneAction: 2);
vol = (pitch/440).sqrt * amp;
signal =	Klank.ar(`[[866.700604, 816.110913, 766.459488, 723.324936, 687.941053, 571.619871, 541.390254, 497.778836, 440.777164, 399.120127, 306.889295, 266.425749, 150.200487, 122.724922, 95.7782989, 50.0718679]*(pitch/440), [0.000178, 0.000125, 0.000147, 0.00024, 0.000121, 0.0000963, 0.000153, 0.0000523, 0.015221, 0.0000634, 0.00142, 0.000276, 0.000272, 0.000641, 0.000171, 0.00028344], [0.09, 0.09, 0.11, 0.07, 0.13, 0.27, 0.23, 1.78, 1.84, 1.81, 1.84, 0.22, 1.84, 0.17, 1.46, 1.83]
], Decay.ar(PlayBuf.ar(1,3,1,1, loop: 1)*env));
signal2 = signal * env2 * vol;
Out.ar(out, (signal2 * 0.75)*globamp);
});		
	
SynthDef.writeOnce("bandinstrumentbuf", {arg vol, cut, time = 3, anabuf = 30, soundbuf = 5, midi = 373.5, out = 18, gates = 1, oct = 1, dec, sus, rel, crv, globamp;
var signal, in, amplitude, signal2, pitch, in2, noisecontrol, noiseiness, ampnoise, env, atk, freq = 4;
atk = (((CuspL.ar(5, 0.9, 1.8, 0.3)).abs)*1.4);
env = EnvGen.ar(Env.adsr(atk, dec, sus, rel, 0.8, crv), gates);
in = PlayBuf.ar(1,anabuf, (midi/373.5), loop: 1);
amplitude = Amplitude.kr(in);
ampnoise = 1;
freq = 4;
noiseiness = SinOsc.kr((amplitude*time), 0, 75, 125);
pitch = Pitch.kr(in, ampThreshold: 0.02, median: 7 )/((66.midicps+18.49543598193)*oct);
in2 = PitchShift.ar(PlayBuf.ar(1,soundbuf, 1,1, loop: 1), 2+(amplitude*2), 2,0,0.004); 
signal = 
BPF.ar(in2, SinOsc.kr(freq,0, 1.799652, 294.185547)*pitch, noiseiness*cut, LFNoise1.kr(1, 0.05876, 0.037782)*ampnoise) +
BPF.ar(in2, SinOsc.kr(freq,0, 3.1345219999999, 588.586853)*pitch, noiseiness*cut, LFNoise1.kr(1, 0.039791, 0.052352)*ampnoise) +
BPF.ar(in2, SinOsc.kr(freq,0, 10.958313, 878.889099)*pitch, noiseiness*cut, LFNoise1.kr(1, 0.079872, 0.00237)*ampnoise) +
BPF.ar(in2, SinOsc.kr(freq,0, 7.5338139999999, 1176.838623)*pitch, noiseiness*cut, LFNoise1.kr(1, 0.040652, 0.004278)*ampnoise) +
BPF.ar(in2, SinOsc.kr(freq,0, 8.244385, 1471.385498)*pitch, noiseiness*cut, LFNoise1.kr(1, 0.029647, 0.011265)*ampnoise) +
BPF.ar(in2, SinOsc.kr(freq,0, 12.759643, 1763.432129)*pitch, noiseiness*cut, LFNoise1.kr(1,  0.020349, 0.003619)*ampnoise) +
BPF.ar(in2, SinOsc.kr(freq,0, 16.206543, 2055.528564)*pitch, noiseiness*cut, LFNoise1.kr(1,  0.017095, 0.000931)*ampnoise) +
BPF.ar(in2, SinOsc.kr(freq,0, 15.138183, 2352.275391)*pitch, noiseiness*cut, LFNoise1.kr(1, 0.008189, 0.001148)*ampnoise) +
BPF.ar(in2, SinOsc.kr(freq,0, 16.656006, 2648.459717)*pitch, noiseiness*cut, LFNoise1.kr(1, 0.010325, 0.000638)*ampnoise); 
signal2 = signal * 1000 * Lag.kr(vol, 1.6);
Out.ar(out, (signal2 * env)*globamp);
});	

	SynthDef.writeOnce("pianogatemicbuf",{arg out=0, bufnum=0, justfreq = 830, soundbuf = 3, gates = 0, specgate = 5.875, vol = 1, oct = 1;
var inputAmps, threshhold, gate, signal, rate;
var source1, chain, signal2, env2, v, w;
	source1 = BPF.ar(PlayBuf.ar(1, soundbuf, 1, loop: 1),(justfreq*7)*oct, 2);
	chain = FFT(bufnum, source1);
	chain = PV_MagAbove(chain, specgate); 
	signal = 0.5 * IFFT(chain);
	env2 = EnvGen.ar(Env.adsr(0.01, 0.01, 1, 0.1, 1, -4), gates);
	signal2 = (((signal*12)+AudioIn.ar(1, vol)) * env2);
rate = Impulse.kr(10);
inputAmps = Amplitude.kr(signal2);
# v, w = Pitch.kr(signal, ampThreshold: 0.001, median: 7); 
SendTrig.kr(rate, 0, v.cpsmidi.round(1));
SendTrig.kr(rate, 1, inputAmps);
});

	SynthDef.writeOnce("pianoOut", {arg out=5, globamp=1;
var signal = AudioIn.ar(4)*globamp;
Out.ar(out, signal); });


}

}