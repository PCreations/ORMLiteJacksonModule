package fr.pcreations.labs.RESTDroid.modules.ORMLiteJacksonModule;


import fr.pcreations.labs.RESTDroid.core.Parser;
import fr.pcreations.labs.RESTDroid.core.ParserFactory;
import fr.pcreations.labs.RESTDroid.core.Resource;


public class SimpleJacksonParserFactory extends ParserFactory{

	@SuppressWarnings("unchecked")
	@Override
	public <P extends Parser<T>, T extends Resource> P getParser(Class<T> clazz) {
		if(mParserMap.containsKey(clazz))
			return (P) mParserMap.get(clazz);
		
		mParserMap.put(clazz, new SimpleJacksonParser(clazz));
		return (P) mParserMap.get(clazz);
	}

}
