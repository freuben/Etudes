PianoRuidoLive {
	var <>in1, <>in2, <>in3, <>interp, <> source, y1, y2, y3, <>volume, osc, net;
	var a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, s, <>x, <>dirIn;
	
	*new {arg in1=5, in2=6, in3=7, interp = 2.5, source = 5, volume = 1.0;
		^super.new.initPianoRuidoLive(in1,in2,in3,interp, source, volume);
	}
	
	initPianoRuidoLive {arg audioIn1=5, audioIn2=6, audioIn3=7, sourcePiano = 5, interPiano = 2.5, volPiano=1.0;
	s = Server.default;
	interp = interPiano;
	source = sourcePiano;
	volume = volPiano;
	in1 = audioIn1;
	in2 = audioIn2;
	in3 = audioIn3;
	MIDIIn.allsources;
		
a = Buffer.alloc(s, 44100 *30, 1);
b = Buffer.alloc(s, 44100 *15, 1);
c = Buffer.alloc(s, 44100 *45, 1);
d = Buffer.alloc(s, 44100 *60, 1);
e = Buffer.alloc(s, 44100 *75, 1);
f = Buffer.alloc(s, 44100 *30, 1);
g = Buffer.alloc(s, 44100 *15, 1);
h = Buffer.alloc(s, 44100 *45, 1);
i = Buffer.alloc(s, 44100 *60, 1);
j = Buffer.alloc(s, 44100 *75, 1);
k = Buffer.alloc(s, 44100 *30, 1);
l = Buffer.alloc(s, 44100 *15, 1);
m = Buffer.alloc(s, 44100 *45, 1);
n = Buffer.alloc(s, 44100 *60, 1);
o = Buffer.alloc(s, 44100 *75, 1);
		
					}
			
	startsynth {
	
y1 = Synth.head(s, "live-shift1", [\in, in1]);
Synth.tail(s, "live-play1", [\bufnum, a.bufnum, \rate, 1.015, \pan, -1]);
Synth.tail(s, "live-play1", [\bufnum, b.bufnum, \pan,  1, \rate, 1.0125]);
Synth.tail(s, "live-play1", [\bufnum, c.bufnum, \pan, 0.01, \rate, 1.01]);
Synth.tail(s, "live-play1", [\bufnum, d.bufnum, \pan, 0.533, \rate, 0.995]);
Synth.tail(s, "live-play1", [\bufnum, e.bufnum, \pan, -0.6, \rate, 0.9975]);

y2 = Synth.head(s, "live-shift2", [\in, in2]);
Synth.tail(s, "live-play2", [\bufnum, f.bufnum, \rate, 1.015, \pan, -1]);
Synth.tail(s, "live-play2", [\bufnum, g.bufnum, \pan,  1, \rate, 1.0125]);
Synth.tail(s, "live-play2", [\bufnum, h.bufnum, \pan, 0.01, \rate, 1.01]);
Synth.tail(s, "live-play2", [\bufnum, i.bufnum, \pan, 0.533, \rate, 0.995]);
Synth.tail(s, "live-play2", [\bufnum, j.bufnum, \pan, -0.6, \rate, 0.9975]);


y3 = Synth.head(s, "live-shift3", [\in, in3]);
Synth.tail(s, "live-play3", [\bufnum, k.bufnum, \rate, 1.015, \pan, -1]);
Synth.tail(s, "live-play3", [\bufnum, l.bufnum, \pan,  1, \rate, 1.0125]);
Synth.tail(s, "live-play3", [\bufnum, m.bufnum, \pan, 0.01, \rate, 1.01]);
Synth.tail(s, "live-play3", [\bufnum, n.bufnum, \pan, 0.533, \rate, 0.995]);
Synth.tail(s, "live-play3", [\bufnum, o.bufnum, \pan, -0.6, \rate, 0.9975]);

	}
		
		
	ruido {arg int = interp, sour = source, vol = volume;
	
x = Synth.tail(s, "XenakisInterp", [\interpolation, int, \source, sour, \intervol, vol]);

	}
	
	ruidoLive {arg int = interp, sour = source, vol = volume;
	
x = Synth.tail(s, "XenakisInterp", [\interpolation, int, \source, sour, \intervol, vol]);
dirIn = Synth.tail(s, "DirInterpLive", [\interpolation, int, \source, sour, \intervol, vol, \audioIn1, in1, \audioIn2, in2 ,\audioIn3, in3]);
}
		
	setSource {arg sour = 5;
source = sour;
x.set(\source, sour);
dirIn.set(\source, sour);

}	

	setInterp {arg int = 2.5;
	interp = int;
x.set(\interpolation, int);	
dirIn.set(\interpolation, int);		
}
	
	setPianosVol {arg intervol = 2.5;

y1.set(\globamp, intervol);
y2.set(\globamp, intervol);
y3.set(\globamp, intervol);	
	
}

	setVol {arg vol = 1.0;
	volume = vol;

x.set(\intervol, vol);	
//dirIn.set(\intervol, vol);	
	
	}
	
	wakePianos {
	
Routine({3.do({
	MIDIClient.sources.size.do({|i| MIDIOut(0, MIDIClient.destinations[i].uid).allNotesOn });
	1.0.yield;
	});
	}).play;	
	
}
	
	network {arg ipAddress = "169.254.157.224";
	osc = OSCresponder(NetAddr(ipAddress, 57120), '/midi', { arg time, resp, msg; 
var spec = ControlSpec(0.001, 10, \exponential);
var spec2 = ControlSpec(0.0.ampdb, 10.ampdb, \db, units: "dB");
//msg.postln;
	if(msg[1] == 87, 
		{'interp: '.post; 
		x.set(\interpolation, spec.map(msg[2]/127.0).postln); 
		dirIn.set(\interpolation, spec.map(msg[2]/127.0).postln); 
		interp = spec.map(msg	[2]/127.0)};
	);
	if(msg[1] == 88, 
		{'source: '.post; 
		x.set(\source, spec.map(msg[2]/127.0).postln;); 
		dirIn.set(\source, spec.map(msg[2]/127.0).postln;); 
		source = spec.map(msg[2]/127.0)};
	);	
	if(msg[1] == 7, 
		{'vol: '.post; 
		x.set(\intervol, spec2.map(msg[2]/127.0).postln); 
		dirIn.set(\intervol, spec2.map(msg[2]/127.0).postln); 
		volume = spec2.map(msg[2]/127.0)};
	);
	if(msg[1] == 70, 
		{x.set(\interpolation, 2.5, \source, 5); 
		dirIn.set(\interpolation, 2.5, \source, 5); 
		'interp: '.post; 2.5.postln; 
		'source: '.post; 5.postln; };
	);
	if(msg[1] == 78, 
		{x.set(\interpolation, 7, \source, 3); 
		dirIn.set(\interpolation, 7, \source, 3); 
		'interp: '.post; 4.postln; 
		'source: '.post; 10.postln; };
	);
	if(msg[1] == 71, 
		{x.set(\interpolation, 0.0080, \source, 0.1);
		dirIn.set(\interpolation, 0.0080, \source, 0.1);
		'interp: '.post; 0.0080.postln; 
		'source: '.post; 0.1.postln; };
	);
	if((msg[1] == 79).or(msg[1] == 72).or(msg[1] == 80), 
		{'interp: '.post; 
		x.set(\interpolation, spec.map(msg[2]/127.0).postln);
		dirIn.set(\interpolation, spec.map(msg[2]/127.0).postln);
		};
	);
	if((msg[1] == 69).or(msg[1] == 62).or(msg[1] == 60),  
		{'source: '.post; 
		x.set(\source, spec.map(msg[2]/127.0).postln);
		dirIn.set(\source, spec.map(msg[2]/127.0).postln);
		};
	);
	
}).add;

//routine for headphones
Routine({inf.do({
x.set(\fadeHead, 0, \lag, (this.source + this.interp)*1.35);
dirIn.set(\fadeHead, 1, \lag, (this.source + this.interp)*1.35);
"normal: ".postln;
((this.source + this.interp)*15).postln.yield;
x.set(\fadeHead, 6.dbamp, \lag, (this.source + this.interp)*1.35);
dirIn.set(\fadeHead, 0, \lag, (this.source + this.interp)*1.35);
"noise: ".postln;
((this.source + this.interp)*3).postln.yield;
});
}).play;	
	
	}
	
	freenet {
	osc.remove;
	
	}
	
	
	//write Synth Defs 
	*initClass {
	
		SynthDef.writeOnce("live-shift1", 
{arg bufnum, rate = 1, pan = 0.0, globamp = 1.0, in=5;
var signal, signal2, output;
signal = Mix.new(PitchShift.ar(
		AudioIn.ar(in)*globamp,	// stereo audio input
		0.1, 			// grain size
		[1.3333,1.25, 1.1111,1.19999,45/44],	// mouse x controls pitch shift ratio
		0, 				// pitch dispersion
		0.004			// time dispersion
	), pan);
signal2 = Mix.new(PitchShift.ar(
		signal,	// stereo audio input
		0.1, 			// grain size
		[1.3333,1.25, 1.1111,1.19999,45/44],	// mouse x controls pitch shift ratio
		0, 				// pitch dispersion
		0.004			// time dispersion
	), pan);
Out.ar(18, signal2);
	});
		
		SynthDef.writeOnce("live-play1",
{arg bufnum=0, rate = 1, pan;
var output;
RecordBuf.ar(In.ar(18,1), bufnum,  0, 1.0, 0.1, 1, 1, 0.1); Silent.ar;
output = PlayBuf.ar(1, bufnum, rate, loop: 1);
Out.ar(24, (output/5));	
	});
	
	SynthDef.writeOnce("live-shift2", 
{arg bufnum, rate = 1, pan = 0.0, globamp = 1.0, in=6;
var signal, signal2, output;
signal = Mix.new(PitchShift.ar(
		AudioIn.ar(in)*globamp,	// stereo audio input
		0.1, 			// grain size
		[1.3333,1.25, 1.1111,1.19999,45/44],	// mouse x controls pitch shift ratio
		0, 				// pitch dispersion
		0.004			// time dispersion
	), pan);
signal2 = Mix.new(PitchShift.ar(
		signal,	// stereo audio input
		0.1, 			// grain size
		[1.3333,1.25, 1.1111,1.19999,45/44],	// mouse x controls pitch shift ratio
		0, 				// pitch dispersion
		0.004			// time dispersion
	), pan);
Out.ar(20, signal2);
	});
		
	SynthDef.writeOnce("live-play2",
{arg bufnum=0, rate = 1, pan;
var output;
RecordBuf.ar(In.ar(20, 1), bufnum,  0, 1.0, 0.1, 1, 1, 0.1); Silent.ar;
output = PlayBuf.ar(1, bufnum, rate, loop: 1);
Out.ar(26, (output/5));
	});
	
	
	SynthDef.writeOnce("live-shift3", 
{arg bufnum, rate = 1, pan = 0.0, globamp = 1.0, in=7;
var signal, signal2, output;
signal = Mix.new(PitchShift.ar(
		AudioIn.ar(in)*globamp,	// stereo audio input
		0.1, 			// grain size
		[1.3333,1.25, 1.1111,1.19999,45/44],	// mouse x controls pitch shift ratio
		0, 				// pitch dispersion
		0.004			// time dispersion
	), pan);
signal2 = Mix.new(PitchShift.ar(
		signal,	// stereo audio input
		0.1, 			// grain size
		[1.3333,1.25, 1.1111,1.19999,45/44],	// mouse x controls pitch shift ratio
		0, 				// pitch dispersion
		0.004			// time dispersion
	), pan);
Out.ar(22, signal2);
	});
	
	SynthDef.writeOnce("live-play3",
{arg bufnum=0, rate = 1, pan;
var output;
RecordBuf.ar(In.ar(22,1), bufnum,  0, 1.0, 0.1, 1, 1, 0.1); Silent.ar;
output = PlayBuf.ar(1, bufnum, rate, loop: 1);
Out.ar(28, (output/5));
	});
	
	SynthDef.writeOnce("XenakisInterp", {|interpolation = 10, source = 5, intervol = 1.0, fadeHead=0, lag=10|
var vol1, vol2, vol3, vol4, finalSig; 
var interp = interpolation*(3/7), interpos = interpolation*(4/7);
var signal, signal1, signal2, signal3, sound1, sound2, sound3;
	sound1 = EnvGen.kr(Env.new([1,1,0.75,0.25,0.25,0.75,1], 
		[source, interp, interpos, ((2*source)+interpolation), interpos, interp], 'exponential'), 
		Impulse.kr(1/((3*interpolation)+(3*source))));
	sound2 = EnvGen.kr(Env.new([0.25,0.25,0.75,1,1,0.75,0.25,0.25], 
		[source, interpos, interp, source, interp, interpos, (source+interpolation)], 'exponential'),
		 Impulse.kr(1/((3*interpolation)+(3*source))));
	sound3 = EnvGen.kr(Env.new([0.25,0.25,0.75,1,1,0.75,0.25], 
		[((2*source)+interpolation), interpos,interp, source, interp, interpos], 'exponential'),
		 Impulse.kr(1/((3*interpolation)+(3*source))));
	vol1 = EnvGen.kr(Env.new([1,1,0.85,0.15,0,0.85,1], 
		[source, interp, interpos, ((2*source)+interpolation), interpos, interp], 'welch'), 
		Impulse.kr(1/((3*interpolation)+(3*source))));
	vol2 = EnvGen.kr(Env.new([0.15,0.15,0.85,1,1,0.85,0.15,0], 
		[source, interpos, interp, source, interp, interpos, (source+interpolation)], 'welch'),
		 Impulse.kr(1/((3*interpolation)+(3*source))));
	vol3 = EnvGen.kr(Env.new([0,0.15,0.85,1,1,0.85,0.15], 
		[((2*source)+interpolation), interpos,interp, source, interp, interpos], 'welch'),
		 Impulse.kr(1/((3*interpolation)+(3*source))));	signal1 =	
	PitchShift.ar(In.ar(24, 1),
	0.1, 			// grain size
		sound1,	// mouse x controls pitch shift ratio
		0, 				// pitch dispersion
		0.004			// time dispersion
	); 
	signal2 =
	PitchShift.ar(In.ar(26,1),
	0.1, 			// grain size
		sound2,	// mouse x controls pitch shift ratio
		0, 				// pitch dispersion
		0.001			// time dispersion
	);
	signal3 =
	PitchShift.ar(In.ar(28,1),
	0.1, 			// grain size
		sound3,	// mouse x controls pitch shift ratio
		0, 				// pitch dispersion
		0.001			// time dispersion
	);
	finalSig = ((signal1*(vol1))+(signal2*(vol2))+(signal3*(vol3)))*intervol;
		Out.ar(0, finalSig);
		Out.ar(2, Pan2.ar(finalSig, 0, Ramp.kr(fadeHead,lag))); //stereo for headphones
	});
	
	
	
		SynthDef.writeOnce("live-shift1-buffer", 
{arg bufnum, rate = 1, pan = 0.0, globamp = 1.0;
var signal, signal2, output;
signal = Mix.new(PitchShift.ar(
		Mix.ar(PlayBuf.ar(1, bufnum, 1,loop: 1))*globamp,	// stereo audio input
		0.1, 			// grain size
		[1.3333,1.25, 1.1111,1.19999,45/44],	// mouse x controls pitch shift ratio
		0, 				// pitch dispersion
		0.004			// time dispersion
	), pan);
signal2 = Mix.new(PitchShift.ar(
		signal,	// stereo audio input
		0.1, 			// grain size
		[1.3333,1.25, 1.1111,1.19999,45/44],	// mouse x controls pitch shift ratio
		0, 				// pitch dispersion
		0.004			// time dispersion
	), pan);
Out.ar(18, signal2);
	});
		
		SynthDef.writeOnce("live-shift2-buffer", 
{arg bufnum, rate = 1, pan = 0.0, globamp = 1.0;
var signal, signal2, output;
signal = Mix.new(PitchShift.ar(
		Mix.ar(PlayBuf.ar(1, bufnum, 1,loop: 1))*globamp,	// stereo audio input
		0.1, 			// grain size
		[1.3333,1.25, 1.1111,1.19999,45/44],	// mouse x controls pitch shift ratio
		0, 				// pitch dispersion
		0.004			// time dispersion
	), pan);
signal2 = Mix.new(PitchShift.ar(
		signal,	// stereo audio input
		0.1, 			// grain size
		[1.3333,1.25, 1.1111,1.19999,45/44],	// mouse x controls pitch shift ratio
		0, 				// pitch dispersion
		0.004			// time dispersion
	), pan);
Out.ar(20, signal2);
	});
			
	SynthDef.writeOnce("live-shift3-buffer", 
{arg bufnum, rate = 1, pan = 0.0, globamp = 1.0;
var signal, signal2, output;
signal = Mix.new(PitchShift.ar(
		Mix.ar(PlayBuf.ar(1, bufnum, 1,loop: 1))*globamp,	// stereo audio input
		0.1, 			// grain size
		[1.3333,1.25, 1.1111,1.19999,45/44],	// mouse x controls pitch shift ratio
		0, 				// pitch dispersion
		0.004			// time dispersion
	), pan);
signal2 = Mix.new(PitchShift.ar(
		signal,	// stereo audio input
		0.1, 			// grain size
		[1.3333,1.25, 1.1111,1.19999,45/44],	// mouse x controls pitch shift ratio
		0, 				// pitch dispersion
		0.004			// time dispersion
	), pan);
Out.ar(22, signal2);
	});		
	
	SynthDef.writeOnce("DirInterpLive", {|interpolation = 10, source = 5, intervol = 1.0, out1=0, out2=2, audioIn1=5, audioIn2=6, audioIn3=7, fadeHead=1, lag=10|
var vol1, vol2, vol3, vol4, finalSig; 
var interp = interpolation*(3/7), interpos = interpolation*(4/7);
var signal, signal1, signal2, signal3, sound1, sound2, sound3;
	sound1 = EnvGen.kr(Env.new([1,1,0.75,0.25,0.25,0.75,1], 
		[source, interp, interpos, ((2*source)+interpolation), interpos, interp], 'exponential'), 
		Impulse.kr(1/((3*interpolation)+(3*source))));
	sound2 = EnvGen.kr(Env.new([0.25,0.25,0.75,1,1,0.75,0.25,0.25], 
		[source, interpos, interp, source, interp, interpos, (source+interpolation)], 'exponential'),
		 Impulse.kr(1/((3*interpolation)+(3*source))));
	sound3 = EnvGen.kr(Env.new([0.25,0.25,0.75,1,1,0.75,0.25], 
		[((2*source)+interpolation), interpos,interp, source, interp, interpos], 'exponential'),
		 Impulse.kr(1/((3*interpolation)+(3*source))));
	vol1 = EnvGen.kr(Env.new([1,1,0.85,0.15,0,0.85,1], 
		[source, interp, interpos, ((2*source)+interpolation), interpos, interp], 'welch'), 
		Impulse.kr(1/((3*interpolation)+(3*source))));
	vol2 = EnvGen.kr(Env.new([0.15,0.15,0.85,1,1,0.85,0.15,0], 
		[source, interpos, interp, source, interp, interpos, (source+interpolation)], 'welch'),
		 Impulse.kr(1/((3*interpolation)+(3*source))));
	vol3 = EnvGen.kr(Env.new([0,0.15,0.85,1,1,0.85,0.15], 
		[((2*source)+interpolation), interpos,interp, source, interp, interpos], 'welch'),
		 Impulse.kr(1/((3*interpolation)+(3*source))));	signal1 =	
	PitchShift.ar(
	AudioIn.ar(audioIn1),
	0.1, 			// grain size
		sound1,	// mouse x controls pitch shift ratio
		0, 				// pitch dispersion
		0.004			// time dispersion
	); 
	signal2 =
	PitchShift.ar(
	AudioIn.ar(audioIn2),
	0.1, 			// grain size
		sound2,	// mouse x controls pitch shift ratio
		0, 				// pitch dispersion
		0.001			// time dispersion
	);
	signal3 =
	PitchShift.ar(
	AudioIn.ar(audioIn3),
	0.1, 			// grain size
		sound3,	// mouse x controls pitch shift ratio
		0, 				// pitch dispersion
		0.001			// time dispersion
	);
	finalSig = ((signal1*(vol1))+(signal2*(vol2))+(signal3*(vol3)))*intervol; //mix
		Out.ar(1, finalSig); //main out to computer2
		Out.ar(2, Pan2.ar(finalSig, 0, Ramp.kr(fadeHead,lag))); //stereo for headphones
	});
	
	SynthDef.writeOnce("DirInterpBuf", {|interpolation = 10, source = 5, intervol = 1.0, out1=0, out2=2, buffer1=0, buffer2=1, buffer3=2, fadeHead=1, lag=10|
var vol1, vol2, vol3, vol4, finalSig; 
var interp = interpolation*(3/7), interpos = interpolation*(4/7);
var signal, signal1, signal2, signal3, sound1, sound2, sound3;
	sound1 = EnvGen.kr(Env.new([1,1,0.75,0.25,0.25,0.75,1], 
		[source, interp, interpos, ((2*source)+interpolation), interpos, interp], 'exponential'), 
		Impulse.kr(1/((3*interpolation)+(3*source))));
	sound2 = EnvGen.kr(Env.new([0.25,0.25,0.75,1,1,0.75,0.25,0.25], 
		[source, interpos, interp, source, interp, interpos, (source+interpolation)], 'exponential'),
		 Impulse.kr(1/((3*interpolation)+(3*source))));
	sound3 = EnvGen.kr(Env.new([0.25,0.25,0.75,1,1,0.75,0.25], 
		[((2*source)+interpolation), interpos,interp, source, interp, interpos], 'exponential'),
		 Impulse.kr(1/((3*interpolation)+(3*source))));
	vol1 = EnvGen.kr(Env.new([1,1,0.85,0.15,0,0.85,1], 
		[source, interp, interpos, ((2*source)+interpolation), interpos, interp], 'welch'), 
		Impulse.kr(1/((3*interpolation)+(3*source))));
	vol2 = EnvGen.kr(Env.new([0.15,0.15,0.85,1,1,0.85,0.15,0], 
		[source, interpos, interp, source, interp, interpos, (source+interpolation)], 'welch'),
		 Impulse.kr(1/((3*interpolation)+(3*source))));
	vol3 = EnvGen.kr(Env.new([0,0.15,0.85,1,1,0.85,0.15], 
		[((2*source)+interpolation), interpos,interp, source, interp, interpos], 'welch'),
		 Impulse.kr(1/((3*interpolation)+(3*source))));	signal1 =	
	PitchShift.ar(
	PlayBuf.ar(1, buffer1, loop:1),
	0.1, 			// grain size
		sound1,	// mouse x controls pitch shift ratio
		0, 				// pitch dispersion
		0.004			// time dispersion
	); 
	signal2 =
	PitchShift.ar(
	PlayBuf.ar(1, buffer2, loop:1),
	0.1, 			// grain size
		sound2,	// mouse x controls pitch shift ratio
		0, 				// pitch dispersion
		0.001			// time dispersion
	);
	signal3 =
	PitchShift.ar(
	PlayBuf.ar(1, buffer3, loop:1),
	0.1, 			// grain size
		sound3,	// mouse x controls pitch shift ratio
		0, 				// pitch dispersion
		0.001			// time dispersion
	);
	finalSig = ((signal1*(vol1))+(signal2*(vol2))+(signal3*(vol3)))*intervol; //mix
		Out.ar(1, finalSig); //main out to computer2
		Out.ar(2, Pan2.ar(finalSig, 0, Ramp.kr(fadeHead,lag))); //stereo for headphones
	})		
			
	}

}