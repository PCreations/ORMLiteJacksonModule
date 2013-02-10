package fr.pcreations.labs.RESTDroid.modules.ORMLiteJacksonModule;


import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;

import fr.pcreations.labs.RESTDroid.core.DaoFactory;
import fr.pcreations.labs.RESTDroid.core.Module;
import fr.pcreations.labs.RESTDroid.core.ParserFactory;
import fr.pcreations.labs.RESTDroid.core.Processor;

public class ORMLiteJacksonModule extends Module {
	
	private OrmLiteSqliteOpenHelper mDatabaseHelper;
	
	public ORMLiteJacksonModule(OrmLiteSqliteOpenHelper helper) {
		mDatabaseHelper = helper;
	}
	
	@Override
	public Processor setProcessor() {
		return new ORMLiteJacksonProcessor();
	}

	@Override
	public ParserFactory setParserFactory() {
		return new SimpleJacksonParserFactory();
	}

	@Override
	public DaoFactory setDaoFactory() {
		return new ORMLiteDaoFactory(mDatabaseHelper);
	}

}
