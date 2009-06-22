PianoRuidoBuffer : PianoRuidoLive {
	var piano1, piano2, piano3, osc, net;
		
	buffers {var documentPath;
	documentPath = Document.current.path.dirname;
piano1 = Buffer.read(s, documentPath ++ "/etudes_recordings/Chopin Op.25 No1 _1");
piano2 = Buffer.read(s, documentPath ++ "/etudes_recordings/Ligiti");
piano3 = Buffer.read(s, documentPath ++ "/etudes_recordings/op. 10 No. 5 Chopin 3");

	}
	
	//bufferstrumpet {
//piano1 = Buffer.read(s, "/Users/freuben/Music/Nick/trumpet.aif");
//piano2 = Buffer.read(s, "/Users/freuben/Music/Nick/trumpet2.aif");
//piano3 = Buffer.read(s, "/Users/freuben/Music/Nick/trumpet3.aif");
//
//	}

	ruidoBuffer {arg int = interp, sour = source, vol = volume;
	
x = Synth.tail(s, "XenakisInterp", [\interpolation, int, \source, sour, \intervol, vol]);
dirIn = Synth.tail(s, "DirInterpBuf", [\interpolation, int, \source, sour, \intervol, vol, \buffer1, piano1.bufnum, \buffer2, piano2.bufnum ,\buffer3, piano3.bufnum]);

}
			
	startsynth2 {
	
y1 = Synth.head(s, "live-shift1-buffer", [\bufnum, piano1.bufnum]);
Synth.tail(s, "live-play1", [\bufnum, a.bufnum, \rate, 1.015, \pan, -1]);
Synth.tail(s, "live-play1", [\bufnum, b.bufnum, \pan,  1, \rate, 1.0125]);
Synth.tail(s, "live-play1", [\bufnum, c.bufnum, \pan, 0.01, \rate, 1.01]);
Synth.tail(s, "live-play1", [\bufnum, d.bufnum, \pan, 0.533, \rate, 0.995]);
Synth.tail(s, "live-play1", [\bufnum, e.bufnum, \pan, -0.6, \rate, 0.9975]);

y2 = Synth.head(s, "live-shift2-buffer", [\bufnum, piano2.bufnum]);
Synth.tail(s, "live-play2", [\bufnum, f.bufnum, \rate, 1.015, \pan, -1]);
Synth.tail(s, "live-play2", [\bufnum, g.bufnum, \pan,  1, \rate, 1.0125]);
Synth.tail(s, "live-play2", [\bufnum, h.bufnum, \pan, 0.01, \rate, 1.01]);
Synth.tail(s, "live-play2", [\bufnum, i.bufnum, \pan, 0.533, \rate, 0.995]);
Synth.tail(s, "live-play2", [\bufnum, j.bufnum, \pan, -0.6, \rate, 0.9975]);


y3 = Synth.head(s, "live-shift3-buffer", [\bufnum, piano3.bufnum]);
Synth.tail(s, "live-play3", [\bufnum, k.bufnum, \rate, 1.015, \pan, -1]);
Synth.tail(s, "live-play3", [\bufnum, l.bufnum, \pan,  1, \rate, 1.0125]);
Synth.tail(s, "live-play3", [\bufnum, m.bufnum, \pan, 0.01, \rate, 1.01]);
Synth.tail(s, "live-play3", [\bufnum, n.bufnum, \pan, 0.533, \rate, 0.995]);
Synth.tail(s, "live-play3", [\bufnum, o.bufnum, \pan, -0.6, \rate, 0.9975]);

	}
		
}