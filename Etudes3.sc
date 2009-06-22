Etudes3 : EtudesMain {
	var buffSteps, buff1, buff2, buff3, buff4, buff5, buff6, array1, array2, array3, array4, array5, array6, arraySteps;
	var partials, partials2, envelopeControl, index6, ffttime, newbin;
	
	startEtudes3 {this.init;
	
	//Buffers [0,1,2,4] for partialtrackerfile	
	
	Buffer.alloc(s, 1024, 1, bufnum: 6); //fft attack detector
	Buffer.alloc(s, 1024, 1, bufnum: 8); //filter for steps - fft
	buffSteps = Buffer.alloc(s, 1024, 1, bufnum: 9);//filter for steps - index buffer
	
	Buffer.alloc(s, 1024, 1, bufnum: 10); //fftbuffer for fft process
	Buffer.alloc(s, 1024, 1, bufnum: 11); //fftbuffer for fft process
	Buffer.alloc(s, 1024, 1, bufnum: 12); //fftbuffer for fft process
	Buffer.alloc(s, 1024, 1, bufnum: 13); //fftbuffer for fft process
	Buffer.alloc(s, 1024, 1, bufnum: 14); //fftbuffer for fft process
	Buffer.alloc(s, 1024, 1, bufnum: 15); //fftbuffer for fft process
	
	Buffer.alloc(s, 1024, 1, bufnum: 16); //don't know this one
	
	}
	
	buffers {
	//Buffer.alloc(s, 1024, 1, bufnum:7); //???

	buff1 = Buffer.alloc(s, 1024, 1, bufnum:17); //index buffer for fft process
	buff2 = Buffer.alloc(s, 1024, 1, bufnum:18); //index buffer for fft process
	buff3 = Buffer.alloc(s, 1024, 1, bufnum:19); //index buffer for fft process
	buff4 = Buffer.alloc(s, 1024, 1, bufnum:20); //index buffer for fft process
	buff5 = Buffer.alloc(s, 1024, 1, bufnum:21); //index buffer for fft process
	buff6 = Buffer.alloc(s, 1024, 1, bufnum:22); //index buffer for fft process
	
	Buffer.alloc(s, 1024, 1, bufnum: 23); //fft footsteps
	Buffer.alloc(s, 1024, 1, bufnum: 24); //fft footsteps
	Buffer.alloc(s, 1024, 1, bufnum: 25); //fft footsteps
	Buffer.alloc(s, 1024, 1, bufnum: 26); //fft footsteps
	Buffer.alloc(s, 1024, 1, bufnum: 27); //fft footsteps
	Buffer.alloc(s, 1024, 1, bufnum: 28); //fft footsteps

s.sendBundle(1.0,
['b_allocRead',29, documentPath ++ "/samples/steps/tile1.aif"],
['b_allocRead',30, documentPath ++ "/samples/steps/tile2.aif"],
['b_allocRead',31, documentPath ++ "/samples/steps/tile3.aif"],
['b_allocRead',32, documentPath ++ "/samples/steps/tile4.aif"],
['b_allocRead',33, documentPath ++ "/samples/steps/tile5.aif"],
['b_allocRead',34, documentPath ++ "/samples/steps/tile6.aif"],
['b_allocRead',35, documentPath ++ "/samples/steps/tile7.aif"],
['b_allocRead',36, documentPath ++ "/samples/steps/tile8.aif"],
['b_allocRead',37, documentPath ++ "/samples/steps/tile9.aif"],
['b_allocRead',38, documentPath ++ "/samples/steps/tile10.aif"],
['b_allocRead',39, documentPath ++ "/samples/steps/tile11.aif"],
['b_allocRead',40, documentPath ++ "/samples/steps/tile12.aif"],
['b_allocRead',41, documentPath ++ "/samples/steps/tile13.aif"],
['b_allocRead',42, documentPath ++ "/samples/steps/tile14.aif"],
['b_allocRead',43, documentPath ++ "/samples/steps/tile15.aif"],
['b_allocRead',44, documentPath ++ "/samples/steps/tile16.aif"],
['b_allocRead',45, documentPath ++ "/samples/steps/tile17.aif"],
['b_allocRead',46, documentPath ++ "/samples/steps/tile18.aif"],
['b_allocRead',47, documentPath ++ "/samples/steps/tile19.aif"],
['b_allocRead',48, documentPath ++ "/samples/steps/tile20.aif"],
['b_allocRead',49, documentPath ++ "/samples/steps/snowatk1.aif"],
['b_allocRead',50, documentPath ++ "/samples/steps/snowatk2.aif"],
['b_allocRead',51, documentPath ++ "/samples/steps/snowatk3.aif"],
['b_allocRead',52, documentPath ++ "/samples/steps/snowatk4.aif"],
['b_allocRead',53, documentPath ++ "/samples/steps/snowatk5.aif"],
['b_allocRead',54, documentPath ++ "/samples/steps/snowatk6.aif"],
['b_allocRead',55, documentPath ++ "/samples/steps/snowatk7.aif"],
['b_allocRead',56, documentPath ++ "/samples/steps/snowatk8.aif"],
['b_allocRead',57, documentPath ++ "/samples/steps/snowatk9.aif"],
['b_allocRead',58, documentPath ++ "/samples/steps/snowatk10.aif"],
['b_allocRead',59, documentPath ++ "/samples/steps/snowatk11.aif"],
['b_allocRead',60, documentPath ++ "/samples/steps/snowatk12.aif"],
['b_allocRead',61, documentPath ++ "/samples/steps/snowatk13.aif"],
['b_allocRead',62, documentPath ++ "/samples/steps/snowatk14.aif"],
['b_allocRead',63, documentPath ++ "/samples/steps/snowatk15.aif"],
['b_allocRead',64, documentPath ++ "/samples/steps/snowatk16.aif"],
['b_allocRead',65, documentPath ++ "/samples/steps/snowatk17.aif"],
['b_allocRead',66, documentPath ++ "/samples/steps/snowatk18.aif"],
['b_allocRead',67, documentPath ++ "/samples/steps/snowatk19.aif"],
['b_allocRead',68, documentPath ++ "/samples/steps/snowatk20.aif"],
['b_allocRead',69, documentPath ++ "/samples/steps/snowrel1.aif"],
['b_allocRead',70, documentPath ++ "/samples/steps/snowrel2.aif"],
['b_allocRead',71, documentPath ++ "/samples/steps/snowrel3.aif"],
['b_allocRead',72, documentPath ++ "/samples/steps/snowrel4.aif"],
['b_allocRead',73, documentPath ++ "/samples/steps/snowrel5.aif"],
['b_allocRead',74, documentPath ++ "/samples/steps/snowrel6.aif"],
['b_allocRead',75, documentPath ++ "/samples/steps/snowrel7.aif"],
['b_allocRead',76, documentPath ++ "/samples/steps/snowrel8.aif"],
['b_allocRead',77, documentPath ++ "/samples/steps/snowrel9.aif"],
['b_allocRead',78, documentPath ++ "/samples/steps/snowrel10.aif"],
['b_allocRead',79, documentPath ++ "/samples/steps/snowrel11.aif"],
['b_allocRead',80, documentPath ++ "/samples/steps/snowrel12.aif"],
['b_allocRead',81, documentPath ++ "/samples/steps/snowrel13.aif"],
['b_allocRead',82, documentPath ++ "/samples/steps/snowrel14.aif"],
['b_allocRead',83, documentPath ++ "/samples/steps/snowrel15.aif"],
['b_allocRead',84, documentPath ++ "/samples/steps/snowrel16.aif"],
['b_allocRead',85, documentPath ++ "/samples/steps/snowrel17.aif"],
['b_allocRead',86, documentPath ++ "/samples/steps/snowrel18.aif"],
['b_allocRead',87, documentPath ++ "/samples/steps/snowrel19.aif"],
['b_allocRead',88, documentPath ++ "/samples/steps/snowrel20.aif"],
['b_allocRead',89, documentPath ++ "/samples/harp/harpglissC1.aif"]);

	
//glass1
Buffer.read(s, documentPath ++ "/samples/glassBreak/speaker1.aif", bufnum: 91);
Buffer.read(s, documentPath ++ "/samples/glassBreak/speaker2.aif", bufnum: 92);
Buffer.read(s, documentPath ++ "/samples/glassBreak/speaker3.aif", bufnum: 93);
//glass2
Buffer.read(s, documentPath ++ "/samples/glassBreak/2_speaker1.aif", bufnum: 94);
Buffer.read(s, documentPath ++ "/samples/glassBreak/2_speaker2.aif", bufnum: 95);
Buffer.read(s, documentPath ++ "/samples/glassBreak/2_speaker3.aif", bufnum: 96);
//door knock1:
//knock1
Buffer.read(s, documentPath ++ "/samples/doorKnock/knokknok1L.aif", bufnum: 97);
Buffer.read(s, documentPath ++ "/samples/doorKnock/knokknok1R.aif", bufnum: 98);
//knock2
Buffer.read(s, documentPath ++ "/samples/doorKnock/knokknok2L.aif", bufnum: 99);
Buffer.read(s, documentPath ++ "/samples/doorKnock/knokknok2R.aif", bufnum: 100);
//knock3
Buffer.read(s, documentPath ++ "/samples/doorKnock/knokknok3L.aif", bufnum: 101);
Buffer.read(s, documentPath ++ "/samples/doorKnock/knokknok3R.aif", bufnum: 102);
//slamslams:
//slamslam1
Buffer.read(s, documentPath ++ "/samples/doorKnock/slamslam1L.aif", bufnum: 107);
Buffer.read(s, documentPath ++ "/samples/doorKnock/slamslam1R.aif", bufnum: 108);
//slamslam2
Buffer.read(s, documentPath ++ "/samples/doorKnock/slamslam2L.aif", bufnum: 109);
Buffer.read(s, documentPath ++ "/samples/doorKnock/slamslam2R.aif", bufnum: 110);
//sines
Buffer.read(s, documentPath ++ "/samples/doorKnock/SinesDown.aif", bufnum: 111);
Buffer.read(s, documentPath ++ "/samples/doorKnock/SinesUp.aif", bufnum: 112);

	}

	
	genenv {

	//envelopes	
	//steps
	makenv1 = Array.fill(14, {var env1, env2, env3, env4, arrenv;
env1 = [[rrand(0.045, 0.055), rrand(0.035, 0.045), rrand(0.035, 0.01), rrand(0.02, 0.01)].wchoose([4,4,1,1].normalizeSum), 
rrand(0.0398, 0.01), rrand(0.95, 1.00), rrand(1.8,2.1), [-4, -3].wchoose([5,4].normalizeSum)];//normal
env2 = [rrand(1.1, 2.55),rrand(0.198, 0.1403), rrand(0.95, 1.0), rrand(3.6,4.4), [4, 3].wchoose([1,1].normalizeSum)]; //slow attack
env3 = [rrand(0.1, 0.5), rrand(0.1, 0.3), rrand(0.95, 1.0), rrand(1.8,2.6), [-2, -3].choose]; //fast attack
env4 = [rrand(0.1, 0.4), rrand(0.398, 0.1403), rrand(0.95, 1.0), rrand(1.5,2.1), [-6, -7].choose];
//medium rise cubic
arrenv = [env1,env2, env3, env4].wchoose([7,2,4,5].normalizeSum); //weighted choose	
});

	//ffthigh
	makenv2 = Array.fill(8, {var env1, env2, env3, env4, arrenv;
env1 = [[rrand(0.045, 0.055), rrand(0.035, 0.045), rrand(0.035, 0.01), rrand(0.02, 0.01)].wchoose([4,4,1,1].normalizeSum), 
rrand(0.0398, 0.01), rrand(0.95, 1.00), rrand(1.8,2.1), [-4, -3].wchoose([5,4].normalizeSum)];//normal
env2 = [rrand(1.1, 2.55),rrand(0.198, 0.1403), rrand(0.95, 1.0), rrand(3.6,4.4), [4, 3].wchoose([1,1].normalizeSum)]; //slow attack
env3 = [rrand(0.1, 0.5), rrand(0.1, 0.3), rrand(0.95, 1.0), rrand(1.8,2.6), [-2, -3].choose]; //fast attack
env4 = [rrand(0.1, 0.4), rrand(0.398, 0.1403), rrand(0.95, 1.0), rrand(1.5,2.1), [-6, -7].choose];
//medium rise cubic
arrenv = [env1,env2, env3, env4].wchoose([7,2,4,5].normalizeSum); //weighted choose	
});
	//fftlow
	makenv3 = Array.fill(11, {var env1, env2, env3, env4, arrenv;
env1 = [[rrand(0.41, 0.53), rrand(0.33, 0.43), rrand(0.1, 0.2)].wchoose([5,4,1].normalizeSum), 
rrand(0.38, 0.43), rrand(0.95, 1.0), rrand(1.7,2.7), [-4, -3].wchoose([4,3].normalizeSum)];//normal
env2 = [rrand(1.9, 2.65), rrand(0.298, 0.303), rrand(0.9, 1.0), rrand(1.63,2.82), [4, 3].wchoose([1,1].normalizeSum)]; //slow attack
env4 = [rrand(0.1, 0.2), rrand(0.2, 0.1), rrand(0.95, 1.2), rrand(1.5, 2.0), [-6, -7].choose];
//medium rise cubic
arrenv = [env1,env2, env4].wchoose([7,2,1].normalizeSum); //weighted choose
});
	//fftmedium
	makenv4 = Array.fill(8, {var env1, env2, env3, env4, arrenv; 
env1 = [[rrand(0.45, 0.55), rrand(0.35, 0.45), rrand(0.35, 0.01)].wchoose([1,5,1].normalizeSum), 
rrand(0.398, 0.403), rrand(0.98, 1.05), rrand(1.8,2.6), [-4, -3].wchoose([5,4].normalizeSum)];//normal
env2 = [rrand(1.1, 2.55),rrand(0.398, 0.403), rrand(0.9, 1.0), rrand(3.6,3.8), [4, 3].wchoose([1.1,1].normalizeSum)]; //slow attack
env3 = [rrand(0.3, 0.01), rrand(0.1, 0.01), rrand(0.98, 1.05), rrand(1.18,1.26), [-2, -3].choose]; //fast attack
env4 = [rrand(0.1, 0.4), rrand(0.3, 0.1), rrand(0.95, 1.0), rrand(3.5,3.1), [1, 2].choose];
//medium rise linear
arrenv = [env1,env2, env3, env4].wchoose([5,3,2,1].normalizeSum); //weighted choice
});

	
atk = 0;
dec = 1;
sus = 2;
rel = 3;
crv = 4;

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

~fft1 = NodeProxy.audio(s, 1);
~fft1.play(sopranoOut);
~fft2 = NodeProxy.audio(s, 1);
~fft2.play(alto1Out);
~fft3 = NodeProxy.audio(s, 1);
~fft3.play(alto2Out);
~fft4 = NodeProxy.audio(s, 1);
~fft4.play(tenorOut);
~fft5 = NodeProxy.audio(s, 1);
~fft5.play(bassOut);
~fft6 = NodeProxy.audio(s, 1); 
~fft6.play(pianoOut); 

}	

	clearproxys {
	~soprano.clear;
	~alto1.clear;
	~alto2.clear;
	~tenor.clear;
	
	~fft1.clear;
	~fft2.clear;
	~fft3.clear;
	~fft4.clear;
	~fft5.clear;
	~fft6.clear;
	envelopeControl.clear;
}	
			
			
	load {
Routine({		
		1.do{ var time1 = 0.25;
		 time1.yield;	
partials = PartialTrackerFile((documentPath ++ "/samples/source/Chopin Op.25 No1 _1"), 2, 4, 256, 6,0,1,2);

partials2 = PartialTracker(512, 6, 113, 114, 115); 

		time1.yield;
~soprano.put(0, \partialthresh2, 0, [\fftbuf, 0, \magbuf, 1, \soundbuf, 4, \freqbuf, 2, \num, 6, \bins, 0.0]);
time1.yield;

		time1.yield;
		partials.getarrays;
		'READY TO START'.postln;

}; 
		
		}).play;
}
		
		
	
midicontrol {arg ipAddress = "169.254.80.149";
var inter, source,inter2, source2,inter3, source3;
var spec1, spec2, spec3, spec4;

spec1 = ControlSpec(0.ampdb, 1.ampdb, \db, units: " dB");
spec2 = ControlSpec(0.0, 1.0, 'lin');
spec3 = ControlSpec(0.0001, 1.0, 'exp');
spec4 = ControlSpec(-90, 6, \db, units: " dB");

n = NetAddr(ipAddress, 57120);

MIDIIn.noteOn = { arg src, chan, num, vel; 	

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
		{~soprano.set(\pianoVol, spec2.map(val/127.0))} //pianoVol (63) 
	); 
	if(num == 2,
		{~soprano.set(\thresh, spec3.map(val/127.0))} //thresh (63) 
	); 
	if(num == 8, {
	s.volume_(spec4.map(val/127.0).round(0.1));
	}); //master volume
	
	
	
//volume control
	
	if(num == 81,
		{~soprano.set(\globamp, ~globamp1 = spec1.map(val/127.0).dbamp);
		~fft1.set(\globamp, spec1.map(val/127.0).dbamp)} //volume soprano
	); 
	if(num == 82,
		{~alto1.set(\globamp, ~globamp2 = spec1.map(val/127.0).dbamp);
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
		{this.finish}
	);
});	
	
};

}

