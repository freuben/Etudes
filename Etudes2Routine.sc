Etudes2Routine {var <>routine, routine2, thresh = 0.001;
var freqgagok = 192, <>interval = 3.0, <>osc, rateFreq = 150, rateAmp = 10;
var count = 0, step = 0, count2 = 0, step2 = 0, s;

*new {^super.new.initEtudes2Routine;
	}
	
	initEtudes2Routine {
	s = Server.local;
	}

startOsc {var buf, table;
 osc =  OSCresponder(s.addr,'/tr',{ arg time,responder,msg;
	if(msg[2] == 0,
		{	if(msg[3] < thresh, {step = step + 1; count = 0;}, 
			{step = 0; count = count + 1;});
		if(count == 2, 
			{~alto2.set(\gates2, 0);
			~alto2.set(\gates3, 1);	
			~alto1.set(\gates, 1, \delay, rrand(0.1,0.3));
			~gagokAudio2.set(\gates, 1, \delay, rrand(0.1,0.3));	
		});
		if(step == 2, 
			{~alto2.set(\gates3, 0);
			~alto2.set(\gates2, 1);
			~alto1.set(\gates, 0, \delay, rrand(0.1,0.3));
			~gagokAudio2.set(\gates, 0, \delay, rrand(0.1,0.3));
			});		
			
		});
		
	
	if(msg[2] == 1, {freqgagok = msg[3]; //this get's frequency
	}); 
});

}

routines {arg vocalOut = 2;
~pitch = 1;
routine = Routine({var first, second, step, jumpstep, timer, time1, time2, difTime, rate;
step = 0;
jumpstep = 0;
timer = 0.0;
time2 = 0.0;
difTime = 0.0;
~delay1 = 0.2;
~delay2 = 0.23;
second = freqgagok;
rate = ~pitch;
~alto2.set(\highfilter, freqgagok);
inf.do({
first = freqgagok;
if((first.cpsmidi - second.cpsmidi).abs > interval, 
	{if(step < 1, 
		{step = step + 1;},
		{step = 0; 
		jumpstep = jumpstep + 1; 
		if(jumpstep.odd, 
			{time1 = timer;
			difTime = difTime + ((time1 - time2)*~pitch);
			difTime; 
			
			rate = ~pitch;
			~delay1 = rrand(0.0, 0.4);
			~delay2 = rrand(0.0, 0.4);
			
				~env.newenvelope;
				
				Routine({1.do({
				0.2.yield;
				~gagokControl.env(~env.envelope);	
				~alto2.set(\rate, rate - 0.05 + rrand(0.0, 0.1));
				~gagokAudio.send([\start, difTime*44100, \rate, rate], 0);})
				}).play;
				
				Routine({1.do({
					~delay1.yield;
					~gagokControl2.env(~env.envelope);
					~alto1.set(\rate, rate - 0.05 + rrand(0.0, 0.1));});
				}).play;
				
				Routine({1.do({
					~delay2.yield;
					~gagokControl3.env(~env.envelope);
					~gagokAudio2.set(\rate, rate - 0.1 + rrand(0.0, 0.2));});
				}).play;

			
			},
			
			{time2 = timer;
			difTime = difTime + ((time2 - time1)*~pitch);
			difTime; 
			
			rate = ~pitch;
			~delay1 = rrand(0.0, 0.4);
			~delay2 = rrand(0.0, 0.4);
				
				~env.newenvelope;
				
				Routine({1.do({
				0.2.yield;
				~gagokControl.env(~env.envelope); 
				~alto2.set(\rate, rate - 0.05 + rrand(0.0, 0.1));
				~gagokAudio.send([\start, difTime*44100, \rate, rate], 0);})
				}).play;
				
				Routine({1.do({
					~delay1.yield;
					~gagokControl2.env(~env.envelope);
					~alto1.set(\rate, rate - 0.05 + rrand(0.0, 0.1));});
				}).play;
				
				Routine({1.do({
					~delay2.yield;
					~gagokControl3.env(~env.envelope);
					~gagokAudio2.set(\rate, rate - 0.1 + rrand(0.0, 0.2));});
				}).play;
				
			}
		);
		
		}
	)};
);

timer = timer + 0.1;		
0.1.yield;
second = freqgagok;
~alto2.set(\highfilter, freqgagok);
if((first.cpsmidi - second.cpsmidi).abs > interval, 
	{if(step < 1, 
		{step = step + 1;},
		{step = 0; 
		jumpstep = jumpstep + 1;  
		if(jumpstep.odd, 
			{time1 = timer;
			difTime = difTime + ((time1 - time2)*~pitch);
			difTime; 
			
			rate = ~pitch;
			~delay1 = rrand(0.0, 0.4);
			~delay2 = rrand(0.0, 0.4);
				
				~env.newenvelope;
				
				Routine({1.do({
				0.2.yield;
				~gagokControl.env(~env.envelope); 
				~alto2.set(\rate, rate - 0.05 + rrand(0.0, 0.1));
				~gagokAudio.send([\start, difTime*44100, \rate, rate], 0);})
				}).play;
				
				Routine({1.do({
					~delay1.yield;
					~gagokControl2.env(~env.envelope);
					~alto1.set(\rate, rate - 0.05 + rrand(0.0, 0.1));});
				}).play;
				
				Routine({1.do({
					~delay2.yield;
					~gagokControl3.env(~env.envelope);
					~gagokAudio2.set(\rate, rate - 0.1 + rrand(0.0, 0.2));});
				}).play;
			
			},
			
			{time2 = timer;
			difTime = difTime + ((time2 - time1)*~pitch);
			difTime;  
			
			rate = ~pitch;
			~delay1 = rrand(0.0, 0.4);
			~delay2 = rrand(0.0, 0.4);
			
				~env.newenvelope;
				
				Routine({1.do({
				0.2.yield;
				~gagokControl.env(~env.envelope); 
				~alto2.set(\rate, rate - 0.05 + rrand(0.0, 0.1));
				~gagokAudio.send([\start, difTime*44100, \rate, rate], 0);})
				}).play;
				
				Routine({1.do({
					~delay1.yield;
					~gagokControl2.env(~env.envelope);
					~alto1.set(\rate, rate - 0.05 + rrand(0.0, 0.1));});
				}).play;
				
				Routine({1.do({
					~delay2.yield;
					~gagokControl3.env(~env.envelope);
					~gagokAudio2.set(\rate, rate - 0.1 + rrand(0.0, 0.2));});
				}).play;

					
			}
		);
		
		
		}
	)};
);

timer = timer + 0.1;
0.1.yield;
})
});

//a lazy Routine
routine2 = Routine({var func;
1.do({
~env = RecorderEnv2.new;
~env.synthy;
~env.newatkenv;
~gagokControl = NodeProxy.control(s, 1); //main sound
~envelopeControl = NodeProxy.control(s, 1); //main sound
~envelopeControl2 = NodeProxy.control(s, 1); //nasal sound
~envelopeControl3 = NodeProxy.control(s, 1); //flute sound

~gagokAudio = NodeProxy.audio(s, 1); //tracking
~gagokAudio.play; //tracking 
~gagokAudio2 = NodeProxy.audio(s, 1); //flute sound
~gagokAudio2.play(0); //flute sound
~gagokControl2 = NodeProxy.control(s, 1); //nasal sound
~gagokControl3 = NodeProxy.control(s, 1); //flute sound

~gagokControl.source = 1.0;
~gagokControl2.source = 1.0;
~envelopeControl.source = 0.0;
~alto2.map(\sinenv, ~gagokControl); //map recorder envelopes
~alto2.map(\gates, ~envelopeControl); //envelop main
~alto1.map(\gates2, ~envelopeControl2); //envelope nasal
~alto1.map(\sinenv, ~gagokControl2); //map nasal
~gagokAudio2.map(\gates2, ~envelopeControl3); //envelope nasal
~gagokAudio2.map(\sinenv, ~gagokControl3); //map nasal


0.1.yield;

~gagokAudio.source = {arg gates = 1, start = 0.0, rate= 1.0; var gagok, freq, hasFreq, ampGagok;
gagok = PlayBuf.ar(1, 30, rate, 1.0, start, 1);
# freq, hasFreq = Pitch.kr(gagok, 192.3, ampThreshold: 0.0, median: 7);
ampGagok = Amplitude.kr(gagok);
	SendTrig.kr(Impulse.kr(rateAmp), 0, ampGagok);
	SendTrig.kr(Impulse.kr(rateFreq),1,freq);
};
~alto2.put(0, ~env.recfunc, 0, [\audioOut, vocalOut, \amp, 2.0, \noise, 0.36, \noise2, 0.52, \sinamp, 0.5]); 
~alto1.put(0, \reedgagaku, 0, [\gates, 0, \centerFreq, 391.96/2, \freqadj, 1.0, \amp, 1.0]);
~gagokAudio2.put(0, \flutegagok, 0, [\gates, 0, \centerFreq, 391.96/2, \freqadj, 1.0, \amp, 4.0, \globamp, 0.24607849215698]);
//start audio

~envelopeControl.env(~env.atkenvelope);
//~alto1.set(\delay, ~delay, \delaytime, ~delay);
0.2.yield;
routine.play; //start routine
})
});

routine2.play;

}

}