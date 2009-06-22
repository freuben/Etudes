EtudesMain { var s, n, <h, <step5, <>control, <>sopranoOut=0, <>alto1Out=1, <>alto2Out=2, <>tenorOut=3, <>bassOut=4, <>pianoOut=5, <>gagokOut=2, <midiPad = 112951606, <traveler = -462705089, <midiSportInA = 194243008, <midiSportInB = -812038230, <behringer=1396639616, makenv1, makenv2, makenv3, makenv4, makenv5, makenv6, atk, dec, rel, sus, crv, <documentPath, <midiBehringer=1165770703, <midiTraveler=657259080;

	init {
	s = Server.local;
	h = MIDIOut(0, midiTraveler); //midi piano 
	control = MIDIOut(0, midiBehringer); //midi controller  
	documentPath = Document.current.path.dirname;
	}


}