finish {	
		//partials.stoptask;
		this.clearproxys;
		s.freeAll; 
		s.bufFreeRange(0,90); 
		s.queryAllNodes;
		currentEnvironment.clear;

}

	initKnobs {
	
	////knobs
	control.control(0, 1, 63); //pianoVol
	control.control(0, 2, 59); //thresh
	
	control.control(0, 7, 40); //vol pianoruido
	control.control(0, 8, 90); //vol main

	//sliders
	control.control(0, 81, 63); //chan1 (soprano)
	control.control(0, 82, 54); //chan2 (alto1)
	control.control(0, 83, 90); //chan3 (alto2)
	control.control(0, 84, 80); //chan4 (tenor)
	control.control(0, 85, 80); //chan5 (bass)
	control.control(0, 86, 80); //chan6 (piano)
	
	//set inicial volumes
	~soprano.set(\globamp, ~globamp1 = 0.24607849215698);
	~alto1.set(\globamp, ~globamp2 = 0.18079236158472);
	~alto2.set(\globamp, ~globamp3 = 0.50220100440201);
	~tenor.set(\globamp, ~globamp4 = 0.50220100440201);

	~fft1.set(\globamp, 0.39680079360159);
	~fft2.set(\globamp, 0.39680079360159);
	~fft3.set(\globamp, 0.39680079360159);
	~fft4.set(\globamp, 0.39680079360159);
	~fft5.set(\globamp, ~globamp5 = 0.39680079360159);
	~fft6.set(\globamp, ~globamp6 = 0.39680079360159);		
}

	begining { 
	'S/ '.post; ~step1 = 0;
	'A1/ '.post; ~step2 = 0;
	'A2/ '.post; ~step3 = 0;
	'T/ '.post; ~step4 = 0;
	'B/ '.post; ~step5 = 0;

	//array for FFT
	array1 = Array.fill(512, 0);
	array2 = Array.fill(512, 0);
	array3 = Array.fill(512, 0);
	array4 = Array.fill(512, 0);
	array5 = Array.fill(512, 0);
	array6 = Array.fill(512, 0);
	arraySteps = Array.fill(512, 1);
	
	//fill buffers with arrays
	buff1.setn(0, array1);
	buff2.setn(0, array2);
	buff3.setn(0, array3);
	buff4.setn(0, array4);
	buff5.setn(0, array5);
	buff6.setn(0, array6);
	buffSteps.setn(0, arraySteps);
	
this.initKnobs;
	
~soprano.add(\celestaEtudes3, 0, [\pitch, 440, \amp, 0, \out, 0]); 
~soprano.add(\attackdetect, 0, [\thresh, 0.007215521357901]); 

~alto2.put(0, \footstep, 0, [\fftbufA, 23, \fftbufB, 24, \vol, 0, \rate, 0.7.randDifMul, \soundbuf, 49, \atk, makenv1[0][atk], \dec, makenv1[0][dec], \sus, makenv1[0][sus], \rel, makenv1[0][rel], \crv, makenv1[0][crv]]);
~alto2.add(\footstep2, 0, [\vol, 0, \rate, 0.8.randDifMul, \fftbufA, 27, \fftbufB, 28, \soundbuf, 29, \atk, makenv1[0][atk], \dec, makenv1[0][dec], \sus, makenv1[0][sus], \rel, makenv1[0][rel], \crv, makenv1[0][crv]]);	
envelopeControl = NodeProxy.control(s, 1); //main sound
envelopeControl.source = 0.0;
~alto2.map(\env, envelopeControl);	

//fft process
~fft1.put(0, \purenoiseFFT, 0, [\fftbuf, 10, \bufnum, 17]);
~fft2.put(0, \purenoiseFFT, 0, [\fftbuf, 11, \bufnum, 18]);
~fft3.put(0, \purenoiseFFT, 0, [\fftbuf, 12, \bufnum, 19]);
~fft4.put(0, \purenoiseFFT, 0, [\fftbuf, 13, \bufnum, 20]);
~fft5.put(0, \purenoiseFFT, 0, [\fftbuf, 14, \bufnum, 21]);
~fft6.put(0, \purenoiseFFT, 0, [\fftbuf, 15, \bufnum, 22]);


}


