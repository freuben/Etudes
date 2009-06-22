RecorderEnv2 {var <envelope, <recfunc, <array, <envelope2, <array2, <atkenvelope, <atkenv, <relenvelope, <relarray;

newenvelope {var atkarray1,atkarray2,atkarray3,atkarray4,atkarray5,atkarray;
var relarray1, relarray2, relarray3, relarray4, relarray5, relarray;
var newatkarray, halfatkarray, newrelarray;

atkarray1 = [0.060907449573278, 0.083372466266155];

atkarray2 = [0.061503179371357, 0.061614610254765];

atkarray3 = [0.060272984206676, 0.060272220522165];

atkarray4 = [0.068115934729576, 0.099874332547188];

atkarray5 = [0.099285550415516, 0.13064280152321];

relarray1 = [0.040561612695456, 0.030211200937629];

relarray2 = [0.095268800854683, 0.048584051430225];

relarray3 = [0.083419695496559, 0.071392461657524];

relarray4 = [0.083419695496559, 0.071392461657524];

relarray5 = [0.083419695496559, 0.071392461657524];

atkarray = [atkarray1, atkarray2, atkarray3, atkarray4, atkarray5].choose;
atkarray = atkarray.linlin(0,atkarray.maxItem, 0, 1.0);

relarray = [relarray1, relarray2, relarray3, relarray4, relarray5].choose;
relarray = relarray.linlin(0,relarray.maxItem, 0, 1.0);

array = relarray ++ atkarray;
envelope = Env.new(array , Array.fill((array.size-1), {rrand(0.024,0.026)}), -4);
}

newatkenv {var atkenv1,atkenv2,atkenv3,atkenv4,atkenv5;

atkenv1 = [0, 0.0010749424109235, 0.0280427839607, 0.060907449573278, 0.083372466266155];

atkenv2 = [0, 0.019958175718784, 0.030818866565824, 0.0275321546942, 0.036524850875139, 0.061503179371357];

atkenv3 = [0, 0.012631084024906, 0.060497473925352];

atkenv4 = [0, 0.029452143236995, 0.058831982314587, 0.068115934729576, 0.099874332547188];

atkenv5 = [0, 0.036668673157692, 0.060110483318567, 0.099285550415516, 0.13064280152321, 0.10949608683586];

atkenv = [atkenv1, atkenv2, atkenv3, atkenv4, atkenv5].choose;

atkenv = atkenv * 1/atkenv.maxItem;
atkenvelope = Env.new(atkenv , Array.fill((atkenv.size-1), {rrand(0.024,0.026)}), -4);
}

newrelenv {var relarray1, relarray2, relarray3, relarray4, relarray5;

relarray1 = [0.040561612695456, 0.015666930004954, 0.0061078215949237, 0.00012483559839893, 0.0];

relarray2 = [0.095268800854683, 0.0037656838539988, 0.00067141244653612, 0.0];

relarray3 = [0.083419695496559, 0.031129909679294, 0.012750434689224, 0.0038260500878096, 0.0069247987121344, 0.0025471488479525, 2.9635625509172e-08, 0.0];

relarray4 = [0.083419695496559, 0.031129909679294, 0.012750434689224,  0.0038260500878096,  0.0069247987121344,  0.0025471488479525, 2.9635625509172e-08, 0.0];

relarray5 = [0.083419695496559, 0.031129909679294, 0.012750434689224, 0.0038260500878096, 0.0069247987121344, 0.0025471488479525, 2.9635625509172e-08, 0.0];

relarray = [relarray1, relarray2, relarray3, relarray4, relarray5].choose;

relarray = relarray * 1/relarray.maxItem;
relenvelope = Env.new(relarray , Array.fill((relarray.size-1), {rrand(0.049,0.051)}), -4);
}

synthy { recfunc = { arg i_out, noise = 0.06, gates = 0.0, pan, amp=1.0, oct=1, eg=1, noisefreq=1, bufnum=30, rate = 1.0, sinenv = 1, gates2 = 0, noise2 = 0.2, gates3 = 1, delayon = 0.2, delayoff = 0.2, highfilter = 20, lag = 0.1, globamp=1.0, vol=1.0, audioOut=0, overtone=1.0, endgate = 1, sinamp=1.0, delaytime = 0.2;
	var out, fc, osc, a, b, w, decay, finenv;
	var freqs, amps, ringtimes, signal, freqmul, freqadds, ampmul, ampadds, chaosenv;
	var gagok, ampgagok, freq, hasFreq, signal2, signal3, noisy, chain, in, twonoisy;
	
	gagok = PlayBuf.ar(1, bufnum, rate, loop: 1);

	#freq, hasFreq = Pitch.kr(gagok, 130.81, ampThreshold: 0.0, median: 7).lag(0.1);


	freqmul = [ 9.450554, 77.379639, 71.312439, 49.405464, 89.68457, 41.950317, 35.728699, 50.253052, 57.868775, 88.477417, 5.610855, 55.063965, 15.767761, 65.577515, 19.763427, 18.997803, 66.784423, 14.455932, 24.67456, 55.316193, 7.328951, 79.102905, 48.867524, 52.945923 ];
	
	freqadds = [ 47.151604, 652.5390015, 848.3708805, 122.294613, 555.541565, 1768.6453245, 766.7276305, 1214.050476, 1560.3202515, 917.3297725, 73.4706495, 1103.9290775, 434.1169435, 358.8726195, 3075.9576415, 2626.2139895, 2225.3343505, 1321.413025, 3953.709839, 379.8906405, 26.3069925, 1380.5829465, 506.659256, 1263.5023805 ];
	
	ampmul = [ 0.008254, 0.0033, 0.003776, 0.001352, 0.001261, 0.001976, 0.001836, 0.00721, 0.002404, 0.010734, 0.004557, 0.001812, 0.362847, 0.001641, 0.00513, 0.001688, 0.004599, 0.047547, 0.001959, 0.002762, 0.010118, 0.003789, 0.003316, 0.002872 ];

	ampadds = [ 0.00921, 0.001723, 0.002006, 0.001155, 0.0007145, 0.001032, 0.000979, 0.003681, 0.001239, 0.005454, 0.0042995, 0.000954, 0.1815525, 0.0009725, 0.002618, 0.000896, 0.0023925, 0.0241035, 0.0011225, 0.001713, 0.006376, 0.0020915, 0.002032, 0.001436 ];
	
	freqs = (LFNoise1.kr(10, freqmul, freqadds) * 0.418225) * ((freq*oct)/192.3);
	amps = LFNoise1.kr(10, ampmul, ampadds);
	ampgagok = Amplitude.kr(gagok);
	fc = LinExp.kr(LFNoise1.kr(Rand(0.25,0.4)*noisefreq), -1,1,500,2000);
	osc = Mix.fill(8, {SinOsc.ar((freq*oct) * [Rand(0.999,1.001),Rand(0.999,1.001)], 0, 0.1*((freq*oct)/400)) }).distort * 0.1;
	
	chaosenv = (((CuspL.ar(5, 1.2, 1.8, 0.3)).abs)*1.0)+0.4;
	
	in = PlayBuf.ar(1, 3, BufRateScale.kr(3), loop: 1);
	
	noisy = (in*noise)*max(amps.sum.lag(2.0).linlin(0.0,0.3,0.0, 0.1));
	
	noisy = noisy * EnvGen.kr(Env.dasdr(delayon, chaosenv,1.0, delayon,chaosenv,'sine'), gates3);
	noisy = HPF.ar(noisy, Lag.kr(highfilter, lag));
	
	chain = FFT(27, in);
	chain = chain.pvcalc(1024, {|mags, phases|
	[mags.sqrt, phases]; 
	}, frombin: 0, tobin: 250, zeroothers: 0);
	twonoisy = (noise2 * IFFT(chain).dup)*max(ampgagok.lag(2.0).linlin(0.0,0.3,0.3, 0.0));
	twonoisy = Mix.ar(twonoisy) * EnvGen.kr(Env.dasdr(delayoff, chaosenv,1.0, (chaosenv-0.2), 0.0,'sine'), gates2); //release envelope 0.2 less than attack
	out = ( RLPF.ar((osc*sinamp)*sinenv, fc, 0.1,1.0)*0.2);
	decay = ampgagok.linlin(0.0, 0.3, 0, 50.0)*overtone;
	signal = DynKlank.ar(`[freqs, amps, Array.fill(freqs.size, {rrand(0.1, 0.5)})*decay.lag(0.2)], ((out * EnvGen.kr(Env.dasr(delayon, chaosenv+0.2,1.0,chaosenv+0.15,'sine'), gates3))+ noisy));
	signal2 = (signal*gates)+BPF.ar(twonoisy, 1276, 1.91849);
	signal3 = (Mix.ar((signal2*0.4)*eg)).linlin(-1.0,1.0,-0.8, 0.8)*amp; 
	finenv = EnvGen.kr(Env.asr(0.2, 1.0, 0.5), endgate)*Lag.kr(vol, 1.6);
	Out.ar(audioOut, (DelayL.ar(signal3, delaytime, delaytime)*finenv)*globamp);
	}
	
}


}