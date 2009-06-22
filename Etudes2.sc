Etudes2 : EtudesMain {
	var partials, routineGagok;
	
	startEtudes2 {this.init;
s.sendBundle(1.0,[\b_allocRead, 3, documentPath ++ "/samples/source/ruido2.aif"], [\b_allocRead, 4, documentPath ++ "/samples/source/singlesine.aiff"],  [\b_allocRead, 5, documentPath ++ "/samples/percussion/highpitched5.aif"], [\b_allocRead, 6, documentPath ++ "/samples/source/transpdrum8.aiff"], [\b_allocRead, 7, documentPath ++ "/samples/percussion/japandrum8.aif"], [\b_allocRead, 10, documentPath ++ "/samples/anthony/Anothonysine.aiff"], [\b_allocRead, 20, documentPath ++ "/samples/source/harmonic.aiff"], [\b_allocRead, 276, documentPath ++ "/samples/percussion/highmetal2.aif"],
[\b_allocRead, 277, documentPath ++ "/samples/gagok/gagok3/begining_perc1.aif"],
[\b_allocRead, 278, documentPath ++ "/samples/gagok/gagok3/begining_perc2.aif"],
[\b_allocRead, 279, documentPath ++ "/samples/gagok/gagok3/begining_perc3.aif"],
[\b_allocRead, 280, documentPath ++ "/samples/gagok/gagok3/begining_perc4.aif"],
[\b_allocRead, 281, documentPath ++ "/samples/gagok/gagok3/begining_perc5.aif"],
[\b_allocRead, 282, documentPath ++ "/samples/gagok/gagok3/begining_perc6.aif"],
[\b_allocRead, 283, documentPath ++ "/samples/gagok/gagok3/begining_perc7.aif"],
[\b_allocRead, 284, documentPath ++ "/samples/gagok/gagok3/begining_perc8.aif"]);
}

			
	buffers {var buf, table;
	Routine({1.do({
buf = Buffer.alloc(s, 1024, 1, 1, 21);
0.2.yield;
//amplificatinon function
table = Env.new([-1.0, -1.0,-1.0, 0, 1.0, 1.0], [0, 0.499, 0.5 , 0.501, 1 ], [8, 8, 8, -8, -8]).asSignal(512);
0.1.yield;
//Wavetable
buf.sendCollection(table.asWavetable);
0.1.yield;
s.sendBundle(2.2,[\b_allocRead, 8, documentPath ++ "/samples/gagaku/T23.aif"],[\b_allocRead, 9, documentPath ++ "/samples/gagaku/T27.aif"],[\b_allocRead, 11, documentPath ++ "/samples/gagaku/T31.aif"],[\b_allocRead, 12, documentPath ++ "/samples/gagaku/T33.aif"],[\b_allocRead, 13, documentPath ++ "/samples/gagaku/T37.aif"],[\b_allocRead, 14, documentPath ++ "/samples/gagaku/T41.aif"],[\b_allocRead, 15, documentPath ++ "/samples/gagaku/T51.aif"],[\b_allocRead, 16, documentPath ++ "/samples/gagaku/T55.aif"],  [\b_allocRead, 17, documentPath ++ "/samples/gagaku/T59.aif"],[\b_allocRead, 18, documentPath ++ "/samples/gagaku/T63.aif"], [\b_allocRead, 19, documentPath ++ "/samples/gagaku/T67.aif"],[\b_allocRead, 22, documentPath ++ "/samples/gagaku/T71.aif"], [\b_allocRead, 23, documentPath ++ "/samples/gagaku/T75.aif"], [\b_allocRead, 24, documentPath ++ "/samples/gagaku/T85.aif"], [\b_allocRead, 25, documentPath ++ "/samples/gagaku/T87.aif"], [\b_allocRead, 26, documentPath ++ "/samples/gagaku/T105a.aif"], [\b_alloc, 27, 1024, 1], [\b_allocRead, 28, documentPath ++ "/samples/gagaku/T175_2.wav"], [\b_allocRead, 29, documentPath ++ "/samples/gagaku/T175.wav"], [\b_allocRead, 30, documentPath ++ "/samples/source/voice_inF.aiff"],  [\b_allocRead, 31, documentPath ++ "/samples/gagaku/T179.wav"], [\b_allocRead, 32, documentPath ++ "/samples/gagaku/T183_2.wav"], [\b_allocRead, 33, documentPath ++ "/samples/gagaku/T183.wav"], [\b_allocRead, 34, documentPath ++ "/samples/source/gagokflutes.aiff"], [\b_allocRead, 35, documentPath ++ "/samples/source/boingsine.aiff"]);
});
//'done'.postln;
}).play
}	
		
	buffers2 {
var step = 10, step2 = 50, chan=1, bufDur = 44100 * 6.21;
Routine({13.do({
s.sendBundle(0.1,[\b_allocRead, step2, (documentPath ++ "/samples/gagok/gagok2/" ++ "string" ++ step.asString ++ ".aif")], [\b_allocRead, step2 + 1, (documentPath ++ "/samples/gagok/gagok2/" ++ "string" ++ (step + 1).asString ++ ".aif")], [\b_allocRead, step2 + 2, (documentPath ++ "/samples/gagok/gagok2/" ++ "string" ++ (step + 2).asString ++ ".aif")], [\b_allocRead, step2 + 3, (documentPath ++ "/samples/gagok/gagok2/" ++ "string" ++ (step + 3).asString ++ ".aif")], [\b_allocRead, step2 + 4, (documentPath ++ "/samples/gagok/gagok2/" ++ "string" ++ (step + 4).asString ++ ".aif")], [\b_allocRead, step2 + 5, (documentPath ++ "/samples/gagok/gagok2/" ++ "string" ++ (step + 5).asString ++ ".aif")], [\b_allocRead, step2 + 6, (documentPath ++ "/samples/gagok/gagok2/" ++ "string" ++ (step + 6).asString ++ ".aif")], [\b_allocRead, step2 + 7, (documentPath ++ "/samples/gagok/gagok2/" ++ "string" ++ (step + 7).asString ++ ".aif")], [\b_allocRead, step2 + 8, (documentPath ++ "/samples/gagok/gagok2/" ++ "string" ++ (step + 8).asString ++ ".aif")], [\b_allocRead, step2 + 9, (documentPath ++ "/samples/gagok/gagok2/" ++ "string" ++ (step + 9).asString ++ ".aif")]);
0.2.yield;
step = step + 10;
step2 = step2 + 10;
});
s.sendBundle(0.1,[\b_allocRead, step2, (documentPath ++ "/samples/gagok/gagok2/" ++ "string" ++ step.asString ++ ".aif")], [\b_allocRead, step2 + 1, (documentPath ++ "/samples/gagok/gagok2/" ++ "string" ++ (step + 1).asString ++ ".aif")], ['b_allocRead',263, documentPath ++ "/samples/gagok/gagok3/drum1_10.aif"], ['b_allocRead',264, documentPath ++ "/samples/gagok/gagok3/drum1_11.aif"],
['b_allocRead',265, documentPath ++ "/samples/gagok/gagok3/drum1_12.aif"], ['b_allocRead',266, documentPath ++ "/samples/gagok/gagok3/drum1_13.aif"], ['b_allocRead',267, documentPath ++ "/samples/gagok/gagok3/drum1_14.aif"],['b_allocRead',268, documentPath ++ "/samples/gagok/gagok3/drum1_15.aif"],['b_allocRead',269, documentPath ++ "/samples/gagok/gagok3/drum1_16.aif"],
['b_allocRead',270, documentPath ++ "/samples/gagok/gagok3/drum1_17.aif"],['b_allocRead',271, documentPath ++ "/samples/gagok/gagok3/drum1_18.aif"],['b_allocRead',272, documentPath ++ "/samples/gagok/gagok3/drum1_19.aif"],['b_allocRead',273, documentPath ++ "/samples/gagok/gagok3/drum1_20.aif"],['b_allocRead',274, documentPath ++ "/samples/gagok/gagok3/drum1_21.aif"],['b_allocRead',275, documentPath ++ "/samples/gagok/gagok3/drum1_22.aif"]);
//'done'.postln;
}).play;
}

	buffers3 {var step2 = 200, stepNormal = 10, stepQuiet = 10, stepNoisy = 10, stepMuted = 10, stepWood = 10, count = 0, choose, count3 = 0;
var newGagokTwo;

newGagokTwo =

[ 
[ 0, 21, 137, 200 ],
[ 0, 51, 139, 201 ],
[ 0, 21, 145, 202 ],
[ 0.40135132516144, 81, 145, 203 ],
[ 0.58085615261106, 81, 145, 204 ],
[ 0.79077541926311, 81, 145, 205 ],
[ 0, 21, 155, 206 ],
[ 0, 61, 161, 207 ],
[ 0, 21, 171, 208 ],
[ 0, 21, 175, 209 ],
[ 0, 71, 183, 210 ],
[ 0.89096416016522, 21, 183, 211 ],
[ 0, 81, 185, 212 ],
[ 0.089454232948315, 81, 185, 213 ],
[ 0.15147583445915, 81, 185, 214 ],
[ 0, 21, 187, 215 ],
[ 0.096014210031192, 21, 187, 216 ],
[ 0, 21, 193, 217 ],
[ 0.07275610946463, 21, 193, 218 ],
[ 0, 71, 195, 219 ],
[ 0, 21, 197, 220 ],
[ 1.0907452804165, 51, 197, 221 ],
[ 1.5553109301947, 21, 197, 222 ],
[ 2.6627543340949, 21, 197, 223 ],
[ 3.5543148558131, 51, 197, 224 ],
[ 4.0051641898726, 21, 197, 225 ],
[ 4.0552585603236, 81, 197, 226 ],
[ 4.091040253503, 81, 197, 227 ],
[ 4.1244365004703, 81, 197, 228 ],
[ 4.1584291089907, 81, 197, 229 ],
[ 0, 51, 199, 230 ],
[ 4.6098748046032, 21, 199, 231 ],
[ 0, 21, 201, 232 ],
[ 0, 21, 203, 233 ],
[ 0.30295166891829, 61, 203, 234 ],
[ 0.63631777703902, 21, 203, 235 ],
[ 0.98041839311354, 71, 203, 236 ],
[ 1.696648618253, 51, 203, 237 ],
[ 1.9727640172868, 71, 203, 238 ],
[ 2.497562183917, 21, 203, 239 ],
[ 2.6705070342837, 71, 203, 240 ],
[ 2.9585496643773, 51, 203, 241 ],
[ 2.9973131653216, 81, 203, 242 ],
[ 3.0480038973256, 81, 203, 243 ],
[ 3.0712619978922, 81, 203, 244 ],
[ 3.1034655217536, 81, 203, 245 ],
[ 3.186956139172, 21, 203, 246 ],
[ 3.556103940472, 21, 203, 247 ],
[ 3.5930783567573, 21, 203, 248 ],
[ 4.1465018779309, 51, 203, 249 ],
[ 4.3945882839742, 51, 203, 250 ],
[ 4.4518389930612, 21, 203, 251 ],
[ 4.9199828121573, 21, 203, 252 ],
[ 5.1579310717999, 51, 203, 253 ],
[ 6.2397309289215, 21, 203, 254 ],
[ 7.0883534188245, 51, 203, 255 ],
[ 7.3352471017619, 51, 203, 256 ],
[ 7.3990577879317, 21, 203, 257 ],
[ 7.4258940578162, 21, 203, 258 ],
[ 7.4402067350879, 81, 203, 259 ],
[ 7.4533266892536, 81, 203, 260 ],
[ 7.4741993436082, 81, 203, 261 ],
[ 7.4920901901979, 81, 203, 262 ] ];

Routine({newGagokTwo.flop[1].size.do({
newGagokTwo.flop[2][count];
if(newGagokTwo.flop[1][count] == 21, {

if(((newGagokTwo.flop[2][count] == 145).or(newGagokTwo.flop[2][count] == 187).or(newGagokTwo.flop[2][count] == 197).or(newGagokTwo.flop[2][count] == 199).or(newGagokTwo.flop[2][count] == 201).or(newGagokTwo.flop[2][count] == 203)).and(count3 == 0), {
count3;
s.sendMsg(\b_allocRead, step2, (documentPath ++ "/samples/gagok/gagok3/" ++ "highsound" ++ stepNormal.asString ++ ".aif") );
count3 = count3 + 1;
}, {  

choose = [0, 1].choose;
if(choose == 0, {s.sendMsg(\b_allocRead, step2, (documentPath ++ "/samples/gagok/gagok3/" ++ "drum" ++ stepNormal.asString ++ ".aif"));},
{s.sendMsg(\b_allocRead, step2, (documentPath ++ "/samples/gagok/gagok3/" ++ "highsound" ++ stepNormal.asString ++ ".aif") );});
});
stepNormal = stepNormal + 1;
//normal
});
if(newGagokTwo.flop[1][count] == 51, {
count3 = 0;
s.sendMsg(\b_allocRead, step2, (documentPath ++ "/samples/gagok/gagok3/" ++ "silent" ++ stepQuiet.asString ++ ".aif"));
stepQuiet = stepQuiet + 1;

});
if(newGagokTwo.flop[1][count] == 61, {
count3 = 0; 
s.sendMsg(\b_allocRead, step2, (documentPath ++ "/samples/gagok/gagok3/" ++ "noisy" ++ stepNoisy.asString ++ ".aif"));
stepNoisy = stepNoisy + 1;
});
if(newGagokTwo.flop[1][count] == 71, {
count3 = 0;
s.sendMsg(\b_allocRead, step2, (documentPath ++ "/samples/gagok/gagok3/" ++ "muted" ++ stepMuted.asString ++ ".aif"));
stepMuted = stepMuted + 1;
});
if(newGagokTwo.flop[1][count] == 81, {
count3 = 0;
s.sendMsg(\b_allocRead, step2, (documentPath ++ "/samples/gagok/gagok3/" ++ "wood" ++ stepWood.asString ++ ".aif"));
stepWood = stepWood + 1;
});
count = count + 1;
step2 = step2 + 1;
0.01.yield
});
//'done'.postln;
}).play

}			
		
	startproxys {
~soprano = NodeProxy.audio(s, 1);
~soprano.play(sopranoOut);
~alto1 = NodeProxy.audio(s, 1);
~alto1.play(alto1Out);
~alto2 = NodeProxy.audio(s, 1);
~alto2.play(alto2Out);
~tenor = NodeProxy.audio(s, 1);
~tenor.play(tenorOut);
~bass = NodeProxy.audio(s, 1);
~bass.play(bassOut);
~piano = NodeProxy.audio(s, 1); //for piano
~piano.play(pianoOut); 
~piano.source = {|globamp = 1.0| AudioIn.ar(4)*globamp}; //startpiano
//extra proxys for gagaku


}	

	clearproxys {
	~soprano.clear;
	~alto1.clear;
	~alto2.clear;
	~tenor.clear;
	~bass.clear;	
	~piano.clear;
	~gagokControl.clear;
	~gagokControl2.clear;
	~gagokAudio.clear;
	~gagokAudio2.clear;
	~envelopeControl.clear;
	~envelopeControl2.clear;
}	
			
	load {
Routine({		
		1.do{ var time = 0.25;
		 time.yield;	
partials = PartialTracker(256);

		time.yield;
~tenor.put(0, \numpar, 0, [\fftbuf, 0, \magbuf, 1, \freqbuf, 2]);

partials.getarrays;
		time.yield;
		'READY TO START'.postln;

}; 
		
		}).play;
}


	midicontrol {arg ipAddress = "169.254.80.149";
var inter, source,inter2, source2,inter3, source3;
var spec,spec1,spec2,spec3,spec4;

spec = ControlSpec(0.1, 4, 'lin');
spec1 = ControlSpec(0.ampdb, 1.ampdb, \db, units: " dB");
spec2 = ControlSpec(0.01, 50.0, 'lin');
spec3 = ControlSpec(0.0, 1.0, 'lin');
spec4 = ControlSpec(-90, 6, \db, units: " dB");
n = NetAddr(ipAddress, 57120);

MIDIIn.noteOn = { | src, chan, num, val |
if(src == midiPad, {
if(num == 53, {
		n.sendMsg("/midi", num, val);
		});
});

};

MIDIIn.control = { | port, chan, num, val |
	
	if(port == behringer, {

//start
	
	if(num == 65,
		{this.startproxys; 'nodeproxys'.postln;}
	);
	if(num == 73,
		{this.load;}
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
		{~soprano.set(\globamp, ~globamp1 = spec1.map(val/127.0).dbamp);
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
			routineGagok.osc.remove;
			this.finish;
		}).play; 
		}
	);

});	
	
};

}