//rehearsal marks

rehearsalA {
	'S/ '.post; ~step1 = 36;
	'A1/ '.post; ~step2 = 36;
	'A2/ '.post; ~step3 = 38;
	'T/ '.post; ~step4 = 38;
	'B/ '.post; ~step5 = 38;
	
		//array for FFT
	array1 = Array.fill(512, 0);
	array2 = Array.fill(512, 0);
	array3 = Array.fill(512, 0);
	array4 = Array.fill(512, 0);
	array5 = Array.fill(512, 0);
	array6 = Array.fill(512, 0);
	arraySteps = Array.fill(512, 1);
	
	//fill buffers with arrays
	buff1.setn(0, array1);
	buff2.setn(0, array2);
	buff3.setn(0, array3);
	buff4.setn(0, array4);
	buff5.setn(0, array5);
	buff6.setn(0, array6);
	buffSteps.setn(0, arraySteps);
	
	this.initKnobs;
	
~soprano.add(\celestaEtudes3, 0, [\pitch, 440, \amp, 0, \out, 0]); 
~soprano.add(\attackdetect, 0, [\thresh, 0.007215521357901]); 

~alto2.put(0, \footstep, 0, [\fftbufA, 23, \fftbufB, 24, \vol, 0, \rate, 0.7.randDifMul, \soundbuf, 49, \atk, makenv1[0][atk], \dec, makenv1[0][dec], \sus, makenv1[0][sus], \rel, makenv1[0][rel], \crv, makenv1[0][crv]]);
~alto2.add(\footstep2, 0, [\vol, 0, \rate, 0.8.randDifMul, \fftbufA, 27, \fftbufB, 28, \soundbuf, 29, \atk, makenv1[0][atk], \dec, makenv1[0][dec], \sus, makenv1[0][sus], \rel, makenv1[0][rel], \crv, makenv1[0][crv]]);	
envelopeControl = NodeProxy.control(s, 1); //main sound
envelopeControl.source = 0.0;
~alto2.map(\env, envelopeControl);	

//fft process
~fft1.put(0, \purenoiseFFT, 0, [\fftbuf, 10, \bufnum, 17]);
~fft2.put(0, \purenoiseFFT, 0, [\fftbuf, 11, \bufnum, 18]);
~fft3.put(0, \purenoiseFFT, 0, [\fftbuf, 12, \bufnum, 19]);
~fft4.put(0, \purenoiseFFT, 0, [\fftbuf, 13, \bufnum, 20]);
~fft5.put(0, \purenoiseFFT, 0, [\fftbuf, 14, \bufnum, 21]);
~fft6.put(0, \purenoiseFFT, 0, [\fftbuf, 15, \bufnum, 22]);

~soprano.set(\start, 0.0);
~alto1.put(0, \sineflute, 0, [\freq, "a#3".notemidi.midijust(415), \amp, 0.3, \lag, 1.6]);
~alto1_1 = ~alto1.objects[0];
~alto1.add(\sineflute, 0, [\freq, "b3".notemidi.midijust(415), \amp, 0.3, \lag, 1.6]);
~alto1_2 = ~alto1.objects[1];

}

