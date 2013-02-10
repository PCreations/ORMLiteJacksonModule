package fr.pcreations.labs.RESTDroid.modules.ORMLiteJacksonModule;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;

import fr.pcreations.labs.RESTDroid.exceptions.DatabaseManagerNotInitializedException;

public interface HelperGetter {

	abstract OrmLiteSqliteOpenHelper getHelper() throws DatabaseManagerNotInitializedException;
	
}