finish {		partials.stoptask;
			s.freeAll; 
			s.bufFreeRange(0,284); 
			s.queryAllNodes;
			this.clearproxys;
			currentEnvironment.clear;
}		

initKnobs {	////knobs
	control.control(0, 1, 29); //overtones
	control.control(0, 2, 46); //noise
	control.control(0, 3, 66); //noise2
	control.control(0, 4, 63); //sinamp piano
	control.control(0, 5, 29); //legato piano
	control.control(0, 7, 40); //piano ruido
	control.control(0, 8, 90); //legato piano
	//sliders
	control.control(0, 81, 58); //chan1 (soprano)
	control.control(0, 82, 58); //chan2 (alto1)
	control.control(0, 83, 58); //chan3 (alto2)
	control.control(0, 84, 48); //chan4 (tenor)
	control.control(0, 85, 75); //chan5 (bass)
	control.control(0, 86, 88); //chan6 (piano)
	//set inicial volumes
	~soprano.set(\globamp, ~globamp1 = 0.20856841713683);
	~alto1.set(\globamp, 0.20856841713683);
	~alto2.set(\globamp, 0.20856841713683);
	~tenor.set(\globamp, ~globamp4 = 0.14284828569657);
	~bass.set(\globamp, 0.34875069750139);
	~piano.set(\globamp, 0.48012896025792);	
}
		
	begining { 
	'S/ '.post; ~step1 = 0;
	'A1/ '.post; ~step2 = 0;
	'A2/ '.post; ~step3 = 0;
	'T/ '.post; ~step4 = 0;
	'B/ '.post; ~step5 = 0;
	
	this.initKnobs;
	

}