rehearsalB {
	'S/ '.post; ~step1 = 70;
	'A1/ '.post; ~step2 = 80;
	'A2/ '.post; ~step3 = 88;
	'T/ '.post; ~step4 = 68;
	'B/ '.post; ~step5 = 38;
	
		//array for FFT
	array1 = Array.fill(512, 0);
	array2 = Array.fill(512, 0);
	array3 = Array.fill(512, 0);
	array4 = Array.fill(512, 0);
	array5 = Array.fill(512, 0);
	array6 = Array.fill(512, 0);
	arraySteps = Array.fill(512, 1);
	
	//fill buffers with arrays
	buff1.setn(0, array1);
	buff2.setn(0, array2);
	buff3.setn(0, array3);
	buff4.setn(0, array4);
	buff5.setn(0, array5);
	buff6.setn(0, array6);
	buffSteps.setn(0, arraySteps);
	
	this.initKnobs;
	
~soprano.add(\celestaEtudes3, 0, [\pitch, 440, \amp, 0, \out, 0]); 
~soprano.add(\attackdetect, 0, [\thresh, 0.007215521357901]); 

~alto2.put(0, \footstep, 0, [\fftbufA, 23, \fftbufB, 24, \vol, 0, \rate, 0.7.randDifMul, \soundbuf, 49, \atk, makenv1[0][atk], \dec, makenv1[0][dec], \sus, makenv1[0][sus], \rel, makenv1[0][rel], \crv, makenv1[0][crv]]);
~alto2.add(\footstep2, 0, [\vol, 0, \rate, 0.8.randDifMul, \fftbufA, 27, \fftbufB, 28, \soundbuf, 29, \atk, makenv1[0][atk], \dec, makenv1[0][dec], \sus, makenv1[0][sus], \rel, makenv1[0][rel], \crv, makenv1[0][crv]]);	
envelopeControl = NodeProxy.control(s, 1); //main sound
envelopeControl.source = 0.0;
~alto2.map(\env, envelopeControl);	

//fft process
~fft1.put(0, \purenoiseFFT, 0, [\fftbuf, 10, \bufnum, 17]);
~fft2.put(0, \purenoiseFFT, 0, [\fftbuf, 11, \bufnum, 18]);
~fft3.put(0, \purenoiseFFT, 0, [\fftbuf, 12, \bufnum, 19]);
~fft4.put(0, \purenoiseFFT, 0, [\fftbuf, 13, \bufnum, 20]);
~fft5.put(0, \purenoiseFFT, 0, [\fftbuf, 14, \bufnum, 21]);
~fft6.put(0, \purenoiseFFT, 0, [\fftbuf, 15, \bufnum, 22]);

~soprano.set(\start, 0.0);
~alto1.put(0, \sineflute, 0, [\freq, "a#3".notemidi.midijust(415), \amp, 0.3, \lag, 1.6]);
~alto1_1 = ~alto1.objects[0];
~alto1.add(\sineflute, 0, [\freq, "b3".notemidi.midijust(415), \amp, 0.3, \lag, 1.6]);
~alto1_2 = ~alto1.objects[1];
envelopeControl.env(Env.new([0, 1], [makenv1[2][atk]], makenv1[2][crv])); //attack
	index6 = (66, 67.. 511);
	index6 = index6.scramble;

		
	}

