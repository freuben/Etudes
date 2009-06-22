Etudes4 {var s, network, control, <>speaker1, <>speaker2, <>speaker3, <>speaker4, <>speaker5, <>speaker6, <midiBehringer=1165770703, <behringer=1396639616;
var chan1, chan2, chan3, chan4, chan5, chan6, basicPath;


*new {arg ipAddress = "169.254.80.149";
		^super.new.initEtudes4(ipAddress);
}
	
initEtudes4 {arg ipAddress = "169.254.80.149";
	
	0.midiin;
	s = Server.local;
	control = MIDIOut(0, midiBehringer); //midi controller  
	network = NetAddr(ipAddress, 57120);
	basicPath = Document.current.path.dirname;
	
	Buffer.read(s, basicPath ++ "/samples/pygmies/etudes4(spk1).aif", bufnum:0); //buffer for chan1
	Buffer.read(s, basicPath ++ "/samples/pygmies/etudes4(spk2).aif", bufnum:1); //buffer for chan2
	Buffer.read(s, basicPath ++ "/samples/pygmies/etudes4(spk3).aif", bufnum:2); //buffer for chan3
	Buffer.read(s, basicPath ++ "/samples/pygmies/etudes4(spk4).aif", bufnum:3); //buffer for chan4
	Buffer.read(s, basicPath ++ "/samples/pygmies/etudes4(spk5).aif", bufnum:4); //buffer for chan5
	Buffer.read(s, basicPath ++ "/samples/pygmies/etudes4(spk6).aif", bufnum:5); //buffer for chan6

}

etudes4Start {arg mainvol=0, out1 = 1.0, out2 = 1.0, out3 = 1.0, out4 = 1.0, out5 = 1.0, out6 = 1.0;
var step = 0, midChan1, midChan2, midChan3, midChan4, midChan5, midChan6, ampspec, ampspec2;

chan1 = out1;
chan2 = out2;
chan3 = out3;
chan4 = out4;
chan5 = out5;
chan6 = out6;

s.volume_(mainvol);

MIDIIn.noteOn = {arg src, chan, num, vel;
if(step == 0, {
"START".postln;
speaker1 = Synth("monoOutBuf", [\out, 0, \buffer, 0, \globamp, chan1]); //speaker1
speaker2 = Synth("monoOutBuf", [\out, 1, \buffer, 1, \globamp, chan2]); //speaker2
speaker3 = Synth("monoOutBuf", [\out, 2, \buffer, 2, \globamp, chan3]); //speaker3
speaker4 = Synth("monoOutBuf", [\out, 3, \buffer, 3, \globamp, chan4]); //speaker4
speaker5 = Synth("monoOutBuf", [\out, 4, \buffer, 4, \globamp, chan5]); //speaker5
speaker6 = Synth("monoOutBuf", [\out, 5, \buffer, 5, \globamp, chan6]); //speaker6
});
if(step < 2, {
step = step + 1;
});
};

ampspec = ControlSpec(0.ampdb, 1.ampdb, \db, units: " dB");
ampspec2 = ControlSpec(-90, 6, \db, units: " dB");

MIDIIn.control = { | port, chan, num, val |

if(port == behringer, {

case
{num == 81} {
speaker1.set(\globamp, chan1 = ampspec.map(val/127.0).dbamp.postln); //slider 1
}
{num == 82} {
speaker2.set(\globamp, chan2 = ampspec.map(val/127.0).dbamp.postln); //slider 2
}
{num == 83} {
speaker3.set(\globamp, chan3 = ampspec.map(val/127.0).dbamp.postln); //slider 3
}
{num == 84} {
speaker4.set(\globamp, chan4 = ampspec.map(val/127.0).dbamp.postln); //slider 4
}
{num == 85} {
speaker5.set(\globamp, chan5 = ampspec.map(val/127.0).dbamp.postln); //slider 5
}
{num == 86} {
speaker6.set(\globamp, chan6 = ampspec.map(val/127.0).dbamp.postln); //slider 6
}
{(num == 87).or(num == 88)} {
network.sendMsg("/midi", num, val); //computer 2 network
}
{num == 8} {
s.volume_(ampspec2.map(val/127.0).round(0.1)); //master volume
}
;

});

};

midChan1 = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(chan1.ampdb).linlin(0,1,0,127).round(1);

midChan2 = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(chan2.ampdb).linlin(0,1,0,127).round(1);

midChan3 = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(chan3.ampdb).linlin(0,1,0,127).round(1);

midChan4 = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(chan4.ampdb).linlin(0,1,0,127).round(1);

midChan5 = ControlSpec(0.ampdb, 1.ampdb, \db, units:
 "dB").unmap(chan5.ampdb).linlin(0,1,0,127).round(1);

midChan6 = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(chan6.ampdb).linlin(0,1,0,127).round(1);

control.control(0,81, midChan1);
control.control(0,82, midChan2);
control.control(0,83, midChan3);
control.control(0,84, midChan4);
control.control(0,85, midChan5);
control.control(0,86, midChan6);

}

etudes4forceStart {

"START".postln;
speaker1 = Synth("monoOutBuf", [\out, 0, \buffer, 0, \globamp, chan1]); //speaker1
speaker2 = Synth("monoOutBuf", [\out, 1, \buffer, 1, \globamp, chan2]); //speaker2
speaker3 = Synth("monoOutBuf", [\out, 2, \buffer, 2, \globamp, chan3]); //speaker3
speaker4 = Synth("monoOutBuf", [\out, 3, \buffer, 3, \globamp, chan4]); //speaker4
speaker5 = Synth("monoOutBuf", [\out, 4, \buffer, 4, \globamp, chan5]); //speaker5
speaker6 = Synth("monoOutBuf", [\out, 5, \buffer, 5, \globamp, chan6]); //speaker6

}

etudes4finish {
		s.freeAll; 
		s.bufFreeRange(0,90); 
		s.queryAllNodes;
		currentEnvironment.clear;
}

}