rehearsalA { 	~step1 = 26;
			~step2 = 26;
			~step3 = 26;
			~step4 = 22;
			~step5 = 28;
			
		~soprano.put(0, \gagaku, 0, [\bufnum, 20, \out, 0, \amp, 0, \window, 1.0, \start, rrand(0, 8720430)]);
		~alto1.put(0, \bowedharmonics, 0, [\bufnum, 20, \amp, 0, \out, 0, \start, rrand(0, 8720430)]);
		~alto1_1 = ~alto1.objects[0];
		
		~alto2.put(0, \bowedharmonics, 0, [\bufnum, 20, \amp, 0, \out, 0, \start, rrand(0, 8720430)]);
		~alto2_1 = ~alto2.objects[0];
		~alto2_1.set(\gates, 0); 
		~alto2.add(\bowedharmonics, 0, [\bufnum, 20, \amp, 0, \out, 0, \start, rrand(0, 8720430)]);
		~alto2_2 = ~alto2.objects[1];
		
		~tenor.add(\korea, 0, [\amp, 0, \lengh, 6, \bufnum, 8]);		this.initKnobs;	
			
}

rehearsalB { 	~step1 = 46;
			~step2 = 46;
			~step3 = 56;
			~step4 = 52;
			~step5 = 48;
			
		~soprano.put(0, \gagaku, 0, [\bufnum, 20, \out, 0, \amp, 0, \window, 1.0, \start, rrand(0, 8720430)]);
		~alto1.put(0, \bowedharmonics, 0, [\bufnum, 20, \amp, 0, \out, 0, \start, rrand(0, 8720430)]);
		~alto1_1 = ~alto1.objects[0];
		
		~alto2.put(0, \bowedharmonics, 0, [\bufnum, 20, \amp, 0, \out, 0, \start, rrand(0, 8720430)]);
		~alto2_1 = ~alto2.objects[0];
		~alto2_1.set(\gates, 0); 
		~alto2.add(\bowedharmonics, 0, [\bufnum, 20, \amp, 0, \out, 0, \start, rrand(0, 8720430)]);
		~alto2_2 = ~alto2.objects[1];
		
		~tenor.add(\korea, 0, [\amp, 0, \lengh, 6, \bufnum, 8]);	
		~tenor1 = ~tenor.objects[0];
		~legato = 1.5;
		
		~bass.put(0, \korea, 0, [\amp, 0.0, \lengh, 2, \bufnum, 277]);
		~bass.put(1, \korea, 0, [\amp, 0.0, \lengh, 2, \bufnum, 278]);
		this.initKnobs;	
}