rehearsalC {'S/ '.post; ~step1 = 106;
'A1/ '.post; ~step2 = 116;
'A2/ '.post; ~step3 = 126;
'T/ '.post; ~step4 = 106;
'B/ '.post; ~step5 = 76;

	//array for FFT
	array1 = Array.fill(512, 0);
	array2 = Array.fill(512, 0);
	array3 = Array.fill(512, 0);
	array4 = Array.fill(512, 0);
	array5 = Array.fill(512, 0);
	array6 = Array.fill(512, 0);
	arraySteps = Array.fill(512, 1);
	
	//fill buffers with arrays
	buff1.setn(0, array1);
	buff2.setn(0, array2);
	buff3.setn(0, array3);
	buff4.setn(0, array4);
	buff5.setn(0, array5);
	buff6.setn(0, array6);
	buffSteps.setn(0, arraySteps);
	
	this.initKnobs;
	
~alto2.put(0, \footstep, 0, [\fftbufA, 23, \fftbufB, 24, \vol, 0, \rate, 0.7.randDifMul, \soundbuf, 49, \atk, makenv1[0][atk], \dec, makenv1[0][dec], \sus, makenv1[0][sus], \rel, makenv1[0][rel], \crv, makenv1[0][crv]]);
~alto2.add(\footstep2, 0, [\vol, 0, \rate, 0.8.randDifMul, \fftbufA, 27, \fftbufB, 28, \soundbuf, 29, \atk, makenv1[0][atk], \dec, makenv1[0][dec], \sus, makenv1[0][sus], \rel, makenv1[0][rel], \crv, makenv1[0][crv]]);	
envelopeControl = NodeProxy.control(s, 1); //main sound
envelopeControl.source = 0.0;
~alto2.map(\env, envelopeControl);	

//fft process
~fft1.put(0, \purenoiseFFT, 0, [\fftbuf, 10, \bufnum, 17]);
~fft2.put(0, \purenoiseFFT, 0, [\fftbuf, 11, \bufnum, 18]);
~fft3.put(0, \purenoiseFFT, 0, [\fftbuf, 12, \bufnum, 19]);
~fft4.put(0, \purenoiseFFT, 0, [\fftbuf, 13, \bufnum, 20]);
~fft5.put(0, \purenoiseFFT, 0, [\fftbuf, 14, \bufnum, 21]);
~fft6.put(0, \purenoiseFFT, 0, [\fftbuf, 15, \bufnum, 22]);


index6 = (66, 67.. 511);
index6 = index6.scramble;

Routine({var count=0;
32.do({
array1 = array1.put(index6[count], 0);
array2 = array2.put(index6[count], 0);
array6 = array6.put(index6[count], 1);
buff1.setn(0, array1);
buff2.setn(0, array2);
buff6.setn(0, array6);
0.001.yield;
count = count + 1;
});
0.6.yield;
envelopeControl.env(Env.new([0, 1], [makenv1[3][atk]], makenv1[3][crv])); //attack
'done'.postln;
}).play;

ffttime = 1.7/6;

~fft3.set(\gates, 1, \atk, makenv2[5][atk], \dec, makenv2[5][dec], \sus, makenv2[5][sus], \rel, makenv2[5][rel], \crv, makenv2[5][crv]);
	
//filter for steps
~alto2[2] = \filter -> { arg in, bufnum=9, fftbuf = 8;
var chain, signal, signal2;
chain = FFT(fftbuf, in);
chain = chain.pvcollect(1024, {|mag, phase, index|
[mag*Index.kr(bufnum, index), phase];
}, frombin: 0, tobin: 250, zeroothers: 1);	
signal = Mix.ar(0.5 * IFFT(chain).dup); };
	
~fft5.set(\adjVol, ~step5.linlin(39,76,2.0,1.0));

newbin = rrand(0,4);

array1.put(newbin, 0);
array2.put(newbin, 0);
array5.put(newbin, 1);

buff1.setn(0, array1);
buff2.setn(0, array2);
buff5.setn(0, array5);
	
~fft5.set(\atk, makenv3[0][atk], \dec, makenv3[0][dec], \sus, makenv3[0][sus], \rel, makenv3[0][rel], \crv,makenv3[0][crv]);
}	

