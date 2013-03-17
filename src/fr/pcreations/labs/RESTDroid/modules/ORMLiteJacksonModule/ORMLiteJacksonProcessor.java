package fr.pcreations.labs.RESTDroid.modules.ORMLiteJacksonModule;


import java.io.InputStream;

import fr.pcreations.labs.RESTDroid.core.Processor;
import fr.pcreations.labs.RESTDroid.core.RESTRequest;
import fr.pcreations.labs.RESTDroid.core.Resource;
import fr.pcreations.labs.RESTDroid.exceptions.ParsingException;

public class ORMLiteJacksonProcessor extends Processor{

	@Override
	protected void preGetRequest(RESTRequest<? extends Resource> r) {
		// TODO Auto-generated method stub
	}

	@Override
	protected void preDeleteRequest(RESTRequest<? extends Resource> r) {
		// TODO Auto-generated method stub
	}

	@Override
	protected InputStream prePostRequest(RESTRequest<? extends Resource> r) {
		InputStream is = null;
		try {
			is =  parseToInputStream(r.getResource());
		} catch (ParsingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return is;
	}

	@Override
	protected InputStream prePutRequest(RESTRequest<? extends Resource> r) {
		return prePostRequest(r);
	}

	@Override
	protected int postRequestProcess(
		int statusCode, RESTRequest<? extends Resource> r, InputStream resultStream) {
    	return updateLocalResource(statusCode, r, resultStream);
	}

	@Override
	protected void preRequestProcess(RESTRequest<? extends Resource> r) {
		mirrorServerState(r);
	}

}