rehearsalC { 	~step1 = 74;
			~step2 = 82;
			~step3 = 94;
			~step4 = 86;
			~step5 = 70;
			
		~soprano.put(0, \gagaku, 0, [\bufnum, 20, \out, 0, \amp, 0, \window, 1.0, \start, rrand(0, 8720430)]);
		~alto1.put(0, \bowedharmonics, 0, [\bufnum, 20, \amp, 0, \out, 0, \start, rrand(0, 8720430)]);
		~alto1_1 = ~alto1.objects[0];
		
		~alto2.put(0, \bowedharmonics, 0, [\bufnum, 20, \amp, 0, \out, 0, \start, rrand(0, 8720430)]);
		~alto2_1 = ~alto2.objects[0];
		~alto2_1.set(\gates, 0); 
		~alto2.add(\bowedharmonics, 0, [\bufnum, 20, \amp, 0, \out, 0, \start, rrand(0, 8720430)]);
		~alto2_2 = ~alto2.objects[1];
		
		~tenor.add(\korea, 0, [\amp, 0, \lengh, 6, \bufnum, 8]);	
		~tenor1 = ~tenor.objects[0];
		~legato = 1.5;
		
		~bass.put(0, \korea, 0, [\amp, 0.0, \lengh, 2, \bufnum, 277]);
		~bass.put(1, \korea, 0, [\amp, 0.0, \lengh, 2, \bufnum, 278]);
		this.initKnobs;	
}	

rehearsalD { 	~step1 = 96;
			~step2 = 100;
			~step3 = 120;
			~step4 = 122;
			~step5 = 84;
			
		//~soprano.put(0, \gagaku, 0, [\bufnum, 20, \out, 0, \amp, 0, \window, 1.0, \start, rrand(0, 8720430), \gates, 0]);		
		
		~tenor.add(\korea, 0, [\amp, 0, \lengh, 6, \bufnum, 8]);	
		~tenor1 = ~tenor.objects[0];
		~legato = 1.5;
		
		~bass.put(0, \korea, 0, [\amp, 0.0, \lengh, 2, \bufnum, 277]);
		~bass.put(1, \korea, 0, [\amp, 0.0, \lengh, 2, \bufnum, 278]);		this.initKnobs;		
			
}	


rehearsalE { 	~step1 = 124;
			~step2 = 126;
			~step3 = 146;
			~step4 = 148;
			~step5 = 110;
			
		~soprano.put(0, \gagaku, 0, [\bufnum, 20, \out, 0, \amp, 0, \window, 1.0, \start, rrand(0, 8720430), \gates, 0]);		
		
		routineGagok = Etudes2Routine.new;
		routineGagok.startOsc;
		routineGagok.osc.add;
		routineGagok.interval = 1.0;
		routineGagok.routines;
		~alto2.set(\vol, 0);
		
		
		~tenor.add(\korea, 0, [\amp, 0, \lengh, 6, \bufnum, 8]);	
		~tenor1 = ~tenor.objects[0];
		~legato = 1.5;
		
		~bass.put(0, \korea, 0, [\amp, 0.0, \lengh, 2, \bufnum, 277]);
		~bass.put(1, \korea, 0, [\amp, 0.0, \lengh, 2, \bufnum, 278]);
		this.initKnobs;		
			
}	


