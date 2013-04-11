package fr.pcreations.labs.RESTDroid.modules.ORMLiteJacksonModule;


import java.io.InputStream;

import fr.pcreations.labs.RESTDroid.core.Processor;
import fr.pcreations.labs.RESTDroid.core.RESTRequest;
import fr.pcreations.labs.RESTDroid.core.ResourceRepresentation;
import fr.pcreations.labs.RESTDroid.exceptions.ParsingException;

public class ORMLiteJacksonProcessor extends Processor{

	@Override
	protected void preGetRequest(RESTRequest<ResourceRepresentation<?>> r) {
		// TODO Auto-generated method stub
	}

	@Override
	protected void preDeleteRequest(RESTRequest<ResourceRepresentation<?>> r) {
		// TODO Auto-generated method stub
	}

	@Override
	protected InputStream prePostRequest(RESTRequest<ResourceRepresentation<?>> r) {
		InputStream is = null;
		try {
			is =  parseToInputStream(r.getResourceRepresentation());
		} catch (ParsingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return is;
	}

	@Override
	protected InputStream prePutRequest(RESTRequest<ResourceRepresentation<?>> r) {
		return prePostRequest(r);
	}

	@Override
	protected int postRequestProcess(
		int statusCode, RESTRequest<ResourceRepresentation<?>> r, InputStream resultStream) {
    	return updateLocalResource(statusCode, r, resultStream);
	}

	@Override
	protected void preRequestProcess(RESTRequest<ResourceRepresentation<?>> r) {
		mirrorServerState(r);
	}

}
