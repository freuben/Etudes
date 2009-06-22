Etudes3Score : Etudes3 {

var <>initSines=1.0, <>initCel=1.4, <>initSteps=2.4, <>initHarp=3, <>initHighFFT=0.65, <>initLowFFT= 1.0, <>initMainFFT=1.2, <>initGlass=1.0, <>initKnock=1.0, <>initSlam=1.0, <>initSines2=1.0, <>initPiano=1.0;
var task, noteRate;
var routine, routine1, routine2, routine3, routine4; 
var mid, mid2, volsteps, volsteps2, volGlass, midGlass, volKnock, midKnock, volSlam, midSlam, volSines2, midSines2, volPiano, midPiano, arrPianos, arrPianos2, arrPianos3, freqPianos, magsPianos, specChord;


startEtudes3Score {arg firstSines, firstCel, firstSteps, firstHarp, firstHighFFT, firstLowFFT, firstMainFFT, firstGlass, firstKnock, firstSlam, firstSines2, firstPiano;
var count, index1, index2, index3, index4, index5, bins, randBins, detect, osc;

initSines=firstSines;
initCel=firstCel;
initSteps=firstSteps;
initHarp=firstHarp;
initHighFFT=firstHighFFT;
initLowFFT= firstLowFFT;
initMainFFT=firstMainFFT;
initGlass=firstGlass;
initKnock=firstKnock;
initSlam=firstSlam;
initSines2=firstSines2;
initPiano=firstPiano;

osc = OSCresponder(s.addr,'/tr',{ arg time,responder,msg;
	detect = msg[3];
}).add;


routine = Routine({inf.do
({
var pat1, pat2, pat3, pat4, diff, arrayFreq;

arrayFreq = (partials.frequencies.cpsmidi + 2).midicps;
pat1 = Pseq.new(arrayFreq, 1).asStream;
pat2 = Pseq.new(partials.magnitudes.linlin(0,12,0, 1/(partials.magnitudes.size)), 1).asStream;
pat3 = Pseq.new((0..arrayFreq.size),1).asStream;
	// repeat twice
pat4 = (arrayFreq.size).do({~soprano.spawn([\pitch, ((arrayFreq[pat3.next])*[8,2,2,4,2,4].choose), \amp, (pat2*6).next, \out, 0, \atk, rrand(0.00010, 0.00019), \dec, rrand(0.355, 0.351)], 1);});
nil.yield;
})
});


task = Task({
noteRate = 1;
inf.do
({
if( detect != 0, {routine.next;});
partials.numpartials(rrand(1,6));
([2/7, 1/6, 1/4, 1/3, 1/5].choose*noteRate).yield;
})
});

routine1 = Routine({

inf.do({

bins = (0,1..511);
randBins = bins.scramble;
index1 = randBins.copyRange(0,85);
index2 = randBins.copyRange(86,170);
index3 = randBins.copyRange(171,255);
index4 = randBins.copyRange(256,341);
index5 = randBins.copyRange(342,426);
index6 = randBins.copyRange(427,511);
count = 0;

85.do({
arraySteps = arraySteps.put(index1[count], 0);
array1 = array1.put(index1[count], 1);
array1 = array1.put(index2[count], 0);
array1 = array1.put(index3[count], 0);
array1 = array1.put(index4[count], 0);
array1 = array1.put(index5[count], 0);
array1 = array1.put(index6[count], 0);

arraySteps = arraySteps.put(index2[count], 0);
array2 = array2.put(index2[count], 1);
array2 = array2.put(index3[count], 0);
array2 = array2.put(index4[count], 0);
array2 = array2.put(index5[count], 0);
array2 = array2.put(index6[count], 0);
array2 = array2.put(index1[count], 0);

arraySteps = arraySteps.put(index3[count], 0);
array3 = array3.put(index3[count], 1);
array3 = array3.put(index4[count], 0);
array3 = array3.put(index5[count], 0);
array3 = array3.put(index6[count], 0);
array3 = array3.put(index1[count], 0);
array3 = array3.put(index2[count], 0);

arraySteps = arraySteps.put(index4[count], 0);
array4 = array4.put(index4[count], 1);
array4 = array4.put(index5[count], 0);
array4 = array4.put(index6[count], 0);
array4 = array4.put(index1[count], 0);
array4 = array4.put(index2[count], 0);
array4 = array4.put(index3[count], 0);

arraySteps = arraySteps.put(index5[count], 0);
array5 = array5.put(index5[count], 1);
array5 = array5.put(index6[count], 0);
array5 = array5.put(index1[count], 0);
array5 = array5.put(index2[count], 0);
array5 = array5.put(index3[count], 0);
array5 = array5.put(index4[count], 0);

arraySteps = arraySteps.put(index6[count], 0);
array6 = array6.put(index6[count], 1);
array6 = array6.put(index1[count], 0);
array6 = array6.put(index2[count], 0);
array6 = array6.put(index3[count], 0);
array6 = array6.put(index4[count], 0);
array6 = array6.put(index5[count], 0);


buff1.setn(0, array1);
ffttime.yield;
buff2.setn(0, array2);
ffttime.yield;
buff3.setn(0, array3);
buffSteps.setn(0, arraySteps);
ffttime.yield;
buff4.setn(0, array4);
ffttime.yield;
buff5.setn(0, array5);
ffttime.yield;
buff6.setn(0, array6);
ffttime.yield;
count = count + 1;
});

ffttime.yield;
randBins.postln;

});

});

routine2 = Routine({

inf.do({

bins = (0,1..511);
randBins = bins.scramble;
index1 = randBins.copyRange(0,85);
index2 = randBins.copyRange(86,170);
index3 = randBins.copyRange(171,255);
index4 = randBins.copyRange(256,341);
index5 = randBins.copyRange(342,426);
index6 = randBins.copyRange(427,511);
count = 0;

85.do({
arraySteps = arraySteps.put(index1[count], 0);
array1 = array1.put(index1[count], 1);
array1 = array1.put(index2[count], 0);
array1 = array1.put(index3[count], 0);
array1 = array1.put(index4[count], 0);
array1 = array1.put(index5[count], 0);
array1 = array1.put(index6[count], 0);

arraySteps = arraySteps.put(index2[count], 0);
array2 = array2.put(index2[count], 1);
array2 = array2.put(index3[count], 0);
array2 = array2.put(index4[count], 0);
array2 = array2.put(index5[count], 0);
array2 = array2.put(index6[count], 0);
array2 = array2.put(index1[count], 0);

arraySteps = arraySteps.put(index3[count], 0);
array3 = array3.put(index3[count], 1);
array3 = array3.put(index4[count], 0);
array3 = array3.put(index5[count], 0);
array3 = array3.put(index6[count], 0);
array3 = array3.put(index1[count], 0);
array3 = array3.put(index2[count], 0);

arraySteps = arraySteps.put(index4[count], 0);
array4 = array4.put(index4[count], 1);
array4 = array4.put(index5[count], 0);
array4 = array4.put(index6[count], 0);
array4 = array4.put(index1[count], 0);
array4 = array4.put(index2[count], 0);
array4 = array4.put(index3[count], 0);

arraySteps = arraySteps.put(index5[count], 0);
array5 = array5.put(index5[count], 1);
array5 = array5.put(index6[count], 0);
array5 = array5.put(index1[count], 0);
array5 = array5.put(index2[count], 0);
array5 = array5.put(index3[count], 0);
array5 = array5.put(index4[count], 0);

arraySteps = arraySteps.put(index6[count], 0);
array6 = array6.put(index6[count], 1);
array6 = array6.put(index1[count], 0);
array6 = array6.put(index2[count], 0);
array6 = array6.put(index3[count], 0);
array6 = array6.put(index4[count], 0);
array6 = array6.put(index5[count], 0);


buff1.setn(0, array1);
buff2.setn(0, array2);
buff3.setn(0, array3);
buffSteps.setn(0, arraySteps);
buff4.setn(0, array4);
buff5.setn(0, array5);
buff6.setn(0, array6);

ffttime.yield;
count = count + 1;
});

ffttime.yield;
randBins.postln;

});

});

routine3 = Routine({
~scale = 1;
"SPECTRAL CRECCENDO SPECTRAL CRECCENDO SPECTRAL CRECCENDO SPECTRAL CRECCENDO SPECTRAL CRECCENDO".postln;

inf.do({

bins = (0,1..511);
randBins = bins.scramble;
index1 = randBins.copyRange(0,85);
index2 = randBins.copyRange(86,170);
index3 = randBins.copyRange(171,255);
index4 = randBins.copyRange(256,341);
index5 = randBins.copyRange(342,426);
index6 = randBins.copyRange(427,511);
count = 0;

array1 = array1.put(index1[count], 1);
array2 = array2.put(index2[count], 1);
array3 = array3.put(index3[count], 1);
array4 = array4.put(index4[count], 1);
array5 = array5.put(index5[count], 1);
array6 = array6.put(index6[count], 1);

buff1.setn(0, array1);
buff2.setn(0, array2);
buff3.setn(0, array3);
buff4.setn(0, array4);
buff5.setn(0, array5);
buff6.setn(0, array6);

~fft1.set(\adjVol, array1.select({|item| item == 1}).size.linlin(85,512, 0.7, 0.7/6) * ~scale);
~fft2.set(\adjVol, array2.select({|item| item == 1}).size.linlin(85,512, 0.7, 0.7/6) * ~scale);
~fft3.set(\adjVol, array3.select({|item| item == 1}).size.linlin(85,512, 0.7, 0.7/6) * ~scale);
~fft4.set(\adjVol, array4.select({|item| item == 1}).size.linlin(85,512, 0.7, 0.7/6) * ~scale);
~fft5.set(\adjVol, array5.select({|item| item == 1}).size.linlin(85,512, 0.7, 0.7/6) * ~scale);
~fft6.set(\adjVol, array6.select({|item| item == 1}).size.linlin(85,512, 0.7, 0.7/6) * ~scale);



84.do({

array1 = array1.put(index1[count], 1);
array2 = array2.put(index2[count], 1);
array3 = array3.put(index3[count], 1);
array4 = array4.put(index4[count], 1);
array5 = array5.put(index5[count], 1);
array6 = array6.put(index6[count], 1);

ffttime.yield;
count = count + 1;

0.04.yield;

});

array1.select({|item| item == 1}).size.postln;

});

});

routine4 = Routine({

inf.do({

bins = (0,1..511);
randBins = bins.scramble;
index1 = randBins.copyRange(0,85).sort;
index2 = randBins.copyRange(86,170).sort;
index3 = randBins.copyRange(171,255).sort;
index4 = randBins.copyRange(256,341).sort;
index5 = randBins.copyRange(342,426).sort;
index6 = randBins.copyRange(427,511).sort;
count = 0;

85.do({

array1 = array1.put(index1[count], 0);
array2 = array2.put(index2[count], 0);
array3 = array3.put(index3[count], 0);
array4 = array4.put(index4[count], 0);
array5 = array5.put(index5[count], 0);
array6 = array6.put(index6[count], 0);

count = count + 1;
0.0008.yield;

});

buff1.setn(0, array1.copyRange(0,511));
buff2.setn(0, array2.copyRange(0,511));
buff3.setn(0, array3.copyRange(0,511));
buff4.setn(0, array4.copyRange(0,511));
buff5.setn(0, array5.copyRange(0,511));
buff6.setn(0, array6.copyRange(0,511));

});

});

//functions to get partials 
freqPianos = {partials2.frequencies.cpsmidi.round(1);};
magsPianos = {partials2.magnitudes.linlin(0.0,12,40,100).round(1)};
//function to play partials as chords
specChord = {arg num=5, arpeg=0.01, lengh=1, sort=\normal;
partials2.numpartials(num); 
if(freqPianos.notNil, {
h.midiChord(freqPianos.value, magsPianos.value, arpeg, lengh, sort);
});
};

//array for duration of chords
arrPianos = #[ 2.809375, 0.28958333333334, 0.29166666666667, 0.58437499999999, 0.3125, 0.88333333333334, 0.89166666666665, 0.29687499999999, 1.1635416666667, 0.58541666666667, 1.4291666666667, 1.696875, 0.265625, 2.8208333333333, 0.59270833333332, 1.15, 0.590625, 0.58333333333333, 3.9458333333333, 1.1145833333333, 3.3614583333333, 1.6645833333333, 2.7864583333333, 0.29375, 1.1072916666667, 0.81041666666665, 1.0989583333333, 0.809375, 1.6666666666667, 3.0520833333333, 1.10625, 0.27499999999999, 0.27499999999999, 2.825, 2.2395833333333, 0.28125, 2.2552083333333, 0.85833333333333, 4.8302083333333, 0.58125000000001, 0.56979166666667, 0.84791666666668, 0.565625, 1.6979166666667, 0.83125, 0.26770833333333, 4.5520833333333, 5.1427083333333, 0.27395833333333, 0.578125, 0.5625, 1.4239583333333, 4.0135416666667, 2.2989583333333, 1.425, 0.27500000000001, 1.4239583333333, 1.1322916666667, 0.86145833333333, 0.31041666666667, 1.4510416666667, 1.1479166666667, 2.28125, 2.284375, 0.55520833333333, 0.29479166666667, 1.6916666666667, 1.6864583333333, 0.85312499999999, 1.7083333333333, 0.840625, 2.009375, 0.29166666666667, 1.4302083333333, 0.57604166666665, 3.7072916666667, 1.1427083333333, 1.41875, 2.2760416666667, 0.56041666666665, 0.27083333333334, 2.8729166666667, 0.88437499999999, 0.88645833333332, 1.4364583333333, 1.4364583333333, 5.6885416666667, 0.30729166666666, 0.30729166666666, 1.4229166666667, 1.7166666666667, 3.1114583333333, 2.0260416666667, 1.1385416666667, 5.4145833333333, 1.9947916666667, 0.27708333333332, 0.84791666666668, 0.575, 0.28750000000001, 0.29583333333333, 0.27916666666667, 2.528125, 2.821875, 1.43125, 0.27499999999999, 0.87916666666666, 0.29270833333334, 0.30208333333333, 1.1572916666667, 2.8385416666667, 0.284375, 0.3125, 2.8333333333333, 0.253125, 2.525, 1.3729166666667, 1.075, 1.9739583333333, 1.9729166666667, 0.28437500000001, 1.3916666666667, 1.103125, 0.53125, 0.52604166666667, 0.81250000000001, 4.846875, 2.5854166666667, 0.29791666666667, 2.29375, 0.86666666666666, 3.4229166666667, 1.1458333333333, 0.26979166666668, 0.27291666666667, 1.4520833333333, 2.5739583333333, 0.28958333333333, 2.5916666666667, 0.29062500000001, 1.7302083333333, 0.57291666666667, 3.1677083333333, 2.2927083333333, 0.28333333333333, 3.1552083333333, 0.58020833333333, 2.5541666666667, 0.26875000000001, 2.296875, 5.1322916666667, 0.85520833333334, 0.29479166666667, 3.6927083333333, 0.83541666666666, 0.56041666666667, 1.4145833333333, 1.1177083333333, 0.85208333333333, 1.7114583333333, 3.1260416666667, 0.57187499999999, 3.4416666666666, 1.4458333333333, 5.6864583333333, 2.2635416666667, 0.28124999999999, 5.571875, 0.29687500000001, 0.86250000000001, 1.3989583333333, 0.29791666666667, 2.2708333333333, 0.296875, 0.296875, 0.82187499999999, 2.55625, 1.403125, 1.69375, 3.39375, 0.81874999999999, 0.54375, 0.81458333333333, 0.29479166666667, 1.1572916666667, 2.015625, 1.7302083333333, 1.1447916666667, 5.39375, 2.5666666666667, 0.30625000000001, 1.7208333333333, 0.56145833333333, 0.28333333333333, 1.1375, 0.84791666666666, 1.1510416666667, 0.30208333333333, 2.003125, 1.69375, 2.4395833333333, 0.55312500000001, 0.27812499999999, 0.58125, 0.58125, 1.4208333333333, 1.9885416666667, 1.9885416666667, 3.6875, 0.296875, 3.6729166666667, 1.14375, 0.56979166666667, 1.4, 0.55520833333334, 2.5479166666667, 1.425, 3.6895833333333, 0.28020833333333, 0.29479166666667, 0.86562499999999, 0.55104166666666, 0.559375, 1.1354166666667, 1.965625, 1.709375, 4.5145833333333, 0.58854166666667, 2.2791666666667, 0.671875, 2.096875, 0.55416666666666, 3.10625, 1.128125, 2.5510416666667, 0.565625, 0.26979166666666, 0.58125000000001, 0.28541666666668, 1.415625, 1.4166666666667, 1.1385416666667, 2.259375, 1.9739583333333, 0.26041666666666, 0.54166666666667, 2.534375, 4.5729166666667, 1.4229166666667, 3.996875, 4.2677083333333, 0.56458333333333, 1.415625, 0.28020833333333, 0.83541666666666, 0.56666666666666, 0.56354166666668, 0.28020833333332, 0.28958333333333, 0.28333333333333, 1.4052083333333, 3.1197916666667, 1.7177083333333, 0.86145833333333, 0.85416666666667, 0.56249999999999, 3.6927083333333, 1.4177083333333, 0.28125000000001, 0.55625000000001, 0.86979166666667, 2.2802083333333, 0.85416666666669, 1.9864583333333, 1.1270833333333, 2.84375, 0.57916666666668, 0.30000000000001, 1.996875, 0.28229166666667, 0.55624999999999, 0.56145833333333, 2.5375, 0.27604166666666, 0.828125, 0.546875, 0.26770833333333, 0.26458333333333, 0.59479166666667, 0.27708333333334, 1.15, 1.45, 1.39375, 0.29062499999999, 1.3885416666667, 2.2427083333333, 1.1364583333333, 1.1114583333333, 0.84479166666667, 0.26354166666667, 0.84583333333333, 1.696875, 3.9510416666667, 0.30208333333333, 0.29270833333332, 0.81145833333333, 0.83645833333334, 0.52499999999999, 0.246875, 0.81770833333334, 1.403125, 1.4052083333333, 0.30104166666668, 0.59166666666665, 2.2583333333333, 0.27395833333334, 1.1125, 1.1072916666667, 0.28124999999999, 0.27395833333334, 0.56249999999999, 0.26666666666668, 0.87604166666667, 1.428125, 0.58020833333333 ];

arrPianos2 = arrPianos.reject({|item| item < 1.5});

arrPianos3 = arrPianos.reject({|item| item < 1});

arrPianos = arrPianos.reject({|item| item > 1.5});

}

// play the score
sopranoScore {arg note, vel; 
var justnote, volcel, mid;
	~step1.post; '/S'.postln;
	
	//noteOn
	
	if((~step1 > 0).and(~step1 < 150).and(~step1.odd), {
	noteRate = note.linexp(58, 82, 0.5,2);
	~soprano.set(\amp2, vel.linlin(0,127,0,1.75));
	});
	
	if(~step1 == 3, {
	volcel = ~globamp2*initCel;
	~globamp2 = volcel;
	mid = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(volcel.ampdb).linlin(0,1,0,127).round(1);
	control.control(0, 81, mid);
	~soprano.set(\globamp, volcel);
	~soprano.set(\start, 0.0);
	task.play;
	});
	if(~step1 == 5, {
		~alto1.add(\sineflute, 0, [\freq, "b3".notemidi.midijust(415), \amp, vel.linlin(0,127,0,0.6), \lag, 1.6]);
		~alto1_2 = ~alto1.objects[1];
	});
	if((~step1 > 5).and(~step1 <= 27).and(~step1.odd), {
		~alto1_2.set(\freq, "b3".notemidi.midijust(415)+((~step1-1)*1.2808641975309));
	}); //detune sineflute 
	if((~step1 > 27).and(~step1 <= 105).and(~step1.odd), {
		~alto1_2.set(\freq, "d4".notemidi.midijust(415)-((~step1-27)*0.55422008547009));
	}); //detune sineflute 
	if(~step1 == 7, {
		task.play;
	});
	if(~step1 == 23, {
		task.play;
	});
	if(~step1 == 63, {
		~alto1_2.set(\gates, 1);
	});
	if(~step1 == 83, {
		~alto1_2.set(\gates, 1);
	});	
	if(~step1 == 93, {
		~alto1_2.set(\gates, 1);
	});	
	if(~step1 == 107, {
	ffttime = 1.7/6;
	});
	if(~step1 == 109, {
	volsteps2 = ~globamp6*initMainFFT;
	mid2 = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(volsteps2.ampdb).linlin(0,1,0,127).round(1);
	~fft1.set(\globamp, volsteps2);
	~fft2.set(\globamp, volsteps2);
	~fft3.set(\globamp, volsteps2);
	~fft4.set(\globamp, volsteps2);
	control.control(0, 81, mid2); //chan1 (soprano)
	routine1.play;
	task.stop;
	});
	if((~step1 >= 109).and(~step1 <= 169).and(~step1.odd), {	
	~fft1.set(\adjVol, ~step1.linexp(109,169,1.0,0.8));
	});
	if(~step1 == 141, {
	~fft1.set(\atk, makenv1[0][atk], \dec, makenv1[0][dec], \sus, makenv1[0][sus], \rel, makenv1[0][rel], \crv, makenv1[0][crv])
	});
	if((~step1 >= 151).and(~step1.odd), {
	~fft1.set(\amp, vel.linlin(0,127, 0, 6.0));
	});
	if(~step1 == 151, {
	~soprano.source = 0;
	~alto1.source = 0;
	~alto2.source = 0;
	~tenor.source = 0;
	//~bass.source = 0;
	
	~fft1.set(\gates, 1, \atk, makenv1[4][atk], \dec, makenv1[4][dec], \sus, makenv1[4][sus], \rel, makenv1[4][rel], \crv, makenv1[4][crv])
	});
	if((~step1 >= 109).and(~step1 < 171).and(~step1.odd), 
	{ffttime = ~step1.linlin(109,171,1.7/6, 0.07/6);}
	);
	
	if(~step1 == 323, {
	routine4.stop;
	
	~fft1.source = 0;
	~fft2.source = 0;
	~fft3.source = 0;
	~fft4.source = 0;
	~fft5.source = 0;
	~fft6.source = 0;
	
	});
	
	if(~step1 == 343, {
	volGlass = initGlass;
	midGlass = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(volGlass.ampdb).linlin(0,1,0,127).round(1);
	~fft4.set(\globamp, volGlass);
	~fft5.set(\globamp, volGlass);
	~fft6.set(\globamp, volGlass);
	
	~fft4.put(0, \monoOutBuf, 0, [\buffer, 91]);
	~fft5.put(0, \monoOutBuf, 0, [\buffer, 92]);
	~fft6.put(0, \monoOutBuf, 0, [\buffer, 93]);
	
	control.control(0, 84, midGlass);
	control.control(0, 85, midGlass);
	control.control(0, 86, midGlass);
	
	"GLASS BREAK GLASS BREAK GLASS BREAK GLASS BREAK GLASS BREAK GLASS BREAK GLASS BREAK".postln
	}); 
	
	if(~step1 == 361, {
	
	//knocking sounds
	volKnock = initKnock;
	midKnock = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(volKnock.ampdb).linlin(0,1,0,127).round(1);
	~fft1.set(\globamp, volKnock);
	~fft2.set(\globamp, volKnock);
	
	~fft1.put(0, \monoOutBuf, 0, [\buffer, 99]);
	~fft2.put(0, \monoOutBuf, 0, [\buffer, 100]);
	
	control.control(0, 81, midKnock);
	control.control(0, 82, midKnock);
	
	"KNOCK 2".postln;
	});
	
	if(~step1 == 383, {
	//sines
	volSines2 = initSines2;
	midSines2 = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(volSines2.ampdb).linlin(0,1,0,127).round(1);
	
	~alto2.set(\globamp, volSines2);
	
	~alto2.put(0, \monoOutBuf, 0, [\buffer, 111]);
	
	control.control(0, 83, midSines2);
	
	"SINE DOWN".postln;
	
	//start partial tracker synth
	~soprano.put(0, \numpar, 0, [\fftbuf, 113, \magbuf, 114, \freqbuf, 115, \bus, 3, \num, 6]); //analize audio from bus 3 

//	partials2.startsynth(3); //use AudioIn input - channel 3
	partials2.getarrays;

	});
	
	if(~step1 == 417, {
	
	//knocking sounds
	~fft1.set(\globamp, volKnock);
	~fft2.set(\globamp, volKnock);
	
	~fft1.put(0, \monoOutBuf, 0, [\buffer, 101]);
	~fft2.put(0, \monoOutBuf, 0, [\buffer, 102]);
	
	control.control(0, 81, midKnock);
	control.control(0, 82, midKnock);
	
	"KNOCK 3".postln;
	
	~alto1.source = {|globamp = 1.0| AudioIn.ar(4)*globamp*2};

	partials2.numpartials(5);
	});
	
	if(~step1 == 445, {
	volPiano = initPiano;
	midPiano = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(volPiano.ampdb).linlin(0,1,0,127).round(1);
	
	~alto1.set(\globamp, volPiano);
	control.control(0, 82, midPiano);
	
	"Partials".postln;
	specChord.value(rrand(4,7), 0.005, arrPianos2.choose, \downUp);
	});

	if(~step1 == 451, {
	"Partials".postln;
	specChord.value(rrand(4,7), 0.005, arrPianos2.choose, \random);
	});
	
	if(~step1 == 455, {
	"Partials".postln;
	specChord.value(rrand(4,7), 0.001, arrPianos2.choose, \upDown);
	});

	if(~step1 == 475, {
	//sines
	~alto2.set(\globamp, volSines2);
	
	~alto2.put(0, \monoOutBuf, 0, [\buffer, 112]);
	
	control.control(0, 83, midSines2);
	
	"SINE UP".postln;
	
	});
	
	if(~step1 == 489, {
	
	//slamming sounds
	volSlam = initSlam;
	midSlam = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(volSlam.ampdb).linlin(0,1,0,127).round(1);
	~fft2.set(\globamp, volSlam);
	~fft3.set(\globamp, volSlam);
	
	~fft2.put(0, \monoOutBuf, 0, [\buffer, 107]);
	~fft3.put(0, \monoOutBuf, 0, [\buffer, 108]);
	
	control.control(0, 82, midSlam);
	control.control(0, 83, midSlam);
	
	"SLAM 1".postln;
	partials2.numpartials(3);
	});
	
	if(~step1 == 497, {
	~alto1.set(\globamp, volPiano);
	control.control(0, 82, midPiano);
	
	"Partials".postln;
	specChord.value(rrand(4,6), 0.001, arrPianos3.choose, \random);
	});
	
	if(~step1 == 499, {
	"Partials".postln;
	specChord.value(rrand(4,6), 0.001, arrPianos3.choose, \random);
	});
	
	if(~step1 == 505, {
	"Partials".postln;
	specChord.value(rrand(4,6), 0.001, arrPianos3.choose, \random);
	});
	
	if(~step1 == 509, {
	"Partials".postln;
	specChord.value(rrand(4,6), 0.001, arrPianos3.choose, \random);
	});
	
	//noteoff
	
	if((~step1 > 5).and(~step1 <= 27).and(~step1.even), {
		~alto1_2.set(\freq, "b3".notemidi.midijust(415)+((~step1-1)*1.2808641975309));
	}); //detune sineflute 

	if((~step1 > 27).and(~step1 <= 105).and(~step1.even), {
		~alto1_2.set(\freq, "d4".notemidi.midijust(415)-((~step1-27)*0.55422008547009));
	}); //detune sineflute 
	if(~step1 == 6, {
		task.stop;
	});
	if(~step1 == 22, {
		task.stop;
	});
	if(~step1 == 54, {
		~alto1_2.set(\gates, 0);
	});		
	if(~step1 == 62, {
		~alto1_2.set(\gates, 0);
	});	
	if(~step1 == 76, {
		~alto1_2.set(\gates, 0);
	});	
	if(~step1 == 92, {
		~alto1_2.set(\gates, 0);
	});	
	if(~step1 == 108, {
		~alto1_2.set(\gates, 0);
	});		
	if((~step1 >= 109).and(~step1 < 171).and(~step1.even), 
	{ffttime = ~step1.linlin(109,171,1.7/6, 0.07/6);}
	);
	if((~step1 >= 109).and(~step1 <= 169).and(~step1.even), {	
	~fft1.set(\adjVol, ~step1.linexp(109,169,1.0,0.8));
	});
	if(~step1 == 150, {
		task.stop;
		~fft1.set(\gates, 0);
	});
	if(~step1 == 170, {
	~fft1.set(\gates, 0);
	ffttime = 0.07;
	routine1.stop;
	routine2.play;
	});

	//end noteOff
	

}

alto1Score { arg note, vel; 
var justnote, volsines, mid, numIndex;
	justnote = note.midijust(415);
	~step2.post; '/A1'.postln;
	
	//noteOn
	
	//noteOn
	if((~step2 > 0).and(~step2 <= 158).and(~step2.odd), {
	~alto1.set(\amp, vel.linlin(0,127,0,0.6));
	});
	
	if(~step2 == 1, {
		volsines = ~globamp2*initSines;
		~globamp2 = volsines;
		mid = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(volsines.ampdb).linlin(0,1,0,127).round(1);
		control.control(0, 82, mid);
		~alto1.set(\globamp, volsines);
		~alto1.put(0, \sineflute, 0, [\freq, "a#3".notemidi.midijust(415), \lag, 1.6]);
		~alto1_1 = ~alto1.objects[0];
	});
	
	if((~step2 > 1).and(~step2 <= 27).and(~step2.odd), {
		~alto1_1.set(\freq, "a#3".notemidi.midijust(415)-((~step2-1)*1.2808641975309));
	}); //detune sineflute 
	
	if((~step2 > 27).and(~step2 <= 115).and(~step2.odd), {
		~alto1_1.set(\freq, "g3".notemidi.midijust(415)+((~step2-27)*0.53053977272727));
	}); //detune sineflte

	if(~step2 == 15, {
		~alto1_1.set(\gates, 1);
	});			
	if(~step2 == 39, {
		~alto1_1.set(\gates, 1);
	});		
	if(~step2 == 61, {
		task.play;
	});	
	if(~step2 == 69, {
		~alto1_1.set(\gates, 1);
	});		
	if(~step2 == 75, {
		task.play;
	});	
	if(~step2 == 91, {
		task.play;
	});			
	if(~step2 == 95, {
	~alto1_1.set(\gates, 1);
	});	
	if(~step2 == 109, {
	~alto1_1.set(\gates, 1);
	});		
	if((~step2 >= 119).and(~step2.odd), {
	~fft2.set(\amp, vel.linlin(0,127, 0, 6.0));
	});	
	if((~step2 >= 119).and(~step2 <= 177).and(~step2.odd), {	
	~fft2.set(\adjVol, ~step2.linexp(119,177,1.0,0.8));
	});
	if(~step2 == 119, {
	control.control(0, 82, mid2); //chan2 (alto1)
	~fft2.set(\gates, 1, \atk, makenv1[4][atk], \dec, makenv1[4][dec], \sus, makenv1[4][sus], \rel, makenv1[4][rel], \crv, makenv1[4][crv])
	});	
	if(~step2 == 159, {
	~fft2.set(\gates, 1, \atk, makenv1[5][atk], \dec, makenv1[5][dec], \sus, makenv1[5][sus], \rel, makenv1[5][rel], \crv, makenv1[5][crv])
	});
	if(~step2 == 179, {
	~fft2.set(\gates, 1, \adjVol, 0.7, \atk, makenv1[6][atk], \dec, makenv1[6][dec], \sus, makenv1[6][sus], \rel, makenv1[6][rel], \crv, makenv1[6][crv])
	});
	if(~step2 == 181, {
	~fft1.set(\gates, 1, \atk, makenv1[10][atk], \dec, makenv1[10][dec], \sus, makenv1[10][sus], \rel, makenv1[10][rel], \crv, makenv1[10][crv]);
	});
	
	if(~step2 == 385, {
	
	//knocking sounds
	volKnock = initKnock;
	midKnock = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(volKnock.ampdb).linlin(0,1,0,127).round(1);
	~fft1.set(\globamp, volKnock);
	~fft2.set(\globamp, volKnock);
	
	~fft1.put(0, \monoOutBuf, 0, [\buffer, 97]);
	~fft2.put(0, \monoOutBuf, 0, [\buffer, 98]);
	
	control.control(0, 81, midKnock);
	control.control(0, 82, midKnock);
	
	});
	
	if(~step2 == 543, {
	
	//slamming sounds
	volSlam = initSlam;
	midSlam = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(volSlam.ampdb).linlin(0,1,0,127).round(1);
	~fft2.set(\globamp, volSlam);
	~fft3.set(\globamp, volSlam);
	
	~fft2.put(0, \monoOutBuf, 0, [\buffer, 109]);
	~fft3.put(0, \monoOutBuf, 0, [\buffer, 110]);
	
	control.control(0, 82, midSlam);
	control.control(0, 83, midSlam);
	
	"SLAM 2".postln;
	partials2.numpartials(3);
	});
	
	if(~step2 == 551, {
	volPiano = initPiano;
	midPiano = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(volPiano.ampdb).linlin(0,1,0,127).round(1);
	
	~alto1.set(\globamp, volPiano);
	control.control(0, 82, midPiano);
	
	"ROUTINE".postln;
	Routine({1.do({
	partials2.numpartials(3);
	
	numIndex = rrand(0, arrPianos.size-10);
	"Partials".postln;
	specChord.value(rrand(3,6), 0.001, arrPianos[numIndex], \random);
	
	0.2046838210008.yield;
	numIndex = numIndex + 1;
	"Partials".postln;
	specChord.value(rrand(3,6), 0.001, arrPianos[numIndex], \random);
	
	0.24722380099774.yield;
	numIndex = numIndex + 1;
	"Partials".postln;
	specChord.value(rrand(3,6), 0.001, arrPianos[numIndex], \random);
	
	0.20320908400026.yield;
	numIndex = numIndex + 1;
	"Partials".postln;
	specChord.value(rrand(3,6), 0.001, arrPianos[numIndex], \random);
	
	0.24291946599988.yield;
	numIndex = numIndex + 1;
	"Partials".postln;
	specChord.value(rrand(3,6), 0.001, arrPianos[numIndex], \random);
	
	1.0638531100012.yield;
	numIndex = numIndex + 1;
	"Partials".postln;
	specChord.value(rrand(3,6), 0.001, arrPianos[numIndex], \random);
	
	0.22438868099925.yield;
	numIndex = numIndex + 1;
	"Partials".postln;
	specChord.value(rrand(3,6), 0.001, arrPianos[numIndex], \random);
	
	0.26098398400063.yield;
	numIndex = numIndex + 1;
	"Partials".postln;
	specChord.value(rrand(3,6), 0.001, arrPianos[numIndex], \random);
	
	1.2541706390002.yield;
	numIndex = numIndex + 1;
	"Partials".postln;
	specChord.value(rrand(3,6), 0.005, arrPianos[numIndex], \downUp);
	
	(4.5222061620007+rrand(-0.2,0.2)).yield;
	//glass break2
	volGlass = initGlass;
	midGlass = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(volGlass.ampdb).linlin(0,1,0,127).round(1);
	~fft4.set(\globamp, volGlass);
	~fft5.set(\globamp, volGlass);
	~fft6.set(\globamp, volGlass);
	
	~fft4.put(0, \monoOutBuf, 0, [\buffer, 94]);
	~fft5.put(0, \monoOutBuf, 0, [\buffer, 95]);
	~fft6.put(0, \monoOutBuf, 0, [\buffer, 96]);
	
	control.control(0, 84, midGlass);
	control.control(0, 85, midGlass);
	control.control(0, 86, midGlass);
	
	"GLASS BREAK GLASS BREAK GLASS BREAK GLASS BREAK GLASS BREAK GLASS BREAK GLASS BREAK".postln
	
	})}).play;
	});
	//noteoff
	
	if((~step2 > 1).and(~step2 <= 27).and(~step2.even), {
		~alto1_1.set(\freq, "a#3".notemidi.midijust(415)-((~step2-1)*1.2808641975309));
	}); //detune sineflute 
	
	if((~step2 > 27).and(~step2 <= 115).and(~step2.even), {
		~alto1_1.set(\freq, "g3".notemidi.midijust(415)+((~step2-27)*0.53053977272727));
	}); //detune sineflte

	if(~step2 == 14, {
		~alto1_1.set(\gates, 0);
	});	
	if(~step2 == 38, {
		~alto1_1.set(\gates, 0);
	});	
	if(~step2 == 60, {
		task.stop;
	});	
	if(~step2 == 68, {
		~alto1_1.set(\gates, 0);
	});		
	if(~step2 == 74, {
		task.stop;
	});	
	if(~step2 == 86, {
		~alto1_1.set(\gates, 0);
		task.stop;
	});
	if(~step2 == 108, {
		~alto1_1.set(\gates, 0);
	});	
	if(~step2 == 118, {
		~alto1_1.set(\gates, 0);
	});	
	if((~step2 >= 119).and(~step2 <= 177).and(~step2.even), {	
	~fft2.set(\adjVol, ~step2.linexp(119,177,1.0,0.8));
	});
	//if(~step2 == 158, {
	//~fft2.set(\gates, 0);
	//});
//	if(~step2 == 178, {
	//~fft2.set(\gates, 0);
	//});
	
	//end noteOff	
}

alto2Score { arg note, vel;
var justnote, mid3, volsteps3;
	justnote = note.midijust(415);
	~step3.post; '/A2'.postln;
	
	//noteOn
	
	if(~step3 == 39, {
	volsteps = ~globamp1*initSteps;
	volsteps2 = ~globamp1*initLowFFT;
	volsteps3 = ~globamp1*initHighFFT;
	~globamp6 = volsteps2;
	mid = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(volsteps.ampdb).linlin(0,1,0,127).round(1);
	mid2 = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(volsteps2.ampdb).linlin(0,1,0,127).round(1);
	mid3 = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(volsteps3.ampdb).linlin(0,1,0,127).round(1);
	control.control(0, 83, mid);
	control.control(0, 85, mid2);
	control.control(0, 86, mid2);
	
	~alto2.set(\globamp, volsteps);
	~fft5.set(\globamp, volsteps2);
	~fft6.set(\globamp, volsteps3);	
	
	envelopeControl.env(Env.new([0, 1], [makenv1[1][atk]], makenv1[1][crv])); //attack
	'START STEPS! START STEPS! START STEPS! START STEPS! START STEPS! START STEPS! START STEPS! START STEPS! START STEPS! START STEPS! START STEPS!'.postln;
	});
	
	if(~step3 == 61, {
	index6 = (66, 67.. 511);
	index6 = index6.scramble;
	~fft6.set(\atk, makenv2[0][atk], \dec, makenv2[0][dec], \sus, makenv2[0][sus], \rel, makenv2[0][rel], \crv, makenv2[0][crv])}
	);
	
	if((~step3 >= 63).and(~step3 < 95).and(~step3.odd), {	
	array1 = array1.put(index6[~step3-63], 0);
	array2 = array2.put(index6[~step3-63], 0);
	array6 = array6.put(index6[~step3-63], 1);
	buff1.setn(0, array1);
	buff2.setn(0, array2);
	buff6.setn(0, array6);
	});
	
	if((~step3 >= 63).and(~step3 <= 126).and(~step3.odd), {	
	~fft6.set(\adjVol, ~step3.linexp(63,126,0.4,1.0));
	});
	if((~step3 >= 127).and(~step3 <= 183).and(~step3.odd), {	
	~fft6.set(\adjVol, ~step3.linexp(127,183,1.0,0.8));
	~fft3.set(\adjVol, ~step3.linexp(127,183,1.0,0.8));
	});
	
	if((~step3 >= 83).and(~step3.odd), {
	~fft6.set(\amp, vel.linlin(0,127, 0, 6.0));
	});
	if(~step3 == 83, {
	~fft6.set(\gates, 1, \atk, makenv2[1][atk], \dec, makenv2[1][dec], \sus, makenv2[1][sus], \rel, makenv2[1][rel], \crv, makenv2[1][crv]);
	});

	if(~step3 == 89, {
	~fft6.set(\gates, 1, \atk, makenv2[2][atk], \dec, makenv2[2][dec], \sus, makenv2[2][sus], \rel, makenv2[2][rel], \crv, makenv2[2][crv]);
	});
	if(~step3 == 103, {
	~fft6.set(\gates, 1, \atk, makenv2[3][atk], \dec, makenv2[3][dec], \sus, makenv2[3][sus], \rel, makenv2[3][rel], \crv, makenv2[3][crv]);
	});
	if(~step3 == 111, {
	~fft6.set(\gates, 1, \atk, makenv2[4][atk], \dec, makenv2[4][dec], \sus, makenv2[4][sus], \rel, makenv2[4][rel], \crv, makenv2[4][crv]);
	});
	if((~step3 >= 117).and(~step3.odd), {
	~fft3.set(\amp, vel.linlin(0,127, 0, 6.0));
	});
	if(~step3 == 117, {
	~fft3.set(\gates, 1, \atk, makenv2[5][atk], \dec, makenv2[5][dec], \sus, makenv2[5][sus], \rel, makenv2[5][rel], \crv, makenv2[5][crv]);
	});
	if(~step3 == 151, {
	~fft3.set(\gates, 1, \atk, makenv1[7][atk], \dec, makenv1[7][dec], \sus, makenv1[7][sus], \rel, makenv1[7][rel], \crv, makenv1[7][crv]);
	});
	if(~step3 == 155, {
	~fft3.set(\gates, 1, \atk, makenv1[8][atk], \dec, makenv1[8][dec], \sus, makenv1[8][sus], \rel, makenv1[8][rel], \crv, makenv1[8][crv]);
	});
	if(~step3 == 185, {
	partials.stoptask;
	control.control(0, 83, mid2);
	~fft3.set(\globamp, volsteps2);
	~fft3.set(\gates, 1, \adjVol, 0.7, \atk, makenv1[9][atk], \dec, makenv1[9][dec], \sus, makenv1[9][sus], \rel, makenv1[9][rel], \crv, makenv1[9][crv]);
	});
	if(~step3 == 187, {
	control.control(0, 86, mid2);
	~fft6.set(\globamp, volsteps2);	
	~fft6.set(\gates, 1, \adjVol, 0.7, \atk, makenv2[6][atk], \dec, makenv2[6][dec], \sus, makenv2[6][sus], \rel, makenv2[6][rel], \crv, makenv2[6][crv]);
	});
	if(~step3 == 189, {
	routine2.stop;
	routine3.play;
	});
	if((~step3 >= 189).and(~step3 <= 215).and(~step3.odd), {
	ffttime = ~step3.linexp(189, 215, 0.56, 0.01);
	});
	if((~step3 >= 195).and(~step3 <= 285).and(~step3.odd), {
	~scale = ~step3.linlin(195, 285, 1.0, 1.5);
	});
	if(~step3 == 229, {
	'GETTING THERE! GETTING THERE! GETTING THERE! GETTING THERE! GETTING THERE! GETTING THERE! GETTING THERE! GETTING THERE! GETTING THERE! GETTING THERE! GETTING THERE!'.postln; 
	});
	if(~step3 == 253, {
	'FINISH IT! FINISH IT! FINISH IT! FINISH IT! FINISH IT! FINISH IT! FINISH IT! FINISH IT! FINISH IT! FINISH IT! FINISH IT!'.postln; 
	});
	if(~step3 == 285, {
	routine3.stop;
	routine4.play;
	});
	
		
	//noteoff
	
	if(~step3 == 62, {
	envelopeControl.env(Env.new([1, 0], [makenv1[1][rel]], makenv1[1][crv])); //relese
	});				
	
	if((~step3 >= 63).and(~step3 < 95).and(~step3.even), {	
	
	array1 = array1.put(index6[~step3-63], 0);
	array2 = array2.put(index6[~step3-63], 0);
	array6 = array6.put(index6[~step3-63], 1);
	buff1.setn(0, array1);
	buff2.setn(0, array2);
	buff6.setn(0, array6);
	});
	
	if((~step3 >= 63).and(~step3 <= 126).and(~step3.even), {	
	~fft6.set(\adjVol, ~step3.linexp(63,126,0.4,0.9));
	});
	if((~step3 >= 127).and(~step3 <= 183).and(~step3.even), {	
	~fft6.set(\adjVol, ~step3.linexp(127,183,1.0,0.8));
	~fft3.set(\adjVol, ~step3.linexp(127,183,1.0,0.8));
	});
	
	if(~step3 == 82, {
	~fft6.set(\gates, 0);
	});
	if(~step3 == 88, {
	~fft6.set(\gates, 0);
	});
	if(~step3 == 94, {
	~fft6.set(\gates, 0);
	});
	if(~step3 == 110, {
	~fft6.set(\gates, 0);
	});	
	if(~step3 == 116, {
	~fft6.set(\gates, 0);
	});		
	if(~step3 == 184, {
	~fft6.set(\gates, 0);
	});	
	
	if((~step3 >= 189).and(~step3 <= 215).and(~step3.even), {
	ffttime = ~step3.linexp(189, 215, 0.56, 0.01);
	});
	if((~step3 >= 195).and(~step3 <= 285).and(~step3.even), {
	~scale = ~step3.linlin(195, 285, 1.0, 1.5);
	});
	//end noteOff

}

tenorScore { arg note, vel; 
	var justnote, mid3, volharp;
	justnote = note.midijust(415);
	~step4.post; '/T'.postln;
	
		//noteOn
	
	if(~step4 == 55, {
	envelopeControl.env(Env.new([0, 1], [makenv1[2][atk]], makenv1[2][crv])); //attack
	});
	if(~step4 == 75, {
	volharp = initHarp;
	mid3 = ControlSpec(0.ampdb, 1.ampdb, \db, units: "dB").unmap(volharp.ampdb).linlin(0,1,0,127).round(1);
	control.control(0, 84, mid3);
	~tenor.set(\globamp, volharp);
	~tenor.add(\korea, 0, [\amp, 1.3, \lengh, 11, \bufnum, 89]);
	});
	if(~step4 == 81, {
	envelopeControl.env(Env.new([0, 1], [makenv1[3][atk]], makenv1[3][crv])); //attack
	});
	if(~step4 == 107, {
	control.control(0, 84, mid2);
	});
	if((~step4 >= 107).and(~step4 <= 159).and(~step4.odd), {	
	~fft4.set(\adjVol, ~step4.linexp(107,159,1.0,0.8));
	});
	if((~step4 >= 123).and(~step4.odd), {
	~fft4.set(\amp, vel.linlin(0,127, 0, 6.0));
	});
	if(~step4 == 123, {
	~fft4.set(\gates, 1, \atk, makenv4[0][atk], \dec, makenv4[0][dec], \sus, makenv4[0][sus], \rel, makenv4[0][rel], \crv, makenv4[0][crv]);
	});
	if(~step4 == 161, {
	~fft4.set(\gates, 1, \adjVol, 0.7, \atk, makenv4[1][atk], \dec, makenv4[1][dec], \sus, makenv4[1][sus], \rel, makenv4[1][rel], \crv, makenv4[1][crv]);
	});

	//noteoff
	if(~step4 == 74, {
	envelopeControl.env(Env.new([1, 0], [makenv1[2][rel]], makenv1[2][crv])); //relese
	});	
	if(~step4 == 106, {
		//filter for steps
		~alto2[2] = \filter -> { arg in, bufnum=9, fftbuf = 8;
		var chain, signal, signal2;
		chain = FFT(fftbuf, in);
		chain = chain.pvcollect(1024, {|mag, phase, index|
		[mag*Index.kr(bufnum, index), phase];
		}, frombin: 0, tobin: 250, zeroothers: 1);	
		signal = Mix.ar(0.5 * IFFT(chain).dup); };
	});
	if((~step4 >= 107).and(~step4 <= 159).and(~step4.even), {	
	~fft4.set(\adjVol, ~step4.linexp(107,159,1.0,0.8));
	});
	if(~step4 == 122, {
	~fft4.set(\gates, 0);
	});
	if(~step4 == 160, {
	~fft4.set(\gates, 0);
	envelopeControl.env(Env.new([1, 0], [rrand(1.2,2.0)], makenv1[11][crv])); //relese
	});
	
	//end noteOff

}

bassScore { arg note, vel; 
var justnote, mid;
	~step5.post; '/B'.postln;
	
	//noteOn
	if((~step5 >= 39).and(~step5.odd), {
	~fft5.set(\amp, vel.linlin(0,127, 0, 6.0));
	});
	if(~step5 == 39, {
	~fft5.set(\atk, makenv3[0][atk], \dec, makenv3[0][dec], \sus, makenv3[0][sus], \rel, makenv3[0][rel], \crv,makenv3[0][crv]);
	});
	
	if((~step5 >= 39).and(~step5 <= 77).and(~step5.odd), {	
	
	~fft5.set(\adjVol, ~step5.linexp(39,77,0.4,1.0));
	
	newbin = rrand(0,4);

	array1.put(newbin, 0);
	array2.put(newbin, 0);
	array5.put(newbin, 1);
	
	buff1.setn(0, array1);
	buff2.setn(0, array2);
	buff5.setn(0, array5);
	});
	
	if((~step5 >= 77).and(~step5 <= 105).and(~step5.odd), {	
	~fft5.set(\adjVol, ~step5.linexp(77,105,1.0,0.8));
	});
	
	if(~step5 == 93, {
	~fft5.set(\gates, 1, \atk, makenv3[1][atk], \dec, makenv3[1][dec], \sus, makenv3[1][sus], \rel, makenv3[1][rel], \crv,makenv3[1][crv]);
	});
	if(~step5 == 101, {
	~fft5.set(\gates, 1, \atk, makenv3[2][atk], \dec, makenv3[2][dec], \sus, makenv3[2][sus], \rel, makenv3[2][rel], \crv,makenv3[2][crv]);
	});
	if(~step5 == 107, {
	control.control(0, 85, mid2);
	~fft5.set(\globamp, volsteps2);
	~fft5.set(\gates, 1, \adjVol, 0.7, \atk, makenv3[3][atk], \dec, makenv3[3][dec], \sus, makenv3[3][sus], \rel, makenv3[3][rel], \crv,makenv3[3][crv]);
	});
	
	//noteoff
	if((~step5 >= 39).and(~step5 <= 77).and(~step5.even), {	
	~fft5.set(\adjVol, ~step5.linexp(39,77,0.4,1.0));
	});
	if((~step5 >= 77).and(~step5 <= 105).and(~step5.even), {	
	~fft5.set(\adjVol, ~step5.linexp(77,105,1.0,0.8));
	});
	if(~step5 == 92, {
	~fft5.set(\gates, 0);
	});
	if(~step5 == 100, {
	~fft5.set(\gates, 0);
	});
	if(~step5 == 106, {
	~fft5.set(\gates, 0);
	});
	
	//end noteOff

}

}