rehearsalF { 	~step1 = 168;
			~step2 = 166;
			~step3 = 196;
			~step4 = 194;
			~step5 = 132;
			
		~soprano.put(0, \gagaku, 0, [\bufnum, 20, \out, 0, \amp, 0, \window, 1.0, \start, rrand(0, 8720430), \gates, 0]);		
		
		~alto1.put(0, \gagaku, 0, [\gates, 0, \bufnum, 20, \out, 0, \freq,  466.875, \amp, 67.linlin(0,127,0, 15), \midi, 466.875, \window, 1.0, \base,  466.875, \start, rrand(0, 8720430)]);
		
		routineGagok = Etudes2Routine.new;
		routineGagok.startOsc;
		routineGagok.osc.add;
		routineGagok.interval = 1.0;
		routineGagok.routines;
		~alto2.set(\vol, 0);
		
		
		~tenor.add(\korea, 0, [\amp, 0, \lengh, 6, \bufnum, 8]);
		'SING: CHE'.postln;
		~tenor1.set(\num, 14);
			
		~tenor1 = ~tenor.objects[0];
		~legato = 1.5;
		
		~bass.put(0, \korea, 0, [\amp, 0.0, \lengh, 2, \bufnum, 277]);
		~bass.put(1, \korea, 0, [\amp, 0.0, \lengh, 2, \bufnum, 278]);
		this.initKnobs;
}	


	
	//write Synth Defs 
	*initClass {
	
	SynthDef.writeOnce("highsound", {|amp = 1, out = 0, vol = 7, oct = 1.0, freq = 421.29577636719, start  = 0.0, globamp = 0.5|
var env, env2, signal, exciter, signal2, signal3, amplitude, env3, signal4, othersignal, exciter2, rate;
amplitude = Amplitude.kr(PlayBuf.ar(1,5,1));
rate = Impulse.kr(100);
env2 = EnvGen.kr(Env.perc((((CuspL.ar(5, 1.2, 1.8, 0.3)).abs)*0.10), 0.15, 0.6),1, doneAction: 0);
env3 = EnvGen.kr(Env.new( [0, 1, 1, 0], [0.00001, 2.5, 0.1]),1, doneAction: 2);
exciter = Decay.ar(PlayBuf.ar(1,3,4,1, start, 1) *0.5 *env2);
signal = Klank.ar(`[ [LFNoise1.ar(10, 41, 601), LFNoise1.ar(10, 66, 617), LFNoise1.ar(10, 31, 712), LFNoise1.ar(10, 31, 849), LFNoise1.ar(10, 46, 919), LFNoise1.ar(10, 28, 1255) , LFNoise1.ar(10, 86, 1374), LFNoise1.ar(10, 120, 1420), LFNoise1.ar(10, 21, 1609), LFNoise1.ar(10, 75, 1661), LFNoise1.ar(10, 26, 2247), LFNoise1.ar(10, 40, 2041), LFNoise1.ar(10, 15, 2487), LFNoise1.ar(10, 162, 2575), LFNoise1.ar(10, 19, 2971), LFNoise1.ar(10, 63, 3028), LFNoise1.ar(10, 49, 3129), LFNoise1.ar(10, 72, 3613), LFNoise1.ar(10, 73, 3894), LFNoise1.ar(10, 128, 4112), LFNoise1.ar(10, 21, 4257), LFNoise1.ar(10, 23, 4311), LFNoise1.ar(10, 77, 4510), LFNoise1.ar(10, 81, 4680), LFNoise1.ar(10, 42, 4875), LFNoise1.ar(10, 23, 5514), LFNoise1.ar(10, 90, 5559), LFNoise1.ar(10,102, 5658), LFNoise1.ar(10, 30, 5826), LFNoise1.ar(10, 134, 5875), LFNoise1.ar(10, 64, 6276), LFNoise1.ar(10, 149, 6366), LFNoise1.ar(10, 104, 6848), LFNoise1.ar(10, 54, 6978), LFNoise1.ar(10, 20, 7572), LFNoise1.ar(10, 89, 7626), LFNoise1.ar(10, 67, 7745), LFNoise1.ar(10, 37, 7995), LFNoise1.ar(10, 101, 8095), LFNoise1.ar(10, 94, 8275), LFNoise1.ar(10, 44, 8415), LFNoise1.ar(10, 120, 10187)]*oct,[0.002359, 0.001776, 0.001467, 0.001185, 0.004292, 0.007105, 0.000951, 0.001550, 0.001346, 0.000895, 0.011959, 0.004613, 0.005135, 0.001141, 0.001955, 0.001750, 0.001176, 0.000622, 0.002000, 0.001034, 0.008535, 0.002910, 0.006231, 0.002139, 0.002455, 0.003943, 0.001412, 0.002694, 0.002560, 0.000704, 0.002703, 0.001728, 0.002090, 0.001003, 0.006374, 0.002114, 0.008391, 0.001268, 0.001972, 0.003785, 0.011746, 0.000989].normalizeSum, Array.fill(20, {rrand(1.5, 2.5)})], exciter);
signal2 = Mix.ar((signal * amp )*1) ;
signal3 = signal2 * amplitude;
signal4 =  Mix.ar(signal3  * env3);
Out.ar(out, (signal4 * vol)*globamp);
});

	SynthDef.writeOnce("highsound2", {|amp = 1, out = 0, vol = 7, start  = 0.0, globamp = 0.5, oct=4|
var env, env2, signal, exciter, signal2, signal3, amplitude, env3, signal4, othersignal, exciter2, rate, rusty;
amplitude = Amplitude.kr(PlayBuf.ar(1,5,1));
rate = Impulse.kr(100);
env2 = EnvGen.kr(Env.perc((((CuspL.ar(5, 1.2, 1.8, 0.3)).abs)*0.10), 0.15, 0.6),1, doneAction: 0);
env3 = EnvGen.kr(Env.new( [0, 1, 1, 0], [0.00001, 2.5, 0.1]),1, doneAction: 2);
exciter = Decay.ar(PlayBuf.ar(1,3,oct,1, start, 1) *0.5 *env2);
signal = Ringz.ar(exciter, [LFNoise1.ar(10, 41, 601), LFNoise1.ar(10, 66, 617), LFNoise1.ar(10, 31, 712), LFNoise1.ar(10, 31, 849), LFNoise1.ar(10, 46, 919), LFNoise1.ar(10, 28, 1255) , LFNoise1.ar(10, 86, 1374), LFNoise1.ar(10, 120, 1420), LFNoise1.ar(10, 21, 1609), LFNoise1.ar(10, 75, 1661), LFNoise1.ar(10, 26, 2247), LFNoise1.ar(10, 40, 2041), LFNoise1.ar(10, 15, 2487), LFNoise1.ar(10, 162, 2575), LFNoise1.ar(10, 19, 2971), LFNoise1.ar(10, 63, 3028), LFNoise1.ar(10, 49, 3129), LFNoise1.ar(10, 72, 3613), LFNoise1.ar(10, 73, 3894), LFNoise1.ar(10, 128, 4112), LFNoise1.ar(10, 21, 4257), LFNoise1.ar(10, 23, 4311), LFNoise1.ar(10, 77, 4510), LFNoise1.ar(10, 81, 4680), LFNoise1.ar(10, 42, 4875), LFNoise1.ar(10, 23, 5514), LFNoise1.ar(10, 90, 5559), LFNoise1.ar(10,102, 5658), LFNoise1.ar(10, 30, 5826), LFNoise1.ar(10, 134, 5875), LFNoise1.ar(10, 64, 6276), LFNoise1.ar(10, 149, 6366), LFNoise1.ar(10, 104, 6848), LFNoise1.ar(10, 54, 6978), LFNoise1.ar(10, 20, 7572), LFNoise1.ar(10, 89, 7626), LFNoise1.ar(10, 67, 7745), LFNoise1.ar(10, 37, 7995), LFNoise1.ar(10, 101, 8095), LFNoise1.ar(10, 94, 8275), LFNoise1.ar(10, 44, 8415), LFNoise1.ar(10, 120, 10187)], Array.fill(20, {rrand(1.5, 2.5)}), [0.002359, 0.001776, 0.001467, 0.001185, 0.004292, 0.007105, 0.000951, 0.001550, 0.001346, 0.000895, 0.011959, 0.004613, 0.005135, 0.001141, 0.001955, 0.001750, 0.001176, 0.000622, 0.002000, 0.001034, 0.008535, 0.002910, 0.006231, 0.002139, 0.002455, 0.003943, 0.001412, 0.002694, 0.002560, 0.000704, 0.002703, 0.001728, 0.002090, 0.001003, 0.006374, 0.002114, 0.008391, 0.001268, 0.001972, 0.003785, 0.011746, 0.000989].normalizeSum);
rusty = Friction.ar(signal, friction: 1.75584e-5, mass: 2.69789);
signal2 = Mix.ar((rusty.sum * amp )*1) ;
signal3 = signal2 * amplitude;
signal4 =  Mix.ar(signal3  * env3);
Out.ar(out, (signal4 * vol)*globamp);
});

	SynthDef.writeOnce("bowedharmonics",
{arg out = 0, freq = 440, amp = 0.5, bufnum = 9, gates = 1, start = 0.0, gates2=1, globamp = 0.5;
var root, scale, env, signal, signal2, in, caos, atk, dec, env2, signal3;
var trig, p, s, exc, x;
caos = ((CuspL.ar(3, 1.2, 1.8, 0.3)).abs);
atk = (caos * 0.2) + 0.7;
dec = (caos * 0.6) +2.3;
root = rrand(3,6);
env =  EnvGen.ar(Env.adsr(0.1, 0.5, 1, 3, 0.8, -5), gates, doneAction:2); 
env2 =  EnvGen.ar(Env.asr(atk, 1, dec, 1, -5), gates2, doneAction:2); 
in = PlayBuf.ar(1,bufnum, 1, loop: 1); //play do
exc = (PlayBuf.ar(1,3,2,1, start, 1)*0.25) * max(0, LFNoise1.kr(exprand(0.25,0.5), 0.6, 0.4));
s = (Klank.ar(`[Array.series(12, freq, freq), Array.geom(12,1,rrand(0.8,0.9)), Array.fill(12, {rrand(1.0,3.0)})], exc) * 0.1).softclip;
signal3 = PitchShift.ar(s, 0.1,  2204/(Pitch.kr(in, ampThreshold: 0, median: 7)),0,0.004, mul: Amplitude.kr(in)*2+0.1);
signal = signal3*env;
signal2 = Mix.ar(signal)*Lag.kr(amp, 1.4);
Out.ar(out, (signal2*env2)*globamp);
});