rehearsalD {	'S/ '.post; ~step1 = 170;
			'A1/ '.post; ~step2 = 178;
			'A2/ '.post; ~step3 = 184;
			'T/ '.post; ~step4 = 160;
			'B/ '.post; ~step5 = 106;

	//array for FFT
	array1 = Array.fill(512, 0);
	array2 = Array.fill(512, 0);
	array3 = Array.fill(512, 0);
	array4 = Array.fill(512, 0);
	array5 = Array.fill(512, 0);
	array6 = Array.fill(512, 0);
	arraySteps = Array.fill(512, 1);
	
	//fill buffers with arrays
	buff1.setn(0, array1);
	buff2.setn(0, array2);
	buff3.setn(0, array3);
	buff4.setn(0, array4);
	buff5.setn(0, array5);
	buff6.setn(0, array6);
	buffSteps.setn(0, arraySteps);
	
	this.initKnobs;
	
~alto2.put(0, \footstep, 0, [\fftbufA, 23, \fftbufB, 24, \vol, 0, \rate, 0.7.randDifMul, \soundbuf, 49, \atk, makenv1[0][atk], \dec, makenv1[0][dec], \sus, makenv1[0][sus], \rel, makenv1[0][rel], \crv, makenv1[0][crv]]);
~alto2.add(\footstep2, 0, [\vol, 0, \rate, 0.8.randDifMul, \fftbufA, 27, \fftbufB, 28, \soundbuf, 29, \atk, makenv1[0][atk], \dec, makenv1[0][dec], \sus, makenv1[0][sus], \rel, makenv1[0][rel], \crv, makenv1[0][crv]]);	
envelopeControl = NodeProxy.control(s, 1); //main sound
envelopeControl.source = 0.0;
~alto2.map(\env, envelopeControl);	

//fft process
~fft1.put(0, \purenoiseFFT, 0, [\fftbuf, 10, \bufnum, 17]);
~fft2.put(0, \purenoiseFFT, 0, [\fftbuf, 11, \bufnum, 18]);
~fft3.put(0, \purenoiseFFT, 0, [\fftbuf, 12, \bufnum, 19]);
~fft4.put(0, \purenoiseFFT, 0, [\fftbuf, 13, \bufnum, 20]);
~fft5.put(0, \purenoiseFFT, 0, [\fftbuf, 14, \bufnum, 21]);
~fft6.put(0, \purenoiseFFT, 0, [\fftbuf, 15, \bufnum, 22]);


index6 = (66, 67.. 511);
index6 = index6.scramble;

Routine({var count=0;
32.do({
array1 = array1.put(index6[count], 0);
array2 = array2.put(index6[count], 0);
array6 = array6.put(index6[count], 1);
buff1.setn(0, array1);
buff2.setn(0, array2);
buff6.setn(0, array6);
0.001.yield;
count = count + 1;
});
0.6.yield;
envelopeControl.env(Env.new([0, 1], [makenv1[3][atk]], makenv1[3][crv])); //attack
'done'.postln;
}).play;

ffttime = 1.7/6;

~fft3.set(\gates, 1, \atk, makenv2[5][atk], \dec, makenv2[5][dec], \sus, makenv2[5][sus], \rel, makenv2[5][rel], \crv, makenv2[5][crv]);
	
//filter for steps
~alto2[2] = \filter -> { arg in, bufnum=9, fftbuf = 8;
var chain, signal, signal2;
chain = FFT(fftbuf, in);
chain = chain.pvcollect(1024, {|mag, phase, index|
[mag*Index.kr(bufnum, index), phase];
}, frombin: 0, tobin: 250, zeroothers: 1);	
signal = Mix.ar(0.5 * IFFT(chain).dup); };
	
	

~fft5.set(\adjVol, ~step5.linlin(39,76,2.0,1.0));

newbin = rrand(0,4);

array1.put(newbin, 0);
array2.put(newbin, 0);
array5.put(newbin, 1);

buff1.setn(0, array1);
buff2.setn(0, array2);
buff5.setn(0, array5);
	
~fft5.set(\atk, makenv3[0][atk], \dec, makenv3[0][dec], \sus, makenv3[0][sus], \rel, makenv3[0][rel], \crv,makenv3[0][crv]);
}	

