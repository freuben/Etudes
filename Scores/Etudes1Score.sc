Etudes1Score : Etudes1 {

var <>initVocoder = 0.75, <>initLowband=1.5, <>initGaterev=2.0, <>initGateperc= 2.0, <>initVoc2=2.0, <>initHighband = 2.0, <>initPiano = 2.0, <>initOrchfirst=2.0, <>initBow=2.0, <>initOrchords=2.0, <>initOrchmel = 2.0, <>initCel=2.0, <>initAntony = 2.0, <>initBombo=3.0;
var osc, <volgate=0.5, volgateperc, volbow, volorch, volhighband, volorchmel;
var a, b, c, k, t;


startEtudes1Score {arg firstVocoder = 0.75, firstLowband=1.5, firstGaterev=2.0, firstGateperc= 2.0, firstVoc2=2.0, firstHighband = 2.0, firstPiano = 2.0, firstOrchfirst=2.0, firstBow=2.0, firstOrchords=2.0, firstOrchmel = 2.0, firstCel=2.0, firstAntony = 2.0, firstBombo=3.0;

initVocoder = firstVocoder;
initLowband = firstLowband;
initGaterev = firstGaterev;
initGateperc = firstGateperc;
initVoc2 = firstVoc2;
initHighband = firstHighband;
initPiano = firstPiano;
initOrchfirst = firstOrchfirst;
initBow = firstBow;
initOrchords = firstOrchords;
initOrchmel = firstOrchmel;
initCel = firstCel;
initAntony = firstAntony;
initBombo = firstBombo;


osc = OSCresponder(s.addr,'/tr',{ arg time,responder,msg, addr;
	
			a = [msg[2], msg[3]]; 
			k = Routine({
				1.do({var note, vel, midinote, velo, midinote1;
				note = b;
				midinote = note - 48;
				vel = c.linlin(0,0.05,0,127);
				velo = if(vel > 70, {120;}, {vel;});
					h.noteOn(0, (midinote.round(1)), velo);
					rrand(0.01,0.1).yield; 
					h.noteOff(0, (midinote.round(1)) , velo);
					h.noteOff(0, (midinote.round(1)) , velo);
					h.noteOff(0, (midinote.round(1)) , velo);
					})
					});

				if(a[0] == 0, 
					{b = a[1]; if(c.notNil, {k.play});}
				);
				if(a[0] == 1, 
					{c = a[1];}
				);
	
			});
}


sopranoScore {arg note, vel;
var justnote, mid;
	justnote = note.midijust(415);
	~step1.post; '/S'.postln;
	
	//noteOn

	if((~step1 >= 0).and(~step1 < 51).and(~step1.odd), 
		{s.sendMsg("n_set", 1012, \midi, justnote, \velo, vel.linlin(0,127,0,0.5), \amp2, 1000);}
	); //transpenv
	if(~step1 == 1, 
		{
		~globamp1 = ~globamp2;
		mid = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(~globamp1.ampdb).linlin(0,1,0,127).round(1);
		control.control(0, 81, mid);
		s.sendMsg("n_set", 1014, \gates, 1, \transp, (justnote/442)*8,\atk, makenv1[0][atk], \dec, makenv1[0][dec], \sus, makenv1[0][sus], \rel, makenv1[0][rel], \crv, makenv1[0][crv], \globamp, ~globamp1, \adjVol, 2.0)}
	); //start vocoder
	if((~step1 > 1).and(~step1 <= 13).and(~step1.odd), 
		{s.sendMsg("n_set", 1014, \transp, (justnote/442)*8, \pitch, (42.663256630967+((~step1*0.35)/13)));}
	); //detune vocoder 
	if((~step1 > 13).and(~step1<50).and(~step1.odd), 
		{s.sendMsg("n_set", 1014, \transp, ((justnote/442)*8), \pitch, (43.013256630967+((~step1-13*0.65)/(35-13))));}
	); //detune vocoder
	if(~step1 == 19, 
		{s.sendMsg("n_set", 1014, \gates, 1, \atk, makenv1[2][atk], \dec, makenv1[2][dec], \sus, makenv1[2][sus], \rel, makenv1[2][rel], \crv, makenv1[2][crv])};
	); 
	if(~step1 == 33, 
		{s.sendMsg("n_set", 1014, \gates, 1, \atk, makenv1[3][atk], \dec, makenv1[3][dec], \sus, makenv1[3][sus], \rel, rrand(2.2,2.6), \crv,[-3,-4].choose);}
	);
	if(~step1 == 51, 
		{~globamp1 = ~globamp2*initGaterev;
		volgate = ~globamp1;
		mid = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(~globamp1.ampdb).linlin(0,1,0,127).round(1);
		control.control(0, 81, mid);
		s.sendMsg("n_set", 1017, \gates, 1, \justfreq, justnote, \amp, vel.linlin(0, 127, 0, 15)*0.75, \globamp, ~globamp1, \atk, makenv2[0][atk], \dec, makenv2[0][dec], \sus, makenv2[0][sus], \rel, makenv2[0][rel], \crv, makenv2[0][crv])}
	); //start gaterev
	if((~step1 > 51).and(~step1 < 64).and(~step1.odd), 
		{s.sendMsg("n_set",1017, \justfreq, justnote, \amp, vel.linlin(0, 127, 0, 15)*0.75);}
	); //gaterev
	if(~step1 == 55,
		{s.sendMsg("n_set", 1017, \gates, 1, \atk, makenv2[1][atk], \dec, makenv2[1][dec], \sus, makenv2[1][sus], \rel, makenv2[1][rel], \crv, makenv2[1][crv]);}
	);		
	if(~step1 == 65,
		{~globamp1 = ~globamp2*(initHighband/initGateperc);
		volhighband = ~globamp1;
		mid = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(~globamp1.ampdb).linlin(0,1,0,127).round(1);
		control.control(0, 81, mid);
		s.sendMsg("n_set", 1024, \gates, 1, \globamp, ~globamp1, \adjVol, 4, \atk, makenv3[1][atk], \dec, makenv3[1][dec], \sus, makenv3[1][sus], \rel, makenv3[1][rel], \crv, makenv3[1][crv]);}
	);
	if((~step1 >= 65).and(~step1 <= 85).and(~step1.odd), 
		{s.sendMsg("n_set", 1024, \midi, justnote, \velo, vel.linlin(0,127,0,2000)*0.35); 
		fourier2.ffttime( (0.3166666666667-((~step1-65)*0.01118934240363)))}
	); //bandpass2		
	if(~step1 == 67, 
		{s.sendMsg("n_free", 1017);}
	); //free gaterev
	if(~step1 == 77,
		{s.sendMsg("n_set", 1024, \gates, 1, \atk, makenv3[1][atk], \dec, makenv3[1][dec], \sus, makenv3[1][sus], \rel, makenv3[1][rel], \crv, makenv3[1][crv]);}
	);
	if(~step1 == 87,
		{s.sendMsg("n_set", 1024, \gates, 1, \atk, makenv3[2][atk], \dec, makenv3[2][dec], \sus, makenv3[2][sus], \rel, makenv3[2][rel], \crv, makenv3[2][crv], \globamp, ~globamp1, \adjVol, 4); 
		s.sendBundle(0.1, ["n_free", 1004], ["n_free", 1005], ["n_free", 1006]);}
	); //free nodes for bowedpianos
	if((~step1 >= 87).and(~step1 <= 99).and(~step1.odd), 
		{s.sendMsg("n_set", 1024, \midi, justnote, \velo, vel.linlin(0,127,0,2000)*0.35;); fourier2.ffttime( 0.092879818594104 );}
	); //bandpass2
	if(~step1 == 101, 
		{s.sendMsg("n_free", 1003)}
	);
	if(~step1 == 107, 
		{s.sendMsg("s_new", "bowedpiano", 1006, 0, 2004, \out, 0, \bufnum, 18, \amp, vel.linlin(0, 127, 0, 1.0)*0.75, \globamp, ~globamp1);}
	); //bowedpiano
	if(~step1 == 109, 
		{s.sendMsg("n_set", 1024, \out, 0, \midi, justnote, \velo, vel.linlin(0,127,0,2000)*0.35, \gates, 1, \atk, makenv3[3][atk], \dec, makenv3[3][dec], \sus, makenv3[3][sus], \rel, makenv3[3][rel], \crv, makenv3[3][crv], \globamp, ~globamp1, \adjVol, 4); 
		fourier2.ffttime( 0.092879818594104)}
	); //bandpass2	
	if((~step1 > 109).and(~step1 < 123).and(~step1.odd), 
		{s.sendMsg("n_set", 1024, \midi, justnote, \velo, vel.linlin(0,127,0,2000)*0.35);}
	);
	if(~step1 == 123, 
		{~globamp1 = volgate;
		mid = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(~globamp1.ampdb).linlin(0,1,0,127).round(1);
		control.control(0, 81, mid);
		s.sendMsg("/s_new", "gating2buf", 1017, 1, 2003, \out, 0, \justfreq, justnote, \bufnum, 5, \soundbuf, 3, \gates, 1, \start, rrand(0,6060560), \oct, 8, \amp, vel.linlin(0, 127, 0, 15)*0.75, \atk, makenv2[2][atk], \dec, makenv2[2][dec], \sus, makenv2[2][sus], \rel, makenv2[2][rel], \crv, makenv2[2][crv], \globamp, ~globamp1);}
	); //start gaterev
	if((~step1 > 123).and(~step1 < 132).and(~step1.odd),
		{s.sendMsg("n_set",1017, \justfreq, justnote, \amp, vel.linlin(0, 127, 0, 15)*0.75);}
	); //gaterev
	if(~step1 == 133, 
		{
		~globamp1 = volbow;
		mid = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(~globamp1.ampdb).linlin(0,1,0,127).round(1);
		control.control(0, 81, mid);
		s.sendMsg("s_new", "bowedpiano", 1004, 0, 2004, \out, 0, \bufnum, 16 , \amp, vel.linlin(0, 127, 0, 1.0)*0.75, \globamp, ~globamp1);}
	); //bowedpiano
	if(~step1 == 139, 
		{s.sendMsg("s_new", "bowedpiano", 1005, 0, 2004, \out, 0, \bufnum, 17, \amp, vel.linlin(0, 127, 0, 1.0)*0.75, \globamp, ~globamp1);}
	); //bowedpiano
	if(~step1 == 141, 
		{s.sendMsg("b_allocRead", 18, documentPath ++ "/samples/chords/pianorch_la_si_fa_farriba.aif");}
	);
	if(~step1 == 145, 
		{~globamp1 = volorchmel;
		mid = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(~globamp1.ampdb).linlin(0,1,0,127).round(1);
		control.control(0, 81, mid);
		s.sendMsg("s_new", "pianorch", 3003, 0, 2004, \out, 0, \bufnum, 18, \gates, 1, \amp, vel.linlin(0, 127, 0, 1.5), \start, 0.0, \globamp, ~globamp1);}
	);
	if(~step1 == 151, 
		{s.sendMsg("b_allocRead", 16, documentPath ++ "/samples/harmonics/short_re.aif");}
	);
	if(~step1 == 153, 
		{~globamp1 = volbow;
		mid = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(~globamp1.ampdb).linlin(0,1,0,127).round(1);
		control.control(0, 81, mid);
		s.sendMsg("s_new", "bowedpiano", 1004, 0, 2004, \out, 0, \bufnum, 16 , \amp, vel.linlin(0, 127, 0, 1.0)*0.75, \globamp, ~globamp1);}
	); //bowedpiano
	if(~step1 == 155, 
		{s.sendMsg("b_allocRead", 17, documentPath ++ "/samples/harmonics/short_sol.aif");}
	);
	if(~step1 == 163, 
		{s.sendMsg("b_allocRead", 18, documentPath ++ "/samples/harmonics/short_la.aif");}
	);	
	if(~step1 == 167, 
		{~globamp1 = volhighband;
		mid = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(~globamp1.ampdb).linlin(0,1,0,127).round(1);
		control.control(0, 81, mid);
		fourier2.ffttime( 0.092879818594104); 
		s.sendMsg("n_set", 1024, \gates, 1, \out, 0, \midi, justnote, \velo, vel.linlin(0,127,0,2000)*0.35, \atk, makenv3[4][atk], \dec, makenv3[4][dec], \sus, makenv3[4][sus], \rel, makenv3[4][rel], \crv, makenv3[4][crv], \globamp, ~globamp1, \adjVol, 4);}
	);
	if((~step1 > 167).and(~step1 < 199).and(~step1.odd), 
		{s.sendMsg("n_set", 1024, \midi, justnote,  \velo, vel.linlin(0,127,0,2000)*0.35);}
	);
	if(~step1 == 195, 
		{s.sendBundle(0.1,["/b_alloc", 16, 32768, 1], ["/b_alloc", 17, 32768, 1], ["/b_alloc", 18, 32768, 1]);}
	);
	if(~step1 == 197, 
		{s.sendBundle(0.1,["/b_read", 16, documentPath ++ "/samples/harmonics/long_re.aif", 0, 32768, 0, 1], ["/b_read", 17, documentPath ++ "/samples/harmonics/long_sol.aif", 0, 32768, 0, 1], ["/b_read", 18, documentPath ++ "/samples/harmonics/long_fa.wav", 0, 32768, 0, 1]);}
	);
	if(~step1 == 199, 
		{~globamp1 = volbow;
		mid = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(~globamp1.ampdb).linlin(0,1,0,127).round(1);
		control.control(0, 81, mid);
		fourier2.stoptask; fourier2.stoptask2; 
		s.sendBundle(0.1, ["n_free", 1019], ["n_free", 1020], ["n_free", 1021],["n_free", 1022], ["n_free", 1023], ["n_free", 1024]); 
		s.sendMsg("s_new", "bowedlong", 1003, 0, 2004, \out, 0, \bufnum, 16 , \amp, vel.linlin(0, 127, 0, 1.0)*0.75, \globamp, ~globamp1);}
	); 	
	if(~step1 == 205, 
		{s.sendMsg("s_new", "bowedlong", 1004, 0, 2004, \out, 0, \bufnum, 17 , \amp, vel.linlin(0, 127, 0, 1.0)*0.75, \globamp, ~globamp1);}
	);
	if(~step1 == 211, 
		{s.sendMsg("s_new", "bowedlong", 1005, 0, 2004, \out, 0, \bufnum, 18 , \amp, vel.linlin(0, 127, 0, 1.0)*0.75, \globamp, ~globamp1);}
	);
	if(~step1 == 221, 
		{s.sendMsg("/b_alloc", 20, 32768, 1);}
	);
	if(~step1 == 223, 
		{s.sendMsg("/b_read", 20, documentPath ++ "/samples/harmonics/long_si_flat.aif", 0, 32768, 0, 1);}
	);
	if(~step1 == 225, 
		{s.sendMsg("n_set", 1003, \gates, 1, \amp, vel.linlin(0, 127, 0, 1.0)*0.75); 
		s.sendMsg("n_set", 1005, \gates, 1 , \amp, vel.linlin(0, 127, 0, 1.0)*0.75); 
		s.sendMsg("s_new", "bowedlong", 1006, 0, 2004, \out, 0, \bufnum, 20 , \amp, vel.linlin(0, 127, 0, 1)*0.75, \globamp, ~globamp1);}
	);
	if(~step1 == 227, 
		{s.sendMsg("b_close", 17);}
	);
	if(~step1 == 231, 
		{s.sendMsg("/b_read", 17, documentPath ++ "/samples/harmonics/long_itsdo.wav", 0, 32768, 0, 1);}
	);
	if(~step1 == 233, 
		{s.sendMsg("n_set", 1005, \gates, 0); 
		s.sendMsg("n_set", 1004, \gates, 1, \amp, vel.linlin(0, 127, 0, 1.0)*0.75);}
	);
	if(~step1 == 235, 
		{s.sendMsg("b_close", 18);}
	);
	if(~step1 == 239, 
		{s.sendMsg("n_set", 1003, \gates, 1, \amp, vel.linlin(0, 127, 0, 1.0)*0.75); 
		s.sendMsg("n_set", 1004, \gates, 1, \amp, vel.linlin(0, 127, 0, 1.0)*0.75); 
		s.sendMsg("n_set", 1006, \gates, 1, \amp, vel.linlin(0, 127, 0, 1.0)*0.75); 
		s.sendMsg("/b_read", 18, documentPath ++ "/samples/harmonics/long_miflat.aif", 0, 32768, 0, 1);}
	);
	if(~step1 == 241, 
		{s.sendMsg("n_set", 1006, \gates, 0); 
		s.sendMsg("n_set", 1005, \gates, 1, \amp, vel.linlin(0, 127, 0, 1.0)*0.75);}
	);
	if(~step1 == 243, 
		{s.sendMsg("b_close", 20);}
	);
	if(~step1 == 245, 
		{s.sendMsg("/b_read", 20, documentPath ++ "/samples/harmonics/long_fa.wav", 0, 32768, 0, 1);}
	);
	if(~step1 == 247, 
		{s.sendMsg("n_set", 1003, \gates, 1, \amp, vel.linlin(0, 127, 0, 1.0)); s.sendMsg("n_set", 1005, \gates, 1, \amp, vel.linlin(0, 127, 0, 1.0)); s.sendMsg("n_set", 1006, \gates, 1, \amp, vel.linlin(0, 127, 0, 1.0)*0.75);}
	);
	if(~step1 == 249, 
		{s.sendMsg("b_close", 17);}
	);
	if(~step1 == 251, 
		{s.sendMsg("/b_read", 17, documentPath ++ "/samples/harmonics/long_dosharp.aif", 0, 32768, 0, 1);}
	);
	if(~step1 == 253, 
		{s.sendMsg("n_set", 1005, \gates, 0); 
		s.sendMsg("n_set", 1004, \gates, 1);}
	);
	if(~step1 == 255, 
		{s.sendBundle(0.1, ["b_close", 18], ["/b_read", 18, documentPath ++ "/samples/harmonics/long_minatural.aif", 0, 32768, 0, 1]);}
	);
	if(~step1 == 257, 
		{s.sendMsg("n_set", 1005, \gates, 1, \amp, vel.linlin(0, 127, 0, 0.5)*0.75); s.sendMsg("n_set", 1006, \gates, 0, \amp, vel.linlin(0, 127, 0, 1.0)*0.75);}
	);
	if(~step1 == 259, 
		{s.sendMsg("b_close", 20);}
	);
	if(~step1 == 261, 
		{s.sendMsg("n_set", 1003, \gates, 1, \amp, vel.linlin(0, 127, 0, 1.0)*0.75); 
		s.sendMsg("n_set", 1004, \gates, 1, \amp, vel.linlin(0, 127, 0, 1.0)*0.75); 
		s.sendMsg("n_set", 1005, \gates, 1); 
		s.sendMsg("/b_read", 20, documentPath ++ "/samples/harmonics/long_miflat.aif", 0, 32768, 0, 1);}
	); 
	if(~step1 == 263, 
		{s.sendMsg("n_set", 1006, \gates, 1, \amp, vel.linlin(0, 127, 0, 1.0)*0.75); 
		s.sendMsg("n_set", 1004, \gates, 0);}
	);
	if(~step1 == 265, 
		{s.sendMsg("b_close", 17);}
	);
	if(~step1 == 267, 
		{s.sendMsg("/b_read", 17, documentPath ++ "/samples/harmonics/long_dosharp.aif", 0, 32768, 0, 1);}
	);
	if(~step1 == 269, 
		{s.sendMsg("n_set", 1004, \gates, 1, \amp, vel.linlin(0, 127, 0, 1.0)*0.75);
		s.sendMsg("n_set", 1006, \gates, 0);}
	);
	if(~step1 == 271, 
		{s.sendMsg("n_set", 1003, \gates, 1, \amp, vel.linlin(0, 127, 0, 1.0)*0.75); 
		s.sendMsg("n_set", 1004, \gates, 1, \amp, vel.linlin(0, 127, 0, 1.0)*0.75); 
		s.sendMsg("n_set", 1005, \gates, 1, \amp, vel.linlin(0, 127, 0, 1.0)*0.75); 
		s.sendBundle(0.1,["b_close", 20], ["/b_read", 20, documentPath ++ "/samples/harmonics/long_itsdo.wav", 0, 32768, 0, 1]);}
	);
	if(~step1 == 273, 
		{s.sendMsg("n_set", 1004, \gates, 0); 
		s.sendMsg("n_set", 1006, \gates, 1, \amp, vel.linlin(0, 127, 0, 1.0)*0.75);}
	); 
	if(~step1 == 279, 
		{s.sendMsg("b_close", 17);}
	); 
	if(~step1 == 281, 
		{s.sendMsg("n_set", 1003, \gates, 1, \amp, vel.linlin(0, 127, 0, 1.0)*0.75); 
		s.sendMsg("n_set", 1005, \gates, 1, \amp, vel.linlin(0, 127, 0, 1.0)*0.75); 
		s.sendMsg("n_set", 1006, \gates, 1, \amp, vel.linlin(0, 127, 0, 1.0)*0.75);}
	);
	if(~step1 == 283, 
		{s.sendMsg("/b_read", 17, documentPath ++ "/samples/harmonics/long_fa.wav", 0, 32768, 0, 1);}
	);
	if(~step1 == 287, 
		{s.sendMsg("n_set", 1005, \gates, 0); 
		s.sendMsg("n_set", 1004, \gates, 1, \amp, vel.linlin(0, 127, 0, 1.0)*0.75);}
	);
	if(~step1 == 291, 
		{s.sendMsg("n_set", 1003, \gates, 1, \amp, vel.linlin(0, 127, 0, 1.0)*0.75); 
		s.sendMsg("n_set", 1004, \gates, 1, \amp, vel.linlin(0, 127, 0, 1.0)*0.75); 
		s.sendMsg("n_set", 1006, \gates, 1, \amp, vel.linlin(0, 127, 0, 1.0)*0.75);}
	);
	if(~step1 == 297, 
		{s.sendBundle(0.1,["b_close", 18], ["/b_read", 18, documentPath ++ "/samples/harmonics/long_la.aif", 0, 32768, 0, 1]);}
	);
	if(~step1 == 303, 
		{s.sendMsg("n_set", 1006, \gates, 0); 
		s.sendMsg("n_set", 1005, \gates, 1, \amp, vel.linlin(0, 127, 0, 1.0)*0.75);}
	);
	if(~step1 == 305, 
		{s.sendMsg("n_set", 1003, \gates, 1, \amp, vel.linlin(0, 127, 0, 1.0)*0.75); 
		s.sendMsg("n_set", 1004, \gates, 1, \amp, vel.linlin(0, 127, 0, 1.0)*0.75); 
		s.sendMsg("n_set", 1005, \gates, 1, \amp, vel.linlin(0, 127, 0, 1.0)*0.75);}
	);
	if(~step1 == 311, 
		{s.sendBundle(0.1,["b_close", 20], ["/b_read", 20, documentPath ++ "/samples/harmonics/long_miflat.aif", 0, 32768, 0, 1]);}
	);
	if(~step1 == 315, 
		{s.sendMsg("n_set", 1004, \gates, 0); 
		s.sendMsg("n_set", 1006, \gates, 1, \amp, vel.linlin(0, 127, 0, 1.0)*0.75);}
	);
	if(~step1 == 325, 
		{s.sendMsg("b_close", 17);}
	);
	if(~step1 == 337, 
		{s.sendMsg("n_set", 1003, \gates, 1, \amp, vel.linlin(0, 127, 0, 1.0)*0.75); 
		s.sendMsg("n_set", 1005, \gates, 1, \amp, vel.linlin(0, 127, 0, 1.0)*0.75); 
		s.sendMsg("n_set", 1006, \gates, 1, \amp, vel.linlin(0, 127, 0, 1.0)*0.75); 
		s.sendMsg("/b_read", 17, documentPath ++ "/samples/harmonics/long_sol.aif", 0, 32768, 0, 1)}
	);
	if(~step1 == 343, 
		{s.sendMsg("n_set", 1005, \gates, 0); 
		s.sendMsg("n_set", 1004, \gates, 1);}
	);
	if(~step1 == 345, 
		{s.sendMsg("b_close", 18);}
	);
	if(~step1 == 347, 
		{s.sendMsg("/b_read", 18, documentPath ++ "/samples/harmonics/long_fa.wav", 0, 32768, 0, 1)}
	);
	if(~step1 == 349, 
		{s.sendMsg("n_set", 1006, \gates, 0); 
		s.sendMsg("n_set", 1005, \gates, 1, \amp, vel.linlin(0, 127, 0, 1.0)*0.75);}
	);
	if(~step1 == 355, 
		{s.sendBundle(0.1,["b_close", 20], ["/b_read", 20, documentPath ++ "/samples/harmonics/long_si_flat.aif", 0, 32768, 0, 1]);}
	);
		
	//noteoff
	if((~step1 > 1).and(~step1 <= 13).and(~step1.even), 
		{s.sendMsg("n_set", 1014, \transp, (justnote/442)*8, \pitch, (42.663256630967+((~step1*0.35)/13)));}
	); //detune vocoder
	if((~step1 > 13).and(~step1<50).and(~step1.even), 
		{s.sendMsg("n_set", 1014, \transp, ((justnote/442)*8), \pitch, (43.013256630967+((~step1-13*0.65)/(35-13))));}
	); //detune vocoder
	if(~step1 == 18, 
		{s.sendMsg("n_set", 1014, \gates, 0);}
	); 
	if(~step1 == 32, 
		{s.sendMsg("n_set", 1014, \gates, 0);}
	);
	if(~step1 == 50, 
		{s.sendMsg("n_set", 1014, \gates, 0);}
	); //stop vocoder, buffer gaterev
	if(~step1 == 54,
		{s.sendMsg("n_set",1017, \gates, 0);}
	); //gaterev
	if(~step1 == 62, 
		{fourier2.threshEtudes(2.4);
		fourier2.getQ;}
	); //getfreq banpass2
	if(~step1 == 64, 
		{s.sendMsg("n_set",1017, \gates, 0);
		s.sendMsg("/s_new", "transpenv", 1024, 0, 2003, \in, 42, \out, 0, \gates, 0);}
	); //stop gaterev, start nodes for bandpass2, start bandpass2
	if((~step1 >= 65).and(~step1 <= 85).and(~step1.even), 
		{fourier2.ffttime( (0.3166666666667-((~step1-65)*0.01118934240363)))}
	); //bandpass2
	if(~step1 == 76, 
		{s.sendMsg("n_set", 1024, \gates, 0);}
	); 
	if(~step1 == 86, 
		{s.sendMsg("n_set", 1024, \gates, 0); 
		fourier2.ffttime( 0.092879818594104)}
	); //bandpass2
	if((~step1 >= 87).and(~step1 <= 99).and(~step1.even), 
		{fourier2.ffttime( 0.092879818594104);}
	); //bandpass2
	if(~step1 == 100, 
		{s.sendMsg("n_set", 1024, \gates, 0); 
		fourier2.ffttime( 0.092879818594104); 
		s.sendBundle(0.1, [\b_allocRead, 16, documentPath ++ "/samples/harmonics/short_re.aif"], [\b_allocRead, 17, documentPath ++ "/samples/harmonics/short_mi_flat.aif"], [\b_allocRead, 18, documentPath ++ "/samples/harmonics/short_do.aif"]);}
		); //bandpass2, prepare for bowedpianos
	if(~step1 == 102, 
		{~globamp1 = ~globamp2*(initBow/initGateperc);
		volbow = ~globamp1;
		mid = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(~globamp1.ampdb).linlin(0,1,0,127).round(1);
		control.control(0, 81, mid);
		s.sendMsg("s_new", "bowedpiano", 1004, 0, 2004, \out, 0, \bufnum, 16 , \amp, 81.linlin(0, 127, 0, 1.0)*0.75, \globamp, ~globamp1);}
	); //bowedpiano
	if(~step1 == 104, 
		{s.sendMsg("s_new", "bowedpiano", 1005, 0, 2004, \out, 0, \bufnum, 17, \amp, 76.linlin(0, 127, 0, 1.0)*0.75, \globamp, ~globamp1);}
	); //bowedpiano
	if(~step1 == 122, 
		{s.sendMsg("n_set", 1024, \gates, 0);}
	); 
	if(~step1 == 132, 
		{s.sendMsg("n_set",1017, \gates, 0); 
		s.sendBundle(0.1, [\b_allocRead, 16, documentPath ++ "/samples/harmonics/short_la.aif"], [\b_allocRead, 17, documentPath ++ "/samples/harmonics/short_re.aif"]);}
	); //gaterev, prepare for bowedpianos
	if(~step1 == 152, 
		{s.sendMsg("n_set", 3003, \gates, 0);}
	);
	if(~step1 == 158, 
		{s.sendMsg("s_new", "bowedpiano", 1006, 0, 2004, \out, 0, \bufnum, 17, \amp, 81.linlin(0, 127, 0, 1.0)*0.75, \globamp, ~globamp1);}
	);
	if(~step1 == 166, 
		{s.sendMsg("s_new", "bowedpiano", 1005, 0, 2004, \out, 0, \bufnum, 18 , \amp, 76.linlin(0, 127, 0, 1.0)*0.75, \globamp, ~globamp1);}
	);
	if(~step1 == 198, 
		{s.sendMsg("n_set", 1024, \gates, 0);}
	);
	if(~step1 == 224, 
		{s.sendMsg("n_set", 1003, \gates, 0); 
		s.sendMsg("n_set", 1004, \gates, 0); 
		s.sendMsg("n_set", 1005, \gates, 0);}
	);
	if(~step1 == 238, 
		{s.sendMsg("n_set", 1003, \gates, 0); 
		s.sendMsg("n_set", 1004, \gates, 0); 
		s.sendMsg("n_set", 1006, \gates, 0);}
	);		
	if(~step1 == 246, 
		{s.sendMsg("n_set", 1003, \gates, 0); 
		s.sendMsg("n_set", 1004, \gates, 0); 
		s.sendMsg("n_set", 1005, \gates, 0);}
	);
	if(~step1 == 260, 
		{s.sendMsg("n_set", 1003, \gates, 0); 
		s.sendMsg("n_set", 1004, \gates, 0); 
		s.sendMsg("n_set", 1005, \gates, 0);}
	);
	if(~step1 == 270, 
		{s.sendMsg("n_set", 1003, \gates, 0); 
		s.sendMsg("n_set", 1004, \gates, 0); 
		s.sendMsg("n_set", 1005, \gates, 0);}
	);
	if(~step1 == 280, 
		{s.sendMsg("n_set", 1003, \gates, 0); 
		s.sendMsg("n_set", 1005, \gates, 0); 
		s.sendMsg("n_set", 1006, \gates, 0);}
	);
	if(~step1 == 290, 
		{s.sendMsg("n_set", 1003, \gates, 0); 
		s.sendMsg("n_set", 1004, \gates, 0); 
		s.sendMsg("n_set", 1006, \gates, 0);}
	);
	if(~step1 == 304, 
		{s.sendMsg("n_set", 1003, \gates, 0); 
		s.sendMsg("n_set", 1004, \gates, 0); 
		s.sendMsg("n_set", 1005, \gates, 0);}
	);
	if(~step1 == 336, 
		{s.sendMsg("n_set", 1003, \gates, 0); 
		s.sendMsg("n_set", 1005, \gates, 0); 
		s.sendMsg("n_set", 1006, \gates, 0);}
	);		
				
		
	//end noteOff
}

alto1Score { arg note, vel;
var justnote, mid;
	justnote = note.midijust(415);
	~step2.post; '/A1'.postln;
	
	//noteOn
	
	if((~step2 >= 0).and(~step2 < 68).and(~step2.odd), 
		{s.sendMsg("n_set", 1013, \midi,justnote, \velo, vel.linlin(0,127,0,0.5), \amp2, 1000);}
	); //transpenv
	if(~step2 == 1, 
		{~globamp2 = ~globamp2*initVocoder;
		mid = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(~globamp2.ampdb).linlin(0,1,0,127).round(1);
		control.control(0, 82, mid);
		s.sendMsg("n_set", 1015, \gates, 1, \transp, (justnote/332)*2, \atk, makenv1[4][atk], \dec, makenv1[4][dec], \sus, makenv1[4][sus], \rel, makenv1[4][rel], \crv, makenv1[4][crv], \globamp, ~globamp2, \adjVol, 1.75);}
	);  //start vocoder
	if((~step2 > 1).and(~step2 <= 13).and(~step2.odd), 
		{s.sendMsg("n_set", 1015, \pitch, (41.663256630967-((~step2*0.35)/13)), \transp, (justnote/332)*2);}
	); //detune vocoder
	if((~step2 > 13).and(~step2 < 68).and(~step2.odd), 
		{s.sendMsg("n_set", 1015, \pitch, (41.313256630967-((~step2-13*0.65)/(49-13))), \transp, ((justnote/332)*2));}
	); //detune vocoder
	if(~step2 == 15, 
		{s.sendMsg("n_set", 1015, \gates, 1, \atk, makenv1[5][atk], \dec, makenv1[5][dec], \sus, makenv1[5][sus], \rel, makenv1[5][rel], \crv, makenv1[5][crv]);}
	); 
	if(~step2 == 19, 
		{s.sendMsg("n_set", 1015, \gates, 1, \atk, makenv1[6][atk], \dec, makenv1[6][dec], \sus, makenv1[6][sus], \rel, makenv1[6][rel], \crv,makenv1[6][crv]);}
	); 		
	if(~step2 == 23, 
		{s.sendMsg("n_set", 1015, \gates, 1, \atk, makenv1[7][atk], \dec, makenv1[7][dec], \sus, makenv1[7][sus], \rel, makenv1[7][rel], \crv, makenv1[7][crv]);}
	); //vocoder
	if(~step2 == 33, 
		{s.sendMsg("n_set", 1015, \gates, 1, \atk, makenv1[8][atk], \dec, makenv1[8][dec], \sus, makenv1[8][sus], \rel, rrand(2.2,2.6), \crv,[-3,-4].choose);}
	);	
	if(~step2 == 69, 
		{~globamp2 = ~globamp1*(initGateperc/initGaterev);
		volgateperc = ~globamp2;
		mid = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(~globamp2.ampdb).linlin(0,1,0,127).round(1);
		control.control(0, 82, mid);
		s.sendMsg("n_set",1018, \gates, 1, \amp, vel.linlin(0,127,0,10), \justfreq, justnote, \globamp, ~globamp2, \atk, makenv2[3][atk], \dec, makenv2[3][dec], \sus, makenv2[3][sus], \rel, makenv2[3][rel], \crv, makenv2[3][crv], \adjVol, 1.1)}
	); //start gateperc
	if(~step2 == 79, 
		{s.sendMsg("n_set",1018, \gates, 1, \atk, makenv2[4][atk], \dec, makenv2[4][dec], \sus, makenv2[4][sus], \rel, makenv2[4][rel], \crv, makenv2[4][crv]);}
	); //gateperc
	if(~step2 == 117,
		{s.sendMsg("n_set",1018, \gates, 1, \atk, makenv2[5][atk], \dec, makenv2[5][dec], \sus, makenv2[5][sus], \rel, makenv2[5][rel], \crv, makenv2[5][crv]);}
	);
	if((~step2 > 69).and(~step2 < 130).and(~step2.odd), 
		{s.sendMsg("n_set", 1018, \justfreq, justnote, \amp, vel.linlin(0,127,0,10));}
	); //gateperc
	if(~step2 == 129, 
		{s.sendMsg("b_allocRead", 15, documentPath ++ "/samples/chords/chord3la_do.aif");}
		);	
	if(~step2 == 131, 
		{~globamp2 = volorch;
		mid = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(~globamp2.ampdb).linlin(0,1,0,127).round(1);
		control.control(0, 82, mid);
		s.sendMsg("s_new", "pianorch", 1029, 0, 2004, \out, 1, \bufnum, 15, \amp, vel.linlin(0, 127, 0, 1), \start, 0.0, \rel, makenv6[0][rel], \crv, makenv6[0][crv], \globamp, ~globamp2, \adjVol, 3.0);}
	); //pianorch, free gateperc
	if(~step2 == 137, 
		{s.sendMsg("b_allocRead", 19, documentPath ++ "/samples/chords/chord5do_si_la.aif");}
	); //prepare pianorch
	if(~step2 == 139, 
		{s.sendMsg("s_new", "pianorch", 1028, 0, 2004, \out, 1, \gates, 1, \bufnum, 19, \amp, vel.linlin(0, 127, 0, 1), \start, 0.0, \rel, makenv6[1][rel], \crv, makenv6[1][crv], \globamp, ~globamp2, \adjVol, 1.0);}
	); //pianorch
	if(~step2 == 145, 
		{s.sendMsg("b_allocRead", 15, documentPath ++ "/samples/chords/chord7la_arriba.aif");
		s.sendMsg("n_free", 1018);}
	); //prepare pianorch
	if(~step2 == 147, 
		{s.sendMsg("n_set", 1028, \gates, 0);
		s.sendMsg("s_new", "pianorch", 1029, 0, 2004, \out, 1, \gates, 1, \bufnum, 15, \amp, vel.linlin(0, 127, 0, 1), \start, 0.0, \rel, makenv6[2][rel], \crv, makenv6[2][crv], \globamp, ~globamp2, \adjVol, 2.0);}
	); //pianorch
	if(~step2 == 151, 
		{s.sendMsg("b_allocRead", 19, documentPath ++ "/samples/chords/chord8si_do_la.aif");}
	); //pianorch
	if(~step2 == 153, 
		{s.sendMsg("s_new", "pianorch", 1028, 0, 2004, \out, 1, \gates, 1, \bufnum, 19, \amp, vel.linlin(0, 127, 0, 1.0), \start, 0.0, \rel, makenv6[3][rel], \crv, makenv6[3][crv], \globamp, ~globamp2, \adjVol, 2.0);}
	); //pianorch
	if(~step2 == 155, 
		{s.sendMsg("b_allocRead", 15, documentPath ++ "/samples/chords/chord9si_sol.aif");}
	);
	if(~step2 == 159, 
		{s.sendMsg("n_set", 1028, \gates, 0); s.sendMsg("s_new", "pianorch", 1029, 0, 2004, \out, 1, \gates, 1, \bufnum, 15, \amp, vel.linlin(0, 127, 0, 1.0), \start, 0.0, \rel, makenv6[4][rel], \crv, makenv6[4][crv], \globamp, ~globamp2, \adjVol, 2.0);}
	); //pianorch		
	if(~step2 == 163, 
		{
		~globamp2 = volgate;
		mid = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(~globamp2.ampdb).linlin(0,1,0,127).round(1);
		control.control(0, 82, mid);
		s.sendMsg("n_set",1017, \out, 1, \oct, 8, \amp, vel.linlin(0, 127, 0, 15.0)*0.75, \justfreq, justnote, \gates, 1, \specgate, 4.0, \atk, makenv2[7][atk], \dec, makenv2[7][dec], \sus, makenv2[7][sus], \rel, makenv2[7][rel], \crv, makenv2[7][crv], \globamp, ~globamp2);	
		}
	); //start gaterev
	if((~step2 > 163).and(~step2 < 243).and(~step2.odd), 
		{s.sendMsg("n_set",1017, \amp, vel.linlin(0, 127, 0, 15.0)*0.75, \justfreq, justnote);}
	);
	if(~step2 == 173, 
		{s.sendMsg("n_set",1017,\gates, 1, \atk, makenv2[8][atk], \dec, makenv2[8][dec], \sus, makenv2[8][sus], \rel, makenv2[8][rel], \crv, makenv2[8][crv]);
		s.sendMsg("n_free", 1028);
		s.sendMsg("n_free", 1029);}
	);
	if(~step2 == 183, 
		{s.sendMsg("n_set",1017,\gates, 1, \atk, makenv2[9][atk], \dec, makenv2[9][dec], \sus, makenv2[9][sus], \rel, makenv2[9][rel], \crv, makenv2[9][crv]);}
	);
	if(~step2 == 223, 
		{s.sendMsg("n_set",1017,\gates, 1, \atk, makenv2[10][atk], \dec, makenv2[10][dec], \sus, makenv2[10][sus], \rel, makenv2[10][rel], \crv, makenv2[10][crv]);
		s.sendMsg("n_free", 1003); //take this off for whole mix...
		}
	);
	if(~step2 == 243, 
		{
		~globamp2 = ~globamp3*(initAntony/initGateperc);
		mid = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(~globamp2.ampdb).linlin(0,1,0,127).round(1);
		control.control(0, 82, mid);
		
		s.sendMsg("/s_new", \mcBus, 1034, 0, 2001, \in, 18, \out, 1);
		s.sendMsg("/s_new","bandinstrument", 1030, 0, 2000, \out, 18, \anabuf, 30, \soundbuf, 3, \cut, 0.0002, \vol, vel.linlin(0, 127, 0, 0.8), \midi, justnote*0.5, \oct, 1, \atk, makenv4[0][atk], \dec, makenv4[0][dec], \sus, makenv4[0][sus], \rel, makenv4[0][rel], \crv, makenv4[0][crv], \globamp, ~globamp2); 
		}
	); //bandpass instrument
	if((~step2 > 243).and(~step2 <= 368).and(~step2.odd), 
		{s.sendMsg("n_set", 1030, \midi, justnote*0.5, \vol, vel.linlin(0, 127, 0, 0.8));}
	); 
	if(~step2 == 257, 
		{s.sendMsg("n_set", 1030, \gates, 1, \atk, makenv4[1][atk], \dec, makenv4[1][dec], \sus, makenv4[1][sus], \rel, makenv4[1][rel], \crv, makenv4[1][crv]);}
	);
	if(~step2 == 265, 
		{s.sendMsg("n_set", 1030, \gates, 1, \atk, makenv4[2][atk], \dec, makenv4[2][dec], \sus, makenv4[2][sus], \rel, makenv4[2][rel], \crv, makenv4[2][crv]);}
	);
	if(~step2 == 279, 
		{s.sendMsg("n_set", 1030, \gates, 1, \atk, makenv4[3][atk], \dec, makenv4[3][dec], \sus, makenv4[3][sus], \rel, makenv4[3][rel], \crv, makenv4[3][crv]);}
	);
	if(~step2 == 289, 
		{s.sendMsg("n_set", 1030, \gates, 1, \atk, makenv4[4][atk], \dec, makenv4[4][dec], \sus, makenv4[4][sus], \rel, makenv4[4][rel], \crv, makenv4[4][crv]);}
	);
	if(~step2 == 299, 
		{s.sendMsg("n_set", 1030, \gates, 1, \atk, makenv4[5][atk], \dec, makenv4[5][dec], \sus, makenv4[5][sus], \rel, makenv4[5][rel], \crv, makenv4[5][crv]);}
	);
	if(~step2 == 309, 
		{s.sendMsg("n_set", 1030, \gates, 1, \atk, makenv4[6][atk], \dec, makenv4[6][dec], \sus, makenv4[6][sus], \rel, makenv4[6][rel], \crv, makenv4[6][crv]);}
	);
	if(~step2 == 349, 
		{s.sendMsg("n_set", 1030, \gates, 1, \atk, makenv4[7][atk], \dec, makenv4[7][dec], \sus, makenv4[7][sus], \rel, makenv4[7][rel], \crv, makenv4[7][crv]);}
	);
				
		
	//noteOff
	if((~step2 > 1).and(~step2 <= 13).and(~step2.even), 
		{s.sendMsg("n_set", 1015, \pitch, (41.663256630967-((~step2*0.35)/13)));}
	); //detune vocoder
	if((~step2 > 13).and(~step2 < 68).and(~step2.even), 
		{s.sendMsg("n_set", 1015, \pitch, (41.313256630967-((~step2-13*0.65)/(49-13))));}
	); //detune vocoder
	if(~step2 == 14, 
		{s.sendMsg("n_set", 1015, \gates, 0);}
	); 
	if(~step2 == 18, 
		{s.sendMsg("n_set", 1015, \gates, 0);}
	); 
	if(~step2 == 22, 
		{s.sendMsg("n_set", 1015, \gates, 0);}
	); //vocoder
	if(~step2 == 32, 
		{s.sendMsg("n_set", 1015, \gates, 0);}
	);
	if(~step2 == 68, 
		{s.sendMsg("n_set", 1015, \gates, 0);}
	); 
	if(~step2 == 78, 
		{s.sendMsg("n_set",1018, \gates, 0);}
	); //gateperc
	if(~step2 == 116, 
		{s.sendMsg("n_set",1018, \gates, 0);}
	);
	if(~step2 == 130, 
		{s.sendMsg("n_set", 1018, \gates, 0);}
	); //gateperc off
	if(~step2 == 138, 
		{s.sendMsg("n_set", 1029, \gates, 0);}); //pianorch off
	if(~step2 == 152, 
		{s.sendMsg("n_set", 1029, \gates, 0);}
	); //pianorch off
	if(~step2 == 162, 
		{s.sendMsg("n_set", 1029, \gates, 0);}
	); //pianorch off
	if(~step2 == 172, 
		{s.sendMsg("n_set",1017, \gates, 0);}
	);  //gaterev
	if(~step2 == 182, 
		{s.sendMsg("n_set",1017, \gates, 0);}
	);  //gaterev
	if(~step2 == 222, 
		{s.sendMsg("n_set",1017,\gates, 0);}
	); //gaterev off
	if(~step2 == 244, 
		{s.sendMsg("n_set",1017,\gates, 0);}
	); //gaterev off
	if(~step2 == 248, 
		{s.sendMsg("n_free", 1017);}
	); //gaterev byebye
	if(~step2 == 256, 
		{s.sendMsg("n_set", 1030, \gates, 0);}
	);//bandinstrument off
	if(~step2 == 264, 
		{s.sendMsg("n_set", 1030, \gates, 0);}
	);
	if(~step2 == 278, 
		{s.sendMsg("n_set", 1030, \gates, 0);}
	);
	if(~step2 == 288, 
		{s.sendMsg("n_set", 1030, \gates, 0);}
	);
	if(~step2 == 298, 
		{s.sendMsg("n_set", 1030, \gates, 0);}
	);
	if(~step2 == 308, 
		{s.sendMsg("n_set", 1030, \gates, 0);}
	);
	if(~step2 == 348, 
		{s.sendMsg("n_set", 1030, \gates, 0);}
	);
	if(~step2 == 368, 
		{s.sendMsg("n_set", 1030, \gates, 0);}
	);
		
	//end noteOff
	
}

alto2Score { arg note, vel;
var justnote, mid;
	justnote = note.midijust(415);
	~step3.post; '/A2'.postln;
	
	//noteOn
	
	if((~step3 >= 51).and(~step3 < 123).and(~step3.odd), 
		{s.sendMsg("n_set", 1013, \midi, justnote, \velo, vel.linlin(0,127,0, 1), \amp2, 1000);}
	); //transpenv
	if(~step3 == 51, 
		{
		~globamp3 = ~globamp2*initVoc2;
		mid = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(~globamp3.ampdb).linlin(0,1,0,127).round(1);
		control.control(0, 83, mid);
		s.sendMsg("n_set", 1015, \gates, 1,\out, 2, \atk, rrand(1.1, 2.55), \dec, makenv5[0][dec], \sus, makenv5[0][sus], \rel, makenv5[0][rel], \crv, makenv5[0][crv], \amp, 150, \qrq, 0.001, \transp, 10.015082956259*8, \globamp, ~globamp3, \adjVol, 2.0);
		s.sendMsg("/n_set", 2005, "white", 1, "bins", 1010, "amp", 500.0, "gates", 1);
		control.control(0, 3, 127);
}
	);
	if((~step3 >= 51).and(~step3 < 108).and(~step3.odd), 
		{s.sendMsg("n_set", 1015,  \pitch, (45.229165721876-((~step3-51)*0.054748376623375)));}
	); //vocoder
	if(~step3 == 55, 
		{s.sendMsg("n_set", 1015, \gates, 1, \atk, makenv5[1][atk], \dec, makenv5[1][dec], \sus, makenv5[1][sus], \rel, makenv5[1][rel], \crv, makenv5[1][crv]);}
	);
	if(~step3 == 65, 
		{s.sendMsg("n_set", 1015, \gates, 1, \atk, makenv5[2][atk], \dec, makenv5[2][dec], \sus, makenv5[2][sus], \rel, makenv5[2][rel], \crv, makenv5[2][crv]);}
	);
	if(~step3 == 99, 
		{s.sendMsg("n_set", 1015, \gates, 1, \atk, makenv5[3][atk], \dec, makenv5[3][dec], \sus, makenv5[3][sus], \rel, makenv5[3][rel], \crv, makenv5[3][crv]);}
	);
	if(~step3 == 109, 
		{s.sendMsg("n_set", 1015, \gates, 1, \atk, makenv5[4][atk], \dec, makenv5[4][dec], \sus, makenv5[4][sus], \rel, rrand(2.2,2.6), \crv,[-3,-4].choose, \pitch, 42.163256630967);}
	); //vocoder pitch
	if((~step3 > 108).and(~step3 < 122).and(~step3.odd), 
		{s.sendMsg("n_set", 1015, \transp, 10.015082956259*8, \pitch, 42.163256630967);}
	); //vocoder
	if(~step3 == 123, 
		{s.sendBundle(0.1, ["n_free", 1008],["n_free", 1013],["n_free", 1015]);}
	); //vocoder nodes off
	if(~step3 == 127, 
		{s.sendMsg("/b_alloc", 9, 2048, 1); 
		s.sendMsg("/b_alloc", 6, 2048, 1);}
	); //prepare gateperc mic
	if(~step3 == 131, 
		{
		~globamp3 = volgateperc;
		mid = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(~globamp3.ampdb).linlin(0,1,0,127).round(1);
		control.control(0, 83, mid);

		s.sendMsg("/s_new", "gatingmic", 1031, 1, 2003, \out, 2, \detectbuf, 9, \justfreq,justnote, \bufnum, 6, \soundbuf, 8, \gates, 1, \start, rrand(0, 6399418), \oct, 0.35, \amp, vel.linlin(0,127,0,2.75), \threshhold, 0.1, \atk, makenv2[11][atk], \dec, makenv2[11][dec], \sus, makenv2[11][sus], \rel, makenv2[11][rel], \crv, makenv2[11][crv], \globamp, ~globamp3, \adjVol, 1.2); }
	); //start gateperc mic
	if((~step3 > 131).and(~step3 < 243).and(~step3.odd), 
		{s.sendMsg("n_set", 1031, \justfreq, justnote);}
	); //gateperc mic
	if((~step3 > 131).and(~step3 < 377).and(~step3.odd), 
		{s.sendMsg("n_set", 1031, \amp, vel.linlin(0,127,0,2.75));}
	); //vel for gateperc
	if(~step3 == 145, 
		{s.sendMsg("n_set",1031, \oct, 0.5, \gates, 1, \atk, makenv2[12][atk], \dec, makenv2[12][dec], \sus, makenv2[12][sus], \rel, makenv2[12][rel], \crv, makenv2[12][crv]);}
	);
	if(~step3 == 155, 
		{s.sendMsg("n_set", 1031, \oct, 0.35, \gates, 1, \atk, makenv2[13][atk], \dec, makenv2[13][dec], \sus, makenv2[13][sus], \rel, makenv2[13][rel], \crv, makenv2[13][crv], \amp, vel.linlin(0,127,0,2.5));}
	);
	if(~step3 == 183, 
		{s.sendMsg("n_set",1031, \gates, 1, \atk, makenv2[14][atk], \dec, makenv2[14][dec], \sus, makenv2[14][sus], \rel, makenv2[14][rel], \crv, makenv2[14][crv]);}
	);
	if(~step3 == 243, 
		{s.sendMsg("n_set", 1031, \gates, 1, \atk, makenv2[15][atk], \dec, makenv2[15][dec], \sus, makenv2[15][sus], \rel, makenv2[15][rel], \crv, makenv2[15][crv], \justfreq, 276.66666666667, \amp, vel.linlin(0,127,0,2.75));}
	); //gateperc mic
	
			
	//noteOff
		
	if((~step3 >= 51).and(~step3 < 108).and(~step3.even), 
		{s.sendMsg("n_set", 1015, \pitch, (45.229165721876-((~step3-51)*0.054748376623375)));}
	); //vocoder
	if(~step3 == 54, 
		{s.sendMsg("n_set", 1015, \gates, 0);}
	);
	if(~step3 == 64, 
		{s.sendMsg("n_set", 1015, \gates, 0);}
	);
	if(~step3 == 98, 
		{s.sendMsg("n_set", 1015, \gates, 0);}
	);
	if(~step3 == 108, 
		{s.sendMsg("n_set", 1015, \gates, 0, \pitch, 42.163256630967);}
	); //vocoder pitch
	if(~step3 == 122, 
		{s.sendMsg("n_set", 1015, \gates, 0);}
	); //vocoder off
	if(~step3 == 144, 
		{s.sendMsg("n_set", 1031, \gates, 0);}
	);
	if(~step3 == 154, 
		{s.sendMsg("n_set", 1031, \gates, 0);}
	);
	if(~step3 == 182, 
		{s.sendMsg("n_set", 1031, \gates, 0);}
	);
	if(~step3 == 242, 
		{s.sendMsg("n_set", 1031, \gates, 0);}
	);
		
	//end noteOff

}

tenorScore { arg note, vel;
	var justnote, mid;
	justnote = note.midijust(415);
	~step4.post; '/T'.postln;
	
	//noteOn

	if((~step4 >= 61).and(~step4 < 113).and(~step4.odd), 
		{s.sendMsg("n_set", 1012, \midi, justnote, \velo, vel.linlin(0,127,0,1), \amp2, 1000);
		vel;
		}
	); //transpenv
	if(~step4 == 61, 
		{~globamp4 = ~globamp2*initVoc2;
		mid = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(~globamp4.ampdb).linlin(0,1,0,127).round(1);
		control.control(0, 84, mid);
		s.sendMsg("n_set", 1014, \gates, 1, \out, 3, \amp, 150, \qrq, 0.001, \transp, 2.25, \atk, rrand(0.45, 0.55), \dec, makenv5[5][dec], \sus, makenv5[5][sus], \rel, makenv5[5][rel], \crv, makenv5[5][crv], \globamp, ~globamp4, \adjVol, 2.0)}
	); //vocoder
	if((~step4 >= 61).and(~step4 < 113).and(~step4.odd), 
		{s.sendMsg("n_set", 1014, \pitch, (40.397347540058+((~step4-61)*0.03531818181818)));}
	); //vocoder detune
	if(~step4 == 65, 
		{s.sendMsg("n_set", 1014, \gates, 1, \atk, makenv5[6][atk], \dec, makenv5[6][dec], \sus, makenv5[6][sus], \rel, makenv5[6][rel], \crv, makenv5[6][crv])}
	); //vocoder on
	if(~step4 == 75, 
	 	{s.sendMsg("n_set", 1014, \gates, 1, \atk, makenv5[7][atk], \dec, makenv5[7][dec], \sus, makenv5[7][sus], \rel, makenv5[7][rel], \crv, makenv5[7][crv])}
	 ); //vocoder on
	if(~step4 == 87, 
	 	{s.sendMsg("n_set", 1014, \gates, 1, \atk, makenv5[8][atk], \dec, makenv5[8][dec], \sus, makenv5[8][sus], \rel, makenv5[8][rel], \crv, makenv5[8][crv])}
	 ); 
	if(~step4 == 97, 
	 	{s.sendMsg("n_set", 1014, \gates, 1, \atk, makenv5[9][atk], \dec, makenv5[9][dec], \sus, makenv5[9][sus], \rel, rrand(2.2,2.6), \crv,[-3,-4].choose)}
	 ); 
	if(~step4 == 111, 
		{s.sendBundle(0.1, [\b_allocRead, 13, documentPath ++ "/samples/chords/chord1re_fa_do.aif"], [\b_allocRead, 14, documentPath ++ "/samples/chords/chord2do_re.aif"]);}
	); //vocoder, prepare pianorch
	if(~step4 == 113, 
		{~globamp4 = ~globamp1*(initOrchfirst/initHighband);
		mid = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(~globamp4.ampdb).linlin(0,1,0,127).round(1);
		control.control(0, 84, mid);
		s.sendMsg("n_free", 1007); 
		s.sendMsg("s_new", "pianorch", 1027, 0, 2002, \out, 3, \bufnum, 13, \amp, vel.linlin(0,127,0,2), \start, 0.0, \rel, makenv6[5][rel], \crv, makenv6[5][crv], \globamp, ~globamp4, \adjVol, 1.8);}
	); //vocoder nodes off, pianorch
	if(~step4 == 115, 
		{s.sendMsg("n_free", 1012);}
	); //vocoder off
	if(~step4 == 121, 
		{~globamp4 = ~globamp1*(initOrchords/initHighband);
		volorch = ~globamp4;
		mid = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(~globamp4.ampdb).linlin(0,1,0,127).round(1);
		control.control(0, 84, mid);
		s.sendMsg("s_new", "pianorch", 1127, 0, 2002, \out, 3, \bufnum, 14, \amp, vel.linlin(0,127,0,1), \start, 0.0, \rel, makenv6[6][rel], \crv, makenv6[6][crv], \globamp, ~globamp4, \adjVol, 1.8);}
	); //pianorch
	if(~step4 == 127, 
	{s.sendMsg("b_free", 13);}
	);
	if(~step4 == 129, 
		{s.sendMsg("n_free", 1014);
		s.sendMsg("s_new", "pianorch", 1128, 0, 2002, \out, 3, \bufnum, 130, \amp, vel.linlin(0,127,0,1), \start, 0.0, \rel, makenv6[7][rel], \crv, makenv6[7][crv], \globamp, ~globamp4, \adjVol, 1.8);
	}
	); //pianorch
	if(~step4 == 135, 
		{s.sendMsg("b_free", 14);}
	); //prepare pianorch
	if(~step4 == 139, 
		{s.sendMsg("n_set", 1128, \gates, 0); 
		s.sendMsg("s_new", "pianorch", 1026, 0, 2002, \out, 3, \gates, 1, \bufnum, 131, \amp, vel.linlin(0, 127, 0, 1), \start, 0.0, \rel, rrand(3.5,3.1), \crv, makenv6[8][crv], \globamp, ~globamp4, \adjVol, 1.8);}
	);
	if(~step4 == 141, 
		{s.sendMsg("b_free", 130);}
	);
	if(~step4 == 143, 
		{~globamp4 = ~globamp1*(initOrchmel/initGaterev);
		volorchmel = ~globamp4;
		mid = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(~globamp4.ampdb).linlin(0,1,0,127).round(1);
		control.control(0, 84, mid);
		s.sendMsg("s_new", "pianorch", 1027, 0, 2002, \out, 3, \bufnum, 132, \amp, vel.linlin(0,127,0,1), \start, 0.0, \rel, rrand(1.18,0.56), \crv, makenv6[9][crv], \globamp, ~globamp4, \adjVol, 4.0);}
	);
	if(~step4 == 145, 
		{s.sendMsg("n_set", 1027, \gates, 0); 
		s.sendMsg("s_new", "pianorch", 1127, 0, 2002, \out, 3, \bufnum, 133, \amp, vel.linlin(0,127,0,1), \start, 0.0, \rel, rrand(1.18,0.56), \crv, makenv6[10][crv], \globamp, ~globamp4, \adjVol, 4.0);}
	);
	if(~step4 == 147, 
		{s.sendMsg("n_set", 1127, \gates, 0);  
		s.sendMsg("s_new", "pianorch", 1126, 0, 2002, \out, 3, \bufnum, 134, \amp, vel.linlin(0,127,0,1), \start, 0.0, \rel, rrand(1.18,0.56), \crv, makenv6[11][crv], \globamp, ~globamp4, \adjVol, 4.0);
		s.sendBundle(0.1, [\b_free, 131], [\b_free, 132]);
		}
	);
	if(~step4 == 149, 
		{s.sendMsg("n_set", 1126, \gates, 0); 
		s.sendMsg("s_new", "pianorch", 1026, 0, 2002, \out, 3, \bufnum, 135, \amp, vel.linlin(0,127,0,1), \start, 0.0, \rel, rrand(1.18,0.56), \crv, makenv6[12][crv], \globamp, ~globamp4, \adjVol, 4.0);}
	);
	if(~step4 == 151, 
		{s.sendMsg("n_set", 1026, \gates, 0);
		s.sendMsg("s_new", "pianorch", 1027, 0, 2002, \out, 3, \bufnum, 136, \amp, vel.linlin(0,127,0,1), \start, 0.0, \rel, rrand(1.18,1.26), \crv, makenv6[13][crv], \globamp, ~globamp4, \adjVol, 4.0);
		s.sendBundle(0.1, [\b_free, 133], [\b_free, 134]);
		}
	);
	if(~step4 == 153, 
		{s.sendMsg("n_set", 1027, \gates, 0);
		s.sendMsg("s_new", "pianorch", 1128, 0, 2002, \out, 3, \bufnum, 137, \amp, vel.linlin(0,127,0,1), \start, 0.0, \rel, makenv6[14][rel], \crv, makenv6[14][crv], \globamp, ~globamp4, \adjVol, 4.0);}
	);
	if(~step4 == 161, 
		{s.sendMsg("n_set", 1128, \gates, 0); 
		s.sendMsg("s_new", "pianorch", 1127, 0, 2002, \out, 3, \bufnum, 138, \amp, vel.linlin(0,127,0,1), \start, 0.0, \rel, makenv6[15][rel], \crv, makenv6[15][crv], \globamp, ~globamp4, \adjVol, 4.0);}
	);
	if(~step4 == 171, 
		{
		~globamp4 = ~globamp3*(initCel/initGateperc);
		mid = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(~globamp4.ampdb).linlin(0,1,0,127).round(1);
		control.control(0, 84, mid);
		s.sendMsg("s_new", "celestapurebuf", 1100, 0, 2002, \pitch, 221.33333333333*[8,2,2,4,2,4].choose, \amp, vel.linlin(0,127, 0,0.4), \out, 3, \globamp, ~globamp4, \adjVol, 1.2); 
		s.sendMsg("s_new", "celestapurebuf", 1101, 0, 2002, \pitch, 186.75*[8,2,2,4,2,4].choose, \amp, vel.linlin(0,127, 0,0.4), \out, 3, \globamp, ~globamp4, \adjVol, 1.2); 
		s.sendMsg("s_new", "celestapurebuf", 1102, 0, 2002, \pitch, 207.5*[8,2,2,4,2,4].choose, \amp, vel.linlin(0,127, 0,0.4), \out, 3, \globamp, ~globamp4, \adjVol, 1.2); 
		s.sendMsg("s_new", "celestapurebuf", 1103, 0, 2002, \pitch, 249*[8,2,2,4,2,4].choose, \amp, vel.linlin(0,127, 0,0.4), \out, 3, \globamp, ~globamp4, \adjVol, 1.2); 
		s.sendMsg("s_new", "celestapurebuf", 1104, 0, 2002, \pitch, 166*[8,2,2,4,2,4].choose, \amp, vel.linlin(0,127, 0,0.4), \out, 3, \globamp, ~globamp4, \adjVol, 1.2);}
	); //acordes celesta
	if(~step4 == 177,
		{s.sendBundle(0.1, [\b_free, 135], [\b_free, 136], [\b_free, 137], [\b_free, 138]);}
	);
	if(~step4 == 181, 
		{s.sendMsg("s_new", "celestapurebuf", 1105, 0, 2002, \pitch, 221.33333333333*[8,2,2,4,2,4].choose, \amp, vel.linlin(0,127, 0, 2), \out, 3, \globamp, ~globamp4, \adjVol, 1.2); 
		s.sendMsg("s_new", "celestapurebuf", 1106, 0, 2002, \pitch, 207.5*[8,2,2,4,2,4].choose, \amp, vel.linlin(0,127, 0, 2), \out, 3, \globamp, ~globamp4, \adjVol, 1.2);}
	);
	if(~step4 == 185, 
		{s.sendMsg("s_new", "celestapurebuf", 1107, 0, 2002, \pitch, 249*[8,2,2,4,2,4].choose, \amp, vel.linlin(0,127, 0, 4), \out, 3, \globamp, ~globamp4, \adjVol, 1.2);}
	);
	if(~step4 == 187, 
		{s.sendMsg("s_new", "celestapurebuf", 1108, 0, 2002, \pitch, 186.75*[2,2,4,2,4].choose, \amp, vel.linlin(0,127, 0, 2), \out, 3, \globamp, ~globamp4, \adjVol, 1.2);
		s.sendBundle(0.1, [\b_allocRead, 14, documentPath ++ "/samples/percussion/japercu_fa.wav"], [\b_allocRead, 15, documentPath ++ "/samples/percussion/japercu_re.wav"]);
		}
	);
	if(~step4 == 189, 
		{s.sendMsg("s_new", "percussion", 1026, 0, 2002, \out, 4, \lengh, 1, \bufnum, 14, \amp, vel.linlin(0, 127, 0, 0.5), \globamp, ~globamp4, \adjVol, 1.2); 
		s.sendMsg("s_new", "celestapurebuf", 1100, 0, 2002, \pitch, 332*[8,2,2,4,2,4].choose, \amp, vel.linlin(0,127, 0,0.4), \out, 3, \globamp, ~globamp4, \adjVol, 1.2); 
		s.sendMsg("s_new", "celestapurebuf", 1101, 0, 2002, \pitch, 276.66666666667*[8,2,2,4,2,4].choose, \amp, vel.linlin(0,127, 0,0.4), \out, 3, \globamp, ~globamp4, \adjVol, 1.2); 
		s.sendMsg("s_new", "celestapurebuf", 1102, 0, 2002, \pitch, 295.11111111111*[8,2,2,4,2,4].choose, \amp, vel.linlin(0,127, 0,0.4), \out, 3, \globamp, ~globamp4, \adjVol, 1.2); 
		s.sendMsg("s_new", "celestapurebuf", 1103, 0, 2002, \pitch, 221.33333333333*[8,2,2,4,2,4].choose, \amp, vel.linlin(0,127, 0,0.4), \out, 3, \globamp, ~globamp4, \adjVol, 1.2); 
		s.sendMsg("s_new", "celestapurebuf", 1104, 0, 2002, \pitch, 249*[8,2,2,4,2,4].choose, \amp, vel.linlin(0,127, 0,0.4), \out, 3, \globamp, ~globamp4, \adjVol, 1.2);}
	); 
	if(~step4 == 199, 
		{s.sendMsg("s_new", "celestapurebuf", 1105, 0, 2002, \pitch, 276.66666666667*[8,2,2,4,2,4].choose, \amp, vel.linlin(0,127, 0, 4), \out, 3, \globamp, ~globamp4, \adjVol, 1.2); 
		s.sendMsg("s_new", "percussion", 1027, 0, 2002, \out, 4, \lengh, 1, \bufnum, 15, \amp, vel.linlin(0, 127, 0, 0.5), \globamp, ~globamp4, \adjVol, 1.2); 
		s.sendBundle(0.1, [\b_allocRead, 13, documentPath ++ "/samples/percussion/japercu_la.wav"], [\b_allocRead, 19, documentPath ++ "/samples/percussion/japerc_sol.wav"]);}
	);
	if(~step4 == 203, 
		{s.sendMsg("s_new", "celestapurebuf", 1106, 0, 2002, \pitch, 415*[8,2,2,4,2,4].choose, \amp, vel.linlin(0,127, 0, 1), \out, 3, \globamp, ~globamp4, \adjVol, 1.2); 
		s.sendMsg("s_new", "celestapurebuf", 1107, 0, 2002, \pitch, 207.5*[8,2,2,4,2,4].choose, \amp, vel.linlin(0,127, 0, 1), \out, 3, \globamp, ~globamp4, \adjVol, 1.2); 
		s.sendMsg("s_new", "celestapurebuf", 1108, 0, 2002, \pitch, 276.66666666667*[8,2,2,4,2,4].choose, \amp, vel.linlin(0,127, 0, 1), \out, 3, \globamp, ~globamp4, \adjVol, 1.2); 
		s.sendMsg("s_new", "percussion", 1026, 0, 2002, \out, 4, \lengh, 1,  \bufnum, 13, \amp, vel.linlin(0, 127, 0, 0.5), \globamp, ~globamp4, \adjVol, 1.2); 
		s.sendBundle(0.1,[\b_allocRead, 24, documentPath ++ "/samples/percussion/japercu_si_flat.wav"],[\b_allocRead, 25, documentPath ++ "/samples/percussion/japercu_si.wav"]);}
	);
	if(~step4 == 209, 
		{s.sendMsg("s_new", "celestapurebuf", 1109, 0, 2002, \pitch, 186.75*[8,2,2,4,2,4].choose, \amp, vel.linlin(0,127, 0, 2), \out, 3, \globamp, ~globamp4, \adjVol, 1.2); 
		s.sendMsg("s_new", "celestapurebuf", 1110, 0, 2002, \pitch, 207.5*[8,2,2,4,2,4].choose, \amp, vel.linlin(0,127, 0, 2), \out, 3, \globamp, ~globamp4, \adjVol, 1.2); 
		s.sendMsg("s_new", "percussion", 1027, 0, 2002, \out, 4, \lengh, 1,  \bufnum, 19, \amp, vel.linlin(0, 127, 0, 0.5), \globamp, ~globamp4, \adjVol, 1.2);}
	);
	if(~step4 == 213, 
		{s.sendMsg("s_new", "celestapurebuf", 1100, 0, 2002, \pitch, 221.33333333333*[8,2,2,4,2,4].choose, \amp, vel.linlin(0,127, 0,0.4), \out, 3, \globamp, ~globamp4, \adjVol, 1.2); 
		s.sendMsg("s_new", "celestapurebuf", 1101, 0, 2002, \pitch, 207.5*[8,2,2,4,2,4].choose, \amp, vel.linlin(0,127, 0,0.4), \out, 3, \globamp, ~globamp4, \adjVol, 1.2); 
		s.sendMsg("s_new", "celestapurebuf", 1102, 0, 2002, \pitch, 221.33333333333*[8,2,2,4,2,4].choose, \amp, vel.linlin(0,127, 0,0.4), \out, 3, \globamp, ~globamp4, \adjVol, 1.2); 
		s.sendMsg("s_new", "celestapurebuf", 1103, 0, 2002, \pitch, 186.75*[8,2,2,4,2,4].choose, \amp, vel.linlin(0,127, 0,0.4), \out, 3, \globamp, ~globamp4, \adjVol, 1.2); 
		s.sendMsg("s_new", "celestapurebuf", 1104, 0, 2002, \pitch, 207.5*[8,2,2,4,2,4].choose, \amp, vel.linlin(0,127, 0,0.4), \out, 3, \globamp, ~globamp4, \adjVol, 1.2); 
		s.sendMsg("s_new", "percussion", 1026, 0, 2002, \out, 4, \lengh, 1, \bufnum, 24, \amp, vel.linlin(0, 127, 0, 0.5), \globamp, ~globamp4, \adjVol, 1.2);}
	);
	if(~step4 == 223, 
		{s.sendMsg("s_new", "celestapurebuf", 1105, 0, 2002, \pitch, 233.4375*[2,2,4,2,4].choose, \amp, vel.linlin(0,127, 0, 4), \out, 4, \globamp, ~globamp4, \adjVol, 1.2); 
		s.sendMsg("s_new", "percussion", 1027, 0, 2002, \out, 4, \lengh, 1, \bufnum, 25, \amp, vel.linlin(0, 127, 0, 0.5), \globamp, ~globamp4, \adjVol, 1.2);}
	);
	if(~step4 == 225, 
		{t = Routine({var counter = rrand(0.0,300); 
				inf.do({counter = counter + rrand(0.098,1.002); 
				~seconds = counter.round(0.01) * 44100; 0.01.yield;})
			}).play; 
		s.sendMsg("s_new", "celesta", 1100, 0, 2002, \out, 3, \start, ~seconds, \rate, (justnote/194.53125), \bufnum, 31, \amp, vel.linlin(0, 127, 0, 1.5), \globamp, ~globamp4, \adjVol, 1.4);}
	);
	if((~step4 > 225).and(~step4 < 337).and(~step4.odd), 
		{s.sendMsg("n_set", (~step4-225)+1098, \gates, 0); 
		 s.sendMsg("s_new", "celesta", ((~step4-225)+1100), 0, 2002, \out, 3, \start, ~seconds, \rate, (justnote/194.53125), \gates, 1, \gates2, 1, \bufnum, 31, \amp, vel.linlin(0, 127, 0, 1.5), \globamp, ~globamp4, \adjVol, 1.4);}
	);
	if(~step4 == 233, 
		{s.sendMsg("n_set", 2002, \gates2, 1);}
	); //celesta
	if(~step4 == 241, 
		{s.sendMsg("n_set", 2002, \gates2, 1);}
	); //celesta
	if(~step4 == 255, 
		{s.sendMsg("n_set", 2002, \gates2, 1);}
	); //celesta
	if(~step4 == 283, 
		{s.sendMsg("n_set", 2002, \gates2, 1);}
	); //celesta

		
	//noteOff		
	
	if(~step4 == 64, 
		{s.sendMsg("n_set", 1014, \gates, 0);}
	); //vocoder
 	if(~step4 == 74, 
 		{s.sendMsg("n_set", 1014, \gates, 0);}
 	); //vocoder
 	if(~step4 == 86, 
 		{s.sendMsg("n_set", 1014, \gates, 0);}
 	); 
	 if(~step4 == 96, 
	 	{s.sendMsg("n_set", 1014, \gates, 0);}
	 ); 
	if(~step4 == 112, 
		{s.sendMsg("n_set", 1014, \gates,0);}
	); //vocoder, prepare pianorch
	if(~step4 == 120, 
		{s.sendMsg("n_set", 1027, \gates, 0);}
	); //pianorch off
	if(~step4 == 128, 
		{s.sendMsg("n_set", 1127, \gates, 0);}
	); //prepare for pianorch
	if(~step4 == 142, 
		{s.sendMsg("n_set", 1026, \gates, 0);}
	); //pianorch off
	if(~step4 == 170, 
		{s.sendMsg("n_set", 1127, \gates, 0);}
	); //pianorch off
	if(~step4 == 232, 
		{s.sendMsg("n_set", 2002, \gates2, 0);}
	); //celesta silence
	if(~step4 == 240, 
		{s.sendMsg("n_set", 2002, \gates2, 0);}
	); //celesta silence
	if(~step4 == 254, 
		{s.sendMsg("n_set", 2002, \gates2, 0);}
	); //celesta silence
	if(~step4 == 282, 
		{s.sendMsg("n_set", 2002, \gates2, 0);}
	); //celesta silence
	if(~step4 == 300, 
		{s.sendMsg("n_set", 2002, \gates2, 0);}
	); //celesta silence
	if(~step4 == 336, 
		{s.sendMsg("n_set", 2002, \gates2, 0);}
	); //celesta silence	 	
	 	
	 
	//end noteOff
	
}

bassScore { arg note, vel; 
var justnote, mid;
	justnote = note.midijust(415);
	~step5.post; '/B'.postln;
	
	//noteOn
		
	if(~step5 == 17, 
		{s.sendMsg("/s_new", "transpenv", 1011, 0, 2003, \in, 30, \out, 4, \gates, 0);
		fourier.ffttime(2.0, 0.5)}
	); //start bandpass
	if(~step5 == 19, 
		{
		~globamp5 = ~globamp1*initLowband;
		mid = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(~globamp5.ampdb).linlin(0,1,0,127).round(1);
		control.control(0, 85, mid);
		s.sendMsg("n_set", 1011, \gates, 1, \atk, makenv3[5][atk], \dec, makenv3[5][dec], \sus, makenv3[5][sus], \rel, makenv3[5][rel], \crv, makenv3[5][crv], \globamp, ~globamp5, \adjVol, 3.0);}
	);
	if((~step5 >= 19).and(~step5 <= 35).and(~step5.odd), 
		{s.sendMsg("n_set", 1011, \midi,(justnote*(0.5+((0.5/8)*(~step5-19)))*0.5), \velo, (vel.linlin(0,127,0,1950))*(0.25+((0.75/8)*(~step5-19))));}
	); //bandpass
	if(~step5 == 37, 
		{s.sendMsg("n_set", 1011, \gates, 1, \atk, makenv3[6][atk], \dec, makenv3[6][dec], \sus, makenv3[6][sus], \rel, makenv3[6][rel], \crv, makenv3[6][crv], \globamp, ~globamp5, \adjVol, 3.0);}
	); //bandpass
	if((~step5 > 36).and(~step5 <= 47).and(~step5.odd), 
		{s.sendMsg("n_set", 1011, \midi,(justnote*0.5), \velo, vel.linlin(0,127,0,1950)); 
		fourier.ffttime((2-((~step5-37)*0.05)), 0.5)}
	); //bandspass
	if(~step5 == 47, 
		{s.sendMsg("n_set", 1011, \gates, 1, \atk, makenv3[7][atk], \dec, makenv3[7][dec], \sus, makenv3[7][sus], \rel, makenv3[7][rel], \crv, makenv3[7][crv], \globamp, ~globamp5, \adjVol, 3.0);}
	); //bandpass
	if((~step5 > 47).and(~step5 <= 77).and(~step5.odd), 
		{s.sendMsg("n_set", 1011, \midi,(justnote*0.5), \velo, vel.linlin(0,127,0,1950)); 
		fourier.ffttime((1.5+((~step5-47)*0.016666666666667)), 0.5)}
	); //bandpass
	if(~step5 == 59, 
		{s.sendMsg("n_set", 1011, \gates, 1, \atk, makenv3[8][atk], \dec, makenv3[8][dec], \sus, makenv3[8][sus], \rel, makenv3[8][rel], \crv, makenv3[8][crv], \globamp, ~globamp5, \adjVol, 3.0);}
	); //bandpass
	if(~step5 == 79, 
		{~globamp6 = ~globamp1*(initPiano/initGaterev);
		mid = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(~globamp6.ampdb).linlin(0,1,0,127).round(1);
		control.control(0, 86, mid);
		s.sendMsg(\n_set, 1811, \globamp, ~globamp6);
		s.sendBundle(0.1, ["n_free", 1009],["n_free", 1010], ["n_free", 1011]); 
		s.sendMsg("/s_new", "pianogatebuf", 1025, 0, 2003, \bufnum, 5, \soundbuf, 3, \justfreq, justnote, \gates, 1, \specgate, 0.01, \start, rrand(0,6060560), \oct, 4);}
	); //midipiano
	if((~step5 > 79).and(~step5 < 93).and(~step5.odd), 
		{s.sendMsg("n_set", 1025, \justfreq, justnote);}
	);
	if(~step5 == 89,
		{s.sendMsg("n_free", 1002)};
	);
	if(~step5 == 93, 
		{s.sendMsg("n_set", 1024, \gates, 1, \atk, makenv3[9][atk], \dec, makenv3[9][dec], \sus, makenv3[9][sus], \rel, makenv3[9][rel], \crv, makenv3[9][crv], \globamp, ~globamp5, \adjVol, 3.0);}
	); //bandpass2
	if((~step5 >= 93).and(~step5 < 100).and(~step5.odd), 
		{s.sendMsg("n_set", 1024, \midi, (justnote*0.5), \velo, vel.linlin(0,127,0,1950), \out, 4); 
		fourier2.ffttime( (0.092879818594104+((~step5-92)*0.092879818594104)), 0.5 );}
	); //bandpass2
	if(~step5 == 101,
		{s.sendMsg("/n_set", 1025, \gates, 1);}
	); //midipiano
	if((~step5 > 101).and(~step5 < 115).and(~step5.odd), 
		{s.sendMsg("n_set", 1025, \justfreq, justnote);}
	);
	if(~step5 == 115, 
		{s.sendMsg("n_set", 1024, \gates, 1, \atk, makenv3[10][atk], \dec, makenv3[10][dec], \sus, makenv3[10][sus], \rel, makenv3[10][rel], \crv, makenv3[10][crv], \globamp, ~globamp5, \adjVol, 3.0);}
	); //bandpass2
	if((~step5 >= 115).and(~step5 < 124).and(~step5.odd), 
		{s.sendMsg("n_set", 1024, \midi, (justnote*0.5),  \velo, vel.linlin(0,127,0,1950), \out, 4); 
		fourier2.ffttime( (0.092879818594104+((~step5-92)*0.092879818594104)) , 0.5);}
	); //bandpass2
	if(~step5 == 125, 
		{s.sendMsg("/n_set", 1025, \gates, 1);}
	);
	if((~step5 > 125).and(~step5 < 159), 
		{s.sendMsg("n_set", 1025, \justfreq, justnote);}
	);
	if(~step5 == 135, 
		{s.sendMsg("n_set", 1025, \gates, 1, \oct, 4);}
	); 
	if(~step5 == 145, 
		{s.sendMsg("n_set", 1025, \gates, 1, \oct, 2);}
	); 
	if(~step5 == 159, 
		{s.sendMsg("n_free", 1025); 
		s.sendMsg("/s_new", "pianogatemicbuf", 1002, 1, 2003, \out, 0, \justfreq, justnote, \bufnum, 5, \soundbuf, 3, \gates, 1, \specgate,6.0); 
		~bassPiano = Task({
					1.do({var note, vel, midinote, velo, midinote1;
					s.sendMsg("n_set", 1002, \gates, 1);
					rrand(0.1,0.7).yield; 
					s.sendMsg("n_set", 1002, \gates, 0);})
				}).play;}
			); //midipiano as bass
	if((~step5 > 159).and(~step5 < 360), 
		{s.sendMsg("n_set", 1002, \justfreq, justnote);}
	);
	if(~step5 == 161, 
		{~bassPiano.stop; ~bassPiano.start;}
	);
	if(~step5 == 163, 
		{~bassPiano.stop; ~bassPiano.start;}
	);
	if(~step5 == 165, 
		{~bassPiano.stop; ~bassPiano.start;}
	);
	if(~step5 == 167, 
		{~bassPiano.stop; ~bassPiano.start;}
	);
	if(~step5 == 169, 
		{~bassPiano.stop; ~bassPiano.start;}
	);
	if(~step5 == 171, 
		{~bassPiano.stop; 
		~bassPiano.start; 
		s.sendBundle(0.1, [\b_allocRead, 14, documentPath ++ "/samples/percussion/highmetal.aif"], [\b_allocRead, 15, documentPath ++ "/samples/percussion/bombo2.aif"]); 
		~bombocount = Routine({~bombonode = 1099; 
			{ ~bombonode = ~bombonode + 2; 
			~bombonode; 
			nil.yield;}.loop; });
		}
		); //prepare for midipiano as bass and for perc
	if(~step5 == 173, 
		{
		~globamp5 = ~globamp3*(initBombo/initGateperc);
		mid = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(~globamp5.ampdb).linlin(0,1,0,127).round(1);
		control.control(0, 85, mid);
		
		h.latency = 0.0; 
		~bassPiano.stop; 
		~bassPiano.start; 
		s.sendMsg("s_new", "percussion", 1010, 0, 2004, \out, 3, \lengh, 2.0, \bufnum, 14, \amp, vel.linlin(0, 127, 0, 0.75), \globamp, ~globamp5, \adjVol, 0.7); 
		s.sendMsg("s_new", "percussion", 1011, 0, 2004, \out, 4, \lengh, 1.0, \bufnum, 15, \amp, vel.linlin(0, 127, 0, 0.75), \globamp, ~globamp5, \adjVol, 0.9);}
	); 
	if(~step5 == 187, 
		{osc.add}
	); //piano bass
	if(~step5 == 195, 
		{osc.add; s.sendMsg("b_free", 14);}
	); 
	if(~step5 == 209, 
		{osc.add}
	); 
	if(~step5 == 219, 
		{osc.add}
	); 
	if(~step5 == 229, 
		{osc.add}
	);
	if(~step5 == 239, 
		{osc.add}
	);
	if(~step5 == 253, 
		{osc.add}
	);
		
		
	//noteOff
	
	if((~step5 >= 19).and(~step5 <= 35).and(~step5.even), 
		{s.sendMsg("n_set", 1011, \midi,(justnote*(0.5+((0.5/8)*(~step5-19)))*0.5), \velo, (vel.linlin(0,127,0,1950))*(0.25+((0.75/8)*(~step5-19))));}
	); //bandpass
	if(~step5 == 36, 
		{s.sendMsg("n_set", 1011, \gates, 0);}
	); //bandpass
	if((~step5 > 36).and(~step5 <= 47).and(~step5.even), 
		{fourier.ffttime( (2-((~step5-37)*0.05)) , 0.5)}
	); //bandspass
	if(~step5 == 46, 
		{s.sendMsg("n_set", 1011, \gates, 0);}
	); //bandpass
	if((~step5 > 47).and(~step5 <= 77).and(~step5.even), 
		{fourier.ffttime( (1.5+((~step5-47)*0.016666666666667)) , 0.5)}
	); //bandpass
	if(~step5 == 58, 
		{s.sendMsg("n_set", 1011, \gates, 0);}
	); //bandpass
	if(~step5 == 78, 
		{s.sendMsg("n_set", 1011, \gates, 0); 
		fourier.stoptask; 
		osc.add;}
	); //stop fourier task and bandpass, set OSC message for midipiano 
	if(~step5 == 92, 
		{s.sendMsg("/n_set", 1025, \gates, 0);}
	); //midipiano off
	if((~step5 >= 93).and(~step5 < 100).and(~step5.even), 
		{fourier2.ffttime( (0.092879818594104+((~step5-92)*0.092879818594104)), 0.5 );}
	); 
	if(~step5 == 100, 
		{s.sendMsg("n_set", 1024, \gates, 0);}
	); //bandpass2 off
	if(~step5 == 114,  
		{s.sendMsg("/n_set", 1025, \gates, 0);}
	); //midipiano off
	if((~step5 >= 115).and(~step5 <= 124).and(~step5.even), 
		{fourier2.ffttime( (0.092879818594104+((~step5-92)*0.092879818594104)) , 0.5);}
	); //bandpass2
	if(~step5 == 124, 
		{s.sendMsg("n_set", 1024, \gates, 0);}
	); //bandpass2
	if(~step5 == 134, 
		{s.sendMsg("n_set", 1025, \gates, 0);}
	); //midipiano off
	if(~step5 == 144, 
		{s.sendMsg("n_set", 1025, \gates, 0);}
	);
	if(~step5 == 158, 
		{s.sendMsg("n_set", 1025, \gates, 0);}
	);
	if(~step5 == 186, 
		{osc.remove}
	); //pianobass stop
	if(~step5 == 194, 
		{osc.remove}
	);
	if(~step5 == 208, 
		{osc.remove}
	);
	if(~step5 == 218, 
		{osc.remove}
	);
	if(~step5 == 228, 
		{osc.remove}
	);
	if(~step5 == 238, 
		{osc.remove}
	);
	if(~step5 == 252, 
		{osc.remove}
	);
	if(~step5 == 266, 
		{osc.remove}
	);
		
	
	
	//end noteOff

}

//rehearsal marks
begining {
	~step1 = 0;
	~step2 = 0;
	~step3 = 0;
	~step4 = 0;
	~step5 = 0;
	
	control.control(0, 1, 44); //gaterev
	control.control(0, 2, 79); //pianogate
	control.control(0, 3, 0); //piano noise
	control.control(0, 4, 51); //pianogate as bass
	control.control(0, 5, 44); //gateperc
	control.control(0, 6, 13); //gateperc
	control.control(0, 7, 40); //vol pianoruido
	control.control(0, 8, 90); //vol main
	//knobs
	control.control(0, 81, 73); //chan1 (soprano)
	control.control(0, 82, 73); //chan2 (alto1)
	control.control(0, 83, 73); //chan3 (alto2)
	control.control(0, 84, 73); //chan4 (tenor)
	control.control(0, 85, 73); //chan5 (bass)
	control.control(0, 86, 73); //chan6 (piano)
	//sliders

~globamp1 = 0.33039866079732;
~globamp2 = 0.33039866079732;
~globamp3 = 0.33039866079732;
~globamp4 = 0.33039866079732;
~globamp5 = 0.33039866079732;
~globamp6 = 0.33039866079732;
	
s.sendMsg("/s_new", "vocoderenv", 1014, 1, 2005, \inbus, 16, \vocbus, 32, \out, 0, \amp, 150, \qrq, 0.001, \pitch, 42.663256630967, \rqs, fourier.rq2, \centerfreq, fourier.cutfreq, \white, 0.04, \bins, 1020, \gates, 0);
s.sendMsg("/s_new", "gating2buf", 1017, 1, 2003, \out, 0, \start, rrand(0,6060560), \bufnum, 5, \oct, 10, \gates, 0); //for soprano
s.sendMsg("/s_new", "vocoderenv", 1015, 1, 2005, \inbus, 18, \vocbus, 34, \out, 1, \amp, 150, \qrq, 0.005, \pitch, 41.663256630967, \rqs, fourier.rq2, \centerfreq, fourier.cutfreq, \white, 0.04, \bins, 1020, \window, 3.0, \gates, 0); 
s.sendMsg("/s_new", "gating", 1018, 1, 2003, \out, 1, \bufnum, 7, \soundbuf, 8, \gates, 0, \start, rrand(0, 6399418), \oct, 0.5); //for alto1 (with 1017 too)
this.vocStart;
//reminder
	'NOW MOVE KNOB 3 BEFORE STARTING!! NOW MOVE KNOB 3 BEFORE STARTING!! NOW MOVE KNOB 3 BEFORE STARTING!! NOW MOVE KNOB 3 BEFORE STARTING!! NOW MOVE KNOB 3 BEFORE STARTING!! NOW MOVE KNOB 3 BEFORE STARTING!! NOW MOVE KNOB 3 BEFORE STARTING!! NOW MOVE KNOB 3 BEFORE STARTING!!'.postln;

}

rehearsalA {var mid, mid2;
	~step1 = 32;
	~step2 = 32;
	~step3 = 18;
	~step4 = 28; 
	~step5 = 18; 

	control.control(0, 1, 44); //gaterev
	control.control(0, 2, 79); //pianogate
	control.control(0, 3, 0); //piano noise
	control.control(0, 4, 51); //pianogate as bass
	control.control(0, 5, 44); //gateperc
	control.control(0, 6, 13); //gateperc
	control.control(0, 7, 40); //vol pianoruido
	control.control(0, 8, 90); //vol main
	//knobs
	control.control(0, 83, 73); //chan3 (alto2)
	control.control(0, 84, 73); //chan4 (tenor)
	control.control(0, 85, 73); //chan5 (bass)
	control.control(0, 86, 73); //chan6 (piano)
	//sliders

~globamp1 = 0.33039866079732;
~globamp2 = 0.33039866079732;
~globamp3 = 0.33039866079732;
~globamp4 = 0.33039866079732;
~globamp5 = 0.33039866079732;
~globamp6 = 0.33039866079732;

~globamp2 = ~globamp2*initVocoder;
		mid = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(~globamp2.ampdb).linlin(0,1,0,127).round(1);
		control.control(0, 82, mid);
		
~globamp1 = ~globamp2;
		mid2 = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(~globamp1.ampdb).linlin(0,1,0,127).round(1);
		control.control(0, 81, mid2);
	
	s.sendMsg("/s_new", "vocoderenv", 1014, 1, 2005, \inbus, 16, \vocbus, 32, \out, 0, \amp, 150, \qrq, 0.001, \pitch, 42.663256630967, \rqs, fourier.rq2, \centerfreq, fourier.cutfreq, \white, 0.04, \bins, 1020, \gates, 0, \globamp, ~globamp1, \adjVol, 2.0); //for performance
	s.sendMsg("/s_new", "gating2buf", 1017, 1, 2003, \out, 0, \start, rrand(0,6060560), \bufnum, 5, \gates, 0, \oct, 10); //for soprano
		s.sendMsg("/s_new", "vocoderenv", 1015, 1, 2005, \inbus, 18, \vocbus, 34, \out, 1, \amp, 150, \qrq, 0.005, \pitch, 41.663256630967, \rqs, fourier.rq2, \centerfreq, fourier.cutfreq, \white, 0.04, \bins, 1020, \window, 3.0, \gates, 0, \globamp, ~globamp2, \adjVol, 1.75);  //start vocoder
	s.sendMsg("/s_new", "gating", 1018, 1, 2003, \out, 1, \bufnum, 7, \soundbuf, 8, \gates, 0, \start, rrand(0, 6399418), \oct, 0.5);  //for alto1 (with 1017 too)
	s.sendMsg("/s_new", "transpenv", 1011, 0, 2003, \in, 30, \out, 4, \gates, 0); //bass
	fourier.ffttime(2.0, 0.5);
	this.vocStart;
//reminder
	'NOW MOVE KNOB 3 BEFORE STARTING!! NOW MOVE KNOB 3 BEFORE STARTING!! NOW MOVE KNOB 3 BEFORE STARTING!! NOW MOVE KNOB 3 BEFORE STARTING!! NOW MOVE KNOB 3 BEFORE STARTING!! NOW MOVE KNOB 3 BEFORE STARTING!! NOW MOVE KNOB 3 BEFORE STARTING!! NOW MOVE KNOB 3 BEFORE STARTING!!'.postln;
}

rehearsalB {var mid, mid2, mid3, mid4, mid5;
	~step1 = 54;
	~step2 = 68;
	~step3 = 54;
	~step4 = 64; 
	~step5 = 36; 

	control.control(0, 1, 44); //gaterev
	control.control(0, 2, 79); //pianogate
	control.control(0, 3, 0); //piano noise
	control.control(0, 4, 51); //pianogate as bass
	control.control(0, 5, 44); //gateperc
	control.control(0, 6, 13); //gateperc
	control.control(0, 7, 40); //vol pianoruido
	control.control(0, 8, 90); //vol main
	//knobs
	control.control(0, 86, 73); //chan6 (piano)
	//sliders


~globamp2 = 0.33039866079732;
~globamp6 = 0.33039866079732;

~globamp2 = ~globamp2*initVocoder;
				
~globamp1 = ~globamp2*initGaterev;
		volgate = ~globamp1;
		mid2 = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(~globamp1.ampdb).linlin(0,1,0,127).round(1);
		control.control(0, 81, mid2);

~globamp3 = ~globamp2*initVoc2;
		mid3 = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(~globamp3.ampdb).linlin(0,1,0,127).round(1);
		control.control(0, 83, mid3);
				
~globamp4 = ~globamp2*initVoc2;
		mid4 = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(~globamp4.ampdb).linlin(0,1,0,127).round(1);
		control.control(0, 84, mid4);		

~globamp5 = ~globamp2*initLowband;
		mid5 = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(~globamp5.ampdb).linlin(0,1,0,127).round(1);
		control.control(0, 85, mid5);

~globamp2 = ~globamp1*(initGateperc/initGaterev);
		volgateperc = ~globamp2;
		mid = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(~globamp2.ampdb).linlin(0,1,0,127).round(1);
		control.control(0, 82, mid);			
	
	s.sendMsg("/s_new", "gating2buf", 1017, 1, 2003, \out, 0, \start, rrand(0,6060560), \bufnum, 5, \gates, 0, \oct, 10); //for soprano
	s.sendMsg("/s_new", "gating", 1018, 1, 2003, \out, 1, \bufnum, 7, \soundbuf, 8, \gates, 0, \start, rrand(0, 6399418), \oct, 0.5, \adjVol, 1.1, \globamp, ~globamp2); //for alto1 (with 1017)
	s.sendMsg("/s_new", "vocoderenv", 1015, 1, 2005, \inbus, 18, \vocbus, 34, \out, 2, \amp, 150, \qrq, 0.001, \transp, 10.015082956259*8, \rqs, fourier.rq2, \centerfreq, fourier.cutfreq, \white, 0.04, \bins, 1020, \window, 3.0, \gates, 0, \globamp, ~globamp3, \adjVol, 2.0);  //for alto2
	s.sendMsg("/s_new", "vocoderenv", 1014, 1, 2005, \inbus, 16, \vocbus, 32, \out, 3, \amp, 150, \qrq, 0.001, \pitch, 42.663256630967, \rqs, fourier.rq2, \centerfreq, fourier.cutfreq, \white, 0.04, \bins, 1020, \gates, 0, \globamp, ~globamp4, \adjVol, 2.0); //for tenor
	s.sendMsg("/s_new", "transpenv", 1011, 0, 2003, \in, 30, \out, 4, \gates, 0); //bass
	fourier.ffttime(2.0, 0.5);
		s.sendMsg("n_set", 1011, \gates, 1, \atk, makenv3[5][atk], \dec, makenv3[5][dec], \sus, makenv3[5][sus], \rel, makenv3[5][rel], \crv, makenv3[5][crv], \globamp, ~globamp5, \adjVol, 3.0);
	this.vocStart;
//reminder
	'NOW MOVE KNOB 3 BEFORE STARTING!! NOW MOVE KNOB 3 BEFORE STARTING!! NOW MOVE KNOB 3 BEFORE STARTING!! NOW MOVE KNOB 3 BEFORE STARTING!! NOW MOVE KNOB 3 BEFORE STARTING!! NOW MOVE KNOB 3 BEFORE STARTING!! NOW MOVE KNOB 3 BEFORE STARTING!! NOW MOVE KNOB 3 BEFORE STARTING!!'.postln;	
}

rehearsalC {var mid, mid2, mid3, mid4, mid5;

	~step1 = 64;
	~step2 = 78;
	~step3 = 64;
	~step4 = 74; 
	~step5 = 46; 	
	
	control.control(0, 1, 44); //gaterev
	control.control(0, 2, 79); //pianogate
	control.control(0, 3, 0); //piano noise
	control.control(0, 4, 51); //pianogate as bass
	control.control(0, 5, 44); //gateperc
	control.control(0, 6, 13); //gateperc
	control.control(0, 7, 40); //vol pianoruido
	control.control(0, 8, 90); //vol main
	//knobs
	control.control(0, 86, 73); //chan6 (piano)
	//sliders


~globamp2 = 0.33039866079732;
~globamp6 = 0.33039866079732;

~globamp2 = ~globamp2*initVocoder;
				
~globamp1 = ~globamp2*initGaterev;
		volgate = ~globamp1;
		mid2 = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(~globamp1.ampdb).linlin(0,1,0,127).round(1);
		control.control(0, 81, mid2);

~globamp3 = ~globamp2*initVoc2;
		mid3 = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(~globamp3.ampdb).linlin(0,1,0,127).round(1);
		control.control(0, 83, mid3);
				
~globamp4 = ~globamp2*initVoc2;
		mid4 = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(~globamp4.ampdb).linlin(0,1,0,127).round(1);
		control.control(0, 84, mid4);		

~globamp5 = ~globamp2*initLowband;
		mid5 = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(~globamp5.ampdb).linlin(0,1,0,127).round(1);
		control.control(0, 85, mid5);

~globamp2 = ~globamp1*(initGateperc/initGaterev);
		volgateperc = ~globamp2;
		mid = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(~globamp2.ampdb).linlin(0,1,0,127).round(1);
		control.control(0, 82, mid);			

			
	fourier2.thresh(2.4);
	fourier2.getQ;
	s.sendMsg("/s_new", "gating2buf", 1017, 1, 2003, \out, 0, \start, rrand(0,6060560), \bufnum, 5, \gates, 0, \oct, 10); //for soprano
	s.sendMsg("/s_new", "gating", 1018, 1, 2003, \out, 1, \bufnum, 7, \soundbuf, 8, \gates, 0, \start, rrand(0, 6399418), \oct, 0.5); //for alto1 (with 1017)
	s.sendMsg("/s_new", "vocoderenv", 1015, 1, 2005, \inbus, 18, \vocbus, 34, \out, 2, \amp, 150, \qrq, 0.001, \transp, 10.015082956259*8, \rqs, fourier.rq2, \centerfreq, fourier.cutfreq, \white, 0.04, \bins, 1020, \window, 3.0, \gates, 0, \globamp, ~globamp3, \adjVol, 2.0);  //for alto2
	s.sendMsg("/s_new", "vocoderenv", 1014, 1, 2005, \inbus, 16, \vocbus, 32, \out, 3, \amp, 150, \qrq, 0.001, \pitch, 42.663256630967, \rqs, fourier.rq2, \centerfreq, fourier.cutfreq, \white, 0.04, \bins, 1020, \gates, 0, \globamp, ~globamp4, \adjVol, 2.0); //for tenor
	s.sendMsg("/s_new", "transpenv", 1011, 0, 2003, \in, 30, \out, 4, \gates, 0); //bass
	fourier.ffttime(2.0, 0.5);
		s.sendMsg("n_set", 1011, \gates, 1, \atk, makenv3[5][atk], \dec, makenv3[5][dec], \sus, makenv3[5][sus], \rel, makenv3[5][rel], \crv, makenv3[5][crv], \globamp, ~globamp5, \adjVol, 3.0);
	this.vocStart;
//reminder
	'NOW MOVE KNOB 3 BEFORE STARTING!! NOW MOVE KNOB 3 BEFORE STARTING!! NOW MOVE KNOB 3 BEFORE STARTING!! NOW MOVE KNOB 3 BEFORE STARTING!! NOW MOVE KNOB 3 BEFORE STARTING!! NOW MOVE KNOB 3 BEFORE STARTING!! NOW MOVE KNOB 3 BEFORE STARTING!! NOW MOVE KNOB 3 BEFORE STARTING!!'.postln;	}
			
rehearsalD {var mid, mid2, mid3, mid4, mid5;

	~step1 = 86;
	~step2 = 116;
	~step3 = 108;
	~step4 = 112; 
	~step5 = 78; 
		
	control.control(0, 1, 44); //gaterev
	control.control(0, 2, 79); //pianogate
	control.control(0, 3, 0); //piano noise
	control.control(0, 4, 51); //pianogate as bass
	control.control(0, 5, 44); //gateperc
	control.control(0, 6, 13); //gateperc
	control.control(0, 7, 40); //vol pianoruido
	control.control(0, 8, 90); //vol main
	//knobs
	control.control(0, 86, 73); //chan6 (piano)
	//sliders


~globamp2 = 0.33039866079732;
~globamp6 = 0.33039866079732;

~globamp2 = ~globamp2*initVocoder;
				
~globamp1 = ~globamp2*initGaterev;
		volgate = ~globamp1;
		mid2 = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(~globamp1.ampdb).linlin(0,1,0,127).round(1);
		control.control(0, 81, mid2);

~globamp3 = ~globamp2*initVoc2;
		mid3 = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(~globamp3.ampdb).linlin(0,1,0,127).round(1);
		control.control(0, 83, mid3);
				
~globamp4 = ~globamp2*initVoc2;
		mid4 = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(~globamp4.ampdb).linlin(0,1,0,127).round(1);
		control.control(0, 84, mid4);		

~globamp5 = ~globamp2*initLowband;
		mid5 = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(~globamp5.ampdb).linlin(0,1,0,127).round(1);
		control.control(0, 85, mid5);

~globamp2 = ~globamp1*(initGateperc/initGaterev);
		volgateperc = ~globamp2;
		mid = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(~globamp2.ampdb).linlin(0,1,0,127).round(1);
		control.control(0, 82, mid);			

			
	fourier2.thresh(2.4);
	fourier2.getQ;
	s.sendMsg("/s_new", "gating2buf", 1017, 1, 2003, \out, 0, \start, rrand(0,6060560), \bufnum, 5, \gates, 0, \oct, 10); //for soprano
	s.sendMsg("/s_new", "gating", 1018, 1, 2003, \out, 1, \bufnum, 7, \soundbuf, 8, \gates, 0, \start, rrand(0, 6399418), \oct, 0.5); //for alto1 (with 1017)
	s.sendMsg("/s_new", "vocoderenv", 1015, 1, 2005, \inbus, 18, \vocbus, 34, \out, 2, \amp, 150, \qrq, 0.001, \transp, 10.015082956259*8, \rqs, fourier.rq2, \centerfreq, fourier.cutfreq, \white, 0.04, \bins, 1020, \window, 3.0, \gates, 0, \globamp, ~globamp3, \adjVol, 2.0);  //for alto2
	s.sendMsg("/s_new", "vocoderenv", 1014, 1, 2005, \inbus, 16, \vocbus, 32, \out, 3, \amp, 150, \qrq, 0.001, \pitch, 42.663256630967, \rqs, fourier.rq2, \centerfreq, fourier.cutfreq, \white, 0.04, \bins, 1020, \gates, 0, \globamp, ~globamp4, \adjVol, 2.0); //for tenor
	s.sendMsg("/s_new", "transpenv", 1011, 0, 2003, \in, 30, \out, 4, \gates, 0); //bass
	fourier.ffttime(2.0, 0.5);
		s.sendMsg("n_set", 1011, \gates, 1, \atk, makenv3[5][atk], \dec, makenv3[5][dec], \sus, makenv3[5][sus], \rel, makenv3[5][rel], \crv, makenv3[5][crv], \globamp, ~globamp5, \adjVol, 3.0);
	this.vocStart;
//reminder
	'NOW MOVE KNOB 3 BEFORE STARTING!! NOW MOVE KNOB 3 BEFORE STARTING!! NOW MOVE KNOB 3 BEFORE STARTING!! NOW MOVE KNOB 3 BEFORE STARTING!! NOW MOVE KNOB 3 BEFORE STARTING!! NOW MOVE KNOB 3 BEFORE STARTING!! NOW MOVE KNOB 3 BEFORE STARTING!! NOW MOVE KNOB 3 BEFORE STARTING!!'.postln;	
}
	
rehearsalF {var mid, mid2, mid3, mid4, mid5, mid6;
	
~globamp2 = 0.33039866079732;
~globamp6 = 0.33039866079732;

~globamp2 = ~globamp2*initVocoder;
				
~globamp1 = ~globamp2*initGaterev;
		volgate = ~globamp1;
		
~globamp1 = ~globamp2*(initHighband/initGateperc);
		volhighband = ~globamp1;
		
~globamp4 = ~globamp1*(initOrchords/initHighband);
		volorch = ~globamp4;
		
~globamp1 = ~globamp2*(initBow/initGateperc);
		volbow = ~globamp1;	
		
~globamp1 = volgate;		
mid2 = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(~globamp1.ampdb).linlin(0,1,0,127).round(1);
		control.control(0, 81, mid2);				

~globamp4 = ~globamp1*(initOrchmel/initGaterev);
		volorchmel = ~globamp4;
mid4 = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(~globamp4.ampdb).linlin(0,1,0,127).round(1);
		control.control(0, 84, mid4);				
						
~globamp5 = ~globamp2*initLowband;
		mid5 = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(~globamp5.ampdb).linlin(0,1,0,127).round(1);
		control.control(0, 85, mid5);

~globamp2 = ~globamp1*(initGateperc/initGaterev);
		volgateperc = ~globamp2;
		mid = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(~globamp2.ampdb).linlin(0,1,0,127).round(1);
		control.control(0, 82, mid);			

~globamp3 = volgateperc;
		mid3 = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(~globamp3.ampdb).linlin(0,1,0,127).round(1);
		control.control(0, 83, mid3);		

~globamp6 = ~globamp1*(initPiano/initGaterev);
		mid6 = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(~globamp6.ampdb).linlin(0,1,0,127).round(1);
		control.control(0, 86, mid6);			

		
			
	fourier2.thresh(2.4);
	fourier2.getQ;
	fourier.stoptask;
	fourier.stoptask2;
	s.sendMsg(\n_set, 1811, \globamp, ~globamp6);
		s.sendBundle(0.1, ["n_free", 1004], ["n_free", 1005], ["n_free", 1006]);
		//s.sendBundle(0.1, ["n_free", 1009],["n_free", 1010], ["n_free", 1011]); 
		s.sendMsg("/s_new", "pianogatebuf", 1025, 0, 2003, \bufnum, 5, \soundbuf, 3, \justfreq, 186.75, \gates, 1, \specgate, 0.01, \start, rrand(0,6060560), \oct, 4);
	s.sendMsg("/s_new", "gating2buf", 1017, 1, 2003, \out, 0, \start, rrand(0,6060560), \bufnum, 5, \gates, 0, \oct, 10); //for soprano
	s.sendBundle(0.1, ["n_free", 1002],["n_free", 1010], ["n_free", 1011]); 
	
	s.sendMsg("/s_new", "transpenv", 1011, 0, 2003, \in, 30, \out, 4, \gates, 0); //bass
	fourier.ffttime(2.0, 0.5);
		s.sendMsg("n_set", 1011, \gates, 1, \atk, makenv3[5][atk], \dec, makenv3[5][dec], \sus, makenv3[5][sus], \rel, makenv3[5][rel], \crv, makenv3[5][crv], \globamp, ~globamp5, \adjVol, 3.0);
	osc.add;	

	Routine({ 1.do({
	s.sendBundle(0.1, ["n_free", 1008],["n_free", 1013]);
	s.sendMsg("/b_alloc", 9, 2048, 1); 
	s.sendMsg("/b_alloc", 6, 2048, 1);
	0.3.yield;
	s.sendMsg("/s_new", "gatingmic", 1031, 1, 2003, \out, 2, \detectbuf, 9, \bufnum, 6, \soundbuf, 8, \gates, 0, \start, rrand(0, 6399418), \oct, 0.35, \threshhold, 0.1, \globamp, ~globamp3, \amp, 67.linlin(0,127,0,2.5), \justfreq, 415); //for alto2
	'ready'.postln;
	~step1 = 132; //not yet implemented
	~step2 = 162; //not yet implemented
	~step3 = 154;
	~step4 = 142; //not yet implemented
	~step5 = 124; //not yet implemented
	})}).play 
	
}


rehearsalH {
	~step1 = 224; //not yet implemented
	~step2 = 242; //not yet implemented
	~step3 = 242;
	~step4 = 224; //not yet implemented
	~step5 = 172; //not yet implemented
	
	Routine({ 1.do({
	s.sendBundle(0.1, ["n_free", 1008],["n_free", 1013]);
	s.sendMsg("/b_alloc", 9, 2048, 1); 
	s.sendMsg("/b_alloc", 6, 2048, 1);
	0.3.yield;
	s.sendMsg("/s_new", "gatingmic", 1031, 1, 2003, \out, 2, \detectbuf, 9, \bufnum, 6, \soundbuf, 8, \gates, 0, \start, rrand(0, 6399418), \oct, 0.35, \threshhold, 0.1, \globamp, ~globamp3, \amp, 67.linlin(0,127,0,2.5), \justfreq, 415); //for alto2
	'ready'.postln;
	})}).play 
	
}
	

}