SynthDef.writeOnce("gagaku",
{arg out = 0, freq = 440, amp = 0.5, bufnum = 1, gates = 1, anabuf = 10, midi = 373, window = 0.1, base = 373.5, gates2=1, globamp = 0.5;
var root, scale, env, signal, signal2, signal3, in, pitch, in2, caos, atk, dec, signal4, env2;
// bowed string
var trig, p, s, exc, x;
caos = ((CuspL.ar(2, 1.2, 1.8, 0.3)).abs);
atk = (caos * 0.2) + 1;
dec = (caos * 0.6) +2.8;
root = rrand(3,6);
env2 =  EnvGen.ar(Env.asr(atk, 1, dec, 1, -5), gates2, doneAction:0); 
root = rrand(3,6);
env =  EnvGen.ar(Env.adsr(0.1, 0.5, 1, 3, 0.8, -5), gates, doneAction:2); 
in = PlayBuf.ar(1,bufnum, 1, loop: 1); //play do
in2 = PlayBuf.ar(1,anabuf, (midi/base)*2, loop: 1);
pitch = Pitch.kr(in2, 896.137, 650, ampThreshold: 0.02, median: 7 )/(388.48985869382*2);
exc = (PlayBuf.ar(1,3,2,1, loop: 1)*0.25) * max(0, LFNoise1.kr(exprand(0.25,0.5), 0.6, 0.4));
s = (Klank.ar(`[Array.series(12, freq, freq), Array.geom(12,1,rrand(0.8,0.9)), Array.fill(12, {rrand(1.0,3.0)})], exc) * 0.1).softclip;
signal2 = PitchShift.ar(s, 0.1,  2204/(Pitch.kr(in, ampThreshold: 0, median: 7)),0,0.004, mul: Amplitude.kr(in)*2+0.1);
signal3 = PitchShift.ar(signal2, window, pitch, 0);
signal = signal3*env;
signal4 = Mix.ar(signal)*Lag.kr(amp, 1.4);
Out.ar(out, (signal4*env2)*globamp);
});


	SynthDef.writeOnce("drum", {|amp = 1, out = 0, vol = 1, oct = 1, freq = 949.03741455078, oct2 = 1, start = 0.0, globamp = 0.5|
var env, env2, signal, exciter, signal2, signal3, pitch, amplitude, env3, signal4, othersignal, exciter2, playit;
playit = PlayBuf.ar(1,3,1,1,start, loop: 1);
pitch = Pitch.kr(PlayBuf.ar(1,6,oct2), freq, ampThreshold: 0.001);
amplitude = Amplitude.kr(PlayBuf.ar(1,7,1));
env = EnvGen.kr(Env.perc((((CuspL.ar(5, 1.2, 1.8, 0.3)).abs)*0.001), 0.15, 0.6),1, doneAction: 0);
env2 = EnvGen.kr(Env.perc((((CuspL.ar(5, 1.2, 1.8, 0.3)).abs)*0.001), 0.46, 0.6),1, doneAction: 0);
env3 = EnvGen.kr(Env.new( [0, 1, 1, 0], [0.00001, 0.6, 0.1]),1, doneAction: 2);
exciter = Decay.ar(playit *0.5 *env2);
exciter2 = Decay.ar(playit *0.5 *env);
signal = Klank.ar(`[ [241.98414611816, 302.48388671875, 362.82928466797, 380.56851196289, 430.86346435547, 604.56549072266, 685.33087158203, 677.18078613281, 711.93267822266, 801.72814941406, 807.93762207031, 827.83050537109, 900.46606445312, 970.08648681641, 949.81121826172, 994.63397216797, 1038.2399902344, 1132.1401367188, 1225.7690429688, 1233.2979736328],[1.4711756706238, 13.523810386658, 8.2780437469482, 20.293388366699, 1.7970154285431, 3.2977316379547, 8.0342464447021, 2.5935742855072, 6.4556283950806, 4.0497841835022, 3.6703894138336, 8.0870733261108, 29.362461090088, 25.230842590332, 9.1305751800537, 2.2331495285034, 1.1417412757874, 2.4161162376404, 2.6394062042236, 3.6136891841888].normalizeSum, Array.fill(20, {rrand(3.5, 4.5)})], exciter);
othersignal = Klank.ar(`[ [241.98414611816, 302.48388671875, 362.82928466797, 380.56851196289, 430.86346435547, 604.56549072266, 685.33087158203, 677.18078613281, 711.93267822266, 801.72814941406, 807.93762207031, 827.83050537109, 900.46606445312, 970.08648681641, 949.81121826172, 994.63397216797, 1038.2399902344, 1132.1401367188, 1225.7690429688, 1233.2979736328]*oct,[1.4711756706238, 13.523810386658, 8.2780437469482, 20.293388366699, 1.7970154285431, 3.2977316379547, 8.0342464447021, 2.5935742855072, 6.4556283950806, 4.0497841835022, 3.6703894138336, 8.0870733261108, 29.362461090088, 25.230842590332, 9.1305751800537, 2.2331495285034, 1.1417412757874, 2.4161162376404, 2.6394062042236, 3.6136891841888].normalizeSum, Array.fill(20, {rrand(0.15, 0.15)})], exciter2);
signal2 = Mix.ar(((signal * amp )*1) + (playit *env) + (othersignal * 3 ));
signal3 = PitchShift.ar(signal2, 0.1, pitch/freq, 0, 0.004) * amplitude ;
signal4 =  Mix.ar(signal3  * env3);
Out.ar(out, (signal4 * vol)*globamp);
});

SynthDef.writeOnce("drum2", {|amp = 1, out = 0, vol = 7, pitch = 421.29577636719, oct2 = 0.5, start  = 0.0, globamp = 0.5|
var env, env2, signal, exciter, signal2, signal3, amplitude, env3, signal4, othersignal, exciter2, rate, freq, hasFreq;
# freq, hasFreq = Pitch.kr(PlayBuf.ar(1,4,oct2), pitch, ampThreshold: 0.001);
amplitude = Amplitude.kr(PlayBuf.ar(1,5,1));
rate = Impulse.kr(100);
//env = EnvGen.kr(Env.perc(0.0001, 0.15, 1),1, doneAction: 0);
env2 = EnvGen.kr(Env.perc((((CuspL.ar(5, 1.2, 1.8, 0.3)).abs)*0.10), 0.15, 0.6),1, doneAction: 0);
env3 = EnvGen.kr(Env.new( [0, 1, 1, 0], [0.00001, 2.5, 0.1]),1, doneAction: 2);
exciter = Decay.ar(PlayBuf.ar(1,3,4,1, start, 1) *0.5 *env2);
signal = DynKlank.ar(`[ [LFNoise1.ar(10, 41, 601), LFNoise1.ar(10, 66, 617), LFNoise1.ar(10, 31, 712), LFNoise1.ar(10, 31, 849), LFNoise1.ar(10, 46, 919), LFNoise1.ar(10, 28, 1255) , LFNoise1.ar(10, 86, 1374), LFNoise1.ar(10, 120, 1420), LFNoise1.ar(10, 21, 1609), LFNoise1.ar(10, 75, 1661), LFNoise1.ar(10, 26, 2247), LFNoise1.ar(10, 40, 2041), LFNoise1.ar(10, 15, 2487), LFNoise1.ar(10, 162, 2575), LFNoise1.ar(10, 19, 2971), LFNoise1.ar(10, 63, 3028), LFNoise1.ar(10, 49, 3129), LFNoise1.ar(10, 72, 3613), LFNoise1.ar(10, 73, 3894), LFNoise1.ar(10, 128, 4112), LFNoise1.ar(10, 21, 4257), LFNoise1.ar(10, 23, 4311), LFNoise1.ar(10, 77, 4510), LFNoise1.ar(10, 81, 4680), LFNoise1.ar(10, 42, 4875), LFNoise1.ar(10, 23, 5514), LFNoise1.ar(10, 90, 5559), LFNoise1.ar(10,102, 5658), LFNoise1.ar(10, 30, 5826), LFNoise1.ar(10, 134, 5875), LFNoise1.ar(10, 64, 6276), LFNoise1.ar(10, 149, 6366), LFNoise1.ar(10, 104, 6848), LFNoise1.ar(10, 54, 6978), LFNoise1.ar(10, 20, 7572), LFNoise1.ar(10, 89, 7626), LFNoise1.ar(10, 67, 7745), LFNoise1.ar(10, 37, 7995), LFNoise1.ar(10, 101, 8095), LFNoise1.ar(10, 94, 8275), LFNoise1.ar(10, 44, 8415), LFNoise1.ar(10, 120, 10187)]*(freq/pitch),[0.002359, 0.001776, 0.001467, 0.001185, 0.004292, 0.007105, 0.000951, 0.001550, 0.001346, 0.000895, 0.011959, 0.004613, 0.005135, 0.001141, 0.001955, 0.001750, 0.001176, 0.000622, 0.002000, 0.001034, 0.008535, 0.002910, 0.006231, 0.002139, 0.002455, 0.003943, 0.001412, 0.002694, 0.002560, 0.000704, 0.002703, 0.001728, 0.002090, 0.001003, 0.006374, 0.002114, 0.008391, 0.001268, 0.001972, 0.003785, 0.011746, 0.000989].normalizeSum, Array.fill(20, {rrand(1.5, 2.5)})], exciter);
signal2 = Mix.ar((signal * amp )*1) ;
signal3 = signal2 * amplitude;
signal4 =  Mix.ar(signal3  * env3);
Out.ar(out, (signal4 * vol)*globamp);
});

	SynthDef.writeOnce("korea", {arg out = 0, amp = 1, bufnum = 16, lengh = 6, globamp = 0.5;
var in, signal, signal2, env;
env =  EnvGen.kr(Env.new( [0, 1, 1, 0], [0.00001, lengh, 0.1]),1, doneAction: 2);
in = PlayBuf.ar(1,bufnum, BufRateScale.kr(bufnum), loop: 0);  //play do
signal = in * env;
signal2 = signal * amp;
Out.ar(out, signal2*globamp);
});

SynthDef.writeOnce("reedgagaku",
{arg centerFreq = 440, freqadj = 1.0, in, in2, signal2, rate=1.0, gagok, gates=1.0, globamp=1.0, out=0, amp=1, gates2 = 1.0, sinenv = 1.0, gates3 = 0.0, delaytime = 0.2, delay = 0.2;
 	var sig, amparray, freqarray, exc, klangy, chain, freq, hasFreq, freq2, hasFreq2;
 	var caos, atk, dec, env, signal3, env2;
gagok = PlayBuf.ar(1, 30, rate, loop: 1);
#freq, hasFreq = Pitch.kr(gagok, 292.0, ampThreshold: 0.0, median: 7);

caos = ((CuspL.ar(2, 1.2, 1.8, 0.3)).abs);
atk = (caos * 0.2) + 0.09;
dec = (caos * 0.6) + 1.0;
env =  EnvGen.ar(Env.dasdr(delay, atk, 1, delay, dec, 1, -5), gates, doneAction:0); 
env2 =  EnvGen.ar(Env.dasdr(delay, atk, 1, delay, dec, 1, -5), gates3, doneAction:0); 
freqarray = Array.series(12, centerFreq, centerFreq);
amparray = Array.geom(12,1,rrand(0.7,0.9));
in = PlayBuf.ar(1, 3, BufRateScale.kr(3), loop: 1);
in2 = PlayBuf.ar(1,34, 1, loop: 1);
#freq2, hasFreq2 = Pitch.kr(in2, ampThreshold: 0, median: 7);
chain = FFT(27, in);
chain = chain.pvcalc(1024, {|mags, phases|
[mags.sqrt, phases]; 
}, frombin: 0, tobin: 250, zeroothers: 0);
sig = Shaper.ar(21, BPF.ar(IFFT(chain).dup, 1276*0.5, 1.91849*0.5));
exc = (sig ! 1);
klangy = (DynKlank.ar(`[
					freqarray * (freq/352).lag(0.5) * freqadj,
					amparray,
					Array.fill(12, {rrand(1.0,3.0)})				], exc ) * 0.04).softclip;
signal2 = PitchShift.ar(klangy, 0.1,  (2204/(freq2)),0,0.004, mul: Amplitude.kr(in2).lag(0.1)*2+0.1) * 3;
signal3 = (signal2*env) * gates2 * Lag.kr(amp, 1.4) * sinenv;
Out.ar(out, DelayL.ar(signal3*env2, delaytime, delaytime) * globamp);
});

SynthDef.writeOnce("flutegagok", { arg gate = 1, amp=0.5, out = 0, rate = 1.0, gates2 = 1.0, gates3 = 0.0, gates = 1.0, globamp = 1.0, sinenv=1.0, oct = 1.0, delaytime = 0.2, delay = 0.2;
	var signal, signal2, signal3, eg, fc, osc, a, b, w;
	var gagok, freq, hasFreq, in2, freq2, hasFreq2, env, env2;
	var caos, atk, dec;
	gagok = PlayBuf.ar(1, 30, rate, loop: 1);
	#freq, hasFreq = Pitch.kr(gagok, 292.0, ampThreshold: 0.0, median: 7);

	caos = ((CuspL.ar(2, 1.2, 1.8, 0.3)).abs);
	atk = (caos * 0.2) + 0.05;
	dec = (caos * 0.6) + 0.6;
	env =  EnvGen.ar(Env.dadsdr(delay, atk, atk, dec, delay, dec, 1, -5), gates, doneAction:0); 
	env2 =  EnvGen.ar(Env.dadsdr(delay, atk, atk,dec, delay, dec, 1, -5), gates3, doneAction:0); 

	in2 = PlayBuf.ar(1, 34, 1, loop: 1);
	#freq2, hasFreq2 = Pitch.kr(in2, ampThreshold: 0, median: 7);
	fc = LinExp.kr(LFNoise1.kr(Rand(0.25,0.4)), -1,1,500,2000);
	osc = Mix.fill(8, {LFCub.ar((((freq.cpsmidi + 2).midicps)*oct).lag(0.4) * [Rand(0.99,1.01),Rand(0.99,1.01)], 0, 0.1) }).clip2(1) * 0.2;
	eg = EnvGen.kr(Env.asr(1,1,1), gate, doneAction:2);
	signal = eg * RLPF.ar(osc, fc, 0.1);

	signal2 = PitchShift.ar(signal/2, 0.1,  2204/(freq2),0,0.004, mul: Amplitude.kr(in2).lag(0.13)*2+0.1);
	signal3 = (Mix.ar(signal2) * gates2) * Lag.kr(amp, 1.4) * sinenv*2;
	Out.ar(out, DelayL.ar(signal3*env2, delaytime, delaytime)*globamp);
});

SynthDef.writeOnce("silentPerc",
{
	arg freq = 176, decayTime = 10, out = 0, amp = 1, rate = 0.5, start = 0; 
	var burstEnv, att = 0.0, dec = 0.33, signalOut, delayTime, signal, signal2, signal3, noiseEnv, inton, playit;
	playit = PlayBuf.ar(1,3,rate, 1.0,start , loop: 1);
	inton = Pitch.kr(PlayBuf.ar(1,35), 344.68597412109, ampThreshold: 0.02, median: 7)/344.68597412109; 
	delayTime = freq.reciprocal;
	burstEnv = EnvGen.kr(Env.perc(att, dec));
	noiseEnv = EnvGen.kr(Env.perc(0.01, 0.1));
	signalOut = CombL.ar(playit*burstEnv, delayTime, delayTime, decayTime);
	signal = playit*noiseEnv;
	signal2 = (signal*4) + signalOut;
	signal3 = PitchShift.ar(signal2, 0.1, inton, 0, 0.004);
	DetectSilence.ar(signal3, doneAction: 2);
	Out.ar(out,signal2*amp);
});

}

}