rehearsalFinal { //last section
			'S/ '.post; ~step1 = 334;
			'A1/ '.post; ~step2 = 334;
			'A2/ '.post; ~step3 = 348;
			'T/ '.post; ~step4 = 304;
			'B/ '.post; ~step5 = 228;
			
			this.initKnobs;
			
}


	
	//write Synth Defs 
	*initClass {
	
	SynthDef.writeOnce("purenoiseFFT", {|out=0, bufnum=17, fftbuf = 10, inBus = 30, globamp=1, amp=1, atk = 0.476, dec = 0.399, sus = 0.980, rel = 2.33, crv = -4, gates = 1, adjVol = 1|
	var in, chain, v, signal, signal2, env;
	env = EnvGen.ar(Env.adsr(atk, dec, sus, rel, 0.8, crv), gates);
	in = AudioIn.ar(2);
	chain = FFT(fftbuf, in);
	chain = chain.pvcollect(1024, {|mag, phase, index|
		[mag*Index.kr(bufnum, index), phase];
	}, frombin: 0, tobin: 250, zeroothers: 1);	
	signal = Mix.ar(0.5 * IFFT(chain).dup);
	signal2 = (signal*4*Lag.kr(amp, 1.6))*env;
	Out.ar(out, (signal2*Lag.kr(adjVol, 1.6))*globamp);
	});
	
	
	SynthDef.writeOnce("celestaEtudes3", {|pitch = 440, amp = 0.45, out = 0, pianoVol = 0.5, globamp = 1.0, atk=0.0001, dec=0.35, amp2=1|
	var env, env2, signal, signal2, vol;
	env = EnvGen.kr(Env.perc(atk, dec, 1),1, doneAction: 0);
	env2 = EnvGen.kr(Env.linen(atk, 1.9, 0.001, 1),1, doneAction: 2);
	vol = (pitch/440).sqrt * amp;
	signal =	Klank.ar(`[[866.700604, 816.110913, 766.459488, 723.324936, 687.941053, 571.619871, 541.390254, 497.778836, 440.777164, 399.120127, 306.889295, 266.425749, 150.200487, 122.724922, 95.7782989, 50.0718679]*(pitch/440), [0.000178, 0.000125, 0.000147, 0.00024, 0.000121, 0.0000963, 0.000153, 0.0000523, 0.015221, 0.0000634, 0.00142, 0.000276, 0.000272, 0.000641, 0.000171, 0.00028344], [0.09, 0.09, 0.11, 0.07, 0.13, 0.27, 0.23, 1.78, 1.84, 1.81, 1.84, 0.22, 1.84, 0.17, 1.46, 1.83]
			], Decay.ar(AudioIn.ar(2, pianoVol)*env));
			signal2 = signal * (2 * env2 * vol) * Lag.kr(amp2, 1.6);
			Out.ar(out, signal2 * globamp);
	});
	
	SynthDef.writeOnce(\attackdetect,
	{arg fftbuf = 6, thresh = 0.4;
	 var source1, detect, rate, info, rateTime=4;
	rate = Impulse.kr(rateTime);
	source1= AudioIn.ar(1); 
	detect= PV_JensenAndersen.ar(FFT(fftbuf ,source1), threshold: thresh);
	info = Decay.ar(0.1*detect,0.01);
	SendTrig.kr(rate, 1, info);	
	});	
	
	SynthDef.writeOnce("sineflute", { arg gate = 1, amp=0.5, out = 0, gates3 = 0.0, gates = 1.0, globamp = 1.0, delay = 0.2, freq = 400, lag=0.1;
	var signal, signal2, eg, fc, osc, a, b, w, env, caos, atk, dec;
	caos = ((CuspL.ar(2, 1.2, 1.8, 0.3)).abs);
	atk = (caos * 0.2) + 2.0;
	dec = (caos * 0.6) + 2.0;
	env =  EnvGen.ar(Env.asr(atk, 1.0, dec, -5), gates, doneAction:0); 
	//env2 =  EnvGen.ar(Env.adsr(atk, atk,dec, dec, 1, -5), gates3, doneAction:0); 
	fc = LinExp.kr(LFNoise1.kr(Rand(0.25,0.4)), -1,1,500,2000);
	osc = Mix.fill(8, {SinOsc.ar(Lag2.kr(freq, lag), 0, 0.1) }).clip2(1) * 0.2;
	eg = EnvGen.kr(Env.asr(1,1,1), gate, doneAction:2);
	signal = eg * RLPF.ar(osc, fc, 0.1);
	signal2 = (Mix.ar(signal)) * Lag.kr(amp, 1.6) * env;
	Out.ar(out, signal2*globamp);
	});

	
SynthDef.writeOnce("footstep", {arg vol=0.5, rate=0.6, rate2=1.0, start=0.0, fftbufA=0, fftbufB=1, soundbuf=3, ruidoVol=0.5, globamp=1, env=1, out=0;
	var in, chain, in2, chain2, signal, signal2;
	in = PlayBuf.ar(1, soundbuf, rate, 1, start, loop: 0);
	chain = FFT(fftbufA, in);
		
	in2 = AudioIn.ar(2)*ruidoVol;
	chain2 = FFT(fftbufB, in2);
	
	chain = chain.pvcalc2(chain2, 1024, {|mags, phases, mags2, phases2|
		[mags, phases2]
	}, frombin: 0, tobin: 125, zeroothers: 0);
	signal = IFFT(chain);
	signal2 = Mix.ar(signal.dup) * vol;
	DetectSilence.ar(signal2, doneAction: 2);
	Out.ar(out, signal2*env*globamp);
});


SynthDef.writeOnce("footstep2", {arg vol=0.5, rate=1.0, rate2=1.5, start=0.0, fftbufA=0, fftbufB=1, soundbuf=2, ruidoVol=0.5, globamp=1, env=1, out=1;
	var in, chain, in2, chain2, signal, signal2;
	
	in = PlayBuf.ar(1, soundbuf,rate, 1, start, loop: 0);
	chain = FFT(fftbufA, in);

	in2 = AudioIn.ar(2)*ruidoVol;
	chain2 = FFT(fftbufB, in2);
	
	chain = chain.pvcalc2(chain2, 1024, {|mags, phases, mags2, phases2|
		[mags*mags2, phases2 + phases]
	}, frombin: 0, tobin: 125, zeroothers: 0);
	
	signal = IFFT(chain);
	signal2 = Mix.ar(signal.dup) * vol;
	DetectSilence.ar(signal2, doneAction: 2);
	Out.ar(out, signal2*env*globamp);
});

SynthDef.writeOnce("monoOutBuf", {arg buffer=91, vol=1, globamp=1, out=0;
var signal; 
signal = PlayBuf.ar(1, buffer);
Out.ar(out, signal*vol*globamp);
});

SynthDef.writeOnce("stereoOutBuf", {arg buffer=91, vol=1, globamp=1, out=0;
var signal; 
signal = PlayBuf.ar(2, buffer);
Out.ar(out, signal*vol*globamp);
})

}
}