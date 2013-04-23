package fr.pcreations.labs.RESTDroid.modules.ORMLiteJacksonModule;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.util.Log;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.pcreations.labs.RESTDroid.core.Parser;
import fr.pcreations.labs.RESTDroid.core.Resource;
import fr.pcreations.labs.RESTDroid.core.ResourceRepresentation;
import fr.pcreations.labs.RESTDroid.core.RestService;
import fr.pcreations.labs.RESTDroid.exceptions.ParsingException;

public class SimpleJacksonParser implements Parser<Resource>{

	
	public final static int DATA_OK = 0;
	public final static int PARSER_KO = -1;
	public final static int PARSER_KO_JSON_MALFORMED = -2;
	public final static int PARSER_KO_JSON_OBJETS_INVALID = -3;
	public ObjectMapper mJSONMapper;
	protected int mResultCode;
	protected String mSimpleClassName;
	protected Class<?> mClazz;
	
	public <T extends Resource> SimpleJacksonParser(Class<T> clazz)
	{
		super();
		mJSONMapper = new ObjectMapper(); // can reuse, share globally
		mJSONMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mJSONMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		mResultCode = 0;
		mClazz = clazz;
		mSimpleClassName = clazz.getSimpleName();
		Log.i(RestService.TAG, "Simple class Name JacksonParser = " + mSimpleClassName);
	}
	
	public Resource parseToObject(InputStream content) throws ParsingException{
		Resource JSONObjResponse = null;
		try {
			JSONObjResponse = (Resource) mJSONMapper.readValue(content, mClazz);
			if(null != JSONObjResponse)
				setResultCode(DATA_OK);
		} catch (JsonParseException e) {
			setResultCode(PARSER_KO_JSON_MALFORMED);
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
			setResultCode(PARSER_KO_JSON_OBJETS_INVALID);
		} catch (IOException e) {
			e.printStackTrace();
			setResultCode(PARSER_KO);
		}

		if(mResultCode != 0)
			throw new ParsingException(mResultCode);
		
		return JSONObjResponse;
	}
	
	@Override
	public InputStream parseToInputStream(Resource resource) throws ParsingException {
		ByteArrayOutputStream JSONstream = new ByteArrayOutputStream();
		try {
			mJSONMapper.writeValue(JSONstream, resource);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(mResultCode != 0)
			throw new ParsingException(mResultCode);
		
		return new ByteArrayInputStream(JSONstream.toByteArray());
	}
	
	protected void setResultCode(int resultCode) {
		mResultCode = resultCode;
	}

	/**
	 * @return the mResultCode
	 */
	public int getResultCode() {
		return mResultCode;
	}
	
	public ObjectMapper getObjectMapper() {
		return mJSONMapper;
	}

}

