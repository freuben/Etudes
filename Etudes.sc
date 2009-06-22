Etudes : EtudesMain { var <doc, <>step1on, step2on, step3on, step4on, step5on, step1off, step2off, step3off, step4off, step5off, <>routine1on, routine2on, routine3on, routine4on, routine5on, routine1off, routine2off, routine3off, routine4off, routine5off, <now, <>network;
var freez1, freez2, freez3, freez4, freez5;

	*new {arg ipAddress = "169.254.80.149";
	^super.new.initEtudes(ipAddress);
	}
	
	initEtudes {arg ipAddress;
	network = ipAddress;
	n = NetAddr(ipAddress, 57120);
	MIDIIn.allsources; 
	routine1on = Routine({		
		{ step1on = step1on + 2;
		 // 'S/ '.post; step1on.postln;
		  nil.yield;  
		}.loop; //routine for counting soprano noteOn events 
	});
	
	routine1off = Routine({		
		{ step1off = step1off + 2;
		 // 'S/ '.post; step1off.postln;
		  nil.yield;  
		}.loop;  //routine for counting soprano noteOff events
	});
	routine2on = Routine({		
		{ step2on = step2on + 2;
		  //'A1/ '.post; step2on.postln;
		  nil.yield;  
		}.loop;  //routine for counting alto1 noteIn events 
	});
	
	routine2off = Routine({		
		{ step2off = step2off + 2;
		  //'A1/ '.post; step2off.postln;
		  nil.yield;  
		}.loop; //routine for counting alto1 noteOff events
	});
	
	routine3on = Routine({		
		{ step3on = step3on + 2;
		  //'A2/ '.post; step3on.postln;
		  nil.yield;  
		}.loop; //routine for counting alto2 MIDIIn events 
	});
	
	routine3off = Routine({		
		{ step3off = step3off + 2;
		  //'A2/ '.post; step3off.postln;
		  nil.yield;  
		}.loop; //routine for counting alto2 MIDIOff events 
	});
	
	routine4on = Routine({		
		{ step4on = step4on + 2;
		  //'T/ '.post; step4on.postln; 
		  nil.yield;  
		}.loop; //routine for counting tenor noteOn events 
	});
	
	routine4off = Routine({		
		{ step4off = step4off + 2;
		  //'T/ '.post; step4off.postln; 
		  nil.yield;  
		}.loop; //routine for counting tenor noteOff events 
	});
	
	routine5on = Routine({		
		{ step5on = step5on + 2;
		  //'B/ '.post; step5on.postln; 
		  nil.yield;  
		}.loop;  //routine for counting bass noteOn events 
	});
	
	routine5off = Routine({		
		{ step5off = step5off + 2;
		  //'B/ '.post; step5off.postln; 
		  nil.yield;  
		}.loop; //routine for counting bass noteOff events
	});
	this.init;	
}
	
	clocks {
	SystemClock.sched(0.0,{ arg time;  
		now = time; 
		 0.01
	});
	SystemClock.sched(0.0,{ arg time;  
					freez1 = time; 
					 nil
					});
	SystemClock.sched(0.0,{ arg time;  
					freez2 = time; 
					 nil
					});
	SystemClock.sched(0.0,{ arg time;  
					freez3 = time; 
					 nil
					});								
	SystemClock.sched(0.0,{ arg time;  
					freez4 = time; 
					 nil
					});
	SystemClock.sched(0.0,{ arg time;  
					freez5 = time; 
					 nil
					});		
}

	stepstart {
	'S/ '.post; step1on = ~step1-1; step1off = ~step1.postln;
	'A1/ '.post; step2on = ~step2-1; step2off = ~step2.postln;
	'A2/ '.post; step3on = ~step3-1; step3off = ~step3.postln;
	'T/ '.post; step4on = ~step4-1; step4off = ~step4.postln;
	'B/ '.post; step5on = ~step5-1; step5off = ~step5.postln;
}

	document {arg path;
doc = Document.open(path);
//a.background_(Color.blue(alpha:0.999));
doc.bounds_(Rect(870, 528, 560, 460));
doc.front;
Document.allDocuments.at(0).front;
}

wakePianos {n.sendMsg("/midi", 53, 53);
} 

}